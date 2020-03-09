import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Display implements Gui{
    private Controller controller;
    private JButton coinSlot = new JButton("Coin Slot");
    private JButton goOrStop = new JButton("Go/Stop");
    private JPanel jPanel = new JPanel();
    private JFrame jFrame = new JFrame("SimpleGame");
    private JTextField jTextField = new JTextField(15);

    public Display(){

    }

    @Override
    public void init() {
        jFrame.setSize(350, 200);
        jPanel.setBounds(0,0,200,200);
        jFrame.add(jPanel);
        
        jPanel.add(coinSlot);
        jPanel.add(goOrStop);
        jPanel.add(jTextField);

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        coinSlot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        controller.coinInserted();
                    }
                }).start();
            }
        });
        goOrStop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.goStopPressed();
            }
        });
        
    }

    @Override
    public void connect(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void setDisplay(String s) {
        jTextField.setText(s);
    }


}
