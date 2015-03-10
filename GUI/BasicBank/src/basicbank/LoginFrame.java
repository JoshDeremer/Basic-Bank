/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basicbank;

/**
 *
 * @author Josh
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class LoginFrame extends JFrame
{
    private GridBagPane gridbag;
    private JButton login_button;
    private JButton cancel_button;
    private JTextArea login;
    private JTextArea password;
    // Captcha labels and text area defined.
    private JLabel captcha;
    private JLabel captcha2;
    private JTextArea captcha_try;
    private JLabel failure;
    private String text_holder;
    private String text_psswrd;
    private Account acct_Permssn;
    
    @SuppressWarnings("unchecked")
    public LoginFrame(Database data)
    {
        File file = new File("XML/login.xml");
        gridbag = new GridBagPane(file);
        add(gridbag);
        
        login_button = (JButton) gridbag.get("login_button");
        cancel_button = (JButton) gridbag.get("cancel_button");
        login = (JTextArea) gridbag.get("login");
        password = (JTextArea) gridbag.get("password");
        // Captcha labels and text area instantiated. Use after this point.
        captcha = (JLabel) gridbag.get("captcha");
        captcha2 = (JLabel) gridbag.get("captcha2");
        captcha_try = (JTextArea) gridbag.get("captcha_try");
        failure = (JLabel) gridbag.get("failure");
        captcha.setVisible(false);
        captcha2.setVisible(false);
        captcha_try.setVisible(false);
        failure.setVisible(false);
        
        
        ActionListener login_click = new ActionListener(){
            public void actionPerformed(ActionEvent event){
                //login
               ArrayList<Account> temp = data.getAccounts();
               text_holder = login.getText();
               text_psswrd = password.getText();
               
               for(Account a : temp)
                   if(a.holder.equalsIgnoreCase(text_holder) 
                                    && a.password.equals(text_psswrd))
                   {
                       acct_Permssn = data.setAccount(text_holder, text_psswrd);
                       showAccount(acct_Permssn, data);
                       dispose();
                   }
                   else 
                   {  failure.setText("Sorry Wrong Password!");}
                 
                
            }};
        
        ActionListener cancel_click = new ActionListener(){
            public void actionPerformed(ActionEvent event){
                dispose();
            }};
        
        login_button.addActionListener(login_click);
        cancel_button.addActionListener(cancel_click);
        
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2-this.getSize().width/2), (dim.height/2-this.getSize().height/2));
        this.setResizable(false);
    }
    
    public void showAccount(Account acct, Database data)
    {
        JFrame add = new AccountFrame(data, acct);
        add.setTitle(acct.holder + "'s " + acct.accType + " Account");
        add.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add.setVisible(true);
    }
}
