/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import gramatica.Gramatica;
/**
 *
 * @author Anfu
 */
public class Consult {
       Conexion con = new Conexion();
         
    public void pronomBD(Gramatica g){
        
        Connection cn;       
        PreparedStatement ps;        
        ResultSet rs;
        try{               
             
                con.Conexion();
                cn = con.getConectar();
        ps = cn.prepareStatement("SELECT * FROM pronombres");
        rs = ps.executeQuery();
        
        while(rs.next()){           
            g.agregarPronom(rs.getString("español"), rs.getString("TradIndependiente"), rs.getString("TradDependiente"), rs.getString("tipo"), rs.getString("Cantidad"), rs.getString("extensionplural"));
            }
        g.getListPron();
            cn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }                                                                                                                                 
    }
    public void conjuBD(Gramatica g){
        Connection cn;       
        PreparedStatement ps;        
        ResultSet rs;
        try{                       
                con.Conexion();
                cn = con.getConectar();
        ps = cn.prepareStatement("SELECT * FROM conjunciones");
        rs = ps.executeQuery();
        
        while(rs.next()){
            g.agregarConj(rs.getString("conjuncion"), rs.getString("conjuncionmaya"), rs.getString("tipo"));
            }
        g.getListCon();
            cn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        } 
    }
    public void sustBD(Gramatica g){
        Connection cn;       
        PreparedStatement ps;        
        ResultSet rs;
        try{                       
                con.Conexion();
                cn = con.getConectar();
        ps = cn.prepareStatement("SELECT * FROM sustantivos");
        rs = ps.executeQuery();
        
        while(rs.next()){
            g.agregarSustan(rs.getString("español"), rs.getString("maya"), rs.getString("tipo"), rs.getString("cantidad"));
            }
        g.getListSust();
            cn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        } 
    }
    
    public void artBD(Gramatica g){
        Connection cn;       
        PreparedStatement ps;        
        ResultSet rs;
        try{                       
                con.Conexion();
                cn = con.getConectar();
        ps = cn.prepareStatement("SELECT * FROM sustantivos");
        rs = ps.executeQuery();
        
        while(rs.next()){
            g.agregarSustan(rs.getString("español"), rs.getString("tradmaya"), rs.getString("cantidad"), rs.getString("tipo"));
            }
        g.getListSust();
            cn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        } 
    }
}
