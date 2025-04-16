package progress;

import java.time.LocalDate;
import java.util.Map;

public class ProgressTracker {
    private final Map<String, ProgressTrackingStrategy> strategies;

    public ProgressTracker(Map<String, ProgressTrackingStrategy> strategies) {
        this.strategies = strategies;
    }

    public void logProgress(String type, LocalDate date, double value) {
        if (strategies.containsKey(type)) {
            strategies.get(type).logProgress(date, value);
        } else {
            throw new IllegalArgumentException("Invalid progress type: " + type);
        }
    }

    public Map<LocalDate, Double> getProgressHistory(String type) {
        if (strategies.containsKey(type)) {
            return strategies.get(type).getProgressHistory();
        } else {
            throw new IllegalArgumentException("Invalid progress type: " + type);
        }
    }
}