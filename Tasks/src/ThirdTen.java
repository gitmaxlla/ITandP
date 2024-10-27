// https://github.com/gitmaxlla/ITandP — Github-репозиторий с работами
import java.util.Arrays;
import java.util.HashSet;
public class ThirdTen {
    public static void main(String[] args) {
        System.out.println(isStrangePair("ratio", "orator"));
        System.out.println(isStrangePair("sparkling", "groups"));
        System.out.println(isStrangePair("bush", "hubris"));
        System.out.println(isStrangePair("", ""));
        System.out.println();

        System.out.println(Arrays.deepToString(sale(new Object[][]{new Object[]{"Laptop", 124200}, new Object[]{"Phone", 51450}, new Object[]{"Headphones", 13800}}, 25)));
        System.out.println();

        System.out.println(successShoot(0, 0, 5, 2, 2));
        System.out.println(successShoot(-2, -3, 4, 5, -6));
        System.out.println();

        System.out.println(parityAnalysis(243));
        System.out.println(parityAnalysis(12));
        System.out.println(parityAnalysis(3));
        System.out.println();

        System.out.println(rps("rock", "paper"));
        System.out.println(rps("paper", "rock"));
        System.out.println(rps("paper", "scissors"));
        System.out.println(rps("scissors", "scissors"));
        System.out.println(rps("scissors", "paper"));
        System.out.println();

        System.out.println(bugger(39));
        System.out.println(bugger(999));
        System.out.println(bugger(4));
        System.out.println();

        System.out.println(mostExpensive(new Object[][] {new Object[] {"Скакалка", 550, 8}, new Object[] {"Шлем", 3750, 4}, new Object[] {"Мяч", 2900, 10}}));
        System.out.println();

        System.out.println(longestUnique("abcba"));
        System.out.println(longestUnique("bbb"));
        System.out.println();

        System.out.println(isPrefix("automation", "auto-"));
        System.out.println(isSuffix("arachnophobia", "-phobia"));
        System.out.println(isPrefix("retrospect", "sub-"));
        System.out.println(isSuffix("vocation", "-logy"));
        System.out.println();

        System.out.println(doesBrickFit(1, 1, 1, 1, 1));
        System.out.println(doesBrickFit(1, 2, 1, 1, 1));
        System.out.println(doesBrickFit(1, 2, 2, 1, 1));

        System.out.println();

    }

    public static boolean isStrangePair(String a, String b) {
        return (a.equals(b) && a.isEmpty()) || (a.charAt(0) == b.charAt(b.length() - 1) && b.charAt(0) == a.charAt(a.length() - 1));
    }

    public static Object[][] sale(Object[][] arr, double discount) {
        for (Object[] item : arr) {
            item[1] = Math.max(1, Math.round((Integer)item[1] * (1 - (discount / 100.0))));
        }

        return arr;
    }

    public static boolean successShoot(double x, double y, double r, double m, double n) {
        return Math.pow((m - x), 2) + Math.pow((n - y), 2) <= Math.pow(r, 2);
    }

    public static boolean parityAnalysis(Integer num) {
        int sum = 0;
        final boolean parity = num % 2 == 0;

        do {
            sum += num % 10;
            num /= 10;
        } while (num != 0);

        return (sum % 2 == 0) == parity;
    }

    public static String rps(String p1, String p2) {
        int score = p1.charAt(0) - p2.charAt(0);
        if (score == 0) return "Tie";
        return "Player "  + (1 + ((score == 1 || score == 2 || score == -3) ? 1 : 0)) + " wins";
    }

    public static int bugger(int num) {
        int counter = 0;
        while (num > 10) {
            int accumulator = 1;

            do {
                accumulator *= num % 10;
                num /= 10;
            } while (num != 0);

            num = accumulator;
            ++counter;
        }

        return counter;
    }

    public static String mostExpensive(Object[][] items) {
        int maximum = -1;
        String maximum_name = "";

        for (Object[] item : items) {
            int compare_with = (Integer)item[1] * (Integer)item[2];
            if (compare_with > maximum) {
                maximum = compare_with;
                maximum_name = (String)item[0];
            }
        }

        return "Наиб. общ. стоимость у предмета " + maximum_name + " - " + maximum;
    }

    public static String longestUnique(String input) {
        String longest_substr = "";
        HashSet<Character> already_appeared = new HashSet<Character> ();
        int start_index = 0;

        for (int i = 0; i < input.length() + 1; i++) {
            Character cur_char = input.charAt(Math.min(i, input.length() - 1));

            if (!already_appeared.contains(cur_char)) {
                already_appeared.add(cur_char);
            } else {
                if (input.substring(start_index, i).length() > longest_substr.length()) {
                    longest_substr = input.substring(start_index, i);
                }

                already_appeared.clear();
                already_appeared.add(cur_char);
                start_index = i;
            }
        }
        return longest_substr;
    }

    public static boolean isPrefix(String word, String prefix_) {
        for (int i = 0; i < prefix_.length() - 1; i++) if (word.charAt(i) != prefix_.charAt(i)) return false;
        return true;
    }

    public static boolean isSuffix(String word, String _suffix) {
        for (int i = 1; i < _suffix.length(); i++) if (word.charAt(word.length() - _suffix.length() + i) != _suffix.charAt(i)) return false;
        return true;
    }

    public static boolean doesBrickFit(int a, int b, int c, int w, int h) {
        int abs_min = Math.min(Math.min(a, b), c);
        int abs_max = Math.max(Math.max(a, b), c);
        return (abs_min <= Math.min(w, h) && ((a + b + c) - (abs_max + abs_min)) <= Math.max(w, h));
    }

}
