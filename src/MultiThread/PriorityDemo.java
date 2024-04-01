package MultiThread;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PriorityDemo extends Frame {
    // 2 tuyen co ten la high va low
    private HighThread high;
    private LowThread low;

    // dung de hien thi ket qua
    private TextArea output;
    public PriorityDemo(){
        // goi phuong thuc khoi tao cua lop Frame, thiet lap tieu de cho Frame
        super("PriorityDemo");
        // tao mot TextArea co kich thuoc 10*20 de hien thi ket qua
        output = new TextArea(10, 20);
        add(output);
        // thiet lap giao dien co kich thuoc 250*200
        setSize(250,200);
        setVisible(true);
        // tao khoi dong tuyen co do uu tien cao hon
        high = new HighThread(output);
        high.start();
        // tao va khoi dong tuyen cos do uu tien thap hon
        low = new LowThread(output);
        low.start();
    }

    public static void main(String[] args) {
        // khoi dong chuong trinh
        PriorityDemo app = new PriorityDemo();
        // bat su kien cho phep dong cua so ( bang cach an dau x o cua so)
        app.addWindowFocusListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                      System.exit(0);
                    }
                }
        );
    }


    // tao tuyen va thiet lap do uu tien cao hon
    class HighThread extends Thread{
        // dung de nhan doi tuong TextArea o chuong trinh chinh de hien thi ket qua
        private TextArea display;
        public HighThread (TextArea a){
            // nhan doi tuong TextArea o chuong trinh chinh de hien thi ket qua
            display = a;
            // thiet lap do uu tien cao nhat
            setPriority(Thread.MAX_PRIORITY);
        }
        // dinh nghia lai ham run() cua lop thread de in ket qua
        public void run(){
            // chen them 5 chuoi High... vao TextArea
            for ( int x = 1; x <= 5; x++)
                display.append("High Priority Thread !!!\n");
        }
    }
    class LowThread extends Thread{
        // dung de nhan doi tuong textarea o chuong trinh chinh de hien thi ket qua \
        private  TextArea display;
        public LowThread(TextArea a){
            // nhan doi tuong textarea tu chuong trinh chinh truyen den
            display = a;
            // thiet lap do uu tien thap nhat
            setPriority(Thread.MIN_PRIORITY);
        }
        // dinh nghia lai ham run() cua lop thread de in ket qua
        public void run(){
            // chen them 5 chuoi Low... vao TextArea
            for ( int y = 1; y <= 5; y++)
                display.append("Low Priority Thread !!!\n");
        }
    }
}
