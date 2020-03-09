package centipede;

import java.awt.EventQueue;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class CentipedeGame extends JFrame implements Commons {

	public CentipedeGame() {
            initUI();
	}

	private void initUI() {

            add(new Board());
            setTitle("Centipede");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(BOARD_WIDTH, BOARD_HEIGHT);
            setLocationRelativeTo(null);
            setResizable(false);
            
	}

	public static void main(String[] args) {

            EventQueue.invokeLater(new Runnable() {

                public void run() {
                    CentipedeGame ex = new CentipedeGame();
                    ex.setVisible(true);
                }
                
            });
	}
}