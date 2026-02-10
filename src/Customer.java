public class Customer extends User{
    private Company company;

    public Customer(String username, String password, Company company) {
        super(username, password);
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }
}
