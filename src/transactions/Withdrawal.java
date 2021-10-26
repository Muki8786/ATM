package transactions;

import accounts.Account;
import atm.CashDispenser;

public class Withdrawal implements WithdrawCheck{

    private CashDispenser cashDispenser;
    private Account account;

    public Withdrawal(Account account, CashDispenser cashDispenser)
    {
        this.account = account;
        this.cashDispenser = cashDispenser;
    }

    public boolean withdraw(int amount)
    {
        float balance = account.getBalance();

        if(amount <= balance)
        {
            if (cashDispenser.sufficientATMBalanceCheck(amount)) {

                account.debit(amount);

                return true;
                }
            else
                return false;
        }
        else
        {
            System.out.println("\nSorry! Unable to withdraw! Insufficient balance in your account");
            return false;
        }
    }

    public boolean withdrawCheck()
    {
        float balance = cashDispenser.getBalance();
        if(balance == 0)
        {
            System.out.println("\n\t\tSorry! No cash in ATM");
            return false;
        }
        return true;
    }
}
