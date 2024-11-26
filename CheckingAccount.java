public class CheckingAccount extends Account {
    public CheckingAccount(long accountNumber, double balance) {
        super(accountNumber, balance);
    }

    /**
     * naptien.
     */
    public void deposit(double amount) {
        double initialBalance = balance;
        try {
            doDepositing(amount);
        } catch (InvalidFundingAmountException e) {
            System.out.println(e.getMessage());
            return;
        }
        addTransaction(new Transaction(Transaction.TYPE_DEPOSIT_CHECKING, amount,
                initialBalance, balance));
    }

    /**
     * ruttien.
     */
    public void withdraw(double amount) {
        double initialBalance = balance;
        try {
            doWithdrawing(amount);
        } catch (InvalidFundingAmountException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
            return;
        }
        addTransaction(new Transaction(Transaction.TYPE_WITHDRAW_CHECKING, amount,
                initialBalance, balance));
    }
}
