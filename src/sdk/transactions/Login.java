package sdk.transactions;

import main.transactions.LoginUI;
import sdk.accounts.AccountsDatabase;

public class Login extends GlobalDatabase{
    private AccountsDatabase accountsDatabase ;
    private int accountNumber;

    public Login()
    {
        this.accountsDatabase = GlobalDatabase.accountsDatabase;
    }

    public boolean loginCheck(int accountNumber , int pin)
    {
        if(accountsDatabase.accountCheck(accountNumber))
        {
            return accountsDatabase.authenticateUser(accountNumber, pin);
        }
        return false;
    }

    public boolean login()
    {
        LoginUI loginUI = new LoginUI();
        accountNumber = loginUI.inputAccountNumber();
        int pin = loginUI.inputPin();
        if(loginCheck(accountNumber,pin))
        {
            return true;
        }
        else {
            return false;
        }
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

}
