package transactions;

import accounts.*;

public class BalanceInquiry {

    private Account account;

    public BalanceInquiry(Account account)
    {
        this.account = account;
    }

    public void balanceInquiry() {
        System.out.println("\nYour balance is : Rs." + account.getBalance());
    }
}
