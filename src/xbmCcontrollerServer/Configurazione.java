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
 * Gestisce il savataggio e il caricamento della configurazione da file
 */
public class Configurazione {
    
    String cfg_file;

    
    public Configurazione(String cfg_file) {
        this.cfg_file = cfg_file;
    }
    
    
    public void salvaSuFile() throws IOException{
        Properties defaultProps = new Properties();
        defaultProps.setProperty("percorsi", Costanti.percorsi);
        defaultProps.setProperty("autostartServer", String.valueOf(Costanti.autostartServer));
        defaultProps.setProperty("vlc_exe", Costanti.vlcexe_path);
        defaultProps.storeToXML(new FileOutputStream(cfg_file), "Configurazione server");
    }

    public void caricaDaFile() throws FileNotFoundException, IOException{
        Properties defaultProps = new Properties();
        FileInputStream in = new FileInputStream(cfg_file);
        defaultProps.loadFromXML(in);
        Costanti.percorsi=defaultProps.getProperty("percorsi","");
        Costanti.autostartServer=Boolean.parseBoolean(defaultProps.getProperty("autostartServer"));
        Costanti.vlcexe_path=defaultProps.getProperty("vlc_exe","C:\\Program Files\\VideoLAN\\VLC\\");
        in.close();        
    
    }
    
}
