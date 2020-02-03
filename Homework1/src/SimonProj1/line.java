package SimonProj1;
import java.util.ArrayList;
import java.util.Collections;

public class line {
    // create variables for the number and size of dice
    private int diceSize;
    private int numDice;

    // initialize the upper and lower values of a full house
    int lowerValue = 2;
    int upperValue = 3;

    // Create arrayList variables for the hand, scores, max_appearances, and line strings
    private ArrayList<Integer> Hand;
    private ArrayList<Integer> Score = new ArrayList<>();
    // maxAppearances will be used for Of_A_Kinds/Yahtzees,
    // Full Houses, and Straights
    private ArrayList<Integer> maxAppearances = new ArrayList<>();
    // lineStrings is used for printing score categories to the screen
    private ArrayList<String> lineStrings = new ArrayList<>();

    // initialize a line object with a specified hand, number of sides,
    // and number of dice
    public line(ArrayList<Integer> hand, int numSides, int diceAmount) {
        diceSize = numSides;
        numDice = diceAmount;
        Hand = hand;
    }

    // Calls all necessary functions to fill the score line
    private void fillScores() {
        SingleValueScore();
        ThreeOrFourOfAKind();
        FullHouseCheck();
        Straight();
        YahtzeeCheck();
        GetChanceScore();
    }

    // Gets the score for all single values, finds the number of
    // appearances of each value, and starts filling in the strings
    // to reflect how many sides the dice has
    private void SingleValueScore() {
        // initializes Score, maxAppearances, and linStrings so that
        // they will all be the correct size
        for (int j = 0; j < diceSize; j++) {
            Score.add(0);
            maxAppearances.add(0);
            lineStrings.add(Integer.toString(j+1));
        }
        // For every value in Hand, that value is added to the appropriate
        // score and the number of values for that value is incremented
        for (Integer integer : Hand) {
            Score.set(integer - 1, Score.get(integer - 1) + integer);
            maxAppearances.set(integer - 1, maxAppearances.get(integer - 1) + 1);
        }
    }

    // Looks for 3 and 4 of a kind
    private void ThreeOrFourOfAKind() {
        // if there are more than 2 of the same value, statement is entered
        int threeOfAKindScore = 0;
        int fourOfAKindScore = 0;
        if (Collections.max(maxAppearances) >= 3) {
            // If true, 3ofAKind score is the sum of all dice
            threeOfAKindScore = GetSumOfDice();
            // if there are more than 3 of the same value, statement is entered
            if (Collections.max(maxAppearances) >= 4) {
                // If true, 4ofAKind score is the sum of all dice
                fourOfAKindScore = GetSumOfDice();
            }
        }
        // Add the scores to Score and necessary string to lineStrings
        Score.add(threeOfAKindScore);
        Score.add(fourOfAKindScore);
        lineStrings.add("3 of a Kind");
        lineStrings.add("4 of a Kind");
    }

    // Function to check for and fill out the score for a full house
    private void FullHouseCheck() {
        // Full House
        boolean FHFound = false;

        // If there are at least upperValue occurences of a particular
        // value, then this is entered
        if (Collections.max(maxAppearances) >= upperValue) {
            // Store the max number of a variable for future use
            int tempValue = Collections.max(maxAppearances);
            int tempIndex = maxAppearances.indexOf(Collections.max(maxAppearances));
            maxAppearances.set(tempIndex, 0);
            // If there are at least lowerValue occurences of a secon particular
            // value, then we have a full house
            if (Collections.max(maxAppearances) >= lowerValue) {
                FHFound = true;
            }
            // return maxAppearances to its original state
            maxAppearances.set(tempIndex, tempValue);
        }

        // Full Houses are worth 25 points
        if (FHFound) {
            Score.add(25);
        }
        else {
            Score.add(0);
        }
        // insert the "Full House" string into lineStrings
        lineStrings.add("Full House");
    }

    // Checks for and adds scores for both types of straight
    private void Straight() {
        // Variable to see the length of the longest straight
        int maxStraight = 0;
        // Counter for each straight
        int Count = 0;
        // For every value in maxAppearances
        for (Integer integer : maxAppearances ) {
            // if there is at least one occurence of a particular value,
            // then the counter is incremented, if not it is set to 0
            if (integer > 0) {
                Count += 1;
                // If the current straight is longer than the previous max,
                // Then raise Max_Straight to match
                if (Count > maxStraight) {
                    maxStraight = Count;
                }
            }
            else {
                Count = 0;
            }
        }

        // if the longest straight is at least one less than the number of dice,
        // then 30 is added to the score for small straights, else 0 is added to
        // both the score for small and large straights
        if (maxStraight >= numDice - 1) {
            Score.add(30);
            // If the longest straight spans all dice, 40 is added to to the
            // long straights, else 0 is added
            if (maxStraight >= numDice) {
                Score.add(40);
            }
            else {
                Score.add(0);
            }
        }
        else {
            Score.add(0);
            Score.add(0);
        }
        // Adds the straight strings to lineStrings
        lineStrings.add("Small Straight");
        lineStrings.add("Large Straight");
    }

    // Checks and scores Yahtzee
    private void YahtzeeCheck() {
        // If all dice are the same value, then YAHTZEE!, 50 points are added to
        // the score arrayList, else 0 is added
        if (Collections.max(maxAppearances) == numDice) {
            Score.add(50);
        }
        else {
            Score.add(0);
        }
        // Adds the Yahtzee string to lineStrings
        lineStrings.add("Yahtzee");
    }

    // Finds and scores Chance
    private void GetChanceScore() {
        // The Chance score is the sum of the dice
        Score.add(GetSumOfDice());
        /// Adds the chance string to lineStrings
        lineStrings.add("Chance");
    }

    // Returns the sum of all dice in the hand
    private int GetSumOfDice() {
        int sum = 0;
        for (Integer integer : Hand) {
            sum += integer;
        }
        return sum;
    }

    // Gets and prints the possible scores for a hand
    public void GetAndPrintScorePossibilities() {
        fillScores();
        // Accesses Score and lineStrings to print necessary information
        for (int i = 0; i < Score.size(); i++) {
            System.out.println("Score " + Score.get(i) +
                    " on the " + lineStrings.get(i) + " line");
        }
    }
}
