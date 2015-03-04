package basicbank;

/**
 * Created by Daryl Ebanks for SSE 554 Project 1, Spring 2015
 */

public abstract class Account 
{
    protected double balance;
    protected String holder;
    protected String password;
    protected double rate = .2;
    protected String accType = "";
    
    public enum CompoundResult
    {
        NONE,
        RATE,
        PENALTY
    };
    
    public Account(double balance, String holder, String password, String accType)
    {
        this.balance = balance;
        this.holder = holder;
        this.password = password;
        this.accType = accType;
    }
    
    protected void deposit(int amount)
    {
        if(amount > 0)
            balance+= amount;
    }
    
    protected abstract void withdraw(int amount);
    
    protected Double getBalance(String password)
    {
        if(authenticate(password))
            return balance;
        
        return null;
    }
    
    protected int getBalance(){
        return (int)balance;
    }
    
    protected Boolean isOverdrawn(String password)
    {
        if(authenticate(password))
            return balance < 0;
        
        return null;
    }
    
    protected abstract CompoundResult compoundInterest();
    
    protected Boolean authenticate(String password)
    {
        if(this.password.compareTo(password) == 0)
            return true;
        
        return false;
    }
}
