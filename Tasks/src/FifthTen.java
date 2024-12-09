// https://github.com/gitmaxlla/ITandP — Github-репозиторий с работами
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

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

        System.out.println(takeDownAverage(new String[] {"95%", "83%", "90%", "87%", "88%", "93%"}));
        System.out.println(takeDownAverage(new String[] {"10%"}));
        System.out.println(takeDownAverage(new String[] {"53%", "79%"}));
        System.out.println();

        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));
        System.out.println();

        System.out.println(maxPossible(9328, 456));
        System.out.println(maxPossible(523, 76));
        System.out.println(maxPossible(9132, 5564));
        System.out.println(maxPossible(8732, 91255));
        System.out.println();

        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome"));
        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing"));
        System.out.println();

        System.out.println(isNew(3));
        System.out.println(isNew(30));
        System.out.println(isNew(321));
        System.out.println(isNew(123));
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
        boolean ascDesc = false; // false for descending

        for (int i = 0; i < numbers.length - 1; i++) {
            ++currentLength;

            if (Math.abs(numbers[i] - numbers[i + 1]) == 1) {
                boolean nextAscDesc = numbers[i + 1] - numbers[i] == 1;

                if (ascDesc != nextAscDesc) {
                    ascDesc = !ascDesc;
                    maxLength = Math.max(maxLength, currentLength);
                    currentLength = 1;
                }
                else if (i == numbers.length - 2)
                    maxLength = Math.max(maxLength, currentLength + 1);
            } else {
                maxLength = Math.max(maxLength, currentLength);
                currentLength = 0;
            }
        }

        return maxLength;
    }

    public static String takeDownAverage(String[] input) {
        int sum = 0;

        for (String entry : input) {
            sum += Integer.parseInt(entry.substring(0, entry.length() - 1));
        }

        return Math.round((input.length + 1) * ((double)sum / input.length - 5) - sum) + "%";
    }

    public static boolean canMove(String figure, String from, String to) {
        Map<Character, Integer> letterToCoords  = Map.of('A', 1, 'B', 2, 'C', 3, 'D', 4, 'E', 5, 'F', 6, 'G', 7, 'H', 8);
        int[] fromCoords = {letterToCoords.get(from.charAt(0)), (int)from.charAt(1)};
        int[] toCoords = {letterToCoords.get(to.charAt(0)), (int)to.charAt(1)};

        int distanceH = Math.abs(toCoords[0] - fromCoords[0]);
        int distanceV = Math.abs(toCoords[1] - fromCoords[1]);

        if (distanceH + distanceV == 0) return false;

        boolean diagonalFactor = (distanceH == distanceV);
        boolean parallelFactor = (distanceH == 0 || distanceV == 0);

        return switch (figure) {
            case ("Pawn") -> distanceH == 0 && distanceV == 1;
            case ("King") -> distanceH <= 1 && distanceV <= 1;
            case ("Knight") -> !parallelFactor && (distanceH + distanceV == 3);

            case ("Bishop") -> diagonalFactor;
            case ("Rook") -> parallelFactor;
            case ("Queen") -> diagonalFactor || parallelFactor;

            default -> false;
        };
    }

    public static int maxPossible(int num, int swap) {
        // A solution with no Strings attached (pun intended)
        int maxDigit = 0;
        int numCopy = num;

        do {
            ++maxDigit;
            numCopy /= 10;
        }
        while (numCopy != 0);

        while (maxDigit != 0) {
            int curDigit = (num / (int)Math.pow(10, --maxDigit)) % 10;

            int swappedSwap = 0;
            int swapCopy = swap;
            int swapDigit = 0;

            int maxSwapDigit = 0;
            int maxSwapper = 0;

            do {
                int curSwapDigit = swapCopy % 10;
                swapCopy /= 10;

                swappedSwap += (int)Math.pow(10, swapDigit) * curSwapDigit;

                if (curSwapDigit > maxSwapDigit) {
                    maxSwapper = (int)Math.pow(10, swapDigit) * curSwapDigit;
                    maxSwapDigit = curSwapDigit;
                }

                ++swapDigit;
            }
            while (swapCopy != 0);

            swap = swappedSwap;

            if (maxSwapDigit > curDigit) {
                curDigit = maxSwapDigit;
                swap -= maxSwapper;
            }

            numCopy += (int)Math.pow(10, maxDigit) * curDigit;
        }

        return numCopy;
    }

    public static String timeDifference(String cityA, String timestamp, String cityB) {
        HashMap<String, Integer> timeZone = new HashMap<>(Map.of( "New York", -10, "Caracas", -9, "Buenos Aires", -6, "London", 0, "Rome", 2,
                                                "Moscow", 6, "Tehran", 7, "New Delhi", 11, "Beijing", 16, "Canberra", 20));
        timeZone.put("Los Angeles", -16);

        HashMap<String, Integer> monthToNum = new HashMap<>(Map.of("January", 1, "February", 2, "March", 3, "April", 4, "May", 5, "June", 6, "July", 7, "August", 8, "September", 9));
        monthToNum.putAll(Map.of("October", 10, "November", 11, "December", 12));

        HashMap<Integer, Integer> monthNumDays = new HashMap<>(Map.of(1, 31, 2, 28, 3, 31, 4, 30, 5, 31, 6, 30, 7, 31, 8, 31, 9, 30, 10, 31));
        monthNumDays.putAll(Map.of( 11, 30, 12, 31));


        ArrayList<Integer> data = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\d+");
        for (MatchResult numMatch : pattern.matcher(timestamp).results().toList())
            data.add(Integer.parseInt(numMatch.group()));

        StringBuilder getDateStr = new StringBuilder();
        for (char letter : timestamp.toCharArray()) {
            if (!Character.isAlphabetic(letter)) break;
            getDateStr.append(letter);
        }

        int hours = data.get(2);
        int minutes = data.get(3);
        int year = data.get(1);
        int dayOfMonth = data.get(0);
        int monthNum = monthToNum.get(getDateStr.toString());

        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
            monthNumDays.put(2, 29);

        int halfHourDifference = timeZone.get(cityB) - timeZone.get(cityA);

        if (halfHourDifference % 2 == 1) {
            minutes += 30;
            if (minutes / 60 != 0) {
                ++hours;
                minutes %= 60;
            }
        }

        hours += halfHourDifference / 2;

        if (hours / 24 != 0) {
            dayOfMonth += hours / 24;
            hours %= 24;
        }

        if (dayOfMonth > monthNumDays.get(monthNum)) {
            dayOfMonth = 1;
            if (monthNum == 12) {
                ++year;
                monthNum = 0;
            }
            ++monthNum;
        }

        return String.format("%d-%d-%d %02d:%02d", year, monthNum, dayOfMonth, hours, minutes);
    }

    public static boolean isNew(int num) {
        do {
            int prevNum = (num / 10) % 10;
            int nextNum = num % 10;
            if (!(num / 100 == 0 && num % 10 == 0) && (prevNum > nextNum || (nextNum == 0 && prevNum != 0))) return false;
            num /= 10;
        } while (num / 10 != 0);

        return true;
    }
}