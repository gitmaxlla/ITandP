import java.util.regex.*;

public class UpperAfterLower {
    public static void main(String[] args) {
        String text = "fruit appleJuice SomeText";
        Pattern pattern = Pattern.compile("[a-z][A-Z]");
        Matcher matcher = pattern.matcher(text);
        text = matcher.replaceAll(match -> "!" + match.group() + "!");
        System.out.println(text);
    }
}
