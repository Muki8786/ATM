import atm.Atm;
import atm.CashDispenser;
import atm.DepositSlot;

public class StartAtm {
    public static void main(String args[])
    {

        Atm atm = new Atm("FIRST ");
        Atm atm1 = new Atm("SECOND ");
        atm.run();
        atm1.run();
    }
}
