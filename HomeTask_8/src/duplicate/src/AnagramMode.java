package duplicate.src;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class AnagramMode extends ModeStart {
    private final String FILE_ANAGRAM = "Anagram.txt";
    private final String REG_EXP = "[^a-zA-Z]";
    @Override
    public void perform(){
        System.out.println("Anagram mode start!");
        super.perform();
        final Object[] strAsObjectArray = super.getStrAsObjectArray(REG_EXP);
        Map<String, String> anagram = null;
        ArrayList<String> list = new ArrayList<>();

        System.out.println(Arrays.toString(strAsObjectArray));

        anagram =  Arrays.stream(strAsObjectArray)
                  .map(chArr-> {
                     char[] ch = ((String)chArr).toCharArray();
                     Arrays.sort(ch);
                     String str = Arrays.toString(ch);
                     AbstractMap.SimpleEntry<String,String> simpleEntry = new AbstractMap.SimpleEntry<>((String)chArr, str);
                     return simpleEntry;})
                 .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

//        anagram.entrySet().stream()
//                .collect(groupingBy(Map.Entry::getValue))
//                .entrySet()
//                .forEach(obj -> System.out.println("Word: " + obj.getValue()));

          anagram.entrySet().stream()
                .collect(groupingBy(Map.Entry::getValue))
                .entrySet()
                .forEach(obj -> {list.add("Word: " + obj.getValue());
                                 System.out.println("Word: " + obj.getValue());});


        super.resultToFile(FILE_ANAGRAM, list);

    }


}
