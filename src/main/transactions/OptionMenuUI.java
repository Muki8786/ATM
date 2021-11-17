package main.transactions;

import java.util.InputMismatchException;

import static main.global.GlobalConfigChoice.configChoice;
import static main.global.Input.input;

public class OptionMenuUI {



    public OptionMenuUI()
    {
    }

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

        /*switch (choice)
        {
            case 0 :
                Logout.logout();
                break;
            case 1 :
                new BalanceInquiry().printBalance(accountNumber);
                break;
            case 2 :
                new WithdrawMain(accountNumber).startWithdraw();
                break;
            case 3 :
                new FastCashMain(accountNumber).fastCash();
                break;
            case 4 :
                new DepositMain(accountNumber).startDeposit();
                break;
            case 5 :
                new ChangePinMain(accountNumber).printSuccess();
                break;
            case 6 :
                new FundTransferMain(accountNumber).startFundTransfer();
                break;
            default:
            {
                System.out.println("Invalid choice");
                Logout.logout();
            }

        }

         */

        return choice;
    }

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
