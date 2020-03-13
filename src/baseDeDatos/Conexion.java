/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDeDatos;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Anfu
 */
public class Conexion {
     String BD = "jdbc:postgresql://localhost:5432/traduMaya";
     String usuario = "postgres";
     String contra = "1234";
     Connection conectar;
    
    public static void main(String[] args){
        
    }
    
    public void Conexion(){
        try{
            conectar = DriverManager.getConnection(BD,usuario,contra);                       
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Conexion erronea "+e);            
        }      
    }

    public Connection getConectar() {
        return conectar;
    }
    
}
