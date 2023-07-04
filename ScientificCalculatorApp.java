import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificCalculatorApp extends JFrame {
    private JTextField inputField;
    private JLabel resultLabel;

    public ScientificCalculatorApp() {
        setTitle("Scientific Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        resultLabel = new JLabel();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "sqrt", "log", "sin", "cos"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        add(inputField, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();
            String inputText = inputField.getText();

            switch (buttonText) {
                case "=":
                    calculateResult();
                    break;
                case "sqrt":
                    calculateSquareRoot();
                    break;
                case "log":
                    calculateLogarithm();
                    break;
                case "sin":
                    calculateSine();
                    break;
                case "cos":
                    calculateCosine();
                    break;
                default:
                    inputField.setText(inputText + buttonText);
            }
        }

        private void calculateResult() {
            String expression = inputField.getText();
            try {
                double result = evaluateExpression(expression);
                resultLabel.setText("Result: " + result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid expression");
            }
        }

        private void calculateSquareRoot() {
            String expression = inputField.getText();
            try {
                double number = Double.parseDouble(expression);
                double result = Math.sqrt(number);
                resultLabel.setText("Result: " + result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input for square root");
            }
        }

        private void calculateLogarithm() {
            String expression = inputField.getText();
            try {
                double number = Double.parseDouble(expression);
                double result = Math.log10(number);
                resultLabel.setText("Result: " + result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input for logarithm");
            }
        }

        private void calculateSine() {
            String expression = inputField.getText();
            try {
                double number = Double.parseDouble(expression);
                double result = Math.sin(Math.toRadians(number));
                resultLabel.setText("Result: " + result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input for sine");
            }
        }

        private void calculateCosine() {
            String expression = inputField.getText();
            try {
                double number = Double.parseDouble(expression);
                double result = Math.cos(Math.toRadians(number));
                resultLabel.setText("Result: " + result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input for cosine");
            }
        }

        private double evaluateExpression(String expression) {
            return new Object() {
                int index = -1, ch;

                void nextChar() {
                    ch = (++index < expression.length()) ? expression.charAt(index) : -1;
                }

                boolean isDigit() {
                    return Character.isDigit(ch);
                }

                double parseNumber() {
                    StringBuilder sb = new StringBuilder();
                    while (isDigit()) {
                        sb.append((char) ch);
                        nextChar();
                    }
                    return Double.parseDouble(sb.toString());
                }

                double parseExpression() {
                    nextChar();
                    double result = parseTerm();
                    while (ch == '+' || ch == '-') {
                        char op = (char) ch;
                        nextChar();
                        double term = parseTerm();
                        if (op == '+')
                            result += term;
                        else
                            result -= term;
                    }
                    return result;
                }

                double parseTerm() {
                    double result = parseFactor();
                    while (ch == '*' || ch == '/') {
                        char op = (char) ch;
                        nextChar();
                        double factor = parseFactor();
                        if (op == '*')
                            result *= factor;
                        else
                            result /= factor;
                    }
                    return result;
                }

                double parseFactor() {
                    if (ch == '(') {
                        nextChar();
                        double result = parseExpression();
                        if (ch == ')')
                            nextChar();
                        return result;
                    } else if (isDigit()) {
                        return parseNumber();
                    }
                    throw new RuntimeException("Invalid expression");
                }
            }.parseExpression();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ScientificCalculatorApp app = new ScientificCalculatorApp();
            app.setVisible(true);
        });
    }
}
