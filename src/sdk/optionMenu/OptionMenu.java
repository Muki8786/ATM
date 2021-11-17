package sdk.optionMenu;

import main.global.Logout;
import main.transactions.OptionMenuUI;
import sdk.transactions.*;

import static main.global.GlobalConfigChoice.configChoice;

public class OptionMenu {

    public void createOptionMenu(int accountNumber)
    {
        OptionMenuUI optionMenuUI = new OptionMenuUI();
        int choice = optionMenuUI.optionMenu();
        switch (choice)
        {
            case 0 :
            case -2 :
                Logout.logout();
                break;
            case 1 :
                new BalanceInquiry().balanceInquiry(accountNumber);
                break;
            case 2 :
                new Withdrawal(accountNumber).withdraw();
                break;
            case 3 :
                new FastCash(accountNumber).fastCash();
                break;
            case 4 :
                new Deposit(accountNumber).deposit();
                break;
            case 5 :
                new ChangePin(accountNumber).changeUserPin();
                break;
            case 6 :
                new FundTransfer(accountNumber).fundTransfer();
                break;
            default:
            {
                if(configChoice!=1) {
                    optionMenuUI.printInvalidInput();
                    Logout.logout();
                }
                else
                {
                    optionMenuUI.printInvalidInput();
                    createOptionMenu(accountNumber);
                }
            }

        }
    }
}
