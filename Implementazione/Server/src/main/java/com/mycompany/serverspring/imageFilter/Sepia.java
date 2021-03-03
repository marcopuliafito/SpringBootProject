/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverspring.imageFilter;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author march
 */
public class Sepia extends BufferedImgBase{

    public Sepia(byte[] img) throws IOException{
        super(img);
    }

    @Override
    public byte[] applicaFiltro(){
        // Crea il buffered img in bianco e nero
        BufferedImage sepia = new BufferedImage(super.img.getWidth(), super.img.getHeight(), BufferedImage.TYPE_INT_RGB);  

        int sepiaDepth = 20;

        int w = super.img.getWidth();
        int h = super.img.getHeight();

        WritableRaster raster = sepia.getRaster();

        int[] pixels = new int[w * h * 3];
        img.getRaster().getPixels(0, 0, w, h, pixels);

        for (int i = 0; i < pixels.length; i += 3) {
            int r = pixels[i];
            int g = pixels[i + 1];
            int b = pixels[i + 2];

            int gry = (r + g + b) / 3;
            r = g = b = gry;
            r = r + (sepiaDepth * 2);
            g = g + sepiaDepth;

            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }

            b -= 1;

            if (b < 0) {
                b = 0;
            }
            if (b > 255) {
                b = 255;
            }

            pixels[i] = r;
            pixels[i + 1] = g;
            pixels[i + 2] = b;
        }
        raster.setPixels(0, 0, w, h, pixels);
        // convert BufferedImage to byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(sepia, "jpeg", baos);
        } catch (IOException ex) {
            Logger.getLogger(BlackandWhite.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] bytes = baos.toByteArray();
        return bytes;   
    }
    
}
