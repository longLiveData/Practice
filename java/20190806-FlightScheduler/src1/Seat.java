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
    private static PreparedStatement addDate;
    private static ResultSet rs;
    
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
}}
