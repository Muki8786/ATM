package sdk.UI;

import main.Denomination;

public interface IDepositSlot {


    void acceptCash(int amount, int hun, int twoHun, int fiveHun, int twoThous);

    boolean depositCapacityCheck(int amount, int hun, int twoHun, int fiveHun, int twoThous);

    void withdrawFromDepositSlot();

    int getAmount();

    int getDenominationCount(int key);

    void allDenominations(int hun, int twoHun, int fiveHun, int twoThous);
}
