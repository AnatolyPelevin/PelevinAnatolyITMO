package School.PersonalAndStudents;

import java.util.Date;

public abstract class AbsrtactHumane implements HumaneInterface {
    private String Name;
    private Date dayOfBirth;

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

    public abstract void setClass();
}
