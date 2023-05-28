package vista.Cliente;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import vista.Producto.JFimp;
import vista.frmiPrincipal;
import static vista.frmiPrincipal.nFils;

public class frmiCliente extends javax.swing.JInternalFrame {

    public static int contador = 0, contador_eli = 0, cont_fil = 0, cont_fil_nav = 0, cont_flec = 0, cont_label = 0, columna = 0, cont_filM = 0, cant_med = 0;
    public final int ancho = 15, alto = 15;
    public static String selec_med = "", combo_result = "";

    public Font Arial = new Font("Arial", Font.BOLD, 14);

    //Vector de iconos
    //                                  0                           1                       2                           3                       4                       5                       6
    public String[] ruta_img = {"/imagenes/nota.png", "/imagenes/disco-flexible.png", "/imagenes/producto.png", "/imagenes/Ultima.png", "/imagenes/anterior.png", "/imagenes/siguiente.png", "/imagenes/primera.png",
        "/imagenes/editar-codigo.png", "/imagenes/expediente.png", "/imagenes/manual.png", "/imagenes/buscando.png", "/imagenes/impresora.png", "/imagenes/cerrar-sesion.png", "/imagenes/camara.png", "/imagenes/salida.png"};

    //                 7                               8                           9                  10                      11                          12                            13                          14
    //Tabla de consulta
    public static String[] sCabecera = {"Id", "Nombres", "Apellidos", "Direccion", "Telefono", "Genero", "FechaNacimiento", "Edad", "EstadoCivil", "Ruta_foto"};
    public static String[][] tablaCli = new String[frmiPrincipal.nFils][10];
    public static String[][] DatosCli = new String[frmiPrincipal.nFils][10];
    public static File archivo;
    //final de tabla

    //validador de seteador del combo
    //Fecha de vencimiento
    public static String fechaNac = "";
    public static JDateChooser dateChooser;

