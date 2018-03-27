import FigureTask.TestFigure;

/**
 1. Сделать библиотеку, которая ведет учет книг. Должно быть как минимум два класса: Book и Library. \
 Library имеет два метода: void put(Book book, int quantity) и int get(Book book, int quantity).
 Каждой книге в библиотеке соответствует счетчик, показывающий количество хранящихся книг,
 при добавлении книги - счетчик увеличивается, при извлечении - уменьшается на число quantity.
 Поля класса Book: author, title, pagesNum.
 Библиотека хранит ограниченное число книг, сколько - на ваше усмотрение.


 2. Создать фигуры: Circle, Rectangle, Triangle, у которых будут координаты.
 Все фигуры должны иметь методы, которые возвращают площадь и периметр (для окружности - длина окружности).
 */

public class Main {
    public static void main(String[] args){
        TestLibrary.testLibrary();
        System.out.println("**************************************************************");
        TestFigure.testFigure();
    }
}


