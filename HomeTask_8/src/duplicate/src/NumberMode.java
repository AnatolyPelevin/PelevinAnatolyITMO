package duplicate.src;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class NumberMode extends ModeStart {
    private String strToParse ;
    private final String REG_EXP = "[^0-9]";
    Map<Object, Long> numCount = null;

    private final String FILE_DUPL_NUM = "DuplicateNumbers.txt";
    private final String FILE_DUPL_NUM_COUNT = "DuplicateNumbersCount.txt";

    @Override
    public void perform(){
        System.out.println("Number mode start!");
        super.perform();

        final Object[] strAsObjectArray = super.getStrAsObjectArray(REG_EXP);
        System.out.println(Arrays.toString(strAsObjectArray));
        numCount = Arrays.stream(strAsObjectArray)
                .map(ch -> new AbstractMap.SimpleEntry<>(ch, 1))
                .collect(groupingBy(AbstractMap.SimpleEntry::getKey, counting()));

        numCount.forEach((k,v)->System.out.println(k + " - " + v));

        ArrayList<String> list = new ArrayList<>();

        numCount.entrySet()
                .stream()
                .peek(obj -> list.add(
                        "[" + obj.getKey() + " - " + Long.toString(obj.getValue()) + "]")
                )
                .collect(Collectors.toList())
                ;

        super.resultToFile(FILE_DUPL_NUM, new ArrayList<>(Arrays.asList(strAsObjectArray)));
        super.resultToFile(FILE_DUPL_NUM_COUNT, list);
    }


}
