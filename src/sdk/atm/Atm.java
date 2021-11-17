package sdk.atm;

import main.cashDispenser.CashDispenser;
import main.depositSlot.DepositSlot;
import sdk.accounts.Account;
import sdk.accounts.AccountsDatabase;
import sdk.bank.AdminCashier;
import sdk.bank.AdminLog;
import sdk.transactions.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Atm {

    private boolean userAuthenticated;
    private int currentAccountNumber;
    private String atmName;
    private CashDispenser cashDispenser;
    private DepositSlot depositSlot;
    private AccountsDatabase accountsDatabase;
    private BalanceInquiry balanceInquiry;
    private Withdrawal withdrawal;
    private Deposit deposit;
    private ChangePin changePin;
    private FundTransfer fundTransfer;
    private int configChoice = 1;
    private int depositAmount;
    private int hun;
    private int twoHun;
    private int fiveHun;
    private int twoThous;

    Scanner input = new Scanner(System.in);

    public Atm(String atmName )
    {
        this.atmName = atmName;
        userAuthenticated = false;
        currentAccountNumber = 0;
        cashDispenser = new CashDispenser();
        depositSlot = new DepositSlot();
        accountsDatabase = new AccountsDatabase();
    }

    public void run()
    {
        if(login())
        {
           if(!getAccount(currentAccountNumber).getAdmin())
               createMenu();
           else
               createAdminMenu();
        }
        else
        {
            run();
        }
    }

    public boolean login()
    {
        //System.out.println("\n\t\tWelcome to "+atmName + "ATM\n");
        System.out.println("\n\t\tWelcome to the ATM\n");
        //getConfigChoice();

        int accountNumber = inputAccountNumber();
        if(accountsDatabase.accountCheck(accountNumber))
        {
            int pin = inputPin();
            return authenticate(accountNumber, pin);
        }
        else
        {
            System.out.println("\nNo account found ");
            return false;
        }

    }

    public boolean authenticate(int accountNumber , int pin)
    {
        userAuthenticated = accountsDatabase.authenticateUser(accountNumber,pin);
        if(userAuthenticated)
        {
            currentAccountNumber = accountNumber;
            return true;
        }
        else{
            return false;
        }
    }

    public void createMenu()
    {
        displayMainMenu();
        int choice = getInputMainMenu();
        if(choice == -1 )
        {
            if(configChoice == 1)
            {
                createMenu();
            }
            else
            {
                logout();
            }
        }
        else if(choice == 1) {
            balanceInquiry = new BalanceInquiry(getAccount(currentAccountNumber));
            balanceInquiry.balanceInquiry();
            if(configChoice == 1)
            {
                createMenu();
            }
            else
            {
                logout();
            }
        }

        else if (choice == 2) {
            withdrawal = new Withdrawal(getAccount(currentAccountNumber), cashDispenser);
            if(withdrawal.withdrawCheck()) {
                cashDispenser.denominationsAvailable();
                int amount = getUserAmount();
                if (amount != 0) {
                    if (withdrawal.withdraw(amount)) {
                        System.out.println("\n\t\tWithdrawal successful!\n");
                    }
                }
            }
            if(configChoice == 1)
            {
                createMenu();
            }
            else
            {
                logout();
            }
        }

        else if(choice == 3)
        {
            withdrawal = new Withdrawal(getAccount(currentAccountNumber), cashDispenser);

            if(withdrawal.withdrawCheck()) {
                int amount = displayFastCashMenu();
                if (amount == -1 && configChoice != 1)
                    logout();
                else if (amount > 0) {
                    if (withdrawal.withdraw(amount)) {
                        System.out.println("\n\t\tWithdrawal successful!\n");
                    }
                }
            }
            if(configChoice == 1 )
            {
                createMenu();
            }
            else
            {
                logout();
            }
        }

        else if(choice == 4)
        {
            int hunCount =0,twoHunCount =0,fiveHunCount =0 , twoThousCount =0;
            deposit = new Deposit(getAccount(currentAccountNumber), depositSlot);
            int amount = getUserAmount();
            if(amount == 0 && configChoice != 1)
                logout();
            else {
                hunCount = getCount(100);
                twoHunCount = getCount(200);
                fiveHunCount = getCount(500);
                twoThousCount = getCount(2000);
            }

            if(amount > 0 && initiateDeposit(amount,hunCount,twoHunCount,fiveHunCount,twoThousCount))
            {
                if(deposit.depositCash(amount,hunCount , twoHunCount , fiveHunCount , twoThousCount))
                {
                    System.out.println("\n\t\tDeposit successful!\n");
                }
            }
            if(configChoice == 1)
            {
                createMenu();
            }
            else
            {
                logout();
            }

        }

        else if(choice == 5)
        {
            changePin = new ChangePin(getAccount(currentAccountNumber));
            int newPin = getNewPin();
            if(newPin > 0) {
                if (changePin.changeUserPin(newPin)) {
                    System.out.println("\n\t\tPin change successful");
                }
            }
            if(configChoice == 1)
            {
                createMenu();
            }
            else
            {
                logout();
            }
        }

        else if(choice == 6)
        {

            int inputChoice = 0;
            inputChoice = getInputFundTransferMenu();
            if(inputChoice == -1)
                logout();
            else if(inputChoice == 1)
            {
                int receiverAccountNumber = 0;
                System.out.println("\nPlease provide the Receiver's details");
                System.out.println("\n\t\t!Please press 0 if you want to exit!");
                receiverAccountNumber = inputAccountNumber();
                if(checkReceiver(receiverAccountNumber))
                {
                    fundTransfer = new FundTransfer(getAccount(currentAccountNumber) , getAccount(receiverAccountNumber));
                    deposit = new Deposit(getAccount(currentAccountNumber), depositSlot);
                    int amount = getUserAmount();
                    int hun = getCount(100);
                    int twoHun = getCount(200);
                    int fiveHun = getCount(500);
                    int twoThous = getCount(2000);


                    if(amount !=0 && initiateDeposit(amount,hun,twoHun,fiveHun,twoThous))
                    {
                        if(deposit.depositCash(amount,hun , twoHun , fiveHun , twoThous)) {
                            fundTransfer.transferFund(amount);
                        }
                    }
                }
                else{
                    System.out.println("\nInvalid Account Number");
                }

            }
            else if(inputChoice == 2)
            {
                int receiverAccountNumber = 0;

                System.out.println("\nPlease provide the Receiver's details");
                System.out.println("\n\t\t!Please press 0 if you want to exit!");
                receiverAccountNumber = inputAccountNumber();
                if(checkReceiver(receiverAccountNumber))
                {
                    fundTransfer = new FundTransfer(getAccount(currentAccountNumber) , getAccount(receiverAccountNumber));
                    int amount = getUserAmount();
                    if(amount != 0)
                    {
                        fundTransfer.transferFund(amount);
                    }
                }
                else
                {
                    System.out.println("Invalid account number");
                }
            }
            /*else if(inputChoice != 0)
            {
                System.out.println("Invalid choice");
            }

             */

            if(configChoice == 1)
            {
                createMenu();
            }
            else
            {
                logout();
            }
        }

        else if (choice == 0)
        {
            logout();
        }

    }

    public int displayFastCashMenu() {
        int choice = 0;
        int amount = 0;
        while(true) {
            System.out.println("\nThe choices for Fast Cash are : ");
            System.out.println("\t\tPress 1 --- 500");
            System.out.println("\t\tPress 2 --- 1000");
            System.out.println("\t\tPress 3 --- 5000");
            System.out.println("\t\tPress 4 --- 10000");
            System.out.println("\n\t\t!Please press 0 if you want to exit!");
            System.out.print("\nEnter your choice : ");
            try {
                choice = input.nextInt();

                if (choice == 1) {
                    amount = 500;
                    break;
                } else if (choice == 2) {
                    amount = 1000;
                    break;
                } else if (choice == 3) {
                    amount = 5000;
                    break;
                } else if (choice == 4) {
                    amount = 10000;
                    break;
                } else if(choice == 0) {
                    amount = 0;
                    break;
                }
                System.out.println("\nInvalid Input!");
                if(configChoice != 1)
                break;
                } catch (InputMismatchException inputMismatchException)
                {
                    System.out.println("\nInvalid Input!");

                    input.nextLine();
                    if(configChoice != 1)
                        break;
                }
        }
        if(choice>=0 && choice<=4)
        {
            return amount;
        }
        else
        {
            System.out.println("\nInvalid Input!");
            return -1;
        }


        /*if(choice > 4 || choice < 0)
        {
            System.out.println("Invalid choice!");
            return 0;
        }
        else
            return amount;

         */
    }

    public void logout()
    {
        System.out.println("\n\t\tThank you for using our ATM");
        System.out.println("\t\tPlease sanitize your hands before leaving :) \n\n\n");
        run();
    }

    public void displayMainMenu() {
        System.out.println("\n\t\tHi "+ getAccount(currentAccountNumber).getUsername() + "!");
        System.out.println("\n\t\tPress 1 for BALANCE INQUIRY");
        System.out.println("\t\tPress 2 for WITHDRAWAL");
        System.out.println("\t\tPress 3 for FAST CASH");
        System.out.println("\t\tPress 4 to DEPOSIT");
        System.out.println("\t\tPress 5 to CHANGE PIN");
        System.out.println("\t\tPress 6 to FUND TRANSFER");
        System.out.println("\t\tPress 0 to EXIT");
    }

    public int getInputMainMenu()
    {
        int choice = 0;
        while(true)
        {
            System.out.print("\nEnter your Choice : ");
            try
            {
                choice = input.nextInt();
                break;
            }
            catch (InputMismatchException inputMismatchException)
            {
                System.out.println("\nYour input is mismatched");
                input.nextLine();
                if(configChoice!=1)
                break;
            }
        }
        if(choice>=0 && choice <=6)
            return choice;
        else {
            System.out.println("\nYour input is mismatched");
            return -1;
        }
        /*if(choice > 6 || choice < 0)
        {
            System.out.println("Invalid choice!");
            return -1;
        }
        else
            return choice;

         */
    }

    public int getUserAmount() {
        int userAmount = 0;
        while(true)
        {
            System.out.println("\n\t\t!Please press 0 if you want to exit!");
            System.out.print("\nEnter the amount : ");
            try
            {
                userAmount = input.nextInt();
                if(userAmount<0 && configChoice == 1)
                {
                    System.out.println("Amount cannot be negative");
                    continue;
                }
                else if(userAmount<0)
                {
                    System.out.println("Amount cannot be negative");
                    userAmount =0;
                }
                break;
            }
            catch (InputMismatchException inputMismatchException)
            {
                System.out.println("\nYour input cannot be processed");
                input.nextLine();
                if(configChoice!=1)
                {
                    userAmount = 0;
                    break;
                }
            }
        }
        return userAmount;
    }

    public int balanceAfterTransaction()
    {
        int balanceChoice = 0;
        System.out.println("\n\t\tTransaction successful!\n");
        System.out.println("\nTo see your balance on the screen ");
        while(true) {
            System.out.println("Press 1 to check your balance ");
            System.out.println("Press any other number key to Exit");
            System.out.print("\nEnter your choice : ");
            try {
                balanceChoice = input.nextInt();
                if (balanceChoice != 1) {
                    balanceChoice = 0;
                }
                break;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\n\t\tPress a number key");
                input.nextLine();
            }
        }
        return balanceChoice;

    }

    /*public static Atm getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Atm();
        }
        return uniqueInstance;
    }

     */

    public int inputAccountNumber()
    {
        int userAccountNumber = 0;
        while(true){
            System.out.print("Enter the Account number : ");
            try {
                userAccountNumber = input.nextInt();
                break;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\n\nThe Account number you have entered does not match");
                System.out.println("\nTry again");
                input.nextLine();
            }

        }
        return userAccountNumber;
    }

    public int  inputPin()
    {
        int userPin = 0;
        while(true){
            System.out.print("Enter your Pin : ");
            try {
                userPin = input.nextInt();
                break;
            }
            catch (InputMismatchException inputMismatchException) {
                System.out.println("\n\nThe Pin you have entered does not match");
                System.out.println("\nTry again");
                input.nextLine();
                if(configChoice!=1)
                {
                    userPin = -1;
                    break;
                }
            }
        }
        return userPin;
    }

    public int getNewPin()
    {
        int newPin =0;
        while(true)
        {
            System.out.println("\n\t\t!Please press 0 if you want to exit!");
            System.out.print("Enter the new Pin : ");
            try
            {
                newPin = input.nextInt();
                if(newPin < 0 && configChoice == 1)
                {
                    System.out.println("Pin cannot be negative");
                    continue;
                }
                else if(newPin < 0)
                {
                    System.out.println("Pin cannot be negative");
                    newPin = 0;
                }
                break;
            } catch (InputMismatchException inputMismatchException)
            {
                System.out.println("\n\nSorry! , The Pin you have entered cannot be set ");
                input.nextLine();
                if(configChoice !=1)
                {
                    newPin = 0;
                    break;
                }
            }
        }
        return newPin;
    }

    public boolean checkReceiver(int receiverAccountNumber)
    {
        return accountsDatabase.accountCheck(receiverAccountNumber);
    }

    public int getInputFundTransferMenu()
    {
        int choice = 0;
        while(true)
        {
            System.out.println("\n\t\tPress 1 --- Through Deposit");
            System.out.println("\t\tPress 2 --- Through Account balance");
            System.out.println("\t\tPress 0 --- Exit");
            System.out.print("\nEnter your choice : ");
            try{
                choice = input.nextInt();
                if(choice == 1 || choice == 2 || choice == 0)
                {
                    break;
                }
                if(configChoice == 1)
                {
                    System.out.println("Invalid choice!");
                    continue;
                }
                choice = 0;
                System.out.println("Invalid choice!");
                break;

            } catch (InputMismatchException inputMismatchException)
            {
                System.out.println("Invalid choice!");
                input.nextLine();
                if(configChoice!=1)
                {
                    choice= 0;
                    break;
                }
            }
        }
        return choice;


    }


    public int exitOrContinue()
    {
        int choice = 0;

        while(true)
        {
            System.out.println("\nPress 1 --- Continue to the menu");
            System.out.println("Press any other number key to exit");
            System.out.print("\nEnter your choice : ");
            try {
                choice = input.nextInt();
                break;
            } catch (InputMismatchException inputMismatchException)
            {
                System.out.println("\nEnter a number!");
                input.nextLine();
            }
        }
        return choice;
    }

    public Account getAccount(int currentAccountNumber)
    {
        return accountsDatabase.getAccount(currentAccountNumber);
    }

    public void createAdminMenu()
    {
        displayAdminMenu();
        int choice = getAdminMenuInput();

        if(choice == 1)
        {
            int hunCount =0,twoHunCount=0,fiveHunCount=0,twoThousCount=0,amount=0;


            int depositChoice = getDepositChoice();
            if(depositChoice == 0)
                logout();
            else if(depositChoice == 1) {
                depositAmount = cashDispenser.getAmount();
                hun = cashDispenser.denominationCountNeeded(100);
                twoHun = cashDispenser.denominationCountNeeded(200);
                fiveHun = cashDispenser.denominationCountNeeded(500);
                twoThous = cashDispenser.denominationCountNeeded(2000);
            }


            if (depositAmount != 0) {
                    if (AdminCashier.balanceCheck(depositAmount) && cashDispenser.denominationsCheck(100,hun)
                    && cashDispenser.denominationsCheck(200 , twoHun) && cashDispenser.denominationsCheck(500 , fiveHun)
                    && cashDispenser.denominationsCheck(2000,twoThous)) {
                        AdminCashier.debitBankBalance(amount);
                        System.out.println("\nPlease insert the cash in the dispenser");
                        if(depositChoice == 1)
                            cashDispenser.insertIntoDispenser(depositAmount, 100, 100, 100, 100);
                        else
                            cashDispenser.insertIntoDispenser(depositAmount, hun, twoHun, fiveHun, twoThous);
                        AdminLog adminLog = new AdminLog(getAccount(currentAccountNumber).getUsername(), atmName, "Filling", depositAmount);
                        AdminCashier.addAdminLog(adminLog);
                        System.out.println("\n\t\tCash dispenser is filled");

                    }
                    AdminCashier.printAdminLog();
                }
        }
        else if(choice == 2)
        {
            int amount = depositSlot.getAmount();
            depositAmount = amount;
            setCount();
            if(amount != 0) {
                System.out.println("\nPlease take the cash");
                depositSlot.withdrawFromDepositSlot();
                AdminCashier.creditBankBalance(amount);
                AdminLog adminLog = new AdminLog(getAccount(currentAccountNumber).getUsername() ,atmName,"Emptying" , amount);
                AdminCashier.addAdminLog(adminLog);
            }
            System.out.println("\n\t\tDeposit slot is empty");
            AdminCashier.printAdminLog();
        }
        if(choice != 0 && configChoice == 1)
        {
            createAdminMenu();
        }
        else {
            logout();
        }

    }

    public void displayAdminMenu()
    {
        System.out.println("\n\t\tHi "+ getAccount(currentAccountNumber).getUsername() + "!");
        System.out.println("\t\tPress 1 to fill cash into Dispenser");
        System.out.println("\t\tPress 2 to empty cash from Deposit Slot");
        System.out.println("\t\tPress 0 to Exit");
    }

    public int getAdminMenuInput()
    {
        int choice = 0;
        while(true)
        {
            System.out.print("\nEnter your Choice : ");
            try
            {
                choice = input.nextInt();
                break;
            }
            catch (InputMismatchException inputMismatchException)
            {
                System.out.println("\nYour input is mismatched");
                input.nextLine();
                if(configChoice!=1)
                    break;
            }
        }
        if(choice>=0 && choice <=2)
            return choice;
        else {
            System.out.println("\nYour input is mismatched");
            return -1;
        }

    }

   /* public void getConfigChoice()
    {
        int choice =0;
        System.out.println("Press 1 to redirect to main menu after a Transaction");
        System.out.println("Press any other key to exit immediately after a Transaction");
        while(true)
        {
            System.out.print("\nEnter your Choice : ");
            try
            {
                choice = input.nextInt();
                break;
            }
            catch (InputMismatchException inputMismatchException)
            {
                input.nextLine();
                choice = 0;
                break;
            }
        }
        if(choice == 1)
        {
           configChoice = choice;
        }
        else
        {
            configChoice = 0;
        }
    }
    */

    public int getCount(int key)
    {
        int count = 0;

        while (true)
        {
            System.out.println("\nEnter the count of Rs."+key+" notes : ");
            try
            {
                count = input.nextInt();
                if(count >= 0 && configChoice ==1)
                break;

            }
            catch (InputMismatchException inputMismatchException)
            {
                System.out.println("\nInvalid input");
                input.nextLine();

                if(configChoice!=1)
                {
                    count = 0;
                    break;
                }
            }

        }
        return count;
    }

    public boolean initiateDeposit(int amount , int hun , int twoHun, int fiveHun , int twoThous)
    {
        System.out.println(amount+ " "+ hun+ " "+ twoHun + " "+ fiveHun + " "+ twoThous);
        int sum = (hun *100) + (twoHun*200) + (fiveHun*500) + (twoThous*2000);
        if(amount == sum)
        {
            return true;
        }
        else
        {
            System.out.println("\n\t\tCheck the denominations with the amount");
            return false;
        }
    }

    public void setCount()
    {
        hun = depositSlot.getDenominationCount(100);
        twoHun = depositSlot.getDenominationCount(200);
        fiveHun = depositSlot.getDenominationCount(500);
        twoThous = depositSlot.getDenominationCount(2000);
    }

    public int getDepositChoice()
    {
        int choice =0;
        while(true)
        {
            System.out.println("\nPress 1 to completely fill the cash dispenser");
            System.out.println("Press 2 to fill the amount taken from deposit slot");
            System.out.println("Press 0 to exit");
            try {
                choice = input.nextInt();
                if(choice == 1 || choice == 2 || choice == 0)
                {
                    break;
                }
                else if(configChoice!=1)
                {
                    choice = 0;
                    System.out.println("Invalid choice");
                    break;
                }
            }
            catch (InputMismatchException inputMismatchException)
            {
                System.out.println("Invalid choice");
                input.nextLine();
                if(configChoice !=1)
                {
                    choice = 0;
                    break;
                }

            }
        }
        return choice;
    }
}