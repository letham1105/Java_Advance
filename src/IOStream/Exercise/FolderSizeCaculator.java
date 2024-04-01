package IOStream.Exercise;

import java.io.File;

public class FolderSizeCaculator {
    public static long caculateFolderSize(File folder){
        long size = 0;
        // kiểm tra nếu đối tượng là thư mục và tồn tại
        if(folder.exists() && folder.isDirectory()){
            File[] files = folder.listFiles();
            if(files != null){
                for (File file : files){
                    if (file.isFile()){
                        size += file.length();
                    }else{
                        size += caculateFolderSize(file);
                    }
                }
            }
        }
        return size;
    }

    public static void main(String[] args) {
        // đường dẫn đến thư mục cần kiểm tra
        String folderPath = "D:\\java_advance";
        File folder = new File(folderPath);
        // Tính kích thước của thư mục và hiển thị kết quả
        long folderSize = caculateFolderSize(folder);
        System.out.println("Size of the folder (in bytes): " +folderSize);
    }
}
