package sdk.UI;

import main.Denomination;

public interface ICashDispenser {

    void dispenseCash(int amount, int hunCount, int twoHunCount, int fiveHunCount, int twoThousCount)//amount should be divisible by 100 || 200 || 500 || 2000
    ;

    boolean sufficientATMBalanceCheck(int amount);

    int getBalance();

    int getAmount();

    void withdrawWithCount(int amount);

    void denominationsAvailable();

    void insertIntoDispenser(int amount, int hun, int twoHun, int fiveHun, int twoThous);

    int denominationCountNeeded(int key);

    boolean denominationsCheck(int key, int count);
}
