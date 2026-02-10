public class AppointmentService {
    public Appointment createAppointment(
            RegularUser user,
            Company company,
            TimeSlot slot) {

        if (slot.isAvailable()) {
            return new Appointment(user, company, slot);
        }
        return null;
    }
}
