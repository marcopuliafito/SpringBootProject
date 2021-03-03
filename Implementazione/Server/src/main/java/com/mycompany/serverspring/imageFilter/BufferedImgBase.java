/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverspring.imageFilter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author march
 */
public abstract class BufferedImgBase {
    
    protected BufferedImage img;
    
    //converti i byte di img in un buffered img ogni qualvolta viene istanziata una sottoclasse di BufferedImgBas
    public BufferedImgBase(byte[] byteimg) throws IOException{
        // convert byte[] back to a BufferedImage
        InputStream is = new ByteArrayInputStream(byteimg);
        BufferedImage newBi = ImageIO.read(is);
        //this.img --> BufferedImage
        this.img = newBi;
    }
    
    public BufferedImage getImg(){
        return this.img;
    }
    public void setImg(BufferedImage img){
        this.img = img;
    }
    
    //metodo che converte array di byte in bufferedimage
    public void saveImg(byte[] byteImg, String fileName) throws IOException{
        File outputFile = new File("src/main/resources/tmpImg/" + fileName);
        // convert byte[] back to a BufferedImage
        BufferedImage newBi = ImageIO.read(new ByteArrayInputStream(byteImg));
        // save it
        ImageIO.write(newBi, "jpeg",outputFile);
    }
    
    public abstract byte[] applicaFiltro();
}
