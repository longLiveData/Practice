package staffmanagement.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import staffmanagement.Staff;

/**
 * main frame
 */
public class MainWindow {
    
    // about frame
    private static JFrame jf = null;
    private static Container con = null;
    private static JPanel jp = null;
    private static JTable jt = null;
    
    // about date
    public static Staff st = null;
    
    // update
    public static String updateIdCardNo = "";
    
    // warning
    public static Object[] options = {"ok"};
    
    // operations
    // add
    static void DoAdd(String[] addArgs){
        if(!st.add(addArgs)){
            JOptionPane.showOptionDialog(jp, "add failed!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        }else{
            // refresh show tables
            String resStr = st.getAllInfo();
            refreshShowTable(resStr);
        }
    }
    
    // retriev
    static void DoRetriev(String type, String[] args){
        String resStr = st.retriev(type, args);
        if(resStr.equals("")){
            JOptionPane.showOptionDialog(jp, "no result!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        }else{
            refreshShowTable(resStr);
        }
    }
    
    // update
    static void DoUpdate(String recordIdCardNo, String[] args){
        if(!st.update(recordIdCardNo, args)){
            JOptionPane.showOptionDialog(jp, "update failed!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        }else{
            // refresh show tables
            String resStr = st.getAllInfo();
            refreshShowTable(resStr);
        }
    }
    
    // delete
    static void DoDelete(String cIdCardNo){
        if(!st.delete(cIdCardNo)){
            // create warning frame
            JOptionPane.showOptionDialog(jp, "delete failed!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        }else{
            // refresh show tables
            String resStr = st.getAllInfo();
            refreshShowTable(resStr);
        }
    }
    
    // refresh show tables
    static void refreshShowTable(String resStr){
        
        //((TableModel)jt.getModel()).get getData().clear();
        jt.removeAll();
        
        Object[] tableHead = {"name", "gender", "idCardNo", "birth date", 
            "entry date", "mobile", "address"};
        
        // get data
        String[] records = resStr.split(",");
        Object[][] tableDate = new Object[records.length][7];
        String[] lineDate = new String[7];
        
        int i=0, j;   
        for(String record: records){
            String[] paras = record.split("_");
            j = 0;
            for(String para: paras){
                tableDate[i][j] = para;
                j++;
            }
            i++;
        }
        
        DefaultTableModel dtmView = new DefaultTableModel(tableDate, tableHead);
        jt.setModel(dtmView);
    }
    
    public static int[] reverseArray(int[] array){
        int [] newArray = new int[array.length];
        for(int i=0; i<newArray.length; i++){
            newArray[i] = array[array.length - i - 1];
        }
        return newArray;
    }
    
    
    public static void createMainWindow(){
        
        //main
        jf = new JFrame("staff management");
        jf.setSize(790, 600);
        jf.setLocation(300,200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        
        con = jf.getContentPane();
        jp = new JPanel();
        jp.setLayout(null);
        
        // buttons
        JButton addBtn = new JButton("adding");
        JButton retrievBtn = new JButton("retrievind");
        JButton updateBtn = new JButton("updating");
        JButton deleteBtn = new JButton("deleting");
        
        // add
        addBtn.addActionListener((ActionEvent e) -> {
            AddWindow aw = new AddWindow();
        });
        // retriev
        retrievBtn.addActionListener((ActionEvent e) -> {
            RetrievWindow rw = new RetrievWindow();
        });
        // update
        updateBtn.addActionListener((ActionEvent e) -> {
            int[] indexArray = jt.getSelectedRows();
            if(indexArray.length == 0){
                JOptionPane.showOptionDialog(jp, "please choose update line!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }else if(indexArray.length > 1){
                JOptionPane.showOptionDialog(jp, "you can only update one line!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }else{
                updateIdCardNo = jt.getValueAt(indexArray[0], 2).toString();
                UpdateWindow uw = new UpdateWindow();
            }
        });
        // delete
        deleteBtn.addActionListener((ActionEvent e) -> {
            int[] indexArray = reverseArray(jt.getSelectedRows());
            if(indexArray.length == 0){
                JOptionPane.showOptionDialog(jp, "nothing to delete!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }else{
                for(int index: indexArray){
                String deleteIdCardNo = jt.getValueAt(index, 2).toString();
                DoDelete(deleteIdCardNo);
            }
            }
        });
        
        // table
        Object[] tableHead = {"name", "gender", "idCardNo", "birth date", 
            "entry date", "mobile", "address"};
        Object[][] tableBody = {};
        
        jt = new JTable(tableBody, tableHead);
        jt.setPreferredScrollableViewportSize(new Dimension(550, 100));
        jt.getTableHeader().setPreferredSize(new Dimension(1, 35));
        jt.getTableHeader().setBackground(Color.LIGHT_GRAY);
        jt.setRowHeight(30); 
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();     
        r.setHorizontalAlignment(JLabel.CENTER);     
        jt.setDefaultRenderer(Object.class, r); 
        JScrollPane scroll = new JScrollPane(jt);
        jf.getContentPane().add(jt.getTableHeader(),BorderLayout.NORTH);
        scroll.setSize(300, 200);
        
        jp.add(addBtn);
        jp.add(retrievBtn);
        jp.add(updateBtn);
        jp.add(deleteBtn);
        jp.add(jt);
        con.add(jp);
        
        addBtn.setBounds(120,420,120,40);
        retrievBtn.setBounds(260,420,120,40);
        updateBtn.setBounds(400,420,120,40);
        deleteBtn.setBounds(540,420,120,40);
        jt.setBounds(0,0,780,360);
        
        refreshShowTable(st.getAllInfo());
        
        jf.setVisible(true);
    }
    
}

