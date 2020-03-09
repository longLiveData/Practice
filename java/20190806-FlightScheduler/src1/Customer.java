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
public class Customer {
    private static Connection connection;
    private static PreparedStatement addPassenger;
    private static PreparedStatement getPassenger;
    private static ArrayList<String> Passenger;
    private static ResultSet rs;
    
    public static ArrayList<String> getPassenger()
    {
        try 
        {
            connection = DBConnection.getConnection();
            Passenger = new ArrayList<String>();
            getPassenger = connection.prepareStatement("SELECT CUSTOMER FROM CUSTOMER");
            rs = getPassenger.executeQuery();
            while (rs.next()) {
                Passenger.add(rs.getString(1));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return Passenger;
    }
    
    public static void addPassenger (String name){
        try {
            connection = DBConnection.getConnection();
            addPassenger = connection.prepareStatement("INSERT INTO CUSTOMER(CUSTOMER) VALUES(?)");
            addPassenger.setString(1, name);
            addPassenger.executeUpdate();
        } 
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
