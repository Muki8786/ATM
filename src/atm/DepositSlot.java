package atm;

public class DepositSlot {
    private int depositBalance;
    private static final int depositCapacity = 100000;

    public DepositSlot()
    {
        depositBalance = 0;
    }

    public void acceptCash(int amount)
    {
        depositBalance += amount;
    }

    public boolean depositCapacityCheck(int amount)
    {
        if(amount + depositBalance > depositCapacity)
            return false;
        else
            return true;
    }

    public void withdrawFromDepositSlot()
    {
        depositBalance = 0;
    }

    public int getAmount()
    {
        int amount = depositBalance ;
        System.out.println("\nThe amount to be taken is Rs."+ amount);
        return amount;
    }

}
