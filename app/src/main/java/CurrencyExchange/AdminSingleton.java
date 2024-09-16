package CurrencyExchange;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
//Singleton
public class AdminSingleton {
    private static AdminSingleton instance;
    private final String username;
    private final String passwordHashed;

    private AdminSingleton() {
        this.username = "admin";
        this.passwordHashed = "ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f";
    }


    public static AdminSingleton getInstance() {
        if (instance == null) {
            instance = new AdminSingleton();
        }
        return instance;
    }


    public Boolean verifyUserName(String username) {
        return this.username.equals(username);
    }


    public Boolean verifyPasswordHashed(String password) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            final StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                final String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString().equals(this.passwordHashed);
        } catch (Exception ex) {
            return false;
        }
    }
}
