package School;

import java.util.ArrayList;
import java.util.List;

public final class PerformSchool {

    public static void performSchool() {
        System.out.println("****Start test School.****");
        System.out.println("Init school.");
        School school = new School(1, "SPB, str.Street 1, h.1");
        System.out.println(school.toString());

        System.out.println("Init classes List.");
        List<Class> listOfClasses = new ArrayList<Class>(school.MAX_CLASSES);
        ClassLetter[] classLetter =  ClassLetter.values();
        try {
            for(int i =1; i<=10;i++){
                for (int j =0; j < classLetter.length; j++){
                    listOfClasses.add(new Class(ClassNums.getClassNum(i, classLetter[j])));
                }
            }
        } catch (SchoolExceptions schoolExceptions) {
            schoolExceptions.printStackTrace();
        }

     //   listOfClasses.stream().forEach(obj->System.out.println(obj.toString()));
        System.out.println("Classes added to school!");
        school.initClassList((ArrayList)listOfClasses);
        System.out.println(school.toString());
        school.printClassInformation();
        System.out.println("Test adding existing class!");
        try {
            Class existsClass  = new Class(ClassNums.getClassNum(1, ClassLetter.A));
            school.addClass(existsClass);
        } catch (SchoolExceptions schoolExceptions) {
            schoolExceptions.printStackTrace();
        }

        System.out.println("Test removing existing class!");
        try {
            Class existsClass  = new Class(ClassNums.getClassNum(1, ClassLetter.A));
            school.removeClass(existsClass);
        } catch (SchoolExceptions schoolExceptions) {
            schoolExceptions.printStackTrace();
        }
    }
}
