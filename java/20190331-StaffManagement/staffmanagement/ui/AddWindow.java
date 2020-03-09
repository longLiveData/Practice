package staffmanagement.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static staffmanagement.ui.MainWindow.options;

public class AddWindow{
    
    AddWindow(){
        // main
        JFrame jf = new JFrame("Adding");
        jf.setSize(500, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        
        Container con = jf.getContentPane();
        JPanel jp = new JPanel();
        jp.setLayout(null);
        
        // labels and inputs
        JLabel lName = new JLabel("name:");
        JLabel lGender = new JLabel("gender(male or female):");
        JLabel lIdCardNo = new JLabel("Id card number:");
        JLabel lDateOfBirth = new JLabel("birth date(2019-02-29):");
        JLabel lDateOfEntry = new JLabel("entry date(2019-02-29):");
        JLabel lMobile = new JLabel("mobile:");
        JLabel lAddress = new JLabel("address:");
        JTextField fName = new JTextField();
        JTextField fGender = new JTextField();
        JTextField fIdCardNo = new JTextField();
        JTextField fDateOfBirth = new JTextField();
        JTextField fDateOfEntry = new JTextField();
        JTextField fMobile = new JTextField();
        JTextField fAddress = new JTextField();
        JButton commitBtn = new JButton("add");
        
        commitBtn.addActionListener((ActionEvent e) -> {
            String[] addArgs = new String[7];
            if(fName.getText().equals("")){
                JOptionPane.showOptionDialog(jp, "name can't be null!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                return;
            }else{
                addArgs[0] = fName.getText();
            }
            if(fGender.getText().equals("")){
                JOptionPane.showOptionDialog(jp, "gender can't be null!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                return;
            }else{
                addArgs[1] = fGender.getText();
            }
            if(fIdCardNo.getText().equals("")){
                JOptionPane.showOptionDialog(jp, "id card number can't be null!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                return;
            }else{
                addArgs[2] = fIdCardNo.getText();
            }
            if(fDateOfBirth.getText().equals("")){
                JOptionPane.showOptionDialog(jp, "birth time can't be null!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                return;
            }else{
                addArgs[3] = fDateOfBirth.getText();
            }
            if(fDateOfEntry.getText().equals("")){
                JOptionPane.showOptionDialog(jp, "entry time can't be null!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                return;
            }else{
                addArgs[4] = fDateOfEntry.getText();
            }
            if(fMobile.getText().equals("")){
                JOptionPane.showOptionDialog(jp, "mobile can't be null!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                return;
            }else{
                addArgs[5] = fMobile.getText();
            }
            if(fAddress.getText().equals("")){
                JOptionPane.showOptionDialog(jp, "address can't be null!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                return;
            }else{
                addArgs[6] = fAddress.getText();
            }
            
            MainWindow.DoAdd(addArgs);
            jf.dispose();
        });
        
        jp.add(lName);
        jp.add(lGender);
        jp.add(lIdCardNo);
        jp.add(lDateOfBirth);
        jp.add(lDateOfEntry);
        jp.add(lMobile);
        jp.add(lAddress);
        jp.add(fName);
        jp.add(fGender);
        jp.add(fIdCardNo);
        jp.add(fDateOfBirth);
        jp.add(fDateOfEntry);
        jp.add(fMobile);
        jp.add(fAddress);
        jp.add(commitBtn);
        
        con.add(jp);
        
        lName.setBounds(50, 50, 150, 25);
        fName.setBounds(200, 50, 150, 25);
        lGender.setBounds(50, 100, 150, 25);
        fGender.setBounds(200, 100, 150, 25);
        lIdCardNo.setBounds(50, 150, 150, 25);
        fIdCardNo.setBounds(200, 150, 150, 25);
        lDateOfBirth.setBounds(50, 200, 150, 25);
        fDateOfBirth.setBounds(200, 200, 150, 25);
        lDateOfEntry.setBounds(50, 250, 150, 25);
        fDateOfEntry.setBounds(200, 250, 150, 25);
        lMobile.setBounds(50, 300, 150, 25);
        fMobile.setBounds(200, 300, 150, 25);
        lAddress.setBounds(50, 350, 150, 25);
        fAddress.setBounds(200, 350, 150, 25);
        commitBtn.setBounds(160, 400, 140, 30);
        
        jf.setVisible(true);   
    }
}
