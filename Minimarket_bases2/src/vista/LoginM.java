
package vista;

/**
 *
 * @author santi
 */

import Modelo.C_login;
import Modelo.MySQL;
import static Modelo.MySQL.Conexion;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;

public class LoginM extends javax.swing.JFrame {

    //archivo de conexiones de bases
    MySQL db = new MySQL();
    
    //Login logo = new Login();
    public static Timer tmr;
    public ActionListener escuchar;
    public int intervalo = 0, id;
    
    
    
    public LoginM() {
        
        
        
         
        //Creacion de la base de datos
        db.MySQLConnection("root", "", "");
        
        //Verificar si la base se encuentra, si no la encuentra la crea automaticamente junto con la tabla empleado 
        try {
        PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA where SCHEMA_NAME = ?");
            stp.setString(1, "Minimarket");

            ResultSet res = (ResultSet) stp.executeQuery();
            
            if (res.next()) {
                //JOptionPane.showMessageDialog(rootPane, "Conectado a la base de datos");
            }else{
                db.createDB("Minimarket");
                db.Ctabla_empl();
            }
        } catch (SQLException ex) {
            System.out.println("Error usuario no encotrado");
        }    
        
        
        //Conexion
        db.MySQLConnection("root", "", "Minimarket");
        db.closeConnection();
        

        //asignacion de partes antes del init
         setTitle("Minimarket");
        Image Micono = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/producto.png"));
        setIconImage(Micono);
        
        setLayout(null);
        
        //this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        
        initComponents();
        
        //Metodo contenedor de los diseños de los componentes
        components();
        setLocationRelativeTo(null);
    }
        public Font typeFE = new Font("Tw Cen MT Condensed Extra Bold",Font.PLAIN,16);
    
