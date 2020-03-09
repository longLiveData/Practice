package booking.management;

import booking.management.frame.MainFrame;

/**
 * entrance of program
 */
public class Main {
    
    public static ClassList cl;
    public static FacilityList fl;
    public static RecordList rl;
    
    public static Object[] options = {"ok"};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        cl = new ClassList();
        fl = new FacilityList();
        
        // create data
        String[] a1 = {"yoga", "5","0", "Lily", "2019-05-01","A101"};
        String[] a2 = {"training","5", "0", "Tom", "2019-06-01","A201"};
        String[] a3 = {"football", "free", "2018-01-01"};
        String[] a4 = {"ping-pang","free","2018-01-01"};
        cl.add(a1);
        cl.add(a2);
        fl.add(a3);
        fl.add(a4);
        
        rl = new RecordList();
        
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
        
    }
    
}
