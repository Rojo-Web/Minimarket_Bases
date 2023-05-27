/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vista.Producto;

import Modelo.MySQL;
import static Modelo.MySQL.Conexion;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import vista.frmiPrincipal;
import static vista.frmiPrincipal.nFils;

/**
 *
 * @author santi
 */
public class frmiProducto extends javax.swing.JInternalFrame {

    public static int contador = 0, contador_eli = 0, cont_fil = 0, cont_fil_nav = 0, cont_flec = 0, cont_label = 0, columna = 0, cont_filM = 0, cant_med = 0, cant_prods = 0;
    public final int ancho = 15, alto = 15;
    public static String selec_med = "", combo_result = "";
    private static String Tabla = "productos", ID = "id_prod";

    //Tabla de consulta
    public static String[] sCabecera = {"Id_producto", "Precio", "Nom_prod", "Marca", "Vence", "Medida", "Ruta_foto"};
    public static String[][] tabla_p = new String[frmiPrincipal.nFils][7];
    public static String[][] Datos_p = new String[frmiPrincipal.nFils][7];
    public static File archivo;
    //final de tabla

    //Fecha de vencimiento
    public static String fechaV = "";
    public static JDateChooser dateChooser;

    //Vector de iconos
    //                                  0                           1                       2                           3                       4                       5                       6
    public String[] ruta_img = {"/imagenes/nota.png", "/imagenes/disco-flexible.png", "/imagenes/producto.png", "/imagenes/Ultima.png", "/imagenes/anterior.png", "/imagenes/siguiente.png", "/imagenes/primera.png",
        "/imagenes/editar-codigo.png", "/imagenes/expediente.png", "/imagenes/manual.png", "/imagenes/buscando.png", "/imagenes/impresora.png", "/imagenes/cerrar-sesion.png", "/imagenes/camara.png", "/imagenes/salida.png"};

    //                 7                               8                           9                  10                      11                          12                            13                          14
    /**
     * Creates new form frmiProducto
     */
    public frmiProducto() {

        setTitle("Tabla Producto");
        //Para que no se cierre si cierra otra
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //arrastre 
//        setBorder(null); // Eliminar el borde de arrastre
//        setClosable(false); // Deshabilitar cierre
//        setIconifiable(false);

        initComponents();

        setSize(501, 395);
        jBVenci.setEnabled(false);
        setResizable(false); // Deshabilitar el redimensionamiento
        setLocation(0, 0);

        componets();

        //Pjolder();
    }

