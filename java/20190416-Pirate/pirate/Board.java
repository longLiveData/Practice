package pirate;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable, Commons {
    
    private Dimension d;
    private ArrayList<Obstacle> obs;
    private PlayerOne player1;
    private PlayerTwo player2;
    
    private SpeedPU sp1;
    private SpeedPU sp2;
    private BloodPU bp1;
    private BloodPU bp2;

    private boolean ingame = true;

    private Thread animator;

    public Board() {

        initBoard();
    }

    private void initBoard() {

            addKeyListener(new TAdapter());
            setFocusable(true);
            d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
            setBackground(Color.white);

            gameInit();
            setDoubleBuffered(true);
    }

    @Override
    public void addNotify() {

            super.addNotify();
            gameInit();
    }

    public void gameInit() {
        
        // add obstacles
        obs = new ArrayList<>();
        int num = 0;
        while (num < 30){
            int ox = (int) (Math.random() * (BOARD_WIDTH));
            int oy = (int) (Math.random() * (BOARD_HEIGHT - 30));
            boolean canAdd = true;
            for(Obstacle o: obs){
                if((280 < ox && ox < 300) || ox > 580 || oy < 20 || oy > 350 || 
                        ifHit(o.getX(), o.getY(), 20, 20, ox, oy, 20, 20)){
                    canAdd = false;
                }
            }
            if(canAdd){
                Obstacle o = new Obstacle(ox, oy);
                obs.add(o);
                num += 1;
            }
        }
        
        player1 = new PlayerOne();
        player2 = new PlayerTwo();
        if (animator == null || !ingame) {

            animator = new Thread(this);
            animator.start();
        }
        
        // speed up
        int bpx1 = 0;
        int bpy1 = 0;
        int bpx2 = 0;
        int bpy2 = 0;
        while(!ifPosUsable(bpx1, bpy1)){
            bpx1 = (int) (Math.random() * (BOARD_WIDTH/2 - 20));
            bpy1 = (int) (Math.random() * (BOARD_HEIGHT - 60));
        }
        bp1 = new BloodPU(bpx1, bpy1);
        
        while(!ifPosUsable(bpx2, bpy2)){
            bpx2 = (int) (Math.random() * (BOARD_WIDTH/2 - 20) + BOARD_WIDTH/2);
            bpy2 = (int) (Math.random() * (BOARD_HEIGHT - 60));
        }
        bp2 = new BloodPU(bpx2, bpy2);
        
        // blood up
        int spx1 = 0;
        int spy1 = 0;
        int spx2 = 0;
        int spy2 = 0;
        while(!ifPosUsable(spx1, spy1)){
            spx1 = (int) (Math.random() * (BOARD_WIDTH/2 - 20));
            spy1 = (int) (Math.random() * (BOARD_HEIGHT - 60));
        }
        sp1 = new SpeedPU(spx1, spy1);
        
        while(!ifPosUsable(spx2, spy2)){
            spx2 = (int) (Math.random() * (BOARD_WIDTH/2 - 20) + BOARD_WIDTH/2);
            spy2 = (int) (Math.random() * (BOARD_HEIGHT - 60));
        }
        sp2 = new SpeedPU(spx2, spy2);
        
    }

    public void drawObstacles(Graphics g) {

        for (Obstacle o: obs) {

            if (o.isVisible()) {

                g.drawImage(o.getImage(), o.getX(), o.getY(), this);
            }

            if (o.isDying()) {

                o.die();
            }
        }
    }

    public void drawPlayer(Graphics g) {

        if (sp1.isVisible()) {
            g.drawImage(player1.getImage(), player1.getX(), player1.getY(), this);
        }
        if (sp2.isVisible()) {
            g.drawImage(player2.getImage(), player2.getX(), player2.getY(), this);
        }

        if (sp1.isDying() || sp2.isDying()) {

                sp1.die();
                sp2.die();
        }
            
    }

    public void drawShot(Graphics g) {

            
    }
    
    public void drawGrade(Graphics g){
        
        g.setColor(new Color(0, 32, 48));
        g.fillRect(0, 0, 40, 20);
        g.setColor(Color.white);
        g.setFont(new Font("Helvetica", Font.BOLD, 14));
        g.drawString(String.valueOf(player1.getGrade()), 15, 15);
        
        g.setColor(new Color(0, 32, 48));
        g.fillRect(555, -2, 40, 20);
        g.setColor(Color.white);
        g.drawString(String.valueOf(player2.getGrade()), 572, 15);
    }
    
    public void drawHealth(Graphics g){
        
        String img = "images/blood.png";
        
        int h1 = player1.getBlood();
        int h2 = player2.getBlood();
        
        int y1 = 450;
        for (int i=0; i<h1; i++){
            g.drawImage(new ImageIcon(img).getImage(), 278, y1, this);
            y1 -= 20;
        }
        
        int y2 = 450;
        for (int i=0; i<h2; i++){
            g.drawImage(new ImageIcon(img).getImage(), 302, y2, this);
            y2 -= 20;
        }
    }
    
    public void drawBloodPU(Graphics g) {
        
        if (bp1.isVisible()) {
            g.drawImage(bp1.getImage(), bp1.getX(), bp1.getY(), this);
        }
        if (bp2.isVisible()) {
            g.drawImage(bp2.getImage(), bp2.getX(), bp2.getY(), this);
        }
        
        if (bp1.isDying()) {

            bp1.die();
        }
        
        if (bp2.isDying()) {

            bp2.die();
        }
    }

    public void drawSpeedPU(Graphics g) {
        
        if (sp1.isVisible()) {
            g.drawImage(sp1.getImage(), sp1.getX(), sp1.getY(), this);
        }
        if (sp2.isVisible()) {
            g.drawImage(sp2.getImage(), sp2.getX(), sp2.getY(), this);
        }
        
        if (sp1.isDying()) {

            sp1.die();
        }
        
        if (sp2.isDying()) {

            sp2.die();
        }
        
    }
    
    public boolean ifPosUsable(int ox, int oy){
        
        if((280 < ox && ox < 300) || ox > 580 || oy < 20 || oy > 350 ){
            return false;
        }
        for(Obstacle o: obs){
            if(ifHit(o.getX(), o.getY(), 20, 20, ox, oy, 20, 20)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void paintComponent(Graphics g) {
        
            super.paintComponent(g);

            g.setColor(Color.white);
            g.fillRect(0, 0, d.width, d.height);
            g.setColor(Color.black);

            if (ingame) {

                    g.drawLine(BOARD_WIDTH/2, 0, BOARD_WIDTH/2, BOARD_HEIGHT);
                    drawObstacles(g);
                    drawPlayer(g);
                    drawShot(g);
                    drawBloodPU(g);
                    drawSpeedPU(g);
                    drawGrade(g);
                    drawHealth(g);
            }

            Toolkit.getDefaultToolkit().sync();
            g.dispose();
    }

    
    public void restart(){
        player1.restart();
        player2.restart();
    }

    public void animationCycle() {
        
        player1.act();
        player2.act();
        
        int xp1 = player1.getX();
        int yp1 = player1.getY();
        int xp2 = player2.getX();
        int yp2 = player2.getY();
        
        // players
        if (yp1 < 0){
            player1.gradeUp();
            restart();
            return;
        }
        if (yp2 < 0){
            player2.gradeUp();
            restart();
            return;
        }
        if(player1.getBlood() == 0){
            player2.gradeUp();
            restart();
            return;
        }
        if(player2.getBlood() == 0){
            player1.gradeUp();
            restart();
            return;
        }
         
        // players and obstacle
        for (Obstacle o: obs){
            int ox = o.getX();
            int oy = o.getY();
            if(o.isVisible() && ifHit(xp1, yp1, 20, 20, ox, oy, 20, 20)){
                player1.bloodDown();
                o.setDying(false);
            }
            if(ifHit(xp2, yp2, 20, 20, ox, oy, 20, 20)){
                player2.bloodDown();
                o.setDying(false);
            }
        }
        
        // players and blood
        try{
            int bpx1 = bp1.getX();
            int bpy1 = bp1.getY();
            if(bp1.isVisible() && ifHit(xp1, yp1, 20, 20, bpx1, bpy1, 20, 20)){
                player1.bloodUp();
                bp1.die();
                
            }
            int bpx2 = bp2.getX();
            int bpy2 = bp2.getY();
            if(bp2.isVisible() && ifHit(xp2, yp2, 20, 20, bpx2, bpy2, 20, 20)){
                player2.bloodUp();
                bp2.die();
            }
        }catch (Exception e) {
        }
        
        // players and speed
        try{
            int spx1 = sp1.getX();
            int spy1 = sp1.getY();
            if(sp1.isVisible() && ifHit(xp1, yp1, 20, 20, spx1, spy1, 20, 20)){
                player1.speedUp();
                // sp1.die();
            }
            int spx2 = sp2.getX();
            int spy2 = sp2.getY();
            if(sp2.isVisible() && ifHit(xp2, yp2, 20, 20, spx2, spy2, 20, 20)){
                player2.speedUp();
                // sp2.die();
            }
        }catch (Exception e) {
        }
        
        // blood
        
    }
    
    
    public boolean ifHit(int xp1,int yp1,int w1,int h1,int xp2,int yp2,int w2,int h2){
        int dx = abs((xp1+w1/2) - (xp2+w2/2));
        int dy = abs((yp1+h1/2) - (yp2+h2/2));
        if(dx < (w1+w2)/2 && dy < (h1+h2)/2){
            return true;
        } else {
            return false;
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

    }

    private class TAdapter extends KeyAdapter {

        @Override
		public void keyReleased(KeyEvent e) {

			player1.keyReleased(e);
                        player2.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {

			player1.keyPressed(e);
                        player2.keyPressed(e);

		}
	}
            
}
