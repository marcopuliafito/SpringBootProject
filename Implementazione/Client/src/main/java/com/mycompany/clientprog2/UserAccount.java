package com.mycompany.clientprog2;

import org.json.JSONObject;

public abstract class UserAccount {

    private String email;
    protected String token;
    protected static String param = FileJson.importJSON("data_access_user.json");
    protected static JSONObject obj = new JSONObject(param);

    public UserAccount() {
        String emailjson = obj.getString("email");
        this.email = emailjson;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail() {
        String emailjson = obj.getString("email");
        this.email = emailjson;
    }

    public String getToken() {
        return this.token;
    }
    //implementato in UserAccount_base e UserAccount_Premium
    public abstract void setToken();
}
