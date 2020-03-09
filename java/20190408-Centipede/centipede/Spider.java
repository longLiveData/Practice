package centipede;

import static centipede.Commons.BOARD_HEIGHT;
import static centipede.Commons.BOARD_WIDTH;
import javax.swing.ImageIcon;

public class Spider extends Sprite implements Commons {
    
    private final int START_Y = 200;
    private final int START_X = 200;

    private final String spiderImg = "images/splider.png";
    private int width;
    private int height;
    private int health = 2;

    public Spider() {

        initSpider();
    }

    private void initSpider() {

        ImageIcon ii = new ImageIcon(spiderImg);

        width = ii.getImage().getWidth(null);
        height = ii.getImage().getHeight(null);

        setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);
    }

    public void act() {

        autoMoved();
        
        x += dx;
        y += dy;

        if (x <= 2) {
            x = 2;
        }

        if (x >= BOARD_WIDTH - 2 * width) {
            x = BOARD_WIDTH - 2 * width;
        }

        if (y <= 2) {
            y = 2;
        }

        if (y >= BOARD_HEIGHT - 2 * height) {
            y = BOARD_HEIGHT - 2 * height;
        }

    }

    private void autoMoved() {

        double rx = Math.random();
        double ry = Math.random();
        
        if (rx <= 0.5){
            dx = -1 * SPIDER_STEP;
        } else {
            dx = SPIDER_STEP;
        }
        if (ry <= 0.5){
            dy = -1 * SPIDER_STEP;
        } else {
            dy = SPIDER_STEP;
        }
    }
    
    public void beHitted(){
        
        this.health -= 1;
        if(this.health == 0){
            this.die();
        }
        
    }
}
