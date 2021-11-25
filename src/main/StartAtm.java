package main;


import sdk.Atm;

public class StartAtm {

    private static Atm atm;

    public static void main(String args[])
    {

        atm = MyFactory.createMyAtm();
        Atm.setAtm(atm);
        atm.run();

        /*Atm atm1 = new Atm("SECOND ");
        Atm atm2 = new Atm("Three");
        atm1.run();
        atm2.run();
         */

        //AdminCashier.printAdminLog();
    }

    public static void restart()
    {
        atm.run();
    }
}
