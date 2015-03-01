/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicbank;

/**
 *
 * @author josh
 */

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class BasicBank 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable()
                {
                    public void run()
                    {
//                        JFileChooser chooser = new JFileChooser("read");
//                        chooser.showOpenDialog(null);
//                        File file = chooser.getSelectedFile();
//                        JFrame frame = new LoginFrame(new File("XML/login.xml"));
//                        init(frame, "Login to Account");
//                        JFrame frame2 = new AddAccountFrame(new File("XML/addAccount.xml"));
//                        init(frame2, "Add Account");
                        ArrayList<String> accounts = new ArrayList<String>();
                        accounts.add("mine");
                        accounts.add("yours");
                        accounts.add("his");
                        
                        JFrame frame = new BankFrame(accounts);
                        init(frame,"Basic-Bank");
                    }
                });
    }
    
    public static void init(JFrame frame, String title)
    {
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}