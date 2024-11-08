import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class StringPipeline {
    private String regex = ".";

    public StringPipeline(String regex) {
        this.regex = regex;
    }

    @DataProcessor(DataProcessor.Type.IGNORE)
    public String debugRegex(String input) {
        System.out.println("Using regex: " + regex);
        return "";
    }

    @DataProcessor(DataProcessor.Type.SEARCH)
    public String regex(String input) {
        return Pattern.compile(regex).matcher(input).results().map(MatchResult::group).toList().toString();
    }

    @DataProcessor(DataProcessor.Type.TRANSFORM)
    public String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }

}
