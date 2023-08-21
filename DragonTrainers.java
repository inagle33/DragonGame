/**
 * This program will play a game with the user. Depending on the type of dragon chosen by the user and 
 * CPU points will be awarded and a winner will be chosen. A tie is also possible. 
 * @author Isaac Nagle
 * @version 10172022
 */
import java.util.Random;
import java.util.Scanner;

public class DragonTrainers {

    /**
     * Constant array to hold the types of the dragon in order. 0 - Plant, 1 -
     * Water, 2 - Fire. Make sure your code does not change these values!
     */
    private static final String[] DRAGONS = { "Plant", "Water", "Fire" };

    /**
     * Prompts the user with the message: "How many matches will we play? " and
     * takes in an integer as input. If the user enters a value that is 0 or
     * negative, displays the error message: "ERROR - number of matches must
     * be positive!" and asks again. Continues looping until the user enters a
     * positive number.
     *
     * @param input
     *            - Scanner to read values from the keyboard
     * @return - an integer value strictly larger than zero.
     */
    public static int getNumberOfMatches(Scanner input) {
        System.out.print("How many matches will we play? ");
        
       int games = Integer.parseInt(input.nextLine());
       while (games <= 0) { //iterates until a valid input is given and parses input to an integer
             System.out.println("ERROR - number of matches must be positive!");
             System.out.print("How many matches will we play? ");
             games = Integer.parseInt(input.nextLine());
             }
       
       return games;
    }

    /**
     * Prompts the user with the message "Please select a dragon
     * [Plant/Water/Fire]: " and waits for user input. If the user enters a
     * blank line, prints the error message "ERROR - Dragon prompt cannot be
     * empty" and asks the user again. Repeats until the user enters a non-blank
     * line.
     *
     * @param input
     *            - Scanner to read values from the keyboard
     * @return A non-empty String entered by the user
     */
    public static String promptForDragon(Scanner input) {
        String dragonType;
        System.out.print("Please select a dragon [Plant/Water/Fire]: ");
        dragonType = input.nextLine();
        
        while (dragonType.isEmpty()) { //iterates until a valid dragon is entered
           System.out.println("ERROR - Dragon prompt cannot be empty.");
           System.out.print("Please select a dragon [Plant/Water/Fire]: ");
           dragonType = input.nextLine();
        }
        
        return dragonType;
    }

    /**
     * Takes a single UPPERCASE character. If it is a 'W','P' or 'F' returns the
     * appropriate numeric value given the dragon types in the array DRAGON
     * (i.e. 0 for 'P', 1 for 'W', 2 for 'F'). If it is none of these returns a
     * -1 to represent an invalid value.
     *
     * @param dragon
     *            - the UPPERCASE character to look up a value for.
     * @return 0 if dragon is 'P', 1 if dragon is 'W', 2 if dragon is 'F', -1
     *         otherwise
     */
    public static int dragonToNumber(char dragon) {
       int dragonNum = -1;
       //compares input to elements in array and if they are the same program outputs index number
       for (int i = 0; i < DRAGONS.length; i++) {
          if (dragon == DRAGONS[i].charAt(0)) {
             dragonNum = i;
          }
       }
       
       return dragonNum;
    }

    /**
     * Takes a number representing the player's choice and another representing
     * the computer's choice. Returns a 0 if they tie, a 1 if the player wins,
     * and a -1 if the player loses. Note that the values map to the indexes of
     * the array DRAGONS above (0 is a Plant dragon, 1 is a Water dragon, 2 is a
     * Fire Dragon).
     *
     * @param player
     *            - value 0-2 representing player dragon choice
     * @param cpu
     *            - value 0-2 representing computer dragon choice
     * @return 1 if the player wins, -1 if the computer wins, 0 if they tie
     */
    public static int determineWinner(int player, int cpu) {
      int point = 0; //neither cpu nor user is awarded a point
      if (((player + 1) %3) == cpu) { //awards point to user for winning
         point = 1;
      }
      else if (((cpu + 1) %3) == player) { //awards point to cpu for winning
         point = -1;
      }
        return point;
    }

