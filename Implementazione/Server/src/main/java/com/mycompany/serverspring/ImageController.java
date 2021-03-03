/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverspring;

import com.mycompany.serverspring.imageFilter.BufferedImgBase;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import javax.imageio.ImageIO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author march
 */
@RestController  
public class ImageController {
    //img di tipo BufferedImgBase
    private BufferedImgBase img;
    private byte[] newByte;
    private String fileName;
    private Timestamp timestamp;
    private Constructor classeFiltro;
    
    //POST request 
    @PostMapping("/filterImage")
    @ResponseBody
    public String postRequest(@RequestBody byte[] imgbyte, @RequestHeader(value = "Filter") String filter) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        //ottengo dinamicamente riferimento della classe che ci interessa in base a filter
        classeFiltro = Class.forName("com.mycompany.serverspring.imageFilter." + filter).getConstructor(byte[].class);
       
        this.img = (BufferedImgBase) classeFiltro.newInstance(imgbyte);

        //call applicaFiltro()
        this.newByte = this.img.applicaFiltro();

        if(this.newByte != null){
            return createTempFile();
        }
        else
            return null;    
    }
    
     //POST request (accesso risorsa protetta)
    @PostMapping("/filterImagePremium")
    @ResponseBody
    public String postRequestPremium(@RequestBody byte[] imgbyte, @RequestHeader(value = "Filter") String filter,  @RequestHeader(value = "addText", required=false) String addText) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if(addText == null){ //se non esiste l'altro parametro oltre il filtro (fltro AddText)
            //ottengo dinamicamente riferimento della classe che ci interessa in base a filter
            classeFiltro = Class.forName("com.mycompany.serverspring.imageFilter." + filter).getConstructor(byte[].class);
            this.img = (BufferedImgBase) classeFiltro.newInstance(imgbyte);
        }
        else {
            classeFiltro = Class.forName("com.mycompany.serverspring.imageFilter." + filter).getConstructor(byte[].class, String.class);
            this.img = (BufferedImgBase) classeFiltro.newInstance(imgbyte, addText);
        }      
        //call applicaFiltro()
        this.newByte = this.img.applicaFiltro();

        if(this.newByte != null){
            return createTempFile();
        }
        else
            return null;      
    }
    
    private String createTempFile() throws IOException{
        //Salvo l'img con il valore di timestamp attuale
        timestamp = new Timestamp(System.currentTimeMillis());
        this.fileName = timestamp.getTime()+ ".jpeg";
        this.img.saveImg(this.newByte, this.fileName);
        return fileName; //ritorno il nome del file salvato (timestamp)
    }
    
    //GET request 
    @PostMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> image(@RequestParam("tmpFileName") String tmpFileName) throws IOException {
        System.out.println("Server img tmp name ***************" + tmpFileName);
        File tmp = new File("src/main/resources/tmpImg/" + tmpFileName);
        // convert BufferedImage to byte[]
        BufferedImage bufferedImg = ImageIO.read(tmp);
        tmp.delete(); //elimino il file tmp dell'immagine dopo che l'ho convertite in BufferedImage da restituire
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImg, "jpeg", baos);
        byte[] imgbytes = baos.toByteArray();
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.IMAGE_JPEG)
            .body(imgbytes);
    }
    
}
