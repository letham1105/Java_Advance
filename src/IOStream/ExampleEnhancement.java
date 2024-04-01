package IOStream;

import java.io.*;

public class ExampleEnhancement {

    public static void main(String[] args) {
        String path = "D:\\java_advance";
        File directory = new File(path);
        displayFilesAndFolders(directory);
    }

    // Phương thức hiển thị tất cả các tệp/thư mục (bao gồm cả trong các thư mục con, tất cả các cấp)
    public static void displayFilesAndFolders(File directory) {
        File[] filesAndFolders = directory.listFiles();
        if (filesAndFolders != null) {
            for (File fileOrFolder : filesAndFolders) {
                if (fileOrFolder.isDirectory()) {
                    System.out.print("<Dir> ");
                    System.out.println(fileOrFolder.getName());
                    // Gọi đệ quy để hiển thị tất cả các tệp/thư mục trong thư mục con
                    displayFilesAndFolders(fileOrFolder);
                } else {
                    System.out.println(fileOrFolder.getName());
                }
            }
        }
    }
}
