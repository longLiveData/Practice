package pirate;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class PlayerOne extends Sprite implements Commons{
    
    private final int START_X = BOARD_WIDTH / 4;
    private final int START_Y = BOARD_HEIGHT - SHIP_HEIGHT - 30;
    private int blood = 3;
    private int speedLevel = 1;
    private int grade = 0;

    private final String playerImg = "images/player.png";

    public PlayerOne() {

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

        if (x <= 0) {
            x = 0;
        }
        if (x >= BOARD_WIDTH / 2 - SHIP_WIDTH) {
            x = BOARD_WIDTH / 2 - SHIP_WIDTH;
        }

        y += speedLevel * dy;

        if (y >= BOARD_HEIGHT - SHIP_HEIGHT - 30) {
            y = BOARD_HEIGHT - SHIP_HEIGHT - 30;
        }
            
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            dx = -2;
        }
        if (key == KeyEvent.VK_D) {
            dx = 2;
        }
        if (key == KeyEvent.VK_W) {
            dy = -2;
        }
        if (key == KeyEvent.VK_S) {
            dy = 2;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            dx = 0;
        }
        if (key == KeyEvent.VK_D) {
            dx = 0;
        }
        if (key == KeyEvent.VK_W) {
            dy = 0;
        }
        if (key == KeyEvent.VK_S) {
            dy = 0;
        }
    }
    
    public void speedUp(){
        if(this.speedLevel == 1){
            this.speedLevel += 1;
        }
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
        setX(BOARD_WIDTH / 4);
        setY(BOARD_HEIGHT - SHIP_HEIGHT - 30);
    }
}
