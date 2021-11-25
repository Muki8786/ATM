package sdk.UI;

public interface IDepositUI {
    void printDenominationCheck();

    void printSuccess(boolean status);

    int getCount(int key);

    int getAmount();
}
