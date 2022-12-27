package BankingAppProject3;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Checking {
    private double balance;
    private double transaction;
    public Checking() {
        this.balance = 0;
    }




    public void getBalance() {
        System.out.println("Balance: " +this.balance);
    }

    public void makeDeposit(double amount) {
        if (amount >0) {
            this.balance += amount;
            transaction = amount;
        }
        else {
            System.out.println("You entered a negative amount. Please type d again and enter a positive amount to deposit");
        }
    }

    public void makeWithdrawal(double amount) {
        if (this.balance - amount > 0 && amount>0) {
            this.balance -= amount;
            transaction = -amount;
        }
        else {
            System.out.println("You don't have enough to withdraw $" + amount + ". Your balance is " + this.balance);
        }
    }

    public void viewPreviousTransaction() {
        if (transaction >0) {
            System.out.println("Deposited: " + transaction);
        }
        else if (transaction < 0) {
            System.out.println("Withdrawn: " + Math.abs(transaction));
        }
        else {
            System.out.println("No transaction");
        }
    }

    public void calculateInterest(int years) {
        if (years < 1) {
            System.out.println("You entered a number of years less than 1 so balance is the same");
        }
        System.out.println("You balance " + years + " years from now is: " + (this.balance + (this.balance * 0.02 * years)));
    }

    //    double calculateTimeSinceAccountOpened() {
//        Calendar today = new GregorianCalendar();
//        today.setTime(new Date());
//        Calendar currentDate = today;
//        int yearsDiff = currentDate.get(Calendar.YEAR) - openedAccountDate.get(Calendar.YEAR);
//        int monthsDiff = currentDate.get(Calendar.MONTH) - openedAccountDate.get(Calendar.MONTH);
//        double yearsAndMonthsDiff = yearsDiff + monthsDiff / 12;
//        return yearsAndMonthsDiff;
//
//    }
}

