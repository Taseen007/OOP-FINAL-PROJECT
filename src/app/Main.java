package app;

import storage.FileDataStore;
import user.UserProfile;
import util.ConsoleUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileDataStore dataStore = new FileDataStore();
        LoginManager loginManager = new LoginManager(dataStore);

        UserProfile userProfile = null;

        // Initial menu for login or profile creation
        while (userProfile == null) {
            ConsoleUtil.clearScreen();
            ConsoleUtil.printHeader("Welcome to the Health App");
            int choice = ConsoleUtil.showMenu("Choose an option",
                    List.of("Login", "Create New Profile", "Exit"));

            switch (choice) {
                case 0 -> userProfile = loginManager.login().orElse(null);
                case 1 -> {
                    loginManager.registerNewUser();
                    userProfile = loginManager.login().orElse(null); // Automatically log in after registration
                }
                case 2 -> {
                    ConsoleUtil.printInfo("Exiting the application...");
                    return;
                }
                default -> ConsoleUtil.printError("Invalid choice. Please try again.");
            }
        }

        // Proceed to the main menu after login
        AppInitializer appInitializer = new AppInitializer();
        appInitializer.startApplication(userProfile);
    }
}