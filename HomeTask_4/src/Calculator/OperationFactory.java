package Calculator;

import Calculator.OperationType;

public class OperationFactory {

    public static OperationBase createOperation (OperationType type) {
        switch(type) {
            case ADD: {
               return new Addition();
            }

            case SUB: {
                return new Substraction();
            }

            case MULT: {
                return new Multiplication();
            }

            case DIV: {
                return new Division();
            }
            default: {
                return new OperationBase();
            }
        }

    }
}

