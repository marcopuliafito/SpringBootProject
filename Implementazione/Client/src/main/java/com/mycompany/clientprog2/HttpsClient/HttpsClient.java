package com.mycompany.clientprog2.HttpsClient;

import com.mycompany.clientprog2.FileJson;
import java.awt.image.BufferedImage;
import org.json.JSONObject;

public abstract class HttpsClient {

    private String keyJson = FileJson.importJSON("settings.json");
    private JSONObject obj = new JSONObject(keyJson);
    protected String address;
    protected String port;
    protected  static String charset = "UTF-8";

    public HttpsClient() {
        this.address = this.obj.getString("address");
        this.port = this.obj.getString("port");
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public abstract boolean post_request();

    public abstract boolean get_request();

    public abstract String post_request_image();

    public abstract BufferedImage get_image();
}
