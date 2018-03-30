package Calculator;

public class Addition extends OperationBase {
    @Override
    public float excecute(float a, float b) {
        System.out.println("Do Addition");
        return a + b;
    }
}
