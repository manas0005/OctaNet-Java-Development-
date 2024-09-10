import java.util.ArrayList;
import java.util.Scanner;

public class ATMSimulation {

    private double balance;
    private int pin;
    private ArrayList<String> transactionHistory;

    public ATMSimulation(double initialBalance, int initialPin) {
        this.balance = initialBalance;
        this.pin = initialPin;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account opened with balance: $" + initialBalance);
    }

    public void checkBalance() {
        System.out.println("Your current balance is: $" + balance);
        transactionHistory.add("Checked balance: $" + balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $" + amount);
            transactionHistory.add("Deposited: $" + amount);
            checkBalance();
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew: $" + amount);
            transactionHistory.add("Withdrew: $" + amount);
            checkBalance();
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void changePin(int newPin) {
        if (newPin >= 1000 && newPin <= 9999) {
            pin = newPin;
            System.out.println("PIN successfully changed.");
            transactionHistory.add("PIN changed.");
        } else {
            System.out.println("Invalid PIN. Please enter a 4-digit number.");
        }
    }

    public boolean validatePin(int enteredPin) {
        return enteredPin == pin;
    }

    public void displayTransactionHistory() {
        System.out.println("\nTransaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATMSimulation atm = new ATMSimulation(1000.0, 1234); // Initialize ATM with $1000 and PIN 1234

        System.out.print("Enter your PIN: ");
        int enteredPin = scanner.nextInt();

        if (atm.validatePin(enteredPin)) {
            int choice;
            do {
                
                System.out.println("\nATM Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Change PIN");
                System.out.println("5. Transaction History");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        atm.checkBalance();
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: $");
                        double withdrawalAmount = scanner.nextDouble();
                        atm.withdraw(withdrawalAmount);
                        break;
                    case 4:
                        System.out.print("Enter new PIN: ");
                        int newPin = scanner.nextInt();
                        atm.changePin(newPin);
                        break;
                    case 5:
                        atm.displayTransactionHistory();
                        break;
                    case 6:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                }
            } while (choice != 6);
        } else {
            System.out.println("Invalid PIN. Access denied.");
        }

        scanner.close();
    }
}