package sdk.transactions;


import sdk.Atm;

import sdk.UI.IDepositSlot;
import sdk.UI.IDepositUI;
import sdk.UI.IFundTransferUI;
import sdk.UI.ILogout;

import static sdk.GlobalConfigChoice.configChoice;
import static sdk.transactions.GlobalDatabase.accountsDatabase;


public class FundTransfer {
    private int accountNumber;
    private IFundTransferUI fundTransferUI;
    private IDepositUI depositUI;
    private IDepositSlot depositSlot;
    private ILogout logout;

    public FundTransfer(IFundTransferUI fundTransferUI , IDepositUI depositUI , IDepositSlot depositSlot ,ILogout logout,int accountNumber)
    {
        this.fundTransferUI = fundTransferUI;
        this.depositUI = depositUI;
        this.depositSlot = depositSlot;
        this.accountNumber = accountNumber;
        this.logout = logout;
    }


    public void fundTransfer()
    {


        int receiverAccountNumber =0;
        receiverAccountNumber = fundTransferUI.inputAccountNumber();
        if(checkReceiver(receiverAccountNumber))
        {
            Deposit deposit = new Deposit(depositUI,depositSlot,logout,receiverAccountNumber);

            deposit.deposit();

            fundTransferUI.printSuccess(deposit.fundTransferSuccess);
        }
        else
        {
            fundTransferUI.printNoAccountFound();
        }
        if(configChoice == 1)
        {
            Atm.createOptionMenu(accountNumber);
        }
        else
        {
            logout.logout();
            Atm.restart();
        }
    }

    public boolean checkReceiver(int receiverAccountNumber)
    {
        return accountsDatabase.accountCheck(receiverAccountNumber);
    }
}
