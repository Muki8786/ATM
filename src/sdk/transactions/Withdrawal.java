package sdk.transactions;

import main.transactions.WithdrawMain;
import sdk.Atm;
import sdk.accounts.Account;
import main.cashDispenser.CashDispenser;

import static main.global.GlobalConfigChoice.configChoice;
import static main.global.Logout.logout;

public class Withdrawal implements WithdrawCheck {

    private CashDispenser cashDispenser;
    private int accountNumber;
    private int amount;

    public Withdrawal(int accountNumber)
    {
        this.accountNumber = accountNumber;
        this.cashDispenser = CashDispenser.getInstance();
        this.amount = 0;
    }

    public Withdrawal(int accountNumber , int amount)
    {
        this.accountNumber = accountNumber;
        this.cashDispenser = CashDispenser.getInstance();
        this.amount = amount;
    }

    public void withdraw()
    {
        WithdrawMain withdrawMain = new WithdrawMain();

        if(amount == 0)
        amount = withdrawMain.getAmount();

        if(amount!=0)
        {
            if (withdrawCheck())
            {
                Account account = GlobalDatabase.accountsDatabase.getAccount(accountNumber);
                float balance = account.getBalance();
                if(amount <= balance)
                {
                    if (cashDispenser.sufficientATMBalanceCheck(amount))
                    {
                        //cashDispenser.denominationsAvailable();
                        cashDispenser.withdrawWithCount(amount);
                        account.debit(amount);
                        withdrawMain.printSuccess(1);
                    }
                    else
                    {
                        withdrawMain.printSuccess(-1);
                    }
                }
                else
                {
                    withdrawMain.printSuccess(0);
                }
            }
            else {
                withdrawMain.printNoCashInATM();
            }
        }

        if(configChoice == 1)
        {
            Atm.createOptionMenuUI(accountNumber);
        }
        else
        {
            logout();
        }

    }


    public boolean withdrawCheck()
    {
        float balance = cashDispenser.getBalance();
        if(balance == 0)
        {
            return false;
        }
        return true;
    }
}
