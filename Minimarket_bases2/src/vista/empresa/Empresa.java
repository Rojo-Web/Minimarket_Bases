
package vista.empresa;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;


public class Empresa extends javax.swing.JInternalFrame {
    public Font typeF = new Font("Arial",Font.BOLD,16);

   public int W=135,H=254,W2=501,H2=395, W3=694,H3=134, H4=694,W4=134, W5=458,H5=78; 
    public Empresa() {
        initComponents();
        
        lbId.setText("Id");
        lbId.setFont(typeF);
        
        lblNomEmp.setText("Empresa");
        lblNit.setText("Nit");
        lblCorreo.setText("Correo");
        lblTel.setText("Telefono");
        lblDirec.setText("Direccion");
        lblFotoEmpre.setText("");
        lblFondo.setText("");
        lblFondo2.setText("");
        lblFondo3.setText("");
        
        
        txtId.setText("");
        txtNom.setText("");
        txtNit.setText("");
        txtDirec.setText("");
        txtCorreo.setText("");
        txtDirec.setText("");
        txtTel.setText("");
        
        
        
        //Botones
        //(Nuevo)
        btnNuevo.setHorizontalAlignment(SwingConstants.CENTER); //Centrar imagen
        btnNuevo.setText(""); //Quitar texto al label
        btnNuevo.setBackground(Color.decode("#7498b6")); //Color de fondo del botón
        ImageIcon iconoAñGuar = new ImageIcon(getClass().getResource("/imagenes/iconos/nota.png")); //Cargar imagen original
        Image imagenOriginal9 = iconoAñGuar.getImage(); //Obtener imagen original
        Image Redimension9 = imagenOriginal9.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH); //Redimensionar imagen
        ImageIcon iconoRedimensionado9 = new ImageIcon(Redimension9); //Crear instancia de ImageIcon con la imagen redimensionada
        btnNuevo.setIcon(iconoRedimensionado9); //Asignar imagen redimensionada al botón
        btnNuevo.setToolTipText("Añadir/Guardar");//Letrero mouse up object
        btnNuevo.setPreferredSize(new java.awt.Dimension(W3, H3));
        
