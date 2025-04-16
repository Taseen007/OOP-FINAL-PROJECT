package app;

import progress.ProgressTracker;
import util.ConsoleUtil;

import java.time.LocalDate;
import java.util.List;

public class ProgressManager {
    public static void manageProgress(ProgressTracker progressTracker) {
        while (true) {
            ConsoleUtil.clearScreen();
            ConsoleUtil.printHeader("Progress Management");
            int choice = ConsoleUtil.showMenu("Choose an option",
                    List.of("Log Progress", "View Progress", "Back"));

            switch (choice) {
                case 0 -> logProgress(progressTracker);
                case 1 -> viewProgress(progressTracker);
                case 2 -> {
                    return; // Back to main menu
                }
                default -> ConsoleUtil.printError("Invalid choice. Please try again.");
            }
        }
    }

    private static void logProgress(ProgressTracker progressTracker) {
        String type = ConsoleUtil.readString("Enter progress type (e.g., calories, steps): ");
        double value = ConsoleUtil.readDouble("Enter value: ", v -> v > 0, "Value must be positive.");
        LocalDate date = LocalDate.now();

        progressTracker.logProgress(type, date, value);
        ConsoleUtil.printSuccess("Progress logged successfully!");
        ConsoleUtil.pressEnterToContinue();
    }

    private static void viewProgress(ProgressTracker progressTracker) {
        String type = ConsoleUtil.readString("Enter progress type to view: ");
        var history = progressTracker.getProgressHistory(type);

        if (history.isEmpty()) {
            ConsoleUtil.printInfo("No progress logged for this type.");
        } else {
            history.forEach((date, value) -> ConsoleUtil.printInfo(date + ": " + value));
        }
        ConsoleUtil.pressEnterToContinue();
    }
}