package com.mycompany.clientprog2;

import com.mycompany.clientprog2.HttpsClient.CheckPremium;
import com.mycompany.clientprog2.HttpsClient.HttpsClient;
import com.mycompany.clientprog2.HttpsClient.JwtPremiumRequest;
import com.mycompany.clientprog2.HttpsClient.ReceiveImage;
import com.mycompany.clientprog2.HttpsClient.RequestSmsVerify;
import com.mycompany.clientprog2.HttpsClient.SendImage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import org.json.JSONObject;

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

    @SuppressWarnings("unchecked")
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
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IMAGico");
        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel1.setToolTipText("");
        labelEmail.setFont(new java.awt.Font("Times New Roman", 1, 14));
        labelEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        uploadImage.setFont(new java.awt.Font("Tahoma", 0, 18));
        uploadImage.setText("Scegli un immagine");
        uploadImage.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadImageActionPerformed(evt);
            }
        });
        showImage.setBackground(new java.awt.Color(255, 255, 255));
        showImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        filter_blackAndWhite.setFont(new java.awt.Font("Tahoma", 0, 18));
        filter_blackAndWhite.setText("Black & White");
        filter_blackAndWhite.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_blackAndWhiteActionPerformed(evt);
            }
        });
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel2.setText("Scegli un filtro da inviare al server");
        getImage.setFont(new java.awt.Font("Tahoma", 0, 18));
        getImage.setText("Mostrami immagine filtrata");
        getImage.setEnabled(false);
        getImage.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getImageActionPerformed(evt);
            }
        });
        saveImageButton.setFont(new java.awt.Font("Tahoma", 0, 18));
        saveImageButton.setText("Salva immagine");
        saveImageButton.setEnabled(false);
        saveImageButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveImageButtonActionPerformed(evt);
            }
        });
        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 48));
        jLabel3.setText("IMAGico");
        filter_gray.setFont(new java.awt.Font("Tahoma", 0, 18));
        filter_gray.setText("Gray");
        filter_gray.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_grayActionPerformed(evt);
            }
        });
        filter_sepia.setFont(new java.awt.Font("Tahoma", 0, 18));
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
        face_detector.setFont(new java.awt.Font("Tahoma", 0, 18));
        face_detector.setForeground(new java.awt.Color(255, 0, 0));
        face_detector.setText("Trova un volto");
        face_detector.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                face_detectorActionPerformed(evt);
            }
        });
        filter_red.setFont(new java.awt.Font("Tahoma", 0, 18));
        filter_red.setText("Red");
        filter_red.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_redActionPerformed(evt);
            }
        });
        add_text.setBackground(new java.awt.Color(51, 153, 255));
        add_text.setFont(new java.awt.Font("Tahoma", 0, 18));
        add_text.setForeground(new java.awt.Color(255, 0, 0));
        add_text.setText("Aggiungi testo");
        add_text.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_textActionPerformed(evt);
            }
        });
        premium_button.setBackground(new java.awt.Color(51, 153, 255));
        premium_button.setFont(new java.awt.Font("Tahoma", 1, 14));
        premium_button.setForeground(new java.awt.Color(255, 0, 0));
        premium_button.setText("Diventa Premium");
        premium_button.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                premium_buttonActionPerformed(evt);
            }
        });
        filter_blue.setFont(new java.awt.Font("Tahoma", 0, 18));
        filter_blue.setText("Blue");
        filter_blue.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_blueActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(35, Short.MAX_VALUE).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(saveImageButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(getImage, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(uploadImage, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))).addGap(18, 18, 18).addComponent(showImage, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(36, 36, 36)).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(54, 54, 54).addComponent(labelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(jPanel1Layout.createSequentialGroup().addGap(103, 103, 103).addComponent(premium_button))).addGap(18, 18, 18).addComponent(jLabel3).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel2).addGroup(jPanel1Layout.createSequentialGroup().addGap(24, 24, 24).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(filter_gray, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(filter_blackAndWhite, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(filter_sepia, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(filter_red, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(add_text, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(face_detector, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(filter_blue, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))).addGap(36, 36, 36)).addGroup(jPanel1Layout.createSequentialGroup().addGap(538, 538, 538).addComponent(upload_drive, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel3).addGroup(jPanel1Layout.createSequentialGroup().addComponent(labelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(19, 19, 19).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(premium_button, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))).addGap(24, 24, 24).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(uploadImage).addGap(26, 26, 26).addComponent(getImage).addGap(26, 26, 26).addComponent(saveImageButton)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(filter_blackAndWhite).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(filter_gray).addGap(18, 18, 18).addComponent(filter_sepia).addGap(18, 18, 18).addComponent(filter_red).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(filter_blue).addGap(29, 29, 29).addComponent(add_text).addGap(18, 18, 18).addComponent(face_detector)).addComponent(showImage, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addComponent(upload_drive, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(114, Short.MAX_VALUE)));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        pack();
    }
    
    private void elaboraInvioImmagine(String filter) {
        this.client = new SendImage(this.byteImg, filter, this.user.getToken());
        //chiamo il metodo post_request_image()
        callSend();
    }
    private void elaboraInvioImmagine(String filter, String inputText) {
        this.client = new SendImage(this.byteImg, filter, this.user.getToken(), inputText);
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
    
    private void premium_buttonActionPerformed(java.awt.event.ActionEvent evt) {
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
    }

    private void add_textActionPerformed(java.awt.event.ActionEvent evt) {
        //controllo a monte se l'utenza non ha i requisiti per richiedere filtro
        if (this.user instanceof UserAccount_Premium) {
            if (this.byteImg != null) {
                String inputText = (String) JOptionPane.showInputDialog(this, "Scrivi qui il testo da inviare al server:", "Pannello aggiungi testo", JOptionPane.PLAIN_MESSAGE, null, null, "");
                if (inputText != null) {
                    elaboraInvioImmagine("AddText", inputText);
                }
            } else
                showMessageDialog(null, "Errore! Seleziona prima un immagine.", "Attenzione", JOptionPane.ERROR_MESSAGE);
        } else
            showMessageDialog(null, "Per utilizzare questi filtri clicca su \"Diventa Premium\"", "CI dispiace :(", JOptionPane.ERROR_MESSAGE);
    }

    private void filter_redActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.byteImg != null) {
            elaboraInvioImmagine("Red");
        } else
            showMessageDialog(null, "Errore! Seleziona prima un immagine.", "Attenzione", JOptionPane.ERROR_MESSAGE);
    }

    private void face_detectorActionPerformed(java.awt.event.ActionEvent evt) {
        //controllo a monte se l'utenza non ha i requisiti per richiedere filtro
        if (this.user instanceof UserAccount_Premium) {
            if (this.byteImg != null) {
                elaboraInvioImmagine("FaceDetector");
            } else
                showMessageDialog(null, "Errore! Seleziona prima un immagine.", "Attenzione", JOptionPane.ERROR_MESSAGE);
        } else
            showMessageDialog(null, "Per utilizzare questi filtri clicca su \"Diventa Premium\"", "CI dispiace :(", JOptionPane.ERROR_MESSAGE);
    }

    private void upload_driveActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.byteImg != null) {
            DriveQuickstart drive = new DriveQuickstart();
            try {
                drive.uploadFile(this.filename);
            } catch (GeneralSecurityException | IOException ex) {
                Logger.getLogger(ClientGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else
            showMessageDialog(null, "Seleziona prima un immagine.", "Attenzione", JOptionPane.ERROR_MESSAGE);
    }

    private void filter_sepiaActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.byteImg != null) {
            elaboraInvioImmagine("Sepia");
        } else
            showMessageDialog(null, "Errore! Seleziona prima un immagine.", "Attenzione", JOptionPane.ERROR_MESSAGE);
    }

    private void filter_grayActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.byteImg != null) {
            elaboraInvioImmagine("Gray");
        } else
            showMessageDialog(null, "Errore! Seleziona prima un immagine.", "Attenzione", JOptionPane.ERROR_MESSAGE);
    }

    private void saveImageButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            BufferedImage bi = image.getBufferedImg();
            MyImage.saveImg(bi);
        } catch (IOException ex) {
            Logger.getLogger(ClientGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getImageActionPerformed(java.awt.event.ActionEvent evt) {
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
    }

    private void filter_blackAndWhiteActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.byteImg != null) {
            elaboraInvioImmagine("BlackandWhite");
        } else
            showMessageDialog(null, "Errore! Seleziona prima un immagine.", "Attenzione", JOptionPane.ERROR_MESSAGE);
    }

    private void uploadImageActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            this.byteImg = null;
            this.filename = MyImage.uploadFileImg();
            this.image = new MyImage(628, 451, this.filename);
            BufferedImage imageScale = image.scaleImage();
            ImageIcon imageIcon = new ImageIcon(imageScale);
            showImage.setIcon(imageIcon);
            this.byteImg = image.imageToByte();
        } catch (Exception ex) {
        }
    }

    private void filter_blueActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.byteImg != null) {
            elaboraInvioImmagine("Blue");
        } else
            showMessageDialog(null, "Errore! Seleziona prima un immagine.", "Attenzione", JOptionPane.ERROR_MESSAGE);
    }

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
    // Variables declaration - do not modify                     
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
    private javax.swing.JLabel showImage;
    private javax.swing.JButton uploadImage;
    private javax.swing.JButton upload_drive;
    // End of variables declaration                 


}
