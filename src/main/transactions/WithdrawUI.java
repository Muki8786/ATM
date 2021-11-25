package main.transactions;

import sdk.UI.IWithdrawUI;

import java.util.InputMismatchException;

import static sdk.GlobalConfigChoice.configChoice;
import static main.global.Input.input;


public class WithdrawUI implements IWithdrawUI {

    @Override
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

    @Override
    public void printSuccess(int status)
    {
        if(status == 1)
        {
            System.out.println("\nWithdraw successful");
            System.out.println("Please collect the cash");
        }
        else if(status == 0)
        {
            System.out.println("\nSorry! Unable to withdraw! Insufficient balance in your account");
        }
        else if(status == -1)
            System.out.println("\n\t\tSorry! Insufficient funds at the ATM");
    }

    @Override
    public void printNoCashInATM()
    {
        System.out.println("\n\t\tSorry! No cash in ATM");
    }

}
