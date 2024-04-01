package MultiThread;

import java.applet.Applet;
import java.awt.*;

public class MovingBall extends Applet implements Runnable {
    // tuyen con cos ten t
    Thread t;
    // vi tri khoi tao cua qua bong
    int x=34, y= 14;
    // buoc di chuyen
    int dx=5, dy=5;
    // ham init duoc goi tu dong dau tien khi applet chay
    public void init(){
        // tao va khoi dong tuyen con voi doi so la doi tuonng cua lop dan xuat interface Runnable
        t = new Thread(this);
        // khoi dong tuyen va thuc thi ham run()
        t.start();
    }
    @Override
    public void run() {
    while (true){
        // neu qua bong va vao cac duong bien
        if( x+dx> this.getWidth()||x+dx <0)dx=-dx;
        if( y+dy> this.getHeight()||y+dy<0) dy=-dy;
        // thay doi vi tri qua bong
        x=x+dx;
        y=y+dy;
        // xoa man hinh va goi lai ham paint de ve lai qua bong tai vi tri moi
        repaint();
        try{
            // dung lai 10 mili giay de nhin qua bong truoc khi xoa va ve lai
            Thread.sleep(10);
        } catch (Exception e) {
        }
      }
    }
    public void paint (Graphics g){
        // ve qua bong tai vi tri hien tai
        g.fillOval(x,y, 40, 40);
    }
}