        //btnEditar***************************************************************************************************************************
        btnEditar.setHorizontalAlignment(SwingConstants.CENTER); //Centrar imagen
        btnEditar.setText(""); //Quitar texto al label
        btnEditar.setBackground(Color.decode("#7498b6")); //Color de fondo del botón
        ImageIcon iconoEdit = new ImageIcon(getClass().getResource("/imagenes/iconos/editar-codigo.png")); //Cargar imagen original
        Image imagenOriginal10 = iconoEdit.getImage(); //Obtener imagen original
        Image Redimension10 = imagenOriginal10.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH); //Redimensionar imagen
        ImageIcon iconoRedimensionado10 = new ImageIcon(Redimension10); //Crear instancia de ImageIcon con la imagen redimensionada
        btnEditar.setIcon(iconoRedimensionado10); //Asignar imagen redimensionada al botón
        btnEditar.setToolTipText("Editar");//Letrero mouse up object
        
        //Eliminar*****************************************************************************************************************************
         btnEliminar.setHorizontalAlignment(SwingConstants.CENTER); //Centrar imagen
        btnEliminar.setText(""); //Quitar texto al label
        btnEliminar.setBackground(Color.decode("#7498b6")); //Color de fondo del botón
        ImageIcon iconoElim = new ImageIcon(getClass().getResource("/imagenes/iconos/expediente.png")); //Cargar imagen original
        Image imagenOriginal11 = iconoElim.getImage(); //Obtener imagen original
        Image Redimension11 = imagenOriginal11.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH); //Redimensionar imagen
        ImageIcon iconoRedimensionado11 = new ImageIcon(Redimension11); //Crear instancia de ImageIcon con la imagen redimensionada
        btnEliminar.setIcon(iconoRedimensionado11); //Asignar imagen redimensionada al botón
        btnEliminar.setToolTipText("Eliminar");
        
        //Tabla *********************************************************************************************************************************
        
        btnConsulta.setHorizontalAlignment(SwingConstants.CENTER); //Centrar imagen
        btnConsulta.setText(""); //Quitar texto al label
        btnConsulta.setBackground(Color.decode("#7498b6")); //Color de fondo del botón
        ImageIcon iconoTab = new ImageIcon(getClass().getResource("/imagenes/iconos/buscando.png")); //Cargar imagen original
        Image imagenOriginal2 = iconoTab.getImage(); //Obtener imagen original
        Image imgRedimension2 = imagenOriginal2.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH); //Redimensionar imagen
        ImageIcon iconoRedimension2 = new ImageIcon(imgRedimension2); //Crear instancia de ImageIcon con la imagen redimensionada
        btnConsulta.setIcon(iconoRedimension2); //Asignar imagen redimensionada al botón
        btnConsulta.setToolTipText("Consulta");
        
        
        //Exit********************************************************************************************************************************
        
        btnSalir.setHorizontalAlignment(SwingConstants.CENTER); //Centrar imagen
        btnSalir.setText(""); //Quitar texto al label
        btnSalir.setBackground(Color.decode("#7498b6")); //Color de fondo del botón
        ImageIcon iconoSalir = new ImageIcon(getClass().getResource("/imagenes/iconos/cerrar-sesion.png")); //Cargar imagen original
        Image imagenOriginal4 = iconoSalir.getImage(); //Obtener imagen original
        Image imgRedimension4 = imagenOriginal4.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH); //Redimensionar imagen
        ImageIcon iconoRedimension4 = new ImageIcon(imgRedimension4); //Crear instancia de ImageIcon con la imagen redimensionada
        btnSalir.setIcon(iconoRedimension4); //Asignar imagen redimensionada al botón
        btnSalir.setToolTipText("Salir");
        
        // Imagen Logo ***********************************************************************************************************************
        ImageIcon FotoEmpre = new ImageIcon(getClass().getResource("/imagenes/pic/MM1P.png")); //Cargar imagen original
        Image FotoEmpreOri = FotoEmpre.getImage(); //Obtener imagen original
        Image ReSizeFoto = FotoEmpreOri.getScaledInstance(H, W, java.awt.Image.SCALE_SMOOTH); //Redimensionar imagen
        ImageIcon redimesion = new ImageIcon(ReSizeFoto); //Crear instancia de ImageIcon con la imagen redimensionada
        lblFotoEmpre.setIcon(redimesion); //Asignar imagen redimensionada al botón
        lblFotoEmpre.setToolTipText("Foto");//Letrero mouse up object
        
        // BakcGround
        //FONDO 1
        ImageIcon fondo = new ImageIcon(getClass().getResource("/imagenes/pic/fondo.jpg")); // Cargar imagen original
        Image imagenOri = fondo.getImage(); // Obtener imagen original
        Image reSize = imagenOri.getScaledInstance(W2, H2, Image.SCALE_SMOOTH); // Redimensionar imagen al tamaño del JLabel
        ImageIcon imagenFondo = new ImageIcon(reSize); // Crear instancia de ImageIcon con la imagen redimensionada
        JLabel lblFondo = new JLabel(imagenFondo);
        lblFondo.setBounds(0, 0, W2, H2); // Establecer el tamaño del JLabel para que cubra todo el JInternalFrame
        getContentPane().add(lblFondo); // Agregar el JLabel al contenido del JInternalFrame
        
        //FONDO 2
        ImageIcon fondo2 = new ImageIcon(getClass().getResource("/imagenes/pic/fondo2.jpg")); // Cargar imagen original
        Image imagenOri2 = fondo2.getImage(); // Obtener imagen original
        Image reSize2 = imagenOri2.getScaledInstance(W4, H4, Image.SCALE_SMOOTH); // Redimensionar imagen al tamaño del JLabel
        ImageIcon imgFondo2 = new ImageIcon(reSize2); // Crear instancia de ImageIcon con la imagen redimensionada
        JLabel lblFondo2 = new JLabel(imgFondo2);
        lblFondo2.setBounds(0, 0, W4, H4); // Establecer el tamaño del JLabel para que cubra todo el JInternalFrame
        jPanRecursos2.add(lblFondo2); // Agregar el JLabel al contenido del JInternalFrame
        
         Color colorBorde = new Color (214, 215, 216); // Color del borde
        int grosorBorde = 3; // Grosor del borde
        Border bordeGrueso = BorderFactory.createLineBorder(colorBorde, grosorBorde);//color aplicado al borde
        lblFondo2.setBorder(bordeGrueso);
       
        //FONDO 3
        ImageIcon fondo3 = new ImageIcon(getClass().getResource("/imagenes/pic/fondo5.jpg")); // Cargar imagen original
        Image imagenOri3 = fondo3.getImage(); // Obtener imagen original
        Image reSize3 = imagenOri3.getScaledInstance(W5, H5, Image.SCALE_SMOOTH); // Redimensionar imagen al tamaño del JLabel
        ImageIcon imgFondo3 = new ImageIcon(reSize3); // Crear instancia de ImageIcon con la imagen redimensionada
        JLabel lblFondo3 = new JLabel(imgFondo3);
        lblFondo3.setBounds(0, 0, W5, H5); // Establecer el tamaño del JLabel para que cubra todo el JInternalFrame
        jpGrupo.add(lblFondo3); // Agregar el JLabel al contenido del JInternalFrame
        
        Color color = new Color(227, 232, 239);//color fondo panel grupal
        jpGrupo.setBackground(color);
        lblFondo3.setBorder(bordeGrueso);
        
        
        

     

       
        
        
        // Establecer el tamaño del JInternalFrame
        setSize(501, 395);
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtDirec = new javax.swing.JTextField();
        lblDirec = new javax.swing.JLabel();
        lblFotoEmpre = new javax.swing.JLabel();
        jPanRecursos2 = new javax.swing.JPanel();
        jpGrupo = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnConsulta = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblFondo3 = new javax.swing.JLabel();
        lblFondo2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblNit = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtTel = new javax.swing.JTextField();
        lblTel = new javax.swing.JLabel();
        lblNomEmp = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        lbId = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(501, 395));

        txtDirec.setText("jTextField1");
        txtDirec.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDirecFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDirecFocusLost(evt);
            }
        });

        lblDirec.setText("jLabel1");

        lblFotoEmpre.setText("jLabel1");

        btnNuevo.setText("jButton1");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEliminar.setText("jButton1");

        btnEditar.setText("jButton1");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnConsulta.setText("jButton1");
        btnConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaActionPerformed(evt);
            }
        });

        btnSalir.setText("jButton1");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        lblFondo3.setText("jLabel2");

        javax.swing.GroupLayout jpGrupoLayout = new javax.swing.GroupLayout(jpGrupo);
        jpGrupo.setLayout(jpGrupoLayout);
        jpGrupoLayout.setHorizontalGroup(
            jpGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGrupoLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFondo3)
                .addGap(18, 18, 18)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jpGrupoLayout.setVerticalGroup(
            jpGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGrupoLayout.createSequentialGroup()
                .addGroup(jpGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpGrupoLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(lblFondo3))
                    .addGroup(jpGrupoLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jpGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        lblFondo2.setText("jLabel2");

        javax.swing.GroupLayout jPanRecursos2Layout = new javax.swing.GroupLayout(jPanRecursos2);
        jPanRecursos2.setLayout(jPanRecursos2Layout);
        jPanRecursos2Layout.setHorizontalGroup(
            jPanRecursos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanRecursos2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanRecursos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanRecursos2Layout.createSequentialGroup()
                        .addComponent(jpGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanRecursos2Layout.createSequentialGroup()
                        .addComponent(lblFondo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(650, 650, 650))))
        );
        jPanRecursos2Layout.setVerticalGroup(
            jPanRecursos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanRecursos2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFondo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        txtId.setText("jTextField1");
        txtId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIdFocusLost(evt);
            }
        });

        lblNit.setText("jLabel1");

        lblCorreo.setText("jLabel1");

        txtNit.setText("jTextField1");
        txtNit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNitFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNitFocusLost(evt);
            }
        });

        txtCorreo.setText("jTextField1");
        txtCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCorreoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCorreoFocusLost(evt);
            }
        });
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });

        txtTel.setText("jTextField1");
        txtTel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTelFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTelFocusLost(evt);
            }
        });

        lblTel.setText("jLabel1");

        lblNomEmp.setText("jLabel1");

        txtNom.setText("jTextField1");
        txtNom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNomFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNomFocusLost(evt);
            }
        });

        lbId.setText("jLabel1");

        lblFondo.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbId, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtDirec)
                                .addComponent(lblDirec, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNom)
                                .addComponent(lblNomEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNit)
                                .addComponent(lblNit, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFondo)
                        .addGap(77, 77, 77)
                        .addComponent(lblFotoEmpre, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanRecursos2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFondo)
                    .addComponent(lblFotoEmpre, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lbId)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblDirec)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtDirec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblNit)
                            .addGap(4, 4, 4)
                            .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTel)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(17, 17, 17)
                                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNomEmp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCorreo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanRecursos2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelFocusLost

    }//GEN-LAST:event_txtTelFocusLost

    private void txtTelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelFocusGained

    }//GEN-LAST:event_txtTelFocusGained

    private void txtDirecFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDirecFocusLost

    }//GEN-LAST:event_txtDirecFocusLost

    private void txtDirecFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDirecFocusGained

    }//GEN-LAST:event_txtDirecFocusGained

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void txtCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusLost

    }//GEN-LAST:event_txtCorreoFocusLost

    private void txtCorreoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusGained

    }//GEN-LAST:event_txtCorreoFocusGained

    private void txtNitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNitFocusLost

    }//GEN-LAST:event_txtNitFocusLost

    private void txtNitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNitFocusGained

    }//GEN-LAST:event_txtNitFocusGained

    private void txtIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdFocusLost

    }//GEN-LAST:event_txtIdFocusLost

    private void txtIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdFocusGained

    }//GEN-LAST:event_txtIdFocusGained

    private void txtNomFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomFocusLost

    }//GEN-LAST:event_txtNomFocusLost

    private void txtNomFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomFocusGained

    }//GEN-LAST:event_txtNomFocusGained

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed

    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed

    }//GEN-LAST:event_btnConsultaActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

    }//GEN-LAST:event_btnNuevoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsulta;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanRecursos2;
    private javax.swing.JPanel jpGrupo;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDirec;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblFondo2;
    private javax.swing.JLabel lblFondo3;
    private javax.swing.JLabel lblFotoEmpre;
    private javax.swing.JLabel lblNit;
    private javax.swing.JLabel lblNomEmp;
    private javax.swing.JLabel lblTel;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDirec;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
