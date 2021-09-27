public class Withdrawal extends Transaction{
    private int amount;
    private CashDispenser cashDispenser;

    public Withdrawal(int accountNumber , AccountsDatabase accountsDatabase ,CashDispenser cashDispenser)
    {
        super(accountNumber,accountsDatabase);
        this.cashDispenser = cashDispenser;
    }

    public boolean withdraw(int amount)
    {
        AccountsDatabase accountsDatabase = getAccountsDatabase();
        float balance = accountsDatabase.getBalance(getAccountNumber());

        if(amount <= balance)
        {
            if (cashDispenser.sufficientATMBalanceCheck(amount)) {

                accountsDatabase.debit(getAccountNumber(), amount);

                cashDispenser.dispenseCash(amount);

                return true;
                }
            else
            {
                System.out.println("\nUnable to withdraw! Insufficient funds at the atm!");
                return false;
            }
        }
        else
        {
            System.out.println("\nUnable to withdraw! Insufficient balance in your account");
            return false;
        }
    }

}
