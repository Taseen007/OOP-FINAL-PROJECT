package app;

import storage.FileDataStore;
import user.UserProfile;
import util.ConsoleUtil;

import java.util.Optional;

public class LoginManager {
    private static final String PROFILE_KEY_PREFIX = "userProfile_";
    private final FileDataStore dataStore;

    public LoginManager(FileDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public Optional<UserProfile> login() {
        ConsoleUtil.printHeader("Login");
        String name = ConsoleUtil.readString("Enter your name: ");
        String profileKey = PROFILE_KEY_PREFIX + name.toLowerCase();

        if (dataStore.exists(profileKey)) {
            return dataStore.load(profileKey)
                    .map(data -> {
                        if (data instanceof UserProfile userProfile) {
                            ConsoleUtil.printSuccess("Login successful! Welcome back, " + userProfile.getName() + "!");
                            return userProfile;
                        } else {
                            throw new IllegalStateException("Invalid data type for user profile.");
                        }
                    });
        } else {
            ConsoleUtil.printError("No profile found for the given name.");
            return Optional.empty();
        }
    }

    public void registerNewUser() {
        ConsoleUtil.printHeader("Register New User");
        String name = ConsoleUtil.readString("Enter your name: ");
        String profileKey = PROFILE_KEY_PREFIX + name.toLowerCase();

        if (dataStore.exists(profileKey)) {
            ConsoleUtil.printError("A profile with this name already exists. Please log in instead.");
            return;
        }

        int age = ConsoleUtil.readInteger("Enter your age: ", value -> value > 0, "Age must be a positive number.");
        double height = ConsoleUtil.readDouble("Enter your height in cm: ", value -> value > 0, "Height must be a positive number.");
        double weight = ConsoleUtil.readDouble("Enter your weight in kg: ", value -> value > 0, "Weight must be a positive number.");
        String goal = ConsoleUtil.readString("Enter your goal (e.g., lose weight, build muscle): ");

        UserProfile userProfile = new UserProfile(name, age, height, weight, goal);
        dataStore.save(profileKey, userProfile);
        ConsoleUtil.printSuccess("Registration successful! You can now log in.");
    }
}
