package app;

import habit.Habit;
import habit.HabitLogger;
import habit.HabitType;
import util.ConsoleUtil;

import java.util.List;

public class HabitManager {
    public static void manageHabits(HabitLogger habitLogger) {
        while (true) {
            ConsoleUtil.clearScreen();
            ConsoleUtil.printHeader("Habit Management");
            int choice = ConsoleUtil.showMenu("Choose an option",
                    List.of("Log Habit", "View Habits", "Clear Habits", "Back"));

            switch (choice) {
                case 0 -> logHabit(habitLogger);
                case 1 -> viewHabits(habitLogger);
                case 2 -> clearHabits(habitLogger);
                case 3 -> {
                    return; // Back to main menu
                }
                default -> ConsoleUtil.printError("Invalid choice. Please try again.");
            }
        }
    }

    private static void logHabit(HabitLogger habitLogger) {
        HabitType type = chooseHabitType();
        if (type != null) {
            double amount = ConsoleUtil.readDouble("Enter the amount (" + type.getUnit() + "): ", value -> value > 0, "Amount must be positive.");
            habitLogger.logHabit(type, amount);
            ConsoleUtil.printSuccess("Habit logged successfully!");
        }
    }

    private static void viewHabits(HabitLogger habitLogger) {
        List<Habit> habits = habitLogger.getAllHabits();
        if (habits.isEmpty()) {
            ConsoleUtil.printInfo("No habits logged yet.");
        } else {
            habits.forEach(habit -> ConsoleUtil.printInfo(habit.getDetailedString()));
        }
        ConsoleUtil.pressEnterToContinue();
    }

    private static void clearHabits(HabitLogger habitLogger) {
        if (ConsoleUtil.readBoolean("Are you sure you want to clear all habits? This action cannot be undone.")) {
            habitLogger.clearHistory();
            ConsoleUtil.printSuccess("All habits cleared.");
        }
        ConsoleUtil.pressEnterToContinue();
    }

    private static HabitType chooseHabitType() {
        ConsoleUtil.printHeader("Choose a Habit Type");
        List<HabitType> types = List.of(HabitType.values());
        int choice = ConsoleUtil.showMenu("Available Habit Types", types.stream().map(HabitType::getDisplayName).toList());
        if (choice >= 0 && choice < types.size()) {
            return types.get(choice);
        }
        ConsoleUtil.printWarning("No habit type selected.");
        return null;
    }
}