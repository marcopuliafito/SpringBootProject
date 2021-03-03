/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverspring.imageFilter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author march
 */
public class BlackandWhite extends BufferedImgBase{

    public BlackandWhite(byte[] img) throws IOException{
        super(img);
    }

    @Override
    public byte[] applicaFiltro(){
        // Crea il buffered img in bianco e nero
        BufferedImage im = new BufferedImage(super.img.getWidth(), super.img.getHeight(), BufferedImage.TYPE_BYTE_BINARY); 
        Graphics2D graphics = im.createGraphics();
        graphics.drawImage(super.img, 0, 0, null);
        // convert BufferedImage to byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(im, "jpeg", baos);
        } catch (IOException ex) {
            Logger.getLogger(BlackandWhite.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] bytes = baos.toByteArray();
        return bytes;   
    }
    
}
