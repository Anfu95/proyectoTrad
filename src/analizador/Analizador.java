/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

import java.util.LinkedList;
import javax.swing.JOptionPane;
import java.sql.*;
import baseDeDatos.*;
import java.awt.List;
import java.util.ArrayList;
import javax.swing.*;
import gramatica.Gramatica;
import baseDeDatos.*;
import java.util.HashMap;
import java.util.Iterator;
/**
 *
 * @author Anfu
 */
public class Analizador {
    
    String[] textoInicial;
    //Frase final
    String textoFinal;
    //Frase en traduccion
    LinkedList<String> fraseTrad = new LinkedList<>();
    //Frase en lista
    LinkedList<String> fraseList = new LinkedList<>();
    //Lista gramaticales
    LinkedList<String[]> lP = new LinkedList<>();
    LinkedList<String[]> lA = new LinkedList<>();
    LinkedList<String[]> lS = new LinkedList<>();
    LinkedList<String[]> lC = new LinkedList<>();
    //Tablas Gramaticales
    LinkedList<HashMap> tabP= new LinkedList<>();
    LinkedList<HashMap> tabC= new LinkedList<>();
    LinkedList<HashMap> tabS= new LinkedList<>();
    
    //    
    HashMap dicGram = new HashMap();
    Gramatica g = new Gramatica();
    
    public void limpiar(){
        textoInicial = null;
        textoFinal = null;
        fraseList.clear();
        fraseTrad.clear();
        lP.clear();
        lA.clear();
        lC.clear();
        lS.clear();
        tabC.clear();
        tabP.clear();
        tabS.clear();       
    }
    
    
    
    public void analizarTexto(JTextField txtF){
        
        textoInicial = txtF.getText().split(" ");
        comprobText(textoInicial);
    }
    public void solicitarBd_G(){
        //se crea las columnas y tablas donde guerdaran la informacion de la base de datos 
        Consult c = new Consult();
        g.creacionDeColumnTablas();
        c.pronomBD(g);
        c.conjuBD(g);
        c.sustBD(g);
        g.getListPron();
    }
    public void comprobText(String[] t){
        //se pasan las tablas a nuevas listas para poder manejarlas
        lP = g.getListPron();
        lS = g.getListSust();
        lC = g.getListCon();
        
        //Creacion de tablas(hashmap) con las palabras que se identifican con la base de datos y la frase que introducce el usuario
        //Creando una tabla segun su tipo, solo con las palabras de la frase del usuario.
        //luego se introducce estás tablas en una lista para poder manejarlas todas
        for (int i = 0; i < t.length; i++) {
            //Crear tabla Pronombre
            for (int j = 0; j < lP.size(); j++) {
                if(t[i].equals(lP.get(j)[0])){
                    for (int k = 0; k < 6; k++) {                        
                        dicGram.put(lP.get(0)[k],lP.get(j)[k]);
                    }
                    tabP.add((HashMap)dicGram.clone());
                    dicGram.clear();
                }
            }
            //Crear tabla Sustantivo
            for (int j = 0; j < lS.size(); j++) {
                if(t[i].equals(lS.get(j)[0])){
                    for (int k = 0; k < 4; k++) {
                        dicGram.put(lS.get(0)[k],lS.get(j)[k]);
                    }
                    tabS.add((HashMap)dicGram.clone());
                    dicGram.clear();
                }
            }
            //Crear tabla Conjucion
            for (int j = 0; j < lC.size(); j++) {
                if(t[i].equals(lC.get(j)[0])){
                    for (int k = 0; k < 3; k++) {
                        dicGram.put(lC.get(0)[k],lC.get(j)[k]);
                    }
                    tabC.add((HashMap)dicGram.clone());
                    dicGram.clear();
                }
            }
            
        }
        fL(t);
        gramaticaPronombre(fraseList);
        gramaticaConjuciones(fraseList);
        //traduccion
        traduccion(t);
        //Pruebas
        getDicGram();
        getTabP();
        getTabC();
        getTabS();
    }
    
