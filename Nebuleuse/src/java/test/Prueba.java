/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import nebuleuse.ORM.Conexion;
import nebuleuse.ORM.Persistencia;

/**
 *
 * @author hugo
 */
public class Prueba {
    
    public static  void main(String[] args) throws Exception   {

        
        Tabla01  instancia = new Tabla01();
        Persistencia persistencia = new Persistencia();

        // insert
        instancia.setNumerico(123);        
        instancia.setCaracter("uno dos tres");        
        instancia = (Tabla01) persistencia.insert(instancia);
        
        Integer idDeIntancia = persistencia.getValorClavePrimaria(instancia);
        
        Tabla01  instancia2 = new Tabla01();
        instancia2 = (Tabla01) persistencia.filtrarId(instancia, idDeIntancia);
        
        System.out.println(instancia2.getId());
        System.out.println(instancia2.getNumerico());
        System.out.println(instancia2.getCaracter());
        
        // update 
        instancia.setNumerico(456);        
        instancia.setCaracter("cuatro cinco seis");        
        instancia = (Tabla01) persistencia.update(instancia);
        

        instancia2 = (Tabla01) persistencia.filtrarId(instancia, idDeIntancia);        
        System.out.println(instancia2.getId());
        System.out.println(instancia2.getNumerico());
        System.out.println(instancia2.getCaracter());        
        
        // delete 
        instancia = (Tabla01) persistencia.delete(instancia);
        
        
    }
}