    public frmiCliente() {
        initComponents();

        setSize(501, 395);
        jCalFecNac.setEnabled(false);
        setResizable(false); // Deshabilitar el redimensionamiento
        setLocation(0, 0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Placeholders();

        comboGenero.removeAllItems();
        comboGenero.addItem("Sin seleccionar");
        comboGenero.addItem("Masculino");
        comboGenero.addItem("Femenino");



        //LBLS
        jlFoto.setText("Foto");
        lblIdCli.setText("ID");
        lblNomCli.setText("Nombre");
        lblApeCli.setText("Apellidos");
        lblDireCli.setText("Dirección");
        lblEstCiv.setText("Estado Civil");
        lblTelCli.setText("Telefono");
        lblFecNac.setText("Fecha Nacimiento");
        lblGenCli.setText("Genero");

        jlFoto.setFont(Arial);
        lblIdCli.setFont(Arial);
        lblApeCli.setFont(Arial);
        lblDireCli.setFont(Arial);
        lblEstCiv.setFont(Arial);
        lblFecNac.setFont(Arial);
        lblGenCli.setFont(Arial);
        lblNomCli.setFont(Arial);
        lblTelCli.setFont(Arial);

        //lblNav
        jlNav.setForeground(new java.awt.Color(0, 0, 0));
        jlNav.setFont(Arial);
        jlNav.setText("Nav");
        //lblmmto
        jLCrud.setForeground(new java.awt.Color(0, 0, 0));
        jLCrud.setFont(Arial);
        jLCrud.setText("Mtto");
        //lblFN
        jLFn.setForeground(new java.awt.Color(0, 0, 0));
        jLFn.setFont(Arial);
        jLFn.setText("Fn Especiales");

        //lblinfo
        jLInfo.setText("Tabla: Producto registro " + cont_label + " al " + (nFils));

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

        jCalFecNac.add(dateChooser);

        //Bloqueo del nav
        jBSigui.setEnabled(false);
        jBAnterior.setEnabled(false);
        jBPrimer1.setEnabled(false);
        jBUltim.setEnabled(false);

        //Panel principal Bloqueado
        txtIdCli.setEnabled(false);
        txtNomCli.setEnabled(false);
        txtApeCli.setEnabled(false);
        txtDireCli.setEnabled(false);
        txtTelCli.setEnabled(false);
        jbImg.setEnabled(false);
        dateChooser.setEnabled(false);
        comboGenero.setEnabled(false);
        jCBestado.setEnabled(false);

        //BOTONES
    }

    public void Placeholders() {
        //Caajas de texto
        txtIdCli.setForeground(Color.GRAY);
        txtIdCli.setText("Id");
        txtIdCli.setColumns(5);

        txtNomCli.setForeground(Color.GRAY);
        txtNomCli.setText("Nombre");
        txtNomCli.setColumns(5);

        txtApeCli.setForeground(Color.GRAY);
        txtApeCli.setText("Apellidos");
        txtApeCli.setColumns(5);

        txtDireCli.setForeground(Color.GRAY);
        txtDireCli.setText("Dirección");
        txtDireCli.setColumns(5);

        txtTelCli.setForeground(Color.GRAY);
        txtTelCli.setText("Telefono");
        txtTelCli.setColumns(5);

    }

    public void bloc_sigui() {
        if (cont_flec == cont_fil_nav) {
            jBSigui.setEnabled(false);
            jBUltim.setEnabled(false);
        } else {
            jBSigui.setEnabled(true);
            jBUltim.setEnabled(true);
        }
        bloc_ant();
    }

    public void bloc_ant() {
        if (cont_flec == 0) {
            jBAnterior.setEnabled(false);
            jBPrimer1.setEnabled(false);
        } else {
            jBAnterior.setEnabled(true);
            jBPrimer1.setEnabled(true);
        }
        bloc_sigui();
    }

    public void rec_dat() {
        if (txtIdCli.getText().equalsIgnoreCase("Id") || txtApeCli.getText().equalsIgnoreCase("Apellidos") || txtNomCli.getText().equalsIgnoreCase("Nombres") || txtDireCli.getText().equalsIgnoreCase("Dirección") || dateChooser.getDate() == null || comboGenero.getSelectedIndex() == 0) {
            jbImg.setEnabled(false);
        } else {
            jbImg.setEnabled(true);
        }
    }

    public void ingreso() {
        contador++;

        if (contador % 2 == 0) {
            if (archivo != null) {

                txtIdCli.setEnabled(false);
                txtNomCli.setEnabled(false);
                txtApeCli.setEnabled(false);
                txtDireCli.setEnabled(false);
                txtTelCli.setEnabled(false);
                jbImg.setEnabled(false);
                dateChooser.setEnabled(false);
                comboGenero.setEnabled(false);
                jCBestado.setEnabled(false);

                // establece el icono en el botón
                ImageIcon foto = new ImageIcon(getClass().getResource(ruta_img[0]));
                ImageIcon mitad_1 = new ImageIcon(foto.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
                jBIng.setIcon(mitad_1);

                //Obtencion de datos
                DatosCli[cont_fil][0] = txtIdCli.getText();
                DatosCli[cont_fil][1] = txtNomCli.getText();
                DatosCli[cont_fil][2] = txtApeCli.getText();
                DatosCli[cont_fil][3] = txtDireCli.getText();
                DatosCli[cont_fil][4] = txtTelCli.getText();

                System.out.println(combo_result);
                //Obtencion de datos del combox
                DatosCli[cont_fil][5] = selec_med;

                //Obtencion de la fecha de vencimiento
                Date fechaVenci = dateChooser.getDate();
                SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
                String fechaNac = form.format(fechaVenci);

                DatosCli[cont_fil][6] = fechaNac;
                System.out.println(fechaNac);

                //EDAD [7]
                Date fechaActual = new Date();
                long diffMilis = fechaActual.getTime() - fechaVenci.getTime();
                long milisPorAno = 1000L * 60 * 60 * 24 * 365;
                long edad = diffMilis / milisPorAno;

                DatosCli[cont_fil][7] = Long.toString(edad);

                String estadoCivil = "";

                

               //DatosCli[cont_fil][8] = selec_estado;

                //Obtencion de la imagen
                DatosCli[cont_fil][9] = archivo.getPath();

                cont_fil++;
                cont_fil_nav++;
                cont_label = cont_fil;
                jLInfo.setText("Tabla: Producto registro " + cont_label + " al " + (nFils));

                jBIng.setToolTipText("Ingreso");

                if (frmiPrincipal.nFils == cont_fil) {

                    jBIng.setEnabled(false);
                    jBPrimer1.setEnabled(true);
                    jBAnterior.setEnabled(true);
                    cont_fil_nav = cont_fil_nav - 1;
                    cont_flec = cont_fil_nav;

                    //Esto por si solo hay un dato
                    if (nFils == 1) {
                        jBSigui.setEnabled(false);
                        jBAnterior.setEnabled(false);
                        jBPrimer1.setEnabled(false);
                        jBUltim.setEnabled(false);
                    }

                    //PAra que deje la imagen puesta
                    for (int j = 0; j < DatosCli[cont_fil_nav].length; j++) {

                        txtIdCli.setText(DatosCli[cont_fil_nav][0]);
                        txtNomCli.setText(DatosCli[cont_fil_nav][1]);
                        txtApeCli.setText(DatosCli[cont_fil_nav][2]);
                        txtDireCli.setText(DatosCli[cont_fil_nav][3]);
                        txtTelCli.setText(DatosCli[cont_fil_nav][4]);

                        //set Genero
                        String selec_act = DatosCli[cont_fil_nav][5];
                        System.out.println(selec_act);
                        String selec_act2 = selec_act.substring(2);
                        System.out.println(selec_act2);
                        System.out.println(selec_act2.trim());

                        switch (selec_act2.trim()) {
                            case "Masculino":
                                comboGenero.setSelectedIndex(1);
                                break;
                            case "Femenino":
                                comboGenero.setSelectedIndex(2);
                                break;
                            default:
                                throw new AssertionError();
                        }

                        //Set fecha
                        //Obtencion de la fecha de NACIMIENTO
                        String fech_actual = DatosCli[cont_fil_nav][6];
                        SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");

                        try {
                            Date fechaVenciS = S.parse(fech_actual);
                            dateChooser.setDate(fechaVenciS);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        //Aqui iria el combo de estado
                        

                        //Poner la imagen en el label
                        ImageIcon proFotorec = new ImageIcon(DatosCli[cont_fil_nav][9]);
                        ImageIcon icono_prorec = new ImageIcon(proFotorec.getImage().getScaledInstance(jLImg.getWidth(), jLImg.getHeight(), Image.SCALE_DEFAULT));
                        jLImg.setIcon(icono_prorec);

                    }

                    bloc_sigui();
                    bloc_ant();
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "Aun hay campos sin llenar !Completalos!", "Error de insercion", JOptionPane.WARNING_MESSAGE);
                contador--;
            }

        } else {
            //Volver a poner vacios los jtextfield
            txtIdCli.setText("");
            txtNomCli.setText("");
            txtApeCli.setText("");
            txtDireCli.setText("");
            txtTelCli.setText("");
            Date Vacio_fech = null;
            dateChooser.setDate(Vacio_fech);
            comboGenero.setSelectedIndex(0);
            Placeholders();
            jCBestado.setSelectedIndex(0);

            //Para que se reinicie el label
            ImageIcon proRei = new ImageIcon(getClass().getResource(""));
            ImageIcon rei_pro = new ImageIcon(proRei.getImage().getScaledInstance(jLImg.getWidth(), jLImg.getHeight(), Image.SCALE_DEFAULT));
            jLImg.setIcon(rei_pro);

            dateChooser.setEnabled(true);
            comboGenero.setEnabled(true);
            txtIdCli.setEnabled(true);
            txtNomCli.setEnabled(true);
            txtApeCli.setEnabled(true);
            txtDireCli.setEnabled(true);
            txtTelCli.setEnabled(true);
            jbImg.setEnabled(true);
            ImageIcon foto = new ImageIcon(getClass().getResource(ruta_img[1]));
            ImageIcon mitad_1 = new ImageIcon(foto.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
            jCBestado.setEnabled(true);

            // establece el icono en el botón
            jBIng.setIcon(mitad_1);
            jBIng.setToolTipText("Guardar");

            //Esto para que la condicion del bloque del boton cobre sentido
            archivo = null;

            rec_dat();

        }
    }

    public void desbloqueoNavTodos() {
        jBPrimer1.setEnabled(true);
        jBAnterior.setEnabled(true);
        jBUltim.setEnabled(true);
        jBSigui.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPCent = new javax.swing.JPanel();
        lblIdCli = new javax.swing.JLabel();
        txtIdCli = new javax.swing.JTextField();
        lblNomCli = new javax.swing.JLabel();
        txtNomCli = new javax.swing.JTextField();
        lblApeCli = new javax.swing.JLabel();
        txtApeCli = new javax.swing.JTextField();
        lblDireCli = new javax.swing.JLabel();
        txtDireCli = new javax.swing.JTextField();
        jPfoto = new javax.swing.JPanel();
        jLImg = new javax.swing.JLabel();
        jbImg = new javax.swing.JButton();
        jlFoto = new javax.swing.JLabel();
        lblFecNac = new javax.swing.JLabel();
        jCalFecNac = new javax.swing.JButton();
        lblGenCli = new javax.swing.JLabel();
        comboGenero = new javax.swing.JComboBox<>();
        lblTelCli = new javax.swing.JLabel();
        txtTelCli = new javax.swing.JTextField();
        lblEstCiv = new javax.swing.JLabel();
        jCBestado = new javax.swing.JComboBox<>();
        jPPie = new javax.swing.JPanel();
        jLInfo = new javax.swing.JLabel();
        jLfech = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPNav = new javax.swing.JPanel();
        jBPrimer1 = new javax.swing.JButton();
        jBAnterior = new javax.swing.JButton();
        jBSigui = new javax.swing.JButton();
        jBUltim = new javax.swing.JButton();
        jLFn = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jBIng = new javax.swing.JButton();
        jBEdit = new javax.swing.JButton();
        jBEli = new javax.swing.JButton();
        jLCrud = new javax.swing.JLabel();
        jlNav = new javax.swing.JLabel();
        jPFn = new javax.swing.JPanel();
        jBExit = new javax.swing.JButton();
        JBImpr = new javax.swing.JButton();
        jBConsulta = new javax.swing.JButton();
        jBGuia = new javax.swing.JButton();

        lblIdCli.setText("jLabel1");

        txtIdCli.setText("jTextField1");
        txtIdCli.setMaximumSize(new java.awt.Dimension(71, 22));
        txtIdCli.setMinimumSize(new java.awt.Dimension(71, 22));
        txtIdCli.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIdCliFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIdCliFocusLost(evt);
            }
        });
        txtIdCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdCliActionPerformed(evt);
            }
        });

        lblNomCli.setText("jLabel1");

        txtNomCli.setText("jTextField1");
        txtNomCli.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNomCliFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNomCliFocusLost(evt);
            }
        });
        txtNomCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomCliActionPerformed(evt);
            }
        });

        lblApeCli.setText("jLabel1");

        txtApeCli.setText("jTextField1");
        txtApeCli.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtApeCliFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtApeCliFocusLost(evt);
            }
        });

        lblDireCli.setText("jLabel1");

        txtDireCli.setText("jTextField1");
        txtDireCli.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDireCliFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDireCliFocusLost(evt);
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

        lblFecNac.setText("jLabel1");

        jCalFecNac.setText("jButton1");
        jCalFecNac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCalFecNacActionPerformed(evt);
            }
        });

        lblGenCli.setText("jLabel2");

        comboGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Medida", "Libra", "Kilo", "Unidad" }));
        comboGenero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                comboGeneroFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                comboGeneroFocusLost(evt);
            }
        });
        comboGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboGeneroActionPerformed(evt);
            }
        });

        lblTelCli.setText("jLabel1");

        txtTelCli.setText("jTextField1");
        txtTelCli.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTelCliFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTelCliFocusLost(evt);
            }
        });

        lblEstCiv.setText("jLabel1");

        jCBestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estado", "Soltero", "Casado", "Divorciado", "Viudo" }));

        jLInfo.setText("jLabel1");

        jLfech.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLfech.setText("jLabel1");
        jLfech.setToolTipText("");

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
                .addComponent(jBPrimer1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPNavLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBPrimer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBAnterior, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBSigui, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBUltim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLFn.setText("jLabel1");

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
                    .addComponent(jBIng, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jBEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBEli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

        javax.swing.GroupLayout jPPieLayout = new javax.swing.GroupLayout(jPPie);
        jPPie.setLayout(jPPieLayout);
        jPPieLayout.setHorizontalGroup(
            jPPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPPieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPPieLayout.createSequentialGroup()
                        .addComponent(jLInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLfech, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPPieLayout.createSequentialGroup()
                        .addGroup(jPPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlNav)
                            .addComponent(jPNav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLCrud, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPFn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLFn, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPPieLayout.setVerticalGroup(
            jPPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPPieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlNav)
                    .addComponent(jLCrud)
                    .addComponent(jLFn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPNav, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPFn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLInfo)
                    .addComponent(jLfech))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPCentLayout = new javax.swing.GroupLayout(jPCent);
        jPCent.setLayout(jPCentLayout);
        jPCentLayout.setHorizontalGroup(
            jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPCentLayout.createSequentialGroup()
                        .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFecNac, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblApeCli)
                            .addComponent(txtApeCli, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTelCli, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelCli, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCalFecNac, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< HEAD
                            .addComponent(lblGenCli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
=======
>>>>>>> alex
                            .addGroup(jPCentLayout.createSequentialGroup()
                                .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEstCiv)
                                    .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDireCli)
                                    .addComponent(txtNomCli, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
<<<<<<< HEAD
                                    .addComponent(txtDireCli, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNomCli, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCBestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 31, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
=======
                                    .addComponent(txtDireCli, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPCentLayout.createSequentialGroup()
                                .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGenCli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPCentLayout.createSequentialGroup()
                                        .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNomCli, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jCBestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 38, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)))
>>>>>>> alex
                        .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPCentLayout.createSequentialGroup()
                                .addComponent(jPfoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPCentLayout.createSequentialGroup()
                                .addComponent(jlFoto)
                                .addGap(76, 76, 76))))
                    .addComponent(jPPie, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                        .addGap(9, 9, 9)
                        .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPCentLayout.createSequentialGroup()
                                .addComponent(lblNomCli)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPCentLayout.createSequentialGroup()
                                .addComponent(lblIdCli)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDireCli)
                            .addComponent(lblApeCli))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDireCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApeCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGenCli)
                            .addComponent(lblTelCli))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
