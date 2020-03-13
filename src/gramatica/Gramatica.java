/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gramatica;

import java.util.Hashtable;
import java.util.LinkedList;

/**
 *
 * @author Anfu
 */
public class Gramatica {   
   
   String[] conjuncion = new String[3];
   String[] pronombre = new String[6];//pronombre={0,1,2}
   String[] sustantivo =  new String [4];
   String[] articulo =  new String [4];
   //String[] Gram = {"pronombre","sustantivo","conjuncion"}; 
   String[] columnPron = {"español", "tradInde","tradDep","tipo","cantidad","extensionPlural"};
   String[] columnConju = {"conjuncion", "conjuncionmaya", "tipo"};
   String[] columnSust = {"español", "maya","tipo","cantidad"};
   String[] columnArt = {"español", "tradmaya","cantidad","tipo"};
   
    static LinkedList<String[]> listPron = new LinkedList<>();
    static LinkedList<String[]> listArt = new LinkedList<>();
    static LinkedList<String[]> listSust = new LinkedList<>();
    static LinkedList<String[]> listCon = new LinkedList<>();
    
        
    public Gramatica(){
        
    }
    public void creacionDeColumnTablas(){
       listPron.add(0, columnPron);
       listCon.add(0, columnConju);
       listSust.add(0, columnSust);
       
   }
    public void agregarConj(String v1, String v2, String v3){
        conjuncion[0]=v1.trim();
        conjuncion[1]=v2.trim();
        conjuncion[2]=v3.trim();
        listCon.add(conjuncion);
    }
    
    public void agregarSustan(String v1, String v2, String v3, String v4){
        sustantivo[0]=v1.trim();
        sustantivo[1]=v2.trim();
        sustantivo[2]=v3.trim();
        sustantivo[3]=v4.trim();
        listSust.add(sustantivo);
    }
    public void agregarArticulo(String v1, String v2, String v3, String v4){
        articulo[0]=v1.trim();
        articulo[1]=v2.trim();
        articulo[2]=v3.trim();
        articulo[3]=v4.trim();
        listSust.add(articulo);
    }
    
    public void agregarPronom(String v1, String v2, String v3, String v4, String v5, String v6){
        
        pronombre[0]=v1.trim();
        pronombre[1]=v2.trim();
        pronombre[2]=v3.trim();
        pronombre[3]=v4.trim();
        pronombre[4]=v5.trim();
        if(v6!=null){
        pronombre[5]=v6.trim();
        }else{
            pronombre[5]=v6;
        }
        listPron.add(pronombre.clone());      
    }

    /*public String[] getGram() {
        return Gram;
    }*/
     

    public static LinkedList<String[]> getListPron() {      
        return listPron;
    }

    public static LinkedList<String[]> getListSust() {
        return listSust;
    }

    public static LinkedList<String[]> getListCon() {
        return listCon;
    }

    public static LinkedList<String[]> getListArt() {
        return listArt;
    }
}
