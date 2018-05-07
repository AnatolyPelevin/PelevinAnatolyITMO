/**
 * 1. Написать рефлексивный toString():

 public static String toString(Object o)

 Метод принимает любой объект и формирует строку на основании его полей без необходимости переопределять метод toString() объекта.

 Добавить аннотацию @Exclude для полей, которые не должны быть включены в результирующую строку.

 Необходимые методы:
 Object.getClass()

 Class.getDeclaredFields()
 Class.getSimpleName()
 Class.isPrimitive()
 Class.isArray()

 Field.getName()
 Field.getType()
 Field.setAccessible()
 Field.get()
 */

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
//        System.out.println("****************TEST OBJECT**********************");
//        ReflectionToString reflectionToString = new ReflectionToString(new Object());
//        String str = reflectionToString.getObjectInfo();

        System.out.println("****************TEST TestClass**********************");
        TestClass testClass = new TestClass();
        ReflectionToString reflectionToString2 = new ReflectionToString(testClass);

        String str2 = reflectionToString2.getObjectInfo();
        System.out.println(str2);
    }


}
