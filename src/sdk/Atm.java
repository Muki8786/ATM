package sdk;

import sdk.UI.*;
import sdk.optionMenu.AdminOptionMenu;
import sdk.optionMenu.OptionMenu;
import sdk.transactions.Login;

public class Atm {
    private static String atmName;
    private IBalanceInquiryUI balanceInquiryUI;
    private IChangePinUI changePinUI;
    private IDepositUI depositUI;
    private IFastCashUI fastCashUI;
    private IFundTransferUI fundTransferUI;
    private ILoginUI loginUI;
    private IOptionMenuUI optionMenuUI;
    private IWithdrawUI withdrawUI;
    private IAdminUI adminUI;
    private ICashDispenser cashDispenser;
    private IDepositSlot depositSlot;
    private static OptionMenu optionMenu;
    private static AdminOptionMenu adminOptionMenu;
    private ILogout logout;

    private static Atm atm;


    public Atm(IBalanceInquiryUI balanceInquiryUI, IChangePinUI changePinUI, IDepositUI depositUI, IFastCashUI fastCashUI, IFundTransferUI fundTransferUI, ILoginUI loginUI, IOptionMenuUI optionMenuUI, IWithdrawUI withdrawUI, IAdminUI adminUI, ICashDispenser cashDispenser, IDepositSlot depositSlot,ILogout logout, String atmName) {
        this.balanceInquiryUI = balanceInquiryUI;
        this.changePinUI = changePinUI;
        this.depositUI = depositUI;
        this.fastCashUI = fastCashUI;
        this.fundTransferUI = fundTransferUI;
        this.loginUI = loginUI;
        this.optionMenuUI = optionMenuUI;
        this.withdrawUI = withdrawUI;
        this.adminUI = adminUI;
        this.cashDispenser = cashDispenser;
        this.depositSlot = depositSlot;
        this.logout = logout;
        Atm.atmName = atmName;

    }

    public void run()
    {
        Login login = new Login(loginUI);
        if(login.login())
        {
            int accountNumber = login.getAccountNumber();
            if(!login.checkAdmin())
            {
                optionMenu = new OptionMenu(balanceInquiryUI,changePinUI,depositUI,fastCashUI,fundTransferUI,optionMenuUI
                ,withdrawUI,cashDispenser,depositSlot,logout);
                        optionMenu.createOptionMenu(accountNumber);
            }
            else
            {
                adminOptionMenu = new AdminOptionMenu(adminUI,cashDispenser,depositSlot,logout,atmName);
                adminOptionMenu.createAdminMenu(accountNumber);
            }
        }
        else
        {
            run();
        }
    }

    public static void createOptionMenu(int accountNumber)
    {
        optionMenu.createOptionMenu(accountNumber);
    }

    public static void createAdminOptionMenu(int accountNumber)
    {
        adminOptionMenu.createAdminMenu(accountNumber);
    }

    public static void setAtm(Atm atm)
    {
        Atm.atm = atm;
    }

    public static void restart()
    {
        atm.run();
    }
}
