package sdk.UI;

public interface IWithdrawUI {
    int getAmount();

    void printSuccess(int status);

    void printNoCashInATM();
}
