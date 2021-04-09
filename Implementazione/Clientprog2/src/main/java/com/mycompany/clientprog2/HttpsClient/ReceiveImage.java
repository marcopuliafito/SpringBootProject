package com.mycompany.clientprog2.HttpsClient;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;

public class ReceiveImage extends HttpsClient {

    private String mapping = "getImage";
    private String tmpFileName;

    public ReceiveImage(String tmpFileName) {
        super();
        this.tmpFileName = tmpFileName;
    }

    @Override
    public BufferedImage get_image() {
        try {
            String query = String.format("tmpFileName=%s", URLEncoder.encode(this.tmpFileName, ReceiveImage.charset));
            URL url = new URL("https://" + super.address + ":" + super.port + "/" + mapping);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + ReceiveImage.charset);
            connection.setRequestProperty("Accept-Charset", ReceiveImage.charset);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            try (OutputStream output = connection.getOutputStream()) {
                output.write(query.getBytes(ReceiveImage.charset));
                output.flush();
            }
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
                ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                int c;
                while ((c = in.read()) != -1) {
                    byteArrayOut.write(c);
                }
                ByteArrayInputStream byteImage = new ByteArrayInputStream(byteArrayOut.toByteArray());
                BufferedImage newBi = ImageIO.read(byteImage);
                in.close();
                return newBi;
            } else {
                return null;
            }
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean post_request() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean get_request() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String post_request_image() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
