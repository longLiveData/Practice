package booking.management.single;

public class ClassSingle extends Single{
    
    private String name;
    private int capacity;
    private int curNumOfPeople;
    private String teacher;
    private String time;
    private String room;
    
    public ClassSingle(int ID, String[] args){
        super(ID);
        this.name = args[0];
        this.capacity = Integer.parseInt(args[1]);
        this.curNumOfPeople = Integer.parseInt(args[2]);
        this.teacher = args[3];
        this.time = args[4];
        this.room = args[5];
    }
    
    
    @Override
    public int getID(){
        return super.getID();
    }
    
    public String getName(){
        return this.name;
    }
    
    public boolean isFull(){
        return curNumOfPeople < capacity;
    }
    
    
    public void book(){
        curNumOfPeople += 1;
    }
    
    public void cancal(){
        this.curNumOfPeople -= 1;
    }
    
}
