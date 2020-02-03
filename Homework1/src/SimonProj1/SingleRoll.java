package SimonProj1;
import java.util.ArrayList;

// This class simulates one roll of a hand of dice
public class SingleRoll {
    // ArrayList variables for the values in the user's hand, the dice that the user does
    // and does not want to reroll, and the actual dice in the user's hand
    private ArrayList<Integer> handValues = new ArrayList<>();
    private ArrayList<Character> userChoice = new ArrayList<>();
    private ArrayList<Dice> hand;

    // This constructor is for the first roll; therefore all dice are to be
    // rerolled (or rather rolled for the first time)
    public SingleRoll(ArrayList<Dice> userHand) {
        hand = userHand;
        for (int i = 0; i < userHand.size(); i++){
            userChoice.add('n');
        }
    }

    // This constructor is for all non-first rolls, along with the users hand,
    // it also takes in the users choice for which dice are to be rerolled
    public SingleRoll(ArrayList<Dice> userHand, ArrayList<Character> userDecision) {
        hand = userHand;
        userChoice = userDecision;
    }

    // This function gets all the values of the Dice in the user's
    // hand after the roll
    private void NewDiceValues(ArrayList<Dice> userDice) {
        // This for loop gets values for each dice in the user's hand
        for (int i = 0; i < userDice.size(); i++) {
            // if the user wants to keep their die, it is not rerolled
            if (userChoice.get(i) == 'y') {
                handValues.add(userDice.get(i).GetStaticValue());
            }
            // else, it is rerolled
            else {
                handValues.add(userDice.get(i).GetRolledValue());
            }
        }
    }

    // Simply prints out the values of the dice in the users hand
    public void PrintDiceValues() {
        NewDiceValues(hand);
        System.out.println(handValues);
    }

    public ArrayList<Integer> GetDiceValues() {
        return handValues;
    }
}
