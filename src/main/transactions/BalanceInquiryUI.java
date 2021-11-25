package main.transactions;


import sdk.UI.IBalanceInquiryUI;

public class BalanceInquiryUI implements IBalanceInquiryUI {

    @Override
    public void printBalance(float balance)
    {
        System.out.println("The Balance is Rs."+balance);
    }
}
