package meal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Meal implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private List<String> ingredients;
    private int calories;
    private double proteins;
    private double carbs;
    private double fats;
    private MealType type;

    public enum MealType {
        BREAKFAST, LUNCH, DINNER, SNACK
    }

    public Meal(String name, MealType type) {
        this.name = name;
        this.type = type;
        this.ingredients = new ArrayList<>();
        this.calories = 0;
        this.proteins = 0;
        this.carbs = 0;
        this.fats = 0;
    }

    public Meal(String name, List<String> ingredients, int calories, double proteins, double carbs, double fats, MealType type) {
        this.name = name;
        this.ingredients = new ArrayList<>(ingredients);
        this.calories = calories;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
        this.type = type;
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return new ArrayList<>(ingredients); // Return a copy to prevent modification
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public MealType getType() {
        return type;
    }

    public void setType(MealType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name + " (" + type + "): " + calories + " calories, "
                + proteins + "g protein, " + carbs + "g carbs, " + fats + "g fat";
    }
}