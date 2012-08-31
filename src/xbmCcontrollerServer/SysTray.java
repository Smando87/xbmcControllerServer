/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xbmCcontrollerServer;




import java.awt.AWTException;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JPopupMenu.Separator;

/**

*

* @author sdelprete

*/

public class SysTray {
    static TrayIcon trayIcon = null;
    static private MenuItem hideshowItem;
    static private ActionListener background;
    static private  ActionListener show ;

    public  SysTray(){

        try {
            Image image = Toolkit.getDefaultToolkit().getImage("images/tray.png");
            SystemTray sysTray = SystemTray.getSystemTray();
            ActionListener exitListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    exitActionPerformed(e);
                }

            };

             background = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    hideActionPerformed(e);
                }

            };

            show = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showActionPerformed(e);

                }

            };



            PopupMenu menu = new PopupMenu();

            MenuItem exitItem = new MenuItem("Exit");
            hideshowItem = new MenuItem("Nascondi");

            hideshowItem.addActionListener(background);
            exitItem.addActionListener(exitListener);


            menu.add("VlcController");

            menu.addSeparator();

            menu.add(hideshowItem);

            menu.add(exitItem);



            trayIcon = new TrayIcon(image, "VlcController", menu);

            trayIcon.setImageAutoSize(true);

            sysTray.add(trayIcon);

            trayIcon.displayMessage("VlcController", "Applicazione avviata!", TrayIcon.MessageType.INFO);



        } catch (AWTException ex) {

            Logger.getLogger(SysTray.class.getName()).log(Level.SEVERE, null, ex);

        }



    }





    public void scriviMessaggio(String mess, TrayIcon.MessageType TIPO_MESS){

        trayIcon.displayMessage("VlcController", mess, TIPO_MESS);

    }



    public void exitActionPerformed(ActionEvent e){

        System.out.println("Bye from the tray");

        System.exit(0);



    }

    public void hideActionPerformed(ActionEvent e){
        XbmcControllerServer.l.setVisible(false);
      //   VlcControllerView.hide(VlcControllerApp.getApplication());
         hideMessage();
         

    }

    public static void hideMessage(){
       // trayIcon.displayMessage( "VlcController","L'applicazione continua a girare in Background", TrayIcon.MessageType.INFO);

         hideshowItem.setLabel("Apri");

         hideshowItem.removeActionListener(background);

         hideshowItem.addActionListener(show);
    }

    public void showActionPerformed(ActionEvent e){
         XbmcControllerServer.l.setVisible(true);
         
       // VlcControllerView.show(VlcControllerApp.getApplication());

        hideshowItem.setLabel("Nascondi");

        hideshowItem.removeActionListener(show);

        hideshowItem.addActionListener(background);

    }



}