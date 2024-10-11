import java.util.Scanner;
import java.util.regex.*;

public class StartsWithLetter {
    public static void main(String[] args) {
        char selected = ' ';
        boolean noErrors = true;
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Input a character: ");
            selected = scanner.next().toLowerCase().charAt(0);
        } catch (RuntimeException ex) {
            System.out.println("Error during input of a character: " + ex.getMessage());
            noErrors = false;
        }

        if (noErrors) {
            Matcher finder = RegexInputChecker.matcherForInput(RegexInputChecker.getPattern("\\b" + selected + "[A-Za-z]+\\b", true));

            if (finder != null) {
                if (!finder.find()) System.out.println("No words starting from the letter '" + selected + "' found.");
                finder.reset();

                while (finder.find()) {
                    System.out.println("-> " + finder.group());
                }
            }
        }
    }
}
