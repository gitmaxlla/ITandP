import java.util.regex.*;

public class UpperAfterLower {
    public static void main(String[] args) {
        Pattern pattern = RegexInputChecker.getPattern("[a-z][A-Z]", false);
        Matcher matcher = RegexInputChecker.matcherForInput(pattern);

        if (matcher != null) {
            System.out.println(matcher.replaceAll(match -> "!" + match.group() + "!"));
        }
    }
}
