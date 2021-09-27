public class ChangePin extends Transaction{

    public ChangePin(int accountNumber , AccountsDatabase accountsDatabase)
    {
        super(accountNumber , accountsDatabase);
    }

    public boolean verifyOldPin(int newPin , int oldPin)
    {
        if(newPin == oldPin)
        {
            System.out.println("\nNew Pin cannot be the same as Old Pin ");
            return false;
        }
        else
        {
            return true;
        }

    }

    public boolean changeUserPin(int newPin)
    {
        AccountsDatabase accountsDatabase = getAccountsDatabase();
        int oldPin = accountsDatabase.getAccount(getAccountNumber()).getPin();
        if(verifyOldPin(oldPin , newPin) == false)
        {
            return false;
        }
        else{
            accountsDatabase.getAccount(getAccountNumber()).setPin(newPin);
            return true;
        }
    }
}