    public void componets() {
        //**********COMPONENTES************
        //Labels
        //lblprecio
        jlPrecio.setForeground(new java.awt.Color(0, 0, 0));
        jlPrecio.setFont(new Font("Arial", Font.BOLD, 14));
        jlPrecio.setText("Precio_c/u");
        //lblMarca
        jlMarca.setForeground(new java.awt.Color(0, 0, 0));
        jlMarca.setFont(new Font("Arial", Font.BOLD, 14));
        jlMarca.setText("Marca");
        //lblNombre
        jlNom_prod.setForeground(new java.awt.Color(0, 0, 0));
        jlNom_prod.setFont(new Font("Arial", Font.BOLD, 14));
        jlNom_prod.setText("Nombre");
        //lblNav
        jlNav.setForeground(new java.awt.Color(0, 0, 0));
        jlNav.setFont(new Font("Arial", Font.BOLD, 14));
        jlNav.setText("Nav");
        //lblmmto
        jLCrud.setForeground(new java.awt.Color(0, 0, 0));
        jLCrud.setFont(new Font("Arial", Font.BOLD, 14));
        jLCrud.setText("Mtto");
        //lblFN
        jLFn.setForeground(new java.awt.Color(0, 0, 0));
        jLFn.setFont(new Font("Arial", Font.BOLD, 14));
        jLFn.setText("Fn Especiales");
        //lblVenci
        jLVenci.setForeground(new java.awt.Color(0, 0, 0));
        jLVenci.setFont(new Font("Arial", Font.BOLD, 14));
        jLVenci.setText("Fecha de vencimiento");
        //lblMedida
        jLmedida.setForeground(new java.awt.Color(0, 0, 0));
        jLmedida.setFont(new Font("Arial", Font.BOLD, 14));
        jLmedida.setText("Medida de venta");
        //lblFoto
        jlFoto.setForeground(new java.awt.Color(0, 0, 0));
        jlFoto.setFont(new Font("Arial", Font.BOLD, 14));
        jlFoto.setText("Foto");
        //lbl cantidad
        jLcantidad.setForeground(new java.awt.Color(0, 0, 0));
        jLcantidad.setFont(new Font("Arial", Font.BOLD, 14));
        jLcantidad.setText("Cantidad:");

        //lblinfo
        cant_prods = MySQL.cantRegistros(Tabla, ID);
        System.out.println("hola cantida_prd: " + cant_prods);

        cont_label = 0;
        jLInfo.setText("Tabla: Producto registro " + cont_label + " al " + (cant_prods));
        //lblFech
        //jLfech.setT
        jLfech.setForeground(new java.awt.Color(255, 0, 0));
        jLfech.setFont(new Font("Arial", Font.PLAIN, 14));
        jLfech.setText("");
        //Fecha
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String fechaConHora = formatter.format(new Date());
                jLfech.setText(fechaConHora);
            }
        });
        timer.start();

        //Iconos de los botonos
        //mtto
        //Btn ing
        ImageIcon foto = new ImageIcon(getClass().getResource(ruta_img[0]));
        ImageIcon mitad_1 = new ImageIcon(foto.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        // establece el icono en el botón
        jBIng.setIcon(mitad_1);
        jBIng.setToolTipText("Ingreso");

        //Btn Edit
        ImageIcon foto_edit = new ImageIcon(getClass().getResource(ruta_img[7]));
        ImageIcon mitad_edit = new ImageIcon(foto_edit.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        // establece el icono en el botón
        jBEdit.setIcon(mitad_edit);
        jBEdit.setToolTipText("Editar");

        //Btn Eli
        ImageIcon foto_eli = new ImageIcon(getClass().getResource(ruta_img[8]));
        ImageIcon mitad_eli = new ImageIcon(foto_eli.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        // establece el icono en el botón
        jBEli.setIcon(mitad_eli);
        jBEli.setToolTipText("Eliminar");

        //nav
        //btn Ultimo
        ImageIcon foto_ult = new ImageIcon(getClass().getResource(ruta_img[6]));
        ImageIcon mitad_2 = new ImageIcon(foto_ult.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        // establece el icono en el botón
        jBUltim.setIcon(mitad_2);
        jBUltim.setToolTipText("Dato mas reciente");

        //btn Anterior
        ImageIcon foto_ant = new ImageIcon(getClass().getResource(ruta_img[4]));
        ImageIcon mitad_3 = new ImageIcon(foto_ant.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        // establece el icono en el botón
        jBAnterior.setIcon(mitad_3);
        jBAnterior.setToolTipText("Dato anterior");

        //btn Siguiente
        ImageIcon foto_sig = new ImageIcon(getClass().getResource(ruta_img[5]));
        ImageIcon mitad_4 = new ImageIcon(foto_sig.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        // establece el icono en el botón
        jBSigui.setIcon(mitad_4);
        jBSigui.setToolTipText("Siguiente dato");

        //btn Siguiente
        ImageIcon foto_Primer = new ImageIcon(getClass().getResource(ruta_img[3]));
        ImageIcon mitad_5 = new ImageIcon(foto_Primer.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        // establece el icono en el botón
        jBPrimer1.setIcon(mitad_5);
        jBPrimer1.setToolTipText("Ultimo dato");

        //Fn
        //btn Guia
        ImageIcon foto_guia = new ImageIcon(getClass().getResource(ruta_img[9]));
        ImageIcon mitad_guia = new ImageIcon(foto_guia.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        // establece el icono en el botón
        jBGuia.setIcon(mitad_guia);
        jBGuia.setToolTipText("Guia");

        //btn consul
        ImageIcon foto_consul = new ImageIcon(getClass().getResource(ruta_img[10]));
        ImageIcon mitad_consul = new ImageIcon(foto_consul.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        // establece el icono en el botón
        jBConsulta.setIcon(mitad_consul);
        jBConsulta.setToolTipText("Consulta");

        //btn Impr
        ImageIcon foto_impr = new ImageIcon(getClass().getResource(ruta_img[11]));
        ImageIcon mitad_impr = new ImageIcon(foto_impr.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        // establece el icono en el botón
        JBImpr.setIcon(mitad_impr);
        JBImpr.setToolTipText("Impresion de datos");

        //btn exit
        ImageIcon foto_exit = new ImageIcon(getClass().getResource(ruta_img[12]));
        ImageIcon mitad_exit = new ImageIcon(foto_exit.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        // establece el icono en el botón
        jBExit.setIcon(mitad_exit);

        //Imagen del tooltip text
        jBExit.setToolTipText("<html>" + "<img src=\"" + getClass().getResource(ruta_img[14]) + "\" width=\"50\" height=\"30\" ></html>");

        //btn Imagen
        ImageIcon foto_img = new ImageIcon(getClass().getResource(ruta_img[13]));
        ImageIcon mitad_img = new ImageIcon(foto_img.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        // establece el icono en el botón
        jbImg.setIcon(mitad_img);
        jbImg.setToolTipText("Foto");

        //btn fechVenci
        dateChooser = new JDateChooser();

        jBVenci.add(dateChooser);

//        //Bloqueo del nav
//        jBSigui.setEnabled(false);
        jBAnterior.setEnabled(false);
        jBPrimer1.setEnabled(false);
//        jBUltim.setEnabled(false);

        //Panel principal Bloqueado
        jtMarca.setEnabled(false);
        jtNom_prod.setEnabled(false);
        jtPrecio.setEnabled(false);
        jbImg.setEnabled(false);
        dateChooser.setEnabled(false);
        jCBmedida.setEnabled(false);
        jTcantidad.setEnabled(false);

    }

    public void Pjolder() {
        //Caajas de texto
        jtNom_prod.setForeground(Color.GRAY);
        jtNom_prod.setText("Nombre");
        jtNom_prod.setColumns(5);

        jtMarca.setForeground(Color.GRAY);
        jtMarca.setText("Marca");
        jtMarca.setColumns(5);

        jtPrecio.setForeground(Color.GRAY);
        jtPrecio.setText("$$$$");
        jtPrecio.setColumns(5);

        jTcantidad.setForeground(Color.GRAY);
        jTcantidad.setText("cantidad");
        jTcantidad.setColumns(5);

    }

    public void bloc_sigui() {
        int ult_regis = MySQL.cantRegistros(Tabla, ID);

        if (cont_flec == ult_regis) {
            jBSigui.setEnabled(false);
            jBUltim.setEnabled(false);
        } else if (cont_flec != ult_regis && jBSigui.isEnabled() == false && jBUltim.isEnabled() == false) {
            jBSigui.setEnabled(true);
            jBUltim.setEnabled(true);
        } else {
            jBSigui.setEnabled(true);
            jBUltim.setEnabled(true);
        }
        //bloc_ant();

    }

    public void bloc_ant() {
        if (cont_flec == 1) {
            jBAnterior.setEnabled(false);
            jBPrimer1.setEnabled(false);
        } else if (cont_flec != 1 && jBAnterior.isEnabled() == false && jBPrimer1.isEnabled() == false) {
            jBAnterior.setEnabled(true);
            jBPrimer1.setEnabled(true);
        } else {
            jBAnterior.setEnabled(true);
            jBPrimer1.setEnabled(true);
        }
        //bloc_sigui();

    }

    public void rec_dat() {
        if (jTcantidad.getText().equalsIgnoreCase("cantidad") || jtMarca.getText().equalsIgnoreCase("Marca") || jtNom_prod.getText().equalsIgnoreCase("Nombre") || jtPrecio.getText().equalsIgnoreCase("$$$$") || dateChooser.getDate() == null || jCBmedida.getSelectedIndex() == 0) {
            jbImg.setEnabled(false);
        } else {
            jbImg.setEnabled(true);
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

        jPanel2 = new javax.swing.JPanel();
        jPCent = new javax.swing.JPanel();
        jlNom_prod = new javax.swing.JLabel();
        jtNom_prod = new javax.swing.JTextField();
        jlMarca = new javax.swing.JLabel();
        jtMarca = new javax.swing.JTextField();
        jlPrecio = new javax.swing.JLabel();
        jtPrecio = new javax.swing.JTextField();
        jPfoto = new javax.swing.JPanel();
        jLImg = new javax.swing.JLabel();
        jbImg = new javax.swing.JButton();
        jlFoto = new javax.swing.JLabel();
        jLVenci = new javax.swing.JLabel();
        jBVenci = new javax.swing.JButton();
        jLmedida = new javax.swing.JLabel();
        jCBmedida = new javax.swing.JComboBox<>();
        jLcantidad = new javax.swing.JLabel();
        jTcantidad = new javax.swing.JTextField();
        jlNav = new javax.swing.JLabel();
        jPNav = new javax.swing.JPanel();
        jBPrimer1 = new javax.swing.JButton();
        jBAnterior = new javax.swing.JButton();
        jBSigui = new javax.swing.JButton();
        jBUltim = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jBIng = new javax.swing.JButton();
        jBEdit = new javax.swing.JButton();
        jBEli = new javax.swing.JButton();
        jLCrud = new javax.swing.JLabel();
        jPFn = new javax.swing.JPanel();
        jBExit = new javax.swing.JButton();
        JBImpr = new javax.swing.JButton();
        jBConsulta = new javax.swing.JButton();
        jBGuia = new javax.swing.JButton();
        jLFn = new javax.swing.JLabel();
        jPPie = new javax.swing.JPanel();
        jLInfo = new javax.swing.JLabel();
        jLfech = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jlNom_prod.setText("jLabel1");

        jtNom_prod.setText("jTextField1");
        jtNom_prod.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtNom_prodFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtNom_prodFocusLost(evt);
            }
        });

        jlMarca.setText("jLabel1");

        jtMarca.setText("jTextField1");
        jtMarca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtMarcaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtMarcaFocusLost(evt);
            }
        });

        jlPrecio.setText("jLabel1");

        jtPrecio.setText("jTextField1");
        jtPrecio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtPrecioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtPrecioFocusLost(evt);
            }
        });

        jbImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbImgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPfotoLayout = new javax.swing.GroupLayout(jPfoto);
        jPfoto.setLayout(jPfotoLayout);
        jPfotoLayout.setHorizontalGroup(
            jPfotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPfotoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPfotoLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jbImg)
                .addGap(38, 38, 38))
        );
        jPfotoLayout.setVerticalGroup(
            jPfotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPfotoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLImg, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbImg, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jlFoto.setText("jLabel1");

        jLVenci.setText("jLabel1");

        jBVenci.setText("jButton1");
        jBVenci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVenciActionPerformed(evt);
            }
        });

        jLmedida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLmedida.setText("jLabel2");

        jCBmedida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Medida", "Libra", "Kilo", "Unidad" }));
        jCBmedida.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCBmedidaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCBmedidaFocusLost(evt);
            }
        });
        jCBmedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBmedidaActionPerformed(evt);
            }
        });

        jLcantidad.setText("jLabel1");

        jTcantidad.setText("jTextField1");
        jTcantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTcantidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTcantidadFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPCentLayout = new javax.swing.GroupLayout(jPCent);
        jPCent.setLayout(jPCentLayout);
        jPCentLayout.setHorizontalGroup(
            jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCentLayout.createSequentialGroup()
                .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPCentLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLVenci, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLmedida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPCentLayout.createSequentialGroup()
                                .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlMarca)
                                    .addComponent(jtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBVenci, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18))
                    .addGroup(jPCentLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jCBmedida, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPCentLayout.createSequentialGroup()
                        .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLcantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTcantidad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPfoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(jPCentLayout.createSequentialGroup()
                        .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlPrecio)
                            .addGroup(jPCentLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jlNom_prod, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                        .addComponent(jlFoto)
                        .addGap(76, 76, 76))
                    .addGroup(jPCentLayout.createSequentialGroup()
                        .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtNom_prod, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPCentLayout.setVerticalGroup(
            jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCentLayout.createSequentialGroup()
                .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPCentLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jlFoto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPfoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPCentLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPCentLayout.createSequentialGroup()
                                .addComponent(jlNom_prod)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtNom_prod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jlPrecio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPCentLayout.createSequentialGroup()
                                .addComponent(jlMarca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLVenci)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBVenci)
                                .addGap(18, 18, 18)
                                .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLmedida)
                                    .addComponent(jLcantidad))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jCBmedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        jlNav.setText("jLabel1");

        jBPrimer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPrimer1ActionPerformed(evt);
            }
        });

        jBAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAnteriorActionPerformed(evt);
            }
        });

        jBSigui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSiguiActionPerformed(evt);
            }
        });

        jBUltim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBUltimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPNavLayout = new javax.swing.GroupLayout(jPNav);
        jPNav.setLayout(jPNavLayout);
        jPNavLayout.setHorizontalGroup(
            jPNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPNavLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBPrimer1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBSigui, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBUltim, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPNavLayout.setVerticalGroup(
            jPNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPNavLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBPrimer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBSigui, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBUltim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jBIng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBIngActionPerformed(evt);
            }
        });

        jBEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEditActionPerformed(evt);
            }
        });

        jBEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBIng, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBEli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBIng, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jBEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBEli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLCrud.setText("jLabel1");

        jBExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBExitActionPerformed(evt);
            }
        });

        JBImpr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBImprActionPerformed(evt);
            }
        });

        jBConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConsultaActionPerformed(evt);
            }
        });

        jBGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPFnLayout = new javax.swing.GroupLayout(jPFn);
        jPFn.setLayout(jPFnLayout);
        jPFnLayout.setHorizontalGroup(
            jPFnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JBImpr, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBExit, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPFnLayout.setVerticalGroup(
            jPFnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPFnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBImpr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBGuia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLFn.setText("jLabel1");

        jLInfo.setText("jLabel1");

        jLfech.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLfech.setText("jLabel1");
        jLfech.setToolTipText("");

        javax.swing.GroupLayout jPPieLayout = new javax.swing.GroupLayout(jPPie);
        jPPie.setLayout(jPPieLayout);
        jPPieLayout.setHorizontalGroup(
            jPPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPPieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLfech, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPPieLayout.setVerticalGroup(
            jPPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPPieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLInfo)
                    .addComponent(jLfech))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlNav)
                    .addComponent(jPNav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLCrud, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPFn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLFn, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
            .addComponent(jPPie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPCent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPCent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlNav)
                    .addComponent(jLCrud)
                    .addComponent(jLFn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPNav, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPFn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(jPPie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBIngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBIngActionPerformed
        ingreso();


    }//GEN-LAST:event_jBIngActionPerformed

    public void ingreso() {
        contador++;

        if (contador % 2 == 0) {
            if (archivo != null) {

                jtMarca.setEnabled(false);
                jtNom_prod.setEnabled(false);
                jtPrecio.setEnabled(false);
                jbImg.setEnabled(false);
                dateChooser.setEnabled(false);
                jCBmedida.setEnabled(false);
                jTcantidad.setEnabled(false);

                ImageIcon foto = new ImageIcon(getClass().getResource(ruta_img[0]));
                ImageIcon mitad_1 = new ImageIcon(foto.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

                // establece el icono en el botón
                jBIng.setIcon(mitad_1);

                //Entrada de datos a la base
                //Obtencion de la fecha de vencimiento
                Date fechaVenci = dateChooser.getDate();
                SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
                String fechaV = form.format(fechaVenci);

                //Conversion de la imgen 
                String img_paht = MySQL.imgenEnviar(archivo.getAbsolutePath());
                System.out.println(img_paht);

                //Enviar los datos                                                                                                      cantidad                               medida  img
                MySQL.insert_prod("productos", jtNom_prod.getText(), jtMarca.getText(), Integer.parseInt(jtPrecio.getText()), fechaV, Integer.parseInt(jTcantidad.getText()), selec_med, img_paht);

//                MIRAR COMO VAN A FUNCIONAR AHORA ESTOS CONTADORES
                cant_prods = MySQL.cantRegistros(Tabla, ID);

                cont_label = cant_prods;
                jLInfo.setText("Tabla: Producto registro " + cont_label + " al " + (cant_prods));

                jCBmedida.setSelectedIndex(0);

//                  AQUI ESTABA LA CONDICION DE CUANDO LLEGABA A EL TOPE
//                    MIRAR COMO VAN AHORA ESTOS CONTADORES
                cont_flec = cant_prods;

                //Esto por si solo hay un dato
                int cont_prod = MySQL.cantRegistros(Tabla, ID);
                if (cont_prod == 1) {
                    jBSigui.setEnabled(false);
                    jBAnterior.setEnabled(false);
                    jBPrimer1.setEnabled(false);
                    jBUltim.setEnabled(false);
                }
                jBIng.setToolTipText("Ingreso");

                MySQL.closeConnection();
                bloc_sigui();
                bloc_ant();

            } else {
                JOptionPane.showMessageDialog(rootPane, "Porfavor llena todos los campos faltantes", "Error de insercion", JOptionPane.WARNING_MESSAGE);

            }

        } else {
            dateChooser.setEnabled(true);
            jCBmedida.setEnabled(true);
            jtMarca.setEnabled(true);
            jtNom_prod.setEnabled(true);
            jtPrecio.setEnabled(true);
            jbImg.setEnabled(true);
            jTcantidad.setEnabled(true);
            ImageIcon foto = new ImageIcon(getClass().getResource(ruta_img[1]));
            ImageIcon mitad_1 = new ImageIcon(foto.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

            // establece el icono en el botón
            jBIng.setIcon(mitad_1);
            jBIng.setToolTipText("Guardar");

            //Esto para que la condicion del bloque del boton cobre sentido
            archivo = null;

            //Volver a poner vacios los jtextfield
            jtMarca.setText("");
            jtNom_prod.setText("");
            jtPrecio.setText("");
            jTcantidad.setText("");
            Date Vacio_fech = null;
            dateChooser.setDate(Vacio_fech);

            jCBmedida.setSelectedIndex(0);

            Pjolder();

            //Para que se reinicie el label
            ImageIcon proRei = new ImageIcon(getClass().getResource(""));
            ImageIcon rei_pro = new ImageIcon(proRei.getImage().getScaledInstance(jLImg.getWidth(), jLImg.getHeight(), Image.SCALE_DEFAULT));
            jLImg.setIcon(rei_pro);

            rec_dat();

        }
    }

    private void jBConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConsultaActionPerformed

        for (int i = 0; i < tabla_p.length; i++) {
            for (int l = 0; l <= 6; l++) {
                tabla_p[i][l] = frmiProducto.Datos_p[i][l];
            }
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFconsulta().setVisible(true);
            }
        });
    }//GEN-LAST:event_jBConsultaActionPerformed

    private void jtNom_prodFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtNom_prodFocusGained
        if (jtNom_prod.getText().equals("Nombre")) {
            jtNom_prod.setForeground(Color.BLACK);
            jtNom_prod.setText("");
            rec_dat();
        }
    }//GEN-LAST:event_jtNom_prodFocusGained

    private void jtNom_prodFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtNom_prodFocusLost
        if (!jtNom_prod.getText().isEmpty()) {

        } else {
            jtNom_prod.setForeground(Color.GRAY);
            jtNom_prod.setText("Nombre");
            rec_dat();
        }
    }//GEN-LAST:event_jtNom_prodFocusLost

    private void jtMarcaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtMarcaFocusGained
        if (jtMarca.getText().equals("Marca")) {
            jtMarca.setForeground(Color.BLACK);
            jtMarca.setText("");
            rec_dat();
        }
    }//GEN-LAST:event_jtMarcaFocusGained

    private void jtMarcaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtMarcaFocusLost
        if (!jtMarca.getText().isEmpty()) {

        } else {
            jtMarca.setForeground(Color.GRAY);
            jtMarca.setText("Marca");
            rec_dat();
        }
    }//GEN-LAST:event_jtMarcaFocusLost

    private void jtPrecioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtPrecioFocusGained
        if (jtPrecio.getText().equals("$$$$")) {
            jtPrecio.setForeground(Color.BLACK);
            jtPrecio.setText("");
            rec_dat();
        }
    }//GEN-LAST:event_jtPrecioFocusGained

    private void jtPrecioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtPrecioFocusLost
        if (!jtPrecio.getText().isEmpty()) {

        } else {
            jtPrecio.setForeground(Color.GRAY);
            jtPrecio.setText("$$$$");
            rec_dat();
        }
    }//GEN-LAST:event_jtPrecioFocusLost

    private void jbImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbImgActionPerformed
        JFileChooser jf = new JFileChooser();
        File directorioInicio = new File("C:\\Users\\santi\\Downloads\\Segundo consolidado\\Minimarket_Bases\\Minimarket_bases2\\src\\imagenes\\productos");

