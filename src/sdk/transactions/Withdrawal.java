package sdk.transactions;

import sdk.Atm;

import sdk.UI.ICashDispenser;
import sdk.UI.ILogout;
import sdk.UI.IWithdrawUI;
import sdk.accounts.Account;

import static sdk.GlobalConfigChoice.configChoice;


public class Withdrawal {

    private IWithdrawUI withdrawUI;
    private ICashDispenser cashDispenser;
    private int accountNumber;
    private int amount;
    private ILogout logout;

    public Withdrawal(IWithdrawUI withdrawUI , ICashDispenser cashDispenser ,ILogout logout,int accountNumber)
    {
        this.withdrawUI = withdrawUI;
        this.accountNumber = accountNumber;
        this.cashDispenser = cashDispenser;
        this.amount = 0;
        this.logout = logout;
    }

    public Withdrawal(IWithdrawUI withdrawUI , ICashDispenser cashDispenser ,int accountNumber , int amount)
    {
        this.accountNumber = accountNumber;
        this.cashDispenser = cashDispenser;
        this.withdrawUI = withdrawUI;
        this.amount = amount;
    }

    public void withdraw()
    {


        if(amount == 0)
        amount = withdrawUI.getAmount();

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
                        withdrawUI.printSuccess(1);
                    }
                    else
                    {
                        withdrawUI.printSuccess(-1);
                    }
                }
                else
                {
                    withdrawUI.printSuccess(0);
                }
            }
            else {
                withdrawUI.printNoCashInATM();
            }
        }

        if(configChoice == 1)
        {
            Atm.createOptionMenu(accountNumber);
        }
        else
        {
            logout.logout();
            Atm.restart();
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
