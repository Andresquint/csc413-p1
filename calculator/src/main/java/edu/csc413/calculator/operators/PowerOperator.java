package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator extends Operator {
    public int priority() {
        return 3;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        int tempVal2 = op2.getValue();
        int tempVal1 = op1.getValue();
        int tempVal3 = tempVal1;
        for (int i = 1; i < tempVal2; i++) {
            tempVal1 = tempVal1 * tempVal3;
        }
        return new Operand(tempVal1);
    }
}
