package IOStream;

import java.io.*;

public class CountFileExample {

    // Method to count all files in the folder (including all subfolders)
    public static int countFile(String path) {
        int count = 0;
        File f = new File(path);

        String[] names = f.list();

        for (int i = 0; i < names.length; i++) {
            File fi = new File(path + "\\" + names[i]);

            if (fi.isDirectory())
                count += countFile(fi.getAbsolutePath());
            else
                count++;
        }
        return count;
    }

    // Method to calculate the size (in bytes) of a folder (including all subfolders)
    public static long calculateFolderSize(String path) {
        long size = 0;
        File folder = new File(path);

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    size += calculateFolderSize(file.getAbsolutePath());
                } else {
                    size += file.length();
                }
            }
        }

        return size;
    }

    public static void main(String[] args) {
        String folderPath = "D:\\java_advance";
        System.out.println("Number of files in the folder: " + CountFileExample.countFile(folderPath));
        System.out.println("Size of the folder (in bytes): " + CountFileExample.calculateFolderSize(folderPath));
    }
}
