package IOStream;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;

import static java.util.Collections.sort;

public class QLSV extends Frame implements ActionListener {
    // dung he thong menu de chua cac chuc nang chuong trinh
    private MenuBar bar;
    private Menu action;
    private MenuItem input, search, sort_view, exit;
    FileWriter f;
    PrintWriter pr;
    // dung de chua danh sach sinh vien
    Vector list = new Vector();
    public QLSV(String s){
        super(s);
        // doc du lieu tu tep tin va tao danh sach sinh vien
        loadData();
        // tao he thong menu
        bar = new MenuBar();
        setMenuBar(bar);
        action = new Menu("Action");
        bar.add(action);
        input = new MenuItem("Input");
        action.add(input);
        input.addActionListener(this);

        action.addSeparator();
        sort_view = new MenuItem("Sort and View");
        action.add(sort_view);
        sort_view.addActionListener(this);

        action.addSeparator();
        search = new MenuItem("Search");
        action.add(search);
        search.addActionListener(this);

        action.addSeparator();
        exit = new MenuItem("Exit");
        action.add(exit);
        exit.addActionListener(this);
        // thiet lap kich thuoc va hien thi cua cua so chinh
        setSize(400, 300);
        setVisible(true);
        setResizable(false);
    }

    private void loadData() {
        try{
            FileReader fr = new FileReader("./src/data.txt");

            BufferedReader br = new BufferedReader(fr);
            String s;
            while((s = br.readLine()) != null){
                //thong tin sinh vien duow=c doc tu tep tin la 1 chuoi, phan cach nhau bang 1 ki tu & tach thong tin de tao laij doi tuong Student
                String[] data = s.split("&");
                Student st = new Student (data[0], data[1], Float.parseFloat(data[2]));
                // them vao danh sach
                list.add(st);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Exit")){
            // truoc khi ket thuc luu dl xuong tep tin
            saveFile();
            System.exit(0);
        }
        if (ae.getActionCommand().equals("Input")){
            // tao cua so nhap thong tin sinh vien
            InputForm f = new InputForm(this, "Input information");
        }
        if (ae.getActionCommand().equals("Sort and View")){
            // goi ham sap sep sinh vien theo chieu tang dan cua aver
            sort();
        }
        if (ae.getActionCommand().equals("Search")){
            new SearchForm("Search", list);
        }
    }
    // cap nhat sinh vien vao danh sach sau khi chon nut save o cua so  nhap sinh vien
    public void updateList(String n, String i, float a){
        Student st = new Student(n, i, a);
        list.add(st);
    }


    private void sort() {
        // tao mang Student tu danh sach de sap xep
        Student[] sts = new Student[list.size()];
        int index =0;
        Enumeration vEnum = list.elements();
        while(vEnum.hasMoreElements()){
            sts[index] =(Student) vEnum.nextElement();
            index++;
        }
        // sao xeo theo chieu tang dan cua aver
        Arrays.sort(sts);
        // hien thi danh sach da sap xep vao TextArea
        TextArea ta = new TextArea("Name \t id \t aver \n");
        for (index =0; index <sts.length; index++){
            ta.append(sts[index].getName()+"\t"+sts[index].getId()+"\t"+sts[index].getAver()+"\n");
        }
        this.add(ta);
        this.validate();
    }

    public static void main(String[] args) {
        new QLSV("QLSV");
    }

    private void saveFile() {
        try {
            f = new FileWriter("./src/data.txt", false);
            pr = new PrintWriter(f);
            Enumeration vEnum = list.elements();
            while (vEnum.hasMoreElements()){
                Student st = (Student) vEnum.nextElement();
                // gia tri cac thuoc tinh cua mot sinh vien duoc ghi xuong tep thanh 1 chuoi ohan cach nhau bang ki tu &
                String toString = st.getName()+"&"+st.getId()+"&"+st.getAver();
                pr.println(toString);
                pr.flush();
            }
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
}
class Student implements Comparable{
    private String name;
    private  String id;
    private float aver;
public Student(String n, String i, float a){
    name = n;
    id = i;
    aver = a;
}

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
    public float getAver() {
        return aver;
    }
    @Override
    public int compareTo(Object other) {
    Student otherReact = (Student) other;
    return (int)(this.aver-otherReact.aver);
    }
}
class InputForm extends Frame implements ActionListener{
    private Label namelb;
    private TextField name;
    private Label idlb;
    private  TextField id;
    private Label averlb;
    private TextField aver;
    Button save, newInput, cancel;
    QLSV main_frame;
    public InputForm(QLSV a, String s){
        super(s);
        // lay doi tuong cua cua so chin de goi ham cap nhat thong tin sinh vien duoc nhao o cua so nay vao danh sach list o chuong tring chinh
        main_frame = a;
        // tao giao dien nhap thong tin cua sinh vien
        setLayout(new BorderLayout());
        Panel p1 = new Panel();
        p1.setLayout(new GridLayout(3, 2));
        namelb = new Label("Name");
        name = new TextField(20);
        p1.add(namelb);
        p1.add(name);
        idlb = new Label("ID");
        id = new TextField(20);
        p1.add(idlb);
        p1.add(id);
        averlb = new Label("Average");
        aver = new TextField(20);
        p1.add(averlb);
        p1.add(aver);

        this.add(p1, "North");
        Panel p2 = new Panel();
        save = new Button("Save");
        newInput = new Button("New");
        cancel = new Button("Cancel");
        save.addActionListener(this);
        newInput.addActionListener(this);
        cancel.addActionListener(this);
        p2.add(save);
        p2.add(newInput);
        p2.add(cancel);

        this.add(p2, "South");
        setSize(300, 300);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Save")){
            // Truyen va cap nhat sinh vien moi vao danh sach o chuong trinh chinh
            main_frame.updateList(name.getText(), id.getText(), Float.parseFloat(aver.getText()));
        }
        if (ae.getActionCommand().equals("New")){
            reset();
        }
        if (ae.getActionCommand().equals("Cancel")){
            this.dispose();
        }
    }

    private void reset() {
        name.setText("");
        id.setText("");
        aver.setText("0");
    }
}
class SearchForm extends Frame implements ActionListener{
private Label namelb;
private TextField name;
private TextArea ta;
private  Button search, cancel;
private Vector list;
public SearchForm(String s, Vector v){
    super(s);
    // lay danh sach sinh vien tu chuong trinh chinh
    list =v;
    // tao giao dien co cua so tim kiem
    setLayout(new BorderLayout());
    Panel p1 = new Panel();
    p1.setLayout(new GridLayout(3,2));
    namelb = new Label("Name");
    name = new TextField(20);
    p1.add(namelb);
    p1.add(name);
    this.add(p1, "North");
    ta = new TextArea("Name \t Id \t Aver \n");
    this.add(ta, "Center");
    Panel p2 = new Panel();
    search = new Button ("Search");

    cancel = new Button("Cancel");
    search.addActionListener(this);
    cancel.addActionListener(this);

    p2.add(search);
    p2.add(cancel);

    this.add(p2, "South");
    setSize(300, 300);
    setVisible(true);
    setResizable(false);
}
    @Override
    public void actionPerformed(ActionEvent ae) {
 if (ae.getActionCommand().equals("Search")){
     // goi ham tim kiem sinh vien
     search(name.getText());
 }
 if(ae.getActionCommand().equals("Cancel")){
     this.dispose();
 }
    }

    private void search(String s) {
    Enumeration vEnum = list.elements();
    while (vEnum.hasMoreElements()){
        Student st = (Student) vEnum.nextElement();
        if(st.getName().indexOf(s) != -1){
            ta.append(st.getName()+"\t"+st.getId()+"\t"+st.getAver()+"\n");
        }
    }
    this.validate();
    }
}