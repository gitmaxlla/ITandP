// https://github.com/gitmaxlla/ITandP — Github-репозиторий с работами
import java.util.*;

public class SixthTen {
    public static void main(String[] args) {
        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
        System.out.println(hiddenAnagram("An old west action hero actor", "Clint Eastwood"));
        System.out.println(hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison"));
        System.out.println(hiddenAnagram("Banana? margaritas", "ANAGRAM"));
        System.out.println(hiddenAnagram("D  e b09it ->$ (c)a r...d,,#~", "bad credit"));
        System.out.println(hiddenAnagram("Bright is the moon", "Bongo mirth"));
        System.out.println();

        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[] {"b"}));
        System.out.println(stripUrlParams("https://edabit.com", new String[] {"b"}));
        System.out.println();

        System.out.println(nicoCipher("myworldevolvesinhers", "tesh"));
        System.out.println(nicoCipher("andiloveherso", "tesha"));
        System.out.println(nicoCipher("mubashirhassan", "crazy"));
        System.out.println(nicoCipher("edabitisamazing", "matt"));
        System.out.println(nicoCipher("iloveher", "612345"));
        System.out.println();

        System.out.println(Arrays.toString(twoProduct(new int[] {1, 2, 3, 9, 4, 5, 15, 3}, 45)));
        System.out.println(Arrays.toString(twoProduct(new int[] {1, 2, 3, 9, 4, 15, 3, 5}, 45)));
        System.out.println(Arrays.toString(twoProduct(new int[] {1, 2, -1, 4, 5, 6, 10, 7}, 20)));
        System.out.println(Arrays.toString(twoProduct(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10)));
        System.out.println(Arrays.toString(twoProduct(new int[] {100, 12, 4, 1, 2}, 15)));
        System.out.println();

        System.out.println(Arrays.toString(isExact(6)));
        System.out.println(Arrays.toString(isExact(24)));
        System.out.println(Arrays.toString(isExact(125)));
        System.out.println(Arrays.toString(isExact(720)));
        System.out.println(Arrays.toString(isExact(1024)));
        System.out.println(Arrays.toString(isExact(40320)));
        System.out.println();

        System.out.println(fractions("0.(6)"));
        System.out.println(fractions("1.(1)"));
        System.out.println(fractions("3.(142857)"));
        System.out.println(fractions("0.19(2367)"));
        System.out.println(fractions("0.1097(3)"));
        System.out.println();

        System.out.println(pilishString("HOWINEEDADRINKALCOHOLICINNATUREAFTERTHEHEAVYLECTURESINVOLVINGQUANTUMMECHANICSANDALLTHESECRETSOFTHEUNIVERSE"));
        System.out.println(pilishString("FORALOOP"));
        System.out.println(pilishString("CANIMAKEAGUESSNOW"));
        System.out.println(pilishString("33314444"));
        System.out.println(pilishString("TOP"));
        System.out.println(pilishString("X"));
        System.out.println(pilishString(""));
        System.out.println();

        System.out.println(formula("6 * 4 = 24"));
        System.out.println(formula("18 / 17 = 2"));
        System.out.println(formula("16 * 10 = 160 = 14 + 120"));
        System.out.println();

        System.out.println(isValid("aabbcd"));
        System.out.println(isValid("aabbccddeefghi"));
        System.out.println(isValid("abcdefghhgfedecba"));
        System.out.println();

