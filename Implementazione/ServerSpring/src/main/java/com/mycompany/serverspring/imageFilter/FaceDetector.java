/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverspring.imageFilter;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import nu.pattern.OpenCV;
import org.opencv.core.CvType;
import static org.opencv.core.CvType.CV_8UC;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

/**
 *
 * @author march
 */
public class FaceDetector extends BufferedImgBase {
    
    public FaceDetector(byte[] img) throws IOException{
       super(img);
    }

    @Override
    public byte[] applicaFiltro() {
        //carico la libreria OpenCV
        OpenCV.loadShared();
        //trasformo super.img(buffered image) in Mat image
        byte[] data = ((DataBufferByte) super.img.getRaster().getDataBuffer()).getData();

        Mat loadedImage = new Mat(super.img.getHeight(), super.img.getWidth(), CvType.CV_8UC3);
        loadedImage.put(0, 0, data);
        
        MatOfRect facesDetected = new MatOfRect();
        CascadeClassifier cascadeClassifier = new CascadeClassifier(); 
            int minFaceSize = Math.round(loadedImage.rows() * 0.1f); 
            cascadeClassifier.load("./src/main/resources/haarcascade_frontalface_alt.xml"); 
            cascadeClassifier.detectMultiScale(loadedImage, 
            facesDetected, 
            1.1, 
            3, 
            Objdetect.CASCADE_SCALE_IMAGE, 
            new Size(minFaceSize, minFaceSize), 
            new Size() 
         );
        Rect[] facesArray = facesDetected.toArray(); 
        for(Rect face : facesArray) { 
            Imgproc.rectangle(loadedImage, face.tl(), face.br(), new Scalar(0, 0, 255), 3); 
        }
        //trasformo l'oggetto Mat con nome loadedImage in BufferedImage e successivamente al solito trasformo BufferedImage in byte per assere restituito
        if (!loadedImage.empty()) {
            int type = BufferedImage.TYPE_BYTE_GRAY;
            if (loadedImage.channels() > 1) {
                type = BufferedImage.TYPE_3BYTE_BGR;
            }
            int bufferSize = loadedImage.channels() * loadedImage.cols() * loadedImage.rows();
            byte[] b = new byte[bufferSize];
            loadedImage.get(0, 0, b); // get all the pixels
            BufferedImage image = new BufferedImage(loadedImage.cols(), loadedImage.rows(), type);
            final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
            System.arraycopy(b, 0, targetPixels, 0, b.length);
            // convert BufferedImage to byte[]
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ImageIO.write(image, "jpeg", baos);
            } catch (IOException ex) {
                Logger.getLogger(BlackandWhite.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] bytes = baos.toByteArray();
            return bytes;         
        }
        return null;
    }
    
}
