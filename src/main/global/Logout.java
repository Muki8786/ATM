package main.global;

import sdk.UI.ILogout;

public class Logout implements ILogout {
    @Override
    public void logout()
    {
        System.out.println("\n\t\tThank you for using our ATM");
        System.out.println("\t\tPlease sanitize your hands before leaving :) \n\n");
    }
}
