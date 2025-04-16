package app;

import habit.HabitLogger;
import meal.MealPlanner;
import progress.ProgressTracker;
import storage.FileDataStore;
import tips.RandomTipProvider;
import user.BmiCalculator;
import user.UserProfile;
import util.ConsoleUtil;

import java.util.List;

public class MainMenu {
    private UserProfile userProfile;
    private final MealPlanner mealPlanner;
    private final HabitLogger habitLogger;
    private final HealthApp healthApp;
    private final FileDataStore dataStore;
    private final RandomTipProvider tipProvider;
    private final ProgressTracker progressTracker;

    public MainMenu(UserProfile userProfile, MealPlanner mealPlanner, HabitLogger habitLogger, HealthApp healthApp, FileDataStore dataStore, ProgressTracker progressTracker) {
        this.userProfile = userProfile;
        this.mealPlanner = mealPlanner;
        this.habitLogger = habitLogger;
        this.healthApp = healthApp;
        this.dataStore = dataStore;
        this.tipProvider = new RandomTipProvider();
        this.progressTracker = progressTracker;
    }

    public void show() {
        while (true) {
            ConsoleUtil.clearScreen();
            ConsoleUtil.printHeader("Main Menu");
            int choice = ConsoleUtil.showMenu("Choose an option",
                    List.of("Profile Status", "Calculate BMI", "Change Meal Plan", "Manage Habits", "Get Health Tip", "Manage Progress", "Exit"));

            if (choice == -1) {
                ConsoleUtil.printInfo("Exiting the application...");
                return;
            }

            switch (choice) {
                case 0 -> manageProfile();
                case 1 -> calculateBmi();
                case 2 -> healthApp.changeMealPlan();
                case 3 -> HabitManager.manageHabits(habitLogger);
                case 4 -> displayHealthTip();
                case 5 -> ProgressManager.manageProgress(progressTracker);
                case 6 -> {
                    ConsoleUtil.printSuccess("Thank you for using the Health App!");
                    return;
                }
                default -> ConsoleUtil.printError("Invalid choice. Please try again.");
            }
        }
    }

    private void manageProfile() {
        while (true) {
            ConsoleUtil.clearScreen();
            ConsoleUtil.printHeader("Profile Status");
            int choice = ConsoleUtil.showMenu("Choose an option",
                    List.of("View Profile", "Update Profile", "Create New Profile", "Back"));

            switch (choice) {
                case 0 -> ConsoleUtil.printInfo(userProfile.toString());
                case 1 -> updateProfile();
                case 2 -> createNewProfile();
                case 3 -> {
                    return; // Back to main menu
                }
                default -> ConsoleUtil.printError("Invalid choice. Please try again.");
            }
        }
    }

    private void updateProfile() {
        userProfile = ProfileManager.updateProfile(userProfile, dataStore);
        mealPlanner.setStrategy(mealPlanner.getCurrentStrategyName());
    }

    private void createNewProfile() {
        if (ConsoleUtil.readBoolean("Are you sure you want to create a new profile? This will overwrite the existing profile.")) {
            userProfile = ProfileManager.createNewProfile(dataStore);
            mealPlanner.setStrategy(mealPlanner.getCurrentStrategyName());
        }
    }

    private void calculateBmi() {
        double bmi = BmiCalculator.calculateBmi(userProfile);
        String interpretation = BmiCalculator.interpretBmi(bmi);
        ConsoleUtil.printInfo("Your BMI is: " + String.format("%.2f", bmi));
        ConsoleUtil.printInfo("BMI Interpretation: " + interpretation);
        ConsoleUtil.pressEnterToContinue();
    }

    private void displayHealthTip() {
        String tip = tipProvider.getHealthTipForUser(userProfile);
        ConsoleUtil.printInfo("Health Tip: " + tip);
        ConsoleUtil.pressEnterToContinue();
    }
}