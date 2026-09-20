# codsoft-java-development
import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalScore = 0;
        int roundsPlayed = 0;
        boolean playAgain = true;

        System.out.println("==========================================");
        System.out.println("   WELCOME TO THE NUMBER GUESSING GAME!   ");
        System.out.println("==========================================");

        while (playAgain) {
            roundsPlayed++;
            int lowerBound = 1;
            int upperBound = 100;
            int maxAttempts = 7;
            int attemptsUsed = 0;
            boolean guessedCorrectly = false;

            // Generate a random number between 1 and 100
            int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;

            System.out.println("\n--- Round " + roundsPlayed + " ---");
            System.out.println("I've picked a number between " + lowerBound + " and " + upperBound + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess it!");

            while (attemptsUsed < maxAttempts) {
                System.out.print("Enter your guess: ");

                // Validate integer input
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    scanner.next(); // Clear invalid input
                    continue;
                }

                int userGuess = scanner.nextInt();
                attemptsUsed++;

                if (userGuess == targetNumber) {
                    guessedCorrectly = true;
                    int roundScore = (maxAttempts - attemptsUsed + 1) * 10;
                    totalScore += roundScore;

                    System.out.println("🎉 Correct! You guessed the number in " + attemptsUsed + " attempt(s).");
                    System.out.println("Round Score: +" + roundScore + " points!");
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low! Try again. (Attempts left: " + (maxAttempts - attemptsUsed) + ")");
                } else {
                    System.out.println("Too high! Try again. (Attempts left: " + (maxAttempts - attemptsUsed) + ")");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("\n❌ Out of attempts! The correct number was: " + targetNumber);
            }

            System.out.println("Total Score: " + totalScore + " points");

            // Ask to play another round
            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = scanner.next().trim().toLowerCase();
            if (!response.equals("yes") && !response.equals("y")) {
                playAgain = false;
            }
        }

        System.out.println("\n==========================================");
        System.out.println("Game Over! Rounds Played: " + roundsPlayed + " | Final Score: " + totalScore);
        System.out.println("Thanks for playing!");
        System.out.println("==========================================");

        scanner.close();
    }
}
