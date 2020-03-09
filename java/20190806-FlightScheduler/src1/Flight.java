/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author yangyu
 */
public class Flight {
       private static Connection connection;
    private static PreparedStatement getFlight;
    private static PreparedStatement getFlightDate;
    private static PreparedStatement addFlight;
    private static PreparedStatement dropFlight;
    private static ArrayList<String> FlightDate;
    private static ArrayList<String> Flight;
    private static ResultSet rs;
    
    public static ArrayList<String> getFlight()
    {
        try 
        {
            connection = DBConnection.getConnection();
            Flight = new ArrayList<String>();
            getFlight = connection.prepareStatement("SELECT FLIGHT FROM FLIGHT_NUMBER");
            rs = getFlight.executeQuery();
            while (rs.next()) {
                Flight.add(rs.getString(1));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return Flight;
    }
    public static ArrayList<String> getFlightDate()
    {
        try 
        {
            connection = DBConnection.getConnection();
            FlightDate = new ArrayList<String>();
            getFlightDate = connection.prepareStatement("SELECT DATE FROM DATE");
            rs = getFlightDate.executeQuery();
            while (rs.next()) {
                FlightDate.add(rs.getString(1));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return FlightDate;
    }
    public static void addFlight (String Flight, int seats){
        try {
            connection = DBConnection.getConnection();
            addFlight = connection.prepareStatement("INSERT INTO FLIGHT_NUMBER(FLIGHT,SEATS) VALUES(?,?)");
            addFlight.setString(1, Flight);
            addFlight.setInt(2,seats);
            addFlight.executeUpdate();
        } 
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static boolean dropFlight (String Flight){
        try {
            connection = DBConnection.getConnection();
            dropFlight = connection.prepareStatement("DELETE FROM FLIGHT_NUMBER WHERE FLIGHT = ?");
            dropFlight.setString(1, Flight);
            if(dropFlight.executeUpdate() == 1){
                return true;
            }
        } 
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }
}
