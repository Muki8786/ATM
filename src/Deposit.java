public class Deposit extends Transaction{
    private int amount;
    private DepositSlot depositSlot;
    public Deposit(int accountNumber, AccountsDatabase accountsDatabase , DepositSlot depositSlot)
    {
        super(accountNumber , accountsDatabase);
        this.depositSlot = depositSlot;
    }

    public boolean depositCash(int amount ) {
        AccountsDatabase accountsDatabase = getAccountsDatabase();

        if(depositSlot.depositCapacityCheck(amount))
        {
            depositSlot.acceptCash(amount);
            accountsDatabase.credit(getAccountNumber() , amount);
            System.out.println("\nDeposit successful");
            return true;
        }
        else
        {
            System.out.println("Sorry , ATM cannot accept cash at the moment");
            return false;
        }
    }
}
