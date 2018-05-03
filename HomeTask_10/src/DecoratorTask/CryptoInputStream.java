package DecoratorTask;


import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CryptoInputStream extends FilterInputStream
{
    private byte[] password;

    public CryptoInputStream(InputStream in, byte[] password) {
        super(in);
        if (password ==null) {
            throw new IllegalArgumentException("Password can't be  null!");
        }
        this.password = password;
    }

     @Override
     public int read(byte b[]) throws IOException {
         return read(b, 0, b.length);
     }

     @Override
     public int read(byte b[], int off, int len) throws IOException {
         int byteCount =  in.read(b, off, len);
         decodeEncodeByte(b);
         return byteCount;
     }


     private void  decodeEncodeByte(byte[] textArr){
        int length  = textArr.length;
        byte[] result = new byte[length];
        System.arraycopy (textArr, 0, result, 0, length);

        for (int i = 0; i < length; i++) {
            textArr[i] = (byte) (result[i] ^ password[i % password.length]);
        }
    }
}
