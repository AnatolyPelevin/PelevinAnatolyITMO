import HomeTask9Utils.ExceptionEnum;
import HomeTask9Utils.FileTaskException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class FileCopyTask {
    private File one, two;
    private Path one_path, two_path;

    public FileCopyTask(){
        this.one = new File("1.txt");
        createFile( this.one);
        this.one_path = one.toPath();
        this.two= new File("2.txt");
        createFile( this.two);
        this.two_path = two.toPath();
    }

    public FileCopyTask( File one, File two){
        setOne(one);
        setTwo(two);
        createFile( this.one);
        createFile( this.two);
        this.one_path = one.toPath();
        this.two_path = two.toPath();
    }

    public FileCopyTask( Path one_path, Path two_path){
        setOne_path(one_path);
        setTwo_path(two_path);
        createFile( this.one);
        createFile( this.two);
        this.one = one_path.toFile();
        this.two= two_path.toFile();
    }
    public File getOne() {
        return one;
    }

    public void setOne(File one) {
        if (one ==null) {
            throw new FileTaskException(ExceptionEnum.NO_FILE.getErrorText());
        }
        this.one = one;
        createFile( this.one);
    }

    public File getTwo() {
        return two;
    }

    public void setTwo(File two) {
        if (two ==null) {
            throw new FileTaskException(ExceptionEnum.NO_FILE.getErrorText());
        }
        this.two = two;
        createFile(this.two);
    }

    public Path getOne_path() {
        return one_path;
    }

    public void setOne_path(Path one_path) {
        if (one_path ==null) {
            throw new FileTaskException(ExceptionEnum.NO_FILE.getErrorText());
        }
        this.one_path = one_path;
    }

    public Path getTwo_path() {
        return two_path;
    }

    public void setTwo_path(Path two_path) {
        if (two_path ==null) {
            throw new FileTaskException(ExceptionEnum.NO_FILE.getErrorText());
        }
        this.two_path = two_path;
    }


    public void fileCopy() throws IOException {
            Files.copy(one_path, two_path , StandardCopyOption.REPLACE_EXISTING);
      }

    private boolean checkFile( File file) {
        System.out.println(file.getName() + ":");
        if (file.exists()) {
            System.out.println("File exists.");
            return true;
        } else {
            System.out.println("File does not exists.");
            return false;
        }
    }

    private void createFile(File file){
        if (!checkFile(file)){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeToFileTest(File file, String str){
        if(!checkFile(file)) {
            throw new FileTaskException(ExceptionEnum.NO_FILE.getErrorText());
        }
        if (str ==null || "".equals(str)){
            System.out.println("No test string to insert into file!");
            return;
        }

        Path path = file.toPath();

        if (!Files.isWritable(path)) {
            throw new FileTaskException(ExceptionEnum.NOT_WRITABLE.getErrorText());
        }

        try {
            Files.write(path, str.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException err) {
            System.err.println(err);
        }
    }

    public void splitFile(String fileName, Integer chunkCount) throws IOException {
        String fName = "HelpFile/test.txt";
        Integer chCount = 10;
        if (fileName != null && !"".equals(fileName)) {
            fName = fileName;
        } else {
            System.out.println("Will use default test file - test.txt, from resource!");
        }

        if (chunkCount != null && chunkCount >= 1) {
            chCount = chunkCount;
        } else {
            System.out.printf("Will use default chunkCount = %d, from resource!", chCount);
            System.out.println("");
        }


        try (InputStream inputStream = getClass().getResourceAsStream(fName);
             BufferedInputStream bufferedReader = new BufferedInputStream(inputStream)) {

            File file = new File(getClass().getResource(fName).getFile());
            long fileSize = file.length();
            long filePartSize = fileSize / chCount;
            byte[] buf = new byte[1024];
            String baseFileName = file.getName();
            String name  = baseFileName.substring( 0,baseFileName.length() - 4);

            for (int i = 1; i <= chCount; i++) {
                String partFileName = name + "_part_" + i + ".txt";
                try (OutputStream outputStream = Files.newOutputStream(Paths.get(partFileName));
                     BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {
                    int currentSize = 0;
                    while ( currentSize < filePartSize) {
                        int byteCount = bufferedReader.read(buf);
                        if( byteCount ==-1) {
                            break;
                        }
                        bufferedOutputStream.write(buf, 0, byteCount);
                        currentSize += byteCount;
                    }
                }
            }

        }
    }

  public void  joinFiles (String toFile, String dirFrom, String filter) throws IOException {
        String fDir = "D:\\FOR_ME\\ITMO\\PelevinAnatolyITMO";
        String tFile = "joinFiles.txt";

        if (dirFrom != null && !"".equals(dirFrom)) {
            fDir = dirFrom;
      } else {
          System.out.println("Will use default dir to join! " + fDir);
      }

      if (toFile != null && !"".equals(toFile)) {
          tFile = toFile;
      } else {
          System.out.println("Will use default file to join! " +  tFile);
      }

      Path pathToFile = Paths.get(tFile);
      try(BufferedWriter bufferedWriter =  Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)){
          Files.list(Paths.get(fDir)).filter(path ->filterFile(path,filter)).forEach(path -> process(path, bufferedWriter));
      }
  }

  private boolean filterFile(Path path, String filter) {
      File file  = path.toFile();
      if (!file.isFile()){
          return false;
      }

      if (filter!=null && !"".equals(filter)) {
         if (file.getName().matches(filter)) {
             return true;
         }
      }
      return true;
  }

  private void process(Path path, BufferedWriter  bufferedWriter){
    File file  = path.toFile();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            bufferedReader.lines().forEach(line -> {
                try {
                    bufferedWriter.write(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
  }
}
