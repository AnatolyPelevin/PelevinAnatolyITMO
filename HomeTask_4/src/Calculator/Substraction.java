package Calculator;

public class Substraction extends OperationBase {
    @Override
    public float excecute(float a, float b) {
        System.out.println("Do Substraction");
        return a - b;
    }
}
