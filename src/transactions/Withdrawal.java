package transactions;

import accounts.*;
import atm.*;

public class Withdrawal {

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

                cashDispenser.dispenseCash(amount);

                return true;
                }
            else
            {
                System.out.println("\nSorry! Unable to withdraw! Insufficient funds at the atm!");
                return false;
            }
        }
        else
        {
            System.out.println("\nSorry! Unable to withdraw! Insufficient balance in your account");
            return false;
        }
    }

}
