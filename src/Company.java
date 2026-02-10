import java.util.ArrayList;
import java.util.List;

public class Company {
    private String name;
    private List<TimeSlot> availableSlots = new ArrayList<>();

    public Company(String name) {
        this.name = name;
    }

    public void addAvailableSlot(TimeSlot slot) {
        availableSlots.add(slot);
    }

    public List<TimeSlot> getAvailableSlots() {
        return availableSlots;
    }

    public String getName() {
        return name;
    }
}
