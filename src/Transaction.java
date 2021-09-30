public  class Transaction {

    private int accountNumber;
    private AccountsDatabase accountsDatabase;

    public Transaction(int accountNumber, AccountsDatabase accountsDatabase) {
        this.accountNumber = accountNumber;
        this.accountsDatabase = accountsDatabase;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public AccountsDatabase getAccountsDatabase() {
        return accountsDatabase;
    }

}
