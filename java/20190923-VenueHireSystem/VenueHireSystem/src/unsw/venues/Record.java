package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;

public class Record {
    
    private String id;
    private LocalDate start;
    private LocalDate end;
    private Venue venue;
    private ArrayList<Room> roomList;
    
    Record (String id, LocalDate start, LocalDate end) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.roomList = new ArrayList<>();
    }
    
    Record (String id, LocalDate start, LocalDate end, Venue venue, ArrayList<Room> roomList) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.venue = venue;
        this.roomList = roomList;
    }
    
    public void setVenue(Venue v) {
        this.venue = v;
    }
    
    public Venue getVenue() {
        return this.venue;
    }
    
    public void addRoom(Room r) {
        boolean add = this.roomList.add(r);
    }
    
    public ArrayList<Room> getRooms() {
        return this.roomList;
    }
    
    public boolean containRoom(Room room) {
        for (Room r: this.roomList) {
            if (r.getName().equals(room.getName())) {
                return true;
            }
        }
        return false;
    }
    
    public String getID(){
        return this.id;
    }
    
    public LocalDate getStartDate() {
        return this.start;
    }
    
    public LocalDate getEndDate() {
        return end;
    }
    
    public void addRecordToRooms(){
        LocalDate tempDate = this.getStartDate();
        LocalDate endDate = this.getEndDate().plusDays(1);
        while (!tempDate.equals(endDate)) {
            for(Room room: this.roomList) {
                room.addDate(tempDate);
            }
            tempDate = tempDate.plusDays(1);
        }
    }
    
    public void deleteRecordFromRooms(){
        LocalDate tempDate = this.getStartDate();
        LocalDate endDate = this.getEndDate().plusDays(1);
        
        while (tempDate.compareTo(endDate) <= 0) {
            for(Room room: this.roomList) {
                room.removeDate(tempDate);
            }
            tempDate = tempDate.plusDays(1);
        }
    }
    
}