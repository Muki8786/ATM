package sdk.transactions;

import main.transactions.BalanceInquiryUI;
import sdk.Atm;

import static main.global.GlobalConfigChoice.configChoice;
import static main.global.Logout.logout;

public class BalanceInquiry extends GlobalDatabase{


    public void balanceInquiry(int accountNumber) {
        BalanceInquiryUI balanceInquiryUI = new BalanceInquiryUI();

        float balance = GlobalDatabase.accountsDatabase.getAccount(accountNumber).getBalance();
        balanceInquiryUI.printBalance(balance);
        if(configChoice == 1)
        {
            Atm.createOptionMenuUI(accountNumber);
        }
        else
        {
            logout();
        }
    }
}
