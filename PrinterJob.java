package lab6t2;

class PrinterTray {
    private int totalPages;

    // Constructor to initialize the total pages in the printer tray
    public PrinterTray(int initialPages) {
        this.totalPages = initialPages;
    }

    // Method to print pages
    public synchronized void printPages(int pagesToPrint) {
        System.out.println("Requested to print: " + pagesToPrint + " pages.");
        
        // Check if there are enough pages
        while (totalPages < pagesToPrint) {
            try {
                System.out.println("Not enough pages in tray. Waiting for more pages...");
                wait();  // Wait for enough pages to be added
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // If enough pages are available, print the pages
        totalPages -= pagesToPrint;
        System.out.println("Printing " + pagesToPrint + " pages. Pages left in tray: " + totalPages);
    }

    // Method to add pages to the printer tray
    public synchronized void addPages(int pagesToAdd) {
        totalPages += pagesToAdd;
        System.out.println(pagesToAdd + " pages added to the tray. Total pages: " + totalPages);
        notify();  // Notify waiting print thread that enough pages are available
    }
}

class PrintThread extends Thread {
    private PrinterTray tray;
    private int pagesToPrint;

    public PrintThread(PrinterTray tray, int pagesToPrint) {
        this.tray = tray;
        this.pagesToPrint = pagesToPrint;
    }

    @Override
    public void run() {
        tray.printPages(pagesToPrint);  // Try to print the pages
    }
}

class PageTrayThread extends Thread {
    private PrinterTray tray;
    private int pagesToAdd;

    public PageTrayThread(PrinterTray tray, int pagesToAdd) {
        this.tray = tray;
        this.pagesToAdd = pagesToAdd;
    }

    @Override
    public void run() {
        tray.addPages(pagesToAdd);  // Add pages to the tray
    }
}

public class PrinterJob {
    public static void main(String[] args) {
        // Create a PrinterTray with initial pages
        PrinterTray tray = new PrinterTray(10);  // Initially, there are 10 pages in the tray

        // Create two threads: one for printing pages and one for adding pages
        PrintThread printJob = new PrintThread(tray, 15);  // A job requesting to print 15 pages
        PageTrayThread addPagesJob = new PageTrayThread(tray, 10);  // Adding 10 pages to the tray

        // Start the threads
        printJob.start();
        addPagesJob.start();

        try {
            // Wait for both threads to complete
            printJob.join();
            addPagesJob.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
