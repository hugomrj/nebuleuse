
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nebuleuse.GUI.web;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;

public class Mensaje {
    
    private Locale locale = Locale.getDefault();
    private String informacion;   
    private String etiqueta = "";
    private String ruta ="com.itx.properties.mensajes";
    private String mensajeBase = "";
    private String tipo = "";
    
    private ResourceBundle RecursoPropiedades(){
        
        ResourceBundle.clearCache();     
        ResourceBundle propiedades = ResourceBundle.getBundle(getRuta(), this.getLocale());              
        return propiedades;    
    } 
    
    public  String mostrarMensaje(String strEtiqueta) {    
        return this.RecursoPropiedades().getString(strEtiqueta);        
    }
                
    public  String mostrarMensaje() {                 
        return this.RecursoPropiedades().getString(getEtiqueta());
    }
    
    public void guardarInformacion(){
        this.setInformacion(this.mostrarMensaje());
    }
    
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getMensajeBase() {
        return mensajeBase;
    }

    public void setMensajeBase(String mensajeBase) {
        this.mensajeBase = mensajeBase;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void info(String etiqueta, HttpServletRequest request) {
                
        this.setTipo("info");
        this.setEtiqueta(etiqueta);
        this.guardarInformacion();                    
        request.getSession().setAttribute("SessionMensajes", this);            
        
    }    

    public void error(String etiqueta, HttpServletRequest request) {
                
        this.setTipo("error");
        this.setEtiqueta(etiqueta);
        this.guardarInformacion();                    
        request.getSession().setAttribute("SessionMensajes", this);            
        
    }    



    
}