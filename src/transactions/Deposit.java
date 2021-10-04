package transactions;

import accounts.*;
import atm.*;

public class Deposit {
    private Account account;
    private DepositSlot depositSlot;

    public Deposit(Account account, DepositSlot depositSlot)
    {
        this.account = account;
        this.depositSlot = depositSlot;
    }

    public boolean depositCash(int amount ) {

        if(depositSlot.depositCapacityCheck(amount))
        {
            depositSlot.acceptCash(amount);
            account.credit(amount);
            return true;
        }
        else
        {
            System.out.println("Sorry , ATM cannot accept cash at the moment");
            return false;
        }
    }
}
