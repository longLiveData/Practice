package centipede;

import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

/**
 * player move
 */
public class Player extends Sprite implements Commons {

	private final int START_Y = 280;
	private final int START_X = 270;

	private final String playerImg = "images/player.png";
	private int width;
        private int height;

	public Player() {
            initPlayer();
	}

	private void initPlayer() {

            ImageIcon ii = new ImageIcon(playerImg);

            width = ii.getImage().getWidth(null);
            height = ii.getImage().getHeight(null);

            setImage(ii.getImage());
            setX(START_X);
            setY(START_Y);
	}

	public void act() {

            x = dx;
            y = dy;

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

	public void mouseMoved(MouseEvent e) {
            
            int px = (int)e.getX();
            int py = (int)e.getY();
            
            dx = px;
            dy = py;
            
    }

}