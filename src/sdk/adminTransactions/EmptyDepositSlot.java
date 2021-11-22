package sdk.adminTransactions;

import main.AdminUI;
import main.depositSlot.DepositSlot;
import sdk.Atm;
import sdk.accounts.AccountsDatabase;
import sdk.bank.AdminCashier;
import sdk.bank.AdminLog;
import sdk.transactions.GlobalDatabase;

import static main.global.GlobalConfigChoice.configChoice;
import static main.global.Logout.logout;

public class EmptyDepositSlot extends GlobalDatabase{

    private DepositSlot depositSlot;
    private AccountsDatabase accountsDatabase;
    private int depositAmount;
    private int hunCount;
    private int twoHunCount;
    private int fiveHunCount;
    private int twoThousCount;
    private final String atmName;

    public EmptyDepositSlot(String atmName)
    {
        depositSlot = DepositSlot.getInstance();
        this.accountsDatabase = GlobalDatabase.accountsDatabase;
        depositAmount =0;
        hunCount =0;
        twoHunCount = 0;
        fiveHunCount =0;
        twoThousCount = 0;
        this.atmName = atmName;
    }



    public void emptyDepositSlot(int accountNumber) {
        AdminUI adminUI = new AdminUI();
        int amount = depositSlot.getAmount();
        depositAmount = amount;
        setCount();
        if (amount != 0) {
            adminUI.printDepositSlotSuccess(amount);
            depositSlot.withdrawFromDepositSlot();
            AdminCashier.creditBankBalance(amount);
            AdminLog adminLog = new AdminLog(accountsDatabase.getAccount(accountNumber).getUsername(), atmName, "Emptying", amount);
            AdminCashier.addAdminLog(adminLog);
            setGlobals();
        }
        adminUI.printDepositSlotSuccess(0);
        AdminCashier.printAdminLog();

        if (configChoice == 1) {
            Atm.createAdminOptionMenu(accountNumber);
        } else {
            logout();
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
