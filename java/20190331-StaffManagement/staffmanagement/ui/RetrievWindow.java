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

public class RetrievWindow {
    
    RetrievWindow(){
        // main
        JFrame jf = new JFrame("Retrieving");
        jf.setSize(580, 500);
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
        JTextField fDateOfBirthStart = new JTextField();
        JTextField fDateOfBirthEnd = new JTextField();
        JTextField fDateOfEntryStart = new JTextField();
        JTextField fDateOfEntryEnd = new JTextField();
        JTextField fMobile = new JTextField();
        JTextField fAddress = new JTextField();
        JButton retrievByNameBtn = new JButton("retriev by name");
        JButton retrievByGenderBtn = new JButton("retriev by gender");
        JButton retrievByIdCardNoBtn = new JButton("retriev by id card no");
        JButton retrievByBirthDateBtn = new JButton("retriev by birth date");
        JButton retrievByEntryDateBtn = new JButton("retriev by entry time");
        JButton retrievByMobileBtn = new JButton("retriev by mobile");
        JButton retrievByAddressBtn = new JButton("retriev by address");
        
        // name
        retrievByNameBtn.addActionListener((ActionEvent e) -> {
            String[] retriev = new String[2];
            if(fName.getText().equals("")){
                JOptionPane.showOptionDialog(jp, "name can't be null!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                return;
            }else{
                retriev[0] = fName.getText();
            }
            MainWindow.DoRetriev("name", retriev);
            jf.dispose();
        });
        
        // gender
        retrievByGenderBtn.addActionListener((ActionEvent e) -> {
            String[] retriev = new String[2];
            if(fGender.getText().equals("")){
                JOptionPane.showOptionDialog(jp, "gender can't be null!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                return;
            }else{
                retriev[0] = fGender.getText();
            }
            MainWindow.DoRetriev("gender", retriev);
            jf.dispose();
        });
        
        // id card no 
        retrievByIdCardNoBtn.addActionListener((ActionEvent e) -> {
            String[] retriev = new String[2];
            if(fIdCardNo.getText().equals("")){
                JOptionPane.showOptionDialog(jp, "id card no can't be null!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                return;
            }else{
                retriev[0] = fIdCardNo.getText();
            }
            MainWindow.DoRetriev("IdCardNo", retriev);
            jf.dispose();
        });
        
        // birth date
        retrievByBirthDateBtn.addActionListener((ActionEvent e) -> {
            String[] retriev = new String[2];
            if(fDateOfBirthStart.getText().equals("")){
                JOptionPane.showOptionDialog(jp, "birth start time can't be null!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                return;
            }else{
                retriev[0] = fDateOfBirthStart.getText();
            }
            if(fDateOfBirthEnd.getText().equals("")){
                JOptionPane.showOptionDialog(jp, "birth end time can't be null!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                return;
            }else{
                retriev[1] = fDateOfBirthEnd.getText();
            }
            MainWindow.DoRetriev("dateOfBirth", retriev);
            jf.dispose();
        });
        
        // entry date
        retrievByEntryDateBtn.addActionListener((ActionEvent e) -> {
            String[] retriev = new String[2];
            if(fDateOfEntryStart.getText().equals("")){
                JOptionPane.showOptionDialog(jp, "entry start time can't be null!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                return;
            }else{
                retriev[0] = fDateOfEntryStart.getText();
            }
            if(fDateOfEntryEnd.getText().equals("")){
                JOptionPane.showOptionDialog(jp, "entry end time can't be null!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                return;
            }else{
                retriev[1] = fDateOfEntryEnd.getText();
            }
            MainWindow.DoRetriev("dateOfEntry", retriev);
            jf.dispose();
        });
        
        // mobile
        retrievByMobileBtn.addActionListener((ActionEvent e) -> {
            String[] retriev = new String[2];
            if(fMobile.getText().equals("")){
                JOptionPane.showOptionDialog(jp, "mobile can't be null!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                return;
            }else{
                retriev[0] = fMobile.getText();
            }
            MainWindow.DoRetriev("mobile", retriev);
            jf.dispose();
        });
        
        // address
        retrievByAddressBtn.addActionListener((ActionEvent e) -> {
            String[] retriev = new String[2];
            if(fAddress.getText().equals("")){
                JOptionPane.showOptionDialog(jp, "address can't be null!", "warning",JOptionPane.YES_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                return;
            }else{
                retriev[0] = fAddress.getText();
            }
            MainWindow.DoRetriev("address", retriev);
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
        jp.add(fDateOfBirthStart);
        jp.add(fDateOfBirthEnd);
        jp.add(fDateOfEntryStart);
        jp.add(fDateOfEntryEnd);
        jp.add(fMobile);
        jp.add(fAddress);
        jp.add(retrievByNameBtn);
        jp.add(retrievByGenderBtn);
        jp.add(retrievByIdCardNoBtn);
        jp.add(retrievByBirthDateBtn);
        jp.add(retrievByEntryDateBtn);
        jp.add(retrievByMobileBtn);
        jp.add(retrievByAddressBtn);
        
        con.add(jp);
        
        lName.setBounds(50, 50, 150, 25);
        fName.setBounds(200, 50, 150, 25);
        retrievByNameBtn.setBounds(370, 50, 150, 25);
        lGender.setBounds(50, 100, 150, 25);
        fGender.setBounds(200, 100, 150, 25);
        retrievByGenderBtn.setBounds(370, 100, 150, 25);
        lIdCardNo.setBounds(50, 150, 150, 25);
        fIdCardNo.setBounds(200, 150, 150, 25);
        retrievByIdCardNoBtn.setBounds(370, 150, 150, 25);
        lDateOfBirth.setBounds(50, 200, 150, 25);
        fDateOfBirthStart.setBounds(200, 200, 70, 25);
        fDateOfBirthEnd.setBounds(280, 200, 70, 25);
        retrievByBirthDateBtn.setBounds(370, 200, 150, 25);
        lDateOfEntry.setBounds(50, 250, 150, 25);
        fDateOfEntryStart.setBounds(200, 250, 70, 25);
        fDateOfEntryEnd.setBounds(280, 250, 70, 25);
        retrievByEntryDateBtn.setBounds(370, 250, 150, 25);
        lMobile.setBounds(50, 300, 150, 25);
        fMobile.setBounds(200, 300, 150, 25);
        retrievByMobileBtn.setBounds(370, 300, 150, 25);
        lAddress.setBounds(50, 350, 150, 25);
        fAddress.setBounds(200, 350, 150, 25);
        retrievByAddressBtn.setBounds(370, 350, 150, 25);
        
        jf.setVisible(true);
    }
}
