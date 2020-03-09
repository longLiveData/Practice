import java.util.*;

public class Entry {
    
    private String key;
    private List<Integer> values;

    Entry(String key, List<Integer> values){
        this.key = key;
        this.values = values;
    }

    void append(List<Integer> values){
        this.values.addAll(values);
    }

    private static List<Integer> deepCopy(List<Integer> list){
        List<Integer> res = new ArrayList<>();
        for(Integer i : list){
            res.add(i);
        }
        return res;
    }

    //Computes the Cartesian product of the entries.
    static List<List<Integer>> cartprod(List<Entry> entries){
        List<List<Integer>> res = new ArrayList<>();

        if(entries.size() == 2) {
            List<Integer> l1 = entries.get(0).getValues();
            List<Integer> l2 = entries.get(1).getValues();
            for (Integer i1: l1) {
                for (Integer i2: l2) {
                    List<Integer> n = new ArrayList<>();
                    n.add(i1);
                    n.add(i2);
                    res.add(n);
                }
            }
        }

        if(entries.size() == 3) {
            List<Integer> l1 = entries.get(0).getValues();
            List<Integer> l2 = entries.get(1).getValues();
            List<Integer> l3 = entries.get(2).getValues();
            for (Integer i1: l1) {
                for (Integer i2: l2) {
                    for (Integer i3: l3) {
                        List<Integer> n = new ArrayList<>();
                        n.add(i1);
                        n.add(i2);
                        n.add(i3);
                        res.add(n);
                    }
                }
            }
        }
        return res;
    }

    static List<Integer> diff(List<Entry> entries){
        Set<Integer> diff = new HashSet<>();
        Set<Integer> exist = new HashSet<>();
        for(Integer val : entries.get(0).getValues()){
            diff.add(val);
            exist.add(val);
        }
        for(int j=1;j<entries.size();j++){
            List<Integer> cmp = entries.get(j).getValues();
            Set<Integer> s1 = new HashSet<>();
            for(Integer k : cmp){ 
                s1.add(k);
            }
            for(Integer i : s1){
                if(diff.contains(i)) {
                    diff.remove(i);
                } else if (! exist.contains(i)) {
                    diff.add(i);
                    exist.add(i);
                }
            }
        }
        return new ArrayList<>(diff);
    }

    //Formats the Entry for display
    public String get(){
        if(0==values.size()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Integer i : values){
            sb.append(i).append(" ");
        }
        sb.setCharAt(sb.length()-1, ']');
        return sb.toString();
    }
    
    public String get1(){
        if(0==values.size()) {
            return key + " []";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(key).append(" [");
        for(Integer i : values){
            sb.append(i).append(" ");
        }
        sb.setCharAt(sb.length()-1, ']');
        return sb.toString();
    }

    static List<Integer> inter(List<Entry> entries){
        
        Map<Integer, Integer> map = new HashMap<>();
        for(Integer val : entries.get(0).getValues()){
            map.put(val, 1);
        }
        for(int j=1;j<entries.size();j++){
            List<Integer> cmp = entries.get(j).getValues();
            Set<Integer> s1 = new HashSet<>();
            for(Integer k : cmp){ 
                s1.add(k);
            }
            for(Integer i : s1){
                if(map.get(i) != null) {
                    map.put(i, map.get(i) + 1);
                }
            }
        }
        List<Integer> inter = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == entries.size()) {
                inter.add(entry.getKey());
            }
        }
        return inter;
    }

    public Integer len(){
        return values.size();
    }

    //Formats all the entries for display.
    static String listAllEntries(List<Entry> entries){
        String res = "";
        for(Entry entry : entries){
            res += entry.getKey() + " " + entry.get() + "\n";
        }
        String temp = res.substring(0, res.length()-1);
        return temp;
    }

    public Integer max(){
        if(values.size() == 0) {
            return null;
        }
        
        int max = Integer.MIN_VALUE;
        for(Integer i : values){
            if(i>max) max = i;
        }
        return max;
    }

    public Integer min(){
        if(values.size() == 0) {
            return null;
        }
        int min = Integer.MAX_VALUE;
        for(Integer i : values){
            if(i<min) min = i;
        }
        return min;
    }

    public Integer pick(int index){
        if(index<1 || index>values.size()) {
            return null;
        }
        return values.get(index - 1);
    }

    //Finds and removes the value at the given index.
    public Integer pluck(int index){
        return values.remove(index-1);
    }

    public Integer pop(){
        if(values.isEmpty()){
            return null;
        }
        return values.remove(0);
    }

    public void push(List<Integer> values){
        for(Integer i:values){
            this.values.add(0,i);
        }
    }

    public void rev(){
        List<Integer> reversed = new ArrayList<>();
        for(Integer i : values){
            reversed.add(0, i);
        }
        values = reversed;
    }

    //Sets the values of this Entry.
    public void set(List<Integer> values){
        this.values.clear();
        this.values = values;
    }

    public void sort(){
        Collections.sort(values);
    }

    public Integer sum(){
        if(values.isEmpty()) {
            return null;
        }
        int sum = 0;
        for(int i : values){
            sum += i;
        }
        return sum;
    }

    public static List<Integer> union(List<Entry> entries){
        Set<Integer> union = new HashSet<>();
        for(Entry entry : entries){
            for(Integer i:entry.getValues()){
                union.add(i);
            }
        }
        return new ArrayList<>(union);
    }

    public void uniq(){
        List<Integer> newArr = new ArrayList<>();
        newArr.add(values.get(0));
        for(int i=1;i<values.size();i++){
            if(!Objects.equals(values.get(i), newArr.get(newArr.size()-1))) {
                newArr.add(values.get(i));
            }
        }
        values = newArr;
    }

    public String getKey(){
        return  key;
    }

    public List<Integer> getValues() {
        return values;
    }
}