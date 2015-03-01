package basicbank;

/**
 * Created by Daryl Ebanks for SSE 554 Project 1, Spring 2015
 */

public class CheckingAccount extends Account
{
    protected int minimumBalance = 50;
    
    /**
     * This method initializes a checking account.
     * @param balance the starting balance of account
     * @param holder the name of the account owner
     * @param password the password for the account
     */
    public CheckingAccount(double balance, String holder, String password)
    {
        super(balance, holder, password);
        
        rate = .1;
    }
    
    /**
     * Used to withdraw funds from the account
     * @param amount amount of funds to be withdrawn
     * @param password the password for the account
     */
    protected @Override void withdraw(double amount, String password)
    {
        if(!authenticate(password))
            return;
        
        if(amount > 0)
            balance -= amount;
    }
    
    /**
     * Performs a compound interest operation on the account.
     * @return NONE if no operation performed. RATE if interest rate is applied to 
     * account. PENALTY if the balance is below minimum at time of operation.
     */
    protected @Override CompoundResult compoundInterest()
    {
        if(balance == 0)
            return CompoundResult.NONE;
        
        if(balance > minimumBalance)
        {
            balance+= balance*rate;
            return CompoundResult.RATE;
        }
        else
        {
            // 30 dollar fee for low balance
            balance -= 30;
            return CompoundResult.PENALTY;
        }
    }
    
    /**
     * Convert the object to a string with holder name and account type
     * @return string of holder name and Checking
     */
    public @Override String toString()
    {
        return holder + " *Checking*";
    }
}
