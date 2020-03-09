package booking.management;

import booking.management.single.FacilitySingle;
import java.util.ArrayList;

public class FacilityList {
    
    private int maxID;
    public static ArrayList<FacilitySingle> facilityList;
    private static ArrayList<String> nameList;
    
    FacilityList(){
        this.maxID = 0;
        facilityList = new ArrayList<>();
        nameList = new ArrayList<>();
    }
    
    // add a facility into list
    public void add(String[] args){
        int ID = maxID;
        maxID += 1;
        facilityList.add(new FacilitySingle(ID, args));
        if (nameList.indexOf(args[0]) == -1){
            nameList.add(args[0]);
        }
    }
    
    
    // if this type can book, book it and return id
    // else return -1
    public int getIDByType(String type){
        for(FacilitySingle fs: facilityList){
            if(fs.getName().equals(type) && fs.getCondition().equals("free")){
                fs.book();
                return fs.getID();
            }
        }
        return -1;
    }
    
    // cancel by ID
    public void cancelByID(int ID){
        for(FacilitySingle fs: facilityList){
            if(fs.getID() == ID){
                fs.cancal();
            }
        }
    }
    
    // return name list to show in ui
    public String[] getNameList(){
        String[] nlist = new String[nameList.size()];
        int i = 0;
        for (String nl: nameList){
            nlist[i] = nl;
            i += 1;
        }
        return nlist;
    }
    
}
