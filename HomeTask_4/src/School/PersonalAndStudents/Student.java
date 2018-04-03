package School.PersonalAndStudents;

public class Student extends AbsrtactHumane{
    public Class getClassForStudent() {
        return classForStudent;
    }

    public void setClassForStudent(Class classForStudent) {
        this.classForStudent = classForStudent;
    }

    public boolean isStudenNow() {
        return isStudenNow;
    }

    public void setStudenNow(boolean studenNow) {
        isStudenNow = studenNow;
    }

    private Class classForStudent;
    private boolean isStudenNow;

    public Student(String Name) {
        super(Name);
    }

    @Override
    public void setClass(Class classForStudent) {
        this.classForStudent = classForStudent;
    }

    public void learn(){
        System.out.println("Learn");
    }


}
