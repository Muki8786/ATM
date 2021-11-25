package sdk.UI;

public interface IAdminUI {
    void displayAdminMenu();

    int getAdminMenuInput();

    int getDepositChoice();

    void printInvalidInput();

    void printDepositSlotSuccess(int amount);

    void displayAmountToBeDeposited(int amount);

    void displayAmountRemainingFromDepositSlot(int amount);

    void askForDeposit();

    void printCashDispenserSuccess(boolean status);
}
