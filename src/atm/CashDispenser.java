package atm;

public class CashDispenser {
    private static final int initialBalance = 280000;
    private int balance;
    private Denomination denomination;

    public CashDispenser()
    {
        denomination = new Denomination(100);
        balance = initialBalance;
    }

    public void dispenseCash(int amount , int hunCount , int twoHunCount , int fiveHunCount ,int twoThousCount )//amount should be divisible by 100 || 200 || 500 || 2000
    {
        balance -= amount;
        denomination.addAllDenominations(hunCount , twoHunCount , fiveHunCount , twoThousCount);
        //denomination.printMap();
        System.out.println("Please collect the cash");
    }

    public boolean sufficientATMBalanceCheck(int amount)
    {
        //denomination.printMap();
        if(amount <= balance && (amount % 100 == 0) )
        {
            return withdrawWithCount(amount);
        }
        else if(amount > balance)
        {
            System.out.println("\n\t\tInsufficient Funds!");
            return false;
        }
        else if(amount % 100 != 0)
        {
            System.out.println("\n\t\tSorry! Enter a multiple of 100");
            return false;
        }
        else
        {
            return false;
        }
    }

    public int getAmount()
    {
        int amount = initialBalance-balance;
        System.out.println("\nThe amount needed to be full is Rs."+ amount);
        return amount;
    }

    public void insertIntoDispenser(int amount , int hun , int twoHun, int fiveHun , int twoThous)
    {
        balance += amount;
        denomination.addAllDenominations(hun,twoHun,fiveHun,twoThous);
        //denomination.printMap();
    }

    public boolean withdrawWithCount(int amount)
    {
        int hun = denomination.getCount(100);
        int twoHun = denomination.getCount(200);
        int fiveHun = denomination.getCount(500);
        int twoThous = denomination.getCount(2000);

        int hunCount =0;
        int twoHunCount =0;
        int fiveHunCount = 0;
        int twoThousCount = 0;

        boolean flag = true;
        int amount1 = amount;
        while(amount>0)
        {
            if((amount>2000 && twoThous>0) || (amount==2000 && fiveHun<4 && twoHun < 10 && hun < 20 && twoThous>0))
            {
                amount -= 2000;
                twoThous--;
                twoThousCount++;
            }
            else if((amount > 500 && fiveHun > 0) || (amount == 500 && (twoHun < 2 && hun < 1) && fiveHun>0))
            {
                amount -= 500;
                fiveHun--;
                fiveHunCount++;
            }
            else if((amount >200 && twoHun>0) || (amount == 200 && (hun < 2) && twoHun>0))
            {
                amount -= 200;
                twoHun--;
                twoHunCount++;
            }
            else if(amount >=100 && hun>0)
            {
                amount -= 100;
                hun--;
                hunCount++;
            }
            else
            {
                break;
            }

        }
        if(amount == 0)
        {
            dispenseCash(amount1 ,denomination.getCount(100) - hunCount ,
                    denomination.getCount(200) - twoHunCount ,
                    denomination.getCount(500) - fiveHunCount ,
                    denomination.getCount(2000) - twoThousCount );
            return true;
        }
        else
        {
            System.out.println("\n\t\tSorry! Insufficient funds in the ATM");
            return false ;
        }
    }

    public void denominationsAvailable()
    {
        int hun = denomination.getCount(100);
        int twoHun = denomination.getCount(200);
        int fiveHun = denomination.getCount(500);
        int twoThous = denomination.getCount(2000);

        System.out.println("\n\t\tDenominations Available : ");
        if(twoThous>0)
        {
            System.out.println("2000");
        }
        if(fiveHun>0)
        {
            System.out.println("500");
        }
        if(twoHun>0)
        {
            System.out.println("200");
        }
        if(hun>0)
        {
            System.out.println("100");
        }
        System.out.println();
    }

    public int getBalance()
    {
        return balance;
    }

    public int denominationCountNeeded(int key)
    {
        return 100 - denomination.getCount(key);
    }

    public boolean denominationsCheck(int key , int count)
    {
        return (count <= denominationCountNeeded(key));
    }

}
