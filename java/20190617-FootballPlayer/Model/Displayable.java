package Model;

public interface Displayable {
    
    public int getFirstLineToDisplay();
    public int getLineToHighLight();
    public int getLastLineToDisplay();
    public int getLinesBeingDisplayed();
    
    public void setFirstLineToDisplay(int firstLine);
    public void setLineToHighLight(int highlightedLine);
    public void setLastLineToDisplay(int lastLine);
    public void setLinesBeingDisplayed(int numberOfLines);
    
}
