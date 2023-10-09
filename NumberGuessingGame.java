/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package numberguessinggame;

/**
 *
 * @author eshan
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class NumberGuessingGame extends JFrame {
    private int numberToGuess;
    private int attempts;
    private JTextField inputField;
    private JLabel resultLabel;
    private JButton guessButton;
    private JPanel gamePanel;

    public NumberGuessingGame() {
        numberToGuess = (int) (Math.random() * 100) + 1;
        attempts = 0;

        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBackground(g);
            }
        };

        gamePanel.setLayout(new GridBagLayout());
        gamePanel.setBackground(Color.CYAN);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        inputField = new JTextField(20);
        inputField.setPreferredSize(new Dimension(300, 40)); // Increase input field size

        guessButton = new JButton("Guess");
        guessButton.setPreferredSize(new Dimension(100, 40)); // Increase button size

        resultLabel = new JLabel("");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gamePanel.add(inputField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gamePanel.add(guessButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gamePanel.add(resultLabel, gbc);

        add(gamePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void drawBackground(Graphics g) {
        try {
            BufferedImage background = ImageIO.read(getClass().getResource("/image/background.png"));
            g.drawImage(background, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(inputField.getText());
            attempts++;

            if (guess < numberToGuess) {
                resultLabel.setText("Higher! Attempts: " + attempts);
            } else if (guess > numberToGuess) {
                resultLabel.setText("Lower! Attempts: " + attempts);
            } else {
                resultLabel.setText("Congratulations! You guessed it in " + attempts + " attempts.");
                inputField.setEditable(false);
                guessButton.setEnabled(false);
            }

            // Change button color on click
            guessButton.setBackground(Color.GREEN);
            Timer timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    guessButton.setBackground(UIManager.getColor("Button.background"));
                }
            });
            timer.setRepeats(false);
            timer.start();
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame();
            }
        });
    }
}
