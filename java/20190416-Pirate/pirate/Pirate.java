package pirate;

import pirate.Board;
import pirate.Commons;
import java.awt.EventQueue;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * 
 */
public class Pirate extends JFrame implements Commons {

    public Pirate() {

            initUI();
	}

	private void initUI() {

            add(new Board());
            setTitle("Pirate");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(BOARD_WIDTH, BOARD_HEIGHT);
            setLocationRelativeTo(null);
            setResizable(false);
	}

	public static void main(String[] args) {

            EventQueue.invokeLater(() -> {
                Pirate p = new Pirate();
                p.setVisible(true);
            });
	}
    
}
