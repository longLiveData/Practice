package src;

import bagel.Image;
import bagel.Input;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

import java.util.ArrayList;

public class Player {

    public static final double VELOCITY = 5;

    public int id;
    public Vector2 direction;
    public Image image;
    public Controller controller;
    public Rectangle rect;

    public ArrayList<Projectile> projectiles = new ArrayList<>();

    public Player(int id, Point position, Controller controller, String imageSource) {
        this.id = id;
        this.direction = id == StarWars.PLAYER_1_ID ? Vector2.right : Vector2.left;
        this.image = new Image(imageSource);
        this.controller = controller;
        this.rect = new Rectangle(position.x - image.getWidth() / 2,
                                    position.y - image.getHeight() / 2,
                                       image.getWidth(),
                                       image.getHeight());
    }

    public void update(Input input) {
        Point position = rect.topLeft();

        double newY = position.y;

        if (input.isDown(controller.upKey)) {
            newY -= VELOCITY;
        } else if (input.isDown(controller.downKey)) {
            newY += VELOCITY;
        }

        if (input.wasPressed(controller.fireKey)) {
            this.fireProjectile();
        }

        rect.moveTo(new Point(position.x, newY));

        for (Projectile p : projectiles) {
            p.update();
        }
    }

    public void render() {
        image.draw(rect.centre().x, rect.centre().y);

        for (Projectile p : projectiles) {
            p.render();
        }
    }

    public void fireProjectile() {
        System.out.format("Player %d firing projectile\n", this.id);
        projectiles.add(new Projectile(rect.centre(), direction));
    }
}
