package src;

import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

public class Projectile {

    public static final double PROJECTILE_VELOCITY = 10;

    public Point position;
    public Vector2 direction;

    public Image image;
    public Rectangle rect;

    public Projectile(Point position, Vector2 direction) {
        this.position = position;
        this.direction = direction;
        this.image = new Image("res/ball.png");
        this.rect = new Rectangle(position.x - image.getWidth() / 2,
                                    position.y - image.getHeight() / 2,
                                        image.getWidth(),
                                        image.getHeight());
    }

    public void update() {
        this.position = this.position.asVector().add(direction.mul(PROJECTILE_VELOCITY)).asPoint();
    }

    public void render() {
        image.draw(position.x, position.y);
    }
}
