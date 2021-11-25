package main.transactions;

import sdk.UI.IOptionMenuUI;

import java.util.InputMismatchException;

import static sdk.GlobalConfigChoice.configChoice;
import static main.global.Input.input;

public class OptionMenuUI implements IOptionMenuUI {



    public OptionMenuUI()
    {
    }

    @Override
    public int optionMenu()
    {
        int choice = 0;
        while(true)
        {
            printOptionMenu();
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
                {
                    choice = -2;
                    break;
                }
            }
        }
        return choice;
    }

    @Override
    public void printInvalidInput()
    {
        System.out.println("\nInvalid choice");
    }

    private void printOptionMenu()
    {
        System.out.println("\n\t\tPress 1 for BALANCE INQUIRY");
        System.out.println("\t\tPress 2 for WITHDRAWAL");
        System.out.println("\t\tPress 3 for FAST CASH");
        System.out.println("\t\tPress 4 to DEPOSIT");
        System.out.println("\t\tPress 5 to CHANGE PIN");
        System.out.println("\t\tPress 6 to FUND TRANSFER");
        System.out.println("\t\tPress 0 to EXIT");
    }

}
