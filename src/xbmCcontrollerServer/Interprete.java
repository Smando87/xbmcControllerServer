/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xbmCcontrollerServer;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Del Prete Simone
 * @date 18/06/2012
 * @mod 22/06/2012
 * @version 0.1.35
 * @since 0.1.1
 * 
 * TODO implementare i comandi utente
 *
 * 
 * @note: I comandi sono codificati nella forma comando;parametro1;parametro2;parametroN
 *        i parametri sono delimitati dal carattere ";"
 * 
 * 
 * @comandi:
 */
public class Interprete {
  
   
    
    public Interprete(){
        
       
    }
        
    
    /**
     * 
     * @param cmd: comando+parametri
     * @return String
     * 
     */
    public String decode(String cmd){
        try {
            //tutti i comandi finiscono per ';'
            String command=cmd.substring(0,cmd.indexOf(";"));
            System.out.println("Eseguo: "+command);
            Runtime.getRuntime().exec(command);
                      
        } catch (IOException ex) {
            Logger.getLogger(Interprete.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    
    
     
    
}
