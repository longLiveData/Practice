package Controller;

import Model.Model;
import View.View;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Controller {
    
    Model model;
    View view;
    
    public Controller(Model m, View v){
        model = m;
        view = v;
        
        view.CenterInitialSetup(model.getFpData().getLinesBeingDisplayed(), model.getFpData().getHeaders().size());    
        
        view.CenterUpdate(model.getFpData().getLines(model.getFpData().getFirstLineToDisplay(),
                model.getFpData().getLastLineToDisplay()), model.getFpData().getHeaders());
        
        addScrolling();
        addClicking();
    }
    
    private void addScrolling(){
        view.getInitialFrame().getInitialPanel().getCp().addMouseWheelListener(
                new MouseWheelListener(){

                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    
                    int units = e.getUnitsToScroll();
                    
                    int newFirstLine = model.getFpData().getFirstLineToDisplay() + units;
                    int newLastLine = model.getFpData().getLastLineToDisplay() + units;
                    
                    if (newFirstLine < 0) {
                        newFirstLine = 0;
                        newLastLine = newFirstLine + 8;
                    }
                    
                    if (newLastLine > 122){
                        newLastLine = 122;
                        newFirstLine = newLastLine - 8;
                    }
                    
                    model.getFpData().setFirstLineToDisplay(newFirstLine);
                    model.getFpData().setLastLineToDisplay(newLastLine);
                    
                    view.CenterUpdate(model.getFpData().getLines(model.getFpData().getFirstLineToDisplay(),
                        model.getFpData().getLastLineToDisplay()), model.getFpData().getHeaders());
                    
                    
                }
            }
        );
    }
    
    private void addClicking(){
        view.getInitialFrame().getInitialPanel().getCp().addMouseListener(
                new MouseListener(){
                    
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println(e.getX());
                    System.out.println(e.getY());
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

            }
        );
    }
    
    private int min(int a, int b){
        if (a < b){
            return a;
        } else {
            return b;
        }
    }
    
    private int max(int a, int b){
        if (a > b){
            return a;
        } else {
            return b;
        }
    }
    
}
