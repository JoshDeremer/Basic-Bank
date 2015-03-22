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
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class LoginFrame extends JFrame
{
    private GridBagPane gridbag;
    private JButton login_button;
    private JButton cancel_button;
    private JTextArea login;
    private JPasswordField password;
    // Captcha labels and text area defined.
    private JLabel captcha=new JLabel();
    private JTextArea captcha_try;
    private JLabel failure;
    private String text_holder;
    private String text_psswrd;
    private Account acct_Permssn;
    private int attempts=6;
    private String captchaText="";
    @SuppressWarnings("unchecked")
    public LoginFrame(Database data)
    {
        File file = new File("XML/login.xml");
        gridbag = new GridBagPane(file);
        add(gridbag);
        
        login_button = (JButton) gridbag.get("login_button");
        cancel_button = (JButton) gridbag.get("cancel_button");
        login = (JTextArea) gridbag.get("login");
        password = (JPasswordField) gridbag.get("password");
        // Captcha labels and text area instantiated. Use after this point.
        captcha = (JLabel) gridbag.get("captcha");
        captcha_try = (JTextArea) gridbag.get("captcha_try");
        failure = (JLabel) gridbag.get("failure");
        captcha.setVisible(false);
        captcha_try.setVisible(false);
        failure.setVisible(false);
        
        ActionListener login_click = new ActionListener(){
            public void actionPerformed(ActionEvent event){
                //login
               text_holder = login.getText();
               text_psswrd = password.getText();
               
               if (data.verifyAcct(text_holder,text_psswrd)&&captchaText.equals(captcha_try.getText()))
               {
                  showAccount(data.setAccount(text_holder, text_psswrd),data);
                  attempts=6;
                  failure.setVisible(false);
                  captcha.setVisible(false);
                  captcha_try.setVisible(false);
                  captchaText="";
                  captcha_try.setText("");
                  pack();
               }
               else if (attempts==6)
               {
                  attempts--;
                  failure.setText("<html><div style=\"text-align: center;\">Bad username/password combination.<br>You have 5 login attempts remaining.</html>");
                  failure.setVisible(true);
                  pack();
               }  
               else if (attempts>0)
               {
                  attempts--;
                  if (attempts==1)
                     failure.setText("<html><div style=\"text-align: center;\">Bad username/password combination.<br>You have "+attempts+" login attempt remaining.</html>");
                  else
                     failure.setText("<html><div style=\"text-align: center;\">Bad username/password combination.<br>You have "+attempts+" login attempts remaining.</html>");
               }
               else if (attempts==0)
               {
                  attempts--;
                  captcha.setText("Please enter the text you see above.");
                  captcha.setIcon(newCaptcha());
                  captcha.setVerticalTextPosition(JLabel.BOTTOM);
                  captcha.setHorizontalTextPosition(JLabel.CENTER);
                  captcha.setVisible(true);
                  captcha_try.setVisible(true);
                  failure.setText("Bad username/password combination.");
                  pack();
               }
               else
               {
                  captcha.setIcon(newCaptcha());
               }
                
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
    
    private ImageIcon newCaptcha()
    {
       captchaText="";
       Random r=new Random();
       int selector;
       for (int i=0;i<5;i++)
       {
          selector=r.nextInt(3);
          if (selector==0)
             captchaText=captchaText+Character.toString((char)(r.nextInt(10)+48));
          else if (selector==1)
             captchaText=captchaText+Character.toString((char)(r.nextInt(26)+65));
          else
             captchaText=captchaText+Character.toString((char)(r.nextInt(26)+97));
       }
   
       int w=150;
       int h=50;
       BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
       Graphics2D g2d = img.createGraphics();
       Font font = new Font("Verdana", Font.PLAIN, 36);
       g2d.setFont(font);
       FontMetrics fm = g2d.getFontMetrics();
   
    
       g2d.setFont(font);
       fm = g2d.getFontMetrics();
       g2d.setColor(Color.WHITE);
       g2d.fillRect(0, 0, w, h);
       int drawTimes=r.nextInt(11);
       for (int i=0;i<(10+drawTimes);i++)
       {
          g2d.setColor(new Color(r.nextFloat(),r.nextFloat(),r.nextFloat()));
          g2d.setStroke(new BasicStroke(r.nextInt(3)+1));
          g2d.draw(new Line2D.Float(r.nextInt(150), r.nextInt(50), r.nextInt(151), r.nextInt(51)));
       }
       g2d.setColor(Color.BLACK);
       g2d.drawString(captchaText,((w - fm.stringWidth(captchaText)) / 2),((fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2)));
       g2d.dispose();
       ImageIcon icon=new ImageIcon(img);
       return icon;
    }
    
    public void showAccount(Account acct, Database data)
    {
        JFrame add = new AccountFrame(data, acct);
        add.setTitle(acct.holder + "'s " + acct.accType + " Account");
        add.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add.setVisible(true);
    }
}
