public class Transaction {
    public static final int TYPE_DEPOSIT_CHECKING = 0;
    public static final int TYPE_WITHDRAW_CHECKING = 1;
    public static final int TYPE_DEPOSIT_SAVINGS = 2;
    public static final int TYPE_WITHDRAW_SAVINGS = 3;

    private int type;
    private double amount;
    private double initialBalance;
    private double finalBalance;

    /**
     * Constructor.
     */
    public Transaction(int type, double amount,
                       double initialBalance, double finalBalance) {
        this.type = type;
        this.amount = amount;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }

    /**
     * gettype.
     */
    private String getTransactionTypeString(int type) {
        switch (type) {
            case 0:
                return "Nạp tiền vãng lai";
            case 1:
                return "Rút tiền vãng lai";
            case 2:
                return "Nạp tiền tiết kiệm";
            case 3:
                return "Rút tiền tiết kiêm";
            default:
                return "";
        }
    }

    /**
     * summary.
     */
    public String getTransactionSummary() {
        return "Kiểu giao dịch: " + getTransactionTypeString(type) + ". "
                + "Số dư ban đầu: $" + String.format("%.2f", initialBalance) + ". "
                + "Số dư cuối: $" + String.format("%.2f", finalBalance) + ".";
    }
}
