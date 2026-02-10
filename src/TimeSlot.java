import java.time.LocalDate;
import java.time.LocalTime;
public class TimeSlot {
    private LocalDate date;
    private LocalTime time;
    private boolean isBooked;

    public TimeSlot(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
        this.isBooked = false;
    }

    public boolean isAvailable() {
        return !isBooked;
    }

    public void book() {
        isBooked = true;
    }
}
