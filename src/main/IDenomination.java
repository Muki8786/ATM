package main;

public interface IDenomination {
    void addAllDenominations(int hun, int twoHun, int fiveHun, int twoThous);

    int getCount(int key);

    void setCount(int key, int value);

    void printMap();

    int getMaxCount();
}
