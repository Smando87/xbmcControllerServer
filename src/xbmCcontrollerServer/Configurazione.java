/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xbmCcontrollerServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author sdelprete
 */
public class Configurazione {
    
    String cfg_file;

    
    public Configurazione(String cfg_file) {
        this.cfg_file = cfg_file;
    }
    
    public void inizializza() throws FileNotFoundException, IOException{
        Properties defaultProps = new Properties();
        defaultProps.setProperty("percorsi", "test");
        defaultProps.storeToXML(new FileOutputStream(cfg_file), "Configurazione server");
    }
    
    public void salvaSuFile() throws IOException{
        Properties defaultProps = new Properties();
        defaultProps.setProperty("percorsi", Costanti.percorsi);
        defaultProps.storeToXML(new FileOutputStream(cfg_file), "Configurazione server");
    }

    public void caricaDaFile() throws FileNotFoundException, IOException{
        Properties defaultProps = new Properties();
        FileInputStream in = new FileInputStream(cfg_file);
        defaultProps.loadFromXML(in);
        Costanti.percorsi=defaultProps.getProperty("percorsi");
    //    defaultProps.
        
        in.close();
    
    }
    
}
