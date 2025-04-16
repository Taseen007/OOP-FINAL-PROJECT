package meal;

import user.UserProfile;
import java.util.ArrayList;
import java.util.List;

public class HighProteinStrategy implements MealSuggestionStrategy {

    @Override
    public List<Meal> suggestMeals(UserProfile userProfile) {
        List<Meal> suggestions = new ArrayList<>();

        // Suggest meals for all meal types
        suggestions.addAll(suggestMealsByType(userProfile, Meal.MealType.BREAKFAST));
        suggestions.addAll(suggestMealsByType(userProfile, Meal.MealType.LUNCH));
        suggestions.addAll(suggestMealsByType(userProfile, Meal.MealType.DINNER));
        suggestions.addAll(suggestMealsByType(userProfile, Meal.MealType.SNACK));

        return suggestions;
    }

    private List<Meal> suggestMealsByType(UserProfile userProfile, Meal.MealType mealType) {
        List<Meal> suggestions = new ArrayList<>();

        switch (mealType) {
            case BREAKFAST -> {
                suggestions.add(createProteinOatmeal());
                suggestions.add(createEggWhiteOmelet());
                suggestions.add(createGreekYogurtParfait());
            }
            case LUNCH -> {
                suggestions.add(createChickenSalad());
                suggestions.add(createTunaSandwich());
                suggestions.add(createTurkeyWrap());
            }
            case DINNER -> {
                suggestions.add(createSalmonWithVeggies());
                suggestions.add(createChickenStirFry());
                suggestions.add(createBeefAndQuinoa());
            }
            case SNACK -> {
                suggestions.add(createProteinShake());
                suggestions.add(createGreekYogurt());
                suggestions.add(createCottageCheeseWithFruit());
            }
        }

        // Adjust based on user's goals
        if (userProfile.getGoal().toLowerCase().contains("muscle") ||
                userProfile.getGoal().toLowerCase().contains("strength")) {
            increaseProteinContent(suggestions);
        } else if (userProfile.getGoal().toLowerCase().contains("lose weight")) {
            reduceCarbsAndFats(suggestions);
        }

        return suggestions;
    }

    private void increaseProteinContent(List<Meal> meals) {
        for (Meal meal : meals) {
            meal.setProteins(meal.getProteins() * 1.3);
            meal.setCalories((int) (meal.getCalories() * 1.1));
            meal.setName(meal.getName() + " (Extra Protein)");
        }
    }

    private void reduceCarbsAndFats(List<Meal> meals) {
        for (Meal meal : meals) {
            meal.setCarbs(meal.getCarbs() * 0.7);
            meal.setFats(meal.getFats() * 0.7);
            meal.setCalories((int) (meal.getCalories() * 0.85));
            meal.setName(meal.getName() + " (Low Carb)");
        }
    }

    // Sample meal creators
    private Meal createProteinOatmeal() {
        return new Meal("Protein Oatmeal", List.of("Oats", "Protein powder", "Almond milk", "Banana", "Peanut butter"), 400, 30, 50, 10, Meal.MealType.BREAKFAST);
    }

    private Meal createEggWhiteOmelet() {
        return new Meal("Egg White Omelet", List.of("Egg whites", "Spinach", "Tomatoes", "Low-fat cheese", "Bell peppers"), 350, 35, 10, 12, Meal.MealType.BREAKFAST);
    }

    private Meal createGreekYogurtParfait() {
        return new Meal("Greek Yogurt Parfait", List.of("Greek yogurt", "Berries", "Granola", "Honey", "Almonds"), 380, 25, 45, 12, Meal.MealType.BREAKFAST);
    }

    private Meal createChickenSalad() {
        return new Meal("Grilled Chicken Salad", List.of("Grilled chicken", "Mixed greens", "Quinoa", "Avocado", "Olive oil dressing"), 450, 40, 30, 20, Meal.MealType.LUNCH);
    }

    private Meal createTunaSandwich() {
        return new Meal("Tuna Sandwich", List.of("Tuna", "Whole grain bread", "Greek yogurt", "Lettuce", "Tomato"), 420, 35, 40, 12, Meal.MealType.LUNCH);
    }

    private Meal createTurkeyWrap() {
        return new Meal("Turkey Wrap", List.of("Turkey breast", "Whole grain wrap", "Hummus", "Spinach", "Bell peppers"), 430, 38, 45, 10, Meal.MealType.LUNCH);
    }

    private Meal createSalmonWithVeggies() {
        return new Meal("Salmon with Veggies", List.of("Salmon fillet", "Sweet potatoes", "Asparagus", "Olive oil", "Lemon"), 520, 42, 35, 22, Meal.MealType.DINNER);
    }

    private Meal createChickenStirFry() {
        return new Meal("Chicken Stir Fry", List.of("Chicken breast", "Brown rice", "Broccoli", "Bell peppers", "Soy sauce"), 480, 45, 50, 10, Meal.MealType.DINNER);
    }

    private Meal createBeefAndQuinoa() {
        return new Meal("Beef and Quinoa Bowl", List.of("Lean beef", "Quinoa", "Mixed vegetables", "Olive oil", "Herbs"), 550, 48, 40, 18, Meal.MealType.DINNER);
    }

    private Meal createProteinShake() {
        return new Meal("Protein Shake", List.of("Protein powder", "Almond milk", "Banana", "Ice"), 220, 25, 15, 5, Meal.MealType.SNACK);
    }

    private Meal createGreekYogurt() {
        return new Meal("Greek Yogurt with Berries", List.of("Greek yogurt", "Honey", "Berries"), 180, 20, 15, 5, Meal.MealType.SNACK);
    }

    private Meal createCottageCheeseWithFruit() {
        return new Meal("Cottage Cheese with Fruit", List.of("Cottage cheese", "Pineapple", "Almonds"), 200, 22, 12, 8, Meal.MealType.SNACK);
    }

    @Override
    public String getStrategyName() {
        return "High Protein Diet Plan";
    }
}