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
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import java.io.IOException;
import javax.swing.UIManager.*;

public class BasicBank 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        try {
          for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
          {
              if ("Nimbus".equals(info.getName()))
              {
                  UIManager.setLookAndFeel(info.getClassName());
                  break;
              }
           }
        } catch (Exception e) {}
        
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
                //ArrayList<Account> accounts = new ArrayList<Account>();

                ArrayList<Account> accounts = new ArrayList<Account>();
               accounts.add(new CheckingAccount(300, "mine", "pass", "Checking"));
               accounts.add(new CheckingAccount(300, "yours", "pass", "Checking"));
               accounts.add(new CheckingAccount(300, "mine", "pass", "Checking"));
                
            
                
                Database database = new Database(accounts,new File("Database.xml"));
                JFrame frame = new BankFrame(database);
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