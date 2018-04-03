package util;


import java.io.File;
import java.io.IOException;

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
}
