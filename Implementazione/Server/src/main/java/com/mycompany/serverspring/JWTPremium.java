/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverspring;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 *
 * @author march
 */
public class JWTPremium {
    private String token;
     
    protected void setToken(String email){
        //chiamo metodo per reperire chiave segreta da file json
        String secretKey = secret_key_jwt();
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
            .commaSeparatedStringToAuthorityList("ROLE_USER");
        this.token = Jwts
            .builder()
            .setSubject(email)
            .claim("authorities",
            grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 60000000))
            .signWith(SignatureAlgorithm.HS512,
            secretKey.getBytes()).compact();
    }
                      
    protected String getToken() {
        return "Bearer " + this.token;
    }
    
    protected static String secret_key_jwt(){
        String param;
        String secret_key = null;
        try {
            //chiamo il metodo statico della classe ImportTxt che passando il path come argomento mi estrae le informazioni json
            param = ImportTxt.importJSON("settings.json");
            JSONObject obj = new JSONObject(param);
            secret_key = obj.getString("secretKey");
        }
        catch (JSONException ex) {
            Logger.getLogger(JWTPremium.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return secret_key;
    }
  
}
