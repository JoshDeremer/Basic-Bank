package basicbank;

/**
 * Created by Daryl Ebanks for SSE 554 Project 1, Spring 2015
 */
 import java.security.*;
 import java.util.*;

public abstract class Account 
{
    protected double balance;
    protected String holder;
    protected String salt;
    private byte[]saltArray=new byte[256];
    protected String password;
    protected double rate = .2;
    protected String accType = "";
    private MessageDigest alg;
    private Base64.Encoder encoder=Base64.getEncoder();
    private Base64.Decoder decoder=Base64.getDecoder();
    
    public enum CompoundResult
    {
        NONE,
        RATE,
        PENALTY
    };
    
    public Account(double balance, String holder, String passwordString, String accType)
    {
        //Kei'Shawn made adjusments
        this.balance = balance;
        this.holder = holder;
        
        SecureRandom random=new SecureRandom();
        random.nextBytes(saltArray);
        
        password=createHash(passwordString);
         
        
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
    
    protected String createHash(String passwordString)
    {
        byte[]pwStringBytes=passwordString.getBytes();
        byte[]temp=new byte[pwStringBytes.length+saltArray.length];
        System.arraycopy(pwStringBytes,0,temp,0,pwStringBytes.length);
        System.arraycopy(saltArray,0,temp,pwStringBytes.length,saltArray.length);
        try
        {
           alg=MessageDigest.getInstance("SHA-256");         
           alg.update(temp);
           byte[]passwordArray=alg.digest();
           return (encoder.encodeToString(passwordArray));
        }
        catch(Exception e){
        return "A";}
    }
    
    protected boolean authenticate(String attempt)
    {
        String attemptHash=createHash(attempt);
        return (attemptHash.compareTo(password)==0);
    }
}
