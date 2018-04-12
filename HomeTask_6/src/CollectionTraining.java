
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class CollectionTraining {
    Map<String, Integer> counterMap = new HashMap<>();
    String fileName;

    public CollectionTraining(String fileName) {
        if (fileName == null || "".equals(fileName)) {
            this.fileName = "HelpFiles/wp.txt";

        } else {
            this.fileName = "HelpFiles/wp.txt";
        }
        calcUniqueWordsCount();
    }

    public void showUniqueWordsCount() {
        System.out.println("*******Count Unique Words!*******");
        counterMap.entrySet().forEach(obj -> System.out.println("Word: " + obj.getKey() + " - " + obj.getValue()));
        System.out.println("**************");
    }

    public void showTopNWords(int N) {
        System.out.println("*******Show Top N Words!*******");

        Map<String, Integer> sortedMap = counterMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        sortedMap.entrySet().stream().limit(N).forEach(obj -> System.out.println("Word: " + obj.getKey() + " - " + obj.getValue()));

        System.out.println("**************");
    }


    public void groupWordsByLetterCount() {
        System.out.println("*******Group words by letters' count!*******");
        Map<String, Integer> letterCount = counterMap.entrySet()
                .stream()
                .collect(Collectors.toMap(Entry::getKey, stringIntegerEntry -> stringIntegerEntry.getKey().toCharArray().length,
                (e1, e2) -> e1, LinkedHashMap::new));

        letterCount.entrySet().stream()
                .collect(groupingBy(Entry::getValue))
                .entrySet()
                .forEach(obj -> System.out.println("Word: " + obj.getValue()));

        System.out.println("**************");
    }


    public void groupWordsByLetterCountWithFilter() {
        System.out.println("*******Group words by letters' count! Without few words!*******");
        Map<String, Integer> letterCount = counterMap.entrySet()
                .stream()
                .collect(Collectors.toMap(Entry::getKey, stringIntegerEntry -> stringIntegerEntry.getKey().toCharArray().length,
                        (e1, e2) -> e1, LinkedHashMap::new));

        List<String> excludeList = Arrays.asList("1", "2", "3","4","5","6","7","8","9",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "07", "10", "11", "12", "13", "15", "17", "18", "20", "22", "23", "24", "25", "26", "27",
                "30", "31", "40", "50", "60", "62", "4x", "70", "80", "90", "ab", "ah", "am", "an", "as",
                "at", "au", "ax", "be", "bl", "bs", "by", "ce", "de", "do", "dr", "ds", "du", "eh", "em",
                "en", "et", "ex", "fo", "fr", "go", "ha", "he", "hi", "hm", "ho", "id", "if", "ii", "il",
                "im", "in", "is", "it", "iv", "ix", "ja", "je", "ke", "la", "le", "lu", "ma", "me", "mi",
                "mr", "my", "na", "ne", "ni", "nn", "no", "ny", "of", "oh", "on", "oo", "or", "ou", "pe",
                "ps", "qu", "re", "rs", "sa", "se", "si", "so", "ss", "st",
                "to", "tt", "tu", "un", "up", "us", "ve", "vi", "vy", "wa", "we", "xi", "xv", "xx", "ze", "zu");

        letterCount.entrySet().stream()
                .filter(o-> !excludeList.contains(o.getKey()))
                .collect(groupingBy(Entry::getValue))
                .entrySet()
                .forEach(obj -> System.out.println("Word: " + obj.getKey() + " - " + obj.getValue()));

        System.out.println("**************");
    }


    public void lettersCountByPercent() {
        System.out.println("*******Show letters count by percent!*******");

       Map<Character, Long> letterCount = null;
        try (InputStream inputStream = getClass().getResourceAsStream(fileName)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            letterCount =
                    bufferedReader.lines()
                    .flatMap(line -> Arrays.stream(line.trim().split("\\s")))
                    .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase().trim())
                            .filter(word -> word.length() > 0)
                            .map(word->word.chars().mapToObj(ch -> (char)ch))
                            .flatMap(s-> s)
                            .collect(Collectors.toList())
                            .stream()
                            .map(ch -> new AbstractMap.SimpleEntry<>(ch, 1))
                            .collect(groupingBy(AbstractMap.SimpleEntry::getKey, counting()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final long cnt  = letterCount.values().stream().mapToInt(Number::intValue).sum();
         System.out.println(String.format("All letters in text  = %d", cnt));

         letterCount.forEach((k, v) -> System.out.println(String.format("Letter %s ==>> count: %f %%", k, (float)((float)v*100/(float)cnt))));

    System.out.println("**************");
    }


    /**
     * Использовал регулярное выражение с удалением символов. Делит слова не совсем корреткно.
     * В задании 5 (lettersCountByPercent) более корретнко работает. Решил оставтиь, как пример для
     * тренировки.
     */
    private void calcUniqueWordsCount() {
        try (InputStream inputStream = getClass().getResourceAsStream(fileName)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            bufferedReader.lines()
                    .forEach((String line) -> {
                                String[] strings = line.toLowerCase().replaceAll("[-.?!)(,:;'\"*]", "").split("\\s");
                                Arrays.stream(strings).forEach((String str) -> putWordCount(str));
                            }
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void putWordCount(String word) {
        if (!word.isEmpty()) {
            Integer count = counterMap.get(word);
            if (count == null) {
                count = 0;
            }
            counterMap.put(word, ++count);
        }
    }

    private int getLetterCount(String word) {
        return word.toCharArray().length;
    }
}


