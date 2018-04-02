package Calculator;

import java.util.ArrayList;
import java.util.List;

public class PerformCalculator {
    public static void performCalculator () {
        System.out.println("****Start test Calculator.****");
        List<OperationBase> opList = new ArrayList<OperationBase>();
        opList.add(OperationFactory.createOperation(OperationType.ADD));
        opList.add(OperationFactory.createOperation(OperationType.SUB));
        opList.add(OperationFactory.createOperation(OperationType.MULT));
        opList.add(OperationFactory.createOperation(OperationType.DIV));

        Accumulator accumulator = new Accumulator (4,null);
        opList.forEach(opl-> {
            accumulator.setOperation(opl);
            System.out.println(accumulator.accumulate(2));
        });
    }
}
