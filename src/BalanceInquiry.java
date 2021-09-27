public class BalanceInquiry extends Transaction{


    public BalanceInquiry(int accountNumber , AccountsDatabase accountsDatabase )
    {
        super(accountNumber,accountsDatabase);
    }


    public void balanceInquiry() {
        AccountsDatabase accountsDatabase = getAccountsDatabase();

        float balance = accountsDatabase.getBalance(getAccountNumber());

        System.out.println("\nYour balance is : Rs." + balance);

    }
}
