package util;


import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class FileUtils {

    public static void createFile(String filePath) throws IOException {
        if (filePath == null) {
            throw new NullPointerException("File path is null.");
        }

        File file = new File(filePath);

        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            file.createNewFile();
        }
    }

    public static void deleteFile(String filePath, boolean recursive) {
        deleteFile(new File(filePath), recursive);
    }

    public static void deleteFile(File file, boolean recursive) {
        if (recursive) {
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    deleteFile(f, true);
                }
            }
        }

        file.delete();
    }

    public static Collection<File> getFilesFromDirectory(File directory,
                                                         FileFilter filter,
                                                         boolean recursive) {
        Collection<File> container = new LinkedList<File>();

        getFilesFromDirectory(directory, filter, container, recursive);

        return container;
    }

    public static void getFilesFromDirectory(File directory,
                                             FileFilter filter,
                                             Collection<File> container,
                                             boolean recursive) {
        if (!directory.isDirectory()) {
            return;
        }

        File[] files = directory.listFiles();


        for (File file : files) {
            if (filter.accept(file)) {
                container.add(file);
            }
            if (file.isDirectory() && recursive) {
                getFilesFromDirectory(file, filter, container, recursive);
            }
        }
    }
}
