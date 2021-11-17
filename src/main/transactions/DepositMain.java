package main.transactions;

import java.util.InputMismatchException;

import static main.global.GlobalConfigChoice.configChoice;
import static main.global.Input.input;

public class DepositMain implements GetAmount{


    public void printDenominationCheck()
    {
        System.out.println("\n\t\tCheck the denominations with the amount");
    }

    public void printSuccess(boolean status)
    {
        if(status)
        {
            System.out.println("\n\t\tDeposit successful!\n");
        }
        else
        {
            System.out.println("\n\t\tDeposit unsuccessful!\n");
        }
    }

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

    public int getAmount()
    {
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

}
