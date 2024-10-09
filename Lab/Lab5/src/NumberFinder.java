import java.util.regex.*;

public class NumberFinder {
    public static void main(String[] args) {
        String text = "The price of product is 19.99$";
        Pattern numbers = Pattern.compile("\\d+.\\d+");
        Matcher finder = numbers.matcher(text);

        while (finder.find()) {
            System.out.println(finder.group());
        }
    }
}
