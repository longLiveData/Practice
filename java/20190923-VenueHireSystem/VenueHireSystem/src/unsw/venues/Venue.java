package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;

public class Venue {
    
    private final String name;
    private ArrayList<Room> rooms;
    private int largeNum = 0;
    private int mediumNum = 0;
    private int smallNum = 0;
    
    Venue(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
    }
    
    public void addRoom(Room room) {
        this.rooms.add(room);
        if (room.getSize().equals("large")) {
            this.largeNum += 1;
        } else if (room.getSize().equals("medium")) {
            this.mediumNum += 1;
        } else {
            this.smallNum += 1;
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public ArrayList<Room> getRooms(){
        return this.rooms;
    }
    
    public int getLargeNum() {
        return this.largeNum;
    }
    
    public int getMediumNum() {
        return this.mediumNum;
    }
    
    public int getSmallNum() {
        return this.smallNum;
    }
    
    public ArrayList<Room> getUseList(String size, LocalDate start, LocalDate end) {
        ArrayList<Room> res = new ArrayList<>();
        for (Room r: this.rooms) {
            if ((r.getSize().equals(size)) && ! (r.inUse(start, end))) {
                res.add(r);
            }
        }
        return res;
    }
}
