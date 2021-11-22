package sdk;

import main.StartAtm;
import main.cashDispenser.CashDispenser;
import main.depositSlot.DepositSlot;
import sdk.optionMenu.AdminOptionMenu;
import sdk.optionMenu.OptionMenu;
import sdk.transactions.Login;

public class Atm {
    private int accountNumber;

    private String atmName;
    private CashDispenser cashDispenser;
    private DepositSlot depositSlot;

    public Atm(String atmName)
    {
        this.atmName = atmName;
        cashDispenser = CashDispenser.getInstance();
        depositSlot = DepositSlot.getInstance();
    }

    public void run()
    {
        Login login = new Login();
        if(login.login())
        {
            accountNumber = login.getAccountNumber();
            if(!login.checkAdmin())
            {
                new OptionMenu().createOptionMenu(accountNumber);
            }
            else
            {
                new AdminOptionMenu(atmName).createAdminMenu(accountNumber);
            }
        }
        else
        {
            run();
        }
    }

    public static void createOptionMenuUI(int accountNumber)
    {
        new OptionMenu().createOptionMenu(accountNumber);
    }

    public static void createAdminOptionMenu(int accountNumber)
    {
        new AdminOptionMenu(StartAtm.atmName).createAdminMenu(accountNumber);
    }



}
