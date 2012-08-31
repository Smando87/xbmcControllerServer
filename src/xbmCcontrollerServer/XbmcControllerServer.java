/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xbmCcontrollerServer;

import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *
 * @author sdelprete
 */
public class XbmcControllerServer {
     final static ResourceBundle rb =  ResourceBundle.getBundle("version"); 
    static Libreria l;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        
        //controllo se sono windows o linux
        Properties p=System.getProperties();
        String sistema=p.getProperty("sun.desktop");
        if(sistema.contains("windows"))
            Costanti.islinux=false;
        else
            Costanti.islinux=true;
        
        l=new Libreria();
        l.setVisible(true);
       
    }
    
    
    public static final String getRbTok(String propToken) { 
        String msg = ""; 
        try { 
            msg = rb.getString(propToken); 
        } catch (MissingResourceException e) { 
            System.err.println("Token ".concat(propToken).concat(" not in Propertyfile!")); 
        } 
       return msg; 
     }
}
