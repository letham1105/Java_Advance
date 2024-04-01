package IOStream;

import java.io.File;

public class ReadDir {
    public ReadDir (String path){
        // tro den thu muc co duong dan path
        File f = new File (path);
        // lay dnah sach ten tep tin hoac thu muc ma f dang tro den
        String[] filenames = f.list();
        // duyet danh sach  va in ten ra man hinh
        for ( int i = 0; i < filenames.length; i++)
            System.out.println(filenames[i]);
    }

    public static void main(String[] args) {
        new ReadDir("./");
    }
}
