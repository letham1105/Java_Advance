package MultiThread;

public class MultiThreadDemo {
    // tao 3 tuyen
    FacThread t1 = new FacThread(2);
    SumThread t2 = new SumThread(3);
    SumPowThread t3 = new SumPowThread(2,1);
    public MultiThreadDemo(){
      // khoi tao 3 tuyen
        t1.start();
        t2.start();
        t3.start();
        try{
            // moi tuyen thuc hien cho den het
            t1.join();
            t2.join();
            t3.join();
            // tinh ra ket qua tong khi cac tuyen con da hoan tat(nho ham join) va in ra man hinh
            long S = t1.getResult()+t2.getResult()+t3.getResult();
            System.out.println("\nKet qua ="+S);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MultiThreadDemo();

    }
}
class FacThread extends Thread {
    long gt = 1;
    int n;
    public FacThread(int k){
        // nhan gia tri n duoc truyen vao tu chuong trinh chinh
        n = k;
    }
    // dinh nghia lai ham run ()
    public void run(){
        // tinh F1(n)
        for ( int i = 2; i <=n; i++){
            gt*=i;
        }
        System.out.println("\nF1 = "+gt);
    }
    // ham tra ket qua ma tuyen tinh duoc
    public long getResult(){
        return gt;
    }
}
// dinh nghia tuyen tinh F2(n)
class SumThread extends Thread{
    long S = 0;
    int n;
    public SumThread(int k){
        // nhan gia tri n duoc truyen vao tu chuong trinh chinh
        n =k;
    }
    // dinh nghia lai ham run()
    public void run() {
        // tinh F2(n)
        for (int i = 1; i <= n; i++) {
            S += i;
        }
        System.out.println("\nF2 =" + S);
    }
        // ham tra ket qua ma tuyen tinh duoc
        public long getResult(){
            return S;
        }
}
// dinh nghia tuyen tinh F3 (x,n)
class SumPowThread extends Thread{
    long S = 0;
    int x, n;
    public SumPowThread(int y, int k){
        // nhan gia tri x. n duoc truyen vao tu chuong trinh chinh
        x = y;
        n = k;
    }
    // dinh nghia lai ham run()
    public void run(){
        // Tinh F3(x, n)
        for (int i =1; i <=n; i++){
            // su dung ham tinh mu pow cua lop math
            S +=Math.pow(x, i);
        }
        System.out.println("\nF3 ="+S);
    }
    // ham ket qua ma tuyen tinh duoc
    public long getResult(){
        return S;
    }

    }
