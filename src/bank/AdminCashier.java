package bank;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminCashier {
    private static float bankBalance = 10000000.0f;
    private static ArrayList<AdminLog> adminLogList ;
    //private static AdminCashier instance;


    public float getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(float bankBalance) {
        this.bankBalance = bankBalance;
    }

    public static void creditBankBalance(float amount)
    {
        bankBalance += amount ;
    }

    public static void debitBankBalance(float amount)
    {
        bankBalance -= amount ;
    }

    public ArrayList<AdminLog> getAdminLogList() {
        return adminLogList;
    }

    public void setAdminLogList(ArrayList<AdminLog> adminLogList) {
        this.adminLogList = adminLogList;
    }

    public static void addAdminLog(AdminLog adminLog)
    {
        if(adminLogList == null)
            adminLogList = new ArrayList<>();
        adminLogList.add(adminLog);
    }

    public static void printAdminLog()
    {
        try {
            for (AdminLog adminLog : adminLogList) {
                System.out.println(adminLog);
            }
        }
        catch (NullPointerException nullPointerException)
        {
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
    }

    public static boolean balanceCheck(int amount)
    {
        if(bankBalance - amount >= 0)
        {
            return true;
        }
        return false;
    }

    /*public static AdminCashier getInstance()
    {
        if(instance == null)
        {
            instance = new AdminCashier();
        }
        return instance;
    }

     */

}
