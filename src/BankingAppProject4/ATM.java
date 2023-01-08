package BankingAppProject4;

public class ATM {
    // withdraw and deposit
    private Account account;
    private final Transaction transaction;

    // passing transaction and account class into ATM
    public ATM(Account account, Transaction transaction) {
        this.account = account;
        this.transaction = transaction;

    }

    // comparing money from account class to update the balance in the checking account
    public boolean withdrawMoney(int amount) {
        if (amount > 0) {
            if (account.getCheckingAccountBalance() > amount) {
                account.setCheckingAccountBalance(account.getCheckingAccountBalance() - amount);
                // add to transaction list
                transaction.addTransaction("w", amount);
                return true;
            }
            System.out.println("You do not have enough money to withdraw that much.");
            return false;
        }
        System.out.println("You entered a negative amount. Choose deposit and try again");
        return false;
    }

    public boolean depositMoney(int amount) {
        if (amount > 0) {
            account.setCheckingAccountBalance(account.getCheckingAccountBalance() + amount);

            // add to transaction list
            transaction.addTransaction("d", amount);
            return true;
        }
        System.out.println("You entered a negative amount. Choose deposit and try again");
        return false;
    }

}
