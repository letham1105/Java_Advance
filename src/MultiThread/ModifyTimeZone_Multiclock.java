package MultiThread;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Set;
import java.util.TimeZone;

public class ModifyTimeZone_Multiclock extends JFrame implements ActionListener, Runnable {
JButton createClock = new JButton("New Clock");
JLabel clock = new JLabel();
JComboBox<String> timezoneSelector = new JComboBox<>();
Thread t;
public ModifyTimeZone_Multiclock(){
    Container cont = this.getContentPane();
    // lay thoi gian hien tai
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    clock = new JLabel(sdf.format(cal.getTime()), JLabel.CENTER);
    // thiet lap kich thuoc va mau hien chu hien thi
    clock.setFont(new Font(clock.getFont().getName(), Font.PLAIN, 40));
    clock.setForeground(Color.RED);
    // thiet lap comboBox cho viec chon timezone
    Set<String> zoneId = ZoneId.getAvailableZoneIds();
    for (String zoneID : zoneId){
        timezoneSelector.addItem(zoneID);
    }

    cont.add(createClock, "North");
    cont.add(clock);
    cont.add(timezoneSelector, "South");
    this.pack();
    this.setVisible(true);
    createClock.addActionListener(this);
    // tao mot tuyen de hien thi dong ho
    Thread t = new Thread(this);
    t.start();

}
// cap n
public void tick(){
    try{
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String selectedZoneID = (String) timezoneSelector.getSelectedItem();
        sdf.setTimeZone(TimeZone.getTimeZone(selectedZoneID));
        clock.setText(sdf.format(cal.getTime()));
        Thread.sleep(1000);
    }catch (Exception e){
        e.printStackTrace();
    }

}

    @Override
    public void actionPerformed(ActionEvent e) {
        Thread t = new Thread(new ModifyTimeZone_Multiclock());
        t.start();
    }

    @Override
    public void run() {
    while (true){
        tick();
    }

    }

    public static void main(String[] args) {
        new ModifyTimeZone_Multiclock();
    }
}
