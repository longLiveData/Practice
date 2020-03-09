
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yangyu
 */
public class Status {
    private static Connection connection;
    private static PreparedStatement gainFlightListTable;
    private static PreparedStatement gainWaitingListTable;
    private static PreparedStatement gainFlightListTitle;
    private static PreparedStatement gainWaitingListTitle;
    private static PreparedStatement gainCustomerStatusListTitle;
    private static PreparedStatement gainCustomerStatusFlightListTable;
    private static PreparedStatement gainCustomerStatusWaitingListTable;
    private static PreparedStatement gainAllRecordOfFlightListTable;


    public static Vector<Vector<Object>> getFlightListTable(Date flightDate, String flightNumber) {
        try {
            Vector<Vector<Object>> flightStatusTable = new Vector<Vector<Object>>();
            connection = DBConnection.getConnection();
             gainFlightListTable = connection.prepareStatement("SELECT PASSENGERNAME, FLIGHTNUMBER, DATE FROM BOOKING_LIST WHERE DATE = ? and FlightNUMBER = ?");
            gainFlightListTable.setDate(1, flightDate);
            gainFlightListTable.setString(2, flightNumber);
            ResultSet rs = gainFlightListTable.executeQuery();
            while (rs.next()) 
            {
                Vector<Object> Data = new Vector<Object>();
                for (int i = 1; i <= 3; i++)
                {
                    Data.add(rs.getObject(i));
                }
                flightStatusTable.add(Data);
            }
            return flightStatusTable;
        }
        catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }



