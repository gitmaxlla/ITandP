import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class NumberPipeline {
    private List<Integer> toIntArray(String input) {
        Scanner scanner = new Scanner(input);
        return scanner.findAll("\\d+").map(matchResult -> Integer.valueOf(matchResult.group())).toList();
    }

    @DataProcessor(DataProcessor.Type.FILTER)
    public String getNumbers(String input) {
        return toIntArray(input).toString();
    }

    @DataProcessor(DataProcessor.Type.SEARCH)
    public String max(String input) {
        AtomicReference<String> number = new AtomicReference<>("NONE");
        toIntArray(input).stream().max(Integer::compareTo).ifPresent((value) -> number.set(String.valueOf(value)));
        return number.get();
    }

    @DataProcessor(DataProcessor.Type.FILTER)
    public String oddNumbers(String input) {
        return Arrays.toString(toIntArray(input).stream().filter(number -> number % 2 == 1).toArray());
    }

    @DataProcessor(DataProcessor.Type.SEARCH)
    public String min(String input) {
        AtomicReference<String> number = new AtomicReference<>("NONE");
        toIntArray(input).stream().min(Integer::compareTo).ifPresent((value) -> number.set(String.valueOf(value)));
        return number.get();
    }

    @DataProcessor(DataProcessor.Type.AGGREGATE)
    public String average(String input) {
        List<Integer> numbers = toIntArray(input);
        if (numbers.isEmpty()) return "NONE";
        long sum = 0;

        for (int i : numbers)
            sum += i;
        return "" + (sum / numbers.size());
    }
}
