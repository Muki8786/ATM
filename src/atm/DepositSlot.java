package atm;

public class DepositSlot {
    private static int depositBalance;
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

    public static void emptyDepositSlot()
    {
        depositBalance = 0;
        System.out.println("\nDeposit slot is empty now");

    }


}