    public static Vector<Vector<Object>> getWaitingListTable(Date flightDate) {
        try {
            Vector<Vector<Object>> getWaitingListTable = new Vector<Vector<Object>>();
            connection = DBConnection.getConnection();
            gainWaitingListTable = connection.prepareStatement("SELECT PASSENGERNAME, FLIGHTNUMBER, DATE FROM WAITING_LIST WHERE DATE = ?");
            gainWaitingListTable.setDate(1, flightDate);
            ResultSet rs = gainWaitingListTable.executeQuery();
            while (rs.next()) 
            {
                Vector<Object> Data = new Vector<Object>();
                for (int i = 1; i <= 3; i++) {
                    Data.add(rs.getObject(i));
                }
                getWaitingListTable.add(Data);
            }
            return getWaitingListTable;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

public static Vector<String> getFlightListTitle() {
        try {
            int NumberOfCount;
            connection = DBConnection.getConnection();
            gainFlightListTitle = connection.prepareStatement("SELECT PASSENGERNAME, FLIGHTNUMBER, DATE FROM BOOKING_LIST UNION SELECT PASSENGERNAME, FLIGHTNUMBER, DATE FROM WAITING_LIST");
            ResultSet rs = gainFlightListTitle.executeQuery();
            ResultSetMetaData rsmd = gainFlightListTitle.getMetaData();
            NumberOfCount = rsmd.getColumnCount();
            Vector<String> title = new Vector<String>();
            for (int i = 1; i <= NumberOfCount; i++) {
                title.add(rsmd.getColumnName(i));
            }
            return title;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Vector<String> getWaitingListTitle() {
        try {
            int NumberOfCount;
            connection = DBConnection.getConnection();
            gainWaitingListTitle = connection.prepareStatement("SELECT PASSENGERNAME, FLIGHTNUMBER, DATE FROM WAITING_LIST");
            ResultSet rs = gainWaitingListTitle.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            NumberOfCount = rsmd.getColumnCount();
            Vector<String> title = new Vector<String>();
            for (int i = 1; i <= NumberOfCount; i++) 
            {
                title.add(rsmd.getColumnName(i));
            }
            return title;
        }
        catch (SQLException ex) 
        {
            ex.printStackTrace();
            return null;
        }
    }
        public static Vector<String> getCustomerStatusListTitle() {
        try {
            int NumberOfCount;
            connection = DBConnection.getConnection();
            gainCustomerStatusListTitle = connection.prepareStatement("SELECT FLIGHTNUMBER,DATE FROM WAITING_LIST");
            ResultSet rs = gainCustomerStatusListTitle.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
           NumberOfCount = rsmd.getColumnCount();
            Vector<String> title = new Vector<String>();
            for (int i = 1; i <= NumberOfCount; i++) 
            {
                title.add(rsmd.getColumnName(i));
            }
            return title;
        }
        catch (SQLException ex) 
        {
            ex.printStackTrace();
            return null;
        }
        }
        public static Vector<Vector<Object>> getCustomerStatusFlightListTable(String Customer) {
        try {
            Vector<Vector<Object>> CustomerStatusFlightListTable = new Vector<Vector<Object>>();
            connection = DBConnection.getConnection();
            gainCustomerStatusFlightListTable = connection.prepareStatement("SELECT FLIGHTNUMBER, DATE FROM BOOKING_LIST WHERE PASSENGERNAME = ?");
            gainCustomerStatusFlightListTable.setString(1, Customer);
            ResultSet rs = gainCustomerStatusFlightListTable.executeQuery();
            while (rs.next()) 
            {
                Vector<Object> Data = new Vector<Object>();
                for (int i = 1; i <= 2; i++)
                {
                    Data.add(rs.getObject(i));
                }
                CustomerStatusFlightListTable.add(Data);
            }
            return CustomerStatusFlightListTable;
        }
        catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
        
        public static Vector<Vector<Object>> getCustomerStatusWaitingListTable(String Customer) {
        try {
            Vector<Vector<Object>> CustomerStatusWaitingListTable = new Vector<Vector<Object>>();
            connection = DBConnection.getConnection();
            gainCustomerStatusWaitingListTable = connection.prepareStatement("SELECT FLIGHTNUMBER, DATE FROM WAITING_LIST WHERE PASSENGERNAME = ?");
            gainCustomerStatusWaitingListTable.setString(1, Customer);
            ResultSet rs = gainCustomerStatusWaitingListTable.executeQuery();
            while (rs.next()) 
            {
                Vector<Object> Data = new Vector<Object>();
                for (int i = 1; i <= 2; i++)
                {
                    Data.add(rs.getObject(i));
                }
                CustomerStatusWaitingListTable.add(Data);
            }
            return CustomerStatusWaitingListTable;
        }
        catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }
        
        public static ArrayList<ArrayList<Object>> getBookingRecordByFlight(String flight){
            try {
            ArrayList<ArrayList<Object>> getNameAndDateRecordListTable = new ArrayList<ArrayList<Object>>();
            connection = DBConnection.getConnection();
            gainAllRecordOfFlightListTable = connection.prepareStatement("SELECT PASSENGERNAME, DATE FROM BOOKING_LIST WHERE FLIGHTNUMBER = ?");
            gainAllRecordOfFlightListTable.setString(1, flight);
            ResultSet rs = gainAllRecordOfFlightListTable.executeQuery();
            while (rs.next()) 
            {
                ArrayList<Object> Data = new ArrayList<Object>();
                // add name and date
                Data.add(rs.getString(1));
                Data.add(rs.getDate(2));
                getNameAndDateRecordListTable.add(Data);
            }
            return getNameAndDateRecordListTable;
        }
        catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        }
        
        
        public static ArrayList<ArrayList<Object>> getWaitingRecordByFlight(String flight){
            try {
            ArrayList<ArrayList<Object>> getNameAndDateRecordListTable = new ArrayList<ArrayList<Object>>();
            connection = DBConnection.getConnection();
            gainAllRecordOfFlightListTable = connection.prepareStatement("SELECT PASSENGERNAME, DATE FROM WAITING_LIST WHERE FLIGHTNUMBER = ?");
            gainAllRecordOfFlightListTable.setString(1, flight);
            ResultSet rs = gainAllRecordOfFlightListTable.executeQuery();
            while (rs.next()) 
            {
                ArrayList<Object> Data = new ArrayList<Object>();
                // add name and date
                Data.add(rs.getString(1));
                Data.add(rs.getDate(2));
                getNameAndDateRecordListTable.add(Data);
            }
            return getNameAndDateRecordListTable;
        }
        catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        }
}
        
    
        
    

