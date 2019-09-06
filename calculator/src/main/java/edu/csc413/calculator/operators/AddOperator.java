package edu.csc413.calculator.operators;

public class AdditionOperator extends Operator{
    AdditionOperator(){
        string operator = "+";
    }
    public int priority()
    {
        return 4;
    }
}
