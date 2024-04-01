package IOStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class writeFile {
    public static void writefile(String path){
        // dung de ghi du lieu len tep tin
        FileOutputStream fos = null;
        // chuoi du lieu can ghi
        String msg = "programing with IO Stream in java";
        try{
            // mo tep tin can ghi
            fos = new FileOutputStream(path);
            // ghi tung ky tu cua chuoi len tep tin
            for ( int i = 0; i < msg.length(); i++)
                fos.write((int)msg.charAt(i));
            // day du lieu con o bo dem xuong tep
            fos.flush();
        }
        catch (IOException e){
            System.out.println("Loi IOException");
        }
      finally {
            try{
                fos.close();
            }
            catch(IOException e){
                System.out.println("Loi IOException");
            }
        }
    }

    public static void main(String[] args) {
        writefile("D:\\Test_file.txt");
    }
}
