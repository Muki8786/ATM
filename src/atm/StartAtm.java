package atm;

public class StartAtm {
    public static void main(String args[])
    {
        Atm atm = Atm.getInstance();

        atm.run();
    }
}
