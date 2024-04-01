package IOStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;


public class SaveFile extends JFrame implements ActionListener {


    JTextArea ta = new JTextArea();
    JButton saveAs = new JButton("Save as...");
    // su dung lop jfilechooser de hien thi hop thoai luu tep tin
    JFileChooser chooser;
    // su dung lop FileWrite de ghi noi dung xuong tep
    FileWriter f;


    public SaveFile() {
        // tao giao dien co nut save as va textarea
        Container cont = this.getContentPane();


        saveAs.addActionListener(this);
        cont.add(ta);
        cont.add(saveAs, "South");
        this.setSize(400, 400);
        this.setVisible(true);
    }
    // ham nay thuc thi khi an nut save as
    @Override
    public void actionPerformed(ActionEvent e) {
        // tao doi tuong Jfilechooser de mo hop thoai ghi tep
        chooser = new JFileChooser();
        // thu muc hien hanh duoc mo
        chooser.setCurrentDirectory(new java.io.File("."));
        // tieu de cua hop thoai
        chooser.setDialogTitle("Save as...");
        if ( chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            // lay ten tep da dat hoac chon
            String filename = chooser.getSelectedFile().getAbsolutePath();
            saveFile(filename);
        }


    }


    private void saveFile(String filename) {
        try {
            String content = ta.getText();
            f = new FileWriter(filename);
            f.write(content);
            f.flush();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        new SaveFile();
    }
}

