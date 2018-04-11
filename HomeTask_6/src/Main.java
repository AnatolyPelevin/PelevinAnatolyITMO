/**
 * 1. Сосчитать частоту встречаемости слов в книге War and peace.
 2. Собрать все слова в группы по количеству букв: например, в одну группу попадут слова: [the, war, jar, get, met...], в другую [on, up, no, of...].
 3. Вывести топ 10 самых частых слов и фраз.
 4. Тоже, что и 2, но без артиклей, частиц и тп (без the, a, on, to...).
 5. Вывести частоту встречаемости букв анг алфавита по этой книге. Вывести в процентах для каждой буквы.
 Плюс доделать задание с лекции
 */


public class Main {
    public static void main(String[] args) {
        CollectionTraining c  = new CollectionTraining("");
//        c.showUniqueWordsCount();
//        c.showTopNWords(10);
//        c.groupWordsByLetterCount();
//        c.groupWordsByLetterCountWithFilter();
        c.lettersCountByPercent();
    }
}
