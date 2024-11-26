import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    public static final String CHECKING = "CHECKING";
    public static final String SAVINGS = "SAVINGS";
    protected long accountNumber;
    protected double balance;
    protected List<Transaction> transactionList;

    public Account() {
        accountNumber = 0;
        balance = 0;
        transactionList = new ArrayList<>();
    }

    /**
     * Constructor.
     */
    public Account(long accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        transactionList = new ArrayList<>();
    }

    //getter
    public long getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * rut tien.
     */
    public void doWithdrawing(double amount) throws InsufficientFundsException,
            InvalidFundingAmountException {
        if (balance < amount) {
            throw new InvalidFundingAmountException(amount);
        } else if (amount < 0) {
            throw new InsufficientFundsException(amount);
        } else {
            balance -= amount;
        }
    }

    /**
     * nap tien.
     */
    public void doDepositing(double amount) throws InvalidFundingAmountException {
        if (amount < 0) {
            throw new InvalidFundingAmountException(amount);
        } else {
            balance += amount;
        }
    }

    public abstract void withdraw(double amount);

    public abstract void deposit(double amount);

    /**
     * getTransactionHistory.
     */
    public String getTransactionHistory() {
        StringBuilder his = new StringBuilder();
        his.append("Lịch sử giao dịch của tài khoản ").append(accountNumber).append(":\n");
        for (Transaction i : transactionList) {
            his.append("-Kiểu giao dịch: ").append(i.getTransactionSummary()).append("\n");
        }

        return his.toString();
    }

    /**
     * addTransaction.
     */
    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    /**
     * equals.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account other = (Account) obj;
            return other.accountNumber == accountNumber;
        }
        return false;
    }
}
