/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basicbank;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import xmlio.XMLIO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import java.lang.Exception;
import javax.xml.parsers.ParserConfigurationException;


/**
 *
 * @author Josh
 */
public class Database 
{

    private ArrayList<Account> db;

    File file;


    public Database(File file)
    {
        db = new ArrayList<Account>();
        this.file=file;        
        //parse document for data
    }
    
    public Database(ArrayList<Account> db, File file)
    {
        this.db = db;
        this.file=file;
        XMLIO io = new XMLIO();
        
        Document doc = io.ReadXMLFile(file);
    }
    
    public void addAccount(String holder, String password, int balance, boolean checking)
    {
        if(checking == true){
            CheckingAccount chck = new CheckingAccount(balance, holder, password, "Checking");
            db.add(chck);
           
        }
        else{
            SavingsAccount svngs = new SavingsAccount(balance, holder, password, "Savings");
            db.add(svngs);
            
        } 
    }
    
    public void removeAccount(String holder)
    {
        // Remove account
        int index = getIndex(holder);
		
		if( index < 0)
			return;
		
		db.remove(index);
        
    }
    
    public int getIndex(String holder)
	{
		for(Account acct : db)
		{
			if(acct.holder.compareTo(holder) == 0)
				return db.indexOf(acct);
		}
		return -1;
	}
   
    public int getBalance()
    {
        // Get balance
        return 0;
    }
    
    public int withdrawls(String holder, String password)
    {
        // Get number of withdrawls
        return 0;
    }
    
    public void withdraw(int amount, Account acct)
    {
        // Withdraw
        if(verifyAcct(acct))
            acct.withdraw(amount);
    }
    
    public void deposit(int amount, Account acct)
    {
        acct.deposit(amount);
    }
    
    public void writeToFile()
    {
       try 
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
            DocumentBuilder builder;

            builder = factory.newDocumentBuilder();

            Document doc =  builder.newDocument();
            XMLIO io = new XMLIO(); 
            
            
            for(Account a: db){
                
            //root element
            Element rootElement = doc.createElement("Bank"); 
            doc.appendChild(rootElement);

            //add account elements
            Element acct_Tag = doc.createElement("Account");
            acct_Tag.appendChild(doc.createTextNode(a.holder));
            acct_Tag.appendChild(doc.createTextNode(a.password));
            acct_Tag.appendChild(doc.createTextNode(Double.toString(a.balance)));
            acct_Tag.appendChild(doc.createTextNode(a.accType));
            rootElement.appendChild(acct_Tag);

            io.WriteXMLFile(doc, new File("XML/Database.xml"));

            BufferedReader reader = new BufferedReader(new FileReader("XML/Database.xml"));
            String line = null, teststr2="";

            while ((line = reader.readLine()) != null) {
                teststr2 += line;
            }

            reader.close();
            }

           // Files.delete(FileSystems.getDefault().getPath("", "XML/Database.xml"));


        } catch (Exception e) {
                e.printStackTrace();
        }{}

    }
    
    public int getSize()
    {
        return db.size();
    }
    
    public ArrayList<String> getHolders()
    {
        ArrayList<String> holders = new ArrayList<String>();
        
        for(Account a : db)
        {
            holders.add(a.holder);
        }
        
        return holders;
    }
    
    public ArrayList<Account> getAccounts(){   
        return db;
    }
    
    public Account setAccount(String holder, String password){
        int temp=0;
        for(int i = 0; i<db.size(); i++){
        if(holder.compareTo(db.get(i).holder)==0 
                && (password.compareTo(db.get(i).password))==0) {
            temp = i;
        }
        
        }
        return db.get(temp);
    }
    
    public boolean verifyAcct(Account acct){
        boolean temp= false;
        for(Account a:db){
            if((a.holder.compareTo(acct.holder)==0) && (a.password.compareTo(acct.password)==0))
                    temp = true;
        }
      return temp;
        
    }

}
