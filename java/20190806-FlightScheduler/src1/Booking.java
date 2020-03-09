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
import java.util.Calendar;
import java.util.Vector;

/**
 *
 * @author yangyu
 */
public class Booking {
    private static Connection connection;
    private static PreparedStatement bookWaitingListPassenger;
    private static PreparedStatement cancelWaitingListPassenger;
    private static PreparedStatement getPassengerWaitingListPassenger;
    private static PreparedStatement bookFlightDayPassenger;
    private static ResultSet rs;

    public static void bookWaitingListCustomer(String Passenger, String flightNumber, Date date) {
        try {
            connection = DBConnection.getConnection();
            bookWaitingListPassenger = connection.prepareStatement("INSERT INTO WAITING_LIST(PASSENGERNAME, FLIGHTNUMBER, DATE, STATUS, TIMESTAMP) VALUES(?, ?, ?, 'WAITLISTED', ?)");
            bookWaitingListPassenger.setString(1, Passenger);
            bookWaitingListPassenger.setString(2, flightNumber);
            bookWaitingListPassenger.setDate(3, date);
            bookWaitingListPassenger.setTimestamp(4, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            bookWaitingListPassenger.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static String getFlightNumber(String cN, Date date){
        try {
            connection = DBConnection.getConnection();
            bookWaitingListPassenger = connection.prepareStatement("SELECT FLIGHTNUMBER FROM BOOKING_LIST WHERE PASSENGERNAME = ? AND DATE = ?");
            bookWaitingListPassenger.setString(1, cN);
            bookWaitingListPassenger.setDate(2, date);
            rs = bookWaitingListPassenger.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return "";
    }
    
    public static String getPassengerWaitingListCustomer(String flightNumber, Date date) {
        try {
            connection = DBConnection.getConnection();
            getPassengerWaitingListPassenger = connection.prepareStatement("SELECT * FROM WAITING_LIST WHERE FLIGHTNUMBER = ? AND DATE = ?");
            getPassengerWaitingListPassenger.setString(1, flightNumber);
            getPassengerWaitingListPassenger.setDate(2, date);
            rs = getPassengerWaitingListPassenger.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return "null";
    }
    
    // cancel waiting list by cn+fligt+date
    public static boolean cancelWaitingListCustomer(String Passenger, String flightNumber, Date date) {
        try {
            connection = DBConnection.getConnection();
            cancelWaitingListPassenger = connection.prepareStatement("DELETE FROM WAITING_LIST WHERE PASSENGERNAME = ? AND FLIGHTNUMBER = ? AND DATE = ?");
            cancelWaitingListPassenger.setString(1, Passenger);
            cancelWaitingListPassenger.setString(2, flightNumber);
            cancelWaitingListPassenger.setDate(3, date);
            if (cancelWaitingListPassenger.executeUpdate() == 1){
                return true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }
    
    // cancel waiting list by flight
    public static boolean cancelWaitingListFlight(String flightNumber) {
        try {
            connection = DBConnection.getConnection();
            cancelWaitingListPassenger = connection.prepareStatement("SELECT PASSENGERNAME, DATE FROM WAITING_LIST WHERE FLIGHTNUMBER = ?");
            cancelWaitingListPassenger.setString(1, flightNumber);
            cancelWaitingListPassenger = connection.prepareStatement("DELETE FROM WAITING_LIST WHERE FLIGHTNUMBER = ?");
            cancelWaitingListPassenger.setString(1, flightNumber);
            if (cancelWaitingListPassenger.executeUpdate() == 1){
                return true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }
    
    
    public static void bookFlightDayPassenger(String Passenger, String flightNumber, Date date) {
        try {
            connection = DBConnection.getConnection();
            bookFlightDayPassenger= connection.prepareStatement("INSERT INTO BOOKING_LIST(PASSENGERNAME, FLIGHTNUMBER, DATE, STATUS, TIMESTAMP) VALUES(?, ?, ?, 'BOOKED', ?)");
            bookFlightDayPassenger.setString(1, Passenger);
            bookFlightDayPassenger.setString(2, flightNumber);
            bookFlightDayPassenger.setDate(3, date);
            bookFlightDayPassenger.setTimestamp(4, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            bookFlightDayPassenger.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static boolean cancelFlightDayPassenger(String Passenger, Date date) {
        try {
            connection = DBConnection.getConnection();
            bookFlightDayPassenger= connection.prepareStatement("DELETE FROM BOOKING_LIST WHERE PASSENGERNAME = ? AND DATE = ?");
            bookFlightDayPassenger.setString(1, Passenger);
            bookFlightDayPassenger.setDate(2, date);
            if(bookFlightDayPassenger.executeUpdate() == 1){
                return true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }
    
    public static void clear(){
        try {
            connection = DBConnection.getConnection();
            bookFlightDayPassenger= connection.prepareStatement("DELETE FROM BOOKING_LIST");
            bookFlightDayPassenger.executeUpdate();
            bookFlightDayPassenger= connection.prepareStatement("DELETE FROM WAITING_LIST");
            bookFlightDayPassenger.executeUpdate();
//            bookFlightDayPassenger= connection.prepareStatement("DELETE FROM CUSTOMER");
//            bookFlightDayPassenger.executeUpdate();
//            bookFlightDayPassenger= connection.prepareStatement("DELETE FROM FLIGHT_NUMBER");
//            bookFlightDayPassenger.executeUpdate();
//            bookFlightDayPassenger= connection.prepareStatement("DELETE FROM DATE");
//            bookFlightDayPassenger.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}