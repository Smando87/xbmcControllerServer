/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xbmCcontrollerServer;


import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
 
   InputStream in;
    
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
                 Costanti.os.write((cmd.substring(cmd.indexOf(";")+1)+"\n").getBytes());
                 Costanti.os.flush();
            }
            if(command.equals("lista_film")){
                return lista_film();
            }
            if(command.equals("InviaFilm")){
                String Titolo=cmd.substring(cmd.indexOf(";")+1);
                InviaFilm(Titolo);
            }
        /*    if(command.equals("Pausa")){
                Costanti.os.write("pause\n".getBytes());
                Costanti.os.flush();
            }*/
           
                      
        } catch (IOException ex) {
            Logger.getLogger(Interprete.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    private void InviaFilm(String titolo){
        try {
            
            Process p=Runtime.getRuntime().exec("vlc -I rc -f "+Costanti.percorsi.replace("\n","")+"/"+titolo);
            in=p.getInputStream();
            Costanti.os=p.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(Interprete.class.getName()).log(Level.SEVERE, null, ex);
        }
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
