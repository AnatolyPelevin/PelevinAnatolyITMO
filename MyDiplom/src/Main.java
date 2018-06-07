import reader.RecordReaderException;
import showWork.ShowWork;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            ShowWork.showWork();
        } catch (RecordReaderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
