package atm;

public interface DinoDepositSlot {
    public int getDenominationCount(int key);

    public void allDenominations(int hun , int twoHun , int fiveHun , int twoThous);

    public boolean depositCapacityCheck(int amount , int hun , int twoHun , int fiveHun , int twoThous);
}
