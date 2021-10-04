package transactions;

import accounts.*;

public class FundTransfer {
    private Account senderAccount;
    private Account receiverAccount;

    public FundTransfer(Account senderAccount , Account receiverAccount)
    {
        this.receiverAccount = receiverAccount;
        this.senderAccount = senderAccount;
    }

    public void transferFund(int amount)
    {
        float senderAccountBalance = senderAccount.getBalance();

        if(amount <= senderAccountBalance)
        {
            senderAccount.debit(amount);
            receiverAccount.credit(amount);
            System.out.println("\n\t\tFund Transfer Successful");
        }
        else
        {
            System.out.println("\n\t\tInsufficient balance");
        }
    }
}
