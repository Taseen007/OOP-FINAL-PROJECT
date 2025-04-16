package app;

import storage.FileDataStore;
import user.UserProfile;
import util.ConsoleUtil;

public class ProfileManager {
    private static final String PROFILE_KEY = "userProfile";

    public static UserProfile loadOrCreateProfile(FileDataStore dataStore) {
        if (dataStore.exists(PROFILE_KEY)) {
            return dataStore.load(PROFILE_KEY)
                    .map(data -> {
                        if (data instanceof UserProfile userProfile) {
                            return userProfile;
                        } else {
                            throw new IllegalStateException("Invalid data type for user profile.");
                        }
                    })
                    .orElseThrow(() -> new IllegalStateException("Failed to load the existing profile."));
        }
        return createNewProfile(dataStore);
    }

    public static UserProfile createNewProfile(FileDataStore dataStore) {
        ConsoleUtil.printInfo("Creating a new profile...");
        String name = ConsoleUtil.readString("Enter your name: ");
        int age = ConsoleUtil.readInteger("Enter your age: ", value -> value > 0, "Age must be a positive number.");
        double height = ConsoleUtil.readDouble("Enter your height in cm: ", value -> value > 0, "Height must be a positive number.");
        double weight = ConsoleUtil.readDouble("Enter your weight in kg: ", value -> value > 0, "Weight must be a positive number.");
        String goal = ConsoleUtil.readString("Enter your goal (e.g., lose weight, build muscle): ");

        UserProfile userProfile = new UserProfile(name, age, height, weight, goal);
        dataStore.save(PROFILE_KEY, userProfile);
        ConsoleUtil.printSuccess("New profile created and saved!");
        return userProfile;
    }

    public static UserProfile updateProfile(UserProfile userProfile, FileDataStore dataStore) {
        ConsoleUtil.printInfo("Updating profile...");
        String name = ConsoleUtil.readString("Enter your name (" + userProfile.getName() + "): ", input -> !input.isBlank(), "Name cannot be empty.");
        int age = ConsoleUtil.readInteger("Enter your age (" + userProfile.getAge() + "): ", value -> value > 0, "Age must be a positive number.");
        double height = ConsoleUtil.readDouble("Enter your height in cm (" + userProfile.getHeightCm() + "): ", value -> value > 0, "Height must be a positive number.");
        double weight = ConsoleUtil.readDouble("Enter your weight in kg (" + userProfile.getWeightKg() + "): ", value -> value > 0, "Weight must be a positive number.");
        String goal = ConsoleUtil.readString("Enter your goal (" + userProfile.getGoal() + "): ");

        userProfile.setName(name);
        userProfile.setAge(age);
        userProfile.setHeightCm(height);
        userProfile.setWeightKg(weight);
        userProfile.setGoal(goal);

        dataStore.save(PROFILE_KEY, userProfile);
        ConsoleUtil.printSuccess("Profile updated and saved!");
        return userProfile;
    }
}
