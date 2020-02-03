import SimonProj1.*;


// This file executes the Yahtzee game for project 1
public class Project1 {
    public static void main(String[] args) {
        // Initialize a YahtzeeProj1 object with 5 six sided dice and 3 turns
        YahtzeeProj1 yahtzee = new YahtzeeProj1(5,6,3);
        // Run the game
        yahtzee.RunGame();
    }
}
