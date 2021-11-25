package sdk.adminTransactions;

import main.depositSlot.DepositSlot;
import sdk.Atm;
import main.StartAtm;
import sdk.UI.IAdminUI;
import sdk.UI.IDepositSlot;
import sdk.UI.ILogout;
import sdk.accounts.AccountsDatabase;
import sdk.bank.AdminCashier;
import sdk.bank.AdminLog;
import sdk.transactions.GlobalDatabase;

import static sdk.GlobalConfigChoice.configChoice;


public class EmptyDepositSlot extends GlobalDatabase {

    private IAdminUI adminUI;
    private IDepositSlot depositSlot;
    private AccountsDatabase accountsDatabase;
    private int depositAmount;
    private int hunCount;
    private int twoHunCount;
    private int fiveHunCount;
    private int twoThousCount;
    private final String atmName;
    private boolean allow;
    private ILogout logout;

    public EmptyDepositSlot(IAdminUI adminUI , IDepositSlot depositSlot ,ILogout logout, String atmName, boolean allow)
    {
        this.adminUI = adminUI;
        this.depositSlot = depositSlot;
        this.accountsDatabase = GlobalDatabase.accountsDatabase;
        depositAmount =0;
        hunCount =0;
        twoHunCount = 0;
        fiveHunCount =0;
        twoThousCount = 0;
        this.atmName = atmName;
        this.allow = allow;
        this.logout = logout;
    }



    public void emptyDepositSlot(int accountNumber){
        int amount = depositSlot.getAmount();
        depositAmount = amount;
        setCount();
        if (amount != 0) {
            adminUI.printDepositSlotSuccess(amount);
            depositSlot.withdrawFromDepositSlot();
            AdminCashier.creditBankBalance(amount);
            System.out.println(DepositSlot.depositBalance);
            AdminLog adminLog = new AdminLog(accountsDatabase.getAccount(accountNumber).getUsername(), atmName, "Emptying", amount);
            AdminCashier.addAdminLog(adminLog);
            setGlobals();
        }
        adminUI.printDepositSlotSuccess(0);
        AdminCashier.printAdminLog();

        if(!allow)
        {
            return;
        }

        if (configChoice == 1) {
            Atm.createAdminOptionMenu(accountNumber);
        } else {
            logout.logout();
            StartAtm.restart();
        }

    }


    public void setCount()
    {
        hunCount = depositSlot.getDenominationCount(100);
        twoHunCount = depositSlot.getDenominationCount(200);
        fiveHunCount = depositSlot.getDenominationCount(500);
        twoThousCount = depositSlot.getDenominationCount(2000);
    }

    public void setGlobals()
    {
        DenominationsCount.setAllCountAndAmount(depositAmount,hunCount,twoHunCount,fiveHunCount,twoThousCount);
    }


}
