package MultiThread;

import javax.swing.*;
import java.awt.*;

public class



TrafficLight extends JApplet implements Runnable{
    String light = "green";
    public void init(){
        // tao 3 tuyen
        Thread one = new Thread(this);
        Thread two = new Thread(this);
        Thread three = new Thread(this);
        // dat ten cho 3 tuyen
        one.setName("red");
        two.setName("yellow");
        three.setName("green");
        // khoi dong 3 tuyen
        one.start();
        two.start();
        three.start();
    }
    public void paint (Graphics g){
        // thiet lap mau tuong ung tuyen dang haot dong
        if(light.equals("green")) g.setColor(Color.GREEN);
        if(light.equals("yellow")) g.setColor(Color.YELLOW);
        if(light.equals("red")) g.setColor(Color.RED);
        // ve den giao thong voi mau tuong ung
        g.fillOval(100, 100, 50, 50);
    }
    public void showLight(){
        // lay ten tuyen dang hoat dong
        light = Thread.currentThread().getName();
        // xoa man hinh va goi lai ham paint(...)
        repaint();
    }
    @Override
    public void run() {
        while (true){
            // goi ham de ve den giao thong
            showLight();
            try{
                // hien thi den voi mau hien tai 500 mili giay
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
