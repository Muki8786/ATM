package accounts;

public class Account {

    private String username;
    private int accountNumber;
    private int pin;
    private String  mobileNumber;
    private String address;
    private float balance;
    private int admin;


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

    private void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    private void setAddress(String address)
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

    private void setUsername(String username)
    {
        this.username = username;
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

    private void setAccountNumber(int accountNumber)
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

    private void setBalance(float balance)
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

    private void setAdmin(int admin)
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

    public void credit(float amount)
    {
        setBalance(getBalance() + amount);
    }

    public void debit(float amount)
    {
        setBalance(getBalance() - amount);
    }

}
