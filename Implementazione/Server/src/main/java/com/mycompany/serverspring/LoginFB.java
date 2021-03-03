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
import com.mycompany.serverspring.ImportTxt;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author march
 */
@RestController  
public class LoginFB{
    
    private static String client_id;
    private static String chiave_segreta;
    private static String redirect_uri;
    private String tokenFB = "";
    private String email;
    
    //nel costruttore inizializzo le proprietà statiche prelevate dal file di configurazione json
    public LoginFB(){
        //chiamo il metodo statico della classe ImportTxt che passando il path come argomento mi estrae le informazioni json
        String param = ImportTxt.importJSON("settings_fb.json");
        JSONObject obj = new JSONObject(param);
        LoginFB.client_id = obj.getString("client_id");
        LoginFB.chiave_segreta = obj.getString("chiave_segreta");
        LoginFB.redirect_uri = obj.getString("redirect_uri");
        //pulisco eventuali file tokenFB sul server 
        File file = new File("tokenFB.json"); 
        file.delete();
    }
    
    //indirizzo redirect uri per accesso tramite login FB (ottengo parametro code dalla richiesta GET e salvo il code in un file json sul server)
    @GetMapping("loginFB")
    @ResponseBody
    public String loginFB(@RequestParam("code") String code) throws IOException{
        try {
            String[] key = {"code"};
            String[] value ={code};
            //ottenuto code rispondo sul browser e salvo il codice sul server 
            if(code != null){
                ImportTxt.writeFileJson("codeFB.json", key, value);
                return "OK! Torna all'applicazione e clicca il pulsante \"Conferma accesso\"";
            }
            else{
                ImportTxt.writeFileJson("codeFB.json", key, null);
                return "Ops qualcosa è andato storto, riprova!";
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginFB.class.getName()).log(Level.SEVERE, null, ex);
            return "Ops qualcosa è andato storto, riprova!";
        }
    }
    
    //indirizzo redirect uri per accesso tramite login FB (ottengo parametro code dalla richiesta GET)
    @GetMapping("getTokenFB")
    @ResponseBody
    public String listenClientFB() throws IOException{
       //ottieni code utente per ottenere il token d'accesso e restituirlo al client
        String param = ImportTxt.importJSON("codeFB.json");
        JSONObject obj = new JSONObject(param);
        String code = obj.getString("code");
        this.tokenFB = getAccessToken(code);
        if(this.tokenFB != null){
            String graph = getFBGraph();
            if(graph != null){
                this.email = getGraphData(graph);
                //ok email prelevata posso crearmi un jwt con le autorizzazioni per il client
                if(this.email != null){
                    //encrypt tokenFB prima di inviarlo al client con la tecnica di cifratura AES256
                    String encryptTokenFB = AES256.encrypt(this.tokenFB); //codifica tokenfb prima di mandarlo al client
                    System.out.println("{tokenFB:" + encryptTokenFB +", email:" + this.email + "}");
                    return "{tokenFB: " + '"' + encryptTokenFB + '"' +", email:" + this.email + "}";
                }
                else{
                    return null; 
                }
            }
            else
                return "Ops qualcosa è andato storto nella comunicazione con Facebook API! Riprova."; 
        } 
        else
            return null;
    }
    
    
    //compongo l'url per la richiesta get del token
    public String getUrlToken(String code) {
        String getUrlToken = "";
        try {
            getUrlToken = "https://graph.facebook.com/oauth/access_token?"
                + "client_id=" + LoginFB.client_id + "&redirect_uri="
                + URLEncoder.encode(LoginFB.redirect_uri , "UTF-8")
                + "&client_secret=" +  LoginFB.chiave_segreta + "&code=" + code;
        } catch (UnsupportedEncodingException e) {
        }
        return getUrlToken;
    }
    //ottengo il token d'accesso facendo una richiesta get dove al suo interno ho il code precedentemente ricevuto
    public String getAccessToken(String code) throws IOException {
        URL fbGraphURL;
        try {
            //uri definita in getUrlToken per ottenere l'access token
            fbGraphURL = new URL(getUrlToken(code));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid code received " + e);
        }
        URLConnection fbConnection;
        StringBuffer response = null;
        try {
            fbConnection = fbGraphURL.openConnection();
            BufferedReader in;
            in = new BufferedReader(new InputStreamReader(
                fbConnection.getInputStream()));
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine).append("\n");
            in.close();
        } catch (IOException e) {
            throw new RuntimeException("Unable to connect with Facebook "
                + e);
        }
        JSONObject obj = new JSONObject(response.toString());
        this.tokenFB = obj.getString("access_token");
        //mi salvo in un file json il token d'accesso fb 
        /*if (this.tokenFB .startsWith("{")) {
            throw new RuntimeException("ERROR: Access Token Invalid: "
                + this.tokenFB );
        }*/
        return this.tokenFB ;
    }
    
    //metodo per reperimento dei dati utente tramite access token FB
    public String getFBGraph() {
        String graph = null;
        try {
            String g = "https://graph.facebook.com/me?fields=email&access_token=" + this.tokenFB;
            URL u = new URL(g);
            URLConnection c = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                            c.getInputStream()));
            String inputLine;
            StringBuffer b = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                    b.append(inputLine + "\n");
            in.close();
            graph = b.toString();
            System.out.println(graph);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR in getting FB graph data. " + e);
        }
        return graph;
    }

    public String getGraphData(String fbGraph) {
        boolean verify = false;
        try {
            String email = null;
            JSONObject json = new JSONObject(fbGraph);
            //System.out.println(json);
            if (json.has("email")){
                email = json.getString("email");
                return email;
            }
            else
                return null;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR in parsing FB graph data. " + e);
        }
    }
    
}
