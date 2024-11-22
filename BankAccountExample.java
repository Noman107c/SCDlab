package lab6;



class BankAccount {
    private double balance = 50000; // Initial account balance

    // Synchronized method to ensure thread safety during withdrawal
    public synchronized boolean withdraw(double amount) {
        // Check if sufficient funds are available
        if (balance >= amount) {
            // Simulate the withdrawal process
            System.out.println(Thread.currentThread().getName() + " is withdrawing: " + amount);
            balance -= amount; // Deduct the amount from the balance
            System.out.println(Thread.currentThread().getName() + " completed withdrawal. Current balance: " + balance);
            return true;
        } else {
            // If there are insufficient funds
            System.out.println(Thread.currentThread().getName() + " tried to withdraw " + amount + " but insufficient funds. Current balance: " + balance);
            return false;
        }
    }
}

class User implements Runnable {
    private BankAccount account;
    private double amountToWithdraw;

    // Constructor to initialize the bank account and withdrawal amount
    public User(BankAccount account, double amountToWithdraw) {
        this.account = account;
        this.amountToWithdraw = amountToWithdraw;
    }

    @Override
    public void run() {
        // User attempts to withdraw the specified amount
        account.withdraw(amountToWithdraw);
    }
}

public class BankAccountExample {
    public static void main(String[] args) {
        // Create a shared bank account
        BankAccount account = new BankAccount();

        // Create two user threads, one for User A and another for User B
        User userA = new User(account, 45000); // User A wants to withdraw $45,000
        User userB = new User(account, 20000); // User B wants to withdraw $20,000

        Thread threadA = new Thread(userA, "User A");
        Thread threadB = new Thread(userB, "User B");

        // Start both threads
        threadA.start();
        threadB.start();
    }
}
