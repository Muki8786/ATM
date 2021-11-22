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
            String name = accountsDatabase.getAccount(accountNumber).getUsername();
            loginUI.login(true,name);
            return true;
        }
        else {
            loginUI.login(false,"Empty");
            return false;
        }
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

    public boolean checkAdmin()
    {
        return accountsDatabase.getAccount(accountNumber).getAdmin();
    }
}
