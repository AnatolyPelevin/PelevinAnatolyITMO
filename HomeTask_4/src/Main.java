import Calculator.PerformCalculator;

/**
 *
 * 1. Создать класс Operation с методом execute(int a, int b)

 У него наследники: Сложение, Вычитание, Умножение, Деление
 и у каждого соответственно своя реализация родительского execute(int a, int b)

 Следующий класс Accumulator с методом accumulate(int a). В зависимости от переданных в конструктор Accumulator
 int value и Operation, в методе accumulate(int a) изменяется value (Сложение - value + a, и тд).
 У класса Accumulator также должен быть метод getValue()
 */

public class Main {
    public static void main(String[] args){
        PerformCalculator.performCalculator();
    }
}