    private void components(){
        
        
        //Imagen USUARIO NN *************************************************************************************************
        
        int nuevoAncho = 122,nuevoAlto = 122; // ancho deseado, alto deseado
        int wFLogin=303 , hFlogin=264;
        jBsingUp.setText("SingUp");
        jBsingUp.setFont(typeFE);
        jBsingUp.setForeground(new Color(240, 113, 1));
        
        // SELECCION img BACKGROUND
        lblFondoLogin.setText("");
        ImageIcon fondoLog = new ImageIcon(getClass().getResource("/imagenes/pic/fondo5.jpg")); // Cargar imagen original
        Image imagenLog = fondoLog.getImage(); // Obtener imagen original
        Image reSize = imagenLog.getScaledInstance(wFLogin, hFlogin, Image.SCALE_SMOOTH); // Redimensionar imagen al tamaño del JLabel
        ImageIcon imgFondoLog = new ImageIcon(reSize); // Crear instancia de ImageIcon con la imagen redimensionada
        JLabel lblFondoLogin = new JLabel(imgFondoLog);
        lblFondoLogin.setBounds(0, 0, wFLogin, hFlogin); // Establecer el tamaño del JLabel para que cubra todo el JInternalFrame
        getContentPane().add(lblFondoLogin); // Agregar el JLabel al contenido del JInternalFrame
     
        // SELECCION IMG LABEL USER 
        ImageIcon img_0 = new ImageIcon(getClass().getResource("/imagenes/pic/user3.png"));
        ImageIcon foto_0 = new ImageIcon(img_0.getImage().getScaledInstance(jLfoto.getWidth(), jLfoto.getHeight(), Image.SCALE_SMOOTH));
        jLfoto.setText("");
        jLfoto.setIcon(foto_0);
        jLfoto.setToolTipText("Usuario img");
        
        // REDIMENSION IMAGEN LABEL
        Image imgOrigi = foto_0.getImage();
        Image imagenRedimensionada = imgOrigi.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
        ImageIcon fotoRedimensionada = new ImageIcon(imagenRedimensionada);
        jLfoto.setPreferredSize(new Dimension(nuevoAncho, nuevoAlto));
        jLfoto.setIcon(fotoRedimensionada);
        jLfoto.setHorizontalAlignment(SwingConstants.CENTER); //Centrar imagen
        //jLfoto.setVerticalAlignment(SwingConstants.BOTTOM);
            
        // BORDE FONDOLABEL
        Color bordeColor = new Color(219, 220, 221);
        int grosorBorde = 3; // Grosor del borde en píxeles
        Border borde = BorderFactory.createLineBorder(bordeColor, grosorBorde);
        jLfoto.setBorder(borde);
        
        // Color fondo label
        Color color = new Color (235, 236, 237);
        jLfoto.setBackground(color);
        jLfoto.setOpaque(true); // para que se muestre el color de fondo
        jLfoto.setForeground(Color.WHITE);        
        
       //****************************************************************************************************************************
        
       jLcarga.setText("0%");
        jTuser.setText("");
        jPFclave.setEchoChar('#');
        jPBcarga.setStringPainted(false);
       
       Color gris_1 = new Color(90, 90, 90);
        jTuser.setText("Digite su usuario");
        jTuser.setForeground(gris_1);

        jPFclave.setEnabled(false);
        jPFclave.setText("clave");
        jPFclave.setForeground(gris_1);
        
    }

    
    public void barrapro2() {
        C_login.ver_nivel(jTuser.getText());
        escuchar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                intervalo++;
                jPBcarga.setValue(intervalo);
                jLcarga.setText(intervalo + "%");
                if (jPBcarga.getValue() == 100) {
                    dispose();
                    tmr.stop();
                    frmiPrincipal Prin = new frmiPrincipal();
                    Prin.setVisible(true);
                }
            }
        };
        tmr = new Timer(10, escuchar);
        tmr.start();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLfoto = new javax.swing.JLabel();
        jTuser = new javax.swing.JTextField();
        jPFclave = new javax.swing.JPasswordField();
        jLcarga = new javax.swing.JLabel();
        jPBcarga = new javax.swing.JProgressBar();
        jBsingUp = new javax.swing.JButton();
        lblFondoLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLfoto.setText("jLabel1");

        jTuser.setText("jTextField1");
        jTuser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTuserFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTuserFocusLost(evt);
            }
        });
        jTuser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTuserKeyPressed(evt);
            }
        });

        jPFclave.setText("jPasswordField1");
        jPFclave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPFclaveKeyPressed(evt);
            }
        });

        jLcarga.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLcarga.setText("jLabel2");

        jBsingUp.setText("SingUp");
        jBsingUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsingUpActionPerformed(evt);
            }
        });

        lblFondoLogin.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLcarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTuser)
                                    .addComponent(jPFclave, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jBsingUp)
                                        .addGap(28, 28, 28))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblFondoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(53, 53, 53))))))
                    .addComponent(jPBcarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFondoLogin)
                        .addGap(29, 29, 29)
                        .addComponent(jPFclave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jBsingUp)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLcarga)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPBcarga, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTuserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTuserFocusGained
        if (jTuser.getText().equals("Digite su usuario")) {
            Color gris_1 = new Color(0, 0, 0);
            jTuser.setForeground(gris_1);
            jTuser.setText("");
        }
    }//GEN-LAST:event_jTuserFocusGained

    private void jTuserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTuserFocusLost
        if (jTuser.getText().equals("")) {
            Color gris_1 = new Color(0, 0, 0);
            jTuser.setForeground(gris_1);
            jTuser.setText("Digite su usuario");
        }
    }//GEN-LAST:event_jTuserFocusLost

    private void jTuserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTuserKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            C_login.rec_nom(jTuser.getText());
            
            if (C_login.val_nom==true) {
                //cambio de caja
                jPFclave.setEnabled(true);
                jTuser.setEnabled(false);
                jPFclave.setFocusable(true);

                //atencion
                jPFclave.selectAll();
                jPFclave.getSelectedText();
                
                //poner el usuario escrito
                String us=jTuser.getText();
                jTuser.setText(us);
                
                
                //poner imgen del usurio si encuentra
               
                ImageIcon img_user = new ImageIcon(getClass().getResource("/imagenes/perfil_encontrado.png"));
                ImageIcon foto_user = new ImageIcon(img_user.getImage().getScaledInstance(jLfoto.getWidth(), jLfoto.getHeight(), Image.SCALE_DEFAULT));
                jLfoto.setIcon(foto_user);
            }else{
                JOptionPane.showMessageDialog(rootPane, "Usuario no existe, vuelve a intentarlo");
                jTuser.setEnabled(true);
                jPFclave.setEnabled(false);
                jTuser.setFocusable(true);
                jTuser.selectAll();
                jTuser.getSelectedText();
                jTuser.setText("");
            }
            
        }
        db.closeConnection();
    }//GEN-LAST:event_jTuserKeyPressed

    private void jPFclaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPFclaveKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            C_login.rec_clave(jPFclave.getText());
            
            if (C_login.val_cont==true) {
                //cambio de caja
                jPFclave.setEnabled(false);
                jTuser.setEnabled(false);
                
                barrapro2();
            }else{
                JOptionPane.showMessageDialog(rootPane, "Contraseña incorrecta, Vuelva a intentar");
                jTuser.setEnabled(false);
                jPFclave.setEnabled(true);
                jPFclave.setFocusable(true);
                jPFclave.selectAll();
                jPFclave.getSelectedText();
                jPFclave.setText("");
            }
            
        }
        db.closeConnection();
    }//GEN-LAST:event_jPFclaveKeyPressed

    private void jBsingUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsingUpActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SingUp().setVisible(true);
                setVisible(false);
            }
        });
    }//GEN-LAST:event_jBsingUpActionPerformed

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
            java.util.logging.Logger.getLogger(LoginM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBsingUp;
    private javax.swing.JLabel jLcarga;
    private javax.swing.JLabel jLfoto;
    private javax.swing.JProgressBar jPBcarga;
    private javax.swing.JPasswordField jPFclave;
    private javax.swing.JTextField jTuser;
    private javax.swing.JLabel lblFondoLogin;
    // End of variables declaration//GEN-END:variables
}
