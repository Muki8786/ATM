import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Atm {
    private boolean userAuthenticated;
    private int currentAccountNumber;

    private CashDispenser cashDispenser;
    private DepositSlot depositSlot;
    private AccountsDatabase accountsDatabase;
    private BalanceInquiry balanceInquiry;
    private Withdrawal withdrawal;
    private Deposit deposit;
    private ChangePin changePin;
    private FundTransfer fundTransfer;
    private static Atm uniqueInstance;

    Scanner input = new Scanner(System.in);

    public Atm()
    {
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
            createMenu();
        }
        else
        {
            run();
        }

    }

    public boolean login()
    {
        System.out.println("\t\tWelcome!\n");
       return authenticate(inputAccountNumber() , inputPin());
    }

    public boolean authenticate(int accountNumber , int pin)
    {
        userAuthenticated = accountsDatabase.authenticateUser(accountNumber,pin);
        if(userAuthenticated == true)
        {
            currentAccountNumber = accountNumber;
            return true;
        }
        else{
            System.out.println("Invalid Account number/Pin!");
            return false;
        }

    }

    public void createMenu()
    {
        displayMainMenu();
        int choice = getInputMainMenu();
        if(choice == -1)
            run();
        else if(choice == 1) {
            balanceInquiry = new BalanceInquiry(currentAccountNumber, accountsDatabase);
            balanceInquiry.balanceInquiry();
            if(exitOrContinue() == 1)
            {
                createMenu();
            }
            else
            {
                logout();
            }
        }

        else if (choice == 2) {
            withdrawal = new Withdrawal(currentAccountNumber, accountsDatabase, cashDispenser);

            int amount = getUserAmount();
            if(amount != 0)
            {
                if (withdrawal.withdraw(amount)) {
                    int balanceChoice = balanceAfterTransaction();
                    if (balanceChoice == 1) {
                        balanceInquiry = new BalanceInquiry(currentAccountNumber, accountsDatabase);
                        balanceInquiry.balanceInquiry();
                    }
                }
            }
            if(exitOrContinue() == 1)
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
            withdrawal = new Withdrawal(currentAccountNumber , accountsDatabase , cashDispenser);

            int amount = displayFastCashMenu();
            if(amount > 0)
            {
                if(withdrawal.withdraw(amount) == true)
                {
                        int balanceChoice = balanceAfterTransaction();
                        if (balanceChoice == 1) {
                            balanceInquiry = new BalanceInquiry(currentAccountNumber, accountsDatabase);
                            balanceInquiry.balanceInquiry();
                        }
                }
            }
            if(exitOrContinue() == 1)
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
            deposit = new Deposit(currentAccountNumber , accountsDatabase , depositSlot);
            int amount = getUserAmount();
            if(amount > 0)
            {
                if(deposit.depositCash(amount))
                {
                    int balanceChoice = balanceAfterTransaction();
                    if (balanceChoice == 1) {
                        balanceInquiry = new BalanceInquiry(currentAccountNumber, accountsDatabase);
                        balanceInquiry.balanceInquiry();
                    }
                }
            }
            if(exitOrContinue() == 1)
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
            changePin = new ChangePin(currentAccountNumber , accountsDatabase);
            int newPin = getNewPin();
            if(newPin != 0)
            {
                if(changePin.changeUserPin(newPin))
                {
                    System.out.println("\n\t\tPin change successful");
                }
            }
            else
            {
                System.out.println("\n\t\tPin cannot be set");
            }
            if(exitOrContinue() == 1)
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
            fundTransfer = new FundTransfer(currentAccountNumber , accountsDatabase);

            int inputChoice = 0;
            inputChoice = getInputFundTransferMenu();
            if(inputChoice == 1)
            {
                int receiverAccountNumber = 0;
                System.out.println("\nPlease provide the Receiver's details");
                System.out.println("\n\t\t!Please press 0 if you want to exit!");
                receiverAccountNumber = inputAccountNumber();
                if(fundTransfer.checkReceiver(receiverAccountNumber))
                {
                    deposit = new Deposit(currentAccountNumber , accountsDatabase , depositSlot);
                    int amount = getUserAmount();
                    if(amount !=0)
                    {
                        if(deposit.depositCash(amount)) {
                            fundTransfer.transferFund(receiverAccountNumber , amount);
                        }
                    }
                }
                else{
                    System.out.println("\nInvalid Account Number");
                }
                if(exitOrContinue() == 1)
                {
                    createMenu();
                }
                else
                {
                    logout();
                }
            }
            else if(inputChoice == 2)
            {
                int receiverAccountNumber = 0;

                System.out.println("\nPlease provide the Receiver's details");
                System.out.println("\n\t\t!Please press 0 if you want to exit!");
                receiverAccountNumber = inputAccountNumber();
                if(fundTransfer.checkReceiver(receiverAccountNumber))
                {
                    int amount = getUserAmount();
                    if(amount != 0)
                    {
                        fundTransfer.transferFund(receiverAccountNumber , amount);
                    }
                }
                else
                {
                    System.out.println("Invalid account number");
                }
                if(exitOrContinue() == 1)
                {
                    createMenu();
                }
                else
                {
                    logout();
                }
            }

            else if(inputChoice != 0)
            {
                System.out.println("Invalid choice");
            }
            if(exitOrContinue() == 1)
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
        System.out.println("\n\t\tThank you for using our ATM");
        System.out.println("\t\tPlease sanitize your hands before leaving :) \n\n\n");
        run();
    }

    public void displayMainMenu() {
        System.out.println("\n\t\tHi "+ accountsDatabase.getAccount(currentAccountNumber).getUsername() + "!");
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

    public static Atm getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Atm();
        }
        return uniqueInstance;
    }

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
                System.out.println("\n\nThe Pin you have entered cannot be set ");
                System.out.println("\nTry again");
                input.nextLine();
            }
        }
        return newPin;
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
            System.out.println("\nPress 1 --- Continue to Transaction menu");
            System.out.println("Press any other number key to exit");
            try {
                choice = input.nextInt();
                break;
            } catch (InputMismatchException inputMismatchException)
            {
                System.out.println("Enter a number!");
                input.nextLine();
            }
        }
        return choice;
    }


}
