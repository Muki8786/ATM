package bank;

public class AdminLog {
    private int sno;
    private String adminName;
    private String type;
    private float amount;
    private String atmName ;
    private static int count = 1;

    public AdminLog(String adminName , String atmName , String type , float amount)
    {
        this.sno = count++;
        this.adminName = adminName;
        this.atmName = atmName;
        this.type = type;
        this.amount = amount;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getAtmName() {
        return atmName;
    }

    public void setAtmName(String atmName) {
        this.atmName = atmName;
    }

    @Override
    public String toString() {
        return "AdminLog{" +
                "sno=" + sno +
                ", adminName='" + adminName + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", atmName='" + atmName + '\'' +
                '}';
    }
}
