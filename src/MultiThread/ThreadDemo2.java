package MultiThread;
class MyThread extends Thread
{
    // su dung start de nhan gia tri khoi dau, neu start = 1 thi in cac so le, neu start = 2 thi in cac so chan
    int start;
    public MyThread(int s){
        // khoi tao gia tri cho start
        start = s;
    }
    // ham static nay se duoc dung chung cho tat ca cac tuyen
    public static void go(int s)
    {
        // tuy vao gia tri s ( 1 hoac 2) ham go (...) se in ra man hinh cac so le hay chan tu 1 den 10*
        for ( int i = s; i< 10; i+= 2){
            System.out.println(i+"  ");
            try {
                // tam ngung tuyen hien tai de tuyen khac hoat dong
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }
    }
    // ham run() se duoc goi tu dong boi ham start()
    public void run (){
        go(start);
    }
}
public class ThreadDemo2 {
    public static void main(String[] args) {
        // tao 2 tuyen con, tuyen t1 se in cac so le tu 1 den 9
        Thread t1 = new MyThread(1);
        // tuyen t2 se in cac so chan tu 2 den 10
        Thread t2 = new MyThread(2);
        // khoi dong 2 tueyn va ham run() se duoc goi tu dong
        t1.start();
        t2.start();
    }
}
