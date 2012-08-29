
package log;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author Simone Del Prete
 * @since 19/11/2011
 * @mod 18/06/2012
 * @version 0.3
 *
 */
public class Logger {

    static PrintWriter logfile;
    int lunghmaxstringhe=15;
    public static String INFO="INFO";
    public static String WARNING="WARNING";
    public static String ERROR="ERROR";
    static String libversion="0.2";
    JTextPane SCREEN;
    public boolean DEBUG;
    /*
     * Costruttore di default 
     * 
     */
    public Logger(){
      
    }
    /**
     * Costruttore 2
     * @param append indica se i log vanno in coda al file o se ne viene genrato uno nuovo
     * @param path cartella relativa per il file di log, se "" verrà ignorata
     * @param nomefile nome del file di log
     *
     */
    public Logger(boolean append, String path, String nomefile){
       File f;
        try {
            if(path.length()>0)
                f=new File(path+"/"+nomefile);
            else
                f=new File("./"+nomefile);
            
            if(!f.exists())
                f.createNewFile();
            logfile = new PrintWriter(new BufferedWriter(new FileWriter(f,true)),true);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
        aggiungiRiga(true,"Logger "+libversion+" avviato...", "Logger",INFO);
    }

    
    /**
     * Costruttore 3
     * 
     * @param append true aggiunge in coda al file, false ne crea uno nuovo
     * @param path cartella di log
     * @param nomefile nome del file di log
     * @param jep JEditorPane su cui visualizzare il log
     */
     public Logger(boolean append, String path, String nomefile,JTextPane jep){
       File f;
        try {
            if(path.length()>0)
                f=new File(path+"/"+nomefile);
            else
                f=new File("./"+nomefile);
            
            if(!f.exists())
                f.createNewFile();
            logfile = new PrintWriter(new BufferedWriter(new FileWriter(f,true)),true);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
        SCREEN=jep;
      //  toScreen("Logger "+libversion+" avviato...", this.getClass(),INFO);
       
    }
    
    public void toScreen(String s,Class module,String TYPE){
        Color c = Color.BLACK;
        if(TYPE!=null){
            if(TYPE.contains("ERROR"))
                c=Color.RED; 
            if(TYPE.contains("WARNING"))
                c=new Color(255,106,0);
            if(TYPE.contains("DEBUG"))
                c=Color.BLUE;
            if(module.toString().equalsIgnoreCase("Exception"))
                c=Color.RED; 
        }
        StyleContext sc = StyleContext.getDefaultStyleContext(); 
        javax.swing.text.AttributeSet aset =  sc.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground, c);
        int len =SCREEN.getDocument().getLength(); // same value as
        SCREEN.setCaretPosition(len); // place caret at the end (with no selection)
        SCREEN.setCharacterAttributes( aset, false);
        SCREEN.replaceSelection("["+getData()+" - "+module.getSimpleName()+" - "+TYPE+"] "+s+"\n"); // there is no selection, so inserts at caret
        SCREEN.setFont(new Font("Monospaced",Font.PLAIN,14));
    
        aggiungiRiga(s,module,TYPE);
    }
    
    public void debugToScreen(String s,Class module,String TYPE){
        if(DEBUG){
            toScreen(s,module,(TYPE+"-DEBUG"));
            aggiungiRiga(s,module,TYPE);
        }
    }
    public void debugStackTraceToScreen(Exception e){
    
        StackTraceElement[] st=e.getStackTrace();
        for(int i=0;i<st.length;i++)
            toScreen(st[i].toString(),e.getClass(),"ERROR");
            
        
    }
    
    /**
     *
     * @param attivadata
     * @param txt
     * @param classe
     * @param tipomess
     */
    public void aggiungiRiga(boolean attivadata,String txt, String classe, String tipomess){
        String data="";
        int lungclass=classe.length();
        if(lungclass<lunghmaxstringhe)
            for(int i=0;i<lunghmaxstringhe-lungclass;i++)
                classe+=" ";
        if(attivadata)
            data=getData()+" | ";        
         logfile.println(data+classe+" | "+tipomess+" | "+txt+"");
         logfile.flush();
        
    }
    
    
    /**
     *
     * @param attivadata
     * @param txt
     * @param classe
     * @param tipomess
     */
    public void aggiungiRiga(String s,Class module,String TYPE){
        String data="";
      
       
         logfile.println("["+getData()+" - "+module.getSimpleName()+" - "+TYPE+"] "+s+"");
         logfile.flush();
        
    }

    /**
     *
     * @return data e ora correnti
     */
    private String getData(){
       return new GregorianCalendar().getTime().toString();
    }

   
}
