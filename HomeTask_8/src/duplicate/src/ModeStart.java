package duplicate.src;

import java.io.*;
import java.util.*;

public class ModeStart {
    private String strMain = "";

    protected void perform() {
        start();
    }

    protected final String getStrMain() {
        return strMain;
    }

    protected final Object[] getStrAsObjectArray(String regEx) {
        return (Arrays.stream(strMain
                .trim()
                .toLowerCase()
                .split("\\s"))
                .map(word -> word.replaceAll(regEx, "").trim())
                .filter(word -> word.length() > 0))
                .toArray();
    }


    protected final void resultToFile(String fileName, ArrayList strings) {
        File file = new File(fileName);

        if (fileName == null || "".equals(fileName)){
           System.out.println("No file");
           return;
       }

        if (strings == null || strings.isEmpty()){
            System.out.println("No string");
            return;
        }

        if (!checkFile(file)){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try (OutputStream f = new FileOutputStream(fileName, false))
        {
            OutputStreamWriter writer = new OutputStreamWriter(f);
            BufferedWriter out = new BufferedWriter(writer);
            for(int i = 0; i < strings.size(); i++)
            {
                out.write((String)strings.get(i) + " ");
                out.flush();
            }
        }
        catch(IOException ex)
        {
            System.err.println(ex);
        }

    }

    private static boolean checkFile( File file) {
        System.out.println(file.getName() + ":");
        if (file.exists()) {
            System.out.println("File exists.");
            return true;
        } else {
            System.out.println("File does not exists.");
            return false;
        }
    }

    private String start(){
        Scanner sc = new Scanner(System.in);
        while (strMain ==null||"".equals(strMain)) {
            System.out.println("Enter the main string to process.");
            strMain = sc.nextLine();
        }
        return strMain;
    }
}
