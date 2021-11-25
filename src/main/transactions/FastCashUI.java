package main.transactions;



import sdk.UI.IFastCashUI;

import java.util.InputMismatchException;

import static sdk.GlobalConfigChoice.configChoice;
import static main.global.Input.input;


public class FastCashUI implements IFastCashUI {



    @Override
    public int getFastCashMenuChoice() {
        int choice = 0;

        while (true) {
            System.out.println("\nThe choices for Fast Cash are : ");
            System.out.println("\t\tPress 1 --- 500");
            System.out.println("\t\tPress 2 --- 1000");
            System.out.println("\t\tPress 3 --- 5000");
            System.out.println("\t\tPress 4 --- 10000");
            System.out.println("\n\t\t!Please press 0 if you want to exit!");
            System.out.print("\nEnter your choice : ");
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

    @Override
    public void printInvalidInput()
    {
        System.out.println("\nInvalid Input!");
    }

}
