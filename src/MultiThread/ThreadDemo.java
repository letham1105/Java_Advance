package MultiThread;
class Thread_1 extends Thread {
    // dinh nghia lai ham run cua ham run() của lớp Thread de in ra man hinh cac so le tu 1 den 9
    public void run() {
        for (int i = 1; i < 10; i += 2) {
            System.out.println(i + "  ");
            try {
                // tam dung 100 mili giay de tuyen khac hoat dong
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }
    }
}
    class Thread_2 extends Thread {
        // Dinh nghia lai ham run() cua lop thread de in ra man hinh cac so chan tu 2 den 10
        public void run() {
            for (int i = 2; i <= 10; i += 2) {
                System.out.println(i + "  ");
                try {
                    // tam dung 100 mili giay de tuyen khac hoat dong
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            }
        }
    }
        public class ThreadDemo {
            public static void main(String[] args) {
                // tao 2 tuyen con
                Thread t1 = new Thread_1();
                Thread t2 = new Thread_2();
                // khoi dong tuyen va tu dong goi ham run()
                t1.start();
                t2.start();
            }
        }
