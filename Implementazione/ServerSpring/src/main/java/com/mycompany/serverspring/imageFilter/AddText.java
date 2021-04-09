/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverspring.imageFilter;

import java.awt.Font;
import java.awt.Graphics;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author march
 */
public class AddText extends BufferedImgBase{
    
    private String addText;
    private int x;
    private int y;

    public AddText(byte[] img, String addText, String x, String y) throws IOException{
        super(img);
        this.addText = addText;
        this.x = parseInt(x);
        this.y = parseInt(y);
    }

    @Override
    public byte[] applicaFiltro(){
        Graphics g = super.img.getGraphics();
        g.setFont(g.getFont().deriveFont(Font.BOLD, 18));
        g.drawString(addText, this.x, this.y);
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
