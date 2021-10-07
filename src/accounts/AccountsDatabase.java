package accounts;

import java.util.ArrayList;

public class AccountsDatabase {
    private static ArrayList<Account> accounts = new ArrayList<Account>() ;

    public AccountsDatabase()
    {
        Account accounts1 = new Account("Nantha" ,98712  ,"9999999999", "159/10,Jayam Nagar,Theni,Tamil Nadu", 12345,300000.0f,false );
        Account accounts2 = new Account("Aslam" ,95433  ,"9999955555", "89,Big Bazaar Street,Tiruppur,Tamil Nadu", 12121,500.0f,false);
        Account accounts3 = new Account("Ramesh" ,98123  ,"9999999333", "1,Anna Nagar,Chennai,Tamil Nadu", 11111,500.0f,false);
        Account accounts4 = new Account("Mukesh" ,99999  ,"9993332221", "2/292,Somanur,Tiruppur , Tamil Nadu", 44444,0.0f,true);
        Account accounts5 = new Account("Ismail",55555,"9876987698","Chennai", 7777 , 900000.0f , false);
        Account accounts6 = new Account("Balaji",11111 , "9629484484" , "Tiruppur" , 1111 , 0.0f , true);
        accounts.add(accounts1);
        accounts.add(accounts2);
        accounts.add(accounts3);
        accounts.add(accounts4);
        accounts.add(accounts5);
        accounts.add(accounts6);
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
        {
            return false;
        }

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

}
