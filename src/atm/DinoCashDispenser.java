package atm;

public interface DinoCashDispenser {
    public int denominationCountNeeded(int key);

    public boolean denominationsCheck(int key , int count);

    public void denominationsAvailable();

    public boolean withdrawWithCount(int amount);

}
