package app;

import habit.HabitLogger;
import meal.MealPlanner;
import meal.VeganStrategy;
import meal.HighProteinStrategy;
import progress.CaloriesTrackingStrategy;
import progress.ProgressTracker;
import progress.StepsTrackingStrategy;
import storage.FileDataStore;
import user.UserProfile;
import util.ConsoleUtil;

import java.util.Map;

public class AppInitializer {
    public void startApplication(UserProfile userProfile) {
        FileDataStore dataStore = new FileDataStore();

        Map<String, meal.MealSuggestionStrategy> strategies = initializeMealStrategies();
        MealPlanner mealPlanner = new MealPlanner(userProfile, strategies);
        HabitLogger habitLogger = new HabitLogger(dataStore);
        HealthApp healthApp = new HealthApp(mealPlanner);

        ProgressTracker progressTracker = new ProgressTracker(Map.of(
                "calories", new CaloriesTrackingStrategy(),
                "steps", new StepsTrackingStrategy()
        ));

        MainMenu mainMenu = new MainMenu(userProfile, mealPlanner, habitLogger, healthApp, dataStore, progressTracker);
        mainMenu.show();
    }

    private Map<String, meal.MealSuggestionStrategy> initializeMealStrategies() {
        return Map.of(
                "Vegan Diet Plan", new VeganStrategy(),
                "High Protein Diet Plan", new HighProteinStrategy()
        );
    }
}