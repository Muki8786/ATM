package main;

import main.cashDispenser.CashDispenser;
import main.global.Logout;
import sdk.UI.*;
import main.depositSlot.DepositSlot;
import main.transactions.*;
import sdk.Atm;

public class MyFactory {
    public static Atm createMyAtm() {
        return new Atm(createBalanceInquiryUI(), createChangePinUI(), createDepositUI(), createFastCashUI(), createFundTransferUI()
                , createLoginUI(), createOptionMenuUI(), createWithdrawUI(), createAdminUI(), createCashDispenser(),
                createDepositSlot(),createLogout(), "First");
    }

    public static IDenomination createDenomination(int count) {
        return new Denomination(count);
    }

    public static ICashDispenser createCashDispenser() {
        return CashDispenser.getInstance(createDenomination(100));
    }

    public static IDepositSlot createDepositSlot() {
        return DepositSlot.getInstance(createDenomination(0));
    }

    public static IAdminUI createAdminUI() {
        return new AdminUI();
    }

    public static IBalanceInquiryUI createBalanceInquiryUI() {
        return new BalanceInquiryUI();
    }

    public static IChangePinUI createChangePinUI() {
        return new ChangePinUI();
    }

    public static IDepositUI createDepositUI() {
        return new DepositUI();
    }

    public static IFastCashUI createFastCashUI() {
        return new FastCashUI();
    }

    public static IFundTransferUI createFundTransferUI() {
        return new FundTransferUI();
    }

    public static ILoginUI createLoginUI() {
        return new LoginUI();
    }

    public static IOptionMenuUI createOptionMenuUI() {
        return new OptionMenuUI();
    }

    public static IWithdrawUI createWithdrawUI() {
        return new WithdrawUI();
    }

    public static ILogout createLogout(){return new Logout();
    }
}
