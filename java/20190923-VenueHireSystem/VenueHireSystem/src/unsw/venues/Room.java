
package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;

public class Room {
    
    private final String name;
    private final String size;
    private ArrayList<LocalDate> dates;
    
    Room(String name, String size) {
        this.name = name;
        this.size = size;
        this.dates = new ArrayList<>();
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getSize() {
        return this.size;
    }
    
    public boolean inUse(LocalDate start, LocalDate end) {
        for (LocalDate d: dates) {
            if ((d.compareTo(start) >= 0) && (d.compareTo(end) <= 0)){
                return true;
            }
        }
        return false;
    }
    
    public void addDate(LocalDate date) {
        this.dates.add(date);
    }
    
    public void removeDate(LocalDate date) {
        this.dates.remove(date);
    }
    
    public String getDates() {
        String res = "";
        for(LocalDate ld: this.dates) {
            res += ld + "\n";
        }
        return res;
    }
}
