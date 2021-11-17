package sdk.transactions;

import main.transactions.ChangePinMain;
import sdk.Atm;
import sdk.accounts.Account;

import static main.global.GlobalConfigChoice.configChoice;
import static main.global.Logout.logout;

public class ChangePin{

    private final int accountNumber;


    public ChangePin(int accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    private boolean verifyOldPin(int newPin, int oldPin)
    {
        if(newPin == oldPin)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public void changeUserPin()
    {
        ChangePinMain changePinMain = new ChangePinMain();
        Account account = GlobalDatabase.accountsDatabase.getAccount(accountNumber);
        int oldPin = account.getPin();
        int newPin = changePinMain.getNewPin();


        if(verifyOldPin(oldPin, newPin))
        {
            account.setPin(newPin);
            changePinMain.printSuccess(true);
        }
        else{
            changePinMain.printSuccess(false);
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
}
