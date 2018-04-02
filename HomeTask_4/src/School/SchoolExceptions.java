package School;

public class SchoolExceptions extends Exception  {
    private int number;
    public int getNumber(){return number;}

    public SchoolExceptions(String message, int num){
        super(message);
        number = num;
    }
}
