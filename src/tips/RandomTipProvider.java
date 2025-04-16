package tips;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import user.UserProfile;

public class RandomTipProvider implements TipProvider {
    private final List<String> generalTips;
    private final List<String> weightLossTips;
    private final List<String> muscleBuildingTips;
    private final List<String> wellnessMaintenanceTips;
    private final Random random;

    public RandomTipProvider() {
        this.generalTips = initializeGeneralTips();
        this.weightLossTips = initializeWeightLossTips();
        this.muscleBuildingTips = initializeMuscleBuildingTips();
        this.wellnessMaintenanceTips = initializeWellnessMaintenanceTips();
        this.random = new Random();
    }

    @Override
    public String getHealthTip() {
        return generalTips.get(random.nextInt(generalTips.size()));
    }

    @Override
    public String getHealthTipForUser(UserProfile userProfile) {
        if (userProfile == null) {
            return getHealthTip(); // Fall back to general tip if no profile
        }

        String goal = userProfile.getGoal().toLowerCase();

        if (goal.contains("lose weight") || goal.contains("weight loss")) {
            return weightLossTips.get(random.nextInt(weightLossTips.size()));
        } else if (goal.contains("build muscle") || goal.contains("gain muscle")) {
            return muscleBuildingTips.get(random.nextInt(muscleBuildingTips.size()));
        } else if (goal.contains("maintain") || goal.contains("wellness")) {
            return wellnessMaintenanceTips.get(random.nextInt(wellnessMaintenanceTips.size()));
        } else {
            return getHealthTip(); // Default to general tip
        }
    }

    private List<String> initializeGeneralTips() {
        return new ArrayList<>(Arrays.asList(
                "Stay hydrated! Aim to drink at least 8 glasses of water per day.",
                "Try to get 7-9 hours of sleep each night for optimal health.",
                "Take short breaks during work to reduce eye strain and mental fatigue.",
                "Include a variety of colorful fruits and vegetables in your diet.",
                "Regular physical activity can improve your mood and energy levels.",
                "Practice mindful eating by eliminating distractions during meals.",
                "Stretch for a few minutes each morning to improve flexibility.",
                "Deep breathing exercises can help reduce stress and anxiety.",
                "Regular health check-ups are important for preventive care.",
                "Limit processed foods and focus on whole, unprocessed foods."
        ));
    }

    private List<String> initializeWeightLossTips() {
        return new ArrayList<>(Arrays.asList(
                "Focus on portion control rather than eliminating food groups.",
                "Keep a food journal to track your eating habits and identify patterns.",
                "Include protein in every meal to help control hunger.",
                "Choose water over sugary drinks to reduce calorie intake.",
                "Aim for at least 30 minutes of moderate exercise most days of the week.",
                "Get enough sleep, as sleep deprivation can lead to weight gain.",
                "Eat slowly and mindfully to recognize when you're full.",
                "Plan your meals ahead to avoid impulsive food choices.",
                "Include strength training in your exercise routine to boost metabolism.",
                "Find healthy substitutes for your favorite high-calorie foods."
        ));
    }

    private List<String> initializeMuscleBuildingTips() {
        return new ArrayList<>(Arrays.asList(
                "Consume protein within 30 minutes after strength training.",
                "Focus on compound exercises that work multiple muscle groups.",
                "Ensure you're in a caloric surplus to support muscle growth.",
                "Allow at least 48 hours of rest for muscle groups after intense training.",
                "Gradually increase weight or resistance to continuously challenge your muscles.",
                "Stay hydrated before, during, and after workouts.",
                "Include all essential amino acids in your diet for optimal muscle repair.",
                "Get enough sleep to allow for muscle recovery and growth.",
                "Consider creatine supplementation for increased strength and performance.",
                "Balance your training with both pushing and pulling exercises."
        ));
    }

    private List<String> initializeWellnessMaintenanceTips() {
        return new ArrayList<>(Arrays.asList(
                "Practice mindfulness or meditation to reduce stress.",
                "Stay socially connected with friends and family.",
                "Spend time in nature to improve mental wellbeing.",
                "Find a form of exercise you enjoy to maintain consistency.",
                "Balance your plate with proteins, healthy fats, and complex carbohydrates.",
                "Stay mentally active with puzzles, reading, or learning new skills.",
                "Practice good posture to prevent back and neck pain.",
                "Take breaks from screens to reduce eye strain and improve sleep.",
                "Stay up to date with vaccinations and health screenings.",
                "Find healthy ways to manage stress, such as yoga or deep breathing."
        ));
    }
}