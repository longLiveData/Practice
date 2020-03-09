package Model;

import java.util.ArrayList;

public class Model 
{
    private FootballPlayerData fpData = new FootballPlayerData();
    private Height height = new Height();
    
    public Model()
    {
        this.fpData.loadTable();       
    }

    public void displayArrayOfArrays(ArrayList<ArrayList<String>> manyLines)
    {
        for (int i = 0; i < manyLines.size(); i++)
        {
            displayArray(manyLines.get(i));
        }
    }

    public void displayArray(ArrayList<String> oneLineOnly)
    {
        for (String oneLineOnly1 : oneLineOnly) {
            System.out.print(oneLineOnly1);
            System.out.print(" ");
        }
        System.out.println("");

    }

    /**
     * @return the fpData
     */
    public FootballPlayerData getFpData()
    {
        return fpData;
    }

    /**
     * @param fpData the fpData to set
     */
    public void setFpData(FootballPlayerData fpData)
    {
        this.fpData = fpData;
    }
    
    
}

