package generictest;

import java.util.List;

public class TestGeneric {
    public static <T> void fillList(List<T> lst, T val) {

    }

    public static void printList(List<?> lst) {
        for(Object l:lst){
            System.out.println("List item: " + "{ "+l + " }");
        }
    }
}
