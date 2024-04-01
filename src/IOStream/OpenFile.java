package IOStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenFile extends JFrame implements ActionListener {
    // tao cac thanh phan tren gia dien
    JTextArea ta = new JTextArea();
    JButton open = new JButton("Open...");
    // su dung lop Jfilechooser cho phep mo hop thoai mo he thong tep tin
    JFileChooser chooser;
    // su dung cac lop nay de doc noi dung tep tin
    FileReader fr;
    BufferedReader br;

    public OpenFile() {
        // them cac thanh phan vao tang ContentPane cua JFrame
        Container cont = this.getContentPane();
        open.addActionListener(this);
        cont.add(ta);
        cont.add(open, "South");
        this.setSize(400, 400);
        this.setVisible(true);
    }

    // ham nay duoc thuc hien khi an nut open
    @Override
    public void actionPerformed(ActionEvent e) {
        // tao doi tuong Jfilechooser de hien thi hop thoai de mo tep
        chooser = new JFileChooser();
        // mo thu muc hien hanh
        chooser.setCurrentDirectory(new java.io.File("."));
        // tieu de cua hop thoai
        chooser.setDialogTitle("Open file");
        // neu da chon tep tin
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            // lay ten tep tin da chon
            String filename = chooser.getSelectedFile().getAbsolutePath();
            // doc coi dung tep va hien thi ow textarea
            readFile(filename);
        }
    }

    // doc noi dung tep va hien thi ow textarea
    public void readFile(String filename) {
        try {
            // xoa het noi dung hien co o textarea
            ta.setText("");
            // mo tep va doc
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            String s;
            while ((s = br.readLine()) != null) { // Fix syntax error here
                // chen them noi dung vao textarea
                ta.append(s + "\n");
            }
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new OpenFile();
    }
}
