package MultiThread;
class MyThread1 extends Thread{
    int start;
    public MyThread1(int s){
        start = s;
    }
    // them tu khoa synchronized se ngan cam nhieu tuyen thuc thi ham go() dong thoi. cac tuyen lan luot thuc thi cho den het ham go()
    public static synchronized void go (int s){
        for ( int i = s; i < 10; i+=2){
            System.out.println(i+"  ");
            try{
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }
    }
    public void run(){
        go(start);
    }
}

public class SynchDemo{
    public static void main(String[] args) {
        // tao 2 tuyen con, tuyen 1 se in cac so le tu 1 den 9
        Thread t1 = new MyThread(1);
        // tuyen t2 se in cac so chan tu 2 den 10
        Thread t2 = new MyThread(2);
        // khoi dong 2 tuyen va ham run() se duoc goi tu dong
        t1.start();
        t2.start();
    }
}
