package LB4t2;

public class Main {

    public static void main(String[] args) {
        String[] rollnumbers = new String[10];

        for (int i = 0; i < 10; i++) {
            rollnumbers[i] = String.format("2022-SE-%03d", i + 1);
        }

        String[] birthdates = {
            "20-Oct", "30-April", "28-May", "15-June",
            "10-Jan", "5-Feb", "12-Mar", "8-Dec",
            "22-Jul", "1-Aug"
        };

        Thread rollNumberThread = new Thread(() -> {
            System.out.println("-------------------------------------------------");
            System.out.println("| Roll Number       | Date of Birth     |");
            System.out.println("-------------------------------------------------");
            for (int i = 0; i < rollnumbers.length; i++) {
                System.out.printf("| %-17s | %-15s |\n", rollnumbers[i], birthdates[i]);
            }
            System.out.println("-------------------------------------------------");
        });

        rollNumberThread.start();

        try {
            rollNumberThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}