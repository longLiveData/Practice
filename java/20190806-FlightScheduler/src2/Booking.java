/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author yangyu
 */
public class Booking {
       private static Connection connection;
    private static PreparedStatement bookWaitingListPassenger;
    private static PreparedStatement bookFlightDayPassenger;

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
}
