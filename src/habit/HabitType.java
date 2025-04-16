package habit;

public enum HabitType {
    WATER("Water Intake", "cups"),
    SLEEP("Sleep", "hours"),
    EXERCISE("Exercise", "minutes");

    private final String displayName;
    private final String unit;

    HabitType(String displayName, String unit) {
        this.displayName = displayName;
        this.unit = unit;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return displayName;
    }
}