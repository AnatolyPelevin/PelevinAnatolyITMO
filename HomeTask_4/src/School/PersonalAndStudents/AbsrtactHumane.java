package School.PersonalAndStudents;

import java.util.Date;

public abstract class AbsrtactHumane implements HumaneInterface {
    protected String Name;
    protected Date dayOfBirth;

    public AbsrtactHumane(String Name){
        this.Name = Name;
        }
    @Override
    public String getName() {
        return Name;
    }

    @Override
    public void setName(String name) {
      this.Name = name;
    }

    @Override
    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    @Override
    public void setDayOfBirth(Date dayOfBirth) {
      this.dayOfBirth = dayOfBirth;
    }

    public abstract void setClass(Class classToAdd);
}
