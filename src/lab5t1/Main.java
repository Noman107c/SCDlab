package lab5t1;

public class Main{
public static void main(String[] args) {
    // Create and start threads for each alphabet
    Thread[] threads = new Thread[26];
    
    // Create and start a thread for each letter from A to Z
    for (char ch = 'A'; ch <= 'Z'; ch++) {
        threads[ch - 'A'] = new AlphabetThread(ch);
        threads[ch - 'A'].start();
    }

    // Allow threads to complete
    for (int i = 0; i < 26; i++) {
        try {
        	AlphabetThread at = new AlphabetThread('k');
        	at.start();
            threads[i].join(); // Ensure the main thread waits for the alphabet threads to finish
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted: " + e.getMessage());
        }
    }
}
}