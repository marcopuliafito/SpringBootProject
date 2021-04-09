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
public class Red extends BufferedImgBase{

    public Red(byte[] img) throws IOException{
        super(img);
    }

    @Override
    public byte[] applicaFiltro(){
        int w = super.img.getWidth();
        int h = super.img.getHeight();

        // convert to red image 
        for (int y = 0; y < h; y++) 
        { 
            for (int x = 0; x < w; x++) 
            { 
                int p = super.img.getRGB(x,y); 
  
                int a = (p>>24)&0xff; 
                int r = (p>>16)&0xff; 
 
                p = (a<<24) | (r<<16) | (0<<8) | 0; 
  
                super.img.setRGB(x, y, p); 
            } 
        } 
        // convert BufferedImage to byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(super.img, "jpeg", baos);
        } catch (IOException ex) {
            Logger.getLogger(BlackandWhite.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] bytes = baos.toByteArray();
        return bytes;   
    }
    
}
