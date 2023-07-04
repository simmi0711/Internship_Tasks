import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeApp extends JFrame {
    private JButton[] buttons;
    private boolean playerXTurn;
    private int moves;

    public TicTacToeApp() {
        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[9];
        playerXTurn = true;
        moves = 0;

        initializeButtons();
    }

    private void initializeButtons() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
            buttons[i].addActionListener(new ButtonClickListener());
            add(buttons[i]);
        }
    }

    private void resetGame() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(true);
            buttons[i].setText("");
        }
        playerXTurn = true;
        moves = 0;
    }

    private boolean checkWinCondition(String symbol) {
        // Rows
        if (buttons[0].getText().equals(symbol) && buttons[1].getText().equals(symbol) && buttons[2].getText().equals(symbol))
            return true;
        if (buttons[3].getText().equals(symbol) && buttons[4].getText().equals(symbol) && buttons[5].getText().equals(symbol))
            return true;
        if (buttons[6].getText().equals(symbol) && buttons[7].getText().equals(symbol) && buttons[8].getText().equals(symbol))
            return true;

        // Columns
        if (buttons[0].getText().equals(symbol) && buttons[3].getText().equals(symbol) && buttons[6].getText().equals(symbol))
            return true;
        if (buttons[1].getText().equals(symbol) && buttons[4].getText().equals(symbol) && buttons[7].getText().equals(symbol))
            return true;
        if (buttons[2].getText().equals(symbol) && buttons[5].getText().equals(symbol) && buttons[8].getText().equals(symbol))
            return true;

        // Diagonals
        if (buttons[0].getText().equals(symbol) && buttons[4].getText().equals(symbol) && buttons[8].getText().equals(symbol))
            return true;
        if (buttons[2].getText().equals(symbol) && buttons[4].getText().equals(symbol) && buttons[6].getText().equals(symbol))
            return true;

        return false;
    }

    private void declareWinner(String symbol) {
        JOptionPane.showMessageDialog(this, symbol + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        resetGame();
    }

    private void declareDraw() {
        JOptionPane.showMessageDialog(this, "It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        resetGame();
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();

            if (button.getText().isEmpty()) {
                if (playerXTurn) {
                    button.setText("X");
                    if (checkWinCondition("X")) {
                        declareWinner("X");
                        return;
                    }
                } else {
                    button.setText("O");
                    if (checkWinCondition("O")) {
                        declareWinner("O");
                        return;
                    }
                }

                moves++;
                if (moves == 9) {
                    declareDraw();
                } else {
                    playerXTurn = !playerXTurn;
                }

                button.setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeApp app = new TicTacToeApp();
            app.setVisible(true);
        });
    }
}

