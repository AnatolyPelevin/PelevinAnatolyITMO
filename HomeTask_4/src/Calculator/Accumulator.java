package Calculator;

public class Accumulator {
    private float value;
    private OperationBase operation;

    public Accumulator(float value, OperationBase operation){
        this.operation =  operation;
        this.value =  value;
    }

    public float accumulate(float a) {
        return operation.excecute(value, a);
    };

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public OperationBase getOperation() {
        return operation;
    }

    public void setOperation(OperationBase operation) {
        this.operation = operation;
    }


}
