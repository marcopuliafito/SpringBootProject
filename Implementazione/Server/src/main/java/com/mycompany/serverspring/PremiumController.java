/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverspring;

/**
 *
 * @author march
 */
import java.sql.Connection;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class PremiumController extends JWTPremium{
    
    private ConnectDB conn;
            
    public PremiumController(){
        super();
    }
    
    //blocco per il classico login con rilascio del token utente
    @PostMapping("checkPremium")
    @ResponseBody
    public String checkPremium(@RequestParam("email") String email) throws ClassNotFoundException{
        //verifica nel DB se l'utente è registrato
        boolean responseDB = checkPremiumToDB(email);
        //se la risposta del db è positiva genera un token per l'utente e crea oggetto user
        if(responseDB){
            //passo l'email per la generazione del token personale
            super.setToken(email);
            //ottengo il token
            String token = AES256.encrypt(super.getToken()); //codifica tokenPremium prima di mandarlo al client
            return token;
        }
        //autenticazione non riuscita
        else
            return null;
    }
    
    //blocco per il classico login con rilascio del token utente
    @PostMapping("getTokenPremium")
    @ResponseBody
    public String getTokenPremium(@RequestParam("email") String email) throws ClassNotFoundException{
        //registra il nuovo utente nel db
        boolean responseDB = createUserPremium(email);
        //se la risposta del db è positiva genera un token per l'utente e crea oggetto user
        if(responseDB){
            //passo l'email per la generazione del token personale
            super.setToken(email);
            //ottengo il token
            String token = AES256.encrypt(super.getToken()); //codifica tokenPremium prima di mandarlo al client
            return token;
        }
        //autenticazione non riuscita
        else
            return null;
    }
    
    //metodo che restituisce true se l'utente è autenticato, false se qualcosa è andato storto
    public boolean checkPremiumToDB(String email) throws ClassNotFoundException{
        //creo Connection
        conn= new ConnectDB();
        Connection connection = conn.connectDB();
        Statement stmt;
        ResultSet rs;
        String verify_access = null;
        try {
            stmt = connection.createStatement();

            if (stmt.execute("SELECT email FROM utenti WHERE email = " +"'" + email + "'")) {
                rs = stmt.getResultSet();
                // iterate through the java resultset
                while (rs.next())
                {
                    verify_access = rs.getString("email");
                }
                System.out.println("verify_DB_UserPremium ---> " + verify_access);
                //se trovo un valore nella query significa che è stata trovato un utente con il permesso
                if(verify_access != null)
                    return true;
                else
                    return false;         
            }
            else
                return false;
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
        
    }

     //metodo che restituisce true se l'utente è autenticato, false se qualcosa è andato storto
    public boolean createUserPremium(String email) throws ClassNotFoundException{
        //creo Connection
        conn= new ConnectDB();
        Connection connection = conn.connectDB();
        Statement stmt;
        ResultSet rs;
        String verify_access = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate("INSERT into utenti(email) values("+"'" + email + "'" +")");
            try{
                if(stmt!=null){
                  connection.close();
                  return true;
                }
                else
                    return false;
            }catch(SQLException se){
                System.out.println(se);
                return false;
            } 
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
        
    }
}