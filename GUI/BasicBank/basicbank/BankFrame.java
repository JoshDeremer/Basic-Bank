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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class BankFrame extends JFrame
{
    private GridBagPane gridbag;
    private JButton add_button;
    private JButton remove_button;
    private JButton use_button;
    private JList account;
    private JScrollPane scroller;
    
    @SuppressWarnings("unchecked")
    public BankFrame(Database data)
    {
        File file = new File("XML/bank.xml");
        gridbag = new GridBagPane(file);
        add(gridbag);
        
        add_button = (JButton) gridbag.get("add_button");
        remove_button = (JButton) gridbag.get("remove_button");
        use_button = (JButton) gridbag.get("use_button");
        
        initList(data);
        scroller=(JScrollPane) gridbag.get("scroller");
        scroller.setViewportView(account);
        //scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        account.setVisibleRowCount(10);
        
        WindowListener frame_focus = new WindowListener(){
            @Override
            public void windowOpened(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosing(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 data.writeToFile();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowIconified(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowActivated(WindowEvent e) {
                    initList(data);
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        ActionListener add_click = new ActionListener(){
            public void actionPerformed(ActionEvent event){
                JFrame add = new AddAccountFrame(data);
                add.setTitle("Add Account");
                add.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                add.setVisible(true);
            }};
        
        ActionListener remove_click = new ActionListener(){
            public void actionPerformed(ActionEvent event){
                data.removeAccount((String)account.getSelectedValue());
                initList(data);
            }};
        
        ActionListener use_click = new ActionListener(){
            public void actionPerformed(ActionEvent event){
                JFrame add = new LoginFrame(data);
                add.setTitle("Login to Account");
                add.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                add.setVisible(true);
            }};
        
        this.addWindowListener(frame_focus);
        add_button.addActionListener(add_click);
        remove_button.addActionListener(remove_click);
        use_button.addActionListener(use_click);
        
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2-this.getSize().width/2), (dim.height/2-this.getSize().height/2));
    }
    
    public void initList(Database data)
    {
        DefaultListModel<String> list = new DefaultListModel<String>();
        ArrayList<String> holders = data.getHolders();
        
        for(String str: holders)
        {
            list.addElement(str);
        }
        account=new JList(list);
    }
}
