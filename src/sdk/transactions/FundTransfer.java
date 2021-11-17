package sdk.transactions;

import main.transactions.FundTransferMain;
import sdk.Atm;


import static main.global.GlobalConfigChoice.configChoice;
import static main.global.Logout.logout;
import static sdk.transactions.GlobalDatabase.accountsDatabase;


public class FundTransfer {
    private int accountNumber;
    public FundTransfer(int accountNumber)
    {
        this.accountNumber = accountNumber;
    }
    public void fundTransfer()
    {
        FundTransferMain fundTransferMain = new FundTransferMain();

        int receiverAccountNumber =0;
        receiverAccountNumber = fundTransferMain.inputAccountNumber();
        if(checkReceiver(receiverAccountNumber))
        {
            Deposit deposit = new Deposit(receiverAccountNumber);

            deposit.deposit();

            fundTransferMain.printSuccess(deposit.fundTransferSuccess);
        }
        else
        {
            fundTransferMain.printNoAccountFound();
        }
        if(configChoice == 1)
        {
            Atm.createOptionMenuUI(accountNumber);
        }
        else
        {
            logout();
        }
    }

    public boolean checkReceiver(int receiverAccountNumber)
    {
        return accountsDatabase.accountCheck(receiverAccountNumber);
    }
}
