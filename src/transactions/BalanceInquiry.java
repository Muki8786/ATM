package transactions;

import accounts.*;

public class BalanceInquiry {

    private Account account;

    public BalanceInquiry(Account account)
    {
        this.account = account;
    }


    public void balanceInquiry() {
       float balance = account.getBalance();

        System.out.println("\nYour balance is : Rs." + balance);
    }
}
