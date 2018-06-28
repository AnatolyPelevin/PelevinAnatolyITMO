import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class WordCountLamda {
    private String fileName;
    private static Map<String, Integer> result = new HashMap<>();

    public WordCountLamda(String fileName) {
        if (fileName == null || "".equals(fileName)) {
            this.fileName = "HelpFiles/wp.txt";

        } else {
            this.fileName = "HelpFiles/wp.txt";
        }
    }

    private static Path prepareFile() throws IOException {
        InputStream in = WordCountLamda.class.getClassLoader().getResourceAsStream("HelpFiles/fixedincome_bo_euro.out");

        File tmp = File.createTempFile("fixedincome_bo_euro", "out");
        tmp.deleteOnExit();

        Path wapPath = tmp.toPath();

        Files.copy(in, wapPath, StandardCopyOption.REPLACE_EXISTING);
        return wapPath;
    }

    public  void showTopNWords(int N) throws IOException, InterruptedException, ExecutionException {
        System.out.println("*******Show Top N Words!*******");
        Path wapPath = prepareFile();
        Map<String, Long> result =
                Files.lines(wapPath)
                        .parallel()
                        .map(line -> line.toLowerCase().replaceAll("\\pP", " "))
                        .flatMap(line -> Arrays.stream(line.split(" ")))
                        .map(String::trim)
                        .filter(word -> !"".equals(word))
                        .collect(groupingBy(identity(), counting()))
                        .entrySet().parallelStream()
                        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                        //.limit(10)
                        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(result);

        System.out.println("**************");
    }
}
