package com.mycompany.clientprog2;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyImage {

    private BufferedImage bufferedImg;
    private int width;
    private int height;

    public MyImage(int width, int height, String fileImg) throws IOException {
        this.bufferedImg = ImageIO.read(new File(fileImg));
        this.width = width;
        this.height = height;
    }

    public MyImage(int width, int height, BufferedImage bufferedImg) throws IOException {
        this.bufferedImg = bufferedImg;
        this.width = width;
        this.height = height;
    }
    
    public MyImage(int width, int height, byte[] byteimg) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(byteimg);
        this.bufferedImg = ImageIO.read(bais);
        this.width = width;
        this.height = height;
    }
    
    public void setBufferedImg(BufferedImage bufferedImg) {
        this.bufferedImg = bufferedImg;
    }

    public BufferedImage getBufferedImg() {
        return this.bufferedImg;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public static String uploadFileImg() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Scegli un immagine...");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG", "jpeg", "jpg", "png");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        return filename;
    }

    public BufferedImage scaleImage() throws Exception {
        BufferedImage bi = new BufferedImage(this.width, this.height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(this.bufferedImg, 0, 0, this.width, this.height, null);
        g2d.dispose();
        return bi;
    }

    public byte[] imageToByte() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(this.bufferedImg, "png", baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    public static boolean saveImg(BufferedImage img) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salva la nuova immagine");
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ImageIO.write(img, "jpg", file);
            return true;
        } else
            return false;
    }
}
