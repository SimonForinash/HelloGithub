package SimonProj1;

import java.util.ArrayList;

public class Hand {
    // Creates an arrayList of type Dice to simulate a Yahtzee hand
    private ArrayList<Dice> hand = new ArrayList<Dice>();

    // Generates a hand with a specified number of dice
    // and sides on said dice
    public Hand(int numDice, int numSides) {
        Dice die;
        for (int i = 0; i < numDice; i++) {
            die = new Dice(numSides);
            hand.add(die);
        }
    }

    // Returns the hand of dice
    public ArrayList<Dice> GetHand() {
        return hand;
    }
}
