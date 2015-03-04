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
        
        ActionListener login_click = new ActionListener(){
            public void actionPerformed(ActionEvent event){
                //login
                
                
                if(true)
                    showAccount("John", data);
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
    
    public void showAccount(String account, Database data)
    {
        JFrame add = new AccountFrame(data);
        add.setTitle(account + "'s Account");
        add.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add.setVisible(true);
    }
}
