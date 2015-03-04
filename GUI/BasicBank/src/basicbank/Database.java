/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basicbank;

import java.io.File;
import java.util.ArrayList;
import org.w3c.dom.Document;
import xmlio.XMLIO;

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
        XMLIO io = new XMLIO();
        
        Document doc = io.ReadXMLFile(file);
        
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
            CheckingAccount chck = new CheckingAccount(balance, holder, password);
            db.add(chck);
           
        }
        else{
            SavingsAccount svngs = new SavingsAccount(balance, holder, password);
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
    
    public void withdraw(int amount)
    {
        // Withdraw
       
    }
    
    public boolean deposit(int amount)
    {
        // Deposit
        return true;
    }
    
    private void writeToFile()
    {
        // Write the db to a file?
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
    
}
