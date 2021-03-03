package com.mycompany.clientprog2.HttpsClient;

import com.mycompany.clientprog2.FileJson;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;

public class JwtPremiumRequest extends HttpsClient {

    private String postmapping = "getTokenPremium";
    private String email;

    public JwtPremiumRequest(String email) {
        super();
        this.email = email;
    }

    @Override
    public boolean post_request() {
        try {
            String query = String.format("email=%s", URLEncoder.encode(this.email, JwtPremiumRequest.charset));
            URL url = new URL("https://" + super.address + ":" + super.port + "/" + postmapping);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + JwtPremiumRequest.charset);
            connection.setRequestProperty("Accept-Charset", JwtPremiumRequest.charset);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            try (OutputStream output = connection.getOutputStream()) {
                output.write(query.getBytes(JwtPremiumRequest.charset));
                output.flush();
            }
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream()), "UTF-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                connection.disconnect();
            }
            System.out.println("***********");
            if (!"".equals(response.toString())) {
                String[] key = { "token_premium" };
                String[] value = { response.toString() };
                FileJson.writeFileJson("premium_token.json", key, value);
                return true;
            } else
                return false;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean get_request() {
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
