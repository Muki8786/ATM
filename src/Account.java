import java.util.ArrayList;
import java.util.List;

public class Account {

    private String username;
    private int accountNumber;
    private int pin;
    private String  mobileNumber;
    private String address;
    private float balance;
    private int admin;
    private List<String> transactionHistories;
    private boolean isTrueOrFalse ;



    public Account(String Username, int accountNumber, String mobileNumber , String address , int pin , float balance, int admin)
    {
        setUsername(Username);
        setAccountNumber(accountNumber);
        setPin(pin);
        setBalance(balance);
        setAdmin(admin);
        setMobileNumber(mobileNumber);
        setAddress(address);
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public int getPin()
    {
        return pin;
    }

    public void setPin(int pin)
    {
        this.pin = pin;
    }

    public void setBalance(float balance)
    {
        this.balance = balance;
    }

    public float getBalance()
    {
        return balance;
    }

    public int getAdmin()
    {
        return admin;
    }

    public void setAdmin(int admin)
    {
        this.admin = admin;
    }



    public boolean validatePIN(int userPIN)
    {
        if (userPIN == getPin())
            return true;
        else
            return false;
    }


    public float checkBalance()
    {
        return balance;
    }

    public void credit(float amount)
    {
        setBalance(getBalance() + amount);
    }

    public void debit(float amount)
    {
        setBalance(getBalance() - amount);
    }

    public void setTransactionHistories(String transactionDetails)
    {
        if(transactionHistories.size() == 0)
            transactionHistories = new ArrayList<>();

        transactionHistories.add(transactionDetails);
    }

    public List<String> getTransactionHistories()
    {
        return transactionHistories;
    }


}
