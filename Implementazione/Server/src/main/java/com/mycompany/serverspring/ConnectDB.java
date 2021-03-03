package com.mycompany.serverspring;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author march
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author march
 */
public class ConnectDB {
    
    private Connection conn = null;
    private final String USERNAME = "root";
    private final String PASSWORD = "password";
    private final String CONN_STRING = "jdbc:mysql://localhost:3306/utenti_java";
    
    public ConnectDB(){
    }
    
    public Connection connectDB() throws ClassNotFoundException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
            return conn;
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
   }
}

