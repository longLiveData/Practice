/**
 *
 */
package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Venue Hire System for COMP2511.
 *
 * A basic prototype to serve as the "back-end" of a venue hire system. Input
 * and output is in JSON format.
 *
 * @author Robert Clifton-Everest
 *
 */
public class VenueHireSystem {
    
    private ArrayList<Record> records;
    private ArrayList<Venue> venues;

    /**
     * Constructs a venue hire system. Initially, the system contains no venues,
     * rooms, or bookings.
     */
    public VenueHireSystem() {
        // TODO Auto-generated constructor stub
        this.records = new ArrayList<>();
        this.venues = new ArrayList<>();
    }

    private void processCommand(JSONObject json) {
        switch (json.getString("command")) {

            case "room":
                String venue = json.getString("venue");
                String room = json.getString("room");
                String size = json.getString("size");
                addRoom(venue, room, size);
                break;

            case "request":
                String id = json.getString("id");
                LocalDate start = LocalDate.parse(json.getString("start"));
                LocalDate end = LocalDate.parse(json.getString("end"));
                int small = json.getInt("small");
                int medium = json.getInt("medium");
                int large = json.getInt("large");

                JSONObject result = request(id, start, end, small, medium, large);

                System.out.println(result.toString(2));
                break;

            // TODO Implement other commands
            case "change":
                String changeId = json.getString("id");
                LocalDate changeStart = LocalDate.parse(json.getString("start"));
                LocalDate changeEnd = LocalDate.parse(json.getString("end"));
                int changeSmall = json.getInt("small");
                int changeMedium = json.getInt("medium");
                int changeLarge = json.getInt("large");

                JSONObject changeResult = change(changeId, changeStart, changeEnd, 
                        changeSmall, changeMedium, changeLarge);

                System.out.println(changeResult.toString(2));
                break;
                
            case "cancel":
                String cancelID = json.getString("id");
                cancel(cancelID);
                break;
                
            case "list":
                String ve = json.getString("venue");
                JSONArray listResult = list(ve);
                System.out.println(listResult.toString());
        }
    }

    private void addRoom(String venue, String room, String size) {
        // TODO Process the room command
        Room r = new Room(room, size);
        Venue v = getVenueByName(venue);
        if (v == null) {
            v = new Venue(venue);
            this.venues.add(v);
        }
        v.addRoom(r);
    }
    
    private Venue getVenueByName(String name) {
        for(Venue v: this.venues) {
            if (v.getName().equals(name)) {
                return v;
            }
        }
        return null;
    }
    
    private List<Room> getRoomByVenueName(String name) {
        for(Venue v: this.venues) {
            if (v.getName().equals(name)) {
                return v.getRooms();
            }
        }
        return null;
    }

    public JSONObject request(String id, LocalDate start, LocalDate end, int small, int medium, int large) {
        JSONObject result = new JSONObject();

        // TODO Process the request commmand
        for (Venue v: venues) {
            ArrayList<Room> largeList = v.getUseList("large", start, end);
            ArrayList<Room> mediumList = v.getUseList("medium", start, end);
            ArrayList<Room> smallList = v.getUseList("small", start, end);
            if (largeList.size() >= large && mediumList.size() >= medium && smallList.size() >= small) {
                Record r = new Record(id, start, end);
                r.setVenue(v);
                for (int i=0; i<large; i++) {
                    Room room = largeList.get(i);
                    r.addRoom(room);
                }
                for (int j=0; j<medium; j++) {
                    r.addRoom(mediumList.get(j));
                }
                for (int k=0; k<small; k++) {
                    r.addRoom(smallList.get(k));
                }
                r.addRecordToRooms();
                this.records.add(r);
                
                result.put("status", "success");
                result.put("venue", v.getName());
                JSONArray rooms = new JSONArray();
                for (Room room: r.getRooms()) {
                    rooms.put(room.getName());
                }
                result.put("rooms", rooms);
                
                return result;
            }
        }

        // FIXME Shouldn't always produce the same answer
        result.put("status", "rejected");
        return result;
    }
    
    public JSONObject change(String id, LocalDate start, LocalDate end, int small, int medium, int large) {
        JSONObject result = new JSONObject();
        
        // cancel old and record state
        // get old record
        Record cancelRecord = null;
        for(Record record: this.records){
            if (record.getID().equals(id)){
                cancelRecord = record;
            }
        }
        
        // cancel it and record status
        String id1 = cancelRecord.getID();
        LocalDate start1 = cancelRecord.getStartDate();
        LocalDate end1 = cancelRecord.getEndDate();
        Venue venue1 = cancelRecord.getVenue();
        ArrayList<Room> roomList1 = cancelRecord.getRooms();
        
        cancelRecord.deleteRecordFromRooms();
        this.records.remove(cancelRecord);
        
        // request new
        result = request(id, start, end, small, medium, large);
        
        // if not ok, request old
        if(! result.getString("status").equals("success")) {
            Record r = new Record(id1, start1, end1, venue1, roomList1);
            r.addRecordToRooms();
        }
        // return
        return result;
    }
    
    public void cancel(String cancelID) {
        Record cancelRecord = null;
        for(Record record: this.records){
            if (record.getID().equals(cancelID)){
                cancelRecord = record;
            }
        }
        if (cancelRecord != null) {
            cancelRecord.deleteRecordFromRooms();
            this.records.remove(cancelRecord);
        }
    }
    
    public JSONArray list(String ve) {
        JSONArray result = new JSONArray();
        
        for (Room room: this.getRoomByVenueName(ve)) {
            JSONObject temp = new JSONObject();
            temp.put("room", room.getName());
            temp.put("reservations", new JSONArray());
            result.put(temp);
            for (Record record: this.records) {
                if(record.containRoom(room)) {
                    JSONObject rec = getExist(result, room.getName());
                    JSONArray ress = (JSONArray) (rec.get("reservations"));
                    ress.put(infoJson(record.getID(), record.getStartDate(), record.getEndDate()));
                    rec.put("reservations", ress);
                }
            }
        }
        return result;
    }
    
    public JSONObject getExist(JSONArray jArray, String room) {
        for (int i=0;  i<jArray.length(); i++) {
            if (jArray.getJSONObject(i).get("room").equals(room)) {
                return jArray.getJSONObject(i);
            }
        }
        return null;
    }
    
    public JSONObject infoJson(String id, LocalDate start, LocalDate end) {
        JSONObject result = new JSONObject();
        result.put("id", id);
        result.put("start", start);
        result.put("end", end);
        return result;
    }

    public static void main(String[] args) {
        VenueHireSystem system = new VenueHireSystem();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.trim().equals("")) {
                JSONObject command = new JSONObject(line);
                system.processCommand(command);
            }
        }
        sc.close();
    }

}
