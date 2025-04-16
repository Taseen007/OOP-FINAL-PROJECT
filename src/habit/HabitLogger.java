package habit;

import storage.DataStore;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HabitLogger {
    private static final String HABITS_KEY = "habits";
    private final List<Habit> habits;
    private final DataStore dataStore;

    public HabitLogger(DataStore dataStore) {
        this.dataStore = dataStore;
        this.habits = loadHabits();
    }

    @SuppressWarnings("unchecked")
    private List<Habit> loadHabits() {
        return dataStore.load(HABITS_KEY)
                .map(data -> (List<Habit>) data)
                .orElse(new ArrayList<>());
    }

    private void saveHabits() {
        dataStore.save(HABITS_KEY, new ArrayList<>(habits));
    }

    public void logHabit(HabitType type, double amount) {
        logHabit(new Habit(type, amount));
    }

    public void logHabit(HabitType type, double amount, String notes) {
        logHabit(new Habit(type, amount, LocalDateTime.now(), notes));
    }

    public void logHabit(Habit habit) {
        habits.add(habit);
        saveHabits();
    }

    public List<Habit> getAllHabits() {
        return new ArrayList<>(habits);
    }

    public List<Habit> getHabitsByType(HabitType type) {
        return habits.stream()
                .filter(habit -> habit.getType() == type)
                .collect(Collectors.toList());
    }

    public List<Habit> getHabitsByDate(LocalDate date) {
        return habits.stream()
                .filter(habit -> habit.getDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<Habit> getHabitsByTypeAndDate(HabitType type, LocalDate date) {
        return habits.stream()
                .filter(habit -> habit.getType() == type && habit.getDate().equals(date))
                .collect(Collectors.toList());
    }

    public double getTotalForDay(HabitType type, LocalDate date) {
        return getHabitsByTypeAndDate(type, date).stream()
                .mapToDouble(Habit::getAmount)
                .sum();
    }

    public Map<LocalDate, Double> getDailyTotals(HabitType type) {
        Map<LocalDate, Double> dailyTotals = new HashMap<>();

        for (Habit habit : getHabitsByType(type)) {
            LocalDate date = habit.getDate();
            dailyTotals.put(date, dailyTotals.getOrDefault(date, 0.0) + habit.getAmount());
        }

        return dailyTotals;
    }

    public double getAverageDaily(HabitType type) {
        Map<LocalDate, Double> dailyTotals = getDailyTotals(type);

        if (dailyTotals.isEmpty()) {
            return 0.0;
        }

        double sum = dailyTotals.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        return sum / dailyTotals.size();
    }

    public void clearHabits() {
        habits.clear();
        saveHabits();
    }

    /**
     * Clear all logged habits
     */
    public void clearHistory() {
        habits.clear();
        dataStore.save("habits", habits);
    }
}