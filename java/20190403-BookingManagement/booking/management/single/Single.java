package booking.management.single;

/**
 * parent class of class ClassInfo and class FaclityInfo
 */
public class Single {
    
    private final int ID;
    
    Single(int Id){
        this.ID = Id;
    }
    
    public int getID(){
        return this.ID;
    }
}
