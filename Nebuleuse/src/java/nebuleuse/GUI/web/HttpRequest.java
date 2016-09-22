/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nebuleuse.GUI.web;
import javax.servlet.http.HttpServletRequest;
import nebuleuse.ORM.Secuencia;


public  class HttpRequest {
    
    public Integer getPage (HttpServletRequest request)
    {
        Integer pagina = 1;
        if ( request.getParameter("page") != null) 
        {
            pagina = Integer.parseInt(request.getParameter("page"));
        }
        return pagina;
    }
    
    
    public String getBuscar (HttpServletRequest request)
    {
        String retornar = null;
        
        if ( request.getParameter("buscar") != null) 
        {
            retornar = (String) request.getParameter("buscar");
        }
                
        return retornar;
    }
    
    

    public String getJSPorigen (HttpServletRequest request)
    {
        String retornar = null;
        
        if ( request.getParameter("jsp") != null) 
        {
            retornar = (String) request.getParameter("jsp");
        }
                
        return retornar;
    }
    


    
    
}