// Establecer la carpeta de inicio del JFileChooser
        jf.setCurrentDirectory(directorioInicio);
        
        jf.showOpenDialog(this);
        archivo = jf.getSelectedFile();
        if (archivo != null) {
            //Poner la imagen en el label
            ImageIcon proFoto = new ImageIcon(archivo.getAbsolutePath());
            ImageIcon icono_pro = new ImageIcon(proFoto.getImage().getScaledInstance(jLImg.getWidth(), jLImg.getHeight(), Image.SCALE_DEFAULT));
            jLImg.setIcon(icono_pro);
        }
    }//GEN-LAST:event_jbImgActionPerformed

    private void jBEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliActionPerformed

        String Dato_eli = JOptionPane.showInputDialog(rootPane, "Digita el ID del producto que desea eliminar");

        //Intento con geerman
        boolean Scuentra = false;
        int pos = 0;

        while (!Scuentra && pos < nFils) {
            System.out.println("#1");
            //esta preguntando si la cadena es de esa posicion es igual a identificador
            if (Dato_eli.equals(Datos_p[pos][0])) {
                System.out.println("#2");
                Scuentra = true; // halle la fila
            } else {
                pos++;
            }
        }

        // si es valida la posicion
        if (pos < nFils) {
            System.out.println("#3 " + pos);
            Object[][] nMatriz = new Object[nFils - 1][Datos_p[0].length];
            contador_eli = 0;
            for (int filaAC = 0; filaAC < pos; filaAC++) {
                System.out.println("#4");
                for (int columnaAC = 0; columnaAC < sCabecera.length; columnaAC++) {
                    System.out.println("#5");
                    if (pos == filaAC) {
                        System.out.println("#6");
                        filaAC++;
                    }
                    if (pos > 0) {
                        System.out.println("#7" + pos);
                        nMatriz[contador_eli][columnaAC] = Datos_p[filaAC][columnaAC];
                    }
                }
                contador_eli++;
            }

            for (int i = 0; i < sCabecera.length; i++) {
                System.out.println("#8");
                Datos_p[pos][i] = null;
            }
            JOptionPane.showMessageDialog(rootPane, "El dato ha sido eliminado", "Eliminar", JOptionPane.PLAIN_MESSAGE);

            //Esto es para que su puestamente puedas volver agregar un dato
//            cont_fil--;
//            jBIng.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "El identificador ingresado no existe", "Eliminar fallido", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jBEliActionPerformed

    private void jBEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditActionPerformed
        String Dato_mod = JOptionPane.showInputDialog(rootPane, "Digita el ID del producto que desea modificar");

        boolean Scuentra = false;//bandera de while
        int pos = 0;
        int columna = 0;
        cont_filM = 0;

        while (!Scuentra && pos < nFils) {
            //la cadena de esa posicion es igual a nfils
            if (Dato_mod.equals(Datos_p[pos][0])) {
                Scuentra = true;// halla la fila
            } else {
                pos++;
                cont_filM++;
            }
        }

        if (pos < nFils) {
            //System.out.println("Digite Valor: [0-Id_producto][1-Precio][2-Nom_prod][3-Marca][4][] ");
            String Dato_coluMod = JOptionPane.showInputDialog(rootPane, "Digite Valor: [0-Id_producto][1-Precio][2-Nom_prod][3-Marca][4-Vencimiento][5-Medida][6-rut_foto]");
            columna = Integer.parseInt(Dato_coluMod);//conversion a entero
            modificarDatos(pos, columna);

        } else {
            System.out.println("El identificador ingresado no existe");
        }
    }//GEN-LAST:event_jBEditActionPerformed


    private void jBGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuiaActionPerformed
        File file = new File("C:/Users/santi/Downloads/Segundo consolidado/Producto_2/src/imagenes/CRUD.pdf");
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

    private void jBExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExitActionPerformed
        setVisible(false);
        contador=0;
    }//GEN-LAST:event_jBExitActionPerformed

    private void JBImprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBImprActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFimp().setVisible(true);
            }
        });
    }//GEN-LAST:event_JBImprActionPerformed

    private void jBPrimer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPrimer1ActionPerformed
        /*
        *Lo que hacemos aqui es para que vaya a el primer dato y me los muestre
        *
         */

        //Base de datos
        try {
            PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("SELECT * FROM productos where id_prod = 1;");

            ResultSet res = (ResultSet) stp.executeQuery();

            if (res.next()) {

                jtNom_prod.setText(res.getString("nombre_prod"));
                jtMarca.setText(res.getString("marca"));
                jtPrecio.setText(res.getString("precio"));
                jTcantidad.setText(res.getString("cantidad"));

                //Poner fecha
                String fech_actual = res.getString("fecha_venci");
                SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fechaVenciS = S.parse(fech_actual);
                    dateChooser.setDate(fechaVenciS);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //set tipo de Medida
                String selec_act = res.getString("medida");
                System.out.println(selec_act);

                switch (selec_act.trim()) {
                    case "Libra":
                        jCBmedida.setSelectedIndex(1);
                        break;
                    case "Kilo":
                        jCBmedida.setSelectedIndex(2);
                        break;
                    case "Unidad":
                        jCBmedida.setSelectedIndex(3);
                        break;
                    default:
                        throw new AssertionError();
                }

                //Poner la imagen en el label
                ImageIcon proFoto2 = new ImageIcon(getClass().getResource(res.getString("img_prod")));
                ImageIcon icono_pro2 = new ImageIcon(proFoto2.getImage().getScaledInstance(jLImg.getWidth(), jLImg.getHeight(), Image.SCALE_DEFAULT));
                jLImg.setIcon(icono_pro2);

            }
        } catch (SQLException ex) {
            Logger.getLogger(frmiProducto.class.getName()).log(Level.SEVERE, null, ex);
        }

        // RESET PARA EL LABEL DE POSICIONES 
        jLInfo.setText("Tabla: Producto registro  1  al " + (cant_prods));

        cont_flec = 1;

        jBUltim.setEnabled(true);
        jBSigui.setEnabled(true);

        bloc_ant();


    }//GEN-LAST:event_jBPrimer1ActionPerformed

    private void jBUltimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBUltimActionPerformed
        /*
        *Lo que hacemos aqui es para primero entre al ciclo y lo lleve al ultimo registro que hizo
        *Esto mismo ejecuta un contador para los botones del medio del nav se ejecuten correctamente y el contador del label se ejecute contando los registros en los que vamos 
         */

        int ult_regis = MySQL.cantRegistros(Tabla, ID);

        try {
            PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("SELECT * FROM productos where id_prod = " + ult_regis + ";");

            ResultSet res = (ResultSet) stp.executeQuery();

            if (res.next()) {

                jtNom_prod.setText(res.getString("nombre_prod"));
                jtMarca.setText(res.getString("marca"));
                jtPrecio.setText(res.getString("precio"));
                jTcantidad.setText(res.getString("cantidad"));

                //Poner fecha
                String fech_actual = res.getString("fecha_venci");
                SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fechaVenciS = S.parse(fech_actual);
                    dateChooser.setDate(fechaVenciS);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //set tipo de Medida
                String selec_act = res.getString("medida");
                System.out.println(selec_act);

                switch (selec_act.trim()) {
                    case "Libra":
                        jCBmedida.setSelectedIndex(1);
                        break;
                    case "Kilo":
                        jCBmedida.setSelectedIndex(2);
                        break;
                    case "Unidad":
                        jCBmedida.setSelectedIndex(3);
                        break;
                    default:
                        throw new AssertionError();
                }

                //Poner la imagen en el label
                ImageIcon proFoto2 = new ImageIcon(getClass().getResource(res.getString("img_prod")));
                ImageIcon icono_pro2 = new ImageIcon(proFoto2.getImage().getScaledInstance(jLImg.getWidth(), jLImg.getHeight(), Image.SCALE_DEFAULT));
                jLImg.setIcon(icono_pro2);

            }
        } catch (SQLException ex) {
            Logger.getLogger(frmiProducto.class.getName()).log(Level.SEVERE, null, ex);
        }

        // RESET PARA EL LABEL DE POSICIONES 
        jLInfo.setText("Tabla: Producto registro " + cant_prods + "  al " + (cant_prods));

        cont_flec = ult_regis;

        jBPrimer1.setEnabled(true);
        jBAnterior.setEnabled(true);

        bloc_sigui();


    }//GEN-LAST:event_jBUltimActionPerformed

    private void jBSiguiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSiguiActionPerformed

        //Bases de datos
        cont_flec++;

        try {
            PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("SELECT * FROM productos where id_prod = " + cont_flec + ";");

            ResultSet res = (ResultSet) stp.executeQuery();

            if (res.next()) {

                jtNom_prod.setText(res.getString("nombre_prod"));
                jtMarca.setText(res.getString("marca"));
                jtPrecio.setText(res.getString("precio"));
                jTcantidad.setText(res.getString("cantidad"));

                //Poner fecha
                String fech_actual = res.getString("fecha_venci");
                SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fechaVenciS = S.parse(fech_actual);
                    dateChooser.setDate(fechaVenciS);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //set tipo de Medida
                String selec_act = res.getString("medida");
                System.out.println(selec_act);

                switch (selec_act.trim()) {
                    case "Libra":
                        jCBmedida.setSelectedIndex(1);
                        break;
                    case "Kilo":
                        jCBmedida.setSelectedIndex(2);
                        break;
                    case "Unidad":
                        jCBmedida.setSelectedIndex(3);
                        break;
                    default:
                        throw new AssertionError();
                }

                //Poner la imagen en el label
                ImageIcon proFoto2 = new ImageIcon(getClass().getResource(res.getString("img_prod")));
                ImageIcon icono_pro2 = new ImageIcon(proFoto2.getImage().getScaledInstance(jLImg.getWidth(), jLImg.getHeight(), Image.SCALE_DEFAULT));
                jLImg.setIcon(icono_pro2);

            }
        } catch (SQLException ex) {
            Logger.getLogger(frmiProducto.class.getName()).log(Level.SEVERE, null, ex);
        }

        // RESET PARA EL LABEL DE POSICIONES 
        jLInfo.setText("Tabla: Producto registro  " + cont_flec + "  al " + (cant_prods));

        bloc_sigui();
        bloc_ant();

        //***************

    }//GEN-LAST:event_jBSiguiActionPerformed

    private void jBAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAnteriorActionPerformed
        cont_flec--;

        try {
            PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("SELECT * FROM productos where id_prod = " + cont_flec + ";");

            ResultSet res = (ResultSet) stp.executeQuery();

            if (res.next()) {

                jtNom_prod.setText(res.getString("nombre_prod"));
                jtMarca.setText(res.getString("marca"));
                jtPrecio.setText(res.getString("precio"));
                jTcantidad.setText(res.getString("cantidad"));

                //Poner fecha
                String fech_actual = res.getString("fecha_venci");
                SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fechaVenciS = S.parse(fech_actual);
                    dateChooser.setDate(fechaVenciS);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //set tipo de Medida
                String selec_act = res.getString("medida");
                System.out.println(selec_act);

                switch (selec_act.trim()) {
                    case "Libra":
                        jCBmedida.setSelectedIndex(1);
                        break;
                    case "Kilo":
                        jCBmedida.setSelectedIndex(2);
                        break;
                    case "Unidad":
                        jCBmedida.setSelectedIndex(3);
                        break;
                    default:
                        throw new AssertionError();
                }

                //Poner la imagen en el label
                ImageIcon proFoto2 = new ImageIcon(getClass().getResource(res.getString("img_prod")));
                ImageIcon icono_pro2 = new ImageIcon(proFoto2.getImage().getScaledInstance(jLImg.getWidth(), jLImg.getHeight(), Image.SCALE_DEFAULT));
                jLImg.setIcon(icono_pro2);

            }
        } catch (SQLException ex) {
            Logger.getLogger(frmiProducto.class.getName()).log(Level.SEVERE, null, ex);
        }

        // RESET PARA EL LABEL DE POSICIONES 
        jLInfo.setText("Tabla: Producto registro  " + cont_flec + "  al " + (cant_prods));

        bloc_ant();
        bloc_sigui();

    }//GEN-LAST:event_jBAnteriorActionPerformed

    private void jBVenciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVenciActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBVenciActionPerformed

    private void jCBmedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBmedidaActionPerformed
        int indice = jCBmedida.getSelectedIndex();
        switch (indice) {
            case 0:
                selec_med = null;
                break;
            case 1:
                selec_med = "Libra";
                break;
            case 2:
                selec_med = "Kilo";
                break;
            case 3:
                selec_med = "Unidad";
                break;
        }
    }//GEN-LAST:event_jCBmedidaActionPerformed

    private void jCBmedidaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCBmedidaFocusGained
        rec_dat();
    }//GEN-LAST:event_jCBmedidaFocusGained

    private void jCBmedidaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCBmedidaFocusLost
        rec_dat();
    }//GEN-LAST:event_jCBmedidaFocusLost

    private void jTcantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTcantidadFocusGained
        if (jTcantidad.getText().equals("cantidad")) {
            jTcantidad.setForeground(Color.BLACK);
            jTcantidad.setText("");
            rec_dat();
        }
    }//GEN-LAST:event_jTcantidadFocusGained

    private void jTcantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTcantidadFocusLost
        if (!jTcantidad.getText().isEmpty()) {

        } else {
            jTcantidad.setForeground(Color.GRAY);
            jTcantidad.setText("cantidad");
            rec_dat();
        }
    }//GEN-LAST:event_jTcantidadFocusLost

    public void modificarDatos(int fils, int cols) {
        if (cols == 6) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new JFcamImg().setVisible(true);
                }
            });
        } else if (cols == 4) {

            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new JFcamVenci().setVisible(true);
                }
            });

        } else if (cols == 5) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new JFcamMedida().setVisible(true);
                }
            });
        } else {
            String Dato_cambia = JOptionPane.showInputDialog(rootPane, "Digite el nuevo dato de " + sCabecera[cols] + " :");
            System.out.println("Digite su " + sCabecera[cols] + ":");
            Datos_p[fils][cols] = Dato_cambia;
            JOptionPane.showMessageDialog(rootPane, "El dato a sido cambiado", "Dato cambiado", JOptionPane.PLAIN_MESSAGE);
        }

    }

