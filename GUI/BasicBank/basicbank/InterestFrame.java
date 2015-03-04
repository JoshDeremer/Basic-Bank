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

public class InterestFrame extends JFrame
{
    private GridBagPane gridbag;
    
    @SuppressWarnings("unchecked")
    public InterestFrame()
    {
        File file = new File("XML/interest.xml");
        gridbag = new GridBagPane(file);
        add(gridbag);
        
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2-this.getSize().width/2), (dim.height/2-this.getSize().height/2));
    }
}
