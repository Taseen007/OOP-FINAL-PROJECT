package meal;

import user.UserProfile;
import java.util.List;

/**
 * Strategy interface for different meal suggestion algorithms
 */
// src/meal/MealSuggestionStrategy.java


import user.UserProfile;

import java.util.List;

public interface MealSuggestionStrategy {
    List<Meal> suggestMeals(UserProfile userProfile);
    String getStrategyName();
}
