package edu.csc413.calculator.evaluator;

/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */
public class Operand {
    /**
     * construct operand from string token.
     */
    private int currentOperandValue = 0;
    public Operand(String token) {
        currentOperandValue = Integer.parseInt(token);
    }

    /**
     * construct operand from integer
     */
    public Operand(int value) {
        currentOperandValue = value;
    }

    /**
     * return value of operand
     */
    public int getValue() {
        return currentOperandValue;
    }

    /**
     * Check to see if given token is a valid
     * operand.
     */
    public static boolean check(String token) {
        return false;
    }
}
