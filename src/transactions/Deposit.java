package transactions;

import accounts.Account;
import atm.DepositSlot;


public class Deposit {
    private Account account;
    private DepositSlot depositSlot;

    public Deposit(Account account, DepositSlot depositSlot)
    {
        this.account = account;
        this.depositSlot = depositSlot;
    }

    public boolean depositCash(int amount , int hun , int twoHun , int fiveHun ,int twoThous )
    {

        if(depositSlot.depositCapacityCheck(amount ,hun,twoHun,fiveHun,twoThous))
        {
            account.credit(amount);
            depositSlot.allDenominations(hun, twoHun, fiveHun, twoThous);
            return true;
        }
        else
        {
            return false;
        }
    }
}
