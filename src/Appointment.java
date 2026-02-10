public class Appointment {
    private RegularUser user;
    private Company company;
    private TimeSlot timeSlot;

    public Appointment(RegularUser user, Company company, TimeSlot timeSlot) {
        this.user = user;
        this.company = company;
        this.timeSlot = timeSlot;
        timeSlot.book();
    }
}
