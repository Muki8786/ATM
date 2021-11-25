package main.transactions;

import sdk.UI.ILoginUI;

import java.util.InputMismatchException;

import static sdk.GlobalConfigChoice.configChoice;
import static main.global.Input.input;

public class LoginUI implements ILoginUI {

    @Override
    public void login(boolean allow, String name)
    {

        if(allow)
        {
            System.out.println("\nHi "+ name);
        }
        else
        {
            System.out.println("Invalid credentials");
        }

    }

    @Override
    public int inputAccountNumber()
    {
        System.out.println("\n\t\tWelcome to the ATM\n");
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
                if(configChoice!=1)
                    break;
            }

        }
        return userAccountNumber;
    }

    @Override
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


}
