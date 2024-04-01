package IOStream.Exercise;

import java.io.*;

public class FolderCopy {
    public static void main(String[] args) {
        // Đường dẫn của thư mục nguồn và đích
        String sourceDirectoryPath = "D:\\Project_ByLeThiTham";
        String destinationDirectoryPath = "D:\\Java_Advance\\java_advance";

        // Thực hiện sao chép thư mục
        try {
            copyDirectory(new File(sourceDirectoryPath), new File(destinationDirectoryPath));
            System.out.println("Directory copied successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Phương thức sao chép thư mục
    private static void copyDirectory(File sourceDir, File destDir) throws IOException {
        // Kiểm tra nếu thư mục nguồn tồn tại và là thư mục
        if (sourceDir.isDirectory()) {
            // Kiểm tra nếu thư mục đích không tồn tại, tạo mới nếu cần
            if (!destDir.exists()) {
                destDir.mkdir();
            }

            // Lấy danh sách tất cả các tệp và thư mục trong thư mục nguồn
            String[] files = sourceDir.list();
            if (files != null) {
                // Duyệt qua từng tệp và thư mục
                for (String file : files) {
                    File sourceFile = new File(sourceDir, file);
                    File destFile = new File(destDir, file);
                    // Nếu là thư mục, gọi đệ quy để sao chép
                    if (sourceFile.isDirectory()) {
                        copyDirectory(sourceFile, destFile);
                    } else {
                        // Nếu là tệp, sao chép tệp
                        copyFile(sourceFile, destFile);
                    }
                }
            }
        } else {
            System.out.println("Source directory does not exist or is not a directory.");
        }
    }

    // Phương thức sao chép tệp
    private static void copyFile(File sourceFile, File destFile) throws IOException {
        // Tạo luồng đọc và ghi
        try (InputStream in = new FileInputStream(sourceFile);
             OutputStream out = new FileOutputStream(destFile)) {
            // Sao chép dữ liệu từ tệp nguồn sang tệp đích
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }
}
