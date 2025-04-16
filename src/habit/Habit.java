package habit;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Habit implements Serializable {
    private static final long serialVersionUID = 1L;

    private final HabitType type;
    private final double amount;
    private final LocalDateTime timestamp;
    private String notes;

    public Habit(HabitType type, double amount) {
        this(type, amount, LocalDateTime.now(), "");
    }

    public Habit(HabitType type, double amount, LocalDateTime timestamp, String notes) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.notes = notes;
    }

    public HabitType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public LocalDate getDate() {
        return timestamp.toLocalDate();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return String.format("%s: %.1f %s on %s",
                type.getDisplayName(),
                amount,
                type.getUnit(),
                timestamp.toLocalDate());
    }

    public String getDetailedString() {
        String result = String.format("%s: %.1f %s on %s at %02d:%02d",
                type.getDisplayName(),
                amount,
                type.getUnit(),
                timestamp.toLocalDate(),
                timestamp.getHour(),
                timestamp.getMinute());

        if (notes != null && !notes.isEmpty()) {
            result += " - Notes: " + notes;
        }

        return result;
    }
}