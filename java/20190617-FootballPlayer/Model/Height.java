package Model;

public class Height {
    
    private int inches;
    
    private int feet;
    
    Height(){
        this.feet = 0;
        this.inches = 0;
    };
    
    Height(int inches,int feet)
    {
        this.inches = inches;
        this.feet = feet;
    }        
    
    public int getFeet() {
        return feet;
    }
    
    public void setFeet(int feet) {
        this.feet = feet;
    }
    
    public int getInches(){
        return inches;
    }
    
    public void setInches(int inches){
        this.inches=inches;
    }
    
    @Override
    public String toString()
    {
        return this.inches + "\'" + this.feet + "\"";
    }
}