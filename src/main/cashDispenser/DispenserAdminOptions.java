package main.cashDispenser;

public interface DispenserAdminOptions {
    public void insertIntoDispenser(int amount , int hun , int twoHun, int fiveHun , int twoThous);

    public int getAmount();
}
