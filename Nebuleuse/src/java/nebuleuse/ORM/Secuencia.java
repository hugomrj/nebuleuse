/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nebuleuse.ORM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hugo
 */


public class Secuencia<T> {
    
    public Integer lineas = 12;
    public Integer subLineas = 6;
    
    public  List<T> buscarTodos(Object objeto, String strBuscar) throws Exception {
  
        List<T>  listaDeObjetos=new ArrayList<T>(); 
                
        Conexion conexion = new Conexion();
        conexion.conectar();            
        Statement  statement ;
        ResultSet resultset;        
        Persistencia persistencia = new Persistencia();

        try
        {            
            statement = conexion.getConexion().createStatement();               
            resultset = statement.executeQuery( persistencia.selectSQL(objeto, strBuscar));     
            
            while(resultset.next()) 
            {
                Class claseObjeto = Class.forName(objeto.getClass().getName());             
                T  returnObjecto = (T) Class.forName(claseObjeto.getName()).newInstance();                  
                returnObjecto = (T) persistencia.extraerRegistro(resultset, objeto);
                listaDeObjetos.add(returnObjecto);
            }
            resultset.close();
                        
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getErrorCode());
            System.out.println(ex.getMessage());    
        }    
        conexion.desconectar();                            
        return listaDeObjetos;         
    }


    public  List<T> listaSimpleSQL(Object objeto, String strSQL) throws Exception {
  
        List<T>  listaDeObjetos=new ArrayList<T>(); 
                
        Conexion conexion = new Conexion();
        conexion.conectar();            
        Statement  statement ;
        ResultSet resultset;        
        Persistencia persistencia = new Persistencia();
          
        
        try
        {            
            statement = conexion.getConexion().createStatement();                       
            resultset = statement.executeQuery( strSQL );     
            
            while(resultset.next()) 
            {
                Class claseObjeto = Class.forName(objeto.getClass().getName());             
                T  returnObjecto = (T) Class.forName(claseObjeto.getName()).newInstance();                  
                returnObjecto = (T) persistencia.extraerRegistro(resultset, objeto);
                listaDeObjetos.add(returnObjecto);
            }
            resultset.close();
                        
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getErrorCode());
            System.out.println(ex.getMessage());    
        }    
        conexion.desconectar();                            
        return listaDeObjetos;         
    }
    
    
    
    public  List<T> listaPaginacion (Object objeto, String strBuscar, Integer pagina)
            throws Exception {
  
        List<T>  listaDeObjetos = new ArrayList<T>(); 
                
        Conexion conexion = new Conexion();
        conexion.conectar();            
        Statement  statement ;
        ResultSet resultset;        
        Persistencia persistencia = new Persistencia();
        
        if (strBuscar == null) {
            strBuscar = "";        
        }
        
        
        try
        {            
            statement = conexion.getConexion().createStatement();               
            resultset = statement.executeQuery( persistencia.selectSQL(objeto, strBuscar));     
            
            Integer totalRegistros = TotalRegistros( objeto, strBuscar ) ;
            Integer rangoFinal = pagina * this.lineas;
            Integer rangoInicio  = rangoFinal - this.lineas;
            
            if (totalRegistros < rangoFinal)
            {
                rangoFinal = totalRegistros;
            }          
            Integer contador = 0;
            
            while(resultset.next()) 
            {                
                if ((rangoInicio <= contador) & (rangoFinal >= contador)) 
                {                    
                    Class claseObjeto = Class.forName(objeto.getClass().getName());             
                    T  returnObjecto = (T) Class.forName(claseObjeto.getName()).newInstance();                  
                    returnObjecto = (T) persistencia.extraerRegistro(resultset, objeto);
                    listaDeObjetos.add(returnObjecto);                       
                }
                contador ++;
            }
            resultset.close();                        
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getErrorCode());
            System.out.println(ex.getMessage());    
        }    
        conexion.desconectar();                            
        return listaDeObjetos;         
    }    

    
    public  Integer TotalRegistros ( ResultSet resultset )
            throws Exception {
            
            Integer intRetornar = 0;      
        
            while(resultset.next()) 
            {    
                intRetornar++;
            }     
        
        return intRetornar;
    }    
    
    

    public  Integer TotalRegistros ( Object objeto, Object objRelacionado, String strBuscar )
            throws Exception {
            
        Integer intRetornar = 0;      
        
        Conexion conexion = new Conexion();
        conexion.conectar();            
        Statement  statement ;
        ResultSet resultset;        
        Persistencia persistencia = new Persistencia();
        
        try
        {            
            statement = conexion.getConexion().createStatement();               
            resultset = statement.executeQuery( persistencia.countSQL(objeto, objRelacionado,  strBuscar));     
            
            if(resultset.next()) 
            {    
                intRetornar = Integer.parseInt(resultset.getString("total"));
                resultset.close();
            }     
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getErrorCode());
            System.out.println(ex.getMessage());    
        }    
        
        conexion.desconectar();                            
        return intRetornar;
    }    


    
    public  Integer TotalRegistros ( Object objeto, String strBuscar )
            throws Exception {
    
        return this.TotalRegistros(objeto, null, strBuscar);
    }

    

    
    public  Integer TotalRegistros ( Object objeto, Object objRelacionado )
            throws Exception {
    
        return this.TotalRegistros(objeto, objRelacionado, null);
    }

        
       
    public  List<T> listaColeccion (Object objeto, Object objRelacionado, String strBuscar, Integer pagina)
            throws Exception {
  
        List<T>  listaDeObjetos=new ArrayList<T>(); 
                
        Conexion conexion = new Conexion();
        conexion.conectar();            
        Statement  statement;
        ResultSet resultset = null;        
        Persistencia persistencia = new Persistencia();
        Integer totalRegistros = 0 ;
        
        try
        {            
            statement = conexion.getConexion().createStatement();       
                        
            if (objRelacionado == null)
            {
                
                if ( strBuscar == null)
                {
                    resultset = statement.executeQuery( persistencia.selectSQL(objeto, null, null));     
                    totalRegistros = this.TotalRegistros(objeto, null, null);
                }
                else
                {
                    resultset = statement.executeQuery( persistencia.selectSQL(objeto, null, strBuscar));     
                    totalRegistros = this.TotalRegistros(objeto, null, strBuscar) ;                
                }
                
            }
            else 
            {
                
                if ( strBuscar == null)
                {
                    resultset = statement.executeQuery( persistencia.selectSQL(objeto, objRelacionado, null));     
                    totalRegistros = this.TotalRegistros(objeto, objRelacionado, null);
                }
                else
                {    
                    resultset = statement.executeQuery( persistencia.selectSQL(objeto, objRelacionado, strBuscar));     
                    totalRegistros = this.TotalRegistros(objeto, objRelacionado, strBuscar) ;                
                }
            
            }

            
            Integer rangoFinal = pagina * ( this.subLineas );
           
            Integer rangoInicio  = rangoFinal - ( this.subLineas );
            if (rangoInicio == 0) { rangoInicio =1;  }

    
            if (totalRegistros < rangoFinal)
            {
                rangoFinal = totalRegistros;
            }          
            
            Integer contador = 0;
            
            while(resultset.next()) 
            {
                contador ++;                
                
                if ((rangoInicio <= contador) & (rangoFinal >= contador)) 
                {                    
                    Class claseObjeto = Class.forName(objeto.getClass().getName());             
                    T  returnObjecto = (T) Class.forName(claseObjeto.getName()).newInstance();                  
                    returnObjecto = (T) persistencia.extraerRegistro(resultset, objeto);
                    listaDeObjetos.add(returnObjecto);                       
                }
                
            }
            resultset.close();
            
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getErrorCode());
            System.out.println(ex.getMessage());    
        }    
        conexion.desconectar();                            
        return listaDeObjetos;         
    }     
    
    
    
    
    public  List<T> listaPaginacion (Object objeto, ResultSet resultset, String strBuscar, Integer pagina)
            throws Exception {
  
        List<T>  listaDeObjetos = new ArrayList<T>(); 
        Persistencia persistencia = new Persistencia();
             
        
        if (strBuscar == null)
        {
            strBuscar = "";        
        }
        
        try
        {            
            
            Integer totalRegistros = TotalRegistros(resultset);
            
            Integer rangoFinal = pagina * this.lineas;
            Integer rangoInicio  = rangoFinal - this.lineas;
            
            if (totalRegistros < rangoFinal)
            {
                rangoFinal = totalRegistros;
            }          
            Integer contador = 0;
            
            
            resultset.beforeFirst();            
            while(resultset.next()) 
            {                
                if ((rangoInicio <= contador) & (rangoFinal >= contador)) 
                {                    
                    
                    Class claseObjeto = Class.forName(objeto.getClass().getName());             
                    T  returnObjecto = (T) Class.forName(claseObjeto.getName()).newInstance();                  
                    returnObjecto = (T) persistencia.extraerRegistro(resultset, objeto);
                    
                    listaDeObjetos.add(returnObjecto);                       
                }
                contador ++;
                
            }
            resultset.close();                        
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getErrorCode());
            System.out.println(ex.getMessage());    
        }    

        return listaDeObjetos;         
    }    
        
    
}









