package IOStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile {
    public static void main(String[] args) {
        // FileInputStream và FileOutputStream được khai báo ở đây để đảm bảo rằng chúng sẽ được đóng trong khối finally
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            // Mở tệp để đọc và ghi
            fis = new FileInputStream("./src/IOStream/CopyFile.java");
            fos = new FileOutputStream("./CopyFile1.java");
            // Đọc dữ liệu từ FileInputStream và ghi vào FileOutputStream
            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data);
            }
            // Đảm bảo dữ liệu được ghi từ bộ đệm xuống tệp
            fos.flush();
        } catch (IOException e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra trong quá trình đọc hoặc ghi
            e.printStackTrace();
        } finally {
            // Đảm bảo rằng FileInputStream và FileOutputStream được đóng ngay cả khi có ngoại lệ xảy ra
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
