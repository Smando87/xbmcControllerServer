/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xbmCcontrollerServer;


import java.io.File;
import java.io.FileFilter;
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
            if(command.equals("esegui")){
                System.out.println("Eseguo: "+cmd.substring(cmd.indexOf(";")+1));
                Runtime.getRuntime().exec(cmd.substring(cmd.indexOf(";")+1));
            }
            if(command.equals("lista_film")){
                return lista_film();
            }
           
                      
        } catch (IOException ex) {
            Logger.getLogger(Interprete.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    
    public String lista_film(){
      //FileFilter filter=new FileFilter("*.avi");
                 String[] percorsi=Costanti.percorsi.split("\n");
               //  File[] cartelle= new File[percorsi.length];
                 String filesString = "";
                 File f;
                 for(int j = 0; j< percorsi.length;j++){                    
                    f= new File(percorsi[j]);
                    File[] files=f.listFiles();  
                    for(int i =0; i< files.length;i++)
                        if(files[i].getName().contains(".avi"))
                            filesString+=files[i].getName()+"\n";
                    
                 }
                 System.out.println(filesString);
                 return "lista_film;"+filesString;
    }
       
}
