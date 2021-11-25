package main.depositSlot;

import main.IDenomination;
import sdk.UI.IDepositSlot;

public class DepositSlot implements IDepositSlot {
    public static int depositBalance;
    private static final int depositCapacity = 280000;
    private IDenomination denomination;
    private static DepositSlot depositSlot;

    private DepositSlot(IDenomination denomination)
    {
        depositBalance = 0;
        this.denomination = denomination;
    }

    @Override
    public void acceptCash(int amount, int hun, int twoHun, int fiveHun, int twoThous)
    {
        depositBalance += amount;
        denomination.addAllDenominations(hun+ denomination.getCount(100),
                twoHun + denomination.getCount(200),
                fiveHun + denomination.getCount(500),
                twoThous + denomination.getCount(2000));
        denomination.printMap();
    }

    @Override
    public boolean depositCapacityCheck(int amount, int hun, int twoHun, int fiveHun, int twoThous)
    {
        if(depositBalance == depositCapacity)
        {
            System.out.println("\n\t\tSorry! ATM cannot accept cash ");
            return false;
        }
        else if(amount + depositBalance > depositCapacity)
        {
            System.out.println("\n\t\tDeposit smaller amount");
            return false;
        }
        else if(denomination.getCount(100) == denomination.getMaxCount())
        {
            System.out.println("\n\t\tSorry! ATM cannot accept Rs.100 notes");
            return false;
        }
        else if(hun + denomination.getCount(100) > denomination.getMaxCount())
        {
            System.out.println("\n\t\tEnter lesser 100 notes ");
            return false;
        }
        else if(denomination.getCount(200) == denomination.getMaxCount())
        {
            System.out.println("\n\t\tSorry! ATM cannot accept Rs.200 notes");
            return false;
        }
        else if(twoHun + denomination.getCount(200) > denomination.getMaxCount())
        {
            System.out.println("\n\t\tEnter lesser 200 notes ");
            return false;
        }
        else if(denomination.getCount(500) == denomination.getMaxCount())
        {
            System.out.println("\n\t\tSorry! ATM cannot accept Rs.500 notes");
            return false;
        }
        else if(fiveHun + denomination.getCount(500) > denomination.getMaxCount())
        {
            System.out.println("\n\t\tEnter lesser 500 notes ");
            return false;
        }
        else if(denomination.getCount(2000) == denomination.getMaxCount())
        {
            System.out.println("\n\t\tSorry! ATM cannot accept Rs.2000 notes");
            return false;
        }
        else if(twoThous + denomination.getCount(2000) > denomination.getMaxCount())
        {
            System.out.println("\n\t\tEnter lesser 2000 notes ");
            return false;
        }
        else
        {
            /*acceptCash(amount , hun+ denomination.getCount(100),
                    twoHun + denomination.getCount(200),
                    fiveHun + denomination.getCount(500),
                    twoThous + denomination.getCount(2000));

             */
            return true;
        }
    }

    @Override
    public void withdrawFromDepositSlot()
    {
        depositBalance = 0;
        denomination.addAllDenominations(0,0,0,0);
    }

    @Override
    public int getAmount()
    {
        int amount = depositBalance ;
        return amount;
    }

    @Override
    public int getDenominationCount(int key)
    {
        return denomination.getCount(key);
    }

    @Override
    public void allDenominations(int hun, int twoHun, int fiveHun, int twoThous)
    {
        denomination.addAllDenominations(hun, twoHun, fiveHun, twoThous);
        //denomination.printMap();
    }

    public static DepositSlot getInstance(IDenomination denomination) {
        if (DepositSlot.depositSlot == null)
            DepositSlot.depositSlot = new DepositSlot(denomination);
        return DepositSlot.depositSlot;
    }

}
