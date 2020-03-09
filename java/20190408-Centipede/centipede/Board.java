package centipede;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * 
 */
public class Board extends JPanel implements Runnable, Commons {
    
    private Dimension d;
    private ArrayList<Mushroom> mushrooms;
    private ArrayList<Centipede> centipedes;
    private Player player;
    private Spider spider;
    private Shot shot;
    
    private boolean ifLeft = true;
    private boolean ifDown = true;
    private boolean ifHit = false;
    
    private int lives = 1;
    private int score = 0;
    
    private boolean ingame = true;
    private String message;
    
    private Thread animator;
    
    public Board() {

        initBoard();
        
    }

    private void initBoard() {

        addMouseListener(new MAdapter());
        addMouseMotionListener(new MAdapter());
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH + 30, BOARD_HEIGHT + 40);
        setBackground(Color.black);

        gameInit();
        setDoubleBuffered(true);
        
    }
    
    @Override
    public void addNotify() {

        super.addNotify();
        gameInit();
    }
    
    public void gameInit() {

        // mushrooms
        mushrooms = new ArrayList<>();
        int num = 0;
        ArrayList<Integer> xp = new ArrayList<>();
        for (int i = 15; i < 320; i += 12) {
            for (int j = 15; j < 190; j += 12) {
                double r = Math.random();

                if(r < MUSHROOM_PRO && (xp.indexOf(i) == -1)){
                    Mushroom mr = new Mushroom(i, j);
                    mushrooms.add(mr);
                    xp.add(i);
                    num += 1;
                }
            }
        }
        
        // centipedes
        centipedes = new ArrayList<>();
        // int x = BOARD_WIDTH - (int)(CENTIPEDE_WIDTH*1.5);
        int x = 20;
        int y = 0;
        Centipede headCen = new Centipede(x, y);
        headCen.switchToHead();
        centipedes.add(headCen);
        for (int t = 0; t < CENTIPEDE_LEN; t++){
            x += CENTIPEDE_WIDTH;
            centipedes.add(new Centipede(x, y));
        }
        
        // spider
        spider = new Spider();
        
        // player and shot
        player = new Player();
        shot = new Shot();
        
        if (animator == null || !ingame) {

            animator = new Thread(this);
            animator.start();
        }
    }
    
    // show score and lives
    public void drawInfo(Graphics g){
        
        Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.black);
        g.setFont(small);
        
        message = "socre:" + score + " lives:" + lives;
        g.drawString(message, (BOARD_WIDTH - metr.stringWidth(message)) / 2 -10, 
                BOARD_WIDTH - 38);
    }
    
    public void drawMushroom(Graphics g) {

        Iterator it = mushrooms.iterator();

        for (Mushroom mr: mushrooms) {

            if (mr.isVisible()) {

                g.drawImage(mr.getImage(), mr.getX(), mr.getY(), this);
                //g.drawImage(mr.getImage(), 338, 318, this);
            }

            if (mr.isDying()) {

                mr.die();
            }
        }
        
    }
    
    public void restoreMushroom(){
        for (Mushroom mr: mushrooms) {

            if (mr.isVisible()) {

                mr.restore();
            }
        }
    }
    
    public void drawPlayer(Graphics g) {

        if (player.isVisible()) {

            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isDying()) {

                player.die();
                ingame = false;
        }
    }
    
    public void drawSpider(Graphics g){
        
        if (spider.isVisible()) {

            g.drawImage(spider.getImage(), spider.getX(), spider.getY(), this);
        }

        if (spider.isDying()) {

                spider.die();
        }
    }
    
    public void drawShot(Graphics g) {

        if (shot.isVisible()) {

                g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }
    
    public void nextCenPosition(boolean ifHit){
        boolean ifBlack = false;
        int nx = centipedes.get(0).getX();
        int ny = centipedes.get(0).getY();
        
        // update the first one
        if(ifDown){
            if(ifLeft){
                if (ifHit) {
                    ny += CENTIPEDE_WIDTH;
                    ifHit = false;
                    ifLeft = false;
                } else {
                    nx -= CENTIPEDE_WIDTH;
                }

            } else {
                if (ifHit) {
                    ifHit = false;
                    ny += CENTIPEDE_WIDTH;
                    ifLeft = true;
                } else {
                    nx += CENTIPEDE_HEIGHT;
                }
            }
        } else {
            if(ifLeft){
                if (ifHit) {
                    ny -= CENTIPEDE_WIDTH;
                    ifHit = false;
                    ifLeft = false;
                } else {
                    nx -= CENTIPEDE_WIDTH;
                }

            } else {
                if (ifHit) {
                    ifHit = false;
                    ny -= CENTIPEDE_WIDTH;
                    ifLeft = true;
                } else {
                    nx += CENTIPEDE_HEIGHT;
                }
            }
        }
        
        for (Centipede cen: centipedes){
            if(ifBlack){
                cen.switchToHead();
            }
            if(cen.isVisible()){
                ifBlack = false;
            } else {
                ifBlack = true;
            }
            int tx = cen.getX();
            int ty = cen.getY();
            cen.setX(nx);
            cen.setY(ny);
            nx = tx;
            ny = ty;
        }
    }
    
    public void drawCentipede(Graphics g){
        for (Centipede cen: centipedes){
            if (cen.isVisible()) {
                
                g.drawImage(cen.getImage(), cen.getX(), cen.getY(), this);
            }

            if (cen.isDying()) {

                    cen.die();
            }
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.white);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (ingame) {

            g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);
            drawInfo(g);
            drawMushroom(g);
            drawPlayer(g);
            drawSpider(g);
            drawShot(g);
            drawCentipede(g);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    public void gameOver() {

        Graphics g = this.getGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        message = "score:" + score;
        g.drawString(message, (BOARD_WIDTH - metr.stringWidth(message)) / 2,
                        BOARD_WIDTH / 2);
        
    }
    
    public int getMushroomRestoreMark(){
        int mark = 0;
        for (Mushroom mr: mushrooms) {
            if (mr.isVisible() && mr.getHealth() < 3){
                mark += 10;
            }
        }
        return mark;
    }
    
    public void animationCycle() {
        
        // player
        player.act();
        
        // spider
        spider.act();
        
        // centipede
        int shotX = shot.getX();
        int shotY = shot.getY();
        
        int pX = player.getX();
        int pY = player.getY();
        
        int sX = spider.getX();
        int sY = spider.getY();
        
        // spider and player
        if (player.isVisible() && spider.isVisible()) {
            int dx = (pX + MUSHROOM_WIDTH / 2) - (sX + SPIDER_WIDTH / 2);
            int dy = (pY + MUSHROOM_HEIGHT / 2) - (sY + SPIDER_WIDTH / 2);
            int ax = (MUSHROOM_WIDTH + SPIDER_WIDTH) / 2;
            int ay = (MUSHROOM_HEIGHT + SPIDER_HEIGHT) / 2;
            if (abs(dx) < ax && abs(dy) < ay) {
                score += getMushroomRestoreMark();
                if(lives <= 1){
                    ingame = false;
                } else {
                    lives -= 1;
                }
            }
        }
        
        // centipede and player
        for (Centipede cen: centipedes){
            if (cen.isVisible()){
                int cX = cen.getX();
                int cY = cen.getY();
                int dx = abs(cX - pX);
                int dy = abs(cY - pY);
                if (dx <= 10 && dy <= 10){
                    score += getMushroomRestoreMark();
                    if(lives <= 1){
                        ingame = false;
                    } else {
                        lives -= 1;
                    }
                }
            }
        }
        
        // centipede
        Centipede topCen = centipedes.get(0);
        for (Centipede cen: centipedes){
            if (cen.isVisible()){
                topCen = cen;
                break;
            }
        }
        
        // get next position
        int nextY = 0;
        int nextX = 0;
        

        if (ifLeft) {
            nextY = topCen.getY();
            nextX = topCen.getX() - CENTIPEDE_WIDTH;
        } else {
            nextY = topCen.getY();
            nextX = topCen.getX() + CENTIPEDE_WIDTH;
        }
            
        // over range
        if (nextY < 0 || nextY > BOARD_WIDTH  || nextX < 0 || nextX > BOARD_HEIGHT-20){
            ifHit = true;
        }
        if (nextY < 0){
            ifDown = true;
        }
        if (nextY > PLAYER_HEIGHT){
            ifDown = false;
        }
        else {
            for (Mushroom mr: mushrooms) {
                int mrX = mr.getX();
                int mrY = mr.getY();
                int dx = abs(nextX - mrX + 1);
                int dy = abs(nextY - mrY + 1);
                if (mr.isVisible()) {
                    if (dx < 10 && dy < 10 ) {
                        ifHit = true;
                    }
                }
            }
        }
        nextCenPosition(ifHit);
        ifHit = false;
        
        
        // shot
        if (shot.isVisible()) {

            // judge mushroom
            for (Mushroom mr: mushrooms) {
                int mrX = mr.getX();
                int mrY = mr.getY();
                if (mr.isVisible() && shot.isVisible()) {
                    if (shotX >= (mrX) && shotX <= (mrX + MUSHROOM_WIDTH)
                        && shotY >= (mrY) && shotY <= (mrY + MUSHROOM_HEIGHT)) {
                        mr.beHitted();
                        if(mr.isVisible()){
                            score += 1 ;
                        } else {
                            score += 5;
                        }
                        shot.die();
                    }
                }
            }
            
            // judge spider
            if (spider.isVisible() && shot.isVisible()) {
                if (shotX >= (sX) && shotX <= (sX + SPIDER_WIDTH)
                    && shotY >= (sY) && shotY <= (sY + SPIDER_HEIGHT)) {
                    spider.beHitted();
                    if(spider.isVisible()){
                        score += 100;
                    } else {
                        score += 500;
                    }
                    shot.die();
                }
            }
            
            // judge centipede
            for (Centipede cen: centipedes){
            if (cen.isVisible() && shot.isVisible()){
                int mrX = cen.getX();
                int mrY = cen.getY();
                if (cen.isVisible() && shot.isVisible()) {
                    if (shotX >= (mrX) && shotX <= (mrX + MUSHROOM_WIDTH)
                        && shotY >= (mrY) && shotY <= (mrY + MUSHROOM_HEIGHT)) {
                        cen.beHitted();
                        if(cen.isVisible()){
                            score += 2 ;
                        } else {
                            score += 5 ;
                        }
                        shot.die();
                    }
                }
            }
        }

            int y = shot.getY();
            y -= SHOT_SPEED;

            if (y < 0) {
                    shot.die();
            } else {
                    shot.setY(y);
            }
        }
        
    }
    
    
    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (ingame) {

            repaint();
            animationCycle();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();
        }

        gameOver();
        
    }
    
    private class MAdapter extends MouseAdapter {

        @Override
        public void mouseMoved(MouseEvent e) {
            
            super.mouseMoved(e);
            player.mouseMoved(e);
        
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            
            // player.mouseMoved(e);

            int x = player.getX();
            int y = player.getY();

            if (ingame) {
                
                if (!shot.isVisible()) {
                    shot = new Shot(x, y);
                    InputStream in = null;
                    try {
                        in = new FileInputStream("images/shot.WAV");
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    AudioStream as = null;
                    try {
                        as = new AudioStream(in);
                    } catch (IOException ex) {
                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    AudioPlayer.player.start(as);
                    System.out.println("player");
                }
            }
            
	}
    }
    
}
