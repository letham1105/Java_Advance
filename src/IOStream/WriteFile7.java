package IOStream;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFile7 {
    public static void WriteFile7(String path){
        // sử dụng lồng trung gian cho ghi cả chuỗi xuống tệp
        DataOutputStream bf = null;
        String msg = "programing with IO Stream in java";
        try{
            // Noi FileOutputStream vaof luoofng DataOutputStream
            bf = new DataOutputStream(new FileOutputStream(path));
            // ghi cả dòng xuống tệp
            bf.writeChars(msg);
            // đẩy dữ liệu còn ở bộ đệm xuống tệp
            bf.flush();
        } catch (IOException e) {
            System.out.println("Loi IOException");
        }finally {
            try{
                bf.close();
            } catch (IOException e) {
                System.out.println("Loi IOException");
            }
        }
    }

    public static void main(String[] args) {
        WriteFile7("D:\\Test.txt");
    }

}
