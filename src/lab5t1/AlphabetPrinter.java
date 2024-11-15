package lab5t1;
import java.util.Random;


	class AlphabetThread extends Thread {
	    private static final Random random = new Random();
	    private final char letter;

	    public AlphabetThread(char letter) {
	        this.letter = letter;
	    }

	    @Override
	    public void run() {
	        try {
	            // Random sleep time between 100 to 500 milliseconds
	            int sleepTime = random.nextInt(1000) + 100;
	            Thread.sleep(sleepTime);
	            
	            // Print the letter
	            System.out.print(letter + " ");
	        } catch (InterruptedException e) {
	            System.out.println("Thread interrupted: " + e.getMessage());
	        }

	    }
	}