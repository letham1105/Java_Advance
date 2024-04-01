package IOStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadFile1 {
    public static void readFile(String path){
        FileInputStream fis = null;
        try{
            // mo tep tin can doc
            fis = new FileInputStream(path);
            // doc noi dung va hien thi
            int data;
            data = fis.read();
            while(data!= -1){
                System.out.print((char) data);
                data = fis.read();
            }
        }catch (IOException e){
            System.out.println("Loi IOException");
        }
        finally {
            try{
                fis.close();
            }
            catch(IOException e){
                System.out.println("Loi IOException");
            }
        }
    }

    public static void main(String[] args) {
        readFile("./src/IOStream/ReadFile1.java");
    }
}