import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AuthService authService = systemSetup();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("\n--- RANDEVU SİSTEMİ ---");
            System.out.println("1- Giriş Yap");
            System.out.println("2- Çıkış");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            Company hospital = new Company("Özel Hastane");

            switch (choice) {
                case 1:
                    System.out.print("Kullanıcı adı: ");
                    String username = scanner.nextLine();

                    System.out.print("Şifre: ");
                    String password = scanner.nextLine();

                    User loggedIn = authService.login(username, password);

                    if (loggedIn != null) {
                        System.out.println("Hoş geldiniz, " + loggedIn.getUsername());

                        if (loggedIn instanceof Admin) {
                            adminMenu();
                        }
                        else if (loggedIn instanceof Customer) {
                            customerMenu((Customer) loggedIn, scanner);

                        } else if (loggedIn instanceof RegularUser) {
                            userMenu((RegularUser) loggedIn, hospital, scanner);
                        }
                    } else {
                        System.out.println("Hatalı giriş!");
                    }
                    break;

                case 2:
                    System.out.println("Çıkış yapılıyor...");
                    running = false;
                    break;

                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private static AuthService systemSetup() {
        AuthService authService = new AuthService();

        Company hospital = new Company("Özel Hastane");
        hospital.addAvailableSlot(
                new TimeSlot(LocalDate.now(), LocalTime.of(10, 0))
        );

        Customer customer = new Customer("firma1", "1234", hospital);
        RegularUser user = new RegularUser("ali", "1111");

        authService.register(customer);
        authService.register(user);

        return authService;
    }
    private static void adminMenu() {
        System.out.println("\n--- ADMIN MENÜ ---");
        System.out.println("1- Şirket ekle");
        System.out.println("2- Kullanıcı listele");
        System.out.println("0- Çıkış");
    }
    private static void customerMenu(
            Customer customer,
            Scanner scanner) {

        boolean inMenu = true;

        while (inMenu) {
            System.out.println("\n--- CUSTOMER MENÜ ---");
            System.out.println("1- Uygun saat ekle");
            System.out.println("0- Çıkış");
            System.out.print("Seçim: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Saat (örn: 10): ");
                    int hour = scanner.nextInt();
                    scanner.nextLine();

                    TimeSlot slot = new TimeSlot(
                            LocalDate.now(),
                            LocalTime.of(hour, 0)
                    );

                    customer.getCompany().addAvailableSlot(slot);
                    System.out.println("Saat eklendi!");
                    break;

                case 0:
                    inMenu = false;
                    break;
            }
        }
    }

    private static void userMenu(
            RegularUser user,
            Company company,
            Scanner scanner) {

        AppointmentService appointmentService = new AppointmentService();
        boolean inMenu = true;

        while (inMenu) {
            System.out.println("\n--- KULLANICI MENÜ ---");
            System.out.println("1- Randevu al");
            System.out.println("0- Çıkış");
            System.out.print("Seçim: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nUygun Saatler:");

                    for (int i = 0; i < company.getAvailableSlots().size(); i++) {
                        TimeSlot slot = company.getAvailableSlots().get(i);
                        if (slot.isAvailable()) {
                            System.out.println(i + " - " + slot);
                        }
                    }

                    System.out.print("Saat seç (index): ");
                    int index = scanner.nextInt();
                    scanner.nextLine();

                    Appointment appointment =
                            appointmentService.createAppointment(
                                    user,
                                    company,
                                    company.getAvailableSlots().get(index)
                            );

                    if (appointment != null) {
                        System.out.println("Randevu oluşturuldu!");
                    } else {
                        System.out.println("Saat dolu!");
                    }
                    break;

                case 0:
                    inMenu = false;
                    break;
            }
        }
    }



}