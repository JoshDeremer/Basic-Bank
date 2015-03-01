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

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class BankFrame extends JFrame
{
    private GridBagPane gridbag;
    private JButton add_button;
    private JButton remove_button;
    private JButton use_button;
    private JButton interest_button;
    private JList account;
    
    public BankFrame(ArrayList<String> accounts)
    {
        File file = new File("XML/bank.xml");
        gridbag = new GridBagPane(file);
        add(gridbag);
        
        add_button = (JButton) gridbag.get("add_button");
        remove_button = (JButton) gridbag.get("remove_button");
        use_button = (JButton) gridbag.get("use_button");
        interest_button = (JButton) gridbag.get("interest_button");
        account = (JList) gridbag.get("account");
        
        initList(accounts);
        
        ActionListener add_click = new ActionListener(){
            public void actionPerformed(ActionEvent event){
                JFrame add = new AddAccountFrame();
                add.setTitle("Add Account");
                add.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                add.setVisible(true);
            }};
        
        ActionListener remove_click = new ActionListener(){
            public void actionPerformed(ActionEvent event){
                accounts.remove((String)account.getSelectedValue());
                initList(accounts);
            }};
        
        ActionListener use_click = new ActionListener(){
            public void actionPerformed(ActionEvent event){
                JFrame add = new LoginFrame();
                add.setTitle("Login to Account");
                add.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                add.setVisible(true);
            }};
        
        ActionListener interest_click = new ActionListener(){
            public void actionPerformed(ActionEvent event){
                // interest
            }};
        
        add_button.addActionListener(add_click);
        remove_button.addActionListener(remove_click);
        use_button.addActionListener(use_click);
        interest_button.addActionListener(interest_click);
        
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2-this.getSize().width/2), (dim.height/2-this.getSize().height/2));
    }
    
    public void initList(ArrayList<String> accounts)
    {
        DefaultListModel<String> list = new DefaultListModel<String>();
        for (int i = 0; i < accounts.size(); i++)
            list.addElement(accounts.get(i));
        account.setModel(list);
    }
}
