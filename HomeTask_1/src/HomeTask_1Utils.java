import java.text.DecimalFormat;
import java.util.Scanner;

public  class HomeTask_1Utils {

    /**1) Создать программу, выводящую на экран ближайшее к 10 из двух чисел,
     * записанных в переменные m и n. Например,
     * среди чисел 8.5 и 11.45 ближайшее к десяти 11.45.
     * */
    static void Task1(Double var1, Double var2, Double var3){
        double m;
        double n;
        double nearest;
        double compare;
        if (var1 == null) {
            System.out.println("Var1 can't be Null");
            return;
        }
        if (var2 == null){
            System.out.println("Var2 can't be Null");
            return;
        }
        if (var3 == null){
            System.out.println("Var3 can't be Null");
            return;
        }
        compare = var3;
        m = Math.abs(var1 - compare);
        n = Math.abs(var2 - compare);

        if (m == n) {
            System.out.println("Var1 equals Var2. Value  = " + var1);
            return;
        }

        nearest =  m < n ? var1 : var2;
        System.out.println("Nearest Value  = " + nearest);
    }

    /**
     * 2) В три переменные a, b и c записаны три вещественных числа.
     * Создать программу, которая будет находить
     * и выводить на экран вещественные корни квадратного уравнения ax²+bx+c=0, либо сообщать, что корней нет.
     */

     static void Task2(){
        double a,b,c;
        System.out.println("Enter a, b and c:");
        Scanner inScanner = new Scanner(System.in);

        a = inScanner.nextDouble();
        b = inScanner.nextDouble();
        c = inScanner.nextDouble();

        Task2(a, b, c);
    }

    static void Task2(double args[]){
      if(args == null) {
          System.out.println("Args must be specified!");
          return;
      }
      if(args.length<3) {
          System.out.println("Not enough arguments!");
          return;
      }
        Task2(args[0], args[1], args[2]);
    }

    static void Task2(double a, double b, double c) {
        double Descrimenant;

        System.out.println("Solving equation:");
        System.out.println("ax^2 + bx + c = 0");

        if (a==0 && b ==0 && c ==0) {
            System.out.println("All args are zero. Can't build equation!");
            return;
        }

        Descrimenant = b * b - 4 * a * c;
        if (Descrimenant > 0) {
            double x1, x2;
            x1 = (-b - Math.sqrt(Descrimenant)) / (2 * a);
            x2 = (-b + Math.sqrt(Descrimenant)) / (2 * a);
            System.out.println("Roots of the equation are: x1 = " + x1 + ", x2 = " + x2);
        }
        else if (Descrimenant == 0) {
            double x;
            x = -b / (2 * a);
            System.out.println("the equation has one root only: x = " + x);
        }
        else {
            System.out.println("The equation doesn't have roots!");
        }
    }

    /**
     * 3) Создать программу, выводящую на экран   наибольшую цифру любого трехзначного натурального числа.
     * Примеры работы программы:
     * В числе 208 наибольшая цифра 8 В числе 774 наибольшая цифра 7 В числе 613 наибольшая цифра 6
     */

    static void Task3(Long numberToCheck){
        if (numberToCheck ==null) {
            System.out.println("No number! Please enter Value!");
            return;
        }
        char[] numberAsChars = numberToCheck.toString().toCharArray();
        char maxValue = '0';
        for (char numberAsChar : numberAsChars) {
            maxValue = (char) Math.max(numberAsChar, maxValue);
        }
        System.out.printf("Number = %s", numberToCheck);
        System.out.println();

        System.out.printf("Max digit = %s", maxValue);
        System.out.println();
    }

    /**
     * 4) В три переменные a, b и c явно записаны программистом три целых попарно неравных между собой числа.
     * Создать программу, которая переставит числа в переменных таким образом
     * , чтобы при выводе на экран последовательность a, b и c оказалась строго возрастающей.

     Числа в переменных a, b и c: 3, 9, -1
     Возрастающая последовательность: -1, 3, 9

     Числа в переменных a, b и c: 2, 4, 3
     Возрастающая последовательность: 2, 3, 4

     Числа в переменных a, b и c: 7, 0, -5
     Возрастающая последовательность: -5, 0, 7
     */

    static void Task4(int[] arg){
        if (arg == null){
            System.out.println("Arg not specified!");
            return;
        }

        for (int i = arg.length - 1; i >=0; i--){
          for(int j = 0; j < i; j++){
              if (arg[j]>arg[j+1]) {
                  int temp=arg[j];
                  arg[j]=arg[j+1];
                  arg[j+1]=temp;
              }
          }
        }

        for (int anArg : arg) {
            System.out.print(anArg + "  ");
        }
    }

