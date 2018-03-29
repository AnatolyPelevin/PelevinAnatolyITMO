package HouseTask;

public class HouseTaskException extends Exception  {
    private int number; //error number just 4 training
    public int getNumber(){return number;}

    public HouseTaskException(String message, int num){
        super(message);
        number = num;
    }

}
