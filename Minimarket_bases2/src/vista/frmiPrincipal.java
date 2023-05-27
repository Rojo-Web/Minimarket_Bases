/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/MDIApplication.java to edit this template
 */
package vista;

import Modelo.MySQL;
import vista.Producto.frmiProducto;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import vista.Cliente.frmiCliente;
import vista.empresa.Empresa;

/**
 *
 * @author santi
 */
public class frmiPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form frmiPrincipal
     */
    public static int nFils = 0;
    private frmiProducto producto = null;
    private frmiCliente cliente = null;
    private Empresa emp = null;

    public frmiPrincipal() {
        //titulo
        setTitle("Minimarket");
        //Icono del formulario
        Image icon_inter = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/producto.png"));
        setIconImage(icon_inter);

        initComponents();
        

        //Seccion del menu
        fileMenu.setMnemonic('f');
        fileMenu.setText("Entidades");

        JmPrincipales.setText("Principales");

        jMIProducto.setText("Producto");
        JmPrincipales.add(jMIProducto);

        jMICliente.setText("Cliente");
        JmPrincipales.add(jMICliente);

        jMIEmpresa.setText("Empresa");
        JmPrincipales.add(jMIEmpresa);


        fileMenu.add(JmPrincipales);

        jMSecundarias.setText("Admin");

        jMIEmpleado.setText("Empleado");
        jMSecundarias.add(jMIEmpleado);


        fileMenu.add(jMSecundarias);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);
        
        jMSecundarias.setEnabled(false);

        //BLoque por si no es admin
        if (Modelo.C_login.Val_admin==true) {
            jMSecundarias.setEnabled(true);
        }
        
        //Fin del seccion del menu nav
        setLocationRelativeTo(null);
        //setSize(700,600);
        setResizable(false);
        //setLayout(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        JmPrincipales = new javax.swing.JMenu();
        jMIProducto = new javax.swing.JMenuItem();
        jMICliente = new javax.swing.JMenuItem();
        jMIEmpresa = new javax.swing.JMenuItem();
        jMSecundarias = new javax.swing.JMenu();
        jMIEmpleado = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        JmPrincipales.setText("jMenu1");

        jMIProducto.setText("jMenuItem1");
        jMIProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIProductoActionPerformed(evt);
            }
        });
        JmPrincipales.add(jMIProducto);

        jMICliente.setText("jMenuItem2");
        jMICliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIClienteActionPerformed(evt);
            }
        });
        JmPrincipales.add(jMICliente);

        jMIEmpresa.setText("jMenuItem3");
        jMIEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIEmpresaActionPerformed(evt);
            }
        });
        JmPrincipales.add(jMIEmpresa);

        fileMenu.add(JmPrincipales);

        jMSecundarias.setText("jMenu1");

        jMIEmpleado.setText("jMenuItem7");
        jMSecundarias.add(jMIEmpleado);

        fileMenu.add(jMSecundarias);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void jMIProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIProductoActionPerformed
        try {

            if (producto == null || producto.isClosed()) {
                
                producto = new frmiProducto();
                this.desktopPane.add(producto);

            }
            producto.setVisible(true);
            MySQL.Ctabla_prod();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "" + e);
        }
    }//GEN-LAST:event_jMIProductoActionPerformed

    private void jMIClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIClienteActionPerformed
        if (cliente == null || cliente.isClosed()) {
                
                cliente = new frmiCliente();
                this.desktopPane.add(cliente);

            }
            cliente.setVisible(true);
    }//GEN-LAST:event_jMIClienteActionPerformed

    private void jMIEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIEmpresaActionPerformed
        if (emp == null || emp.isClosed()) {
                
                emp = new Empresa();
                this.desktopPane.add(emp);

            }
            emp.setVisible(true);
    }//GEN-LAST:event_jMIEmpresaActionPerformed

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
            java.util.logging.Logger.getLogger(frmiPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmiPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmiPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmiPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu JmPrincipales;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem jMICliente;
    private javax.swing.JMenuItem jMIEmpleado;
    private javax.swing.JMenuItem jMIEmpresa;
    private javax.swing.JMenuItem jMIProducto;
    private javax.swing.JMenu jMSecundarias;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

}
