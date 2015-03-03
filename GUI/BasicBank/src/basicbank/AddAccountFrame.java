/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basicbank;

/**
 *
 * @author Josh
 * @author of Database communication Kei'Shawn
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class AddAccountFrame extends JFrame
{
    private GridBagPane gridbag;
    private JButton save_button;
    private JButton cancel_button;
    private JTextArea name;
    private JTextArea password;
    private JTextArea balance;
    private JRadioButton checking;
    private JRadioButton savings;
    private String text_Name, text_Psswrd;
    private int text_balance;
    
    @SuppressWarnings("unchecked")
    public AddAccountFrame()
    {
        File file = new File("XML/addAccount.xml");
        gridbag = new GridBagPane(file);
        add(gridbag);
        
        save_button = (JButton) gridbag.get("save_button");
        cancel_button = (JButton) gridbag.get("cancel_button");
        name = (JTextArea) gridbag.get("name");
        password = (JTextArea) gridbag.get("password");
        balance = (JTextArea) gridbag.get("balance");
        checking = (JRadioButton) gridbag.get("checking");
        savings = (JRadioButton) gridbag.get("savings");
        
        ButtonGroup group = new ButtonGroup();
        group.add(checking);
        group.add(savings);
        
        ActionListener save_click = new ActionListener(){
            public void actionPerformed(ActionEvent event){
                Database stored_Accts = new Database(file);
                text_Name = name.getText();
                text_Psswrd = password.getText();
                text_balance = Integer.parseInt(balance.getText());
          
               if(checking.isSelected())
                   stored_Accts.addAccount(text_Name, text_Psswrd, text_balance, true);
               if(savings.isSelected())
                   stored_Accts.addAccount(text_Name, text_Psswrd, text_balance, false);
            }};
        
        ActionListener cancel_click = new ActionListener(){
            public void actionPerformed(ActionEvent event){
                dispose();
            }};
        
        save_button.addActionListener(save_click);
        cancel_button.addActionListener(cancel_click);
        
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2-this.getSize().width/2), (dim.height/2-this.getSize().height/2));
        this.setResizable(false);
    }
    
}
