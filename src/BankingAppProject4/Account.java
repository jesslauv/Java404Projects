package BankingAppProject4;

public class Account {
    // view balance
    private int pinNumber;
    private String userID;

    private int checkingAccountBalance;
    private int savingsAccountBalance;
    Account(String userID, int pinNumber, int initialDeposit) {
        this.userID = userID;
        this.pinNumber = pinNumber;
        this.checkingAccountBalance = initialDeposit;
    }

    public String getUserID() {
        return this.userID;
    }
    public int getPinNumber() {
        return this.pinNumber;
    }

    public void setCheckingAccountBalance(int amount) {
        this.checkingAccountBalance = amount;
    }

    public void setSavingAccountBalance(int amount) {
        this.savingsAccountBalance = amount;
    }


    public int getCheckingAccountBalance() {
        return this.checkingAccountBalance ;
    }

    public int getSavingAccountBalance() {
        return this.savingsAccountBalance;
    }

    public String toString() {
        return  "Savings Account: " + savingsAccountBalance +
                "\n Checking Account: " + checkingAccountBalance;
    }



}
