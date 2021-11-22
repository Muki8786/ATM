package sdk.optionMenu;

import main.AdminUI;
import main.global.Logout;
import sdk.adminTransactions.EmptyDepositSlot;
import sdk.adminTransactions.FillDispenser;


import static main.global.GlobalConfigChoice.configChoice;

public class AdminOptionMenu {
    private String atmName;

    public AdminOptionMenu(String atmName)
    {
        this.atmName = atmName;
    }

    public void createAdminMenu(int accountNumber)
    {
        AdminUI adminUI = new AdminUI();
        int choice = adminUI.getAdminMenuInput();
        switch (choice)
        {
            case 0 :
            case -2:
                Logout.logout();
                break;
            case 1 :
                new EmptyDepositSlot(atmName).emptyDepositSlot(accountNumber);
                break;
            case 2 :
                new FillDispenser(atmName).fillCashDispenser(accountNumber);
                break;
            default:
            {
                if(configChoice!=1) {
                    adminUI.printInvalidInput();
                    Logout.logout();
                }
                else
                {
                    adminUI.printInvalidInput();
                    createAdminMenu(accountNumber);
                }
            }
        }
    }
}
