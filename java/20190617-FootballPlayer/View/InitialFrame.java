package View;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class InitialFrame extends JFrame {
    
    InitialPanel mp;

    public InitialFrame()
    {
        
        setLayout(new BorderLayout());
        
        mp = new InitialPanel();
        
        add(mp, BorderLayout.CENTER);
        
        setSize(1000, 480);
        
        setVisible(false);
        
        setVisible(true);
        
    }

    private void setupLayoutForMacs()
    {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public InitialPanel getInitialPanel(){
        
        return this.mp;
        
    }
}
