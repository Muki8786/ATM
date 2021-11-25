package sdk.transactions;

import sdk.Atm;
import sdk.UI.IDepositSlot;
import sdk.UI.IDepositUI;
import sdk.UI.ILogout;
import sdk.accounts.Account;

import static sdk.GlobalConfigChoice.configChoice;

public class Deposit {
    private final int accountNumber;
    private IDepositSlot depositSlot;
    private IDepositUI depositUI;
    public boolean fundTransferSuccess;
    private ILogout logout;

    public Deposit(IDepositUI depositUI , IDepositSlot depositSlot ,ILogout logout, int accountNumber)
    {
        this.accountNumber = accountNumber;
        this.depositUI = depositUI;
        this.depositSlot = depositSlot;
        fundTransferSuccess = false;
        this.logout = logout;
    }

    public void deposit()
    {
        int hunCount =0,twoHunCount =0,fiveHunCount =0 , twoThousCount =0;
        //DepositUI depositUI = new DepositUI();
        int amount = depositUI.getAmount();
        if(amount == 0 && configChoice != 1)
        {
            logout.logout();
            Atm.restart();
        }
        else if(amount==0)
        {
            Atm.createOptionMenu(accountNumber);
        }
        else {
            hunCount = depositUI.getCount(100);
            twoHunCount = depositUI.getCount(200);
            fiveHunCount = depositUI.getCount(500);
            twoThousCount = depositUI.getCount(2000);
        }


        Account account = GlobalDatabase.accountsDatabase.getAccount(accountNumber);

        if(amount > 0 && depositCheck(amount,hunCount,twoHunCount,fiveHunCount,twoThousCount))
        {
            if(depositSlot.depositCapacityCheck(amount ,hunCount,twoHunCount,fiveHunCount,twoThousCount))
            {
                depositSlot.acceptCash(amount, hunCount,twoHunCount,fiveHunCount,twoThousCount);
                account.credit(amount);
                //depositSlot.allDenominations(hun, twoHun, fiveHun, twoThous);
                depositUI.printSuccess(true);
                fundTransferSuccess = true;
            }
            else
            {
                depositUI.printSuccess(false);
            }
        }
        else
        {
            depositUI.printDenominationCheck();
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

    public boolean depositCheck(int amount , int hun , int twoHun, int fiveHun , int twoThous)
    {
        //System.out.println(amount+ " "+ hun+ " "+ twoHun + " "+ fiveHun + " "+ twoThous);
        int sum = (hun *100) + (twoHun*200) + (fiveHun*500) + (twoThous*2000);
        if(amount == sum)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
