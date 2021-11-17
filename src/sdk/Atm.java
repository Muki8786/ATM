package sdk;

import main.cashDispenser.CashDispenser;
import main.depositSlot.DepositSlot;
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
            //if(!account.getAdmin())
            //new OptionMenuUI(accountNumber).optionMenu();
            new OptionMenu().createOptionMenu(accountNumber);
            //else
            {
                //createAdminMenu();
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



}
