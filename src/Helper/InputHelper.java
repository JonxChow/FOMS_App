package Helper;

import java.util.Scanner;

/**
 * The {@code InputHelper} class provides static utility methods for reading and validating user input from the console.
 * It offers methods to read strings and numbers after ensuring they meet specific criteria, such as being non-empty,
 * within a certain range, or of a certain type. The class is designed to handle repeated prompts and validations
 * until the user provides an acceptable input.
 */
public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Gets a validated non-empty string from the user.
     * @param prompt The prompt to display to the user.
     * @return A non-empty string input.
     */
    public static String getValidatedString(String prompt) {
        String input;
        do {
            System.out.println(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while (input.isEmpty());
        return input;
    }

    /**
     * Gets a validated integer from the user.
     * @param prompt The prompt to display to the user.
     * @param min Minimum acceptable value (inclusive).
     * @param max Maximum acceptable value (inclusive).
     * @return A valid integer within the specified range.
     */
    public static int getValidatedInt(String prompt, int min, int max) {
        int number;
        while (true) {
            System.out.println(prompt);
            try {
                number = Integer.parseInt(scanner.nextLine().trim());
                if (number < min || number > max) {
                    System.out.printf("Please enter a number between %d and %d.\n", min, max);
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return number;
    }

    /**
     * Gets a validated double from the user.
     * The method prompts the user and validates that the input is a double within the specified range.
     * If the input is not a valid double or is not within the range, the user is prompted again.
     *
     * @param prompt The prompt to display to the user.
     * @param min The minimum acceptable value (inclusive).
     * @param max The maximum acceptable value (inclusive).
     * @return A valid double within the specified range.
     */
    public static double getValidatedDouble(String prompt, double min, double max) {
        double number;
        while (true) {
            System.out.println(prompt);
            try {
                number = Double.parseDouble(scanner.nextLine().trim());
                if (number < min || number > max) {
                    System.out.printf("Please enter a number between %.2f and %.2f.\n", min, max);
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return number;
    }
}

