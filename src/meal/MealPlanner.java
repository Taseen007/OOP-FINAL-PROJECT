package meal;

import user.UserProfile;
import java.util.List;
import java.util.Map;

public class MealPlanner {
    private final UserProfile userProfile;
    private MealSuggestionStrategy currentStrategy;
    private final Map<String, MealSuggestionStrategy> strategies;

    public MealPlanner(UserProfile userProfile, Map<String, MealSuggestionStrategy> strategies) {
        this.userProfile = userProfile;
        this.strategies = strategies;
        this.currentStrategy = strategies.get("Vegan Diet Plan"); // Default strategy
    }

    public void setStrategy(String strategyName) {
        if (strategies.containsKey(strategyName)) {
            this.currentStrategy = strategies.get(strategyName);
        } else {
            throw new IllegalArgumentException("Invalid strategy name: " + strategyName);
        }
    }

    public String getCurrentStrategyName() {
        return currentStrategy.getStrategyName();
    }

    public List<String> getAvailableStrategies() {
        return strategies.keySet().stream().toList();
    }

    public List<Meal> suggestMealsForDay() {
        return currentStrategy.suggestMeals(userProfile);
    }
}