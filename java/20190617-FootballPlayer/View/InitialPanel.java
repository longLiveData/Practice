package View;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class InitialPanel extends JPanel {
    
    CenterPanel cp;
    
    public InitialPanel(){
        
        
        setLayout(new BorderLayout());
        
        cp = new CenterPanel();
        
        add(cp, BorderLayout.CENTER);
        
        setVisible(false);
        
        setVisible(true);
        
    }
    
    public CenterPanel getCp(){
        
        return this.cp;
        
    }
}
