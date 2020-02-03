package SimonProj1;
import java.util.ArrayList;
import java.util.Scanner;

public class YahtzeeProj1 {
    // Constants for the number of dice, sides, and turns
    private int numDice;
    private int numSides;
    private int numTurns;

    // Creating variable to simulate a user hand
    private Hand hand;
    private ArrayList<Dice> userHand;
    private SingleRoll userIntHand;

    // Defines a reader for user inputs
    private Scanner reader = new Scanner(System.in);
    // Creates a string that will store user inputs
    private String userDecision;
    // Creates an ArrayList of characters for the user's decision
    private ArrayList<Character> userChoice;

    // Creates the line for the game
    private line Score;

    // Initialize the number of dice, number of sides, and number of turns
    public YahtzeeProj1(int dice, int sides, int turns) {
        numDice = dice;
        numSides = sides;
        numTurns = turns;
    }

    // Creates a hand for the user that is then printed to the screen
    private void CreateHand() {
        // Hand object created, then initialized
        hand = new Hand(numDice, numSides);
        userHand = hand.GetHand();
        // Creates an object to get the integer values of the user's band
        userIntHand = new SingleRoll(userHand);
        // Print the initial values to the screen
        System.out.print("Your hand: ");
        userIntHand.PrintDiceValues();
    }

    // This function simulates all rolls, excluding the first, in a round
    private void SequenceTurns() {
        boolean Continue = true;
        for (int i = 1; i < numTurns; i++) {
            if (Continue) {
                Continue = false;
                userChoice = new ArrayList<>();
                System.out.print("Enter dice to keep: ");
                userDecision = reader.next();
                for (int j = 0; j < numDice; j++){
                    char temp_char = userDecision.charAt(j);
                    userChoice.add(temp_char);
                    if ((temp_char != 'y')) {
                        Continue = true;
                    }
                }
                userIntHand = new SingleRoll(userHand, userChoice);

                System.out.print("Your hand: ");
                userIntHand.PrintDiceValues();
            }
        }
    }

    // Obtains and prints the possible scores in a turn
    private void GetAndPrintLine() {
        Score = new line(userIntHand.GetDiceValues(), numSides, numDice);
        Score.GetAndPrintScorePossibilities();
    }

    // Simulates a round of Yahtzee
    // Calls functions relating to an individual round
    private void OneRound() {
        CreateHand();
        SequenceTurns();
        GetAndPrintLine();
    }

    // Runs the game until the user chooses to quit
    public void RunGame() {
        // a boolean is needed for the user to quit when they want to
        boolean playing = true;
        while (playing) {
            // Call a round of yahtzee
            OneRound();
            // Asks the user if they want to play another round
            System.out.println();
            System.out.print("Enter 'y' to play again: ");
            // Checks if the user wants to play again,
            // if they don't, playing becomes false
            if (!reader.next().equals("y")) {
                playing = false;
            }
        }
    }
}
