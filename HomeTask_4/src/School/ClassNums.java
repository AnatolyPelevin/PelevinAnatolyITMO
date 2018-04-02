package School;

public class ClassNums {

    public static String getClassNum (int num, ClassLetter classLetter) throws SchoolExceptions {
        if (num > 0 && num <= 10) {
           return "Class " + num + classLetter;
        } else {
            System.out.println("Wrong class number!");
            throw new SchoolExceptions("Class num must be between 1 and 10!",-1);
        }
    }

    public static boolean checkClassNumFormat(String classNum){
        if (classNum == null || "".equals(classNum)) {
            System.out.println("Wrong class number!");
            return false;
        }

         return classNum.matches("^Class [1-9]{1}[0-1]?+[A-D]{1}");
    }
}
