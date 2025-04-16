package app;

import meal.MealPlanner;
import util.ConsoleUtil;

import java.util.List;

public class HealthApp {
    private final MealPlanner mealPlanner;

    public HealthApp(MealPlanner mealPlanner) {
        this.mealPlanner = mealPlanner;
    }

    public void changeMealPlan() {
        ConsoleUtil.printHeader("Change Meal Plan");

        List<String> strategies = mealPlanner.getAvailableStrategies();
        int choice = ConsoleUtil.showMenu("Available Meal Plans", strategies);

        if (choice >= 0 && choice < strategies.size()) {
            String selectedStrategy = strategies.get(choice);
            mealPlanner.setStrategy(selectedStrategy);
            ConsoleUtil.printSuccess("Meal plan changed to: " + selectedStrategy);
        }

        ConsoleUtil.pressEnterToContinue();
    }
}