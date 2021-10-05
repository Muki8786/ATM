package transactions;

import accounts.Account;

public class ChangePin {

    private Account account;


    public ChangePin(Account account)
    {
        this.account = account;
    }

    public boolean verifyOldPin(int newPin , int oldPin)
    {
        if(newPin == oldPin)
        {
            System.out.println("\nSorry!, New Pin cannot be the same as Old Pin ");
            return false;
        }
        else
        {
            return true;
        }

    }

    public boolean changeUserPin(int newPin)
    {
        int oldPin = account.getPin();
        if(!verifyOldPin(oldPin, newPin))
        {
            return false;
        }
        else{
            account.setPin(newPin);
            return true;
        }
    }
}
