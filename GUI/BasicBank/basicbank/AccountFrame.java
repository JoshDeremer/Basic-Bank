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

public class AccountFrame extends JFrame
{
    private GridBagPane gridbag;
    private JButton withdraw_button;
    private JButton deposit_button;
    private JTextArea value;
    private JLabel current_balance;
    
    @SuppressWarnings("unchecked")
    public AccountFrame(Database data, Account acct)
    {
        File file = new File("XML/account.xml");
        gridbag = new GridBagPane(file);
        add(gridbag);
        
        withdraw_button = (JButton) gridbag.get("withdraw_button");
        deposit_button = (JButton) gridbag.get("deposit_button");
        value = (JTextArea) gridbag.get("value");
        current_balance = (JLabel) gridbag.get("current_balance");
        
       current_balance.setText("Current Balance: " + Integer.toString(acct.getBalance()));
        
        
        ActionListener withdraw_click = new ActionListener(){
            public void actionPerformed(ActionEvent event){
                //@author Kei'Shawn
                //withdraw
                
                data.withdraw(Integer.parseInt(value.getText()), acct);
                current_balance.setText("Current Balance: " + Integer.toString(acct.getBalance()));
            }};
        
        ActionListener deposit_click = new ActionListener(){
            public void actionPerformed(ActionEvent event){
                //@author Kei'Shawn
                //deposit
                data.deposit(Integer.parseInt(value.getText()), acct);
                current_balance.setText("Current Balance: " + Integer.toString(acct.getBalance()));
            }};
        
        withdraw_button.addActionListener(withdraw_click);
        deposit_button.addActionListener(deposit_click);
        
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2-this.getSize().width/2), (dim.height/2-this.getSize().height/2));
        this.setResizable(false);
    }
}
