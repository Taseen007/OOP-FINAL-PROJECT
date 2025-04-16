package meal;

import user.UserProfile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VeganStrategy implements MealSuggestionStrategy {

    @Override
    public List<Meal> suggestMeals(UserProfile userProfile) {
        List<Meal> suggestions = new ArrayList<>();

        // Suggest meals based on meal type
        suggestions.addAll(suggestMealsByType(Meal.MealType.BREAKFAST));
        suggestions.addAll(suggestMealsByType(Meal.MealType.LUNCH));
        suggestions.addAll(suggestMealsByType(Meal.MealType.DINNER));
        suggestions.addAll(suggestMealsByType(Meal.MealType.SNACK));

        // Adjust calorie content based on user's goals
        if (userProfile.getGoal().toLowerCase().contains("lose weight")) {
            adjustCaloriesForWeightLoss(suggestions);
        } else if (userProfile.getGoal().toLowerCase().contains("gain weight")) {
            adjustCaloriesForWeightGain(suggestions);
        }

        return suggestions;
    }

    private List<Meal> suggestMealsByType(Meal.MealType mealType) {
        List<Meal> suggestions = new ArrayList<>();

        switch (mealType) {
            case BREAKFAST -> {
                suggestions.add(createOatmealWithFruits());
                suggestions.add(createTofuScramble());
                suggestions.add(createSmoothieBowl());
            }
            case LUNCH -> {
                suggestions.add(createQuinoaSalad());
                suggestions.add(createVeganWrap());
                suggestions.add(createLentilSoup());
            }
            case DINNER -> {
                suggestions.add(createVeganStirFry());
                suggestions.add(createVeganCurry());
                suggestions.add(createStuffedBellPeppers());
            }
            case SNACK -> {
                suggestions.add(createHummusWithVeggies());
                suggestions.add(createTrailMix());
                suggestions.add(createFruitSalad());
            }
        }

        return suggestions;
    }

    private void adjustCaloriesForWeightLoss(List<Meal> meals) {
        for (Meal meal : meals) {
            meal.setCalories((int) (meal.getCalories() * 0.85));
            meal.setFats(meal.getFats() * 0.8);
            meal.setName(meal.getName() + " (Light)");
        }
    }

    private void adjustCaloriesForWeightGain(List<Meal> meals) {
        for (Meal meal : meals) {
            meal.setCalories((int) (meal.getCalories() * 1.2));
            meal.setProteins(meal.getProteins() * 1.3);
            meal.setName(meal.getName() + " (Enhanced)");
        }
    }

    // Sample meal creators
    private Meal createOatmealWithFruits() {
        List<String> ingredients = Arrays.asList("Oats", "Almond milk", "Banana", "Berries", "Maple syrup");
        return new Meal("Oatmeal with Fruits", ingredients, 350, 12, 60, 7, Meal.MealType.BREAKFAST);
    }

    private Meal createTofuScramble() {
        List<String> ingredients = Arrays.asList("Tofu", "Nutritional yeast", "Turmeric", "Bell peppers", "Spinach");
        return new Meal("Tofu Scramble", ingredients, 280, 20, 15, 12, Meal.MealType.BREAKFAST);
    }

    private Meal createSmoothieBowl() {
        List<String> ingredients = Arrays.asList("Frozen banana", "Berries", "Spinach", "Plant-based yogurt", "Granola");
        return new Meal("Smoothie Bowl", ingredients, 320, 8, 65, 5, Meal.MealType.BREAKFAST);
    }

    private Meal createQuinoaSalad() {
        List<String> ingredients = Arrays.asList("Quinoa", "Chickpeas", "Cucumber", "Tomatoes", "Lemon dressing");
        return new Meal("Quinoa Salad", ingredients, 420, 15, 70, 10, Meal.MealType.LUNCH);
    }

    private Meal createVeganWrap() {
        List<String> ingredients = Arrays.asList("Whole grain wrap", "Hummus", "Avocado", "Mixed greens", "Veggies");
        return new Meal("Vegan Wrap", ingredients, 450, 12, 65, 18, Meal.MealType.LUNCH);
    }

    private Meal createLentilSoup() {
        List<String> ingredients = Arrays.asList("Lentils", "Carrots", "Celery", "Onion", "Vegetable broth");
        return new Meal("Lentil Soup", ingredients, 380, 18, 60, 5, Meal.MealType.LUNCH);
    }

    private Meal createVeganStirFry() {
        List<String> ingredients = Arrays.asList("Tofu", "Broccoli", "Carrots", "Brown rice", "Soy sauce");
        return new Meal("Tofu Stir Fry", ingredients, 480, 22, 75, 12, Meal.MealType.DINNER);
    }

    private Meal createVeganCurry() {
        List<String> ingredients = Arrays.asList("Chickpeas", "Coconut milk", "Curry powder", "Spinach", "Rice");
        return new Meal("Chickpea Curry", ingredients, 520, 18, 80, 15, Meal.MealType.DINNER);
    }

    private Meal createStuffedBellPeppers() {
        List<String> ingredients = Arrays.asList("Bell peppers", "Quinoa", "Black beans", "Corn", "Tomato sauce");
        return new Meal("Stuffed Bell Peppers", ingredients, 410, 15, 70, 8, Meal.MealType.DINNER);
    }

    private Meal createHummusWithVeggies() {
        List<String> ingredients = Arrays.asList("Hummus", "Carrot sticks", "Cucumber", "Bell peppers");
        return new Meal("Hummus with Veggies", ingredients, 180, 6, 25, 8, Meal.MealType.SNACK);
    }

    private Meal createTrailMix() {
        List<String> ingredients = Arrays.asList("Nuts", "Seeds", "Dried fruits");
        return new Meal("Trail Mix", ingredients, 220, 7, 20, 14, Meal.MealType.SNACK);
    }

    private Meal createFruitSalad() {
        List<String> ingredients = Arrays.asList("Mixed fruits", "Mint", "Lime juice");
        return new Meal("Fruit Salad", ingredients, 120, 1, 30, 0, Meal.MealType.SNACK);
    }

    @Override
    public String getStrategyName() {
        return "Vegan Diet Plan";
    }
}