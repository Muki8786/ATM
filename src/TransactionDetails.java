public class TransactionDetails {
    private int accountNumber;
    private String type;
    private float amount;
    private float balance;

    public TransactionDetails(int accountNumber , String type, float amount , float balance)
    {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "\nTransactionDetails - " +
                "accountNumber=" + accountNumber +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", balance=" + balance ;
    }

}
