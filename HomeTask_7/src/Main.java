import enumtest.Planets;

/**
 1. Написать enum, который перечисляет планеты Солнечной системы и возвращает массу планеты, ее радиус и радиус орбиты.

 2. Сделать List, Stack и Queue generic:
 List<String> strings = new ArrayList<>();
 strings.add("string");
 Stack<Integer> stack = new LinkedList<>();
 stack.push(10)
 и т.д.

 */


public class Main {
    public static void main(String[] args) {
        for (Planets p : Planets.values())
            System.out.println(p.returnPlanetInfo());

    }
}
