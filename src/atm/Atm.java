package atm;

import accounts.*;
import transactions.*;
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
    private int configChoice;

    Scanner input = new Scanner(System.in);

    public Atm(String atmName ,CashDispenser cashDispenser , DepositSlot depositSlot )
    {
        this.atmName = atmName;
        userAuthenticated = false;
        currentAccountNumber = 0;
        this.cashDispenser = cashDispenser;
        this.depositSlot = depositSlot;
        accountsDatabase = new AccountsDatabase();
    }

    public void run()
    {

        if(login())
        {
           if(getAccount(currentAccountNumber).getAdmin() == 0)
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
        System.out.println("\t\tWelcome to "+atmName + "ATM\n");
        getConfigChoice();
       return authenticate(inputAccountNumber() , inputPin());
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
        if(choice == -1)
            createMenu();
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

            int amount = getUserAmount();
            if(amount != 0)
            {
                if (withdrawal.withdraw(amount)) {
                    System.out.println("\n\t\tWithdrawal successful!\n");
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

            int amount = displayFastCashMenu();
            if(amount > 0)
            {
                if(withdrawal.withdraw(amount))
                {
                    System.out.println("\n\t\tWithdrawal successful!\n");
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
            deposit = new Deposit(getAccount(currentAccountNumber), depositSlot);
            int amount = getUserAmount();
            if(amount > 0)
            {
                if(deposit.depositCash(amount))
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
            if(inputChoice == 1)
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
                    if(amount !=0)
                    {
                        if(deposit.depositCash(amount)) {
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
            else if(inputChoice != 0)
            {
                System.out.println("Invalid choice");
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
                } else if (choice == 2) {
                    amount = 1000;
                } else if (choice == 3) {
                    amount = 5000;
                } else if (choice == 4) {
                    amount = 10000;
                } else if(choice == 0) {
                    amount = 0;
                }
                break;
                } catch (InputMismatchException inputMismatchException)
                {
                    System.out.println("Invalid Input!");
                    System.out.println("Press 0 to exit\n\n");
                    input.nextLine();
                }
        }
        if(choice > 4 || choice < 0)
        {
            System.out.println("Invalid choice!");
            return 0;
        }
        else
            return amount;
    }

    public void logout()
    {
        System.out.println("\n\t\tThank you for using "+ atmName+" ATM");
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
                System.out.println("Try again!");
                input.nextLine();
            }
        }
        if(choice > 6 || choice < 0)
        {
            System.out.println("Invalid choice!");
            return -1;
        }
        else
            return choice;
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
                if(userAmount<0)
                {
                    System.out.println("Amount cannot be negative");
                    continue;
                }
                break;
            }
            catch (InputMismatchException inputMismatchException)
            {
                System.out.println("Your input cannot be processed");
                input.nextLine();
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
                if(newPin < 0)
                {
                    System.out.println("Pin cannot be negative");
                    continue;
                }
                break;
            } catch (InputMismatchException inputMismatchException)
            {
                System.out.println("\n\nSorry! , The Pin you have entered cannot be set ");
                System.out.println("\nTry again");
                input.nextLine();
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
                break;
            } catch (InputMismatchException inputMismatchException)
            {
                System.out.println("Invalid choice!");
                input.nextLine();
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
            System.out.println("\nPlease insert the cash in the dispenser");
            cashDispenser.insertIntoDispenser();
            System.out.println("\n\t\tCash dispenser is filled");
        }
        else if(choice == 2)
        {
            System.out.println("\nPlease take the cash");
            depositSlot.withdrawFromDepositSlot();
            System.out.println("\n\t\tDeposit slot is empty");
        }
        else if(choice == 0)
        {
            logout();
        }
        if(exitOrContinue() == 1)
        {
            createAdminMenu();
        }
        else
        {
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
            System.out.print("\n\t\tEnter your choice : ");
           try {
               choice = input.nextInt();
               if(choice == 0 || choice == 1 || choice == 2)
               break;
               else
               {
                   System.out.println("\nInvalid choice");
                   displayAdminMenu();
               }
           }
           catch (InputMismatchException inputMismatchException)
           {
               System.out.println("\nInvalid choice");
               displayAdminMenu();
               input.nextLine();
           }
        }
        return choice;
    }

    public void getConfigChoice()
    {
        int choice =0;
        System.out.println("\nPress 1 to redirect to main menu after a transaction");
        System.out.println("Press any other number key to exit immediately after transaction");
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
}
