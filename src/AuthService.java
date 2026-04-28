import java.util.HashMap;
import java.util.Map;
public class AuthService {
    private Map<String, User> users = new HashMap<>();

    public void register(User user) {
        users.put(user.getUsername(), user);
    }
    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        return null;
    }
}