        System.out.println(palindromeDescendant(11211230));
        System.out.println(palindromeDescendant(13001120));
        System.out.println(palindromeDescendant(23336014));
        System.out.println(palindromeDescendant(11));
        System.out.println();
    }

    public static String hiddenAnagram(String text, String anagram) {
        text = onlyLowercaseLetters(text);
        anagram = onlyLowercaseLetters(anagram);

        if (text.length() < anagram.length()) return "noutfond";

        for (int i = 0; i <= text.length() - anagram.length(); i++) {
            String toCheck = text.substring(i, i + anagram.length());
            if (countLetters(toCheck).equals(countLetters(anagram))) return toCheck;
        }

        return "noutfond";
    }

    public static HashMap<Character, Integer> countLetters(String input) {
        HashMap<Character, Integer> counter = new HashMap<>();

        for (char i: input.toCharArray()) {
            if (!counter.containsKey(i)) counter.put(i, 1);
            else counter.put(i, counter.get(i) + 1);
        }

        return counter;
    }

    public static String onlyLowercaseLetters(String input) {
        StringBuilder converter = new StringBuilder();
        for (char i: input.toLowerCase().toCharArray()) {
            if (Character.isAlphabetic(i)) converter.append(i);
        }
        return converter.toString();
    }

    public static String stripUrlParams(String url) {
        return stripUrlParams(url, new String[]{});
    }

    public static String stripUrlParams(String url, String[] removeParams) {
        StringBuilder parameterless = new StringBuilder();
        StringBuilder parameters = new StringBuilder();
        HashMap<String, String> parametersMap = new HashMap<>();

        for (char i : url.toCharArray()) {
            if (i == '?') break;
            parameterless.append(i);
        }

        parameters.append(url.substring(parameterless.length())).append("&");

        if (parameters.isEmpty()) return url;

        int startIndex = 1;
        int equalSignIndex = 1;

        for (int i = 1; i < parameters.length(); i++) {
            switch (parameters.charAt(i)) {
                case '=' -> equalSignIndex = i;
                case '&' -> {
                    String parameterName = parameters.substring(startIndex, equalSignIndex);
                    boolean toRemove = false;
                    for (String parameter: removeParams) {
                        if (parameterName.equals(parameter)) {
                            toRemove = true;
                        }
                    }
                    if  (!toRemove) parametersMap.put(parameterName, parameters.substring(equalSignIndex + 1, i));
                    startIndex = ++i;
                }
            }
        }

        StringBuilder result = new StringBuilder();

        result.append(parameterless).append("?");

        for (Map.Entry<String, String> parameter: parametersMap.entrySet()) {
            result.append(parameter.getKey()).append("=").append(parameter.getValue()).append("&");
        }

        return result.substring(0, result.length() - 1);
    }

    public static String nicoCipher(String message, String key) {
        char[] keySorted = key.toCharArray();
        Arrays.sort(keySorted);
        StringBuilder keySortedBuilder = new StringBuilder(String.valueOf(keySorted));

        StringBuilder keyBuilder = new StringBuilder(key);
        int[] numKey = new int[keyBuilder.length()];

        for (int i = 0; i < keyBuilder.length(); i++) {
            for (int j = 0; j < keyBuilder.length(); j++) {
                if (keyBuilder.charAt(i) == keySortedBuilder.charAt(j)) {
                    keySortedBuilder.setCharAt(j, ' ');
                    numKey[i] = j + 1;
                    break;
                }
            }
        }

        int columnsTotal = (int)(message.length() / key.length()) + (message.length() % key.length() == 0 ? 0 : 1);
        message = padWithSpaces(message, columnsTotal * key.length());

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < columnsTotal; i++) {
            String segment = message.substring(i*key.length(), (i+1)*key.length());
            HashMap<Integer, Character> toSort = new HashMap<>();
            for (int j = 0; j < segment.length(); j++) {
                toSort.put(numKey[j], segment.charAt(j));
            }

            for (char j: toSort.values()) result.append(j);
        }

        return result.toString();
    }

    public static String padWithSpaces(String input, int length) {
        if (input.length() >= length) return input;
        StringBuilder result = new StringBuilder(input);
        for (int i = 0; i < length - input.length(); i++) {
            result.append(' ');
        }
        return result.toString();
    }

    public static int[] twoProduct(int[] arr, int n) {
        HashSet<Integer> pairCandidates = new HashSet<>();

        for (int i: arr) {
            if (n % i == 0) {
                if (pairCandidates.contains(n / i)) {
                    return new int[] {n / i, i};
                }
                pairCandidates.add(i);
            }
        }

        return new int[] {};
    }

    public static int[] isExact(int limit) {
        int[] result = getNearestFactorial(1, 1, limit);
        if (result[2] != 0) return new int[] {};
        return new int[] {result[1], result[0]};
    }

    public static int[] getNearestFactorial(int start, int value, int limit) {
        if (value >= limit) return new int[] {start - 1, value, value % limit};
        return getNearestFactorial(start + 1, value*start, limit);
    }

    public static String fractions(String input) {
        int nonPeriodicDecimals = 0;
        int periodicDecimals = 0;

        boolean afterPeriod = false;
        boolean repeatingPart = false;

        StringBuilder numText = new StringBuilder();

        for (char i: input.toCharArray()) {
            if (Character.isDigit(i) || i == '.') numText.append(i);
            if (i == ')') break;
            if (i == '(') repeatingPart = true;

            if (afterPeriod && i != '(') {
                if (!repeatingPart) ++nonPeriodicDecimals;
                else ++periodicDecimals;
            }

            if (i == '.') afterPeriod = true;
        }

        double num = Double.parseDouble(numText.toString());

        int incPeriodMul = (int) Math.pow(10, nonPeriodicDecimals + periodicDecimals);
        int excPeriodMul = (int) Math.pow(10, nonPeriodicDecimals);

        int intIncludingPeriod = (int) (num * incPeriodMul);
        int intExcPeriod = (int) (num * excPeriodMul);

        System.out.println(input);
        int nominator = intIncludingPeriod - intExcPeriod;
        int denominator = incPeriodMul - excPeriodMul;

        int simpleNominator = nominator % denominator;
        int wholePart = nominator / denominator;

        for (int i = (simpleNominator / 2) + 1; i > 0; i--) {
            if (denominator % i == 0 && simpleNominator % i == 0) {
                simpleNominator /= i;
                denominator /= i;
            }
        }

        return ((wholePart * denominator) + simpleNominator) + "/" + denominator;
    }

    public static String pilishString(String input) {
        final char[] DIGITS = "314159265358979".toCharArray();
        StringBuilder result = new StringBuilder();
        int counter = 0;

        if(input.isEmpty()) return "";

        for (char i: DIGITS) {
            int num = i - '0';

            for (int j = 0; j < num; j++) {
                if (counter == input.length()) {
                    result.repeat(result.charAt(result.length() - 1), num - j);
                    break;
                }
                result.append(input.charAt(counter++));
            }

            if (counter == input.length()) break;
            result.append(' ');
        }

        return result.toString();
    }

    public static boolean formula(String input) {
        String[] terms = input.split("\s");
        String operators = "+-=/*";

        List<Integer> nums = new ArrayList<>();
        List<String> operations = new ArrayList<>();

        for(String i: terms) {
            if (operators.contains(i)) operations.add(i);
            else nums.add(Integer.parseInt(i));
        }

        System.out.println(input);

        operations = operations.reversed();
        nums = nums.reversed();

        ArrayList<Integer> comparisons = new ArrayList<>();

        for (String operation: operations) {
            switch (operation) {
                case "+" -> nums.addFirst(nums.removeFirst() + nums.removeFirst());
                case "-" -> nums.addFirst(nums.removeFirst() - nums.removeFirst());
                case "*" -> nums.addFirst(nums.removeFirst() * nums.removeFirst());
                case "/" -> nums.addFirst(nums.removeFirst() / nums.removeFirst());
                case "=" -> comparisons.add(nums.removeFirst());
            }
        }

        comparisons.add(nums.removeFirst());

        for (int i = 0; i < comparisons.size() - 1; i++) {
            if (comparisons.get(i) != comparisons.get(i + 1)) return false;
        }

        return true;
    }

    public static String isValid(String input) {
        HashMap<Character, Integer> counter = new HashMap<>();

        for (char i: input.toCharArray()) {
            if (!counter.containsKey(i)) counter.put(i, 1);
            else counter.put(i, counter.get(i) + 1);
        }

        int notMins = 0;
        int min = Integer.MAX_VALUE;
        int max = 0;

        for(Map.Entry<Character, Integer> letter: counter.entrySet()) {
            if (letter.getValue() > max) max = letter.getValue();
            if (letter.getValue() < min) min = letter.getValue();
        }

        for(Integer num: counter.values()) if (num != min) ++notMins;

        if (max - min <= 1 && notMins <= 1) {
            return "YES";
        }

        return "NO";
    }

    public static boolean palindromeDescendant(Integer input) {
        StringBuilder numText = new StringBuilder(""+input);

        while(numText.length() > 1) {
            if (isPalindrome(numText)) return true;
            numText = new StringBuilder(descendant(numText));
        }
        return false;
    }

    public static String descendant(StringBuilder input) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length() / 2; i++)
            result.append(Integer.parseInt(input.charAt(i*2) + "") + Integer.parseInt(input.charAt(i*2 + 1) + ""));

        return result.toString();
    }


    public static boolean isPalindrome(StringBuilder input) {
        return input.substring(0, input.length() / 2).contentEquals(new StringBuilder(input.substring(input.length() / 2)).reverse());
    }
}
