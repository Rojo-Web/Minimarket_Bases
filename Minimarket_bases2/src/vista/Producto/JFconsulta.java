/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.Producto;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author santi
 */
public class JFconsulta extends javax.swing.JFrame {

    public static String rut_tab;
    TableRowSorter fil;
    /**
     * Creates new form JFconsulta
     */
    public static int pos_comb = 0;
    public static Object[] OpsCabecera = {"Id_producto", "Precio", "Nom_prod", "Marca"};

    public JFconsulta() {

        System.out.println("Existimos tabla consulta");
        setLayout(null);

        Image icon_inter = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/producto.png"));
        setIconImage(icon_inter);
        initComponents();
        
        //Modelo
        jTconsulta.setModel(new javax.swing.table.DefaultTableModel(frmiProducto.tabla_p,frmiProducto.sCabecera));
        jScrollPane1.setViewportView(jTconsulta);

        comp_consult();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });

        jCBfilt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int indice = jCBfilt.getSelectedIndex();
                pos_comb = 0;
                switch (indice) {
                    case 1:
                        pos_comb = 0;
                        break;
                    case 2:
                        pos_comb = 1;
                        break;
                    case 3:
                        pos_comb = 2;
                        break;
                    case 4:
                        pos_comb = 3;
                        break;
                    case 5:
                        pos_comb = 4;
                        break;
                        
                    case 6:
                        pos_comb = 5;
                        break;    
                }
                System.out.println("Número seleccionado: " + pos_comb);
            }
        });
        setVisible(true);

        setLocationRelativeTo(null);
        setResizable(false);

        jTconsulta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // detecta un solo clic
                    int filaSeleccionada = jTconsulta.getSelectedRow(); // obtiene la fila seleccionada
                    int columnaSeleccionada = jTconsulta.getSelectedColumn(); // obtiene la columna seleccionada
                    if (columnaSeleccionada == 6) {
                        rut_tab =jTconsulta.getValueAt(filaSeleccionada, columnaSeleccionada).toString();
                        System.out.println(rut_tab);
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                new Muestra_tab().setVisible(true);
                            }
                        });
                    }
                }
            }
        });

    }

    public void comp_consult() {
        //lbl col
        lblCol.setForeground(new java.awt.Color(0, 0, 0));
        lblCol.setFont(new Font("Arial", Font.BOLD, 14));
        lblCol.setText("Selecciona la columna");

        //lbl fil
        lblFilt.setForeground(new java.awt.Color(0, 0, 0));
        lblFilt.setFont(new Font("Arial", Font.BOLD, 14));
        lblFilt.setText("Escribe tu filtro:");

        //Jtextfield filt
        jTFilt.setForeground(Color.GRAY);
        jTFilt.setText("filtro");
        jTFilt.setColumns(5);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTconsulta = new javax.swing.JTable();
        lblCol = new javax.swing.JLabel();
        lblFilt = new javax.swing.JLabel();
        jTFilt = new javax.swing.JTextField();
        jCBfilt = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        jScrollPane3.setMaximumSize(new java.awt.Dimension(528, 245));

        jTconsulta.setModel(new javax.swing.table.DefaultTableModel(frmiProducto.tabla_p,frmiProducto.sCabecera));
        jScrollPane1.setViewportView(jTconsulta);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(jPanel1);

        lblCol.setText("jLabel1");

        lblFilt.setText("jLabel1");

        jTFilt.setText("jTextField1");
        jTFilt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTFiltFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTFiltFocusLost(evt);
            }
        });
        jTFilt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFiltKeyTyped(evt);
            }
        });

        jCBfilt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "seleccion", "Id_producto", "Precio", "Nom_prod", "Marca", "Vencimiento", "Medida" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jCBfilt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTFilt, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblCol, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFilt, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCol, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFilt))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTFilt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCBfilt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jTFiltKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFiltKeyTyped
        jTFilt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                fil.setRowFilter(RowFilter.regexFilter("(?i)" + jTFilt.getText(), pos_comb));
            }
        });

        fil = new TableRowSorter(new javax.swing.table.DefaultTableModel(frmiProducto.tabla_p, frmiProducto.sCabecera));
        jTconsulta.setRowSorter(fil);
    }//GEN-LAST:event_jTFiltKeyTyped

    private void jTFiltFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFiltFocusGained
        jTFilt.setForeground(Color.BLACK);
        jTFilt.setText("");
    }//GEN-LAST:event_jTFiltFocusGained

    private void jTFiltFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFiltFocusLost
        if (!jTFilt.getText().isEmpty()) {

        } else {
            jTFilt.setForeground(Color.GRAY);
            jTFilt.setText("filtro");
        }
    }//GEN-LAST:event_jTFiltFocusLost

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
            java.util.logging.Logger.getLogger(JFconsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFconsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFconsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFconsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jCBfilt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTFilt;
    public static javax.swing.JTable jTconsulta;
    private javax.swing.JLabel lblCol;
    private javax.swing.JLabel lblFilt;
    // End of variables declaration//GEN-END:variables
}
