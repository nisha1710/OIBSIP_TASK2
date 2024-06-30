import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI {
    private JFrame frame;
    private JLabel messageLabel;
    private JLabel attemptsLabel;
    private JTextField guessField;
    private JButton guessButton;
    private int targetNumber;
    private int attempts;
    private final int MAX_ATTEMPTS = 5;
    private int points;

    public NumberGuessingGameGUI() {
        frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(4, 1));

        messageLabel = new JLabel("I have chosen a number between 1 and 100. Try to guess it!");
        attemptsLabel = new JLabel("Remaining attempts: " + MAX_ATTEMPTS);
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");

        frame.add(messageLabel);
        frame.add(attemptsLabel);
        frame.add(guessField);
        frame.add(guessButton);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        });

        targetNumber = generateRandomNumber();
        attempts = 0;
        points = 0;

        frame.setVisible(true);
    }

    private int generateRandomNumber() {
        Random random = new Random();
        int number = random.nextInt(100) + 1;
        System.out.println("Computer guessed: " + number); // Print computer's guess
        return number;
    }

    private void processGuess() {
        attempts++;
        attemptsLabel.setText("Remaining attempts: " + (MAX_ATTEMPTS - attempts));

        if (attempts <= MAX_ATTEMPTS) {
            String guessText = guessField.getText();

            if (!guessText.isEmpty()) {
                int guess = Integer.parseInt(guessText);
                String feedback;

                if (guess < targetNumber) {
                    feedback = "Too low! Try again.";
                } else if (guess > targetNumber) {
                    feedback = "Too high! Try again.";
                } else {
                    feedback = "Congratulations! You've guessed the number " + targetNumber + " correctly in " + attempts + " attempts!";
                    guessButton.setEnabled(false);
                    points += (MAX_ATTEMPTS - attempts + 1);
                    feedback += " You earned " + points + " points!";
                }

                if (attempts >= MAX_ATTEMPTS && guess != targetNumber) {
                    feedback = "Sorry, you've run out of attempts. The correct number was: " + targetNumber;
                    guessButton.setEnabled(false);
                }

                System.out.println("User guessed: " + guess); // Print user's guess
                messageLabel.setText(feedback);
                guessField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a guess!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGameGUI();
            }
        });
    }
}
