
package xbmCcontrollerServer;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Del Prete Simone
 * 
 * 
 */

public class Sessione extends Thread{

    // Socket connessione;
    DataInputStream in;
    DataOutputStream out;
    Socket SOCK;
    int PORTA;
    String IP;
   
   
    String read = "";
    public Interprete interprete;
    
    
    //rappresenta la posizione nella lista(LinkedList) dei taxi collegati
    int IDSESSIONE;
    
    //rappresenta la posizione del socket nell' array dei socket 
    int INDEX;
    
    
    public Sessione(Socket sock){
        SOCK=sock;        
        interprete=new Interprete();
       
        
    }

    public void run(){
        try {
           
            //inizializzo
            in = new DataInputStream(SOCK.getInputStream());            
            out = new DataOutputStream(SOCK.getOutputStream());  
            String result;
            
            //Timeout di lettura dallo stream
            SOCK.setSoTimeout(5000);
             
            read=in.readUTF();
           
            if(read.length()>0){
                    result=interprete.decode(read);
                    if(result.length()>0){ 
                        out.writeUTF(result);
                    }
            }

                
           chiudiETerminaSessione();
         

        } catch (IOException ex) {
           // Logger.getLogger(Sessione.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }

     private void chiudiETerminaSessione(){
        try {
            in.close();
            out.close();
            SOCK.close();    
            this.interrupt();
            this.stop();
        } catch (IOException ex) {          
            Logger.getLogger(Sessione.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

}
