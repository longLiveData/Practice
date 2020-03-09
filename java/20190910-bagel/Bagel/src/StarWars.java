package src;

import bagel.*;
import bagel.util.Point;

/**
 * A more complex example Bagel game.
 *
 * @author Matt De Bono
 */
public class StarWars extends AbstractGame {

    public static final int N_PLAYERS = 2;
    public static final int PLAYER_1_ID = 1;
    public static final int PLAYER_2_ID = 2;

    public static final int DISTANCE_FROM_WALL = 50;

    public static final Controller PLAYER_1_CONTROLS = new Controller(Keys.W, Keys.S, Keys.E);
    public static final Controller PLAYER_2_CONTROLS = new Controller(Keys.UP, Keys.DOWN, Keys.LEFT);

    public Player[] players = new Player[N_PLAYERS];

    public StarWars() {
        players[0] = new Player(PLAYER_1_ID, new Point(0 + DISTANCE_FROM_WALL, Window.getHeight()/2),
                                PLAYER_1_CONTROLS, "res/vertical_brick.png");
        players[1] = new Player(PLAYER_2_ID, new Point(Window.getWidth() - DISTANCE_FROM_WALL, Window.getHeight()/2),
                                PLAYER_2_CONTROLS, "res/vertical_brick.png");
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        StarWars game = new StarWars();
        game.run();
    }

    /**
     * Update the state of the game, potentially reading from input.
     *
     * @param input
     */
    @Override
    protected void update(Input input) {

        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }

        for (Player player : players) {
            player.update(input);
            player.render();
        }
    }
}
