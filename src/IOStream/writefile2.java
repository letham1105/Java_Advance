package IOStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class writefile2 {
    public static void main(String[] args) throws IOException {
        String source = "programing with IO Stream in java";
        // su dung lop FileWriter de mo va ghi du lieu
        FileWriter f = new FileWriter("./Test_file.txt");
        f.write(source);
        f.flush();
        f.close();
    }
}
