import java.util.Scanner;
import java.util.regex.*;

public class RegexInputChecker {
    private static final Scanner scanner = new Scanner(System.in);

    public static Matcher matcherForInput(Pattern pattern) {
        String input;

        if (pattern == null) return null;

        try {
            System.out.print("Enter input string: ");
            input = scanner.nextLine();
        } catch (RuntimeException ex) {
            System.out.println("Error reading string: " + ex.getMessage());
            return null;
        }

        return pattern.matcher(input);
    }

    public static Pattern getPattern(String regex, boolean case_insensitive) {
        Pattern pattern = null;

        try {
            if(case_insensitive) pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            else pattern = Pattern.compile(regex);
        } catch (PatternSyntaxException ex) {
            System.out.println("There's an error in regex syntax: " + ex.getMessage());
            return null;
        }

        return pattern;
    }
}
