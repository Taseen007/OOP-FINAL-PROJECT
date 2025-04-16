package progress;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractProgressTrackingStrategy implements ProgressTrackingStrategy {
    private final Map<LocalDate, Double> progressHistory = new HashMap<>();

    @Override
    public void logProgress(LocalDate date, double value) {
        progressHistory.put(date, progressHistory.getOrDefault(date, 0.0) + value);
    }

    @Override
    public Map<LocalDate, Double> getProgressHistory() {
        return progressHistory;
    }
}