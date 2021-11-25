package sdk.optionMenu;

import sdk.Atm;
import sdk.UI.*;
import sdk.transactions.*;

import static sdk.GlobalConfigChoice.configChoice;

public class OptionMenu {

    private IBalanceInquiryUI balanceInquiryUI;
    private IChangePinUI changePinUI;
    private IDepositUI depositUI;
    private IFastCashUI fastCashUI;
    private IFundTransferUI fundTransferUI;
    private IOptionMenuUI optionMenuUI;
    private IWithdrawUI withdrawUI;
    private ICashDispenser cashDispenser;
    private IDepositSlot depositSlot;
    private ILogout logout;

    public OptionMenu(IBalanceInquiryUI balanceInquiryUI, IChangePinUI changePinUI, IDepositUI depositUI, IFastCashUI fastCashUI, IFundTransferUI fundTransferUI, IOptionMenuUI optionMenuUI, IWithdrawUI withdrawUI, ICashDispenser cashDispenser, IDepositSlot depositSlot,ILogout logout) {
        this.balanceInquiryUI = balanceInquiryUI;
        this.changePinUI = changePinUI;
        this.depositUI = depositUI;
        this.fastCashUI = fastCashUI;
        this.fundTransferUI = fundTransferUI;
        this.optionMenuUI = optionMenuUI;
        this.withdrawUI = withdrawUI;
        this.cashDispenser = cashDispenser;
        this.depositSlot = depositSlot;
        this.logout = logout;
    }



    public void createOptionMenu(int accountNumber)
    {
        int choice = optionMenuUI.optionMenu();
        switch (choice)
        {
            case 0 :
            case -2 :
                logout.logout();
                Atm.restart();
                break;
            case 1 :
                new BalanceInquiry(balanceInquiryUI,logout).balanceInquiry(accountNumber);
                break;
            case 2 :
                new Withdrawal(withdrawUI,cashDispenser,logout,accountNumber).withdraw();
                break;
            case 3 :
                new FastCash(fastCashUI,withdrawUI ,cashDispenser ,logout,accountNumber).fastCash();
                break;
            case 4 :
                new Deposit(depositUI,depositSlot,logout,accountNumber).deposit();
                break;
            case 5 :
                new ChangePin(changePinUI,logout,accountNumber).changeUserPin();
                break;
            case 6 :
                new FundTransfer(fundTransferUI,depositUI,depositSlot,logout,accountNumber).fundTransfer();
                break;
            default:
            {
                if(configChoice!=1) {
                    optionMenuUI.printInvalidInput();
                    logout.logout();
                    Atm.restart();
                }
                else
                {
                    optionMenuUI.printInvalidInput();
                    createOptionMenu(accountNumber);
                }
            }

        }
    }
}
