package edu.csc413.calculator.operators;

public class SubtractOperator extends Operator {
    public int priority() {
        return 3;
    }
    static {
        operators.put("-", new SubtractOperator());
    }
}
