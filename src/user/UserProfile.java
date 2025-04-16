package user;

import java.io.Serializable;
import java.util.Objects;

public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private double heightCm;
    private double weightKg;
    private String goal;
    private String passwordHash; // Store hashed password

    public UserProfile(String name, int age, double heightCm, double weightKg, String goal, String passwordHash) {
        this.name = name;
        this.age = age;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.goal = goal;
        this.passwordHash = passwordHash;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return name.equals(that.name) && passwordHash.equals(that.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passwordHash);
    }
}