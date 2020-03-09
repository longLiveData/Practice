package pirate;

import javax.swing.ImageIcon;

public class Obstacle extends Sprite  {
    
    private final String obstacleImg = "images/obstacle.png";

	public Obstacle() {
	}

	public Obstacle(int x, int y) {

		initObstacle(x, y);
	}

	private void initObstacle(int x, int y) {

		ImageIcon ii = new ImageIcon(obstacleImg);
		setImage(ii.getImage());

		setX(x);
		setY(y);
	}
}
