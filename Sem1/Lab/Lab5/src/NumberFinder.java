import java.util.regex.*;

public class NumberFinder {
    public static void main(String[] args) {
        Matcher finder = RegexInputChecker.matcherForInput(RegexInputChecker.getPattern("-?(\\d+[.,]\\d+|\\d+)", false));

        if (finder != null) {
            if (!finder.find()) System.out.println("No numbers found.");
            finder.reset();

            while (finder.find()) {
                System.out.println("-> " + finder.group());
            }
        }
    }
}
