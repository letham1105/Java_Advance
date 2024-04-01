package IOStream;
import java.io.FileInputStream;
import java.io.IOException;


public class CountCharFile {
    public static int count ( String path, char ch){
        // Dung lam bien dem
        int c = 0;
        // Dung doc noi dung tep tin
        FileInputStream fis = null;
        try{
            // mo tep tin can doc noi dung
            fis = new FileInputStream(path);
            // doc du lieu va kiem tra, ket thuc nhan duoc ma -1
            int data;
            data = fis.read();
            while ( data != -1){
                if ( data == ch ) c++;
                data = fis.read();
            }
        }catch (IOException e){
            System.out.println("Error IOException");
        }
        finally {
            try {
                fis.close();
            }
            catch (IOException e){
                System.out.println(" Error IOException");
            }
        }
        return c;
    }


    public static void main(String[] args) {
        // kiem tra tep chuong trinh hien tai
        System.out.println(count("./src/IOStream/CountCharFile.java", 'i'));
    }
}
