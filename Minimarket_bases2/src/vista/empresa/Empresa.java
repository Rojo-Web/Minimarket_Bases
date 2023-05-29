package vista.empresa;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import Modelo.MySQL;
import static Modelo.MySQL.Conexion;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Empresa extends javax.swing.JInternalFrame {

    //Sebas
    public Font typeF = new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 16);
    //***********************

    public static int contador = 0, contador_eli = 0, cont_fil = 0, cont_fil_nav = 0, cont_flec = 0, cont_label = 0, columna = 0, cont_filM = 0, cant_med = 0, cant_clies = 0;
    public final int ancho = 15, alto = 15;
    public static String selec_med = "", selec_estado = "", combo_result = "";

    private static String Tabla = "empresa", ID = "ID_E";
    private static boolean veri_Bedit = false, bloq_grup = false, access = false;
    public static int contador_edit = 0;

    //Tabla
    public static String[] sCabecera = {"ID_E", "nom_empresa", "correo", "direccion", "telefono", "NIT"};
    public static String[][] tablaEmp;

    public int W = 135, H = 254, W2 = 501, H2 = 395, W3 = 694, H3 = 134, H4 = 694, W4 = 134, W5 = 458, H5 = 78;

    public Empresa() {
        initComponents();

        

        lblNomEmp.setText("Empresa");
        lblNomEmp.setFont(typeF);
        lblNomEmp.setForeground(new Color(240, 113, 1));

        lblNit.setText("Nit");
        lblNit.setFont(typeF);
        lblNit.setForeground(new Color(240, 113, 1));

        lblCorreo.setText("Correo");
        lblCorreo.setFont(typeF);
        lblCorreo.setForeground(new Color(240, 113, 1));

        lblTel.setText("Telefono");
        lblTel.setFont(typeF);
        lblTel.setForeground(new Color(240, 113, 1));

        lblDirec.setText("Direccion");
        lblDirec.setFont(typeF);
        lblDirec.setForeground(new Color(240, 113, 1));

        lblFotoEmpre.setText("");
        lblFotoEmpre.setFont(typeF);
        lblFotoEmpre.setForeground(new Color(240, 113, 1));

        lblFondo.setText("");
        lblFondo.setFont(typeF);
        lblFondo.setForeground(new Color(240, 113, 1));

        lblFondo2.setText("");
        lblFondo2.setFont(typeF);
        lblFondo2.setForeground(new Color(240, 113, 1));

        lblFondo3.setText("");
        lblFondo3.setFont(typeF);
        lblFondo3.setForeground(new Color(240, 113, 1));

//        txtId.setText("");
//        txtNom.setText("");
//        txtNit.setText("");
//        txtDirec.setText("");
//        txtCorreo.setText("");
//        txtDirec.setText("");
//        txtTel.setText("");
        Placeholders();
        
        //Por si ya hay una empresa
        int can_reg=MySQL.cantRegistros(Tabla, ID);
        if (can_reg==1) {
            btnNuevo.setEnabled(false);
            
            try {
                PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("SELECT * FROM " + Tabla + " where ID_E = 1;");

                ResultSet res = (ResultSet) stp.executeQuery();

                if (res.next()) {

                    txtNom.setText(res.getString("nom_empresa"));
                    txtCorreo.setText(res.getString("correo"));
                    txtDirec.setText(res.getString("direccion"));
                    txtTel.setText(res.getString("telefono"));
                    txtNit.setText(res.getString("NIT"));


                }
            } catch (SQLException ex) {
                Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

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

        //GUIA********************************************************************************************************************************
        jBGuia.setHorizontalAlignment(SwingConstants.CENTER); //Centrar imagen
        jBGuia.setText(""); //Quitar texto al label
        jBGuia.setBackground(Color.decode("#7498b6")); //Color de fondo del botón
        ImageIcon iconoGuia = new ImageIcon(getClass().getResource("/imagenes/manual.png")); //Cargar imagen original
        Image imagenOriginal5 = iconoGuia.getImage(); //Obtener imagen original
        Image imgRedimension5 = imagenOriginal5.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH); //Redimensionar imagen
        ImageIcon iconoRedimension5 = new ImageIcon(imgRedimension5); //Crear instancia de ImageIcon con la imagen redimensionada
        jBGuia.setIcon(iconoRedimension5); //Asignar imagen redimensionada al botón
        jBGuia.setToolTipText("Guia");

        //Impresion********************************************************************************************************************************
        btnImpr.setHorizontalAlignment(SwingConstants.CENTER); //Centrar imagen
        btnImpr.setText(""); //Quitar texto al label
        btnImpr.setBackground(Color.decode("#7498b6")); //Color de fondo del botón
        ImageIcon iconoImpr = new ImageIcon(getClass().getResource("/imagenes/impresora.png")); //Cargar imagen original
        Image imagenOriginal6 = iconoImpr.getImage(); //Obtener imagen original
        Image imgRedimension6 = imagenOriginal6.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH); //Redimensionar imagen
        ImageIcon iconoRedimension6 = new ImageIcon(imgRedimension6); //Crear instancia de ImageIcon con la imagen redimensionada
        btnSalir.setIcon(iconoRedimension4); //Asignar imagen redimensionada al botón
        btnSalir.setToolTipText("Imprimir");

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

        Color colorBorde = new Color(214, 215, 216); // Color del borde
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

        txtCorreo.setEnabled(false);
        txtDirec.setEnabled(false);
        txtNit.setEnabled(false);
        txtNom.setEnabled(false);
        txtTel.setEnabled(false);

    }

    public void Placeholders() {
        //Caajas de texto


        txtNom.setForeground(Color.GRAY);
        txtNom.setText("Nombre");
        txtNom.setColumns(5);

        txtCorreo.setForeground(Color.GRAY);
        txtCorreo.setText("hola@cecep.com");
        txtCorreo.setColumns(5);

        txtDirec.setForeground(Color.GRAY);
        txtDirec.setText("Direccion");
        txtDirec.setColumns(5);

        txtTel.setForeground(Color.GRAY);
        txtTel.setText("Telefono");
        txtTel.setColumns(5);

        txtNit.setForeground(Color.GRAY);
        txtNit.setText("2345-1");
        txtNit.setColumns(5);

    }

    public void rec_dat() {
        if ( txtNom.getText().equals("Nombres") || txtCorreo.getText().equals("hola@cecep.com")
                || txtDirec.getText().equals("Dirección") || txtTel.getText().equals("Telefono") || txtNit.getText().equals("2345-1")) {
            access = false;
        } else {
            access = true;
        }
    }

    public void bloq_grup() {
        if (bloq_grup == true) {
            btnImpr.setEnabled(false);
            btnConsulta.setEnabled(false);
            btnEditar.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnSalir.setEnabled(false);
            jBGuia.setEnabled(false);

        } else {
            btnImpr.setEnabled(true);
            btnConsulta.setEnabled(true);
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnSalir.setEnabled(true);
            jBGuia.setEnabled(true);
        }
    }

    public void ingreso() {
        contador++;

        if (contador % 2 == 0) {
            if (access != false) {
                if (veri_Bedit == true) {

                    MySQL.edit_emp(txtNom.getText(), txtCorreo.getText(), txtDirec.getText(), txtTel.getText(), txtNit.getText());
                    veri_Bedit = false;
                    access=false;

                    //Bloquear campos
                    btnNuevo.setEnabled(false);
                    txtCorreo.setEnabled(false);
                    txtDirec.setEnabled(false);
                    txtNit.setEnabled(false);
                    txtNom.setEnabled(false);
                    txtTel.setEnabled(false);

                    //Cajas de color gris en texto
                    txtCorreo.setForeground(Color.GRAY);
                    txtDirec.setForeground(Color.GRAY);
                    txtNit.setForeground(Color.GRAY);
                    txtNom.setForeground(Color.GRAY);
                    txtTel.setForeground(Color.GRAY);

                    // establece el icono en el botón
                    ImageIcon foto = new ImageIcon(getClass().getResource("/imagenes/iconos/nota.png"));
                    ImageIcon mitad_1 = new ImageIcon(foto.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
                    btnNuevo.setIcon(mitad_1);

                    ImageIcon foto_edit2 = new ImageIcon(getClass().getResource("/imagenes/iconos/editar-codigo.png"));
                    ImageIcon mitad_edit2 = new ImageIcon(foto_edit2.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
                    // establece el icono en el botón
                    btnEditar.setIcon(mitad_edit2);

                    contador_edit = 0;

                } else {
                    //Desbloque el grupo de botones
                    bloq_grup = false;
                    bloq_grup();

                    txtCorreo.setEnabled(false);
                    txtDirec.setEnabled(false);
                    txtNit.setEnabled(false);
                    txtNom.setEnabled(false);
                    txtTel.setEnabled(false);

                    // establece el icono en el botón
                    ImageIcon foto = new ImageIcon(getClass().getResource("/imagenes/iconos/nota.png"));
                    ImageIcon mitad_1 = new ImageIcon(foto.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
                    btnNuevo.setIcon(mitad_1);

                    //Enviar los datos
                    MySQL.insert_emp(Tabla, txtNom.getText(), txtCorreo.getText(), txtDirec.getText(), txtTel.getText(), txtNit.getText());

                    btnNuevo.setToolTipText("Ingreso");

                    //Esto por si solo hay un dato
                    int cont_empr = MySQL.cantRegistros(Tabla, ID);
                    if (cont_empr == 1) {
                        btnNuevo.setEnabled(false);
                    }

                    MySQL.closeConnection();
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "Aun hay campos sin llenar !Completalos!", "Error de insercion", JOptionPane.WARNING_MESSAGE);
                contador--;
            }

        } else {

            if (veri_Bedit == true) {
                btnNuevo.setEnabled(true);
                
                txtCorreo.setEnabled(true);
                txtDirec.setEnabled(true);
                txtNit.setEnabled(true);
                txtNom.setEnabled(true);
                txtTel.setEnabled(true);
                ImageIcon foto = new ImageIcon(getClass().getResource("/imagenes/iconos/disco-flexible.png"));
                ImageIcon mitad_1 = new ImageIcon(foto.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
                // establece el icono en el botón
                btnNuevo.setIcon(mitad_1);
                btnNuevo.setToolTipText("Guardar");

                txtCorreo.setForeground(Color.BLACK);
                txtDirec.setForeground(Color.BLACK);
                txtNit.setForeground(Color.BLACK);
                txtNom.setForeground(Color.BLACK);
                txtTel.setForeground(Color.BLACK);

            } else {

                //Para que se bloquen los botones
                bloq_grup = true;
                bloq_grup();

                //Volver a poner vacios los jtextfield
                txtNom.setText("");
                txtNit.setText("");
                txtDirec.setText("");
                txtCorreo.setText("");
                txtDirec.setText("");
                txtTel.setText("");

                Placeholders();

                txtCorreo.setEnabled(true);
                txtDirec.setEnabled(true);
                txtNit.setEnabled(true);
                txtNom.setEnabled(true);
                txtTel.setEnabled(true);
                ImageIcon foto = new ImageIcon(getClass().getResource("/imagenes/iconos/disco-flexible.png"));
                ImageIcon mitad_1 = new ImageIcon(foto.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

                // establece el icono en el botón
                btnNuevo.setIcon(mitad_1);
                btnNuevo.setToolTipText("Guardar");

                //Esto para que la condicion del bloque del boton cobre sentido
                access = false;

                rec_dat();
            }
        }
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
        jBGuia = new javax.swing.JButton();
        btnImpr = new javax.swing.JButton();
        lblFondo2 = new javax.swing.JLabel();
        lblNit = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtTel = new javax.swing.JTextField();
        lblTel = new javax.swing.JLabel();
        lblNomEmp = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
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
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

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

        jBGuia.setText("jButton1");
        jBGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuiaActionPerformed(evt);
            }
        });

        btnImpr.setText("jButton1");
        btnImpr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpGrupoLayout = new javax.swing.GroupLayout(jpGrupo);
        jpGrupo.setLayout(jpGrupoLayout);
        jpGrupoLayout.setHorizontalGroup(
            jpGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGrupoLayout.createSequentialGroup()
                .addGroup(jpGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGrupoLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(lblFondo3))
                    .addGroup(jpGrupoLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnImpr, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jpGrupoLayout.setVerticalGroup(
            jpGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGrupoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jpGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnImpr, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(lblFondo3, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        lblFondo.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDirec)
                            .addComponent(lblDirec, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblFondo)
                                .addGap(77, 77, 77)
                                .addComponent(lblFotoEmpre, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNom)
                                    .addComponent(lblNomEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNit)
                                    .addComponent(lblNit, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanRecursos2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 489, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFondo)
                    .addComponent(lblFotoEmpre, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblNit)
                        .addGap(4, 4, 4)
                        .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNomEmp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblDirec)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDirec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTel)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblCorreo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 24, Short.MAX_VALUE)
                .addComponent(jPanRecursos2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelFocusLost
        if (!txtTel.getText().isEmpty()) {

        } else {
            txtTel.setForeground(Color.GRAY);
            txtTel.setText("Telefono");
            rec_dat();
        }
    }//GEN-LAST:event_txtTelFocusLost

    private void txtTelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelFocusGained
        if (txtTel.getText().equals("Telefono")) {
            txtTel.setForeground(Color.BLACK);
            txtTel.setText("");
            rec_dat();
        }
    }//GEN-LAST:event_txtTelFocusGained

    private void txtDirecFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDirecFocusLost
        if (!txtDirec.getText().isEmpty()) {

        } else {
            txtDirec.setForeground(Color.GRAY);
            txtDirec.setText("Direccion");
            rec_dat();
        }
    }//GEN-LAST:event_txtDirecFocusLost

    private void txtDirecFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDirecFocusGained
        if (txtDirec.getText().equals("Direccion")) {
            txtDirec.setForeground(Color.BLACK);
            txtDirec.setText("");
            rec_dat();
        }
    }//GEN-LAST:event_txtDirecFocusGained

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void txtCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusLost
        if (!txtCorreo.getText().isEmpty()) {

        } else {
            txtCorreo.setForeground(Color.GRAY);
            txtCorreo.setText("hola@cecep.com");
            rec_dat();
        }
    }//GEN-LAST:event_txtCorreoFocusLost

    private void txtCorreoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusGained
        if (txtCorreo.getText().equals("hola@cecep.com")) {
            txtCorreo.setForeground(Color.BLACK);
            txtCorreo.setText("");
            rec_dat();
        }
    }//GEN-LAST:event_txtCorreoFocusGained

    private void txtNitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNitFocusLost
        if (!txtNit.getText().isEmpty()) {

        } else {
            txtNit.setForeground(Color.GRAY);
            txtNit.setText("2345-1");
            rec_dat();
        }
    }//GEN-LAST:event_txtNitFocusLost

    private void txtNitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNitFocusGained
        if (txtNit.getText().equals("2345-1")) {
            txtNit.setForeground(Color.BLACK);
            txtNit.setText("");
            rec_dat();
        }
    }//GEN-LAST:event_txtNitFocusGained

    private void txtNomFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomFocusLost
        if (!txtNom.getText().isEmpty()) {

        } else {
            txtNom.setForeground(Color.GRAY);
            txtNom.setText("Nombre");
            rec_dat();
        }
    }//GEN-LAST:event_txtNomFocusLost

    private void txtNomFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomFocusGained
        if (txtNom.getText().equals("Nombre")) {
            txtNom.setForeground(Color.BLACK);
            txtNom.setText("");
            rec_dat();
        }
    }//GEN-LAST:event_txtNomFocusGained

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        setVisible(false);
        contador = 0;
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed

        MySQL.MySQLConnection("root", "", "minimarket");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            System.out.println("query");
            String query = "SELECT * FROM empresa";
            connection = (Connection) Conexion;
            statement = (Statement) connection.createStatement();

            resultSet = (ResultSet) statement.executeQuery(query);

            ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            int rowCount = 0;

            System.out.println("avance 2");
            if (resultSet.last()) {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst(); // Regresar al inicio del conjunto de resultados
            }

            System.out.println(rowCount);

            //int columnCount = 8; // Número de columnas en la tabla
            tablaEmp = new String[rowCount][columnCount];

            int row = 0;

            System.out.println("avance 3");
            while (resultSet.next()) {
                System.out.println("avance 4");
                for (int col = 0; col < columnCount; col++) {
                    tablaEmp[row][col] = resultSet.getObject(col + 1).toString();

                }

                row++;

            }
            // Resto del código para almacenar los datos en una matriz
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cierre de recursos (resultSet, statement, connection)
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFconsultaEmpr().setVisible(true);
            }
        });

    }//GEN-LAST:event_btnConsultaActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        contador_edit++;

        if (contador_edit % 2 == 0) {

            System.out.println("entre 2");
            //segundo toque en caso de que no quiera hacer la edicion 
            //que bloque y boolean de confirmacion para saber asi que no le resta al contador
            //cambio de icono
            ImageIcon foto_edit2 = new ImageIcon(getClass().getResource("/imagenes/iconos/editar-codigo.png"));
            ImageIcon mitad_edit2 = new ImageIcon(foto_edit2.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
            // establece el icono en el botón
            btnEditar.setIcon(mitad_edit2);

            //Bloquear campos
            btnNuevo.setEnabled(false);
            txtCorreo.setEnabled(false);
            txtDirec.setEnabled(false);
            txtNit.setEnabled(false);
            txtNom.setEnabled(false);
            txtTel.setEnabled(false);

            //contador en 2 para que cuando vuelvan a darle a ingresar siga correctamente
            //veri_Bedit en false para que no active las condiciones
            veri_Bedit = false;
            contador = 2;
            access=false;

            //le restablecemos el icono de ingreso al boton
            ImageIcon foto = new ImageIcon(getClass().getResource("/imagenes/iconos/nota.png"));
            ImageIcon mitad_1 = new ImageIcon(foto.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

            // establece el icono en el botón
            btnNuevo.setIcon(mitad_1);

            //Cajas de color gris en texto
            txtCorreo.setForeground(Color.GRAY);
            txtDirec.setForeground(Color.GRAY);
            txtNit.setForeground(Color.GRAY);
            txtNom.setForeground(Color.GRAY);
            txtTel.setForeground(Color.GRAY);

            //Reintegracion 
            try {
                PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("SELECT * FROM " + Tabla + " where ID_E = 1;");

                ResultSet res = (ResultSet) stp.executeQuery();

                if (res.next()) {

                    txtNom.setText(res.getString("nom_empresa"));
                    txtCorreo.setText(res.getString("correo"));
                    txtDirec.setText(res.getString("direccion"));
                    txtTel.setText(res.getString("telefono"));
                    txtNit.setText(res.getString("NIT"));


                }
            } catch (SQLException ex) {
                Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            System.out.println("entre 1");
            ImageIcon foto_edit = new ImageIcon(getClass().getResource("/imagenes/deshacer.png"));
            ImageIcon mitad_edit = new ImageIcon(foto_edit.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
            // establece el icono en el botón
            btnEditar.setIcon(mitad_edit);

            //Primer toque
            contador = 0;
            veri_Bedit = true;
            access=true;
            

            ingreso();
        }

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        ingreso();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void jBGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuiaActionPerformed
        File file = new File("C:/Users/santi/Downloads/Segundo consolidado/Minimarket_Bases/Minimarket_bases2/src/imagenes/GuiaEmpresa.pdf");
        if (Desktop.isDesktopSupported()) {
            Desktop escritorio = Desktop.getDesktop();
            try {
                escritorio.open(file);
            } catch (IOException ex) {
                //ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al abrir el Documento");
            }
        }
    }//GEN-LAST:event_jBGuiaActionPerformed

    private void btnImprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImprActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        
        int borrado = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea borrar esta empresa?", "Eliminar", JOptionPane.OK_CANCEL_OPTION);
        if (borrado == 0) {
            MySQL.deleteRecord(Tabla, ID, 1);

            JOptionPane.showMessageDialog(null, "Empresa eliminada con exito", "Registro eliminado", JOptionPane.INFORMATION_MESSAGE);
            btnNuevo.setEnabled(true);
            
            txtCorreo.setText("");
            txtDirec.setText("");
            txtNit.setText("");
            txtNom.setText("");
            txtTel.setText("");
        }
        if (borrado == 2) {
        }
        
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsulta;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnImpr;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton jBGuia;
    private javax.swing.JPanel jPanRecursos2;
    private javax.swing.JPanel jpGrupo;
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
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
