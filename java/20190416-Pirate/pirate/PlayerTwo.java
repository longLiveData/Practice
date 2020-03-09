package pirate;

import static pirate.Commons.BOARD_HEIGHT;
import static pirate.Commons.BOARD_WIDTH;
import static pirate.Commons.SHIP_HEIGHT;
import static pirate.Commons.SHIP_WIDTH;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class PlayerTwo extends Sprite implements Commons{
    
    private final int START_X = BOARD_WIDTH / 4 * 3;
    private final int START_Y = BOARD_HEIGHT - SHIP_HEIGHT - 30;
    private int speedLevel = 1;
    private int blood = 3;
    private int grade = 0;

    private final String playerImg = "images/player.png";

    public PlayerTwo() {

            initPlayer();
    }

    private void initPlayer() {

            ImageIcon ii = new ImageIcon(playerImg);
            
            setImage(ii.getImage());
            setX(START_X);
            setY(START_Y);
    }

    public void act() {

            x += speedLevel * dx;

            if (x <= BOARD_WIDTH / 2) {
                x = BOARD_WIDTH / 2;
            }
            if (x >= BOARD_WIDTH - SHIP_WIDTH - 5) {
                    x = BOARD_WIDTH - SHIP_WIDTH - 5;
            }
            
            y += speedLevel * dy;

            if (y >= BOARD_HEIGHT - SHIP_HEIGHT - 30) {
                    y = BOARD_HEIGHT - SHIP_HEIGHT - 30;
            }
            
    }

    public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                dx = -2;
            }
            if (key == KeyEvent.VK_RIGHT) {
                dx = 2;
            }
            if (key == KeyEvent.VK_UP) {
                dy = -2;
            }
            if (key == KeyEvent.VK_DOWN) {
                dy = 2;
            }
    }

    public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                dx = 0;
            }
            if (key == KeyEvent.VK_RIGHT) {
                dx = 0;
            }
            if (key == KeyEvent.VK_UP) {
                dy = 0;
            }
            if (key == KeyEvent.VK_DOWN) {
                dy = 0;
            }
    }
    
    public void speedUp(){
        if(speedLevel == 1){
            speedLevel += 1;
        }
        this.setVisible(true);
    }
    
    public void speedDown(){
        if(speedLevel == 2){
            speedLevel -= 1;
        }
    }
    
    public void bloodUp(){
        if(blood < 3){
            blood += 1;
        }
    }
    
    public void bloodDown(){
        if(blood > 0){
            blood -= 1;
        }
    }
    
    public int getBlood(){
        return blood;
    }
    
    public int getSpeed(){
        return speedLevel;
    }
    
    public int getGrade(){
        return grade;
    }
    
    public void gradeUp(){
        
        this.grade += 1;
    }
    
    public void restart(){
        blood = 3;
        speedLevel = 1;
        setX(BOARD_WIDTH / 4 * 3);
        setY(BOARD_HEIGHT - SHIP_HEIGHT - 30);
    }
}