    /**
     * 1) Создайте программу, выводящую на экран все четырёхзначные числа последовательности 1000 1003 1006 1009 1012 1015 ….
     */

    static void Task5 (){
//        for(int j = 0; j <  arg.length; j++) {
//            System.out.print(arg[j] + "  ");
//        }
        System.out.println("All numbers with 4 digits - ");
        for (int i=1000; i<=9999;i+=3){
            System.out.print(i + " ");
        }
    }

    /**
     * 2) Создайте программу, выводящую на экран все неотрицательные элементы последовательности 90 85 80 75 70 65 60 ….
     */
//    static void Task6 (int[] arg){
//        for(int j = 0; j <  arg.length; j++) {
//            if (arg[j]>0)
//            System.out.print(arg[j] + "  ");
//        }
//    }

    static void Task6 (){
        System.out.println("All positive numbers");
        for(int j = 90; j >0; j-=5) {
            System.out.print( j + "  ");
        }
    }
    /**
     * 3) Создайте программу, выводящую на экран первые 20 элементов последовательности 2 4 8 16 32 64 128 ….
     */
    static void Task7 (int[] arg){
        int stop = 20;
        stop = arg.length >= stop ? arg.length : stop;
        for(int j = 0; j <=  stop - 1 ; j++) {
                System.out.print(arg[j] + "  ");
        }
    }

    /**
     * 4) Проверьте,  является ли  натуральное число — простым.
     */
    static void Task8 (int num){
        for ( int i=2; i < num; i++) {
            if ( num%i == 0) {
                System.out.println("simple!!!");
                return;
            }
        }
        System.out.println("not simple!");
    }
    /**
     * 5) Выведите на экран первые 11 членов последовательности Фибоначчи. Первый и второй
     * члены последовательности равны единицам, а каждый следующий — сумме двух предыдущих.
     */

    static void Task9(){
       int a1 = 1;
       int a2 = 1;
       int an;
        System.out.print( a1 + " " + a2 + " ");
       for (int i = 3 ; i<=11 ; i++) {
           an = a1+a2;
           a1 = a2;
           a2=an;
           System.out.print( an + " ");
       }
    }

    /**
     * 6) В городе N проезд в трамвае осуществляется по бумажным отрывным билетам.
     * Каждую неделю трамвайное депо заказывает в местной типографии рулон билетов с номерами от 000001 до 999999.
     * «Счастливым» считается билетик у которого сумма первых трёх цифр номера равна
     * сумме последних трёх цифр, как, например,
     * в билетах с номерами 003102 или 567576.
     * Трамвайное депо решило подарить сувенир обладателю каждого счастливого билета и теперь раздумывает,
     * как много сувениров потребуется. С помощью программы подсчитайте
     * сколько счастливых билетов в одном рулоне?
     */
    static void Task10(){
       final DecimalFormat decformat = new DecimalFormat("000000");
        int count  = 0;
        final int min = 1;
        final int max = 999999;
      for (int i = min; i <= max; i++) {
          String str = decformat.format(i);
          int firstNumber = Integer.valueOf(str.substring(0, 3));
          int secondNumber = Integer.valueOf(str.substring(3, 6));
          count =  (sumDigits(firstNumber) == sumDigits(secondNumber))  ? ++count : count;
      }
        System.out.print( "Lucky tickets' count =  " + count);
    }

    private static long sumDigits(long i) {
        return i == 0 ? 0 : i % 10 + sumDigits(i / 10);
    }

    /**7) Электронные часы показывают время в формате от 00:00 до 23:59.
     * Подсчитать сколько раз за сутки случается так, что слева
     * от двоеточия показывается симметричная комбинация для той,
     * что справа от двоеточия (например, 02:20, 11:11 или 15:51).
     *
     */
    static void Task11(){
       int count =  0;
        for (int i = 0;i<24; i++){
           for (int j =0; j<=59; j++) {
              // count =  (sumDigits(i) == sumDigits(j))  ? ++count : count;   -- thought that need same sum of numbers first
              count =  (i/10%10==j%10 && i%10==j/10%10)  ? ++count : count;

               if(i/10%10==j%10 && i%10==j/10%10)
                   System.out.println(i + " " + j);

           }
       }
        System.out.print( "Symmetrical time count =  " + count);
    }
}
