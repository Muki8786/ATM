package transactions;

public interface SamePinCheck {
    public boolean verifyOldPin(int newPin , int oldPin);
}
