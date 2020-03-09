package booking.management;

public class RecordListTest {
    
    public static void test(){
        RecordList rl = new RecordList();
        String[] args = {"1", "1", "startTime", 
                "endTime", "singleType", "singleName", "booked"};
        rl.add(args);
        rl.add(args);
        rl.add(args);
        System.out.println("correct test:" + rl.cancel("singleType", 0));
        System.out.println("error test:" + rl.cancel("singleType", 3));
        System.out.println("border test:" + rl.cancel("singleType", 2));
    }
    
    public static void main(String[] args){
        test();
    }
    
}
