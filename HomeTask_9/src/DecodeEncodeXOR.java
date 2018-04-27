//package DecodeEncodeXORTask;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DecodeEncodeXOR {
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

        try (InputStream inputStream = getClass().getResourceAsStream(fNameBase);
             BufferedInputStream bufferedReader = new BufferedInputStream(inputStream)) {

            try (OutputStream outputStream = Files.newOutputStream(Paths.get(fNameDecoded));
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {
                byte[] buf = new byte[1024];
                int byteCount;
                while ((byteCount = bufferedReader.read(buf)) >0) {
                   bufferedOutputStream.write(decodeEncodeByte(buf, cKeyByte), 0, byteCount);
                }
            }
        }
    }

    private byte[] decodeEncodeByte(byte[] textArr, byte[] codeKey){
        int length  = textArr.length;
        byte[] result = new byte[length];

        for (int i = 0; i < length; i++) {
            result[i] = (byte) (textArr[i] ^ codeKey[i % codeKey.length]);
        }
        return result;
    }



}
