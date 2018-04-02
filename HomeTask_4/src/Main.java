import Calculator.PerformCalculator;
import PersonalOrganizer.PerformPersonalOrganizer;
import School.PerformSchool;

/**
 *
 * 1. Создать класс Operation с методом execute(int a, int b)

 У него наследники: Сложение, Вычитание, Умножение, Деление
 и у каждого соответственно своя реализация родительского execute(int a, int b)

 Следующий класс Accumulator с методом accumulate(int a). В зависимости от переданных в конструктор Accumulator
 int value и Operation, в методе accumulate(int a) изменяется value (Сложение - value + a, и тд).
 У класса Accumulator также должен быть метод getValue()

 2. Представьте себе школу, подумайте, как можно спроектировать эту систему на классах,
 выделите основные сущности и подумайте, какие у них будут возможности.
 На основе этого опишите классы и методы (Сколько успеете!)

 3. Мы будем делать ежедневник.
 Для начала также нужно выдельть основные классы и методы и описать их.
 Классы и медоты выделяйте и описывайте на свое усмотрение, потом обсудим!
 Не забывайте про наследование и модификаторы доступа


 */

public class Main {
    public static void main(String[] args){
        PerformCalculator.performCalculator();
        PerformSchool.performSchool();
        PerformPersonalOrganizer.PerformPersonalOrganizer();
    }
}