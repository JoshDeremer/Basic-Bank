/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basicbank;

import java.io.File;
import java.util.ArrayList;

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
        this.file = file;
    }
    
    public Database(ArrayList<Account> init, File file)
    {
        this.db = init;
        this.file = file;
    }
    
    public boolean addAccount(String holder, String password, int balance, boolean checking)
    {
        // Add account
        return true;
    }
    
    public boolean removeAccount(String holder)
    {
        // Remove account
        return true;
    }
    
    public int getBalance(String holder, String password)
    {
        // Get balance
        return 0;
    }
    
    public int withdrawls(String holder, String password)
    {
        // Get number of withdrawls
        return 0;
    }
    
    public boolean withdraw(String holder, String password, int amount)
    {
        // Withdraw
        return true;
    }
    
    public boolean deposit(String holder, String password, int amount)
    {
        // Deposit
        return true;
    }
    
    public boolean interest()
    {
        // Perform interest action
        return true;
    }
    
    private void writeToFile()
    {
        // Write the db to a file?
    }
}