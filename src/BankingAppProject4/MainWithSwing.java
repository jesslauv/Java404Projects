package BankingAppProject4;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class MainWithSwing {
    public static void main(String[] args) {

        // make account
        Scanner sc = new Scanner(System.in);
        // Ask user for userID and pin to make the account
        System.out.println("Enter what userID to use for this account");
        String makeUserID = sc.nextLine();
        System.out.println("Enter four digit pin for your account");
        int makePin = Integer.valueOf(sc.nextLine());
        // initial deposit is prompted which will be added automatically to the checking account
        System.out.println("How much money would you like to start your account with");
        int initialDeposit = Integer.valueOf(sc.nextLine());

        Account account = new Account(makeUserID, makePin, initialDeposit);
        User user = new User(account);
        Transaction transaction = new Transaction();
        Bank bank = new Bank(account, transaction);
        ATM atm = new ATM(account, transaction);



        JFrame jframe = new JFrame("ATM");
        jframe.setSize(600,600);
        jframe.setLayout(new BorderLayout());
        JPanel container = new JPanel();
        container.setLayout(new GridLayout(2,2));
        jframe.add(container);


        // create panels


        JPanel panel1 = new JPanel();
        panel1.setBounds(0, 0, 300, 300 );
        panel1.setBackground(Color.pink);
        panel1.setBorder(new EmptyBorder(10,10,10,10));
        JPanel panel2 = new JPanel();
        panel2.setBounds(300, 0, 300, 300 );
        panel2.setBackground(Color.lightGray);
        panel2.setBorder(new EmptyBorder(10,10,10,10));
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.orange);
        panel3.setBounds(0, 300, 600, 300 );
        panel3.setBorder(new EmptyBorder(10,10,10,10));



//        JLabel text = new JLabel("Log In");
//        panel1.add(text, "span", 0);
        panel1.setLayout(new GridLayout(6, 1));
        JLabel logIn = new JLabel("Log In");
        logIn.setFont(new Font("Log In", 1, 30));
        panel1.add(logIn);



        // username label and box
        JLabel usernameLabel = new JLabel("Username");
        panel1.add(usernameLabel);
        JTextField userText = new JTextField(10);
        panel1.add(userText);
        // password label and box
        JLabel passLabel = new JLabel("Pin");
        panel1.add(passLabel);
        JTextField pinText = new JTextField(10);
        panel1.add(pinText);

        JButton submitPin = new JButton("Submit");
//        submit.setSize(50, 50);
        panel1.add(submitPin);

        // second panel --> has the description and withdraw and deposit boxes
        // username label and box
        panel2.setLayout(new GridLayout(7,1));

        JPanel panel2a = new JPanel();
        panel2a.setLayout(new GridLayout(1,3));

        JLabel withdrawLabel = new JLabel("Withdraw");
        panel2a.add(withdrawLabel);
        JTextField withdrawText = new JTextField(10);
        panel2a.add(withdrawText);
        JButton withdrawButton = new JButton("Withdraw");
        panel2a.add(withdrawButton);

        panel2.add(panel2a);


        JPanel panel2b = new JPanel();
        panel2b.setLayout(new GridLayout(1,3));

        JLabel depositLabel = new JLabel("Deposit");
        panel2b.add(depositLabel);
        JTextField depositText = new JTextField(10);
        panel2b.add(depositText);
        JButton depositButton = new JButton("Deposit");
        panel2b.add(depositButton);
        panel2.add(panel2b);

        JPanel panel2c = new JPanel();
        panel2c.setLayout(new GridLayout(1,3));
        JLabel transferFromSavingsLabel = new JLabel("Transfer from \n Savings to Checking Account");
        panel2c.add(transferFromSavingsLabel,"wrap");
        JTextField transferFromSavingsText = new JTextField(10);
        panel2c.add(transferFromSavingsText);
        JButton transferFromSavingButton = new JButton("Transfer");
        panel2c.add(transferFromSavingButton);
        panel2.add(panel2c);

        JPanel panel2d = new JPanel();
        panel2d.setLayout(new GridLayout(1,3));

        JLabel transferFromCheckingLabel = new JLabel("Transfer from \n Checking to Savings Account");
        panel2d.add(transferFromCheckingLabel, "wrap");
        JTextField transferFromCheckingText = new JTextField(10);
        panel2d.add(transferFromCheckingText);
        JButton transferFromCheckingButton = new JButton("Transfer");
        panel2d.add(transferFromCheckingButton);
        panel2.add(panel2d);



        panel2.setVisible(false);
        panel3.setLayout(new GridLayout());
        JLabel info = new JLabel("<html>" +
                "Checking Account Balance: " + account.getCheckingAccountBalance()
        + "<br/> Savings Account Balance: " + account.getSavingAccountBalance() + "</html>");
        panel3.add(info);
        info.setFont(new Font("test", 1, 15));
        panel3.setVisible(false);



        // upon submit access account and verify if pin and userID match
        // add event listener on submit
        submitPin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String userID = userText.getText();
                System.out.println(userID);

                int pin = Integer.valueOf(pinText.getText());
                System.out.println(pin);
                if (user.validateAccount(userID, pin)) {
                    JOptionPane.showMessageDialog(jframe, "Success");
                    // make second panel visible
                    panel2.setVisible(true);
                    panel3.setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(jframe, "Try again - Incorrect Pin");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount= 0;
                try {
                    amount = Integer.valueOf(withdrawText.getText());
                }
                catch (Exception exc) {
                    JOptionPane.showMessageDialog(jframe, "Please enter valid withdrawal amount");
                }
                if (atm.withdrawMoney(amount)) {
                    JOptionPane.showMessageDialog(jframe,"Success! \n Here's $" + amount + " \n $_$");
                    info.setText("Checking Account Balance: " + account.getCheckingAccountBalance());
                }
                else {
                    JOptionPane.showMessageDialog(jframe,"Whoops! You do not have enough money! \n" + account);
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = 0;
                try {
                    amount = Integer.valueOf(depositText.getText());
                }
                catch (Exception exc) {
                    JOptionPane.showMessageDialog(jframe, "Please enter valid deposit amount");
                }

                if (atm.depositMoney(amount)) {
                    JOptionPane.showMessageDialog(jframe,"Success! \n We added $" + amount + " \n to your account");
                    info.setText("<html> Checking Account Balance: " + account.getCheckingAccountBalance() +
                             "<br/> Savings Account Balance: " + account.getSavingAccountBalance() + "</html>");
                }
                else {
                    JOptionPane.showMessageDialog(jframe,"You cannot add a negative amount \n" + account);
                }
            }
        });

        transferFromCheckingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = 0;
                try {
                    amount = Integer.valueOf(transferFromCheckingText.getText());
                }
                catch (Exception exc) {
                    JOptionPane.showMessageDialog(jframe, "Please enter valid amount");
                }

                if (account.getCheckingAccountBalance() >= amount) {
                    bank.transferMoneyCheckingToSavings(amount);
                    JOptionPane.showMessageDialog(jframe,"Success! \n We transferred $" + amount + " \n from account");
                    info.setText("<html> Checking Account Balance: " + account.getCheckingAccountBalance() +
                            "<br/> Savings Account Balance: " + account.getSavingAccountBalance() + "</html>");
                }
                else {
                    JOptionPane.showMessageDialog(jframe,"You don't have enough money in your checking account\n" + account);
                }
            }
        });

        transferFromSavingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = 0;
                try {
                    amount = Integer.valueOf(transferFromSavingsText.getText());
                }
                catch (Exception exc) {
                    JOptionPane.showMessageDialog(jframe, "Please enter valid amount");
                }

                if (account.getSavingAccountBalance() >= amount) {
                    bank.transferMoneySavingsToChecking(amount);
                    JOptionPane.showMessageDialog(jframe,"Success! \n We transferred $" + amount + " \n from account");
                    info.setText("<html> Checking Account Balance: " + account.getCheckingAccountBalance() +
                            "<br/> Savings Account Balance: " + account.getSavingAccountBalance() + "</html>");
                }
                else {
                    JOptionPane.showMessageDialog(jframe,"You don't have enough money in your savings account\n" + account);
                }

            }
        });



        container.add(panel1,BorderLayout.CENTER);
        container.add(panel2,BorderLayout.CENTER);
        container.add(panel3,BorderLayout.CENTER);

        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setVisible(true);

    }
}
