package edu.csc413.calculator.evaluator;

import edu.csc413.calculator.operators.Operator;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
    private Stack<Operand> operandStack;
    private Stack<Operator> operatorStack;
    private StringTokenizer tokenizer;
    private static final String DELIMITERS = "()+-*^/ ";

    public Evaluator() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    public int eval(String expression) {
        String token;

        //push a beginning operator to prevent popping empty stack in multiple places
        operatorStack.push(Operator.getOperator("("));

        // The 3rd argument is true to indicate that the delimiters should be used
        // as tokens, too. But, we'll need to remember to filter out spaces.
        this.tokenizer = new StringTokenizer(expression, DELIMITERS, true);


        while (this.tokenizer.hasMoreTokens()) {
            // filter out spaces
            if (!(token = this.tokenizer.nextToken()).equals(" ")) {
                // check if token is an operand
                if (Operand.check(token)) {
                    //push the new integer into operand
                    operandStack.push(new Operand(token));
                } else {
                    if (!Operator.check(token)) {
                        System.out.println(token);
                        System.out.println("*****invalid token******");
                        throw new RuntimeException("*****invalid token******");
                    }

                    //now that the token is valid, create a new operator using that token to work with
                    Operator newOperator = Operator.getOperator(token);

                    if (token.equals("(")) {
                        operatorStack.push(Operator.getOperator("("));
                    } else if (token.equals(")")) {
                        while ((!operatorStack.peek().equals(Operator.getOperator("(")) && operandStack.size() > 1)) {
                            // note that when we eval the expression 1 - 2 we will
                            // push the 1 then the 2 and then do the subtraction operation
                            // This means that the first number to be popped is the
                            // second operand, not the first operand - see the following code
                            Operator oldOpr = operatorStack.pop();
                            Operand op2 = operandStack.pop();
                            Operand op1 = operandStack.pop();
                            operandStack.push(oldOpr.execute(op1, op2));
                        }
                        if (operatorStack.peek().equals(Operator.getOperator("("))) {
                            operatorStack.pop();
                        }
                        if (!newOperator.equals(Operator.getOperator(")")))
                            newOperator = Operator.getOperator(token);
                    } else
                        while (!operatorStack.peek().equals(Operator.getOperator("(")) && !newOperator.equals((Operator.getOperator("("))) && operandStack.size() > 1 && operatorStack.peek().priority() >= newOperator.priority()) {
                            Operator oldOpr = operatorStack.pop();
                            Operand op2 = operandStack.pop();
                            Operand op1 = operandStack.pop();
                            operandStack.push(oldOpr.execute(op1, op2));
                        }
                    //check for negative values by checking for "(-" in that order in the operatorStack
                    //if "(-" is found, it will pop the subtraction operator and treat it as multiplying by -1 by calling multiplyOperator
                    if ((operatorStack.size() > operandStack.size()) && operandStack.size() == 1 && 1 == operatorStack.search(Operator.getOperator("-"))){
                        operatorStack.pop();
                        Operand op1 = operandStack.pop();
                        Operand op2 = new Operand(-1);
                        Operator tempMult = Operator.getOperator("*");//MultiplyOperator();
                        operandStack.push(tempMult.execute(op1, op2));
                    }
                    if (!newOperator.equals(Operator.getOperator(")")) && !newOperator.equals((Operator.getOperator("("))))
                        operatorStack.push(newOperator);
                }
            }
        }

        int value = 0;
        //run through all remaining operands
        while (operandStack.size() > 1) {
            Operator testOp = operatorStack.pop();
            Operand op2 = operandStack.pop();
            Operand op1 = operandStack.pop();
            operandStack.push(testOp.execute(op1, op2));
        }
        //get the last value in the operand stack
        if (operandStack.size() == 1) {
            Operator testOp = operatorStack.pop();
            Operand op1 = operandStack.pop();
            value = op1.getValue();
        }

        // Control gets here when we've picked up all of the tokens; you must add
        // code to complete the evaluation - consider how the code given here
        // will evaluate the expression 1+2*3
        // When we have no more tokens to scan, the operand stack will contain 1 2
        // and the operator stack will have + * with 2 and * on the top;
        // In order to complete the evaluation we must empty the stacks,
        // that is, we should keep evaluating the operator stack until it is empty;
        // Suggestion: create a method that processes the operator stack until empty.

        //Don't forget to change the return value!
        return value;
    }
}
