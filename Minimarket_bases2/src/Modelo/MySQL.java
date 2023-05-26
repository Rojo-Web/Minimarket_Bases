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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private static int id_em;

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

    public static void deleteRecord(String table_name, String ID) {
        try {
            String Query = "DELETE FROM " + table_name + " WHERE ID= \"" + ID + "\"";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado ");
        }
    }



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
        
        //Validar cuantos registros
        try {
            
            //System.out.println(user);
            
            //Codigo para mandar ordenes a la base de datos
            PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("select count(*) as id_empl from empleados;");
            
            ResultSet res_cont = (ResultSet) stp.executeQuery();
            
            if (res_cont.next()) {
                int total_users = res_cont.getInt("id_empl");
                System.out.println(total_users);
                id_em=total_users+1;
                System.out.println(id_em);
            }
        } catch (SQLException ex) {
            System.out.println("Error cant no encotrado");
        }


        try {
            System.out.println("Entre");
            String Query = "INSERT INTO " + table_name + "(id_empl, nombre, apellido, turno, fecha_nacimi, edad, nivel, clave) VALUES("
                    +id_em+","
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

}
