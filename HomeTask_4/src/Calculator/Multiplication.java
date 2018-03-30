package Calculator;

public class Multiplication extends OperationBase {
    @Override
    public float excecute(float a, float b) {
        System.out.println("Do Multiplication");
        return a * b;
    }
}
