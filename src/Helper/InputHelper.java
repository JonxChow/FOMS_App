package Helper;

import java.util.Scanner;

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

