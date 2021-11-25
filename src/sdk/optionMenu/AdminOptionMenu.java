package sdk.optionMenu;

import sdk.Atm;
import sdk.UI.IAdminUI;
import sdk.UI.ICashDispenser;
import sdk.UI.IDepositSlot;
import sdk.UI.ILogout;
import sdk.adminTransactions.EmptyDepositSlot;
import sdk.adminTransactions.FillDispenser;

import static sdk.GlobalConfigChoice.configChoice;

public class AdminOptionMenu {
    private final String atmName;
    private IAdminUI adminUI;
    private ICashDispenser cashDispenser;
    private IDepositSlot depositSlot;
    private ILogout logout;

    public AdminOptionMenu(IAdminUI adminUI , ICashDispenser cashDispenser , IDepositSlot depositSlot , ILogout logout , String atmName)
    {
        this.adminUI = adminUI;
        this.cashDispenser = cashDispenser;
        this.depositSlot = depositSlot;
        this.atmName = atmName;
        this.logout = logout;
    }

    public void createAdminMenu(int accountNumber)
    {
        int choice = adminUI.getAdminMenuInput();
        switch (choice)
        {
            case 0 :
            case -2:
                logout.logout();
                Atm.restart();
                break;
            case 1 :
                new EmptyDepositSlot(adminUI,depositSlot,logout,atmName,true).emptyDepositSlot(accountNumber);
                break;
            case 2 :
                new FillDispenser(adminUI,cashDispenser,depositSlot,logout,atmName).fillCashDispenser(accountNumber);
                break;
            default:
            {
                if(configChoice!=1) {
                    adminUI.printInvalidInput();
                    logout.logout();
                    Atm.restart();
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
