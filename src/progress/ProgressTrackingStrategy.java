package progress;

import java.time.LocalDate;
import java.util.Map;

public interface ProgressTrackingStrategy {
    void logProgress(LocalDate date, double value);
    Map<LocalDate, Double> getProgressHistory();
}