package MultiThread;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {

    public static void main(String[] args) {
        int numPhilosophers = 5;
        int eatingCountLimit = 15; // Giới hạn số lần ăn
        Philosopher[] philosophers = new Philosopher[numPhilosophers];
        Lock[] chopsticks = new Lock[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            chopsticks[i] = new ReentrantLock();
        }

        // Tạo một biến cờ dừng chung
        boolean stopFlag = false;

        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % numPhilosophers], eatingCountLimit, stopFlag);
            philosophers[i].start();
        }

        // Chờ một khoảng thời gian trước khi đặt stopFlag thành true để đảm bảo triết gia đã bắt đầu chạy
        try {
            Thread.sleep(1000); // Đợi 1 giây
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Đặt stopFlag thành true để ngừng chương trình
        stopFlag = true;

        // Chờ tất cả các triết gia kết thúc trước khi kết thúc chương trình
        for (Philosopher philosopher : philosophers) {
            try {
                philosopher.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Philosopher extends Thread {
    private final int id;
    private final Lock leftChopstick;
    private final Lock rightChopstick;
    private final int eatingCountLimit; // Giới hạn số lần ăn
    private int eatingCount; // Số lần ăn
    private boolean stopFlag;

    public Philosopher(int id, Lock leftChopstick, Lock rightChopstick, int eatingCountLimit, boolean stopFlag) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.eatingCountLimit = eatingCountLimit;
        this.stopFlag = stopFlag;
    }

    private void eat() {
        System.out.println("Philosopher " + id + " is eating.");
    }

    private void think() {
        System.out.println("Philosopher " + id + " is thinking.");
    }

    @Override
    public void run() {
        try {
            while (!stopFlag && eatingCount < eatingCountLimit) { // Kiểm tra biến dừng và giới hạn số lần ăn
                think();

                // Lấy chopstick bên trái
                leftChopstick.lock();
                System.out.println("Philosopher " + id + " picked up left chopstick.");

                // Lấy chopstick bên phải
                if (rightChopstick.tryLock()) {
                    try {
                        System.out.println("Philosopher " + id + " picked up right chopstick.");

                        eat();
                        eatingCount++; // Tăng biến đếm số lần ăn
                    } finally {
                        // Giải phóng chopstick bên phải
                        rightChopstick.unlock();
                        System.out.println("Philosopher " + id + " released right chopstick.");
                    }
                } else {
                    // Nếu không thể lấy chopstick bên phải, giải phóng chopstick bên trái
                    System.out.println("Philosopher " + id + " couldn't pick up right chopstick. Releasing left chopstick.");
                    leftChopstick.unlock();
                    continue; // Tiếp tục vòng lặp để suy nghĩ lại
                }

                // Giải phóng chopstick bên trái
                leftChopstick.unlock();
                System.out.println("Philosopher " + id + " released left chopstick.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
