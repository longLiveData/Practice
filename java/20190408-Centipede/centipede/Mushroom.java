package centipede;

import javax.swing.ImageIcon;

public class Mushroom extends Sprite{
    
    private final String mushroomImg = "images/mushroom.png";
    private int health = 3;

    public Mushroom() {
    }

    public Mushroom(int x, int y) {

        initMushroom(x, y);
    }

    private void initMushroom(int x, int y) {

        ImageIcon ii = new ImageIcon(mushroomImg);
        setImage(ii.getImage());

        setX(x);
        setY(y);
    }
    
    public void beHitted(){
        
        this.health -= 1;
        if(this.health == 0){
            this.die();
        }
    }
    
    // restore
    public void restore(){
        this.health = 3;
    }
    
    public int getHealth(){
        return this.health;
    }
    
}
