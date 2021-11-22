package sdk.adminTransactions;

public class DenominationsCount {
    private static int depositAmount=0;
    private static int hunCount=0;
    private static int twoHunCount=0;
    private static int fiveHunCount=0;
    private static int twoThousCount=0;

    static void setAllCountAndAmount(int depositAmount ,int hunCount , int twoHunCount , int fiveHunCount , int twoThousCount)
    {
        DenominationsCount.depositAmount = depositAmount;
        DenominationsCount.hunCount = hunCount;
        DenominationsCount.twoHunCount = twoHunCount;
        DenominationsCount.fiveHunCount = fiveHunCount;
        DenominationsCount.twoThousCount = twoThousCount;
    }

    static int getHunCount()
    {
        return hunCount;
    }
    static int getTwoHunCount()
    {
        return twoHunCount;
    }
    static int getFiveHunCount()
    {
        return fiveHunCount;
    }
    static int getTwoThousCount()
    {
        return twoThousCount;
    }
    static int getDepositAmount()
    {
        return depositAmount;
    }

    static void setZero()
    {
        DenominationsCount.depositAmount = 0;
        DenominationsCount.hunCount = 0;
        DenominationsCount.twoHunCount = 0;
        DenominationsCount.fiveHunCount = 0;
        DenominationsCount.twoThousCount = 0;
    }
}
