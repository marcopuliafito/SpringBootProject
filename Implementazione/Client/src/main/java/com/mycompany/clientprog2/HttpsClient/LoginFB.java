package com.mycompany.clientprog2.HttpsClient;

import com.mycompany.clientprog2.FileJson;
import com.mycompany.clientprog2.UserAccount;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

public class LoginFB extends HttpsClient {

    private String postmapping = "getTokenFB";

    public LoginFB() {
        super();
    }

    @Override
    public boolean get_request() {
        try {
            String uri = "https://" + super.address + ":" + super.port + "/" + this.postmapping;
            URL url = new URL(uri);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
                if (!"".equals(response.toString())) {
                    JSONObject obj = new JSONObject(response.toString());
                    String tokenFB = obj.getString("tokenFB");
                    String email = obj.getString("email");
                    String[] key = { "tokenFB", "email" };
                    String[] value = { tokenFB, email };
                    FileJson.writeFileJson("data_access_user.json", key, value);
                    return true;
                } else
                    return false;
            } else
                return false;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean post_request() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BufferedImage get_image() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String post_request_image() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
