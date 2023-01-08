package BankingAppProject4;

import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        // Ask user for userID and pin to make the account
        System.out.println("Enter what userID to use for this account");
        String makeUserID = sc.nextLine();
        System.out.println("Enter four digit pin for your account");
        int makePin = Integer.valueOf(sc.nextLine());
        // initial deposit is prompted which will be added automatically to the checking account
        System.out.println("How much money would you like to start your account with");
        int initialDeposit = Integer.valueOf(sc.nextLine());

        // Make the account using the account class
        Account account = new Account(makeUserID, makePin, initialDeposit);

        // pass the account instance into the user class
        User user = new User(account);

        // Access account and verify if pin and userID match
        System.out.println("Enter your userID to access your account");
        String userID = sc.nextLine();
        System.out.println("Enter your pin ");
        int pin = Integer.valueOf(sc.nextLine());


        //
        int n = 0;
        boolean valid = false;
        while (n < 5) {
            if (user.validateAccount(userID, pin)) {
                System.out.println("Successfully logged in");
                valid = true;
                break;
            }
            System.out.println("Your pin is incorrect you have " + (4-n) + " attempt(s) left");
            System.out.println("Enter your pin ");
            pin = Integer.valueOf(sc.nextLine());
            n++;
            // add some logic to prevent code from continuing
            if (n == 5) {
                System.out.println("You have reached the max number of attempts. Your account will be locked for 30 seconds.");
                for(int i = 0; i <30;i++) {
                    System.out.println((30 - i) + " seconds left");
                    TimeUnit.SECONDS.sleep(1);
                }
                n = 0;
            }


        }



        Transaction transaction = new Transaction();
        Bank bank = new Bank(account, transaction);
        ATM atm = new ATM(account, transaction);

        // Prompt for the main menu

        while (true) {

            System.out.println("What would you like to do today?" +
                    "\n 1 Show account transactions" +
                    "\n 2 Withdraw" +
                    "\n 3 Deposit " +
                    "\n 4 Transfer money from checking to savings account" +
                    "\n 5 Transfer money from savings to checking account" +
                    "\n 6 Quit");



            String choice = sc.nextLine();
            if (choice.equals("1")) {
                System.out.println("Transactions: " + transaction);;
            }
            if (choice.equals("2")) {
                System.out.println("How much would you like to withdraw?");
                int amount = Integer.valueOf(sc.nextLine());
                if (atm.withdrawMoney(amount)) {
                    System.out.println("Success! \n" + account);
                }
            }
            if (choice.equals("3")) {
                System.out.println("How much would you like to deposit?");
                int amount = Integer.valueOf(sc.nextLine());
                if (atm.depositMoney(amount)) {
                    System.out.println("Success! \n" + account);
                }
            }
            if (choice.equals("4")) {
                System.out.println("How mmuch do you want to transfer?");
                int amount = Integer.valueOf(sc.nextLine());
                bank.transferMoneyCheckingToSavings(amount);

            }
            if (choice.equals("5")) {
                System.out.println("How much do you want to transfer?");
                int amount = Integer.valueOf(sc.nextLine());
                bank.transferMoneySavingsToChecking(amount);
            }
            if (choice.equals("6")) {
                break;
            }


        }

        System.out.println("Thank you for using our application. Have a great day!");

    }
}
