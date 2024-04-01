package IOStream;

import java.io.File;

public class CountFolder {
    public static int count (String path){
        // dung de dem so luong tep tin
        int c = 0;
        File f = new File(path);
        String[] filenames = f.list();
        for ( int i = 0; i < filenames.length; i++) {
            // tao fi de kiem tra phan tu hien tai la tep hay thu muc
            File fi = new File(path + "\\"+filenames[i]);
            if (fi.isFile())
                // neu la teo tiin, tang dem len 1
                c++;
            else // neu la thu muc goi lai ham den thu muc
                c += count(fi.getAbsolutePath());
        }
            return c;
        }

    public static void main(String[] args) {
        // goi ham den va in ra ket qua cua thu muc hien hanh
        System.out.println(count ("./"));
    }
    }

