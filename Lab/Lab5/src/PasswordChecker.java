import java.util.regex.*;

public class PasswordChecker {
    public static void main(String[] args) {
        String password = "12345678Aa";
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[A-Z])[0-9a-zA-Z]{8,16}$");
    }
}
