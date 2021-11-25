package sdk.adminTransactions;

import main.cashDispenser.CashDispenser;
import sdk.Atm;
import main.StartAtm;
import sdk.UI.IAdminUI;
import sdk.UI.ICashDispenser;
import sdk.UI.IDepositSlot;
import sdk.UI.ILogout;
import sdk.accounts.AccountsDatabase;
import sdk.bank.AdminCashier;
import sdk.bank.AdminLog;
import sdk.transactions.GlobalDatabase;

import static sdk.GlobalConfigChoice.configChoice;

public class FillDispenser extends GlobalDatabase {
    private String atmName;
    private ICashDispenser cashDispenser;
    private IDepositSlot depositSlot;
    private IAdminUI adminUI;
    private AccountsDatabase accountsDatabase;
    private int depositAmount;
    private int hunCount;
    private int twoHunCount;
    private int fiveHunCount;
    private int twoThousCount;
    private ILogout logout;


    public FillDispenser(IAdminUI adminUI , ICashDispenser cashDispenser , IDepositSlot depositSlot ,ILogout logout, String atmName)
    {
        this.adminUI = adminUI;
        this.cashDispenser = cashDispenser;
        this.depositSlot = depositSlot;
        this.accountsDatabase = GlobalDatabase.accountsDatabase;
        this.atmName = atmName;
        depositAmount =0;
        hunCount =0;
        twoHunCount = 0;
        fiveHunCount =0;
        twoThousCount = 0;
        this.logout = logout;
    }

    public void fillCashDispenser(int accountNumber)
    {



        int depositChoice = adminUI.getDepositChoice();
        if(depositChoice == 0)
        {
            logout.logout();
            StartAtm.restart();
            return;
        }
        else if(depositChoice == 1) {
            depositAmount = cashDispenser.getAmount();
            adminUI.displayAmountToBeDeposited(depositAmount);
            hunCount = cashDispenser.denominationCountNeeded(100);
            twoHunCount = cashDispenser.denominationCountNeeded(200);
            fiveHunCount = cashDispenser.denominationCountNeeded(500);
            twoThousCount = cashDispenser.denominationCountNeeded(2000);
        }
        else if(depositChoice == 2)
        {
            new EmptyDepositSlot(adminUI,depositSlot,logout,atmName,false).emptyDepositSlot(accountNumber);
            depositAmount = DenominationsCount.getDepositAmount();
            adminUI.displayAmountRemainingFromDepositSlot(depositAmount);
            hunCount = DenominationsCount.getHunCount();
            twoHunCount = DenominationsCount.getTwoHunCount();
            fiveHunCount = DenominationsCount.getFiveHunCount();
            twoThousCount = DenominationsCount.getTwoThousCount();
        }

        if (depositAmount != 0) {
            if (AdminCashier.balanceCheck(depositAmount) && cashDispenser.denominationsCheck(100,hunCount)
                    && cashDispenser.denominationsCheck(200 , twoHunCount) && cashDispenser.denominationsCheck(500 , fiveHunCount)
                    && cashDispenser.denominationsCheck(2000,twoThousCount)) {
                adminUI.askForDeposit();
                AdminCashier.debitBankBalance(depositAmount);
                cashDispenser.insertIntoDispenser(depositAmount, hunCount, twoHunCount, fiveHunCount, twoThousCount);
                System.out.println(CashDispenser.balance);
                AdminLog adminLog = new AdminLog(accountsDatabase.getAccount(accountNumber).getUsername(), atmName, "Filling", depositAmount);
                AdminCashier.addAdminLog(adminLog);
                adminUI.printCashDispenserSuccess(true);
                DenominationsCount.setZero();
            }
            else
            {
                adminUI.printCashDispenserSuccess(false);
            }
            AdminCashier.printAdminLog();
        }
        if (configChoice == 1) {
            Atm.createAdminOptionMenu(accountNumber);
        } else {
            logout.logout();
            StartAtm.restart();
        }
    }
}
