package edu.csc413.calculator.operators;

public class PowerOperator extends Operator {
    public int priority() {
        return 1;
    }
    static {
        operators.put("^", new PowerOperator());
    }
}
