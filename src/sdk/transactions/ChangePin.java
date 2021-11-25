package sdk.transactions;

import sdk.Atm;
import sdk.UI.IChangePinUI;
import sdk.UI.ILogout;
import sdk.accounts.Account;


import static sdk.GlobalConfigChoice.configChoice;


public class ChangePin {


    private final int accountNumber;
    private IChangePinUI changePinUI;
    private ILogout logout;

    public ChangePin(IChangePinUI changePinUI,ILogout logout ,int accountNumber)
    {
        this.changePinUI = changePinUI;
        this.accountNumber = accountNumber;
        this.logout = logout;
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

        Account account = GlobalDatabase.accountsDatabase.getAccount(accountNumber);
        int oldPin = account.getPin();
        int newPin = changePinUI.getNewPin();


        if(verifyOldPin(oldPin, newPin))
        {
            account.setPin(newPin);
            changePinUI.printSuccess(true);
        }
        else{
            changePinUI.printSuccess(false);
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
}
