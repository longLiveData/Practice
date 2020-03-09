package Model;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

public class FootballPlayerData implements TableData, Displayable
{

    private ArrayList<FootballPlayer> players;
    
    // lines
    public int firstLine = 0;
    public int lastLine = 8;
    public int highlightedLine = 5;
    public int numberOfLines = 8;
    

    public FootballPlayerData()
    {
        players = new ArrayList<>();
    }

    public void ReadPlayersFromXML()
    {
        
        try
        {
            FootballPlayer fp;
            XMLDecoder decoder;
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("FootballPlayerTable.xml")));
            
            fp = new FootballPlayer();
            
            while (fp != null)
            {
                try
                {
                    fp = (FootballPlayer) decoder.readObject();
                       
                    players.add(fp);

                } 
                catch (ArrayIndexOutOfBoundsException theend)
                {
                    break;
                }
            }
            decoder.close();
        } catch (Exception xx)
        {
            xx.printStackTrace();
        }
    }
    
    public void loadTable(){
        
        this.ReadPlayersFromXML();
        this.adjustData();
        
    }
    
    public void adjustData(){
        
        FootballPlayer fp = players.get(4);
        fp.setHeight(new Height(5, 11));
        
        fp = players.get(0);
        fp.setHeight(new Height(6, 2));
        
        fp = players.get(1);
        fp.setHeight(new Height(5, 9));
        
        fp = players.get(2);
        fp.setHeight(new Height(6, 1));
        
        fp = players.get(3);
        fp.setHeight(new Height(6, 0));
        
        fp = players.get(5);
        fp.setHeight(new Height(6, 1));
        
        fp = players.get(6);
        fp.setHeight(new Height(6, 4));
        
        fp = players.get(7);
        fp.setHeight(new Height(6, 6));
        
        fp = players.get(8);
        fp.setHeight(new Height(6, 1));
        
        fp = players.get(70);
        fp.setHeight(new Height(6, 1));
        
        fp = players.get(71);
        fp.setHeight(new Height(6, 2));
        
        fp = players.get(72);
        fp.setHeight(new Height(6, 0));
        
        fp = players.get(100);
        fp.setHeight(new Height(6 ,3));
        
        fp = players.get(101);
        fp.setHeight(new Height(6 ,0));
        
    }
    
    public ArrayList<FootballPlayer> getTable(){
        
        return players;
        
    }
    
    public ArrayList<String> getHeaders(){
        
        return this.players.get(0).getAttributeNames();
        
    }
    
    public ArrayList<String> getLine(int line){
        
        FootballPlayer fp = players.get(line);
        
        return fp.getAttributes();
        
    }
    
    public ArrayList<ArrayList<String>> getLines(int firstLine, int lastLine){
        
        ArrayList<ArrayList<String>> lines = new ArrayList<>();
        
        for(int line=firstLine; line<=lastLine; line++){
            
            FootballPlayer fp = players.get(line);
            
            lines.add(fp.getAttributes());
            
        }
        
        return lines;
        
    }

    @Override
    public int getFirstLineToDisplay() {
        
        return this.firstLine;
        
    }

    @Override
    public int getLineToHighLight() {
        
        return this.highlightedLine;
        
    }

    @Override
    public int getLastLineToDisplay() {
        
        return this.lastLine;
        
    }

    @Override
    public int getLinesBeingDisplayed() {
        
        return this.numberOfLines; 
        
    }

    @Override
    public void setLineToHighLight(int highlightedLine) {
        
        this.highlightedLine = highlightedLine;
        
    }

    @Override
    public void setLastLineToDisplay(int lastLine) {
        
        this.lastLine = lastLine;
        
    }

    @Override
    public void setLinesBeingDisplayed(int numberOfLines) {
        
        this.numberOfLines = numberOfLines;
        
    }

    @Override
    public void setFirstLineToDisplay(int firstLine) {
        
        this.firstLine = firstLine;
        
    }

}
