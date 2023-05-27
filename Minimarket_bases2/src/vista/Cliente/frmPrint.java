package vista.Cliente;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class frmPrint extends javax.swing.JFrame {

    public frmPrint() {
        initComponents();

        setBackground(new Color(200, 211, 181));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        //COMBO
        comboArchivos.setVisible(false);

        btnGTipoImp.add(rbtnArchivos);
        btnGTipoImp.add(rbtnImpresora);
        btnGTipoImp.add(rbtnPantalla);

        rbtnArchivos.setText("Archivo");
        rbtnImpresora.setText("Impresora");
        rbtnPantalla.setText("Pantalla");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGTipoImp = new javax.swing.ButtonGroup();
        rbtnImpresora = new javax.swing.JRadioButton();
        rbtnPantalla = new javax.swing.JRadioButton();
        rbtnArchivos = new javax.swing.JRadioButton();
        comboArchivos = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        rbtnImpresora.setText("jRadioButton1");

        rbtnPantalla.setText("jRadioButton1");

        rbtnArchivos.setText("jRadioButton1");
        rbtnArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnArchivosActionPerformed(evt);
            }
        });

        comboArchivos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtnArchivos)
                            .addComponent(rbtnPantalla))
                        .addGap(234, 234, 234))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbtnImpresora)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(rbtnPantalla)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnImpresora)
                    .addComponent(comboArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(rbtnArchivos)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbtnArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnArchivosActionPerformed
        String[] nomArchivos = {".docx", ".pdf", ".txt"};
        if (rbtnArchivos.isSelected()) {
            comboArchivos.setVisible(true);
            comboArchivos.removeAllItems();

            for (int i = 0; i < nomArchivos.length; i++) {
                comboArchivos.addItem(nomArchivos[i]);
            }
        } else {
            comboArchivos.setVisible(false);
        }
    }//GEN-LAST:event_rbtnArchivosActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dispose();
        }
    }//GEN-LAST:event_formKeyPressed

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
            java.util.logging.Logger.getLogger(frmPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPrint().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGTipoImp;
    private javax.swing.JComboBox<String> comboArchivos;
    private javax.swing.JRadioButton rbtnArchivos;
    private javax.swing.JRadioButton rbtnImpresora;
    private javax.swing.JRadioButton rbtnPantalla;
    // End of variables declaration//GEN-END:variables
}
