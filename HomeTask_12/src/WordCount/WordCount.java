package WordCount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class WordCount {
    private static Map<String, Integer> result = new HashMap<>();
    String fileName;

    public WordCount(String fileName) {
        if (fileName == null || "".equals(fileName)) {
            this.fileName = "HelpFiles/wp.txt";

        } else {
            this.fileName = "HelpFiles/wp.txt";
        }
    }


    public  void showTopNWords(int N) throws IOException, InterruptedException {
        System.out.println("*******Show Top N Words!*******");
        List<String> lines;
        try (InputStream inputStream = WordCount.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                 lines = bufferedReader.lines().collect(toList());
             }

        if (lines !=null && !lines.isEmpty()) {
            int cpuCount = Runtime.getRuntime().availableProcessors();
            List<WordCountThread> workers = new ArrayList<>();

            chopIntoParts(lines, cpuCount).stream().forEach(lst->{
                WordCountThread wct = new WordCountThread(lst);
                workers.add(wct);
            });

            if(workers !=null && !workers.isEmpty()){
                for (WordCountThread worker : workers){
                    worker.start();
                    worker.join();
                }

                Map<String, Integer> sortedMap = result.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));

                sortedMap.entrySet().stream().limit(N).forEach(obj -> System.out.println("Word: " + obj.getKey() + " - " + obj.getValue()));

                }
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


    private static class WordCountThread extends Thread {
        private List<String> words;
        private Map<String, Integer> wordCnt = new HashMap<>();

        public WordCountThread(List<String> words) {
            this.words = words;
        }

        @Override
        public void run() {
            words.stream().
                    forEach((String line) -> {
                        String[] strings = line.toLowerCase().replaceAll("[-.?!)(,:;'\"*]", "").split("\\s");
                        Arrays.stream(strings).forEach((String str) -> putWordCount(str));
                    }
            );
            synchronized (result){
                result.putAll(wordCnt);
            }
        }

        private void putWordCount(String word) {
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
