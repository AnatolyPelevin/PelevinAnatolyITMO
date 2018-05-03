import ListenerTask.PerformListner;

/**
 * 1. Используя паттерн Decorator создать шифрующие с помощью XOR потоки ввода/вывода.
 * Они могут наследовать FilterInputStream и FilterOutputStream (удобнее их наследовать,
 * но можно и прямиком InputStream и OutputStream).
 * Т.о. конструктор этих потоков должен принимать пароль в виде массива байт и поток, который он декорирует.

 Использование должно выглядеть следующим образом:
 // Трафик автоматически шифрует и дешифруется:
 InputStream in = new CryptoInputStream(socket.getInputStream(), password);
 OutputStream out = new CryptoOutputStream(socket.getOutputStream(), password);

 // Сохраняем в шифрованный файл и читаем из шифрованного файла:
 InputStream in = new CryptoInputStream(new FileInputStream("test.bin"), password);
 OutputStream out = new CryptoOutputStream(new FileOutputStream("test.bin"), password);

 Для простоты можете начать имплементацию, где пароль - один байт.

 2. Реализовать сигнализации, реагирующие на повышение температуры (Observer).
 Класс Sensor инкрементально повышает температуру, и на каждое изменение температуры оповещает слушателей (сигнализации).
 Их должно быть три, соответствующие уровням тревоги: Green (100), Yellow (300), Red (600).

 Описываете интерфейс Alarm, имеющий метод void tempChanged(int temp),
 и подписываете три реализации к датчику на оповещения.

 Сигнализации сработают по преодолению определенного порога температуры:
 100 градусов - Green
 300 градусов - Green, Yellow
 600 градусов - Green, Yellow, Red

 Срабатывание - вывод в консоль сообщения. Если сигнализация сработала,
 то сообщение не повторяется на дальнейшее повышение температуры, но если опустится ниже порога,
 а потом опять преодолеет, то выведется снова.
 */
public class Main {
    public static void main(String[] args) {
        PerformListner.performListner();
    }
}
