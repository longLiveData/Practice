package View;

import java.util.ArrayList;

public class View {
    
    InitialFrame frame;
    
    public View(){
       
        frame = new InitialFrame();
        
    }
    

    public void CenterInitialSetup(int height, int width){
        
        frame.mp.cp.setUp(width, height);
        
    }
    
            
    public void CenterUpdate(ArrayList<ArrayList<String>> lines, ArrayList<String> headers){
        
        frame.mp.cp.addData(lines, headers);
        
    }
    
    public InitialFrame getInitialFrame(){
        
        return this.frame;
    
    }
    
    
    
}
