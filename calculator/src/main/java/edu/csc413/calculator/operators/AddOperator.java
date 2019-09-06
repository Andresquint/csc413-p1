package edu.csc413.calculator.operators;

public class AddOperator extends Operator {
    public int priority() {
        return 3;
    }
    static {
        operators.put("+", new AddOperator());
    }
}
