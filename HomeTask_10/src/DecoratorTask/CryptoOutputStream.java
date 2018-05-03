package DecoratorTask;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CryptoOutputStream extends FilterOutputStream {
    private byte[] password;

    public CryptoOutputStream(OutputStream out, byte[] password) {
        super(out);
        if (password ==null) {
            throw new IllegalArgumentException("Password can't be  null!");
        }
        this.password = password;
    }


    @Override
    public void write(byte b[]) throws IOException {
         write(b, 0, b.length);
    }

    @Override
    public void write(byte b[], int off, int len) throws IOException {
        decodeEncodeByte(b);
        out.write(b, off, len);
    }

    private void decodeEncodeByte(byte[] textArr){
        int length  = textArr.length;
        byte[] result = new byte[length];
        System.arraycopy (textArr, 0, result, 0, length);

        for (int i = 0; i < length; i++) {
            textArr[i] = (byte) (result[i] ^ password[i % password.length]);
        }

    }
}
