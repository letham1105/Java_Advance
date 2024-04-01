package MultiThread;

public class RandomNumberThread extends Thread {
    private int count;
    private int sum;

    public RandomNumberThread(int count) {
        this.count = count;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public void run() {
        // Initialize sum inside run method
        sum = 0;

        for (int i = 0; i < count; i++) {
            // Generate random number from 1 to 30
            int randomNumber = (int) (Math.random() * 30) + 1;

            // Use synchronized block to update sum
            synchronized (this) {
                System.out.println(getName() + "Random number "  + randomNumber);
                sum += randomNumber;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int numThread = 7;
        RandomNumberThread[] threads = new RandomNumberThread[numThread];

        // Create and start threads
        for (int i = 0; i < numThread; i++) {
            threads[i] = new RandomNumberThread(1);
            threads[i].start();
        }

        // Wait for all threads to complete
        try {
            for (int i = 0; i < numThread; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Calculate total sum
        int totalSum = 0;
        for (int i = 0; i < numThread; i++) {
            totalSum += threads[i].getSum();
        }

        System.out.println("Total Sum: " + totalSum);
    }
}