    /**
     * Takes a number representing the player's choice and another number
     * representing the computer's choice, and a third number that is positive
     * if the player is the winner, negative if the computer is the winner, and
     * 0 if they tied. Then displays the appropriate player defeats computer or
     * computer defeats player or tie message as given in the project
     * description.
     *
     * @param player
     *            index into the DRAGONS array representing the player choice
     * @param cpu
     *            index into the DRAGONS array representing the computer's
     *            choice
     * @param winner
     *            0 for a tie, positive for a player win, negative for a
     *            computer win
     */
    public static void displayMatchResult(int player, int cpu, int winner) {
        if (winner == 1) { //output for if user wins
           System.out.println(DRAGONS[player] + " defeats " + DRAGONS[cpu] + " - you win!");
        }
        else if (winner == -1) { //output for if cpu wins
           System.out.println(DRAGONS[cpu] + " defeats " + DRAGONS[player] + " - you lose!");
        }
        else { //output if user and cpu tie
           System.out.println("A Tie!");
        }
    }

    /**
     * Takes the number of wins, losses and ties and displays the final message
     * and summary statistics as given in the project description.
     *
     * @param wins
     *            number of total wins for the player
     * @param losses
     *            number of total losses for the player
     * @param ties
     *            number of ties
     */
    public static void displayFinalResult(int wins, int losses, int ties) {
        System.out.println("The tournament is over!");
        System.out.println("We tied " + ties + " matches.");
        System.out.println("I won " + losses + " matches.");
        System.out.println("You won " + wins + " matches.");
        //announces the winner
        if (wins > losses) {
           System.out.println("You are the winner!");
        }
        else if (losses > wins) {
           System.out.println("I am the winner!");
        }
        else {
           System.out.println("Neither of us can claim victory here!");
        }
    }

    /**
     * NOTE: The main method has been completed for you. If you correctly
     * complete the methods above, the main method will "just work" and produce
     * the correct output.
     */
    public static void main(String[] args) {
        // Prompt for a random number seed
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter a random seed: ");
        int seed = Integer.parseInt(keyboard.nextLine());
        // Create a Random instance with the seed
        Random rnd = new Random(seed);
        // Prompt for number of matches to play
        int totalMatches = getNumberOfMatches(keyboard);

        // Start with wins, losses and ties at 0.
        // Repeat until all matches have been played (use the sum of the
        // results so we don't need another variable)
        int wins = 0, losses = 0, ties = 0;
        while ((wins + losses + ties) < totalMatches) {
            // Ask the user for a dragon to use
            String input = promptForDragon(keyboard);

            // Get the first character of the user's input as an uppercase
            // value.
            char dChar = input.toUpperCase().charAt(0);

            // Convert the user's input to an index for the DRAGONS array
            int playerDragon = dragonToNumber(dChar);

            // Generate a choice between 0 and 2 for the computer
            int cpuDragon = rnd.nextInt(3);

            // Display the results
            System.out.println("I chose: " + DRAGONS[cpuDragon] + " dragon.");

            // If the player didn't enter a valid choice, print out an error
            // message and increase the number of losses.
            if (playerDragon == -1) {
                System.out.println("You don't have the " + input + " dragon.");
                System.out.println("So no dragon fights for you.");
                System.out.println("I win by default!");
                losses++;
            } else {
                // Print out the player's choice.
                System.out.println("You chose: " + DRAGONS[playerDragon] + " dragon.");
                // Determine who won the match.
                int winner = determineWinner(playerDragon, cpuDragon);
                // Display the result of the match.
                displayMatchResult(playerDragon, cpuDragon, winner);

                // Increase the count of wins, losses or ties according to
                // who won the match.
                if (winner > 0) {
                    wins++;
                } else if (winner < 0) {
                    losses++;
                } else {
                    ties++;
                }
            }
            System.out.println();
        }
        // Display the final summary of the match to the screen.
        displayFinalResult(wins, losses, ties);

        // Don't forget to close the Scanner!
        keyboard.close();
    }

}
