import java.util.ArrayList;
import java.util.List;

public class AccountsDatabase {
    static ArrayList<Account> accounts = new ArrayList<Account>() ;

    public AccountsDatabase()
    {
        Account accounts1 = new Account("Nantha" ,98712  ,"9999999999", "159/10,Jayam Nagar,Theni,Tamil Nadu", 12345,500.0f,0 );
        Account accounts2 = new Account("Aslam" ,95433  ,"9999955555", "89,Big Bazaar Street,Tiruppur,Tamil Nadu", 12121,500.0f,0);
        Account accounts3 = new Account("Ramesh" ,98123  ,"9999999333", "1,Anna Nagar,Chennai,Tamil Nadu", 11111,500.0f,0);
        Account accounts4 = new Account("Mukesh" ,99999  ,"9993332221", "2/292,Somanur,Tiruppur , Tamil Nadu", 45453,0.0f,1);
        accounts.add(accounts1);
        accounts.add(accounts2);
        accounts.add(accounts3);
        accounts.add(accounts4);
    }

    public Account getAccount(int accountNumber)
    {
        for (Account currentAccount : accounts)
        {
            if (currentAccount.getAccountNumber() == accountNumber)
                return currentAccount;
        }

        return null;
    }

    public boolean authenticateUser(int accountNumber , int userPIN)
    {

        Account userAccount = getAccount(accountNumber);


        if (userAccount != null)
            return userAccount.validatePIN(userPIN);
        else
            return false; // account number not found, so return false
    }


    public float getBalance(int userAccountNumber)
    {
        return getAccount(userAccountNumber).getBalance();
    }

    public void credit(int userAccountNumber, float amount)
    {
        getAccount(userAccountNumber).credit(amount);
    }

    public void debit(int userAccountNumber, float amount)
    {
        getAccount(userAccountNumber).debit(amount);
    }

    public int getAdmin(int userAccountNumber)
    {
        return getAccount(userAccountNumber).getAdmin();
    }

    public void addTransaction(int userAccountNumber , String transactionDetails)
    {
        getAccount(userAccountNumber).setTransactionHistories(transactionDetails);
    }

    public boolean accountCheck(int accountNumber)
    {
        for(Account currentAccount : accounts)
        {
            if (currentAccount.getAccountNumber() == accountNumber)
                return true;
        }
        return false;

    }

    public List<String> getTransactions(int userAccountNumber)
    {
        return getAccount(userAccountNumber).getTransactionHistories();
    }


}
