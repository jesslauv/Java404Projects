package BankingAppProject4;

import java.util.ArrayList;

public class Transaction {

    ArrayList<String> transactions = new ArrayList<>();
    // method adds transactions to an arraylist

    public void addTransaction(String transactionType , int amount) {
        if (transactionType.equals("w")) {
            transactions.add("Withdrew: " + amount);
        }
        else if (transactionType.equals("d")) {
            transactions.add("Deposited: " + amount);
        }
        else if (transactionType.equals("t")) {
            transactions.add("Transfered: " + amount);
        }
    }
    // print all transactions
    public String toString() {
        return transactions.toString();
    }

}
