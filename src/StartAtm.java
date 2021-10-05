import atm.Atm;
import atm.CashDispenser;
import atm.DepositSlot;

public class StartAtm {
    public static void main(String args[])
    {
        CashDispenser cashDispenser = new CashDispenser();
        DepositSlot depositSlot = new DepositSlot();
        Atm atm = new Atm("FIRST ",cashDispenser,depositSlot);

        CashDispenser cashDispenser1 = new CashDispenser();
        DepositSlot depositSlot1 = new DepositSlot();
        Atm atm1 = new Atm("SECOND",cashDispenser1 , depositSlot1);
        atm.run();
        atm1.run();
    }
}
