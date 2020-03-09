package booking.management;

import booking.management.record.Record;
import java.util.ArrayList;

public class RecordList {
    
    private int maxID;
    public static ArrayList<Record> recordlist;
    public static String searchString = "";
    
    RecordList(){
        maxID = 0;
        recordlist = new ArrayList<>();
    }
    
    public boolean add(String[] args){
        int ID = maxID;
        maxID += 1;
        return recordlist.add(new Record(ID, args));
    }
    
    public int getLength(){
        return recordlist.size();
    }
    
    // get all info
    public void getAllInfo(){
        String resStr = "";
        for (Record r: recordlist){
            if(!"".equals(resStr)){
                resStr += "\n";
            }
            resStr += getInfoByRecord(r);
        }
        searchString = resStr;
    }
    
    // search
    public void search(String type, String value){
        String resStr = "";
        if(type.equals("ID")){
            for (Record r: recordlist){
                if(r.getCustomID() == Integer.parseInt(value)){
                    if(!"".equals(resStr)){
                        resStr += "\n";
                    }
                    resStr += getInfoByRecord(r);
                }
            }
        }else if(type.equals("name")){
            for (Record r: recordlist){
                if(r.getName().equals(value)){
                    if(!"".equals(resStr)){
                        resStr += "\n";
                    }
                    resStr += getInfoByRecord(r);
                }
            }
        }
        searchString = resStr;
    }
    
    // get info String by record
    public String getInfoByRecord(Record rec){
        String resStr = "";
        resStr += rec.recordID + "    ";
        resStr += rec.bookTime + "    ";
        resStr += rec.customID + "    ";
        resStr += rec.singleType + "    ";
        resStr += rec.singleName + "    ";
        resStr += rec.startTime + "    ";
        resStr += rec.endTime;
        return resStr;
    }
    
    // cancel by id
    public boolean cancel(String type, int ID){
        for (Record rec: recordlist){
            if(rec.getType().equals(type) && rec.getRecordID() == ID && rec.getCondition().equals("booked")){
                rec.cancel();
                return true;
            }
        }
        return false;
    }
}
