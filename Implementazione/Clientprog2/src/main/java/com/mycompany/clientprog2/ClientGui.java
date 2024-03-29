/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientprog2;

import com.mycompany.clientprog2.HttpsClient.CheckPremium;
import com.mycompany.clientprog2.HttpsClient.HttpsClient;
import com.mycompany.clientprog2.HttpsClient.JwtPremiumRequest;
import com.mycompany.clientprog2.HttpsClient.ReceiveImage;
import com.mycompany.clientprog2.HttpsClient.RequestSmsVerify;
import com.mycompany.clientprog2.HttpsClient.SendImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import org.json.JSONObject;

/**
 *
 * @author march
 */
public class ClientGui extends javax.swing.JFrame {

    private byte[] byteImg;
    private String filename;
    private String tmpFileName;
    private HttpsClient client;
    private MyImage image;
    private Icon iconNotify = new ImageIcon("icon/notify.png");
    private UserAccount user;
    private String param = FileJson.importJSON("data_access_user.json");
    private JSONObject obj = new JSONObject(param);
    //proprietà per votton addText
    private int x;
    private int y;
    private boolean flagListener;
    
    public ClientGui() {
        initComponents();
        String emailjson = obj.getString("email");
        this.client = new CheckPremium(emailjson);
        boolean premium = this.client.post_request();
        if (premium) {
            this.user = new UserAccount_Premium();//creo un jwt
            labelEmail.setText("Email utente PREMIUM: " + this.user.getEmail());
            labelEmail.setForeground(Color.RED);
            premium_button.setVisible(false);
        } else {
            this.user = new UserAccount_Base();//token null
            JOptionPane.showMessageDialog(null, "Benvenuto! Trasforma il tuo account in Premium per sfruttare al meglio i nostri filtri!", "Benvenuto!", JOptionPane.INFORMATION_MESSAGE);
            labelEmail.setText("Email utente: " + this.user.getEmail());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelEmail = new javax.swing.JLabel();
        uploadImage = new javax.swing.JButton();
        showImage = new javax.swing.JLabel();
        filter_blackAndWhite = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        getImage = new javax.swing.JButton();
        saveImageButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        filter_gray = new javax.swing.JButton();
        filter_sepia = new javax.swing.JButton();
        upload_drive = new javax.swing.JButton();
        face_detector = new javax.swing.JButton();
        filter_red = new javax.swing.JButton();
        add_text = new javax.swing.JButton();
        premium_button = new javax.swing.JButton();
        filter_blue = new javax.swing.JButton();
        segugio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel1.setToolTipText("");

        labelEmail.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        labelEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        uploadImage.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        uploadImage.setText("Scegli un immagine");
        uploadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadImageActionPerformed(evt);
            }
        });

        showImage.setBackground(new java.awt.Color(255, 255, 255));
        showImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        filter_blackAndWhite.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        filter_blackAndWhite.setText("Black & White");
        filter_blackAndWhite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_blackAndWhiteActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Scegli un filtro da inviare al server");

        getImage.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getImage.setText("Mostrami immagine filtrata");
        getImage.setEnabled(false);
        getImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getImageActionPerformed(evt);
            }
        });

        saveImageButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        saveImageButton.setText("Salva immagine");
        saveImageButton.setEnabled(false);
        saveImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveImageButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 48)); // NOI18N
        jLabel3.setText("IMAGico");

        filter_gray.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        filter_gray.setText("Gray");
        filter_gray.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_grayActionPerformed(evt);
            }
        });

        filter_sepia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        filter_sepia.setText("Sepia");
        filter_sepia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_sepiaActionPerformed(evt);
            }
        });

        upload_drive.setText("Carica immagine su Google Drive");
        upload_drive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upload_driveActionPerformed(evt);
            }
        });

        face_detector.setBackground(new java.awt.Color(51, 153, 255));
        face_detector.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        face_detector.setForeground(new java.awt.Color(255, 0, 0));
        face_detector.setText("Trova un volto");
        face_detector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                face_detectorActionPerformed(evt);
            }
        });

        filter_red.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        filter_red.setText("Red");
        filter_red.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_redActionPerformed(evt);
            }
        });

        add_text.setBackground(new java.awt.Color(51, 153, 255));
        add_text.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add_text.setForeground(new java.awt.Color(255, 0, 0));
        add_text.setText("Aggiungi testo");
        add_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_textActionPerformed(evt);
            }
        });

        premium_button.setBackground(new java.awt.Color(51, 153, 255));
        premium_button.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        premium_button.setForeground(new java.awt.Color(255, 0, 0));
        premium_button.setText("Diventa Premium");
        premium_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                premium_buttonActionPerformed(evt);
            }
        });

        filter_blue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        filter_blue.setText("Blue");
        filter_blue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_blueActionPerformed(evt);
            }
        });

        segugio.setBackground(new java.awt.Color(51, 153, 255));
        segugio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        segugio.setForeground(new java.awt.Color(255, 0, 0));
        segugio.setText("Cerca QUALSIASI Immagine!");
        segugio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segugioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(saveImageButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(getImage, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(uploadImage, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(segugio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(showImage, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(labelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addComponent(premium_button)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(filter_gray, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filter_blackAndWhite, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filter_sepia, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filter_red, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_text, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(face_detector, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filter_blue, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(36, 36, 36))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(502, 502, 502)
                .addComponent(upload_drive, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(premium_button, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(uploadImage)
                        .addGap(26, 26, 26)
                        .addComponent(getImage)
                        .addGap(26, 26, 26)
                        .addComponent(saveImageButton)
                        .addGap(58, 58, 58)
                        .addComponent(segugio))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(filter_blackAndWhite)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filter_gray)
                        .addGap(18, 18, 18)
                        .addComponent(filter_sepia)
                        .addGap(18, 18, 18)
                        .addComponent(filter_red)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filter_blue)
                        .addGap(29, 29, 29)
                        .addComponent(add_text)
                        .addGap(18, 18, 18)
                        .addComponent(face_detector))
                    .addComponent(showImage, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(upload_drive, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void elaboraInvioImmagine(String filter) {
        this.client = new SendImage(this.byteImg, filter, this.user.getToken());
        //chiamo il metodo post_request_image()
        callSend();
    }
    private void elaboraInvioImmagine(String filter, String inputText) {
        this.client = new SendImage(this.byteImg, filter, this.user.getToken(), inputText, this.x, this.y);
        //chiamo il metodo post_request_image()
        callSend();
    }

    private void callSend(){
        String responseFileNametmp = this.client.post_request_image();     
        if (responseFileNametmp != null) {
            this.tmpFileName = responseFileNametmp;
            ImageIcon icon = new ImageIcon("icon/check.png");
            JOptionPane.showMessageDialog(null, "Elaborazione completata! Clicca \"Mostrami immagine filtrata\" per scoprirla", "Immagine elaborata", JOptionPane.INFORMATION_MESSAGE, icon);
            getImage.setEnabled(true);
            saveImageButton.setEnabled(true);
            getImage.setText("Mostrami immagine filtrata");
            getImage.setIcon(iconNotify);
        } else {
            showMessageDialog(null, "Qualcosa è andato storto. Riprova o riavvia l'applicazione.", "Attenzione", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void uploadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadImageActionPerformed
        try {
            this.byteImg = null;
            this.filename = MyImage.uploadFileImg();
            this.image = new MyImage(628, 451, this.filename);
            BufferedImage imageScale = this.image.scaleImage();
            ImageIcon imageIcon = new ImageIcon(imageScale);
            showImage.setIcon(imageIcon);
            this.byteImg = this.image.imageToByte();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_uploadImageActionPerformed

    private void filter_blackAndWhiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_blackAndWhiteActionPerformed
        if (this.byteImg != null) {
            elaboraInvioImmagine("BlackandWhite");
        } else
            showMessageDialog(null, "Errore! Seleziona prima un immagine.", "Attenzione", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_filter_blackAndWhiteActionPerformed

    private void getImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getImageActionPerformed
        try {
            this.client = new ReceiveImage(this.tmpFileName);
            BufferedImage processImage = this.client.get_image();
            if (processImage != null) {
                this.image = new MyImage(628, 451, processImage);
                BufferedImage imageScale = this.image.scaleImage();
                ImageIcon imageIcon = new ImageIcon(imageScale);
                showImage.setIcon(imageIcon);
                this.byteImg = this.image.imageToByte();
                getImage.setIcon(null);
                getImage.setEnabled(false);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientGui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClientGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_getImageActionPerformed

    private void saveImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveImageButtonActionPerformed
        try {
            BufferedImage bi = image.getBufferedImg();
            MyImage.saveImg(bi);
        } catch (IOException ex) {
            Logger.getLogger(ClientGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_saveImageButtonActionPerformed

    private void filter_grayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_grayActionPerformed
        if (this.byteImg != null) {
            elaboraInvioImmagine("Gray");
        } else
            showMessageDialog(null, "Errore! Seleziona prima un immagine.", "Attenzione", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_filter_grayActionPerformed

    private void filter_sepiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_sepiaActionPerformed
        if (this.byteImg != null) {
            elaboraInvioImmagine("Sepia");
        } else
            showMessageDialog(null, "Errore! Seleziona prima un immagine.", "Attenzione", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_filter_sepiaActionPerformed

    private void upload_driveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upload_driveActionPerformed
        if (this.byteImg != null) {
            DriveQuickstart drive = new DriveQuickstart();
            try {
                drive.uploadFile(this.filename);
            } catch (GeneralSecurityException | IOException ex) {
                Logger.getLogger(ClientGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else
            showMessageDialog(null, "Seleziona prima un immagine.", "Attenzione", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_upload_driveActionPerformed

    private void face_detectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_face_detectorActionPerformed
        //controllo a monte se l'utenza non ha i requisiti per richiedere filtro
        if (this.user instanceof UserAccount_Premium) {
            if (this.byteImg != null) {
                elaboraInvioImmagine("FaceDetector");
            } else
                showMessageDialog(null, "Errore! Seleziona prima un immagine.", "Attenzione", JOptionPane.ERROR_MESSAGE);
        } else
            showMessageDialog(null, "Per utilizzare questi filtri clicca su \"Diventa Premium\"", "CI dispiace :(", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_face_detectorActionPerformed

    private void filter_redActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_redActionPerformed
        if (this.byteImg != null) {
            elaboraInvioImmagine("Red");
        } else
            showMessageDialog(null, "Errore! Seleziona prima un immagine.", "Attenzione", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_filter_redActionPerformed

    private void addText(){
        //controllo a monte se l'utenza non ha i requisiti per richiedere filtro
        if (this.user instanceof UserAccount_Premium) {
            if (this.byteImg != null) {
                String inputText = (String) JOptionPane.showInputDialog(this, "Scrivi qui il testo da inviare al server:", "Pannello aggiungi testo ("+this.x+","+this.y+")", JOptionPane.PLAIN_MESSAGE, null, null, "");
                if (inputText != null) {
                    elaboraInvioImmagine("AddText", inputText);
                }
            } else
                showMessageDialog(null, "Errore! Seleziona prima un immagine.", "Attenzione", JOptionPane.ERROR_MESSAGE);
        } else
            showMessageDialog(null, "Per utilizzare questi filtri clicca su \"Diventa Premium\"", "CI dispiace :(", JOptionPane.ERROR_MESSAGE);
    }
    private void add_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_textActionPerformed
        if (this.user instanceof UserAccount_Premium) {
            if (this.byteImg != null) {
                //setto il flag di ascolto mouse a true 
                flagListener = true;
                try {
                    Image iconImage = ImageIO.read(new File("icon/coordinate_point.png"));
                    showMessageDialog(null, "Clicca sull'immagine per scegliere il punto su cui scrivere!!", "Scegli coordinate", JOptionPane.INFORMATION_MESSAGE);
                    showImage.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(flagListener == true)
                            {
                                Graphics g = showImage.getGraphics();
                                x = e.getX();
                                y = e.getY();
                                g.drawImage(iconImage, x-67, y-120, null);
                                flagListener = false;
                                addText();
                            }
                        }
                    });
                } catch (IOException ex) {
                    Logger.getLogger(ClientGui.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else
                showMessageDialog(null, "Errore! Seleziona prima un immagine.", "Attenzione", JOptionPane.ERROR_MESSAGE);
        } else
            showMessageDialog(null, "Per utilizzare questi filtri clicca su \"Diventa Premium\"", "CI dispiace :(", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_add_textActionPerformed

    private void premium_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_premium_buttonActionPerformed
        String number = (String) JOptionPane.showInputDialog(this, "Inserisci qui il tuo numero di cellulare e diventa Premium per avere nuove funzionalità", "Diventa Utente Premium!!", JOptionPane.PLAIN_MESSAGE, null, null, "");
        this.client = new RequestSmsVerify(number, null);
        boolean responseStep1 = this.client.post_request();
        if (responseStep1) {
            String tokenNumber = (String) JOptionPane.showInputDialog(this, "Attendi il codice di verifica ed inseriscilo qui!", "Ultimo step", JOptionPane.PLAIN_MESSAGE, null, null, "");
            System.out.println("tokenNumber ---> " + tokenNumber);
            this.client = new RequestSmsVerify(number, tokenNumber);
            boolean responseStep2 = this.client.post_request();
            if (responseStep2) {
                this.client = new JwtPremiumRequest(this.user.getEmail());
                boolean responseTokenPremium = this.client.post_request();
                if (responseTokenPremium) {
                    this.setVisible(false);
                    new ClientGui().setVisible(true);
                    ImageIcon icon = new ImageIcon("icon/premium.png");
                    JOptionPane.showMessageDialog(null, "Benissimo!", "Grandioso, sei diventato un Utente Premium!! Adesso puou sfruttare nuovissimi filtri", JOptionPane.INFORMATION_MESSAGE, icon);
                } else
                    JOptionPane.showMessageDialog(null, "Qualcosa è andato storto, riprova", "Errore nel processo di registrazione Utente Premium", JOptionPane.INFORMATION_MESSAGE);
            }
        } else
            JOptionPane.showMessageDialog(null, "Qualcosa è andato storto, riprova", "Errore inserimento numero di telefono", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_premium_buttonActionPerformed

    private void filter_blueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_blueActionPerformed
        if (this.byteImg != null) {
            elaboraInvioImmagine("Blue");
        } else
            showMessageDialog(null, "Errore! Seleziona prima un immagine.", "Attenzione", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_filter_blueActionPerformed

    private void segugioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segugioActionPerformed
        //controllo a monte se l'utenza non ha i requisiti per richiedere filtro
        if (this.user instanceof UserAccount_Premium) {
            String inputText = (String) JOptionPane.showInputDialog(this, "Scrivi qui per cercare un immagine con Segugio:", "Pannello Segugio", JOptionPane.PLAIN_MESSAGE, null, null, "");
            if (inputText != null) {
                try {
                    //assegno alla variabile di tipo MyImage la nuova img trovata con segugio
                    SpiderImage spider = new SpiderImage(inputText);
                    this.byteImg = spider.chiamaSpider();
                    this.image = new MyImage(628, 451,   this.byteImg);
                    BufferedImage imageScale = this.image.scaleImage();
                    ImageIcon imageIcon = new ImageIcon(imageScale);
                    showImage.setIcon(imageIcon);
                    saveImageButton.setEnabled(true);
                } catch (IOException ex) {
                    Logger.getLogger(ClientGui.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(ClientGui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else
            showMessageDialog(null, "Per utilizzare questa funzione clicca su \"Diventa Premium\"", "CI dispiace :(", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_segugioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
   
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ClientGui().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_text;
    private javax.swing.JButton face_detector;
    private javax.swing.JButton filter_blackAndWhite;
    private javax.swing.JButton filter_blue;
    private javax.swing.JButton filter_gray;
    private javax.swing.JButton filter_red;
    private javax.swing.JButton filter_sepia;
    private javax.swing.JButton getImage;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JButton premium_button;
    private javax.swing.JButton saveImageButton;
    private javax.swing.JButton segugio;
    private javax.swing.JLabel showImage;
    private javax.swing.JButton uploadImage;
    private javax.swing.JButton upload_drive;
    // End of variables declaration//GEN-END:variables
}
