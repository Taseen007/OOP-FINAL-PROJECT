package app;

import habit.HabitLogger;
import habit.HabitType;
import meal.MealPlanner;
import meal.VeganStrategy;
import meal.HighProteinStrategy;
import progress.CaloriesTrackingStrategy;
import progress.ProgressTracker;
import progress.ProgressTrackingStrategy;
import progress.StepsTrackingStrategy;
import storage.FileDataStore;
import user.UserProfile;
import util.ConsoleUtil;

import java.time.LocalDate;
import java.util.Map;

public class AppInitializer {
    public void startApplication() {
        FileDataStore dataStore = new FileDataStore();
        UserProfile userProfile = ProfileManager.loadOrCreateProfile(dataStore);

        Map<String, meal.MealSuggestionStrategy> strategies = initializeMealStrategies();
        MealPlanner mealPlanner = new MealPlanner(userProfile, strategies);
        HabitLogger habitLogger = new HabitLogger(dataStore);
        HealthApp healthApp = new HealthApp(mealPlanner);

        // Initialize ProgressTracker with the two strategies
        ProgressTracker progressTracker = new ProgressTracker(Map.of(
                "calories", new CaloriesTrackingStrategy(),
                "steps", new StepsTrackingStrategy()
        ));

        if (userProfile != null) {
            displayWelcomeMessage(userProfile, habitLogger);
        } else {
            ConsoleUtil.printHeader("Welcome to the Health App");
        }

        MainMenu mainMenu = new MainMenu(userProfile, mealPlanner, habitLogger, healthApp, dataStore, progressTracker);
        mainMenu.show();
    }

    private Map<String, meal.MealSuggestionStrategy> initializeMealStrategies() {
        return Map.of(
                "Vegan Diet Plan", new VeganStrategy(),
                "High Protein Diet Plan", new HighProteinStrategy()
        );
    }

    private void displayWelcomeMessage(UserProfile userProfile, HabitLogger habitLogger) {
        ConsoleUtil.printHeader("Welcome back, " + userProfile.getName() + "!");
        ConsoleUtil.printInfo("Your progress tracking summary:");
        for (var habitType : HabitType.values()) {
            double total = habitLogger.getTotalForDay(habitType, LocalDate.now());
            ConsoleUtil.printInfo(habitType.getDisplayName() + ": " + total + " " + habitType.getUnit());
        }
        ConsoleUtil.pressEnterToContinue();
    }
}