    //En está funcion se compara la frase con las lista<tabla>(hashmap) y se localiza por medio de una clave para obtener la parte del español y compararla
    //con la posicion actual de la frase, una vez localizandola se crea una nueva lista segun el "tipo" de las palabras de la fraseInicial, poniendose 
    //adecuadamente el tipo segun su posicion.
    public void fL(String[] f){
        for (int i = 0; i < f.length; i++) { //frase
            for (int j = 0; j < tabP.size(); j++) { //tamaño de la lista                
                    if(f[i].equals(tabP.get(j).get("español").toString())){
                        fraseList.add(tabP.get(j).get("tipo").toString());
                    }               
            }
            for (int j = 0; j < tabC.size(); j++) { //tamaño de la lista                
                    if(f[i].equals(tabC.get(j).get("conjuncion").toString())){
                        fraseList.add(tabC.get(j).get("tipo").toString());
                    }               
            }
        }        
    }
    
    //En está función se hace la gramatica de pronombres para poder saber que traducción se hará al maya.
    public void gramaticaPronombre(LinkedList<String> ls){
        //se le pasa una lista con el tamaño de la frase inicial para poder modificarla segun su traducción.
        fraseTrad = ls;
        for (int i = 0; i < ls.size(); i++) {
         if(ls.size()>1){
            if(i<ls.size()-1){
                if(ls.get(i).equals("pronombre")&&ls.get(i+1).equals("verbo")){
                    fraseTrad.set(i, "tradDep");
                }else{
                    if(ls.get(i).equals("pronombre")){
                fraseTrad.set(i, "tradInde");
             }
                } 
            }else{
                if(ls.get(i).equals("pronombre")){
                fraseTrad.set(i, "tradInde");
             }
            }
          }else{
             if(ls.get(i).equals("pronombre")){
                fraseTrad.set(i, "tradInde");
             }
         }
       }
    }
     //En está función se hace la gramatica de conjunciones para poder saber que traducción se hará al maya.
    public void gramaticaConjuciones(LinkedList<String> ls){
        for (int i = 0; i < ls.size(); i++) {
            if(ls.get(i).equals("copulativa")){
                fraseTrad.set(i, "conjuncionmaya");
            }
        }
    }
    public void traduccion(String[] f){
        for (int i = 0; i < tabP.size(); i++) {
            for (int j = 0; j < f.length; j++) {
                if(tabP.get(i).get("español").toString().equals(f[j])){
                    f[j]=tabP.get(i).get(fraseTrad.get(j)).toString();
                }
            }  
        }
        for (int i = 0; i < tabC.size(); i++) {
            for (int j = 0; j < f.length; j++) {
                if(tabC.get(i).get("conjuncion").toString().equals(f[j])){
                    f[j]=tabC.get(i).get(fraseTrad.get(j)).toString();
                }
            }  
        }
        setTextoFinal(f);
    }

    public String getTextoFinal() {
        return this.textoFinal;
    }

    public void setTextoFinal(String[] textoFinal) {
        for (int i = 0; i < textoFinal.length; i++) {
            if(this.textoFinal==null){
                this.textoFinal =textoFinal[i]+" ";
            }else{
                this.textoFinal +=textoFinal[i]+" ";
            }            
        }
    }

    public LinkedList<String> getFraseList() {
        return fraseList;
    }

    public LinkedList<HashMap> getTabP() {
        return tabP;
    }

    public LinkedList<HashMap> getTabC() {
        return tabC;
    }

    public void setTabC(LinkedList<HashMap> tabC) {
        this.tabC = tabC;
    }

    public LinkedList<HashMap> getTabS() {
        return tabS;
    }

    public void setTabS(LinkedList<HashMap> tabS) {
        this.tabS = tabS;
    }

    public HashMap getDicGram() {
        return dicGram;
    }
    
    
    
    //Final
}
