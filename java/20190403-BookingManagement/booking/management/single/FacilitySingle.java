package booking.management.single;

/**
 * sports faclity class
 */
public class FacilitySingle extends Single{
    
    private String name;
    private String condition; // "free", "in use", "booking", "under repair"
    private String buyTime;
    
    public FacilitySingle(int ID, String[] args){
        super(ID);
        this.name = args[0];
        this.condition = args[1];
        this.buyTime = args[2];
    }
    
    @Override
    public int getID(){
        return super.getID();
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getCondition(){
        return this.condition;
    }
    
    public void book(){
        this.condition = "booking";
    }
    
    public void cancal(){
        this.condition = "free";
    }
    
}
