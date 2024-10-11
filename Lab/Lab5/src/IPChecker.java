import java.util.regex.*;

public class IPChecker {
    public static void main(String[] args) {
        Matcher finder = RegexInputChecker.matcherForInput(RegexInputChecker.getPattern("^((25[0-5]|2[0-4][0-9]|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4][0-9]|1\\d{2}|[1-9]\\d|\\d)$", false));

        if (finder != null) {
            if (!finder.find()) System.out.println("Not an IP.");
            else System.out.println("Correct IP address.");
        }
    }
}
