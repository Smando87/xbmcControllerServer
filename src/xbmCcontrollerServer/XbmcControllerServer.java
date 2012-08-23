/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xbmCcontrollerServer;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author sdelprete
 */
public class XbmcControllerServer {
     final static ResourceBundle rb =  ResourceBundle.getBundle("version"); 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        int port=9021;
        Server srv=new Server(port);
        
        System.out.println("Avvio xbmcController "+getRbTok("VERSIONE")+"."+getRbTok("BUILD")+" sulla porta: "+port);
        
        srv.start();
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
