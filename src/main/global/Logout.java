package main.global;

import main.StartAtm;

public class Logout {
    public static void logout()
    {
        System.out.println("\n\t\tThank you for using our ATM");
        System.out.println("\t\tPlease sanitize your hands before leaving :) \n\n\n");
        StartAtm.restart();
    }
}