//    public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Login.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Login.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Login.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Login.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frmiPrincipal().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBImpr;
    private javax.swing.JButton jBAnterior;
    private javax.swing.JButton jBConsulta;
    private javax.swing.JButton jBEdit;
    private javax.swing.JButton jBEli;
    private javax.swing.JButton jBExit;
    private javax.swing.JButton jBGuia;
    private javax.swing.JButton jBIng;
    private javax.swing.JButton jBPrimer1;
    private javax.swing.JButton jBSigui;
    private javax.swing.JButton jBUltim;
    private javax.swing.JButton jBVenci;
    private javax.swing.JComboBox<String> jCBmedida;
    private javax.swing.JLabel jLCrud;
    private javax.swing.JLabel jLFn;
    private javax.swing.JLabel jLImg;
    private javax.swing.JLabel jLInfo;
    private javax.swing.JLabel jLVenci;
    private javax.swing.JLabel jLcantidad;
    private javax.swing.JLabel jLfech;
    private javax.swing.JLabel jLmedida;
    private javax.swing.JPanel jPCent;
    private javax.swing.JPanel jPFn;
    private javax.swing.JPanel jPNav;
    private javax.swing.JPanel jPPie;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPfoto;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTcantidad;
    private javax.swing.JButton jbImg;
    private javax.swing.JLabel jlFoto;
    private javax.swing.JLabel jlMarca;
    private javax.swing.JLabel jlNav;
    private javax.swing.JLabel jlNom_prod;
    private javax.swing.JLabel jlPrecio;
    private javax.swing.JTextField jtMarca;
    private javax.swing.JTextField jtNom_prod;
    private javax.swing.JTextField jtPrecio;
    // End of variables declaration//GEN-END:variables
}
