package basicbank;

/**
 * Created by Daryl Ebanks for SSE 554 Project 1, Spring 2015
 */
public class SavingsAccount extends Account
{
    protected int maxWithdrawals = 5, currentWithdrawals = 0;
    protected String accType;
    
    public SavingsAccount(double balance, String holder, String password, String accType)
    {
        super(balance, holder, password, accType);
       
        rate = 0.05;
    }
    
    /**
     * Used to withdraw funds from the account
     * @param amount amount of funds to be withdrawn
     * @param password the password for the account
     */
    protected @Override void withdraw(int amount)
    {
        currentWithdrawals++;
        
        if(currentWithdrawals >= maxWithdrawals)
        {
            // 10 dollar fee for excess withdraw attempts
            balance -=10;
            return;
        }
        
        if(amount > 0)
            balance -= amount;
    }
    
    /**
     * Performs a compound interest operation on the account.
     * @return NONE if no operation performed. RATE if interest rate is applied to 
     * account. PENALTY if the balance is below zero at time of operation.
     */
    protected @Override CompoundResult compoundInterest()
    {
        if(balance > 0)
        {
            balance += balance * rate;
            return CompoundResult.RATE;
        }
        
        if(balance < 0)
        {
            // Apply 30 dollar low balance fee
            balance -= 30;
            return CompoundResult.PENALTY;
        }
        
        this.currentWithdrawals = 0;
        
        return CompoundResult.NONE;
    }
    
    /**
     * Convert the object to a string with holder name and account type
     * @return string of holder name and Checking
     */
    public @Override String toString()
    {
        return holder + " *Savings*";
    }
    
}
