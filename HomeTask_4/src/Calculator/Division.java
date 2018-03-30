package Calculator;

public class Division extends OperationBase {
    @Override
    public float  excecute(float a, float b) {
        System.out.println("Do Division");
        return a / b;
    }
}
