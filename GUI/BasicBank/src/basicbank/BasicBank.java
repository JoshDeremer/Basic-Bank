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
import java.awt.event.*;
import java.io.*;
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
                        JFileChooser chooser = new JFileChooser("read");
                        chooser.showOpenDialog(null);
                        File file = chooser.getSelectedFile();
                        JFrame frame = new FontFrame(file);
                        frame.setTitle("GridBagTest");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                    }
                });
    }
}