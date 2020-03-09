/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yangyu
 */
public class Seat {
    private static Connection connection;
    private static PreparedStatement getSeatAvailable;
    private static PreparedStatement getSeatOccupied;
    private static PreparedStatement removeCustomerFromWaitingList;
    private static PreparedStatement removeCustomerFromBookingList;
    private static PreparedStatement getFlightNumberFromRemovedCustomer;
    private static PreparedStatement addDate;
    private static ResultSet rs;
    private static String gainFlightNumberFromRemovedCustomer;
    private static PreparedStatement getCustomerFromWaitingList;
    private static String gainCustomerFromWaitingList;
 private static PreparedStatement removeCustomerFromWaitingList1;

public static boolean isSeatAvailable(String flight, Date date) 
    {
        int seatRemain;
        seatRemain = getSeatAvailable(flight, date) - getSeatOccupied(flight, date);
        if (seatRemain != 0)
        {
        return true;
        }
        else
        {
        return false;
        }
    }

    public static int getSeatAvailable(String flight, Date date) {
        try {
            connection = DBConnection.getConnection();
            getSeatAvailable = connection.prepareStatement("SELECT SEATS FROM FLIGHT_NUMBER WHERE FLIGHT = ?");
            getSeatAvailable.setString(1, flight);
            rs = getSeatAvailable.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public static int getSeatOccupied(String flightNumber, Date date) {
        try {
            int rowCount;
            connection = DBConnection.getConnection();
            getSeatOccupied = connection.prepareStatement("SELECT COUNT(*) totalCount FROM BOOKING_LIST WHERE FLIGHTNUMBER = ? AND DATE = ?");
            getSeatOccupied.setString(1, flightNumber);
            getSeatOccupied.setDate(2, date);
            rs = getSeatOccupied.executeQuery();
            rowCount = 0;
            if (rs.next()) {
                rowCount = rs.getInt("totalCount");
            }
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
         public static void addDate (Date date){
        try {
            connection = DBConnection.getConnection();
            addDate = connection.prepareStatement("INSERT INTO DATE(DATE) VALUES(?)");
            addDate.setDate(1,date);
            addDate.executeUpdate();
        } 
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
         }
        public static void removeCustomerFromWaitingList (String Passenger, Date date) {
        try {
            connection = DBConnection.getConnection();
            removeCustomerFromWaitingList= connection.prepareStatement("DELETE FROM WAITING_LIST WHERE PASSENGERNAME = ? AND DATE = ?");
            removeCustomerFromWaitingList.setString(1, Passenger);
            removeCustomerFromWaitingList.setDate(2, date);
            removeCustomerFromWaitingList.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        }
        public static void removeCustomerFromBookingList (String Passenger, Date date) {
        try {
            connection = DBConnection.getConnection();
            removeCustomerFromBookingList= connection.prepareStatement("DELETE FROM BOOKING_LIST WHERE PASSENGERNAME = ?  AND DATE = ?");
            removeCustomerFromBookingList.setString(1, Passenger);
            removeCustomerFromBookingList.setDate(2, date);
            removeCustomerFromBookingList.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        }
       public static String getFlightNumberFromRemovedCustomer (String Passenger, Date date) {
        try {
            connection = DBConnection.getConnection();
            getFlightNumberFromRemovedCustomer= connection.prepareStatement("SELECT FLIGHTNUMBER FROM BOOKING_LIST WHERE PASSENGERNAME = ?  AND DATE = ?");
            getFlightNumberFromRemovedCustomer.setString(1, Passenger);
            getFlightNumberFromRemovedCustomer.setDate(2, date);
            rs = getFlightNumberFromRemovedCustomer.executeQuery();
            while (rs.next())
            {
                gainFlightNumberFromRemovedCustomer = rs.getString(1);
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return gainFlightNumberFromRemovedCustomer; 
       } 
       
         public static String getCustomerFromWaitingList (String flight, Date date) {
        try {
            connection = DBConnection.getConnection();
            getCustomerFromWaitingList= connection.prepareStatement("SELECT PASSENGERNAME FROM WAITING_LIST WHERE FLIGHTNUMBER = ?  AND DATE = ?");
            getCustomerFromWaitingList.setString(1, flight);
            getCustomerFromWaitingList.setDate(2, date);
            rs = getCustomerFromWaitingList.executeQuery();
            while (rs.next())
            {
                gainCustomerFromWaitingList = rs.getString(1);
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return gainCustomerFromWaitingList; 
       } 
         public static void removeCustomerFromWaitingList1 (String Passenger,String Flight, Date date) {
        try {
            connection = DBConnection.getConnection();
            removeCustomerFromWaitingList1= connection.prepareStatement("DELETE FROM WAITING_LIST WHERE PASSENGERNAME = ? AND FLIGHTNUMBER = ? AND DATE = ?");
            removeCustomerFromWaitingList1.setString(1, Passenger);
            removeCustomerFromWaitingList1.setString(2, Flight);
            removeCustomerFromWaitingList1.setDate(3, date);
            removeCustomerFromWaitingList1.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }}}
         
       
       

