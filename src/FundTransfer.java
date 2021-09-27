public class FundTransfer extends Transaction{
    private AccountsDatabase accountsDatabase;

    public FundTransfer(int accountNumber , AccountsDatabase accountsDatabase )
    {
        super(accountNumber , accountsDatabase);
    }

    public boolean checkReceiver(int receiverAccountNumber)
    {
        if(getAccountsDatabase().accountCheck(receiverAccountNumber))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void transferFund(int receiverAccountNumber, int amount)
    {
        accountsDatabase = getAccountsDatabase();
        float senderAccountBalance = accountsDatabase.getAccount(getAccountNumber()).checkBalance();

        if(amount <= senderAccountBalance)
        {
            accountsDatabase.debit(getAccountNumber() , amount);
            accountsDatabase.credit(receiverAccountNumber , amount);
            System.out.println("\n\t\tFund Transfer Successful");
        }
        else
        {
            System.out.println("\n\t\tInsufficient balance");
        }

    }
}
