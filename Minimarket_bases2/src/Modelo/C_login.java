/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import static Modelo.MySQL.Conexion;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author santi
 */
public class C_login {

    public static boolean val_nom = false, val_cont=false;
    private static String db_name="minimarket";

    //archivo de conexiones de bases
    private static MySQL db = new MySQL();
    
    public static void rec_nom(String user) {
        db.MySQLConnection("root", "", db_name);
        try {
            
            System.out.println(user);
            
            //Codigo para mandar ordenes a la base de datos
            PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("SELECT nombre FROM empleados where nombre = ?");
            stp.setString(1, user);

            
            ResultSet res = (ResultSet) stp.executeQuery();
            
            if (res.next()) {
                String user1=res.getString("nombre");
                if(user1.equalsIgnoreCase(user)){
                    JOptionPane.showMessageDialog(null, "Bienvenido usuario "+user1);
                    val_nom=true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error usuario no encotrado");
        }
    }
    
    public static void rec_clave(String contra) {
        db.MySQLConnection("root", "", db_name);
        try {
            
            System.out.println(contra);
            
            //Codigo para mandar ordenes a la base de datos
            PreparedStatement stp = (PreparedStatement) Conexion.prepareStatement("SELECT clave FROM empleados where clave = ?");
            stp.setString(1, contra);

            
            ResultSet res2 = (ResultSet) stp.executeQuery();
            
            if (res2.next()) {
                String clave1=res2.getString("clave");
                if(clave1.equalsIgnoreCase(contra)){
                    JOptionPane.showMessageDialog(null, "Acceso conseguido");
                    val_cont=true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error clave no encotrada");
        }
    }
    
}
