package booking.management;

import booking.management.single.ClassSingle;
import java.util.ArrayList;

public class ClassList {
    
    private int maxID;
    public static ArrayList<ClassSingle> classList;
    private static ArrayList<String> nameList;
    
    ClassList(){
        this.maxID = 0;
        classList = new ArrayList<>();
        nameList = new ArrayList<>();
    }
    
    // add a facility into list
    public void add(String[] args){
        int ID = maxID;
        maxID += 1;
        classList.add(new ClassSingle(ID, args));
        if (nameList.indexOf(args[0]) == -1){
            nameList.add(args[0]);
        }
    }
    
    // if this type can book, book it and return id
    // else return -1
    public int getIDByType(String type){
        for(ClassSingle fs: classList){
            if(fs.getName().equals(type) && fs.isFull()){
                fs.book();
                return fs.getID();
            }
        }
        return -1;
    }
    
    // cancel by ID
    public void cancelByID(int ID){
        for(ClassSingle fs: classList){
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
