package sdk.transactions;



import sdk.Atm;
import sdk.UI.ICashDispenser;
import sdk.UI.IFastCashUI;
import sdk.UI.ILogout;
import sdk.UI.IWithdrawUI;


import static sdk.GlobalConfigChoice.configChoice;

public class FastCash {
    private int accountNumber;
    private IFastCashUI fastCashUI;
    private IWithdrawUI withdrawUI;
    private ICashDispenser cashDispenser;
    private ILogout logout;

    public FastCash(IFastCashUI fastCashUI , IWithdrawUI withdrawUI, ICashDispenser cashDispenser,ILogout logout, int accountNumber)
    {
        this.fastCashUI = fastCashUI;
        this.accountNumber = accountNumber;
        this.withdrawUI = withdrawUI;
        this.cashDispenser = cashDispenser;
        this.logout = logout;
    }

    public void fastCash()
    {

        int amount = 0;

        int choice = fastCashUI.getFastCashMenuChoice();
        switch (choice)
        {
            case 0 :
            case -2 :
                logout.logout();
                Atm.restart();
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
                    fastCashUI.printInvalidInput();
                    logout.logout();
                    Atm.restart();
                }
                else
                {
                    fastCashUI.printInvalidInput();
                    fastCash();
                }
            }
        }

        if(amount>0)
        {
            new Withdrawal(withdrawUI ,cashDispenser ,accountNumber,amount).withdraw();
        }
    }
}
