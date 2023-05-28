/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author santi
 */
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MySQL {

    public static Connection Conexion;
    private static byte[] imageBytes;
    public static String imageObt;
    private static int cant_fin;
    private static int id_verifi, tabla_exist;

    public static void MySQLConnection(String user, String pass, String db_name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_name, user, pass);
            System.out.println("Se ha iniciado la conexion con el servidor de forma exitosa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public static void closeConnection() {
        try {
            Conexion.close();
            System.out.println("Se a finalizado la conexion con el servidor");
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void createDB(String name) {
        try {
            String Query = "CREATE DATABASE " + name;
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            MySQLConnection("root", "", name);
            JOptionPane.showMessageDialog(null, "Se ha creado la base de datos " + name + " de forma exitosa");
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insertData(String table_name, String ID, String name, String lastname, String age, String genero) {
        try {
            String Query = "INSERT INTO " + table_name + " VALUES("
                    + "\"" + ID + "\","
                    + "\"" + name + "\","
                    + "\"" + lastname + "\","
                    + "\"" + age + "\","
                    + "\"" + genero + "\")";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }
    }

    //Consulta
    public static void getValues(String table_name) {
        try {
            String Query = "SELECT * FROM " + table_name;
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getString("ID") + " " + "Nombre: " + resultSet.getString("Nombre") + " " + resultSet.getString("Apellido") + "" + "Edad: " + resultSet.getString("Edad") + "" + "Sexo:" + resultSet.getString("Sexo"));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la adquisicion de datos");
        }
    }

    

    public static void deleteRecord(String table_name,String camp_very, int ID) {
        try {
            String Query = "DELETE FROM " + table_name + " WHERE "+camp_very+" = \"" + ID + "\"";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado ");
        }
        
        
        //ESTO ES PARA QUE APENAS BORRE EL DATO EL ID QUE ESTA AUTO INCREMENTANDO SE REINICIE 
        try {
            PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("SET @num := 0;");
            stp.executeUpdate();
            PreparedStatement stp2 = (PreparedStatement) Conexion.prepareStatement("UPDATE "+table_name+" SET "+camp_very+" = @num := (@num+1);");
            stp2.executeUpdate();
            PreparedStatement stp3 = (PreparedStatement) Conexion.prepareStatement("ALTER TABLE "+table_name+" AUTO_INCREMENT = 1;");
            stp3.executeUpdate();

        } catch (SQLException ex) {
        }
    }

    //**************************Metodos fotos********************************************************************************
    public static String imgenEnviar(String enlace) {
//        Reemplazar "\\" por "/"
        String enlaceConvertido = enlace.replace("\\", "/");

        String cadenaOriginal = enlaceConvertido;
        String palabra = "/imagenes";

        // Obtener el índice de la palabra en la cadena
        int indice = cadenaOriginal.indexOf(palabra);

        if (indice != -1) {
            // Obtener la subcadena a partir del índice de la palabra
            String subcadena = cadenaOriginal.substring(indice);
            return subcadena;
        } else {
            // La palabra no se encontró en la cadena
            return "";
        }
    }

    //*****************************Metodos extras****************************************************************************
    //OBTENER CUANTOS REGISTROS HAY EN LA TABLA 
    public static int cantRegistros(String Tabla, String campVerficador) {

        MySQLConnection("root", "", "minimarket");

        //Validar cuantos registros
        try {
            System.out.println(Tabla + " " + campVerficador);
            //Codigo para mandar ordenes a la base de datos
            PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("select count(*) as " + campVerficador + " from " + Tabla + ";");

            ResultSet res_cont = (ResultSet) stp.executeQuery();

            if (res_cont.next()) {
                int total_users = res_cont.getInt(campVerficador);
                cant_fin = total_users;
                System.out.println(cant_fin);

            }
        } catch (SQLException ex) {
            System.out.println("Error cant no encotrado");
        }

        int can_total = cant_fin;
        return can_total;
    }

    //SABER SI UNA TABLA EXISTE O NO, PARA ASI CREALA O NO
    public static int verif_table(String table_name) {
        MySQLConnection("root", "", "minimarket");
        try {
            PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("SELECT EXISTS ( SELECT 1 FROM information_schema.tables WHERE table_schema = 'minimarket' AND table_name = '" + table_name + "' );");

            ResultSet res_ver = (ResultSet) stp.executeQuery();

            if (res_ver.next()) {
                tabla_exist = res_ver.getInt(1);

            }
        } catch (SQLException ex) {
            System.out.println("Error usuario no encotrado");
        }

        return tabla_exist;
    }

    //*********************************************Todo para empleados******************************************************* 
    public static void Ctabla_empl() {

        try {

            String Query = "CREATE TABLE " + "empleados (id_empl int(11) auto_increment, nombre varchar(30), apellido varchar(30), turno varchar(2), fecha_nacimi varchar(50), edad varchar(30), nivel varchar(50), clave varchar(50), primary key(id_empl))";
            JOptionPane.showMessageDialog(null, "Se ha creado la tabla empleados de forma exitosa");
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void insert_empl(String table_name, String name, String lastname, String clave, String turno, String nivel, String fech_naci, String edad) {
        MySQLConnection("root", "", "minimarket");

        int id_em = cantRegistros("empleados", "id_empl");
        id_em++;

        System.out.println(id_em);

        try {
            System.out.println("Entre");
            String Query = "INSERT INTO " + table_name + "(id_empl, nombre, apellido, turno, fecha_nacimi, edad, nivel, clave) VALUES("
                    + id_em + ","
                    + "'" + name + "' ,"
                    + "'" + lastname + "' ,"
                    + "'" + turno + "' ,"
                    + "'" + fech_naci + "' ,"
                    + "'" + edad + "' ,"
                    + "'" + nivel + "' ,"
                    + "'" + clave + "')";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);

            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }
    }

    //**********************************************TODO PARA PRODUCTOS*********************************************************************************************
    public static void Ctabla_prod() {
        int exist = verif_table("productos");
        System.out.println(exist);

        if (exist == 1) {

        } else {
            try {

                String Query = "CREATE TABLE " + "productos (id_prod int(100) auto_increment, nombre_prod varchar(30) NOT NULL, marca varchar(50) NOT NULL, precio INTEGER(255) NOT NULL, fecha_venci varchar(70) NOT NULL, cantidad INTEGER(255) NOT NULL, medida varchar(100) NOT NULL, img_prod varchar(1000), primary key(id_prod))";
                JOptionPane.showMessageDialog(null, "Se ha creado la tabla productos de forma exitosa");
                Statement st = Conexion.createStatement();
                st.executeUpdate(Query);
            } catch (SQLException ex) {
                Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void insert_prod(String table_name, String name_prod, String marca, int precio, String vencimiento, int cantidad, String medida, String img_prod) {
        MySQLConnection("root", "", "minimarket");

        int id_prod = cantRegistros("productos", "id_prod");
        id_prod++;
        System.out.println(id_prod);

        try {
            System.out.println("Entre");
            String Query = "INSERT INTO " + table_name + "(id_prod, nombre_prod, marca, precio, fecha_venci, cantidad, medida, img_prod) VALUES("
                    + id_prod + ","
                    + "'" + name_prod + "' ,"
                    + "'" + marca + "' ,"
                    + "'" + precio + "' ,"
                    + "'" + vencimiento + "' ,"
                    + "'" + cantidad + "' ,"
                    + "'" + medida + "' ,"
                    + "'" + img_prod + "')";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);

            JOptionPane.showMessageDialog(null, "Nuevo producto almacenado de forma exitosa");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos producto");
        }
    }

    public static void edit_prod(int id, String name_prod, String marca, int precio, String vencimiento, int cantidad, String medida, String img_prod) {
        MySQLConnection("root", "", "minimarket");

        try {

            System.out.println(id);

            //Codigo para mandar ordenes a la base de datos
            PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("UPDATE productos SET nombre_prod = ?, marca = ?, precio = ?, fecha_venci = ?, cantidad = ?, medida = ?, img_prod = ? WHERE id_prod = ?");
            stp.setString(1, name_prod);
            stp.setString(2, marca);
            stp.setInt(3, precio);
            stp.setString(4, vencimiento);
            stp.setInt(5, cantidad);
            stp.setString(6, medida);
            stp.setString(7, img_prod);
            stp.setInt(8, id);

            int res = stp.executeUpdate();

            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro actualizado correctamente", "Dato actualizado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error en actualizacion", "Error en actualizacion", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println("Error en actualizacion");
        }

        
    }
    
    //**********************************************TODO PARA CLIENTES*********************************************************************************************
    public static void Ctabla_clie() {
        int exist = verif_table("clientes");
        System.out.println(exist);

        if (exist == 1) {

        } else {
            try {

                String Query = "CREATE TABLE " + "clientes (ID_C int(100) auto_increment, Id_Clie int(100) NOT NULL, nombre varchar(30) NOT NULL, apellidos varchar(50) NOT NULL, direccion varchar(100) NOT NULL, telefono varchar(70) NOT NULL, genero varchar(70) NOT NULL, F_nacimiento varchar(100) NOT NULL, Edad_C int(100) NOT NULL, estado_civil varchar(100) NOT NULL, img_clie varchar(1000), primary key(ID_C))";
                JOptionPane.showMessageDialog(null, "Se ha creado la tabla clientes de forma exitosa");
                Statement st = Conexion.createStatement();
                st.executeUpdate(Query);
            } catch (SQLException ex) {
                Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    public static void insert_clie(String table_name,int id_clie, String name_clie, String apellido, String direccion, String telefono, String genero, String F_nacimiento,int edad, String estado, String img_clie) {
        MySQLConnection("root", "", "minimarket");

        int ID_C = cantRegistros("clientes", "ID_C");
        ID_C++;
        System.out.println(ID_C);

        try {
            System.out.println("Entre");
            String Query = "INSERT INTO " + table_name + "(ID_C, Id_Clie, nombre, apellidos, direccion, telefono, genero, F_nacimiento, Edad_C, estado_civil, img_clie) VALUES("
                    + ID_C + ","
                    + "'" + id_clie + "' ,"
                    + "'" + name_clie + "' ,"
                    + "'" + apellido + "' ,"
                    + "'" + direccion + "' ,"
                    + "'" + telefono + "' ,"
                    + "'" + genero + "' ,"
                    + "'" + F_nacimiento + "' ,"
                    + "'" + edad + "' ,"
                    + "'" + estado + "' ,"
                    + "'" + img_clie + "')";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);

            JOptionPane.showMessageDialog(null, "Nuevo cliente almacenado de forma exitosa");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos cliente");
        }
    }
    
    public static void edit_clie(int id_flec, String name_clie, String apellido, String direccion, String telefono, String genero, String F_nacimiento, int edad, String estado, String img_clie) {
        MySQLConnection("root", "", "minimarket");

        try {

            System.out.println(id_flec);

            //Codigo para mandar ordenes a la base de datos
            PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("UPDATE clientes SET  nombre = ?, apellidos = ?, direccion = ?, telefono = ?, genero = ?, F_nacimiento = ?, Edad_C = ?, estado_civil = ?, img_clie = ? WHERE ID_C = ?");
            stp.setString(1, name_clie);
            stp.setString(2, apellido);
            stp.setString(3, direccion);
            stp.setString(4, telefono);
            stp.setString(5, genero);
            stp.setString(6, F_nacimiento);
            stp.setInt(7, edad);
            stp.setString(8, estado);
            stp.setString(9, img_clie);
            stp.setInt(10, id_flec);

            int res = stp.executeUpdate();

            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro actualizado correctamente", "Dato actualizado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error en actualizacion", "Error en actualizacion", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println("Error en actualizacion");
        }

        
    }
}
