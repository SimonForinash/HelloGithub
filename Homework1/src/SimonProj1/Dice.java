package SimonProj1;
import java.util.Random;

public class Dice {
    private int faceValue;
    private int numSides;

    // Generates a Dice object with a specified number of sides
    public Dice(int sides) {
        numSides = sides;
    }

    // Rolls the dice; a random number is generated for its value
    private int RollDie()
    {
        faceValue = new Random().nextInt(numSides) + 1;
        return faceValue;
    }

    // Returns the current face value of a die
    public int GetStaticValue() {
        return faceValue;
    }

    // Rolls and returns the value of a die
    public int GetRolledValue() {
        return RollDie();
    }
}
