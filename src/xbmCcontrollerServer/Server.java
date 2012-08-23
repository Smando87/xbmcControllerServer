package xbmCcontrollerServer;

import java.awt.Color;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author Del Prete Simone
 * @date 18/06/2012
 * @mod 25/06/2012
 * @version 0.1.35
 * @since 0.1.1
 * 
 * TODO le LinkedList
 * TODO implementare ServerSocket SSL
 */
public class Server extends Thread{
    
  
    // porta di ascolto
    int PORT;
    
    private ServerSocket server;
    private Socket socket;
  
  
    /*
     * 
     * Costruttore di default: prepara il serversocket
     */
    public Server(int port){
    try {
            PORT=port;
            server = new ServerSocket(PORT);
            this.setName("Th-Server");
            
            
        } catch (IOException ex) {
          
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception ex){
                
                System.out.println(ex.toString()+"\nForse il server è gia avviato o è rimasta una sessione bloccata.\n" +
                "Apri il task manager e termina tutti i processi javaw.exe");}
    
    }   
   
    public void run(){
                     
            
           attendiConnessione();

    }
    
    private void attendiConnessione(){
        try {
            socket = server.accept();     
            new Sessione(socket).start();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        attendiConnessione();
    }
    
   
}