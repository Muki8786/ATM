package main;

import java.util.InputMismatchException;

import static main.global.GlobalConfigChoice.configChoice;
import static main.global.Input.input;

public class AdminUI {

    public void displayAdminMenu()
    {
        System.out.println("\n\t\tPress 1 to empty cash from Deposit Slot");
        System.out.println("\t\tPress 2 to fill cash into Dispenser");
        System.out.println("\t\tPress 0 to Exit");
    }

    public int getAdminMenuInput() {
        int choice = 0;
        while (true) {
            displayAdminMenu();
            System.out.print("\nEnter your Choice : ");
            try {
                choice = input.nextInt();
                break;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\nYour input is mismatched");
                input.nextLine();
                if (configChoice != 1)
                {
                    choice = -2;
                    break;
                }
            }
        }
        return choice;
    }

    public int getDepositChoice()
    {
        int choice =0;
        while(true)
        {
            System.out.println("\n\t\tPress 1 to completely fill the cash dispenser");
            System.out.println("\t\tPress 2 to fill the amount taken from deposit slot");
            System.out.println("\t\tPress 0 to exit");
            System.out.print("\t\tEnter your choice: ");
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

    public void printInvalidInput()
    {
        System.out.println("\nInvalid Input");
    }

    public void printDepositSlotSuccess(int amount)
    {
        if(amount==0)
        {
            System.out.println("\n\t\tDeposit slot is empty");
        }
        else
        {
            System.out.println("\nThe amount to be taken is Rs."+ amount);
            System.out.println("\nPlease take the cash");
        }
    }

    public void displayAmountToBeDeposited(int amount)
    {
        System.out.println("\nThe amount needed to be full is Rs."+ amount);
    }

    public void displayAmountRemainingFromDepositSlot(int amount)
    {
        System.out.println("\nThe balance amount from the withdrawal from deposit slot is " + amount);
    }

    public void askForDeposit()
    {
        System.out.println("\nPlease insert the cash in the dispenser");
    }


    public void printCashDispenserSuccess(boolean status)
    {
        if(status)
        {
            System.out.println("\n\t\tCash dispenser is filled");
        }
        else
        {
            System.out.println("\n\t\tProcess failed");
        }
    }
}
