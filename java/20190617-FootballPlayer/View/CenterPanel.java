package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CenterPanel extends JPanel {
    
    public CenterPanel(){
        
        setVisible(false);
        setVisible(true);
        
    }
    
    public void setUp(int height, int width){
        
        setLayout(new GridLayout(width, height));
        
        for (int i=0; i < height; i++){
            for (int j = 0; j < width; j++){
                JButton jb = new JButton("");
                Dimension preferredSize = new Dimension(120, 30);
                jb.setPreferredSize(preferredSize);
                add(jb);
            }
        }
        
        setVisible(false);
        setVisible(true);
        
    }
    
    // add data into jpanel
    public void addData(ArrayList<ArrayList<String>> lines, ArrayList<String> headers){
        
        removeAll();
        
        setLayout(new GridLayout(lines.size()+1, headers.size()));
        
        for(String head: headers){
            JButton jb = new JButton(head);
            Dimension preferredSize = new Dimension(120, 30);
            jb.setPreferredSize(preferredSize);
            add(jb);
        }
        
        for (ArrayList<String> line: lines){
            for (String l: line){
                add(new JButton(l));
            }
        }
        
        setVisible(false);
        
        setVisible(true);
        
    }
}
