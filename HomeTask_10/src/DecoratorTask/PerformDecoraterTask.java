package DecoratorTask;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PerformDecoraterTask {
    private static String fNameBase = "HelpFile/test.txt";
    private static String fNameDecoded = "DECODED_test.txt";
    private static String fNameEncoded = "ENCODED_test.txt";
    private static String cKey = "pipi";

    public void decodeEncode(String fileNameBase, String fileNameDecoded, String codeKey) throws IOException {
        final byte[] cKeyByte;
        if (fileNameBase != null && !"".equals(fileNameBase)) {
            fNameBase = fileNameBase;
        } else {
            System.out.println("Will use default test file - test.txt, from resource!");
        }

        if (codeKey != null && !"".equals(codeKey)) {
            cKey = codeKey;
        } else {
            System.out.println("Will use decode-encode key! - " + cKey);
        }

        cKeyByte = cKey.getBytes();

        if (fileNameDecoded != null && !"".equals(fileNameDecoded)) {
            fNameDecoded = fileNameDecoded;
        } else {
            System.out.println("Will use default decoded file! - " + fNameDecoded);
        }

        try (
             CryptoInputStream bufferedReader = new CryptoInputStream(new FileInputStream("test.txt"), cKeyByte)) {

            try (OutputStream outputStream = Files.newOutputStream(Paths.get(fNameDecoded));
               //  CryptoOutputStream bufferedOutputStream = new CryptoOutputStream(outputStream, cKeyByte)
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {
                byte[] buf = new byte[1024];
                int byteCount;
                while ((byteCount = bufferedReader.read(buf)) >0) {
                    bufferedOutputStream.write(buf, 0, byteCount);
                }
            }
        }


        try (
                BufferedInputStream bufferedReader = new BufferedInputStream(new FileInputStream(fNameDecoded))) {

            try (OutputStream outputStream = Files.newOutputStream(Paths.get(fNameEncoded));
                 CryptoOutputStream bufferedOutputStream = new CryptoOutputStream(outputStream, cKeyByte)
                ) {
                byte[] buf = new byte[1024];
                int byteCount;
                while ((byteCount = bufferedReader.read(buf)) >0) {
                    bufferedOutputStream.write(buf, 0, byteCount);
                }
            }
        }
    }

}
