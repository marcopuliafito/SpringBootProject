/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverspring.imageFilter;

import java.awt.Graphics;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author march
 */
public class AddText extends BufferedImgBase{
    
    private String addText;

    public AddText(byte[] img, String addText) throws IOException{
        super(img);
        this.addText = addText;
    }

    @Override
    public byte[] applicaFiltro(){
        Graphics g = super.img.getGraphics();
        g.setFont(g.getFont().deriveFont(15f));
        g.drawString(addText, 20, 40);
        g.dispose();
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
