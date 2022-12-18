package EmailBusinessProject1;
import java.util.Scanner;

public class EmailBusinessProject1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first name: ");
        String fName = sc.nextLine();
        System.out.println("Enter last name: ");
        String lName = sc.nextLine();
        System.out.println("Enter your department: ");
        String dept = sc.nextLine();

        Employee emp = new Employee(fName, lName, dept);

        System.out.println("Hi " + emp.displayName());
        emp.setEmail();
        System.out.println("Your email is: " + emp.displayEmail());
        System.out.println("You temporary password is " + emp.getPassword());
        System.out.println("Your mailbox capacity is: " + emp.displayMailboxCapacity());

        System.out.println("Do you want to reset your password? (y/n)");
        if (sc.nextLine().equals("y")) {
            System.out.println("Type your new password: ");
            emp.setPassword(sc.nextLine());
        }
        System.out.println("Your new password was changed to: " + emp.getPassword());
    }
}

interface PasswordInterface {
    String genPass();
}

class Employee {
    private String firstName;
    private String lastName;
    private String department;
    private String password;
    private String email;
    private int mailboxCapacity = 500;
    Employee(String firstName, String lastName, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.password = generatePassword.genPass();
    }

    PasswordInterface generatePassword = () -> {
        String alphanumerics = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789" + "abcdefghijklmnopqrstuvwxyz";

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<10; i++) {
            double index = Math.random() * 62;

            int passwordIndex = (int)index;
            sb.append(alphanumerics.charAt(passwordIndex));
        }
        return String.valueOf(sb);
    };
    void setEmail(){
        this.email = this.firstName.toLowerCase() + this.lastName.toLowerCase() + "@" + this.department.toLowerCase() + "." + "coolbusiness.org";

    }
    void setPassword(String newPass) {
        this.password = newPass;
    }


    String getPassword() {
        return this.password;
    }

    String displayName() {
        return this.firstName + " " + this.lastName;
    }

    String displayEmail() {
        return this.email;
    }

    int displayMailboxCapacity() {
        return this.mailboxCapacity;
    }

}


