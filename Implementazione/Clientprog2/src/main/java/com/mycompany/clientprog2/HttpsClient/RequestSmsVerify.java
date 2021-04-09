package com.mycompany.clientprog2.HttpsClient;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;

public class RequestSmsVerify extends HttpsClient {

    private String postmapping;
    private String number;
    private String tokenNumber;

    public RequestSmsVerify(String number) {
        super();
        this.number = number;
    }

    public RequestSmsVerify(String number, String tokenNumber) {
        super();
        this.number = number;
        this.tokenNumber = tokenNumber;
    }

    @Override
    public boolean post_request() {
        try {
            String query;
            if (this.tokenNumber == null) {
                this.postmapping = "smsVerify";
                query = String.format("number=%s", URLEncoder.encode(this.number, RequestSmsVerify.charset));
            } else {
                this.postmapping = "checkNumberToken";
                query = String.format("tokenNumber=%s&number=%s", URLEncoder.encode(this.tokenNumber, RequestSmsVerify.charset), URLEncoder.encode(this.number, this.charset));
            }
            URL url = new URL("https://" + super.address + ":" + super.port + "/" + postmapping);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + RequestSmsVerify.charset);
            connection.setRequestProperty("Accept-Charset", RequestSmsVerify.charset);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            try (OutputStream output = connection.getOutputStream()) {
                output.write(query.getBytes(RequestSmsVerify.charset));
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
            if (Boolean.valueOf(response.toString())) {
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
