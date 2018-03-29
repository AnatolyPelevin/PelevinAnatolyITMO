package HouseTask;

import java.util.Objects;

public class Humane {
    private String name;
    private int age;
    private int wantFloor;

    public Humane (String name, int wantFloor){
       this.name = name;
       this.wantFloor  = wantFloor;
    }

    public Humane (String name, int wantFloor, int age){
        this(name, wantFloor);
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWhatFloor() {
        return wantFloor;
    }

    public void setWhatFloor(int wantFloor) {
        this.wantFloor = wantFloor;
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, wantFloor);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Humane)) {
            return false;
        }
        Humane humane = (Humane) o;
        return wantFloor == humane.wantFloor &&
                Objects.equals(name, humane.name);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("[Humane] - Name:  ")
                .append(getName())
                .append(" want floor: ")
                .append(getWhatFloor())
                .toString();
    }
}
