package main.transactions;

import java.util.InputMismatchException;

import static main.global.GlobalConfigChoice.configChoice;
import static main.global.Input.input;

public class ChangePinMain {

    public void printSuccess(boolean allow)
    {
            if (allow) {
                System.out.println("\n\t\tPin change successful");
            }
            else
            {
                System.out.println("\nSorry!, New Pin cannot be the same as Old Pin ");
            }

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
}
