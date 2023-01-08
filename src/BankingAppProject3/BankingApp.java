package BankingAppProject3;

import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Checking check = new Checking();

        while(true) {
            System.out.println("What would you like to do today? \n Withdraw money (Type w)" +
                    "\n Deposit money (Type d)" +
                    "\n View previous transaction (Type pt)" +
                    "\n Check balance (Type b)" +
                    "\n Check balance in future with interest accrued (type fb)" +
                    "\n Quit (Type q)");
            String input = scanner.nextLine();
            if (input.equals("q")) {
                break;
            }
            else if (input.equals("w")) {
                System.out.println("How much would you like to withdraw?");
                double amount = Double.valueOf(scanner.nextLine());
                check.makeWithdrawal(amount);
            }
            else if (input.equals("d")) {
                System.out.println("How much would you like to deposit?");
                double amount = Double.valueOf(scanner.nextLine());
                check.makeDeposit(amount);
            }
            else if (input.equals("pt")) {
                check.viewPreviousTransaction();
            }
            else if (input.equals("b")) {
                check.getBalance();
            }
            else if (input.equals("fb")) {
                System.out.println("You want to check your balance for how many years in the future?");
                int years = Integer.valueOf(scanner.nextLine());
                check.calculateInterest(years);
            }

        }
        System.out.println("Thanks for using this banking app. Goodbye!");

    }

}


