package sdk.adminTransactions;

import main.AdminUI;
import main.cashDispenser.CashDispenser;
import sdk.Atm;
import sdk.accounts.AccountsDatabase;
import sdk.bank.AdminCashier;
import sdk.bank.AdminLog;
import sdk.transactions.GlobalDatabase;

import static main.global.GlobalConfigChoice.configChoice;
import static main.global.Logout.logout;

public class FillDispenser extends GlobalDatabase{
    private String atmName;
    private CashDispenser cashDispenser;
    private AccountsDatabase accountsDatabase;
    private int depositAmount;
    private int hunCount;
    private int twoHunCount;
    private int fiveHunCount;
    private int twoThousCount;


    public FillDispenser(String atmName)
    {
        cashDispenser = CashDispenser.getInstance();
        this.accountsDatabase = GlobalDatabase.accountsDatabase;
        this.atmName = atmName;
        depositAmount =0;
        hunCount =0;
        twoHunCount = 0;
        fiveHunCount =0;
        twoThousCount = 0;
    }

    public void fillCashDispenser(int accountNumber)
    {
        AdminUI adminUI = new AdminUI();


        int depositChoice = adminUI.getDepositChoice();
        if(depositChoice == 0)
        {
            logout();
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
            logout();
        }
    }
}
