import atm.Atm;
import bank.AdminCashier;

public class StartAtm {
    public static void main(String args[])
    {
        Atm atm = new Atm("FIRST ");
        Atm atm1 = new Atm("SECOND ");
        atm.run();
        atm.run();

        atm1.run();
        atm1.run();

        AdminCashier.printAdminLog();
    }
}
