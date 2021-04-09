package com.mycompany.clientprog2;

import javax.swing.JOptionPane;
import org.json.JSONObject;

public final class UserAccount_Premium extends UserAccount {
    private static String filejson = FileJson.importJSON("premium_token.json");
    private static JSONObject key = new JSONObject(filejson);
    
    public UserAccount_Premium(){
        setToken();
    }
    
    @Override
    public void setToken() {
        this.token = this.key.getString("token_premium");
        if(this.token != null)
            JOptionPane.showMessageDialog(null, "Benvenuto! Usa il tuo gettone Premium per sfruttare nuovissimi filtri!!", "Benvenuto", JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, "Benvenuto! Attenzione qualcosa è andato storto nella creazione del tuo gettone Premium!!", "Oh no!", JOptionPane.ERROR_MESSAGE);
    }
}
