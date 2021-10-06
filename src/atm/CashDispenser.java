package atm;

public class CashDispenser {
    private static final int initialBalance = 100000;
    private int balance;

    public CashDispenser()
    {
        balance = initialBalance;
    }

    public void dispenseCash(int amount)
    {
        balance -= amount;
        System.out.println("Please collect the cash");
    }

    public boolean sufficientATMBalanceCheck(int amount)
    {
        if(amount > balance)
        {
            return false;
        }
        else
            return true;
    }

    public int getAmount()
    {
        int amount = initialBalance-balance;
        System.out.println("\nThe amount needed to be full is Rs."+ amount);
        return amount;
    }

    public void insertIntoDispenser(int amount)
    {
        balance += amount;
    }


}
