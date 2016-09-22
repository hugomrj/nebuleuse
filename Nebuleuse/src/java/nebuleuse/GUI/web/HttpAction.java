/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nebuleuse.GUI.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nebuleuse.ORM.Enlace;
import nebuleuse.ORM.xml.Serializacion;

/**
 * @author hugom_000
 */

public  class HttpAction {
      
    private Mensaje mensaje = new Mensaje();
    private String nombreMetodo = "";
    private Enlace elemento = new Enlace();
    
    
    public  void for_Insert (Object objeto, HttpServletRequest request, HttpServletResponse response) 
            throws Exception{
        
            if (objeto == null)
            {   
                // obtiene valor del request mensaje
                
                mensaje = (Mensaje) request.getSession().getAttribute("SessionMensajes");  
                              
                mensaje.setEtiqueta("insertNOT");
                mensaje.setTipo("error");                                
                mensaje.guardarInformacion();
                                                
                if (mensaje.getMensajeBase() != "")
                {                             
                    String mensajeBase = "";
                    mensajeBase = mensaje.getMensajeBase();                    
                    mensajeBase = mensajeBase.replace("java.sql.SQLException:", "");
                    mensajeBase = mensajeBase.replace("org.postgresql.util.PSQLException:", "");
                    
                    mensajeBase = mensajeBase.trim();                                                   
                    mensaje.setInformacion( mensajeBase + " " + mensaje.getInformacion()  );                                        
                }
                                
                request.getSession().setAttribute("SessionMensajes", mensaje);
                
                response.sendRedirect(request.getHeader("Referer"));
                
            }
            else
            {   
                
                Serializacion serializacion = new Serializacion(objeto);
                serializacion.getElementos() ;                        
                elemento = serializacion.getElementos().get(1);             

                nombreMetodo = "get" + elemento.getObjeto().toString().substring(0,1).toLowerCase().toUpperCase() 
                + elemento.getObjeto().toString().substring(1).toLowerCase() ;
                
                mensaje.setEtiqueta("insertOK");
                mensaje.setTipo("info");
            
                mensaje.guardarInformacion();
                request.getSession().setAttribute("SessionMensajes", mensaje);

                
                    if (request.getSession().getAttribute("vista") != null)                       
                    {
                        
                        Object objVista = new Object();
                        objVista = request.getSession().getAttribute("vista");
           
                        
                        if (objeto.getClass() != objVista.getClass())
                        {
                            serializacion = new Serializacion(objVista);
                            serializacion.getElementos() ;                        
                            elemento = serializacion.getElementos().get(1);             

                            nombreMetodo = "get" + elemento.getObjeto().toString().substring(0,1).toLowerCase().toUpperCase() 
                            + elemento.getObjeto().toString().substring(1).toLowerCase() ;

                            request.getSession().removeAttribute("vista");

                            response.sendRedirect("../"+objVista.getClass().getSimpleName()+"/Filtrar.do?id="
                                    +objVista.getClass().getMethod(nombreMetodo).invoke(objVista).toString()
                                    +"&pag=Registro.jspx") ;

                        }
                        else
                        {

                            response.sendRedirect("../"+objeto.getClass().getSimpleName()+"/Filtrar.do?id="
                                    +objeto.getClass().getMethod(nombreMetodo).invoke(objeto).toString()
                                    +"&pag=Registro.jspx") ;

                        }
                    }
                    else
                    {
                        
                        response.sendRedirect("../"+objeto.getClass().getSimpleName()+"/Filtrar.do?id="
                                +objeto.getClass().getMethod(nombreMetodo).invoke(objeto).toString()
                                +"&pag=Registro.jspx") ;                                                                 
                            
                    }
                
            }

    }
    
    
    public  void for_delete (Object objeto, Class clase,
            HttpServletRequest request, HttpServletResponse response) 
                    throws IOException, NoSuchMethodException, IllegalAccessException, 
                    IllegalArgumentException, InvocationTargetException {
        
                
            if (objeto != null)
            {


                mensaje = (Mensaje) request.getSession().getAttribute("SessionMensajes");
                
                mensaje.setEtiqueta("deleteNOT");
                mensaje.setTipo("error");                
                mensaje.guardarInformacion();

                
                if (mensaje.getMensajeBase() != "")
                {         

                    String mensajeBase = "";
                    mensajeBase = mensaje.getMensajeBase();                    
                    mensajeBase = mensajeBase.replace("java.sql.SQLException:", "");
                    mensajeBase = mensajeBase.replace("org.postgresql.util.PSQLException:", "");
                    
                    mensajeBase = mensajeBase.trim();                                                   
                    mensaje.setInformacion( mensajeBase + " " + mensaje.getInformacion()  );                                        
                }                
                
                request.getSession().setAttribute("SessionMensajes", mensaje);
                            
                response.sendRedirect("../"+clase.getSimpleName()+"/Borrar.jspx");
                  
                                
            }
            else
            {

                
                mensaje.setEtiqueta("deleteOK");
                mensaje.setTipo("info");                
                mensaje.guardarInformacion();
                                
                request.getSession().setAttribute("SessionMensajes", mensaje);

                    if ( request.getSession().getAttribute("vista") != null )
                    {

                        Object objVista = new Object();
                        objVista = request.getSession().getAttribute("vista");
                       
                        Serializacion serializacion = new Serializacion(objVista);
                        serializacion.getElementos() ;                        
                        elemento = serializacion.getElementos().get(1);             

                        nombreMetodo = "get" + elemento.getObjeto().toString().substring(0,1).toLowerCase().toUpperCase() 
                        + elemento.getObjeto().toString().substring(1).toLowerCase() ;
                       
                        response.sendRedirect("../"+objVista.getClass().getSimpleName()+"/Filtrar.do?id="
                                +objVista.getClass().getMethod(nombreMetodo).invoke(objVista).toString()
                                +"&pag=Registro.jspx") ;
                        
                        request.getSession().removeAttribute("vista");
                        
                    }
                    else
                    {

                        response.sendRedirect("../"+clase.getSimpleName()+"/Listar.do");
                        
                    }
               
            }
                        
    }
    
    
    public void for_update (Object objeto, HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
                          
            // falta obtener parametro
        
            if (objeto == null)
            {
                                
                mensaje = (Mensaje) request.getSession().getAttribute("SessionMensajes"); 
                
                mensaje.setEtiqueta("updateNOT");
                mensaje.setTipo("error");
                
                mensaje.guardarInformacion();
                
                
                if (mensaje.getMensajeBase() != "")
                {         
                    
                    String mensajeBase = "";
                    mensajeBase = mensaje.getMensajeBase();                    
                    mensajeBase = mensajeBase.replace("java.sql.SQLException:", "");
                    mensajeBase = mensajeBase.replace("org.postgresql.util.PSQLException:", "");
                    
                    mensajeBase = mensajeBase.trim();                                                   
                    mensaje.setInformacion( mensajeBase + " " + mensaje.getInformacion()  );                                        
                }
                                
                request.getSession().setAttribute("SessionMensajes", mensaje);
                
                response.sendRedirect(request.getHeader("Referer"));                
                
                
            }
            else
            {
                
                Serializacion serializacion = new Serializacion(objeto);
                serializacion.getElementos() ;                        
                elemento = serializacion.getElementos().get(1); 
            
                nombreMetodo = "get" + elemento.getObjeto().toString().substring(0,1).toLowerCase().toUpperCase() 
                + elemento.getObjeto().toString().substring(1).toLowerCase() ;
                
                mensaje.setEtiqueta("updateOK");
                mensaje.setTipo("info");
                
                mensaje.guardarInformacion();
                request.getSession().setAttribute("SessionMensajes", mensaje);
                
                
                    if ( request.getSession().getAttribute("vista") != null )
                    {

                        Object objVista = new Object();
                        objVista = request.getSession().getAttribute("vista");
                   
                        serializacion = new Serializacion(objVista);
                        serializacion.getElementos() ;                        
                        elemento = serializacion.getElementos().get(1);             

                        nombreMetodo = "get" + elemento.getObjeto().toString().substring(0,1).toLowerCase().toUpperCase() 
                        + elemento.getObjeto().toString().substring(1).toLowerCase() ;

                        response.sendRedirect("../"+objVista.getClass().getSimpleName()+"/Filtrar.do?id="
                                +objVista.getClass().getMethod(nombreMetodo).invoke(objVista).toString()
                                +"&pag=Registro.jspx") ;

                    }
                    else
                    {

                            response.sendRedirect("../"+objeto.getClass().getSimpleName()+"/Filtrar.do?id="
                                    +objeto.getClass().getMethod(nombreMetodo).invoke(objeto).toString()
                                    +"&pag=Registro.jspx");                

                    }
                    
            }

    }
    
}
