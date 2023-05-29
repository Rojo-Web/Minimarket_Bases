/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vista.Empleado;

import Modelo.MySQL;
import static Modelo.MySQL.Conexion;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author santi
 */
public class frmiEmpleados extends javax.swing.JInternalFrame {

    public static int contador = 0, contador_edit = 0, contador_eli = 0, cont_fil = 0, cont_fil_nav = 0, cont_flec = 0, cont_label = 0, columna = 0, cont_filM = 0, cant_med = 0, cant_empls = 0;
    public final int ancho = 15, alto = 15;
    private static String Tabla = "empleados", ID = "id_empl";
    private static boolean veri_Bedit = false, bloq_grup = false, acess = false;
    private static String grupturnsec = "", grupNivelsec = "";
    public Font typeFP = new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16);

    //Tabla de empleados
    public static String[] sCabecera = {"id_empl", "nombre", "apellido", "turno", "fecha_nacimi", "edad", "nivel", "clave"};
    public static String[][] tabla_empl;

    //Fecha de nacimiento
    public static String fechaNaci = "";
    private static JDateChooser dateChooser;

    //Vector de iconos
    //                                  0                           1                       2                           3                       4                       5                       6
    public String[] ruta_img = {"/imagenes/nota.png", "/imagenes/disco-flexible.png", "/imagenes/producto.png", "/imagenes/Ultima.png", "/imagenes/anterior.png", "/imagenes/siguiente.png", "/imagenes/primera.png",
        "/imagenes/editar-codigo.png", "/imagenes/expediente.png", "/imagenes/manual.png", "/imagenes/buscando.png", "/imagenes/impresora.png", "/imagenes/cerrar-sesion.png", "/imagenes/camara.png", "/imagenes/salida.png"};

    public frmiEmpleados() {
        setTitle("Tabla Empleados");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();

        setSize(501, 395);
        jBcontFech.setEnabled(false);
        setResizable(false); // Deshabilitar el redimensionamiento
        setLocation(0, 0);

        componets();

        Pjolder();
    }

    public void componets() {
        //**********COMPONENTES************
        //Labels
        //lblNombre
        jLnom.setForeground(new java.awt.Color(0, 0, 0));
        jLnom.setFont(new Font("Arial", Font.BOLD, 14));
        jLnom.setText("Nombres:");
        jLnom.setForeground(new Color(240, 113, 1));
        jLnom.setFont(typeFP);

        //lblApellidos
        jLape.setForeground(new java.awt.Color(0, 0, 0));
        jLape.setFont(new Font("Arial", Font.BOLD, 14));
        jLape.setText("Apellidos:");
        jLape.setForeground(new Color(240, 113, 1));
        jLape.setFont(typeFP);
        //lblNivel de usuario
        jLnivel.setForeground(new java.awt.Color(0, 0, 0));
        jLnivel.setFont(new Font("Arial", Font.BOLD, 14));
        jLnivel.setText("Nivel de usuario:");

        jLnivel.setForeground(new Color(240, 113, 1));
        jLnivel.setFont(typeFP);

        //lblNav
        jlNav.setForeground(new java.awt.Color(0, 0, 0));
        jlNav.setFont(new Font("Arial", Font.BOLD, 14));
        jlNav.setText("Nav");
        jlNav.setForeground(new Color(240, 113, 1));
        jlNav.setFont(typeFP);

        //lblmmto
        jLCrud.setForeground(new java.awt.Color(0, 0, 0));
        jLCrud.setFont(new Font("Arial", Font.BOLD, 14));
        jLCrud.setText("Mtto");
        jLCrud.setForeground(new Color(240, 113, 1));
        jLCrud.setFont(typeFP);

        //lblFN
        jLFn.setForeground(new java.awt.Color(0, 0, 0));
        jLFn.setFont(new Font("Arial", Font.BOLD, 14));
        jLFn.setText("Fn Especiales");
        jLFn.setForeground(new Color(240, 113, 1));
        jLFn.setFont(typeFP);

        //lblfecha
        jLfecha.setForeground(new java.awt.Color(0, 0, 0));
        jLfecha.setFont(new Font("Arial", Font.BOLD, 14));
        jLfecha.setText("Fecha de nacimiento:");
        jLfecha.setForeground(new Color(240, 113, 1));
        jLfecha.setFont(typeFP);

        //lblContraseña
        jLclave.setForeground(new java.awt.Color(0, 0, 0));
        jLclave.setFont(new Font("Arial", Font.BOLD, 14));
        jLclave.setText("Contraseña:");
        //lblTurno
        jLturno.setForeground(new java.awt.Color(0, 0, 0));
        jLturno.setFont(new Font("Arial", Font.BOLD, 14));
        jLturno.setText("Turno:");

        //lblinfo
        cant_empls = MySQL.cantRegistros(Tabla, ID);
        System.out.println("hola cantida_prd: " + cant_empls);

        cont_label = 0;
        jLInfo.setText("Tabla: Producto registro " + cont_label + " al " + (cant_empls));
        //lblFechnaci
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

        //radios
        buttonGroup1.add(jRBdiurno);
        buttonGroup1.add(jRBnocturno);

        //grupTurn = buttonGroup1.getSelection();
        buttonGroup2.add(jRBadmin);
        buttonGroup2.add(jRBempleado);

        //btn fechVenci
        dateChooser = new JDateChooser();

        jBcontFech.add(dateChooser);

//        //Bloqueo del nav
        jBAnterior.setEnabled(false);
        jBPrimer1.setEnabled(false);

        //Panel principal Bloqueado
        jTFape.setEnabled(false);
        jTFclave.setEnabled(false);
        jTFnom.setEnabled(false);
        jPnivel.setEnabled(false);
        dateChooser.setEnabled(false);
        jPturno.setEnabled(false);
        jRBadmin.setEnabled(false);
        jRBempleado.setEnabled(false);
        jRBdiurno.setEnabled(false);
        jRBnocturno.setEnabled(false);

        //Esto se hace para que si no hay productos pues no se activen los botones del nav
        int cant_regis = MySQL.cantRegistros(Tabla, ID);
        if (cant_regis == 0) {
            jBSigui.setEnabled(false);
            jBPrimer1.setEnabled(false);
            jBUltim.setEnabled(false);
            jBAnterior.setEnabled(false);
        }

    }

    public void Pjolder() {
        //Caajas de texto
        jTFnom.setForeground(Color.GRAY);
        jTFnom.setText("Nombre");
        jTFnom.setColumns(5);

        jTFape.setForeground(Color.GRAY);
        jTFape.setText("Apellido");
        jTFape.setColumns(5);

        jTFclave.setForeground(Color.GRAY);
        jTFclave.setText("****");
        jTFclave.setColumns(5);

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

        //Rectifiacar rbuton turno
        if (jRBdiurno.isSelected()) {
            grupturnsec = "D";
        } else if (jRBnocturno.isSelected()) {
            grupturnsec = "N";
        } else {
            grupturnsec = null;
        }

        //Rectifiacar rbuton nivel
        if (jRBadmin.isSelected()) {
            grupNivelsec = "admin";
        } else if (jRBempleado.isSelected()) {
            grupNivelsec = "empleado";
        } else {
            grupNivelsec = null;
        }

        if (jTFnom.getText().equalsIgnoreCase("Nombre") || jTFape.getText().equalsIgnoreCase("Apellido") || jTFclave.getText().equalsIgnoreCase("****") || dateChooser.getDate() == null || grupturnsec == null || grupNivelsec == null) {
            acess = false;
        } else {
            acess = true;
        }
    }

    public void bloq_grup() {
        if (bloq_grup == true) {
            JBImpr.setEnabled(false);
            jBAnterior.setEnabled(false);
            jBConsulta.setEnabled(false);
            jBEdit.setEnabled(false);
            jBEli.setEnabled(false);
            jBExit.setEnabled(false);
            jBGuia.setEnabled(false);
            jBPrimer1.setEnabled(false);
            jBUltim.setEnabled(false);
            jBSigui.setEnabled(false);

        } else {
            JBImpr.setEnabled(true);
            jBAnterior.setEnabled(true);
            jBConsulta.setEnabled(true);
            jBEdit.setEnabled(true);
            jBEli.setEnabled(true);
            jBExit.setEnabled(true);
            jBGuia.setEnabled(true);
            jBPrimer1.setEnabled(true);
            jBUltim.setEnabled(true);
            jBSigui.setEnabled(true);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTFnom = new javax.swing.JTextField();
        jPnivel = new javax.swing.JPanel();
        jLnivel = new javax.swing.JLabel();
        jRBadmin = new javax.swing.JRadioButton();
        jRBempleado = new javax.swing.JRadioButton();
        jLnom = new javax.swing.JLabel();
        jLape = new javax.swing.JLabel();
        jTFape = new javax.swing.JTextField();
        jPturno = new javax.swing.JPanel();
        jLturno = new javax.swing.JLabel();
        jRBdiurno = new javax.swing.JRadioButton();
        jRBnocturno = new javax.swing.JRadioButton();
        jPclave = new javax.swing.JPanel();
        jLclave = new javax.swing.JLabel();
        jTFclave = new javax.swing.JTextField();
        jPfecha_nacimi = new javax.swing.JPanel();
        jLfecha = new javax.swing.JLabel();
        jBcontFech = new javax.swing.JButton();
        jLFn = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jBIng = new javax.swing.JButton();
        jBEli = new javax.swing.JButton();
        jBEdit = new javax.swing.JToggleButton();
        jPPie = new javax.swing.JPanel();
        jLInfo = new javax.swing.JLabel();
        jLfech = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLCrud = new javax.swing.JLabel();
        jlNav = new javax.swing.JLabel();
        jPFn = new javax.swing.JPanel();
        jBExit = new javax.swing.JButton();
        JBImpr = new javax.swing.JButton();
        jBConsulta = new javax.swing.JButton();
        jBGuia = new javax.swing.JButton();
        jPNav = new javax.swing.JPanel();
        jBPrimer1 = new javax.swing.JButton();
        jBAnterior = new javax.swing.JButton();
        jBSigui = new javax.swing.JButton();
        jBUltim = new javax.swing.JButton();

        jTFnom.setText("jTextField1");
        jTFnom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTFnomFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTFnomFocusLost(evt);
            }
        });

        jLnivel.setText("Nivel del usuario:");

        jRBadmin.setText("Administrador");
        jRBadmin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jRBadminFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jRBadminFocusLost(evt);
            }
        });
        jRBadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBadminActionPerformed(evt);
            }
        });

        jRBempleado.setText("Empleado");
        jRBempleado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jRBempleadoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jRBempleadoFocusLost(evt);
            }
        });
        jRBempleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBempleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPnivelLayout = new javax.swing.GroupLayout(jPnivel);
        jPnivel.setLayout(jPnivelLayout);
        jPnivelLayout.setHorizontalGroup(
            jPnivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnivelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLnivel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRBadmin, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(jRBempleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPnivelLayout.setVerticalGroup(
            jPnivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnivelLayout.createSequentialGroup()
                .addComponent(jLnivel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRBadmin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRBempleado)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jLnom.setText("Nombres:");

        jLape.setText("Apellidos:");

        jTFape.setText("jTextField1");
        jTFape.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTFapeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTFapeFocusLost(evt);
            }
        });

        jLturno.setText("Turno:");

        jRBdiurno.setText("Diurno");
        jRBdiurno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jRBdiurnoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jRBdiurnoFocusLost(evt);
            }
        });
        jRBdiurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBdiurnoActionPerformed(evt);
            }
        });

        jRBnocturno.setText("Nocturno");
        jRBnocturno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jRBnocturnoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jRBnocturnoFocusLost(evt);
            }
        });
        jRBnocturno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBnocturnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPturnoLayout = new javax.swing.GroupLayout(jPturno);
        jPturno.setLayout(jPturnoLayout);
        jPturnoLayout.setHorizontalGroup(
            jPturnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPturnoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPturnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLturno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPturnoLayout.createSequentialGroup()
                        .addGroup(jPturnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRBdiurno, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRBnocturno, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPturnoLayout.setVerticalGroup(
            jPturnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPturnoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLturno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRBdiurno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRBnocturno)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLclave.setText("Contraseña:");

        jTFclave.setText("jTextField1");
        jTFclave.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTFclaveFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTFclaveFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPclaveLayout = new javax.swing.GroupLayout(jPclave);
        jPclave.setLayout(jPclaveLayout);
        jPclaveLayout.setHorizontalGroup(
            jPclaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPclaveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPclaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLclave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTFclave))
                .addContainerGap())
        );
        jPclaveLayout.setVerticalGroup(
            jPclaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPclaveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLclave)
                .addGap(18, 18, 18)
                .addComponent(jTFclave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLfecha.setText("Fecha de nacimiento:");

        jBcontFech.setText("jButton1");

        javax.swing.GroupLayout jPfecha_nacimiLayout = new javax.swing.GroupLayout(jPfecha_nacimi);
        jPfecha_nacimi.setLayout(jPfecha_nacimiLayout);
        jPfecha_nacimiLayout.setHorizontalGroup(
            jPfecha_nacimiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPfecha_nacimiLayout.createSequentialGroup()
                .addGroup(jPfecha_nacimiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPfecha_nacimiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLfecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jBcontFech, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPfecha_nacimiLayout.setVerticalGroup(
            jPfecha_nacimiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPfecha_nacimiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLfecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jBcontFech)
                .addGap(26, 26, 26))
        );

        jLFn.setText("jLabel1");

        jBIng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBIngActionPerformed(evt);
            }
        });

        jBEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliActionPerformed(evt);
            }
        });

        jBEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEditActionPerformed(evt);
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
                    .addComponent(jBIng, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jBEli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

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

        jLCrud.setText("jLabel1");

        jlNav.setText("jLabel1");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPturno, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFnom)
                    .addComponent(jLnom, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPfecha_nacimi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLape, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTFape, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPclave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPnivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlNav)
                            .addComponent(jPNav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLCrud, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPFn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLFn, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))
                    .addComponent(jPPie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPnivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPclave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLape)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLnom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPturno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPfecha_nacimi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlNav)
                    .addComponent(jLCrud)
                    .addComponent(jLFn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPNav, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPFn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(jPPie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void ingreso() {
        contador++;
        System.out.println(contador);
        System.out.println(veri_Bedit);

        if (contador % 2 == 0) {
            if (acess != false) {

                if (veri_Bedit == true) {
                    acess = false;

                    Date fechaNaci = dateChooser.getDate();
                    SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
                    String fechaN = form.format(fechaNaci);

                    Date fechaActual = new Date();
                    long diffMilis = fechaActual.getTime() - fechaNaci.getTime();
                    long milisPorAno = 1000L * 60 * 60 * 24 * 365;
                    long edad = diffMilis / milisPorAno;
                    String edad_fin = Long.toString(edad);

                    MySQL.edit_empl(cont_flec, jTFnom.getText(), jTFape.getText(), jTFclave.getText(), grupturnsec, grupNivelsec, fechaN, edad_fin);
                    veri_Bedit = false;

                    jTFape.setEnabled(false);
                    jTFnom.setEnabled(false);
                    jTFclave.setEnabled(false);
                    dateChooser.setEnabled(false);
                    jPnivel.setEnabled(false);
                    jPturno.setEnabled(false);
                    jRBadmin.setEnabled(false);
                    jRBempleado.setEnabled(false);
                    jRBdiurno.setEnabled(false);
                    jRBnocturno.setEnabled(false);
                    jTFape.setForeground(Color.GRAY);
                    jTFnom.setForeground(Color.GRAY);
                    jTFclave.setForeground(Color.GRAY);

                    ImageIcon foto = new ImageIcon(getClass().getResource(ruta_img[0]));
                    ImageIcon mitad_1 = new ImageIcon(foto.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

                    // establece el icono en el botón
                    jBIng.setIcon(mitad_1);

                    ImageIcon foto_edit2 = new ImageIcon(getClass().getResource(ruta_img[7]));
                    ImageIcon mitad_edit2 = new ImageIcon(foto_edit2.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
                    // establece el icono en el botón
                    jBEdit.setIcon(mitad_edit2);

                    contador_edit = 0;
                } else {
                    //Desbloque el grupo de botones
                    bloq_grup = false;
                    bloq_grup();

                    //guardado
                    jTFape.setEnabled(false);
                    jTFnom.setEnabled(false);
                    jTFclave.setEnabled(false);
                    dateChooser.setEnabled(false);
                    jPnivel.setEnabled(false);
                    jPturno.setEnabled(false);
                    jRBadmin.setEnabled(false);
                    jRBempleado.setEnabled(false);
                    jRBdiurno.setEnabled(false);
                    jRBnocturno.setEnabled(false);

                    ImageIcon foto = new ImageIcon(getClass().getResource(ruta_img[0]));
                    ImageIcon mitad_1 = new ImageIcon(foto.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

                    // establece el icono en el botón
                    jBIng.setIcon(mitad_1);

                    //Entrada de datos a la base
                    //Obtencion de la fecha de nacimiento
                    Date fechaNaci = dateChooser.getDate();
                    SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
                    String fechaN = form.format(fechaNaci);

                    Date fechaActual = new Date();
                    long diffMilis = fechaActual.getTime() - fechaNaci.getTime();
                    long milisPorAno = 1000L * 60 * 60 * 24 * 365;
                    long edad = diffMilis / milisPorAno;
                    String edad_fin = Long.toString(edad);

                    //Enviar los datos                                                                                                      cantidad                               medida  img
                    MySQL.insert_empl(Tabla, jTFnom.getText(), jTFape.getText(), jTFclave.getText(), grupturnsec, grupNivelsec, fechaN, edad_fin);

//                MIRAR COMO VAN A FUNCIONAR AHORA ESTOS CONTADORES
                    cant_empls = MySQL.cantRegistros(Tabla, ID);

                    cont_label = cant_empls;
                    jLInfo.setText("Tabla: Empleados registro " + cont_label + " al " + (cant_empls));

//                  AQUI ESTABA LA CONDICION DE CUANDO LLEGABA A EL TOPE
//                    MIRAR COMO VAN AHORA ESTOS CONTADORES
                    cant_empls = MySQL.cantRegistros(Tabla, ID);
                    cont_flec = cant_empls;

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
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "Porfavor llena todos los campos faltantes", "Error de insercion", JOptionPane.WARNING_MESSAGE);

            }

        } else {
            if (veri_Bedit == true) {
                System.out.println("Entre al positivo malo");
                dateChooser.setEnabled(true);
                jTFape.setEnabled(true);
                jTFnom.setEnabled(true);
                jTFclave.setEnabled(true);
                jPnivel.setEnabled(true);
                jPturno.setEnabled(true);
                jRBadmin.setEnabled(true);
                jRBempleado.setEnabled(true);
                jRBdiurno.setEnabled(true);
                jRBnocturno.setEnabled(true);
                ImageIcon foto = new ImageIcon(getClass().getResource(ruta_img[1]));
                ImageIcon mitad_1 = new ImageIcon(foto.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

                // establece el icono en el botón
                jBIng.setIcon(mitad_1);
                jBIng.setToolTipText("Guardar");

                jTFape.setForeground(Color.BLACK);
                jTFnom.setForeground(Color.BLACK);
                jTFclave.setForeground(Color.BLACK);
            } else {
                System.out.println("entre al falso e impar");

                //Para que se bloquen los botones
                bloq_grup = true;
                bloq_grup();

                //Volver a poner vacios los jtextfield
                jTFape.setText("");
                jTFnom.setText("");
                jTFclave.setText("");
                Date Vacio_fech = null;
                dateChooser.setDate(Vacio_fech);

                buttonGroup1.clearSelection();
                buttonGroup2.clearSelection();

                //ingreso
                dateChooser.setEnabled(true);
                jTFape.setEnabled(true);
                jTFnom.setEnabled(true);
                jTFclave.setEnabled(true);
                jPnivel.setEnabled(true);
                jPturno.setEnabled(true);
                jRBadmin.setEnabled(true);
                jRBempleado.setEnabled(true);
                jRBdiurno.setEnabled(true);
                jRBnocturno.setEnabled(true);
                ImageIcon foto = new ImageIcon(getClass().getResource(ruta_img[1]));
                ImageIcon mitad_1 = new ImageIcon(foto.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

                // establece el icono en el botón
                jBIng.setIcon(mitad_1);
                jBIng.setToolTipText("Guardar");

                acess = false;

                Pjolder();

                rec_dat();
            }

        }
    }

    private void jTFnomFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFnomFocusGained
        if (jTFnom.getText().equals("Nombre")) {
            jTFnom.setForeground(Color.BLACK);
            jTFnom.setText("");
            rec_dat();
        }
    }//GEN-LAST:event_jTFnomFocusGained

    private void jTFnomFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFnomFocusLost
        if (!jTFnom.getText().isEmpty()) {

        } else {
            jTFnom.setForeground(Color.GRAY);
            jTFnom.setText("Nombre");
            rec_dat();
        }
    }//GEN-LAST:event_jTFnomFocusLost

    private void jRBadminFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jRBadminFocusGained
        rec_dat();
    }//GEN-LAST:event_jRBadminFocusGained

    private void jRBadminFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jRBadminFocusLost
        rec_dat();
    }//GEN-LAST:event_jRBadminFocusLost

    private void jRBadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBadminActionPerformed
        rec_dat();
    }//GEN-LAST:event_jRBadminActionPerformed

    private void jRBempleadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jRBempleadoFocusGained
        rec_dat();
    }//GEN-LAST:event_jRBempleadoFocusGained

    private void jRBempleadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jRBempleadoFocusLost
        rec_dat();
    }//GEN-LAST:event_jRBempleadoFocusLost

    private void jRBempleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBempleadoActionPerformed
        rec_dat();
    }//GEN-LAST:event_jRBempleadoActionPerformed

    private void jTFapeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFapeFocusGained
        if (jTFape.getText().equals("Apellido")) {
            jTFape.setForeground(Color.BLACK);
            jTFape.setText("");
            rec_dat();
        }
    }//GEN-LAST:event_jTFapeFocusGained

    private void jTFapeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFapeFocusLost
        if (!jTFape.getText().isEmpty()) {

        } else {
            jTFape.setForeground(Color.GRAY);
            jTFape.setText("Apellido");
            rec_dat();
        }
    }//GEN-LAST:event_jTFapeFocusLost

    private void jRBdiurnoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jRBdiurnoFocusGained
        rec_dat();
    }//GEN-LAST:event_jRBdiurnoFocusGained

    private void jRBdiurnoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jRBdiurnoFocusLost
        rec_dat();
    }//GEN-LAST:event_jRBdiurnoFocusLost

    private void jRBdiurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBdiurnoActionPerformed
        rec_dat();
    }//GEN-LAST:event_jRBdiurnoActionPerformed

    private void jRBnocturnoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jRBnocturnoFocusGained
        rec_dat();
    }//GEN-LAST:event_jRBnocturnoFocusGained

    private void jRBnocturnoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jRBnocturnoFocusLost
        rec_dat();
    }//GEN-LAST:event_jRBnocturnoFocusLost

    private void jRBnocturnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBnocturnoActionPerformed
        rec_dat();
    }//GEN-LAST:event_jRBnocturnoActionPerformed

    private void jTFclaveFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFclaveFocusGained
        if (jTFclave.getText().equals("****")) {
            jTFclave.setForeground(Color.BLACK);
            jTFclave.setText("");
            rec_dat();
        }
    }//GEN-LAST:event_jTFclaveFocusGained

    private void jTFclaveFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFclaveFocusLost
        if (!jTFclave.getText().isEmpty()) {

        } else {
            jTFclave.setForeground(Color.GRAY);
            jTFclave.setText("****");
            rec_dat();
        }
    }//GEN-LAST:event_jTFclaveFocusLost

    private void jBIngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBIngActionPerformed
        ingreso();

    }//GEN-LAST:event_jBIngActionPerformed

    private void jBEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliActionPerformed
        int borrado = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea borrar este registro?", "Eliminar", JOptionPane.OK_CANCEL_OPTION);
        if (borrado == 0) {
            MySQL.deleteRecord(Tabla, ID, cont_flec);

            JOptionPane.showMessageDialog(null, "Registro eliminado con exito", "Registro eliminado", JOptionPane.INFORMATION_MESSAGE);
            cont_flec--;

            try {
                PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("SELECT * FROM empleados where id_empl = " + cont_flec + ";");

                ResultSet res = (ResultSet) stp.executeQuery();

                if (res.next()) {

                    jTFnom.setText(res.getString("nombre"));
                    jTFape.setText(res.getString("apellido"));
                    jTFclave.setText(res.getString("clave"));

                    //Poner fecha
                    String fech_actual = res.getString("fecha_nacimi");
                    SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date fechaVenciS = S.parse(fech_actual);
                        dateChooser.setDate(fechaVenciS);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //set tipo de nivel
                    String selec_nivel2 = res.getString("nivel");
                    System.out.println(selec_nivel2);

                    switch (selec_nivel2.trim()) {
                        case "admin":
                            jRBadmin.setSelected(true);
                            break;
                        case "empleado":
                            jRBempleado.setSelected(true);
                            break;
                        default:
                            throw new AssertionError();
                    }

                    //set tipo de Turno
                    String selec_nivel3 = res.getString("turno");
                    System.out.println(selec_nivel3);
                    switch (selec_nivel3.trim()) {
                        case "D":
                            jRBdiurno.setSelected(true);
                            break;
                        case "N":
                            jRBnocturno.setSelected(true);
                            break;
                        default:
                            throw new AssertionError();
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(frmiEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }

            // RESET PARA EL LABEL DE POSICIONES
            cant_empls = MySQL.cantRegistros(Tabla, ID);
            jLInfo.setText("Tabla: Empleados registro  " + cont_flec + "  al " + (cant_empls));

            bloc_sigui();
            bloc_ant();
        }
        if (borrado == 2) {
        }
    }//GEN-LAST:event_jBEliActionPerformed

    private void jBEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditActionPerformed
        contador_edit++;

        if (contador_edit % 2 == 0) {

            System.out.println("entre 2");
            //segundo toque en caso de que no quiera hacer la edicion
            //que bloque y boolean de confirmacion para saber asi que no le resta al contador
            //cambio de icono
            ImageIcon foto_edit2 = new ImageIcon(getClass().getResource(ruta_img[7]));
            ImageIcon mitad_edit2 = new ImageIcon(foto_edit2.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
            // establece el icono en el botón
            jBEdit.setIcon(mitad_edit2);

            //bloqueo de cajas
            jTFape.setEnabled(false);
            jTFnom.setEnabled(false);
            jTFclave.setEnabled(false);
            dateChooser.setEnabled(false);
            jPnivel.setEnabled(false);
            jPturno.setEnabled(false);
            jRBadmin.setEnabled(false);
            jRBempleado.setEnabled(false);
            jRBdiurno.setEnabled(false);
            jRBnocturno.setEnabled(false);

            //contador en 2 para que cuando vuelvan a darle a ingresar siga correctamente
            //veri_Bedit en false para que no active las condiciones
            veri_Bedit = false;
            contador = 2;
            acess = false;

            //le restablecemos el icono de ingreso al boton
            ImageIcon foto = new ImageIcon(getClass().getResource(ruta_img[0]));
            ImageIcon mitad_1 = new ImageIcon(foto.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

            // establece el icono en el botón
            jBIng.setIcon(mitad_1);

            jTFape.setForeground(Color.GRAY);
            jTFclave.setForeground(Color.GRAY);
            jTFnom.setForeground(Color.GRAY);

            //Reintegracion
            try {
                PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("SELECT * FROM empleados where id_empl = " + cont_flec + ";");

                ResultSet res = (ResultSet) stp.executeQuery();

                if (res.next()) {

                    jTFnom.setText(res.getString("nombre"));
                    jTFape.setText(res.getString("apellido"));
                    jTFclave.setText(res.getString("clave"));

                    //Poner fecha
                    String fech_actual = res.getString("fecha_nacimi");
                    SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date fechaVenciS = S.parse(fech_actual);
                        dateChooser.setDate(fechaVenciS);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //set tipo de nivel
                    String selec_nivel2 = res.getString("nivel");
                    System.out.println(selec_nivel2);

                    switch (selec_nivel2.trim()) {
                        case "admin":
                            jRBadmin.setSelected(true);
                            break;
                        case "empleado":
                            jRBempleado.setSelected(true);
                            break;
                        default:
                            throw new AssertionError();
                    }

                    //set tipo de Turno
                    String selec_nivel3 = res.getString("turno");
                    System.out.println(selec_nivel3);
                    switch (selec_nivel3.trim()) {
                        case "D":
                            jRBdiurno.setSelected(true);
                            break;
                        case "N":
                            jRBnocturno.setSelected(true);
                            break;
                        default:
                            throw new AssertionError();
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(frmiEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            System.out.println("entre 1");
            ImageIcon foto_edit = new ImageIcon(getClass().getResource("/imagenes/deshacer.png"));
            ImageIcon mitad_edit = new ImageIcon(foto_edit.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
            // establece el icono en el botón
            jBEdit.setIcon(mitad_edit);

            //Primer toque
            contador = 0;
            veri_Bedit = true;
            acess = true;
            ingreso();

        }
    }//GEN-LAST:event_jBEditActionPerformed

    private void jBExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExitActionPerformed
        setVisible(false);
        contador = 0;
    }//GEN-LAST:event_jBExitActionPerformed

    private void JBImprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBImprActionPerformed
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JFimp().setVisible(true);
//            }
//        });
    }//GEN-LAST:event_JBImprActionPerformed

    private void jBConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConsultaActionPerformed
        MySQL.MySQLConnection("root", "", "minimarket");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            System.out.println("query");
            String query = "SELECT * FROM empleados";
            connection = Conexion;
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
            tabla_empl = new String[rowCount][columnCount];

            int row = 0;

            System.out.println("avance 3");
            while (resultSet.next()) {
                System.out.println("avance 4");
                for (int col = 0; col < columnCount; col++) {
                    tabla_empl[row][col] = resultSet.getObject(col + 1).toString();

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
                new JFconsultaEmpl().setVisible(true);
            }
        });
    }//GEN-LAST:event_jBConsultaActionPerformed

    private void jBGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuiaActionPerformed
        File file = new File("C:/Users/santi/Downloads/Segundo consolidado/Minimarket_Bases/Minimarket_bases2/src/imagenes/GuiaProducto.pdf");
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

    private void jBPrimer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPrimer1ActionPerformed
        /*
        *Lo que hacemos aqui es para que vaya a el primer dato y me los muestre
        *
         */

        //Base de datos
        try {
            PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("SELECT * FROM empleados where id_empl = 1;");

            ResultSet res = (ResultSet) stp.executeQuery();

            if (res.next()) {

                jTFnom.setText(res.getString("nombre"));
                jTFape.setText(res.getString("apellido"));
                jTFclave.setText(res.getString("clave"));

                //Poner fecha
                String fech_actual = res.getString("fecha_nacimi");
                SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fechaVenciS = S.parse(fech_actual);
                    dateChooser.setDate(fechaVenciS);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //set tipo de nivel
                String selec_nivel2 = res.getString("nivel");
                System.out.println(selec_nivel2);

                switch (selec_nivel2.trim()) {
                    case "admin":
                        jRBadmin.setSelected(true);
                        break;
                    case "empleado":
                        jRBempleado.setSelected(true);
                        break;
                    default:
                        throw new AssertionError();
                }

                //set tipo de Turno
                String selec_nivel3 = res.getString("turno");
                System.out.println(selec_nivel3);
                switch (selec_nivel3.trim()) {
                    case "D":
                        jRBdiurno.setSelected(true);
                        break;
                    case "N":
                        jRBnocturno.setSelected(true);
                        break;
                    default:
                        throw new AssertionError();
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(frmiEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }

        // RESET PARA EL LABEL DE POSICIONES
        cant_empls = MySQL.cantRegistros(Tabla, ID);
        jLInfo.setText("Tabla: Empleados registro  1  al " + (cant_empls));

        cont_flec = 1;

        jBUltim.setEnabled(true);
        jBSigui.setEnabled(true);

        bloc_ant();

    }//GEN-LAST:event_jBPrimer1ActionPerformed

    private void jBAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAnteriorActionPerformed
        cont_flec--;

        try {
            PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("SELECT * FROM empleados where id_empl = " + cont_flec + ";");

            ResultSet res = (ResultSet) stp.executeQuery();

            if (res.next()) {

                jTFnom.setText(res.getString("nombre"));
                jTFape.setText(res.getString("apellido"));
                jTFclave.setText(res.getString("clave"));

                //Poner fecha
                String fech_actual = res.getString("fecha_nacimi");
                SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fechaVenciS = S.parse(fech_actual);
                    dateChooser.setDate(fechaVenciS);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //set tipo de nivel
                String selec_nivel2 = res.getString("nivel");
                System.out.println(selec_nivel2);

                switch (selec_nivel2.trim()) {
                    case "admin":
                        jRBadmin.setSelected(true);
                        break;
                    case "empleado":
                        jRBempleado.setSelected(true);
                        break;
                    default:
                        throw new AssertionError();
                }

                //set tipo de Turno
                String selec_nivel3 = res.getString("turno");
                System.out.println(selec_nivel3);
                switch (selec_nivel3.trim()) {
                    case "D":
                        jRBdiurno.setSelected(true);
                        break;
                    case "N":
                        jRBnocturno.setSelected(true);
                        break;
                    default:
                        throw new AssertionError();
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(frmiEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }

        // RESET PARA EL LABEL DE POSICIONES
        cant_empls = MySQL.cantRegistros(Tabla, ID);
        jLInfo.setText("Tabla: Empleados registro  " + cont_flec + "  al " + (cant_empls));

        bloc_ant();
        bloc_sigui();
    }//GEN-LAST:event_jBAnteriorActionPerformed

    private void jBSiguiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSiguiActionPerformed

        //Bases de datos
        cont_flec++;

        try {
            PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("SELECT * FROM empleados where id_empl = " + cont_flec + ";");

            ResultSet res = (ResultSet) stp.executeQuery();

            if (res.next()) {

                jTFnom.setText(res.getString("nombre"));
                jTFape.setText(res.getString("apellido"));
                jTFclave.setText(res.getString("clave"));

                //Poner fecha
                String fech_actual = res.getString("fecha_nacimi");
                SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fechaVenciS = S.parse(fech_actual);
                    dateChooser.setDate(fechaVenciS);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //set tipo de nivel
                String selec_nivel2 = res.getString("nivel");
                System.out.println(selec_nivel2);

                switch (selec_nivel2.trim()) {
                    case "admin":
                        jRBadmin.setSelected(true);
                        break;
                    case "empleado":
                        jRBempleado.setSelected(true);
                        break;
                    default:
                        throw new AssertionError();
                }

                //set tipo de Turno
                String selec_nivel3 = res.getString("turno");
                System.out.println(selec_nivel3);
                switch (selec_nivel3.trim()) {
                    case "D":
                        jRBdiurno.setSelected(true);
                        break;
                    case "N":
                        jRBnocturno.setSelected(true);
                        break;
                    default:
                        throw new AssertionError();
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(frmiEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }

        // RESET PARA EL LABEL DE POSICIONES
        cant_empls = MySQL.cantRegistros(Tabla, ID);
        jLInfo.setText("Tabla: Empleados registro  " + cont_flec + "  al " + (cant_empls));

        bloc_sigui();
        bloc_ant();

        //***************
    }//GEN-LAST:event_jBSiguiActionPerformed

    private void jBUltimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBUltimActionPerformed
        /*
        *Lo que hacemos aqui es para primero entre al ciclo y lo lleve al ultimo registro que hizo
        *Esto mismo ejecuta un contador para los botones del medio del nav se ejecuten correctamente y el contador del label se ejecute contando los registros en los que vamos
         */

        int ult_regis = MySQL.cantRegistros(Tabla, ID);

        try {
            PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("SELECT * FROM empleados where id_empl = " + ult_regis + ";");

            ResultSet res = (ResultSet) stp.executeQuery();

            if (res.next()) {

                jTFnom.setText(res.getString("nombre"));
                jTFape.setText(res.getString("apellido"));
                jTFclave.setText(res.getString("clave"));

                //Poner fecha
                String fech_actual = res.getString("fecha_nacimi");
                SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fechaVenciS = S.parse(fech_actual);
                    dateChooser.setDate(fechaVenciS);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //set tipo de nivel
                String selec_nivel2 = res.getString("nivel");
                System.out.println(selec_nivel2);

                switch (selec_nivel2.trim()) {
                    case "admin":
                        jRBadmin.setSelected(true);
                        break;
                    case "empleado":
                        jRBempleado.setSelected(true);
                        break;
                    default:
                        throw new AssertionError();
                }

                //set tipo de Turno
                String selec_nivel3 = res.getString("turno");
                System.out.println(selec_nivel3);
                switch (selec_nivel3.trim()) {
                    case "D":
                        jRBdiurno.setSelected(true);
                        break;
                    case "N":
                        jRBnocturno.setSelected(true);
                        break;
                    default:
                        throw new AssertionError();
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(frmiEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }

        // RESET PARA EL LABEL DE POSICIONES
        cant_empls = MySQL.cantRegistros(Tabla, ID);
        jLInfo.setText("Tabla: Empleados registro " + cant_empls + "  al " + (cant_empls));

        cont_flec = ult_regis;

        jBPrimer1.setEnabled(true);
        jBAnterior.setEnabled(true);

        bloc_sigui();

    }//GEN-LAST:event_jBUltimActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBImpr;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jBAnterior;
    private javax.swing.JButton jBConsulta;
    private javax.swing.JToggleButton jBEdit;
    private javax.swing.JButton jBEli;
    private javax.swing.JButton jBExit;
    private javax.swing.JButton jBGuia;
    private javax.swing.JButton jBIng;
    private javax.swing.JButton jBPrimer1;
    private javax.swing.JButton jBSigui;
    private javax.swing.JButton jBUltim;
    private javax.swing.JButton jBcontFech;
    private javax.swing.JLabel jLCrud;
    private javax.swing.JLabel jLFn;
    private javax.swing.JLabel jLInfo;
    private javax.swing.JLabel jLape;
    private javax.swing.JLabel jLclave;
    private javax.swing.JLabel jLfech;
    private javax.swing.JLabel jLfecha;
    private javax.swing.JLabel jLnivel;
    private javax.swing.JLabel jLnom;
    private javax.swing.JLabel jLturno;
    private javax.swing.JPanel jPFn;
    private javax.swing.JPanel jPNav;
    private javax.swing.JPanel jPPie;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPclave;
    private javax.swing.JPanel jPfecha_nacimi;
    private javax.swing.JPanel jPnivel;
    private javax.swing.JPanel jPturno;
    private javax.swing.JRadioButton jRBadmin;
    private javax.swing.JRadioButton jRBdiurno;
    private javax.swing.JRadioButton jRBempleado;
    private javax.swing.JRadioButton jRBnocturno;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTFape;
    private javax.swing.JTextField jTFclave;
    private javax.swing.JTextField jTFnom;
    private javax.swing.JLabel jlNav;
    // End of variables declaration//GEN-END:variables
}
