package School;

import java.util.Objects;

public class Class {
    private String classNum;
    public static final int MAX_STUDENTS = 20; //just for trainning

    public Class (String classNum) throws SchoolExceptions{
        if (ClassNums.checkClassNumFormat(classNum)) {
            this.classNum = classNum;
        } else {
            System.out.println("Wrong class number!");
            throw new SchoolExceptions("Class num must b between 1 and 10!",-1);
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(classNum);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Class)) {
            return false;
        }
        Class humane = (Class) o;
        return Objects.equals(classNum, humane.classNum);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("[Class] - Number:  ")
                .append(classNum)
                .toString();
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
}
