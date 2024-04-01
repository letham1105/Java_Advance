package MultiThread;

import java.applet.*;
import java.awt.*;

public class MultithreadApplet extends Applet implements Runnable {
    private String message = "Multithread Programming";
    private int x = 0;
    private int direction = 1;
    private Thread thread;

    public void init() {
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(100); // Điều chỉnh tốc độ chạy của chuỗi
            } catch (InterruptedException e) {
                return;
            }

            x += direction;
            if (x + message.length() * 10 > getWidth() || x < 0) {
                direction *= -1;
            }
            repaint();
        }
    }

    public void paint(Graphics g) {
        g.drawString(message, x, 50);
    }
}
