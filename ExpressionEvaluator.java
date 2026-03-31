package semesterproject;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Stack;

public class ExpressionEvaluator extends JFrame {
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton evaluateButton;

    public ExpressionEvaluator() {
        setTitle("Expression Evaluator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(new Color(0x1E1E1E));

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 18));
        inputField.setForeground(Color.WHITE);
        inputField.setBackground(new Color(0x2B2B2B));
        inputField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        outputArea = new JTextArea();
        outputArea.setFont(new Font("Monospace", Font.PLAIN, 16));
        outputArea.setForeground(new Color(0x00FF00));
        outputArea.setBackground(new Color(0x2B2B2B));
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        evaluateButton = new JButton("Evaluate");
        evaluateButton.setFont(new Font("Arial", Font.BOLD, 16));
        evaluateButton.setForeground(Color.WHITE);
        evaluateButton.setBackground(new Color(0x007ACC));
        evaluateButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        evaluateButton.addActionListener(e -> evaluateExpression());

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(new Color(0x1E1E1E));
        mainPanel.add(inputField, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        mainPanel.add(evaluateButton, BorderLayout.SOUTH);

        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void evaluateExpression() {
        String expression = inputField.getText().trim();
        if(expression.isEmpty()) {
            outputArea.setText("Error: Please enter an expression");
            return;
        }

        try {
            String postfix = convertToPostfix(expression);
            double result = evaluatePostfix(postfix);

            outputArea.setText("Input Expression: " + expression + "\n");
            outputArea.append("Postfix Expression: " + postfix + "\n");
            outputArea.append("Result: " + result);
        } catch(Exception e) {
            outputArea.setText("Error: Invalid Expression");
        }
    }

    private String convertToPostfix(String infix) {
        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        int i = 0;

        while(i < infix.length()) {
            char c = infix.charAt(i);

            if(Character.isDigit(c)) {
                // Multi-digit number handling
                while(i < infix.length() && (Character.isDigit(infix.charAt(i)) || infix.charAt(i)=='.')) {
                    postfix.append(infix.charAt(i));
                    i++;
                }
                postfix.append(' '); // space separator
                continue;
            } else if(c == '(') {
                stack.push(c);
            } else if(c == ')') {
                while(!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop()).append(' ');
                }
                if(!stack.isEmpty() && stack.peek() == '(') stack.pop();
            } else if(c == '+' || c == '-' || c == '*' || c == '/') {
                while(!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop()).append(' ');
                }
                stack.push(c);
            }
            i++;
        }

        while(!stack.isEmpty()) {
            postfix.append(stack.pop()).append(' ');
        }

        return postfix.toString().trim();
    }

    private int precedence(char operator) {
        switch(operator) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            default: return -1;
        }
    }

    private double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = postfix.split(" ");

        for(String token : tokens) {
            if(token.isEmpty()) continue;

            if(token.matches("\\d+(\\.\\d+)?")) { // number with optional decimal
                stack.push(Double.parseDouble(token));
            } else {
                if(stack.size() < 2) throw new RuntimeException("Invalid Expression");
                double b = stack.pop();
                double a = stack.pop();

                switch(token) {
                    case "+": stack.push(a+b); break;
                    case "-": stack.push(a-b); break;
                    case "*": stack.push(a*b); break;
                    case "/": stack.push(a/b); break;
                    default: throw new RuntimeException("Unknown operator");
                }
            }
        }

        if(stack.isEmpty()) throw new RuntimeException("Invalid Expression");
        return stack.pop();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExpressionEvaluator().setVisible(true));
    }
}