<<<<<<< HEAD
                        .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
=======
                        .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
>>>>>>> alex
                            .addComponent(lblEstCiv)
                            .addComponent(lblFecNac))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPCentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCalFecNac)
                            .addComponent(jCBestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPPie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPCent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPCent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExitActionPerformed
        setVisible(false);
        contador = 0;
    }//GEN-LAST:event_jBExitActionPerformed

    private void JBImprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBImprActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFimp().setVisible(true);
            }
        });
    }//GEN-LAST:event_JBImprActionPerformed

    private void jBConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConsultaActionPerformed

        for (int i = 0; i < tablaCli.length; i++) {
            for (int l = 0; l <= 9; l++) {
                tablaCli[i][l] = frmiCliente.DatosCli[i][l];
            }
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmConsultaCliente().setVisible(true);
            }
        });
    }//GEN-LAST:event_jBConsultaActionPerformed

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

    private void jBPrimer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPrimer1ActionPerformed

//        jBPrimer1.setEnabled(false);
//        jBAnterior.setEnabled(false);
//        jBUltim.setEnabled(true);
//        jBSigui.setEnabled(true);
        for (int i = 0; i < DatosCli.length; i++) {

            txtIdCli.setText(DatosCli[0][0]);
            txtNomCli.setText(DatosCli[0][1]);
            txtApeCli.setText(DatosCli[0][2]);
            txtDireCli.setText(DatosCli[0][3]);
            txtTelCli.setText(DatosCli[0][4]);

            //set Genero
            String selec_act = DatosCli[0][5];
            System.out.println(selec_act);
            String selec_act2 = selec_act.substring(2);
            System.out.println(selec_act2);
            System.out.println(selec_act2.trim());

            switch (selec_act2.trim()) {
                case "Masculino":
                    comboGenero.setSelectedIndex(1);
                    break;
                case "Femenino":
                    comboGenero.setSelectedIndex(2);
                    break;
                default:
                    throw new AssertionError();
            }

            //Set fecha
            //Obtencion de la fecha de vencimiento
            String fech_actual = DatosCli[0][6];
            SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date fechaVenciS = S.parse(fech_actual);
                dateChooser.setDate(fechaVenciS);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //Aqui iria el comobo de estado

            //Poner la imagen en el label
            ImageIcon proFoto2 = new ImageIcon(DatosCli[0][9]);
            ImageIcon icono_pro2 = new ImageIcon(proFoto2.getImage().getScaledInstance(jLImg.getWidth(), jLImg.getHeight(), Image.SCALE_DEFAULT));
            jLImg.setIcon(icono_pro2);

            //Para que el contador sepa la posicion
            cont_flec = cont_flec - cont_flec;
            if (cont_label == 0) {
                jLInfo.setText("Tabla: Producto registro  1  al " + (nFils));
            } else {
                cont_label = (cont_label - nFils) + 1;
                jLInfo.setText("Tabla: Producto registro " + cont_label + " al " + (nFils));
            }
            bloc_ant();
        }

    }//GEN-LAST:event_jBPrimer1ActionPerformed

    private void jBAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAnteriorActionPerformed
        cont_flec--;
        for (int j = 0; j < DatosCli[cont_flec].length; j++) {
            if (j == 0) {

                txtIdCli.setText(DatosCli[cont_flec][0]);
                txtNomCli.setText(DatosCli[cont_flec][1]);
                txtApeCli.setText(DatosCli[cont_flec][2]);
                txtDireCli.setText(DatosCli[cont_flec][3]);
                txtTelCli.setText(DatosCli[cont_flec][4]);

                //set genero
                String selec_act = DatosCli[cont_flec][5];
                System.out.println(selec_act);
                String selec_act2 = selec_act.substring(2);
                System.out.println(selec_act2);
                System.out.println(selec_act2.trim());

                switch (selec_act2.trim()) {
                    case "Masculino":
                        comboGenero.setSelectedIndex(1);
                        break;
                    case "Femenino":
                        comboGenero.setSelectedIndex(2);
                        break;
                    default:
                        throw new AssertionError();
                }

                //Set fecha
                //Obtencion de la fecha de vencimiento
                String fech_actual = DatosCli[cont_flec][6];
                SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    Date fechaVenciS = S.parse(fech_actual);
                    dateChooser.setDate(fechaVenciS);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //Aqui va el combo de estado

                //Poner la imagen en el label
                ImageIcon proFotorec = new ImageIcon(DatosCli[cont_flec][9]);
                ImageIcon icono_prorec = new ImageIcon(proFotorec.getImage().getScaledInstance(jLImg.getWidth(), jLImg.getHeight(), Image.SCALE_DEFAULT));
                jLImg.setIcon(icono_prorec);
            }
        }

        jLInfo.setText("Tabla: Producto registro " + cont_label + " al " + (nFils));
        if (cont_label == 1) {
            jLInfo.setText("Tabla: Producto registro  1  al " + (nFils));
        } else {
            cont_label--;
            jLInfo.setText("Tabla: Producto registro " + cont_label + " al " + (nFils));
        }
        bloc_ant();
    }//GEN-LAST:event_jBAnteriorActionPerformed

    private void jBSiguiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSiguiActionPerformed
        cont_flec++;
        for (int j = 0; j < DatosCli[cont_flec].length; j++) {
            if (j == 0) {

                txtIdCli.setText(DatosCli[cont_flec][0]);
                txtNomCli.setText(DatosCli[cont_flec][1]);
                txtApeCli.setText(DatosCli[cont_flec][2]);
                txtDireCli.setText(DatosCli[cont_flec][3]);
                txtTelCli.setText(DatosCli[cont_flec][4]);

                //set Medida
                String selec_act = DatosCli[cont_flec][5];
                System.out.println(selec_act);
                String selec_act2 = selec_act.substring(2);
                System.out.println(selec_act2);
                System.out.println(selec_act2.trim());

                switch (selec_act2.trim()) {
                    case "Masculino":
                        comboGenero.setSelectedIndex(1);
                        break;
                    case "Femenino":
                        comboGenero.setSelectedIndex(2);
                        break;
                    default:
                        throw new AssertionError();
                }

                //Set fecha
                //Obtencion de la fecha de vencimiento
                String fech_actual = DatosCli[cont_flec][6];
                SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    Date fechaVenciS = S.parse(fech_actual);
                    dateChooser.setDate(fechaVenciS);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //aqui va el combo estado

                //Poner la imagen en el label
                ImageIcon proFotorec = new ImageIcon(DatosCli[cont_flec][9]);
                ImageIcon icono_prorec = new ImageIcon(proFotorec.getImage().getScaledInstance(jLImg.getWidth(), jLImg.getHeight(), Image.SCALE_DEFAULT));
                jLImg.setIcon(icono_prorec);
            }
        }

        jLInfo.setText("Tabla: Producto registro " + cont_label + " al " + (nFils));
        if (cont_label == nFils) {
            jLInfo.setText("Tabla: Producto registro  " + nFils + "  al " + (nFils));
        } else {
            cont_label++;
            jLInfo.setText("Tabla: Producto registro " + cont_label + " al " + (nFils));
        }
        bloc_sigui();

    }//GEN-LAST:event_jBSiguiActionPerformed

    private void jBUltimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBUltimActionPerformed

