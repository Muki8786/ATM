package main.cashDispenser;

public interface DinoCashDispenser {
    public int denominationCountNeeded(int key);

    public boolean denominationsCheck(int key , int count);

    public void denominationsAvailable();

    public void withdrawWithCount(int amount);

}
