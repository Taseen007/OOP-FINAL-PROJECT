package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Predicate;

/**
 * Utility class for console input/output operations
 * Following the Single Responsibility Principle - this class only handles console I/O
 */
public class ConsoleUtil {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";

    /**
     * Prints a header with decoration
     *
     * @param text Header text
     */
    public static void printHeader(String text) {
        int length = text.length() + 4;
        String border = "=".repeat(length);

        System.out.println();
        System.out.println(ANSI_BLUE + border + ANSI_RESET);
        System.out.println(ANSI_BLUE + "| " + text + " |" + ANSI_RESET);
        System.out.println(ANSI_BLUE + border + ANSI_RESET);
    }

    /**
     * Prints a success message
     *
     * @param message Success message
     */
    public static void printSuccess(String message) {
        System.out.println(ANSI_GREEN + "✓ " + message + ANSI_RESET);
    }

    /**
     * Prints an error message
     *
     * @param message Error message
     */
    public static void printError(String message) {
        System.out.println(ANSI_RED + "✗ " + message + ANSI_RESET);
    }

    /**
     * Prints a warning message
     *
     * @param message Warning message
     */
    public static void printWarning(String message) {
        System.out.println(ANSI_YELLOW + "! " + message + ANSI_RESET);
    }

    /**
     * Prints an informational message
     *
     * @param message Info message
     */
    public static void printInfo(String message) {
        System.out.println(ANSI_BLUE + "ℹ " + message + ANSI_RESET);
    }

    /**
     * Displays a menu with options and returns the selected index
     *
     * @param title Menu title
     * @param options List of menu options
     * @return Selected option index (0-based)
     */
    public static int showMenu(String title, List<String> options) {
        printHeader(title);

        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        System.out.println("0. Back/Cancel");

        int choice = readInteger("Enter your choice: ", num -> num >= 0 && num <= options.size());
        return choice -1; // Return the actual choice (1-based index, 0 for Back/Cancel)
    }

    /**
     * Reads a string input from the console
     *
     * @param prompt Text to display before input
     * @return User input as string
     */
    public static String readString(String prompt) {
        System.out.print(prompt);
        try {
            return reader.readLine();
        } catch (IOException e) {
            printError("Error reading input: " + e.getMessage());
            return "";
        }
    }

    /**
     * Reads a string input from the console with validation
     *
     * @param prompt Text to display before input
     * @param validator Validation function that returns true if input is valid
     * @param errorMessage Message to display if validation fails
     * @return Valid user input as string
     */
    public static String readString(String prompt, Predicate<String> validator, String errorMessage) {
        while (true) {
            String input = readString(prompt);
            if (validator.test(input)) {
                return input;
            }
            printError(errorMessage);
        }
    }

    /**
     * Reads an integer input from the console
     *
     * @param prompt Text to display before input
     * @return User input as integer
     */
    public static int readInteger(String prompt) {
        while (true) {
            try {
                String input = readString(prompt);
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                printError("Please enter a valid integer.");
            }
        }
    }

    /**
     * Reads an integer input from the console with validation
     *
     * @param prompt Text to display before input
     * @param validator Validation function that returns true if input is valid
     * @return Valid user input as integer
     */
    public static int readInteger(String prompt, Predicate<Integer> validator, String errorMessage) {
        while (true) {
            int input = readInteger(prompt);
            if (validator.test(input)) {
                return input;
            }
            printError(errorMessage);
        }
    }

    public static int readInteger(String prompt, Predicate<Integer> validator) {
        while (true) {
            int input = readInteger(prompt);
            if (validator.test(input)) {
                return input;
            }
            printError("Invalid input. Please try again.");
        }
    }
    /**
     * Reads a double input from the console
     *
     * @param prompt Text to display before input
     * @return User input as double
     */
    public static double readDouble(String prompt) {
        while (true) {
            try {
                String input = readString(prompt);
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                printError("Please enter a valid number.");
            }
        }
    }

    /**
     * Reads a double input from the console with validation
     *
     * @param prompt Text to display before input
     * @param validator Validation function that returns true if input is valid
     * @return Valid user input as double
     */
    public static double readDouble(String prompt, Predicate<Double> validator) {
        while (true) {
            double input = readDouble(prompt);
            if (validator.test(input)) {
                return input;
            }
            printError("Invalid input. Please try again.");
        }
    }

    /**
     * Reads a double input from the console with validation and custom error message
     *
     * @param prompt Text to display before input
     * @param validator Validation function that returns true if input is valid
     * @param errorMessage Message to display if validation fails
     * @return Valid user input as double
     */
    public static double readDouble(String prompt, Predicate<Double> validator, String errorMessage) {
        while (true) {
            double input = readDouble(prompt);
            if (validator.test(input)) {
                return input;
            }
            printError(errorMessage);
        }
    }

    /**
     * Reads a boolean input from the console (y/n)
     *
     * @param prompt Text to display before input
     * @return User input as boolean
     */
    public static boolean readBoolean(String prompt) {
        String input = readString(prompt + " (y/n): ");
        return input.trim().toLowerCase().startsWith("y");
    }

    /**
     * Displays a message and waits for the user to press Enter
     */
    public static void pressEnterToContinue() {
        readString("Press Enter to continue...");
    }

    /**
     * Clears the console screen
     * Note: This may not work in all environments
     */
    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Fallback if clearing doesn't work
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
    }
}