package main.transactions;

import java.util.InputMismatchException;
import static main.global.GlobalConfigChoice.configChoice;
import static main.global.Input.input;


public class FundTransferMain {

    public void printNoAccountFound()
    {
        System.out.println("\nSorry! No Account found ");
    }

    public  void printSuccess(boolean value)
    {
        if(value)
            System.out.println("\nFund Transfer successful");
        else
            System.out.println("\nFund Transfer unsuccessful");
    }

    public int inputAccountNumber()
    {
        int userAccountNumber = 0;
        while(true){
            System.out.println("\nPlease provide the Receiver's details");
            System.out.println("\n\t\t!Please press 0 if you want to exit!");
            System.out.print("Enter the Account number : ");
            try {
                userAccountNumber = input.nextInt();
                break;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\n\nThe Account number you have entered does not match");
                input.nextLine();
                if(configChoice!=1)
                {
                    userAccountNumber = 0;
                    break;
                }
            }

        }
        return userAccountNumber;
    }
}
