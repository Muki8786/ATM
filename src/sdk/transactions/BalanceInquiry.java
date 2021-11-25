package sdk.transactions;

import sdk.Atm;
import sdk.UI.IBalanceInquiryUI;
import sdk.UI.ILogout;

import static sdk.GlobalConfigChoice.configChoice;
import static sdk.transactions.GlobalDatabase.accountsDatabase;


public class BalanceInquiry {

    private IBalanceInquiryUI balanceInquiryUI;
    private ILogout logout;

    public BalanceInquiry(IBalanceInquiryUI balanceInquiryUI,ILogout logout)
    {
        this.balanceInquiryUI = balanceInquiryUI;
        this.logout = logout;
    }

    public void balanceInquiry(int accountNumber) {

        float balance = accountsDatabase.getAccount(accountNumber).getBalance();
        balanceInquiryUI.printBalance(balance);
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
}
