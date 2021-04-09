package com.mycompany.clientprog2;

import com.mycompany.clientprog2.HttpsClient.HttpsClient;
import com.mycompany.clientprog2.HttpsClient.LoginFB;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import org.json.JSONObject;

public class LoginGui extends javax.swing.JFrame {

    private JSONObject obj;
    private HttpsClient client;

    public LoginGui() throws IOException {
        initComponents();
        String param = FileJson.importJSON("settings.json");
        this.obj = new JSONObject(param);
        LoginFB2.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        LoginFB = new javax.swing.JButton();
        LoginFB2 = new javax.swing.JButton();
        jLabelImage = new javax.swing.JLabel();
        jLabel2.setText("jLabel2");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(450, 250));
        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 36));
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Accedi al software");
        jLabel1.setToolTipText("");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LoginFB.setBackground(new java.awt.Color(59, 89, 182));
        LoginFB.setFont(new java.awt.Font("Tahoma", 1, 24));
        LoginFB.setText("Accedi con Facebook");
        LoginFB.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginFBActionPerformed(evt);
            }
        });
        LoginFB2.setBackground(new java.awt.Color(59, 89, 182));
        LoginFB2.setFont(new java.awt.Font("Tahoma", 1, 24));
        LoginFB2.setText("Conferma accesso");
        LoginFB2.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginFB2ActionPerformed(evt);
            }
        });
        jLabelImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\march\\Documents\\NetBeansProjects\\Clientprog2\\icon\\fb.jpg"));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addGap(0, 174, Short.MAX_VALUE).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(LoginFB2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(LoginFB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap(186, Short.MAX_VALUE)).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabelImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(49, 49, 49).addComponent(LoginFB, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(LoginFB2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(385, Short.MAX_VALUE)).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabelImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pack();
    }

    private void LoginFB2ActionPerformed(java.awt.event.ActionEvent evt) {
        this.client = new LoginFB();
        boolean accessGUI = this.client.get_request();
        if (accessGUI) {
            this.setVisible(false);
            new ClientGui().setVisible(true);
        } else {
            LoginFB.setVisible(true);
            LoginFB2.setVisible(false);
            showMessageDialog(null, "Qualcosa è andato storto. Riprova ad accedere con FB.", "Attenzione", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void LoginFBActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            try {
                String client_id = this.obj.getString("client_id_FB");
                String redirect_uri = this.obj.getString("redirect_uri_FB");
                Desktop.getDesktop().browse(new URI("https://www.facebook.com/v9.0/dialog/oauth?client_id=" + client_id + "&redirect_uri=" + redirect_uri + "&scope=email"));
                LoginFB.setVisible(false);
                LoginFB2.setVisible(true);
            } catch (URISyntaxException ex) {
                Logger.getLogger(LoginGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) throws IOException {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new LoginGui().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(LoginGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

   // Variables declaration - do not modify                     
    private javax.swing.JButton LoginFB;
    private javax.swing.JButton LoginFB2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelImage;
    // End of variables declaration     
}
