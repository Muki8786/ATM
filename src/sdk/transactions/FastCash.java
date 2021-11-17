package sdk.transactions;

import main.transactions.FastCashMain;
import main.global.Logout;

import static main.global.GlobalConfigChoice.configChoice;

public class FastCash {
    private int accountNumber;

    public FastCash(int accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public void fastCash()
    {
        FastCashMain fastCashMain = new FastCashMain();
        int amount = 0;

        int choice = fastCashMain.getFastCashMenuChoice();
        switch (choice)
        {
            case 0 :
            case -2 :
                Logout.logout();
                break;
            case 1 :
                amount = 500;
                break;
            case 2 :
                amount = 1000;
                break;
            case 3 :
                amount = 5000;
                break;
            case 4 :
                amount = 10000;
                break;
            default:
            {
                if (configChoice != 1)
                {
                    fastCashMain.printInvalidInput();
                    Logout.logout();
                }
                else
                {
                    fastCashMain.printInvalidInput();
                    fastCash();
                }
            }
        }

        if(amount>0)
        {
            new Withdrawal(accountNumber,amount).withdraw();
        }
    }
}
