package MultiThread;

import java.applet.*;
import java.awt.*;
import java.util.Date;

public class WorldTime extends Applet implements Runnable {
    // lop Date cua goi java.util cho phep lay thong tin thoi gian
    private Date toDay = new Date();
    // tuyen con co ten ch
    private Thread th = null;
    public void start(){
        if (th == null ){
            // tao tuyen bang doi so la doi tuong cua lop dan xuat interface Runnable
            th = new Thread(this);
            // khoi dong tuyen va thuc thi ham run()
            th.start();
        }
    }
    // dinh nghia lai ham run() cua interface Runnable
    @Override
    public void run() {
        // cho tuyen thuc thi co thoi han
        while(true){
            // lay thong tin thoi gian
            toDay = new Date();
            //Xoa man hinh va goi lai ham paint(...) de cap nhat lai man hinh
            repaint();
            try{
                // dung lai 1 giay (1000 mili giay) roi cap nhat lai thoi gian
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
    public void paint(Graphics g){
        // hien thi thoi gian tai thanh pho Tel-Aviv
        g.drawString("Tel-Aviv" +toDay, 50,100);
        // hien thi thoi gian tai thanh pho New-York(cham hon 7h)
        toDay.setTime(toDay.getTime() -7*3600*1000);
        g.drawString("New-York :" +toDay,50,150);
        //hien thi thoi gian tai Ha Noi ( Nhanh hon 7h)
        toDay.setTime(toDay.getTime()+7*3600*1000);
        g.drawString("Hanoi :"+toDay,50, 200);
    }


}
