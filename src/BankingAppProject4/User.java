package BankingAppProject4;

public class User {
    // username id and pin
    // where the main method is
    // instantiate an instance of Account
    private Account account;

    User(Account account) {
        this.account = account;
    }

    public boolean validateAccount(String inputID, int inputPin) {
        if (account.getUserID().equals(inputID)) {
            if (account.getPinNumber() == inputPin) {
                return true;
            }
            return false;
        }
        else {
            System.out.println("That is not a valid userID");
            return false;
        }
    }


}
