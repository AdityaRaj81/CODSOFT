// Task - 1

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI {
    private JFrame frame;
    private JTextField guessField;
    private JLabel messageLabel, attemptsLabel, scoreLabel;
    private JButton submitButton, playAgainButton;
    private Random random;
    private int targetNumber, attemptsLeft, totalScore;

    public NumberGuessingGameGUI() {
        random = new Random();
        targetNumber = random.nextInt(100) + 1;
        attemptsLeft = 5;
        totalScore = 0;

        frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new GridLayout(5, 1));

        messageLabel = new JLabel("Guess a number between 1 and 100", SwingConstants.CENTER);
        frame.add(messageLabel);

        guessField = new JTextField();
        frame.add(guessField);

        submitButton = new JButton("Submit Guess");
        frame.add(submitButton);

        attemptsLabel = new JLabel("Attempts left: " + attemptsLeft, SwingConstants.CENTER);
        frame.add(attemptsLabel);

        scoreLabel = new JLabel("Total Score: " + totalScore, SwingConstants.CENTER);
        frame.add(scoreLabel);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setEnabled(false);
        frame.add(playAgainButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        frame.setVisible(true);
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());

            if (userGuess == targetNumber) {
                messageLabel.setText("Congratulations! You guessed the number.");
                totalScore += attemptsLeft;
                endGame();
            } else if (userGuess > targetNumber) {
                messageLabel.setText("Too high! Try again.");
            } else {
                messageLabel.setText("Too low! Try again.");
            }

            attemptsLeft--;
            attemptsLabel.setText("Attempts left: " + attemptsLeft);
            scoreLabel.setText("Total Score: " + totalScore);

            if (attemptsLeft == 0) {
                messageLabel.setText("Out of attempts! The correct number was: " + targetNumber);
                endGame();
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number.");
        }
    }

    private void endGame() {
        submitButton.setEnabled(false);
        playAgainButton.setEnabled(true);
    }

    private void resetGame() {
        targetNumber = random.nextInt(100) + 1;
        attemptsLeft = 5;
        messageLabel.setText("Guess a number between 1 and 100");
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
        guessField.setText("");
        submitButton.setEnabled(true);
        playAgainButton.setEnabled(false);
    }

    public static void main(String[] args) {
        new NumberGuessingGameGUI();
    }
}