//        jBPrimer1.setEnabled(true);
//        jBAnterior.setEnabled(true);
//        jBUltim.setEnabled(false);
//        jBSigui.setEnabled(false);
        for (int j = 0; j < DatosCli[cont_fil_nav].length; j++) {

            txtIdCli.setText(DatosCli[cont_fil_nav][0]);
            txtNomCli.setText(DatosCli[cont_fil_nav][1]);
            txtApeCli.setText(DatosCli[cont_fil_nav][2]);
            txtDireCli.setText(DatosCli[cont_fil_nav][3]);
            txtTelCli.setText(DatosCli[cont_fil_nav][4]);

            //set Medida
            String selec_act = DatosCli[cont_fil_nav][5];
            System.out.println(selec_act);
            String selec_act2 = selec_act.substring(2);
            System.out.println(selec_act2);
            System.out.println(selec_act2.trim());

            switch (selec_act2.trim()) {
                case "Masculino":
                    comboGenero.setSelectedIndex(1);
                    break;
                case "Femenino":
                    comboGenero.setSelectedIndex(2);
                    break;
                default:
                    throw new AssertionError();
            }

            //Set fecha
            //Obtencion de la fecha de vencimiento
            String fech_actual = DatosCli[cont_fil_nav][6];
            SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");

            try {
                Date fechaVenciS = S.parse(fech_actual);
                dateChooser.setDate(fechaVenciS);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //aqui va el combo estado

            //Poner la imagen en el label
            ImageIcon proFotorec = new ImageIcon(DatosCli[cont_fil_nav][9]);
            ImageIcon icono_prorec = new ImageIcon(proFotorec.getImage().getScaledInstance(jLImg.getWidth(), jLImg.getHeight(), Image.SCALE_DEFAULT));
            jLImg.setIcon(icono_prorec);

            //Para que el contador sepa la posicion
            cont_flec = cont_fil_nav;
            if (cont_label == nFils) {
                jLInfo.setText("Tabla: Producto registro " + nFils + " al " + (nFils));
            } else {
                cont_label = (cont_label + nFils) - 1;
                jLInfo.setText("Tabla: Producto registro " + cont_label + " al " + (nFils));
            }
            bloc_sigui();
        }


    }//GEN-LAST:event_jBUltimActionPerformed

    private void txtIdCliFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdCliFocusGained
        if (txtIdCli.getText().equals("Id")) {
            txtIdCli.setForeground(Color.BLACK);
            txtIdCli.setText("");
            rec_dat();
        }

    }//GEN-LAST:event_txtIdCliFocusGained

    private void txtIdCliFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdCliFocusLost
        if (!txtIdCli.getText().isEmpty()) {

        } else {
            txtIdCli.setForeground(Color.GRAY);
            txtIdCli.setText("Id");
            rec_dat();
        }
    }//GEN-LAST:event_txtIdCliFocusLost

    private void txtIdCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdCliActionPerformed

    private void txtNomCliFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomCliFocusGained
        if (txtNomCli.getText().equalsIgnoreCase("Nombre")) {
            txtNomCli.setForeground(Color.BLACK);
            txtNomCli.setText("");
            rec_dat();
        }
    }//GEN-LAST:event_txtNomCliFocusGained

    private void txtNomCliFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomCliFocusLost
        if (!txtNomCli.getText().isEmpty()) {

        } else {
            txtNomCli.setForeground(Color.GRAY);
            txtNomCli.setText("Nombre");
            rec_dat();
        }
    }//GEN-LAST:event_txtNomCliFocusLost

    private void txtApeCliFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApeCliFocusGained
        if (txtApeCli.getText().equals("Apellidos")) {
            txtApeCli.setForeground(Color.BLACK);
            txtApeCli.setText("");
            rec_dat();
        }
    }//GEN-LAST:event_txtApeCliFocusGained

    private void txtApeCliFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApeCliFocusLost
        if (!txtApeCli.getText().isEmpty()) {

        } else {
            txtApeCli.setForeground(Color.GRAY);
            txtApeCli.setText("Apellidos");
            rec_dat();
        }
    }//GEN-LAST:event_txtApeCliFocusLost

    private void txtDireCliFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDireCliFocusGained
        if (txtDireCli.getText().equals("Dirección")) {
            txtDireCli.setForeground(Color.BLACK);
            txtDireCli.setText("");
            rec_dat();
        }
    }//GEN-LAST:event_txtDireCliFocusGained

    private void txtDireCliFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDireCliFocusLost
        if (!txtDireCli.getText().isEmpty()) {

        } else {
            txtDireCli.setForeground(Color.GRAY);
            txtDireCli.setText("Dirección");
            rec_dat();
        }
    }//GEN-LAST:event_txtDireCliFocusLost

    private void jbImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbImgActionPerformed
        JFileChooser jf = new JFileChooser();
        jf.showOpenDialog(this);
        archivo = jf.getSelectedFile();
        if (archivo != null) {
            //Poner la imagen en el label
            ImageIcon proFoto = new ImageIcon(archivo.getAbsolutePath());
            ImageIcon icono_pro = new ImageIcon(proFoto.getImage().getScaledInstance(jLImg.getWidth(), jLImg.getHeight(), Image.SCALE_DEFAULT));
            jLImg.setIcon(icono_pro);
        }
    }//GEN-LAST:event_jbImgActionPerformed

    private void jCalFecNacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCalFecNacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCalFecNacActionPerformed

    private void comboGeneroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboGeneroFocusGained
        rec_dat();
    }//GEN-LAST:event_comboGeneroFocusGained

    private void comboGeneroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboGeneroFocusLost
        rec_dat();
    }//GEN-LAST:event_comboGeneroFocusLost

    private void comboGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboGeneroActionPerformed
        int indice = comboGenero.getSelectedIndex();
        switch (indice) {
            case 0:

                selec_med = null;

                break;
            case 1:

                selec_med = "Masculino";

                break;
            case 2:

                selec_med = "Femenino";

                break;
        }

    }//GEN-LAST:event_comboGeneroActionPerformed

    private void jBIngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBIngActionPerformed
        ingreso();

    }//GEN-LAST:event_jBIngActionPerformed

    private void jBEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditActionPerformed

    }//GEN-LAST:event_jBEditActionPerformed

    private void jBEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliActionPerformed

    }//GEN-LAST:event_jBEliActionPerformed

    private void txtTelCliFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelCliFocusGained
        if (txtTelCli.getText().equals("Telefono")) {
            txtTelCli.setForeground(Color.BLACK);
            txtTelCli.setText("");
            rec_dat();
        }
    }//GEN-LAST:event_txtTelCliFocusGained

    private void txtTelCliFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelCliFocusLost
        if (!txtTelCli.getText().isEmpty()) {

        } else {
            txtTelCli.setForeground(Color.GRAY);
            txtTelCli.setText("Telefono");
            rec_dat();
        }
    }//GEN-LAST:event_txtTelCliFocusLost

    private void txtNomCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomCliActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBImpr;
    private javax.swing.JComboBox<String> comboGenero;
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
    private javax.swing.JComboBox<String> jCBestado;
    private javax.swing.JButton jCalFecNac;
    private javax.swing.JLabel jLCrud;
    private javax.swing.JLabel jLFn;
    private javax.swing.JLabel jLImg;
    private javax.swing.JLabel jLInfo;
    private javax.swing.JLabel jLfech;
    private javax.swing.JPanel jPCent;
    private javax.swing.JPanel jPFn;
    private javax.swing.JPanel jPNav;
    private javax.swing.JPanel jPPie;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPfoto;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbImg;
    private javax.swing.JLabel jlFoto;
    private javax.swing.JLabel jlNav;
    private javax.swing.JLabel lblApeCli;
    private javax.swing.JLabel lblDireCli;
    private javax.swing.JLabel lblEstCiv;
    private javax.swing.JLabel lblFecNac;
    private javax.swing.JLabel lblGenCli;
    private javax.swing.JLabel lblIdCli;
    private javax.swing.JLabel lblNomCli;
    private javax.swing.JLabel lblTelCli;
    private javax.swing.JTextField txtApeCli;
    private javax.swing.JTextField txtDireCli;
    private javax.swing.JTextField txtIdCli;
    private javax.swing.JTextField txtNomCli;
    private javax.swing.JTextField txtTelCli;
    // End of variables declaration//GEN-END:variables
}
