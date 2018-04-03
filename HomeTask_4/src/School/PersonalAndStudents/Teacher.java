package School.PersonalAndStudents;

import java.util.List;

public class Teacher extends AbsrtactHumane{

    private List<Class> classes;

    public Teacher(String Name) {
        super(Name);
    }

    @Override
    public void setClass(Class classToAdd) {

    }

     public void teach(){
        System.out.println("Teach");
    }
}
