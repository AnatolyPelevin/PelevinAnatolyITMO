package WordCount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class WordCountPool {
    private String fileName;
    private static Map<String, Integer> result = new HashMap<>();
    public WordCountPool(String fileName) {
        if (fileName == null || "".equals(fileName)) {
            this.fileName = "HelpFiles/wp.txt";

        } else {
            this.fileName = "HelpFiles/wp.txt";
        }
    }

    public  void showTopNWords(int N) throws IOException, InterruptedException, ExecutionException {
        System.out.println("*******Show Top N Words!*******");
        List<String> lines;
        try (InputStream inputStream = WordCount.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            lines = bufferedReader.lines().collect(toList());
        }

        if (lines !=null && !lines.isEmpty()) {
            int cpuCount = Runtime.getRuntime().availableProcessors();
            ExecutorService pool = Executors.newFixedThreadPool(cpuCount);
            List<Future<Map<String, Integer>>> futs = new ArrayList<>();
            List<WordCountTask> workers = new ArrayList<>();

            for (int i = 0; i < cpuCount; i++) {
                // TODO submit WordCountTask into pool, futures add to futs
                chopIntoParts(lines, cpuCount).stream().forEach(lst->{
                    WordCountTask wct = new WordCountTask(lst);
                    workers.add(wct);
                    Future<Map<String, Integer>> fut = pool.submit(wct);
                    futs.add(fut);
                });
            }

            for (Future<Map<String, Integer>> fut : futs) {
                // TODO collect results
                Map<String, Integer> cm = fut.get();
                if (result.isEmpty()) {
                    result.putAll(cm);
                } else {
                    cm.forEach((k, v) -> result.merge(k, v, (v1, v2) -> v1 + v2));
                }
            }

            pool.shutdown();
                Map<String, Integer> sortedMap = result.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));

                sortedMap.entrySet().stream().limit(N).forEach(obj -> System.out.println("Word: " + obj.getKey() + " - " + obj.getValue()));

            }
        System.out.println("**************");
    }

    private static <T>List<List<T>> chopIntoParts( final List<T> ls, final int iParts )
    {
        final List<List<T>> lsParts = new ArrayList<List<T>>();
        final int iChunkSize = ls.size() / iParts;
        int iLeftOver = ls.size() % iParts;
        int iTake = iChunkSize;

        for( int i = 0, iT = ls.size(); i < iT; i += iTake )
        {
            if( iLeftOver > 0 )
            {
                iLeftOver--;

                iTake = iChunkSize + 1;
            }
            else
            {
                iTake = iChunkSize;
            }

            lsParts.add( new ArrayList<T>( ls.subList( i, Math.min( iT, i + iTake ) ) ) );
        }

        return lsParts;
    }


    private static class WordCountTask implements Callable<Map<String, Integer>> {
        private final List<String> lines;

        public WordCountTask(List<String> lines) {
            this.lines = lines;
        }

        @Override
        public Map<String, Integer> call() throws Exception {
            Map<String, Integer> res = new HashMap<>();
            lines.stream().
                    forEach((String line) -> {
                                String[] strings = line.toLowerCase().replaceAll("[-.?!)(,:;'\"*]", "").split("\\s");
                                Arrays.stream(strings).forEach((String str) -> putWordCount(str,res));
                            }
                    );
            return res;
        }

        private void putWordCount(String word, Map<String, Integer> wordCnt) {
            if (!word.isEmpty()) {
                Integer count = wordCnt.get(word);
                if (count == null) {
                    count = 0;
                }
                wordCnt.put(word, ++count);
            }
        }

    }
}
