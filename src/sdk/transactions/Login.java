package sdk.transactions;



import sdk.UI.ILoginUI;
import sdk.accounts.AccountsDatabase;

public class Login  {
    private AccountsDatabase accountsDatabase ;
    private int accountNumber;
    private ILoginUI loginUI;

    public Login(ILoginUI loginUI)
    {
        this.loginUI = loginUI;
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
