package user;

import java.io.Serializable;

public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private double heightCm;
    private double weightKg;
    private String goal; // e.g., "lose weight", "build muscle", etc.

    public UserProfile(String name, int age, double heightCm, double weightKg, String goal) {
        this.name = name;
        this.age = age;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.goal = goal;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(double heightCm) {
        this.heightCm = heightCm;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    /**
     * Update all fields from another UserProfile
     *
     * @param other UserProfile to copy data from
     * @throws IllegalArgumentException if other is null
     */
    public void updateFrom(UserProfile other) {
        if (other == null) {
            throw new IllegalArgumentException("Source profile cannot be null");
        }
        
        this.name = other.name;
        this.age = other.age;
        this.heightCm = other.heightCm;
        this.weightKg = other.weightKg;
        this.goal = other.goal;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", heightCm=" + heightCm +
                ", weightKg=" + weightKg +
                ", goal='" + goal + '\'' +
                '}';
    }
}