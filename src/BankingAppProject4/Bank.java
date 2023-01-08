package BankingAppProject4;

public class Bank {
    // transfer
    // grow money with something with interest
    private int checkingAccountBalance;
    private int savingsAccountBalance;
    private Account account;
    private double interest;
    private Transaction transaction;
    Bank(Account account, Transaction transaction) {
        this.account = account;
        this.interest = 0.02;
        this.transaction = transaction;
    }

     void transferMoneySavingsToChecking(int amount) {
        if (amount<0) {
            System.out.println("You entered a negative amount which is not valid");
            return;
        }
        if (account.getSavingAccountBalance() > amount) {
            account.setCheckingAccountBalance(account.getCheckingAccountBalance() + amount);
            account.setSavingAccountBalance(account.getSavingAccountBalance() - amount);


            // since success, add to transaction list
            transaction.addTransaction("t", amount);
        }
    }

     void transferMoneyCheckingToSavings(int amount) {
        if (amount<0) {
            System.out.println("You entered a negative amount which is not valid");
            return;
        }
        if (account.getCheckingAccountBalance() > amount) {
            account.setCheckingAccountBalance(account.getCheckingAccountBalance() - amount);
            account.setSavingAccountBalance(account.getSavingAccountBalance() + amount);

            // since success, add to transaction list
            transaction.addTransaction("t", amount);

        }
    }



//    public int getCheckingAccountBalance() {
//        return checkingAccountBalance;
//    }
//
//    public int getSavingsAccountBalance() {
//        return savingsAccountBalance;
//    }

    //


}
