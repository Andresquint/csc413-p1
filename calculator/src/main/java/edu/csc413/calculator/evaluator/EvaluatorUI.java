package edu.csc413.calculator.evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.csc413.calculator.evaluator.Evaluator;

public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField txField = new TextField();
    private Panel buttonPanel = new Panel();


    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] bText = {
            "7", "8", "9", "+", "4", "5", "6", "- ", "1", "2", "3",
            "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression (or entry), clears last entry up until the last operator.
     */
    private Button[] buttons = new Button[bText.length];

    public static void main(String argv[]) {
        EvaluatorUI calc = new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.txField.setPreferredSize(new Dimension(600, 50));
        this.txField.setFont(new Font("Courier", Font.BOLD, 28));
        txField.setText("");

        add(txField, BorderLayout.NORTH);
        txField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button bt;
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            bt = new Button(bText[i]);
            bt.setFont(new Font("Courier", Font.BOLD, 28));
            buttons[i] = bt;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * This function is triggered anytime a button is pressed
     * on our Calculator GUI.
     *
     * @param eventObject Event object generated when a
     *                    button is pressed.
     */
    public void actionPerformed(ActionEvent eventObject) {
        String temp = txField.getText();
        if (eventObject.getSource() == buttons[0]) {
            txField.setText(temp + "7");
        } else if (eventObject.getSource() == buttons[1]) {
            txField.setText(temp + "8");
        } else if (eventObject.getSource() == buttons[2]) {
            txField.setText(temp + "9");
        } else if (eventObject.getSource() == buttons[3]) {
            txField.setText(temp + "+");
        } else if (eventObject.getSource() == buttons[4]) {
            txField.setText(temp + "4");
        } else if (eventObject.getSource() == buttons[5]) {
            txField.setText(temp + "5");
        } else if (eventObject.getSource() == buttons[6]) {
            txField.setText(temp + "6");
        } else if (eventObject.getSource() == buttons[7]) {
            txField.setText(temp + "-");
        } else if (eventObject.getSource() == buttons[8]) {
            txField.setText(temp + "1");
        } else if (eventObject.getSource() == buttons[9]) {
            txField.setText(temp + "2");
        } else if (eventObject.getSource() == buttons[10]) {
            txField.setText(temp + "3");
        } else if (eventObject.getSource() == buttons[11]) {
            txField.setText(temp + "*");
        } else if (eventObject.getSource() == buttons[12]) {
            txField.setText(temp + "0");
        } else if (eventObject.getSource() == buttons[13]) {
            txField.setText(temp + "^");
        } else if (eventObject.getSource() == buttons[14]) {
            //evaluate expression and set text as result
            try {
                Evaluator ev = new Evaluator();
                int res;
                res = ev.eval(temp);
                txField.setText(Integer.toString(res));
            } catch (Exception error) {
                System.out.println(error.getMessage());
            }
        } else if (eventObject.getSource() == buttons[15]) {
            txField.setText(temp + "/");
        } else if (eventObject.getSource() == buttons[16]) {
            txField.setText(temp + "(");
        } else if (eventObject.getSource() == buttons[17]) {
            txField.setText(temp + ")");
        } else if (eventObject.getSource() == buttons[18]) {
            txField.setText("");
        } else if (eventObject.getSource() == buttons[19]) {
            if (Character.isDigit(temp.charAt(temp.length() - 1))) {
                temp = temp.substring(0, temp.length() - 1);
            }
            txField.setText(temp);
        }
    }
}
