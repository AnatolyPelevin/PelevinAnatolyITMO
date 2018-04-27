import java.io.*;

public class DecodeByFile {

    public  void encodeDecodeByFile(File sourceFile, File encodedFile, File keyFile)throws IOException {
        try (InputStream inputStreamSoutceFile = new BufferedInputStream(new FileInputStream(sourceFile));
             InputStream inputStreamCodeFile = new BufferedInputStream(new FileInputStream(keyFile));
             OutputStream outputStreamEncodedFile = new BufferedOutputStream(new FileOutputStream(encodedFile))){

            long keyLen = keyFile.length();
            inputStreamCodeFile.mark((int) keyLen);
            int fromSource, fromKey;
            long index = 0;
            while((fromSource =  inputStreamSoutceFile.read()) != -1){
                if (++ index > keyLen){
                    index = 0;
                    inputStreamCodeFile.reset();
                }
                fromKey =  inputStreamCodeFile.read();
                outputStreamEncodedFile.write(fromSource ^ fromKey);
            }
        }
    }

    public  void TestClass(){
        File sourceFile = new File(getClass().getResource("HelpFile/test.txt").getFile());
        File keyFile = new File(getClass().getResource("HelpFile/key.txt").getFile());
        File encodedFile = new File("EncodedByFileTest.txt");
        File decodedFile = new File("EncodedByFileTest.txt");

        try {
            encodeDecodeByFile (sourceFile, encodedFile,keyFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            encodeDecodeByFile (encodedFile, decodedFile,keyFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
