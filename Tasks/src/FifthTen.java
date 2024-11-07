// https://github.com/gitmaxlla/ITandP — Github-репозиторий с работами
import java.util.HashMap;

public class FifthTen {
    public static void main(String[] args) {
        System.out.println(sameLetterPattern("ABAB", "CDCD"));
        System.out.println(sameLetterPattern("ABCBA", "BCDCB"));
        System.out.println(sameLetterPattern("FFGG", "CDCD"));
        System.out.println(sameLetterPattern("FFFF", "ABCD"));
        System.out.println();

        System.out.println(memeSum(26, 39));
        System.out.println(memeSum(122, 81));
        System.out.println(memeSum(1222, 30277));
        System.out.println();

        System.out.println(digitsCount(4666));
        System.out.println(digitsCount(544));
        System.out.println(digitsCount(121317));
        System.out.println(digitsCount(0));
        System.out.println(digitsCount(12345));
        System.out.println(digitsCount(1289396387328L));
        System.out.println();

        System.out.println(totalPoints(new String[] {"cat", "create", "sat"}, "caster"));
        System.out.println(totalPoints(new String[] {"trance", "recant"}, "recant"));
        System.out.println(totalPoints(new String[] {"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed"));
        System.out.println();

        System.out.println(longestRun(new int[] {1, 2, 3, 5, 6, 7, 8, 9}));
        System.out.println(longestRun(new int[] {1, 2, 3, 10, 11, 15}));
        System.out.println(longestRun(new int[] {5, 4, 2, 1}));
        System.out.println(longestRun(new int[] {3, 5, 7, 10, 15}));
        System.out.println();

    }

    public static boolean sameLetterPattern(String a, String b) {
        String[] uniPatterns = {a, b};

        for (int i = 0; i < uniPatterns.length; i++) {
            HashMap<Character, Integer> uniPatternMap = new HashMap<>();
            StringBuilder uniPattern = new StringBuilder();
            int id = 0;

            for (char j : uniPatterns[i].toCharArray()) {
                if (!uniPatternMap.containsKey(j))
                    uniPatternMap.put(j, id++);
                uniPattern.append(uniPatternMap.get(j));
            }

            uniPatterns[i] = uniPattern.toString();
        }

        return uniPatterns[0].equals(uniPatterns[1]);
    }

    public static int memeSum(int a, int b) {
        StringBuilder result = new StringBuilder();

        while (a % 10 != 0 || b % 10 != 0) {
            result.append((a % 10 + b % 10));
            a /= 10;
            b /= 10;
        }

        return Integer.parseInt(result.reverse().toString());
    }

    public static int digitsCount(long num) {
        return (num / 10 == 0) ? 1 : 1 + digitsCount(num / 10);
    }

    public static int totalPoints(String[] words, String cipher) {
        int totalSum = 0;

        HashMap<Character, Integer> availableLetters = new HashMap<>();
        for (Character i : cipher.toCharArray()) {
            if (!availableLetters.containsKey(i)) availableLetters.put(i, 1);
            else availableLetters.put(i, availableLetters.get(i) + 1);
        }

        for (String word : words) {
            var curAvbLetters = new HashMap<>(availableLetters);
            boolean validWord = true;

            for (Character i : word.toCharArray()) {
                if (!curAvbLetters.containsKey(i)) {
                    validWord = false;
                    break;
                }
                else {
                    if (curAvbLetters.get(i) == 1) curAvbLetters.remove(i);
                    else curAvbLetters.put(i, curAvbLetters.get(i) - 1);
                }
            }

            if (validWord) totalSum += word.length() - 2 + (word.length() == 6 ? 50 : 0);
        }

        return totalSum;
    }

    public static int longestRun(int[] numbers) {
        int maxLength = 0;
        int currentLength = 0;
        boolean captureAscending = false;

        for (int i = 0; i < numbers.length - 1; i++) {
            ++currentLength;

            if ((numbers[i] + 1 == numbers[i + 1])) {
                if (!captureAscending) {
                    captureAscending = true;
                    maxLength = Math.max(maxLength, currentLength);
                    currentLength = 1;
                }
                else if (i == numbers.length - 2)
                    maxLength = Math.max(maxLength, currentLength + 1);
            } else if ((numbers[i] - 1 == numbers[i + 1])) {
                if (captureAscending) {
                    captureAscending = false;
                    maxLength = Math.max(maxLength, currentLength);
                    currentLength = 1;
                }
                else if (i == numbers.length - 2)
                    maxLength = Math.max(maxLength, currentLength + 1);
            } else if (Math.abs(numbers[i] - numbers[i + 1]) != 1) {
                maxLength = Math.max(maxLength, currentLength);
                currentLength = 0;
            }
        }

        return maxLength;
    }
}