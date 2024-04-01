package IOStream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo {
    public static void main(String[] args) throws IOException {
        // su dung loai luong lam viec voi tep ki tu FileReader
        FileReader fr = new FileReader("./src/IOStream/FileReaderDemo.java");
        // su dung luong trung gian de doc ca dong
        BufferedReader br = new BufferedReader(fr);
        // doc tung dong roi in ra man hinh
        String s;
        while ( (s = br.readLine()) != null){
            System.out.println(s);
        }
        fr.close();
    }
}
