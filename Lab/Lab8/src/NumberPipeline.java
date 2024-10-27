import java.util.ArrayList;
import java.util.Scanner;

public class NumberProcessor {
    private ArrayList<Integer> toIntArray(String input) {
        Scanner scanner = new Scanner(input);
        ArrayList<Integer> numbers = new ArrayList<>();
        while (scanner.hasNextInt()) numbers.add(scanner.nextInt());
        scanner.close();
        return numbers;
    }

    @DataProcessor(DataProcessor.Type.FILTER)
    public String getNumbers(String input) {
        return toIntArray(input).toString();
    }

    @DataProcessor(DataProcessor.Type.SEARCH)
    public String max(String input) {
        return "" + toIntArray(input).stream().max(Integer::compareTo);
    }

    @DataProcessor(DataProcessor.Type.FILTER)
    public String oddNumbers(String input) {
        return toIntArray(input).stream().filter(number -> number % 2 == 1).toString();
    }

    @DataProcessor(DataProcessor.Type.SEARCH)
    public String min(String input) {
        return "" + toIntArray(input).stream().min(Integer::compareTo);
    }

    @DataProcessor(DataProcessor.Type.AGGREGATE)
    public String average(String input) {
        ArrayList<Integer> numbers = toIntArray(input);
        int sum = 0;

        for (int i : numbers)
            sum += i;

        return "" + (sum / numbers.size());
    }
}
