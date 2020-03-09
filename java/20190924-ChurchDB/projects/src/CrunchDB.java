import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrunchDB {

    private static List<Entry> entries;
    private static List<Snapshot> snapshots;

    static {
        entries = new ArrayList<>();
        snapshots = new ArrayList<>();
    }
    
    private void listKeys() {
        if(entries.isEmpty()) {
            System.out.print("> ");
            System.out.println("no keys" + "\n");
            return;
        }
        String res = "";
        for (Entry entry : entries) {
            res += entry.getKey() + "\n";
        }
        System.out.print("> ");
        System.out.print(res);
        System.out.print("\n");
    }

    private void listEntries() {
        if(entries.isEmpty()){
            System.out.print("> ");
            System.out.println("no entries" + "\n");
            return;
        }
        String res = "";
        for (Entry entry : entries) {
            res += entry.get1() + "\n";
        }
        System.out.print("> ");
        System.out.print(res);
        System.out.print("\n");
    }

    private void listSnapshot() {
        if(snapshots.isEmpty()){
            System.out.print("> ");
            System.out.println("no snapshots"+ "\n");
            return;
        }
        String res = "";
        for(Snapshot snapshot : snapshots){
            res += snapshot.getId() + "\n";
        }
        String res1 = res.replace(',', ' ').replaceAll("  ", " ");
        System.out.print("> ");
        System.out.print(res1);
        System.out.print("\n");
    }
    private void get(String key) {
        for (Entry entry : entries) {
            if(entry.getKey().equals(key)){
                if(entry.getValues().isEmpty()) {
                    System.out.print("> ");
                    System.out.println("[]"+ "\n");
                }else{
                    StringBuilder sb = new StringBuilder();
                    sb.append("[");
                    for(Integer i : entry.getValues()){
                        sb.append(i).append(" ");
                    }
                    System.out.print("> ");
                    System.out.println(sb.substring(0, sb.length()-1) + "]"+ "\n");
                }
                return;
            }
        }
        System.out.print("> ");
        System.out.println("no such key"+ "\n");
    }

    private void del(String key) {
        for (int i = 0; i<entries.size();i++) {
            if (entries.get(i).getKey().equals(key)) {
                entries.remove(i);
                System.out.print("> ");
                System.out.println("ok"+ "\n");
                return;
            }
        }
        System.out.print("> ");
        System.out.println("no such key"+ "\n");
    }

    private void purge(String key) {
        for (int i = 0; i<entries.size();i++) {
            if (entries.get(i).getKey().equals(key)) {
                entries.remove(i);
                break;
            }
        }

        for(Snapshot snapshot : snapshots) {
            List<Entry> snapshotsEntries = snapshot.rollback();
            for(int i=0;i<snapshotsEntries.size();i++){
                if(snapshotsEntries.get(i).getKey().equals(key)){
                    snapshotsEntries.remove(i);
                    break;
                }
            }
        }
        System.out.print("> ");
        System.out.println("ok"+ "\n");
    }

    private void set(String key, List<Integer> values) {
        for (Entry entry : entries) {
            if(entry.getKey().equals(key)){
                entry.set(values);
                System.out.print("> ");
                System.out.println("ok"+ "\n");
                return;
            }
        }
        Entry entry = new Entry(key, values);
        entries.add(0, entry);
        System.out.print("> ");
        System.out.println("ok"+ "\n");
    }

    private void push(String key, List<Integer> values) {
        for (Entry entry : entries) {
            if(entry.getKey().equals(key)){
                entry.push(values);
                System.out.print("> ");
                System.out.println("ok"+ "\n");
                return;
            }
        }
        System.out.print("> ");
        System.out.println("no such key" + "\n");
    }

    private void append(String key, List<Integer> values) {
        for (Entry entry : entries) {
            if(entry.getKey().equals(key)){
                entry.append(values);
                System.out.print("> ");
                System.out.println("ok"+ "\n");
                return;
            }
        }
        System.out.print("> ");
        System.out.println("no such key" + "\n");
    }
    private void pick(String key, int index) {
        for (Entry entry : entries) {
            if(entry.getKey().equals(key)){
                Integer i = entry.pick(index);
                if (null == i) {
                    System.out.print("> ");
                    System.out.println("index out of range"+ "\n");
                } else {
                    System.out.print("> ");
                    System.out.println(i+ "\n");
                }
                return;
            }
        }
        System.out.print("> ");
        System.out.println("no such key" + "\n");
    }
    private void pluck(String key, int index) {
        for (Entry entry : entries) {
            if(entry.getKey().equals(key)){
                if (index == 0) {
                    System.out.print("> ");
                    System.out.println("index out of range"+ "\n");
                    return;
                }
                if (entry.getValues().isEmpty()) {
                    System.out.print("> ");
                    System.out.println("index out of range"+ "\n");
                    return;
                }else if (index > entry.getValues().size()) {
                    System.out.print("> ");
                    System.out.println("index out of range"+ "\n");
                    return;
                } else {
                    System.out.print("> ");
                    System.out.println(entry.pluck(index)+ "\n");
                    return;
                }
            }
        }
        System.out.print("> ");
        System.out.println("no such key" + "\n");
    }
    private void pop(String key) {
        for (Entry entry : entries) {
            if(entry.getKey().equals(key)){
                if(entry.len() == 0) {
                    System.out.print("> ");
                    System.out.println("nil"+ "\n");
                }else {
                    Integer i = entry.pop();
                    System.out.print("> ");
                    System.out.println(i+ "\n");
                }
                return;
            }
        }
        System.out.print("> ");
        System.out.println("no such key" + "\n");
    }
    private void drop(int id) {
        if (snapshots.size()<id){
            System.out.print("> ");
            System.out.println("no such snapshot"+ "\n");
        } else {
            snapshots.remove(id);
        }
    }

    public Snapshot getSnapshotByID(int id){
        for(Snapshot snapshot : snapshots){
            if(snapshot.getId() == id){
                return snapshot;
            }
        }
        return null;
    }

    private void rollback(int id) {
        if(snapshots.size()<id) {
            System.out.print("> ");
            System.out.println("no such snapshot"+ "\n");
            return;
        }
        refreshEntries(id);
        snapshots = snapshots.subList(snapshots.size() - id, snapshots.size());
        System.out.print("> ");
        System.out.println("ok"+ "\n");
    }

    //id: id of snapshot
    private void refreshEntries(int id){
        Snapshot snapshot = getSnapshotByID(id);
        entries.clear();
        for(Entry entry : snapshot.rollback()){
            List<Integer> tmp = new ArrayList<>();
            for(Integer i : entry.getValues()){
                tmp.add(i);
            }
            entries.add(new Entry(entry.getKey(), tmp));
        }
    }

    private void checkout(int id) {
        if(id > snapshots.size()) {
            System.out.print("> ");
            System.out.println("no such snapshot"+ "\n");
            return;
        }
        refreshEntries(id);
        System.out.print("> ");
        System.out.println("ok"+ "\n");
    }

    private void snapshot() {
        List<Entry> dumpEntries = new ArrayList<>();
        for(Entry entry : entries) {
            dumpEntries.add(entry);
        }
        Snapshot snapshot = new Snapshot(snapshots.size()+1, dumpEntries);
        snapshots.add(0, snapshot);
        System.out.print("> ");
        System.out.println("saved as snapshot " + snapshots.size()+ "\n");
    }
    private void archive(int id, String filename) throws IOException {
        if(id > snapshots.size()) {
            System.out.print("> ");
            System.out.println("no such snapshot"+ "\n");
        } else {
            snapshots.get(id-1).archive(filename);
        }
    }

    private void restore(String filename) throws IOException {
        entries = Snapshot.restore(filename);
        snapshots.clear();
        System.out.print("> ");
        System.out.println("ok"+ "\n");
    }

    private void min(String key) {
        for (Entry entry : entries) {
            if (entry.getKey().equals(key)){
                Integer min = entry.min();
                if(null == min) {
                    System.out.print("> ");
                    System.out.println("nil"+ "\n");
                } else {
                    System.out.print("> ");
                    System.out.println(min+ "\n");
                }
                return;
            }
        }
        System.out.print("> ");
        System.out.println("no such key" + "\n");
    }

    private void max(String key) {
        for (Entry entry : entries) {
            if(entry.getKey().equals(key)){
                Integer max = entry.max();
                if(null == max) {
                    System.out.print("> ");
                    System.out.println("nil"+ "\n");
                } else {
                    System.out.print("> ");
                    System.out.println(max+ "\n");
                }
                return;
            }
        }
        System.out.print("> ");
        System.out.println("no such key" + "\n");
    }
    private void sum(String key) {
        for (Entry entry : entries) {
            if(entry.getKey().equals(key)){
                Integer sum = entry.sum();
                if(null == sum) {
                    System.out.print("> ");
                    System.out.println("nil"+ "\n");
                } else {
                    System.out.print("> ");
                    System.out.println(sum+ "\n");
                }
                return;
            }
        }
        System.out.print("> ");
        System.out.println("no such key" + "\n");
    }
    private void len(String key) {
        for (Entry entry : entries) {
            if(entry.getKey().equals(key)){
                System.out.print("> ");
                System.out.println(entry.len()+ "\n");
                return;
            }
        }
        System.out.print("> ");
        System.out.println("no such key" + "\n");
    }

    private void rev(String key) {
        for (Entry entry : entries) {
            if(entry.getKey().equals(key)){
                entry.rev();
                System.out.print("> ");
                System.out.println("ok"+ "\n");
                return;
            }
        }
        System.out.print("> ");
        System.out.println("no such key" + "\n");
    }

    private void uniq(String key) {
        for (Entry entry : entries) {
            if(entry.getKey().equals(key)){
                entry.uniq();
                System.out.print("> ");
                System.out.println("ok"+ "\n");
                return;
            }
        }
        System.out.print("> ");
        System.out.println("no such key"+ "\n");
    }


    private void sort(String key) {
        for (Entry entry : entries) {
            if(entry.getKey().equals(key)){
                entry.sort();
                System.out.print("> ");
                System.out.println("ok"+ "\n");
                return;
            }
        }
        System.out.print("> ");
        System.out.println("no such key"+ "\n");
    }
    private void diff(List<String> keys) {
        List<Entry> targetEntries = new ArrayList<>();
        for(String key : keys){
            for (Entry entry : entries) {
                if(entry.getKey().equals(key)){
                    targetEntries.add(entry);
                    break;
                }
            }
        }
        List<Integer> diff =  Entry.diff(targetEntries);
        String res = diff.toString().replace(',', ' ').replaceAll("  ", " ");
        System.out.print("> ");
        System.out.println(res+ "\n");
    }

    private void inter(List<String> keys) {
        List<Entry> targetEntries = new ArrayList<>();
        for(String key : keys){
            for (Entry entry : entries) {
                if(entry.getKey().equals(key)){
                    targetEntries.add(entry);
                    break;
                }
            }
        }
        List<Integer> inter =  Entry.inter(targetEntries);
        String res = inter.toString().replace(',', ' ').replaceAll("  ", " ");
        System.out.print("> ");
        System.out.println(res+ "\n");
    }

    private void union(List<String> keys) {
        List<Entry> targetEntries = new ArrayList<>();
        for(String key : keys){
            for (Entry entry : entries) {
                if(entry.getKey().equals(key)){
                    targetEntries.add(entry);
                    break;
                }
            }
        }
        List<Integer> union =  Entry.union(targetEntries);
        String res = union.toString().replace(',', ' ').replaceAll("  ", " ");
        System.out.print("> ");
        System.out.println(res + "\n");
    }

    private void cartprod(List<String> keys) {
        List<Entry> targetEntries = new ArrayList<>();
        for(int i=0;i<keys.size();i++){
            for(Entry entry : entries){
                if(keys.get(i).equals(entry.getKey())){
                    targetEntries.add(entry);
                }
            }
        }
        List<List<Integer>> cart =  Entry.cartprod(targetEntries);
        String res = cart.toString().replace(',', ' ').replaceAll("  ", " ");
        String res1 = "[ " + res.substring(1, res.length()-1) + " ]";
        System.out.print("> ");
        System.out.println(res1 + "\n");
    }

    private static final String HELP =
                "> BYE   clear database and exit\n"+
                "HELP  display this help message\n"+
                "\n"+
                "LIST KEYS       displays all keys in current state\n"+
                "LIST ENTRIES    displays all entries in current state\n"+
                "LIST SNAPSHOTS  displays all snapshots in the database\n"+
                "\n"+
                "GET <key>    displays entry values\n"+
                "DEL <key>    deletes entry from current state\n"+
                "PURGE <key>  deletes entry from current state and snapshots\n"+
                "\n"+
                "SET <key> <value ...>     sets entry values\n"+
                "PUSH <key> <value ...>    pushes values to the front\n"+
                "APPEND <key> <value ...>  appends values to the back\n"+
                "\n"+
                "PICK <key> <index>   displays value at index\n"+
                "PLUCK <key> <index>  displays and removes value at index\n"+
                "POP <key>            displays and removes the front value\n"+
                "\n"+
                "DROP <id>      deletes snapshot\n"+
                "ROLLBACK <id>  restores to snapshot and deletes newer snapshots\n"+
                "CHECKOUT <id>  replaces current state with a copy of snapshot\n"+
                "SNAPSHOT       saves the current state as a snapshot\n"+
                "\n"+
                "ARCHIVE <id> <filename> saves snapshot to file\n"+
                "RESTORE <filename> loads snapshot from file\n"+
                "\n"+
                "MIN <key>  displays minimum value\n"+
                "MAX <key>  displays maximum value\n"+
                "SUM <key>  displays sum of values\n"+
                "LEN <key>  displays number of values\n"+
                "\n"+
                "REV <key>   reverses order of values\n"+
                "UNIQ <key>  removes repeated adjacent values\n"+
                "SORT <key>  sorts values in ascending order\n"+
                "\n"+
                "DIFF <key> <key ...>   displays set difference of values in keys\n"+
                "INTER <key> <key ...>  displays set intersection of values in keys\n"+
                "UNION <key> <key ...>  displays set union of values in keys\n"+
                "CARTPROD <key> <key ...>  displays set union of values in keys\n";

    public static void bye() {
        entries.clear();
        snapshots.clear();
        System.out.print("> ");
        System.out.println("bye");
    }
    public static void help() {
        System.out.println(HELP);
    }
    
    public static void main(String[] args) {
        
        try{
            CrunchDB crunchDB = new CrunchDB();
            Scanner scanner = new Scanner(System.in);
            
            while (true){
                String cmd =scanner.nextLine();
                
                if(cmd.toUpperCase().equals("BYE")){
                    bye();
                    return;
                }else if(cmd.toUpperCase().equals("HELP")){
                    help();
                }else if(cmd.toUpperCase().equals("LIST KEYS")){
                    crunchDB.listKeys();
                }else if(cmd.toUpperCase().equals("LIST ENTRIES")){
                    crunchDB.listEntries();
                }else if("LIST SNAPSHOTS".equals(cmd.toUpperCase())){
                    crunchDB.listSnapshot();
                }else{
                    String[] params = cmd.split("[ ]+");
                    if(params[0].toUpperCase().equals("GET")){
                        crunchDB.get(params[1]);
                    } else if(params[0].toUpperCase().equals("DEL")){
                        crunchDB.del(params[1]);
                    } else if(params[0].toUpperCase().equals("PURGE")){
                        crunchDB.purge(params[1]);
                    }else if(params[0].toUpperCase().equals("SET") || params[0].toUpperCase().equals("PUSH") || params[0].toUpperCase().equals("APPEND")){
                        String key = params[1];
                        List<Integer> values = new ArrayList<>();
                        for(int i=2;i<params.length;i++){
                            values.add(Integer.parseInt(params[i]));
                        }
                        switch (params[0].toUpperCase()){
                            case  "SET":
                                crunchDB.set(key, values);
                                break;
                            case "PUSH":
                                crunchDB.push(key, values);
                                break;
                            case "APPEND":
                                crunchDB.append(key,values);
                                break;
                        }
                    }else if(params[0].toUpperCase().equals("PICK")){
                        crunchDB.pick(params[1], Integer.parseInt(params[2]));
                    }else if(params[0].toUpperCase().equals("PLUCK")){
                        crunchDB.pluck(params[1], Integer.parseInt(params[2]));
                    }else if (params[0].toUpperCase().equals("POP")){
                        crunchDB.pop(params[1]);
                    }else if(params[0].toUpperCase().equals("DROP")){
                        crunchDB.drop(Integer.parseInt(params[1]));
                    }else if(params[0].toUpperCase().equals("ROLLBACK")){
                        crunchDB.rollback(Integer.parseInt(params[1]));
                    }else if(params[0].toUpperCase().equals("CHECKOUT")){
                        crunchDB.checkout(Integer.parseInt(params[1]));
                    }else if(params[0].toUpperCase().equals("SNAPSHOT")){
                        crunchDB.snapshot();
                    }else if(params[0].toUpperCase().equals("ARCHIVE")){
                        crunchDB.archive(Integer.parseInt(params[1]), params[2]);
                    }else if(params[0].toUpperCase().equals("RESTORE")){
                        crunchDB.restore( params[1]);
                    }else if(params[0].toUpperCase().equals("MIN")){
                        crunchDB.min( params[1]);
                    }else if(params[0].toUpperCase().equals("MAX")){
                        crunchDB.max( params[1]);
                    }else if(params[0].toUpperCase().equals("SUM")){
                        crunchDB.sum( params[1]);
                    }else if(params[0].toUpperCase().equals("LEN")){
                        crunchDB.len( params[1]);
                    }else if(params[0].toUpperCase().equals("REV")){
                        crunchDB.rev( params[1]);
                    }else if(params[0].toUpperCase().equals("UNIQ")){
                        crunchDB.uniq( params[1]);
                    }else if(params[0].toUpperCase().equals("SORT")){
                        crunchDB.sort( params[1]);
                    }else if(params[0].toUpperCase().equals("DIFF") || params[0].equals("INTER") || params[0].equals("UNION")) {
                        List<String> keys = new ArrayList<>();
                        for (int i = 1; i < params.length; i++) {
                            keys.add(params[i]);
                        }
                        switch (params[0].toUpperCase()) {
                            case "DIFF":
                                crunchDB.diff(keys);
                                break;
                            case "INTER":
                                crunchDB.inter(keys);
                                break;
                            case "UNION":
                                crunchDB.union(keys);
                                break;
                        }
                    }else if(params[0].toUpperCase().equals("CARTPROD")){
                        List<String> keys = new ArrayList<>();
                        for(int i=1;i<params.length;i++){
                            keys.add(params[i]);
                        }
                        crunchDB.cartprod(keys);
                    }
                }
            }
        }catch (IOException | NumberFormatException e){
        }

    }
}