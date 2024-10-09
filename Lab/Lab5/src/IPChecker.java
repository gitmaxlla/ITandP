import java.util.regex.*;

public class IPChecker {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("^((25[0-5]|2[0-4][0-9]|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4][0-9]|1\\d{2}|[1-9]\\d|\\d)$");
    }
}
