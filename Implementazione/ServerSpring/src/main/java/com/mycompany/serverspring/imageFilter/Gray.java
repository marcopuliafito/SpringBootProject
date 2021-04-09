/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverspring.imageFilter;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author march
 */
public class Gray extends BufferedImgBase{

    public Gray(byte[] img) throws IOException{
        super(img);
    }

    @Override
    public byte[] applicaFiltro(){
        // Crea il buffered img gray
        BufferedImage im = new BufferedImage(super.img.getWidth(), super.img.getHeight(), BufferedImage.TYPE_INT_ARGB); 
        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        op.filter(super.img, im);
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
