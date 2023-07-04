// CurrencyConverterApp/src/CurrencyConverterApp.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyConverterApp extends JFrame {
    private JTextField usdTextField;
    private JTextField eurTextField;
    private JButton usdToEurButton;
    private JButton eurToUsdButton;
    private DecimalFormat df;

    private double usdToEurRate = 0.85; // 1 USD = 0.85 EUR

    public CurrencyConverterApp() {
        setTitle("Currency Converter");
        setSize(400, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        df = new DecimalFormat("#.##");

        usdTextField = new JTextField();
        eurTextField = new JTextField();
        usdToEurButton = new JButton("USD to EUR");
        eurToUsdButton = new JButton("EUR to USD");

        add(new JLabel("USD:"));
        add(usdTextField);
        add(new JLabel("EUR:"));
        add(eurTextField);
        add(usdToEurButton);
        add(eurToUsdButton);

        usdToEurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertUsdToEur();
            }
        });

        eurToUsdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertEurToUsd();
            }
        });
    }

    private void convertUsdToEur() {
        try {
            double usdAmount = Double.parseDouble(usdTextField.getText());
            double eurAmount = usdAmount * usdToEurRate;
            eurTextField.setText(df.format(eurAmount));
        } catch (NumberFormatException ex) {
            eurTextField.setText("Invalid input");
        }
    }

    private void convertEurToUsd() {
        try {
            double eurAmount = Double.parseDouble(eurTextField.getText());
            double usdAmount = eurAmount / usdToEurRate;
            usdTextField.setText(df.format(usdAmount));
        } catch (NumberFormatException ex) {
            usdTextField.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverterApp app = new CurrencyConverterApp();
            app.setVisible(true);
        });
    }
}
