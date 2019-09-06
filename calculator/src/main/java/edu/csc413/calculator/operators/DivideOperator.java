package edu.csc413.calculator.operators;

public class DivideOperator extends Operator {
    public int priority() {
        return 2;
    }
    static {
        operators.put("/", new DivideOperator());
    }
}
