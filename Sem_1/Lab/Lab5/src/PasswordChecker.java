import java.util.regex.*;

public class PasswordChecker {
    public static void main(String[] args) {
        Pattern pattern = RegexInputChecker.getPattern("^(?=.*\\d)(?=.*[A-Z])[0-9a-zA-Z]{8,16}$", false);

        while (true) {
            Matcher finder = RegexInputChecker.matcherForInput(pattern);

            if (finder != null) {
                if (finder.find()) {
                    System.out.println("Password is correct");
                    break;
                }
                else System.out.println("Password should contain at least one number and one capital letter and be 8-16 characters long.\nIt can only contain latin letters and digits.\nTry again.");
            }
        }
    }
}
