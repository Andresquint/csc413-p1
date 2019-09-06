package edu.csc413.calculator.operators;

public class MultiplyOperator extends Operator {
    public int priority() {
        return 2;
    }
    static {
        operators.put("*", new MultiplyOperator());
    }
}
