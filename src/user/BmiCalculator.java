package user;

public class BmiCalculator {

    // Private constructor to prevent instantiation
    private BmiCalculator() {
        // Utility class should not be instantiated
    }

    /**
     * Calculates BMI using height in cm and weight in kg
     * BMI = weight(kg) / (height(m))²
     */
    public static double calculateBmi(double heightCm, double weightKg) {
        if (heightCm <= 0 || weightKg <= 0) {
            throw new IllegalArgumentException("Height and weight must be positive values");
        }

        double heightM = heightCm / 100.0;
        return weightKg / (heightM * heightM);
    }

    /**
     * Interprets BMI value according to standard categories
     */
    public static String interpretBmi(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal weight";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    /**
     * Calculates BMI for a user profile
     */
    public static double calculateBmi(UserProfile user) {
        return calculateBmi(user.getHeightCm(), user.getWeightKg());
    }
}