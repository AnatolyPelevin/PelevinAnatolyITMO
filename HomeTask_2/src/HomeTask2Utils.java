import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeTask2Utils {
    /**
     * 1) Создайте массив из всех чётных чисел от 2 до 20 и выведите элементы массива на экран сначала в строку,
     * отделяя один элемент от другого пробелом, а затем в столбик
     * (отделяя один элемент от другого началом новой строки).
     * Перед созданием массива подумайте, какого он будет размера. 2 4 6 … 18 20 2 4 6 … 20
     */
   public static void Task1() {
       int arrayInitLength = 0;
       for (int i = 2; i <= 20; i++) {
           arrayInitLength = i % 2 == 0 ? ++arrayInitLength : arrayInitLength;
       }
       int myArray[] = new int[arrayInitLength];

       for (int i = 2, j = 0; i <= 20; i++) {
           if (i % 2 == 0) {
               myArray[j] = i;
               j++;
           }
       }
       System.out.print(Arrays.toString(myArray));
       System.out.println("");
       for (int i = 0; i <= myArray.length-1; i++) {
           System.out.println(myArray[i]);
       }
   }

      /**2) Создайте массив из всех нечётных чисел от 1 до 99,
       *  выведите его на экран в строку,
       *  а затем этот же массив выведите на экран тоже в строку,
       *  но в обратном порядке (99 97 95 93 … 7 5 3 1).
      */

      public static void Task2(){
          int arrayInitLength = 0;
          for (int i = 1; i <= 99; i++) {
              arrayInitLength = i % 2 == 0 ? arrayInitLength : ++arrayInitLength;
          }
          int [] myArray = new int[arrayInitLength];
         for (int i  = 1, j=0; i<=99; i++) {
             if (i % 2 != 0) {
                 myArray[j] = i;
                 j++;
             }
         }

          System.out.print(Arrays.toString(myArray));
          System.out.println("");
          int[] reverseArray = Arrays.stream(myArray).boxed()
                  .sorted( Collections.reverseOrder() )
                  .mapToInt( Integer::intValue ).toArray();
          System.out.print(Arrays.toString(reverseArray));
      }
     /**3) Создайте массив из 15 случайных целых чисел из отрезка [0;9].
      *  Выведите массив на экран.
      *  Подсчитайте сколько в массиве чётных элементов и выведете это количество на экран на отдельной строке.
    */

     public static void Task3(){
         int[] myArray = new int[15];
         int elemCount =0;
         for (int i=0; i< myArray.length;i++){
             Random rndForElem = new Random();
             myArray[i] = rndForElem.nextInt(10);
             elemCount = (myArray[i]>0 && myArray[i]%2==0) ? ++elemCount:elemCount;
         }
         System.out.println(Arrays.toString(myArray));
         System.out.println("Element count = " + elemCount);
      }

    /** 4) Создайте 2 массива из 5 случайных целых чисел из отрезка [0;5] каждый,
     * выведите массивы на экран в двух отдельных строках.
     * Посчитайте среднее арифметическое элементов каждого массива и сообщите,
     * для какого из массивов это значение оказалось больше (либо сообщите,
     * что их средние арифметические равны).
    */

    public static void Task4(){
        int[] array1  = new int[5];
        int[] array2  = new int[5];
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < 5; i ++) {
            Random rndForElem = new Random();
            array1[i] = rndForElem.nextInt(5);
            array2[i] = rndForElem.nextInt(5);
            sum1+=array1[i];
            sum2+=array2[i];
        }
        if (sum1/2 == sum2/2){
            System.out.println("equal");
        } else if (sum1/2 > sum2/2) {
            System.out.println("first");
        } else {
            System.out.println("second");
        }
    }
     /**5) Создайте массив из 4 случайных целых чисел из отрезка [10;99],
      * выведите его на экран в строку. Определить и вывести на экран сообщение о том,
      * является ли массив строго возрастающей последовательностью.
     */
     public static void Task5(){
         int[] array1  = new int[4];
         boolean check = true;
         int privElem = 0;
         for (int i = 0; i < 4; i ++) {
             array1[i] = (int)((Math.random()*90)+10);
             System.out.print(array1[i] + " ");

             if(i==0){
                 privElem =  array1[i];
             } else if (check && i > 0 && array1[i] > privElem) {
                 privElem = array1[i];
             } else {
                 check = false;
             }
         }
         System.out.println("");
         if (check)  {System.out.println("yes");
         } else {
             System.out.println("no");
         }


     }
      /** 6) Пользователь должен указать с клавиатуры чётное положительное число,
       * а программа должна создать массив указанного размера из случайных целых чисел из [-5;5]
       * и вывести его на экран в строку.
       * После этого программа должна определить и сообщить пользователю о том,
       * сумма модулей какой половины массива больше: левой или правой, либо сообщить,
       * что эти суммы модулей равны. Если пользователь введёт неподходящее число,
       * то программа должна требовать повторного ввода до тех пор, пока не будет указано корректное значение.

     Понадобится класс Scanner, например:
       Scanner sc = new Scanner(System.in);
     System.out.println("Введите число | строку и тп");
     int i = sc.nextInt(); для числа
     String str = sc.nextLine(); для строки
      */
      public static void Task6(){
          Scanner sc = new Scanner(System.in);
          int i=0;
          do {
              System.out.print("Enter array's length: ");
              while (true)
                  try {
                      i = Integer.parseInt(sc.nextLine());
                      break;
                  } catch (NumberFormatException nfe) {
                      System.out.print("Try again: ");
                  }
          } while (i<=0 || i%2!=0);

          int[] myArray = new int[i];
          int sumFirst = 0;
          int sumSecond = 0;
          for (int j = 0; j < myArray.length; j++) {
              myArray[j] = -5 + (int) (Math.random() * 11);
              if (j< (myArray.length/2)) {
                  sumFirst+= Math.abs(myArray[j]);
                  System.out.print( myArray[j]+ " first, ");
              } else {
                  sumSecond+= Math.abs(myArray[j]);
                  System.out.print( myArray[j]+ " second, ");
              }
          }
          System.out.println("");
          if  (sumFirst == sumSecond) {
              System.out.println("parts are equal");
          } else if (sumFirst > sumSecond) {
              System.out.println("first part");
          } else {
              System.out.println("second part");
          }

      }
    // Многомерные массивы:
      /**7) Создать двумерный массив из 5 строк по 8 столбцов в каждой из случайных целых чисел из отрезка [-99;99].
       * Вывести массив на экран. После на отдельной строке вывести на экран значение максимального элемента
       * этого массива (его индекс не имеет значения).
      */
      public static void Task7(){
        int[][] myArray = new int [5][8];

        for (int i  = 0 ; i <5; i++) {
            for (int j = 0; j< 8; j++) {
                myArray[i][j] = -99 + (int) (Math.random() * 199);
            }
        }
          System.out.println(Arrays.deepToString(myArray));
          int max =0;
          for (int i  = 0 ; i <5; i++) {
              for (int j = 0; j< 8; j++) {
                  if (max<myArray[i][j]) {
                      max = myArray[i][j];
                  }
              }
          }
          System.out.println("max element = " + max);
      }
      /**8) Для проверки остаточных знаний учеников после летних каникул,
       * учитель младших классов решил начинать каждый урок с того,
       * чтобы задавать каждому ученику пример из таблицы умножения, но в классе 15 человек,
       * а примеры среди них не должны повторяться. В помощь учителю напишите программу,
       * которая будет выводить на экран 15 случайных примеров из таблицы умножения
       * (от 2*2 до 9*9, потому что задания по умножению на 1 и на 10 — слишком просты).
       * При этом среди 15 примеров не должно быть повторяющихся
       * (примеры 2*3 и 3*2 и им подобные пары считать повторяющимися).
      */
      public static void Task8(){
          int [][] myArray = new int [15][2];
          boolean[] checkHaveTest = new boolean[9 * 9];

          for (int i = 0; i< myArray.length;){
              int[] testArray = myArray[i];
              testArray[0] = generateRandomValue();
              testArray[1] = generateRandomValue();

              int indexElemInUsageArray = testArray[0] * testArray[1] - 1;
              if (!checkHaveTest[indexElemInUsageArray]) {
                  checkHaveTest[indexElemInUsageArray] = true;
                  i++;
              }
          }

          System.out.println("All Tests: ");
          for (int i = 0; i < myArray.length; i++) {
              int[] tests = myArray[i];
              System.out.println("Test " + i + ": " + tests[0] + " * " + tests[1] + " = ?");
          }
      }

    public static int generateRandomValue() {
        final Random numberRandom = new Random();
        return numberRandom.nextInt(8) + 2;
    }
     //Строки:
      /** 1) Найти в строке указанную подстроку и заменить ее на новую.
       * Строку, ее подстроку для замены и новую подстроку вводит пользователь.
      */
      public static void Task9(){
          Scanner sc = new Scanner(System.in);
          String strMain="";
          String strWhatToReplace="";
          String strReplceValue="";
          while (strMain ==null||"".equals(strMain)) {
           System.out.println("enter main string");
              strMain = sc.nextLine();
          }
          while (strWhatToReplace ==null||"".equals(strWhatToReplace)) {
              System.out.println("enter part of main string to be replaced:");
              strWhatToReplace = sc.nextLine();
          }
          while (strReplceValue ==null||"".equals(strReplceValue)) {
              System.out.println("enter value of replace string:");
              strReplceValue = sc.nextLine();
          }
          System.out.println(strMain.replace(strWhatToReplace,strReplceValue));
      }
      /** 2) Требуется удалить из нее повторяющиеся символы и все пробелы.
       * Например, если было введено "abc cde def", то должно быть выведено "abcdef".
      */
      public static void Task10(){
          Scanner sc = new Scanner(System.in);
          String strMain="";//"abc cde def";
          while (strMain ==null||"".equals(strMain)) {
              System.out.println("enter main string");
              strMain = sc.nextLine();
          }

          strMain =  strMain.replaceAll("\\s+","");
          char[] stringAsCharArray = strMain.toCharArray();
          StringBuilder strBuilder = new StringBuilder();
          Set<Character> charSetForstring = new LinkedHashSet<Character>();

          for (char charElem : stringAsCharArray) {
              if(charSetForstring.add(charElem) ){
                  strBuilder.append(charElem);
              }
          }
          System.out.println(strBuilder.toString());

      }
      /**   3) Вводится строка, содержащая буквы, целые неотрицательные числа и иные символы.
       * Требуется все числа, которые встречаются в строке, поместить в отдельный целочисленный массив.
       * Например, если дана строка "дом 48, корпус 9, парадная 7, этаж 4",
       * то в массиве должны оказаться числа 48, 9, 7 и 4
     */
      public static void Task11(){

          Scanner sc = new Scanner(System.in);
          String strMain="";//"дом 48, корпус 9, парадная 7, этаж 4";
          while (strMain ==null||"".equals(strMain)) {
              System.out.println("enter main string: ");
              strMain = sc.nextLine();
          }

          String[] array = (strMain.replaceAll("\\D+", " ").trim()).split(" ");
          int[] myArray = new int[array.length];
          for (int i = 0; i < array.length; i++) {
              myArray[i] = Integer.valueOf(array[i]);
          }

          System.out.println(Arrays.toString(myArray));
      }
}
