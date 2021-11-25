package main;

import java.util.HashMap;

public class Denomination implements IDenomination {

    private static final int maxCount = 100;
    private HashMap<Integer,Integer> denominationCount ;

    public Denomination(int count)
    {
        denominationCount = new HashMap<>();
        denominationCount.put(100,count);
        denominationCount.put(200,count);
        denominationCount.put(500,count);
        denominationCount.put(2000,count);
    }

    @Override
    public void addAllDenominations(int hun, int twoHun, int fiveHun, int twoThous)
    {
        denominationCount.put(100,hun);
        denominationCount.put(200,twoHun);
        denominationCount.put(500,fiveHun);
        denominationCount.put(2000,twoThous);
    }

    @Override
    public int getCount(int key)
    {
        return denominationCount.get(key);
    }

    @Override
    public void setCount(int key, int value)
    {
        denominationCount.replace(key,value);
    }

    @Override
    public void printMap()
    {
        System.out.println();
        System.out.println(denominationCount);
    }

    @Override
    public int getMaxCount()
    {
        return maxCount;
    }

}
