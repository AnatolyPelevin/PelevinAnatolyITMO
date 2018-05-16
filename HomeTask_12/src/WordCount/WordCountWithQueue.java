package WordCount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class WordCountWithQueue {
    private String fileName;
    private static final String STOP = new String();
    private static final Map<String, Integer> STOP_MAP = new HashMap<>();
    private static Map<String, Integer> result = new HashMap<>();
    private static BlockingQueue<Map<String, Integer>> resultBlockingQueue = new LinkedBlockingQueue<>();
    private static AtomicInteger count;

    public WordCountWithQueue(String fileName) {
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

            BlockingQueue<String> linesQueue = new LinkedBlockingQueue<>();
            linesQueue.addAll(lines);

            for (int i = 0; i < cpuCount; i++) {
                linesQueue.add(STOP);
            }

            for (int i = 0; i < cpuCount; i++) {
                WordCountThread wct = new WordCountThread(linesQueue, resultBlockingQueue);
                workers.add(wct);
                wct.start();
            }

            synchronized (count){
                count.wait();
            }

                resultBlockingQueue.stream().forEach(r->result.putAll(r));

                Map<String, Integer> sortedMap = result.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));

                sortedMap.entrySet().stream().limit(N).forEach(obj -> System.out.println("Word: " + obj.getKey() + " - " + obj.getValue()));

        }
        System.out.println("**************");
    }
//
//    private static class WordCountThreadConsumer extends Thread {
//        private BlockingQueue<Map<String, Integer>> outQueue ;
//
//        public WordCountThreadConsumer( BlockingQueue<Map<String, Integer>> outQueue) {
//            this.outQueue = outQueue;
//        }
//
//        @Override
//        public void run() {
//            while (!isInterrupted()){
//                try{
//                    Map<String, Integer> mapResult = outQueue.take();
//                    if(mapResult  == STOP_MAP){
//                        break;
//                    }
//
//                    result.put(mapResult.get);
//
//
//                } catch (InterruptedException e) {
//                    //  e.printStackTrace();
//                    return;
//                }
//            }
//
//        }
//    }




    private static class WordCountThread extends Thread {
        private BlockingQueue<String> linesQueue ;
        private BlockingQueue<Map<String, Integer>> outQueue ;
        private Map<String, Integer> wordCnt = new HashMap<>();

        public WordCountThread(BlockingQueue<String> linesQueue, BlockingQueue<Map<String, Integer>> result) {
            this.linesQueue = linesQueue;
            this.outQueue = result;
        }

        @Override
        public void run() {

            while (!isInterrupted()){
                try{
                    String line  = linesQueue.take();
                    if(line  == STOP){
                    //    outQueue.add(STOP_MAP);
                        break;
                    }

                    String[] strings = line.toLowerCase().replaceAll("[-.?!)(,:;'\"*]", "").split("\\s");
                    Arrays.stream(strings).forEach((String str) -> putWordCount(str));

                    outQueue.add(wordCnt);
                } catch (InterruptedException e) {
                  //  e.printStackTrace();
                    return;
                }
            }

            if (count.decrementAndGet() == 0) {
                count.notifyAll();
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
