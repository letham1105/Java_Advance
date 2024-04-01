package MultiThread;

import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaitDemo extends Applet implements Runnable, ActionListener {
    // Thread con có tên là t
    Thread t;
    // Vị trí khởi tạo của quả bóng
    int x = 34, y = 14;
    // Bước di chuyển
    int dx = 5, dy = 5;
    // Hai nút có tên Start và Stop
    Button start, stop;
    // Dùng để biểu diễn trạng thái di chuyển hoặc dừng quả bóng
    String status = "Go";

    public void init() {
        // Thêm 2 nút Start, Stop vào giao diện
        start = new Button("Start");
        stop = new Button("Stop");
        add(start);
        add(stop);
        // Gán người nghe cho cả 2 nút
        start.addActionListener(this);
        stop.addActionListener(this);
        // Tạo và khởi động Thread
        t = new Thread(this);
        t.start();
    }

    public void run() {
        // Quả bóng di chuyển mãi mãi
        while (true) {
            // Nếu trạng thái của quả bóng là Stop thì dừng tạm thời Thread
            if (status.equals("Stop")) {
                try {
                    // Dùng synchronized để đồng bộ hóa
                    synchronized (t) {
                        // Thread rơi vào trạng thái ngừng hoạt động
                        t.wait();
                    }
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            // Nếu chạm vào biên
            if (x + dx > this.getWidth() || x + dx < 0) dx = -dx;
            if (y + dy > this.getHeight() || y + dy < 0) dy = -dy;
            // Thay đổi vị trí
            x = x + dx;
            y = y + dy;
            // Xóa màn hình và gọi lại phương thức paint(...)
            repaint();
            try {
                // Dừng 10 mili giây để có thể nhìn thấy quả bóng
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics g) {
        // Vẽ hình tròn tô màu ở vị trí hiện tại
        g.fillOval(x, y, 40, 40);
    }

    public void actionPerformed(ActionEvent e) {
        // Nếu nhấn nút Stop
        if (e.getSource() == stop) {
            status = "Stop";
        }
        // Nếu nhấn nút Start
        if (e.getSource() == start) {
            status = "Go";
            synchronized (t) {
                // Đánh thức và tiếp tục Thread
                t.notify();
            }
        }
    }
}
