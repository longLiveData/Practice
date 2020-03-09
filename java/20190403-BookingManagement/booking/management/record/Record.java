package booking.management.record;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * a booking record
 */
public class Record {
    
    public int recordID;
    public String bookTime;
    public int customID;
    public int singleID;
    public String startTime;
    public String endTime;
    public String singleName;
    public String singleType;
    public String condition; // "booked", "cancel", "timeout"
    
    public Record(int ID, String[] args){
        this.recordID = ID;
        Date currentTime = new Date(); 
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
        this.bookTime = formatter.format(currentTime); 
        this.customID = Integer.parseInt(args[0]);
        this.singleID = Integer.parseInt(args[1]);
        this.startTime = args[2];
        this.endTime = args[3];
        this.singleType = args[4];
        this.singleName = args[5];
        this.condition = "booked";
    }
    
    public void cancel(){
        this.condition = "cancel";
    }
    
    public void modify(String[] args){
        this.bookTime = args[1];
        this.customID = Integer.parseInt(args[2]);
        this.singleID = Integer.parseInt(args[3]);
        this.startTime = args[4];
        this.endTime = args[5];
    }
    
    public String getInfo(){
        String resStr = "";
        resStr += this.recordID + "_";
        resStr += this.bookTime + "_";
        resStr += this.customID + "_";
        resStr += this.singleID + "_";
        resStr += this.startTime + "_";
        resStr += this.endTime;
        return resStr;
    }
    
    public String getCondition(){
        return this.condition;
    }
    
    public String getType(){
        return this.singleType;
    }
    
    public int getRecordID(){
        return this.recordID;
    }
    
    public int getCustomID(){
        return this.customID;
    }
    
    public String getName(){
        return this.singleName;
    }
}
