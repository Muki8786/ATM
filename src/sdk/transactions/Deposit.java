package sdk.transactions;

import main.transactions.DepositMain;
import sdk.Atm;
import sdk.accounts.Account;
import main.depositSlot.DepositSlot;

import static main.global.GlobalConfigChoice.configChoice;
import static main.global.Logout.logout;


public class Deposit {
    private int accountNumber;
    private DepositSlot depositSlot;
    public boolean fundTransferSuccess;

    public Deposit(int accountNumber)
    {
        this.accountNumber = accountNumber;
        this.depositSlot = DepositSlot.getInstance();
        fundTransferSuccess = false;
    }

    public void deposit()
    {
        int hunCount =0,twoHunCount =0,fiveHunCount =0 , twoThousCount =0;
        DepositMain depositMain = new DepositMain();
        int amount = depositMain.getAmount();
        if(amount == 0 && configChoice != 1)
        {
            logout();
        }
        else {
            hunCount = depositMain.getCount(100);
            twoHunCount = depositMain.getCount(200);
            fiveHunCount = depositMain.getCount(500);
            twoThousCount = depositMain.getCount(2000);
        }


        Account account = GlobalDatabase.accountsDatabase.getAccount(accountNumber);

        if(amount > 0 && depositCheck(amount,hunCount,twoHunCount,fiveHunCount,twoThousCount))
        {
            if(depositSlot.depositCapacityCheck(amount ,hunCount,twoHunCount,fiveHunCount,twoThousCount))
            {
                depositSlot.acceptCash(amount, hunCount,twoHunCount,fiveHunCount,twoThousCount);
                account.credit(amount);
                //depositSlot.allDenominations(hun, twoHun, fiveHun, twoThous);
                depositMain.printSuccess(true);
                fundTransferSuccess = true;
            }
            else
            {
                depositMain.printSuccess(false);
            }
        }
        else
        {
            depositMain.printDenominationCheck();
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
