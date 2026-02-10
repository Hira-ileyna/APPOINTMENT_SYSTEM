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

            switch (choice) {
                case 1:
                    System.out.print("Kullanıcı adı: ");
                    String username = scanner.nextLine();

                    System.out.print("Şifre: ");
                    String password = scanner.nextLine();

                    User loggedIn = authService.login(username, password);

                    if (loggedIn != null) {
                        System.out.println("Hoş geldiniz, " + loggedIn.getUsername());
                        // ŞİMDİLİK BURADA DURUYORUZ
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
}