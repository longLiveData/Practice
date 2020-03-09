import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Snapshot {
    
    private List<Entry>	entries;
    private int	id;
    
    Snapshot(int id, List<Entry> entries){
        this.id = id;
        this.entries = new ArrayList<>();
        for(Entry entry : entries){
            List<Integer> valuesSnap = new ArrayList<>();
            for(Integer i : entry.getValues()){
                valuesSnap.add(i);
            }
            this.entries.add(new Entry(entry.getKey(),valuesSnap));
        }
    }

    //Saves the snapshot to file.
    void archive(String filename)  {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
            for (Entry entry : entries) {
                StringBuilder sb = new StringBuilder();
                sb.append(entry.getKey()).append("|");
                Iterator<Integer> itr = entry.getValues().iterator();
                if (itr.hasNext()) sb.append(itr.next());
                while (itr.hasNext()) {
                    sb.append(",").append(itr.next());
                }
                out.write(sb.toString());
                out.write("\n");
                out.flush();
            }
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("ok");
    }

    //Formats all snapshots for display.
    static String listAllSnapshots(List<Snapshot> snapshots){
        String temp = "";
        for(Snapshot snapshot : snapshots){
            temp += snapshot.id + "\n";
        }
        String res = temp.substring(0, temp.length()-1);
        return res;
    }

    //Finds and removes the key.
    void removeKey(String key){
        for(int i=0;i<entries.size();i++){
            if(key.equals(entries.get(i).getKey())){
                entries.remove(i);
                return;
            }
        }
    }

    //Loads and restores a snapshot from file.
    static List<Entry>	restore(String filename) {
        List<Entry> list = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String line = null;
            while ((line = in.readLine()) != null) {
                String key = line.split("[|]")[0];
                List<Integer> values = new ArrayList<>();

                for (String val : line.split("[|]")[1].split(",")) {
                    values.add(Integer.parseInt(val));
                }
                Entry entry = new Entry(key, values);
                list.add(entry);
            }
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //Finds the list of entries to restore.
    List<Entry>	rollback(){
        return entries;
    }

    public int getId() {
        return id;
    }
}