package centipede;

import javax.swing.ImageIcon;

/**
 *
 */
public class Centipede extends Sprite implements Commons{

    private final String headImg = "images/head.png";
    private final String bodyImg = "images/body.png";
    private int health = 2;

    public Centipede() {
    }

    public Centipede(int x, int y) {

        initCentipede(x, y);
    }

    private void initCentipede(int x, int y) {

        ImageIcon ii = new ImageIcon(bodyImg);
        setImage(ii.getImage());

        setX(x);
        setY(y);
    }
    
    public void switchToHead(){
        
        ImageIcon ii = new ImageIcon(headImg);
        setImage(ii.getImage());
    }
    
    public void beHitted(){
        
        this.health -= 1;
        if(this.health == 0){
            this.die();
        }
    }
    
    public int getHealth(){
        return this.health;
    }
    
}