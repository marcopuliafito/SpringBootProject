/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientprog2;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author march
 */
public class SpiderImage{
    private String searchImage;
    private Element media;
    private String sourceUrl;

    public SpiderImage(String searchImage) throws IOException{
        this.searchImage = searchImage;
    }

    public byte[] chiamaSpider(){
        try {
            String searchImageReplace = this.searchImage.replace(" ", "+");
            System.out.println("searchImage****" + this.searchImage);
            String googleUrl = "https://www.bing.com/images/search?q="+searchImageReplace+".png&scope=images&form=QBLH&sp=-1&sc=0-0&qs=n&cvid=91B08E76292B4A09BF0F25C4F0CEF069&first=1&tsc=ImageBasicHover";
            Document doc1 = Jsoup.connect(googleUrl).get();
            media = doc1.getElementsByClass​("mimg").get(0);
            sourceUrl = media.attr("abs:src");
            //altro tentativo di ricerca cambio value classe html
            if(sourceUrl == null){
                media = doc1.getElementsByClass​("mimg rms_img").get(0);
                sourceUrl = media.attr("abs:src");
            }
            URL url = new URL(sourceUrl);
            BufferedImage img = ImageIO.read(url);
            // convert BufferedImage to byte[]
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ImageIO.write(img, "jpeg", baos);
            } catch (IOException ex) {
                Logger.getLogger(SpiderImage.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException ex) {
            Logger.getLogger(SpiderImage.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
