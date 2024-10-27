// https://github.com/gitmaxlla/ITandP — Github-репозиторий с работами
import java.util.*;

public class FourthTen {
    public static void main(String[] args) {
        System.out.println(nonRepeat("abracadabra"));
        System.out.println(nonRepeat("abababcac"));
        System.out.println();

        System.out.println(bruteForce(1, 5).toString());
        System.out.println(bruteForce(2, 2).toString());
        System.out.println(bruteForce(5, 3).toString());
        System.out.println();

        System.out.println(encode(new int[] {0, 31, 28, 10, 29}, "MKIIT"));
        System.out.println(Arrays.toString(decode("MTUCI", "MKIIT")));
        System.out.println();

        System.out.println(split("()()()").toString());
        System.out.println(split("((()))").toString());
        System.out.println(split("((()))(())()()(()())").toString());
        System.out.println(split("((())())(()(()()))").toString());
        System.out.println();

        System.out.println(shortHand("abbccc"));
        System.out.println(shortHand("vvvvaajaaaaa"));
        System.out.println();

        System.out.println(convertToRome(8));
        System.out.println(convertToRome(1234));
        System.out.println(convertToRome(52));
        System.out.println();

        System.out.println(uniqueSubstring("31312131"));
        System.out.println(uniqueSubstring("1111111"));
        System.out.println(uniqueSubstring("12223234333"));
        System.out.println();

        System.out.println(Arrays.toString(labirint(new int[][] {new int[] {1, 3, 1}, new int[] {1, -1, 1}, new int[] {4, 2, 1}})));
        System.out.println(Arrays.toString(labirint(new int[][]{new int[]{2, -7, 3}, new int[]{-4, -1, 8}, new int[]{4, 5, 9}})));
        System.out.println();

        System.out.println(numericOrder("t3o the5m 1One all6 r4ule ri2ng"));
        System.out.println(numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat"));
        System.out.println();

        System.out.println(fibString("CCCABDD"));
        System.out.println(fibString("ABC"));
        System.out.println();
    }

    public static String nonRepeat(String input) {
        input = input.toLowerCase();
        int counter = 1;
        char toCount = input.charAt(0);
        boolean done = true;
        StringBuilder result = new StringBuilder();

        for(int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == toCount) ++counter;
            if (counter > 3) {
                done = false;
                for (char j : input.toCharArray()) {
                    if (j != toCount) result.append(j);
                }
                break;
            }
        }

        if(done) return input;
        else return nonRepeat(result.toString());
    }

    public static ArrayList<String> bruteForce(int n, int k) {
        StringBuilder alphabet = new StringBuilder();
        for(int i = 0; i < k; i++) alphabet.append(String.valueOf(i));
        return reiterateString(n, alphabet);
    }


    public static ArrayList<String> reiterateString(int n, StringBuilder alphabet) {
        ArrayList<String> result = new ArrayList<>();

        if (n == 0) {
            result.add("");
            return result;
        }

        for (int i = 0; i < alphabet.length(); i++) {
            ArrayList<String> previous = reiterateString(n - 1, new StringBuilder(alphabet).deleteCharAt(i));
            for (String str: previous) {
                result.add(alphabet.charAt(i) + str);
            }
        }

        return result;
    }

    public static String encode(int[] data, String key) {
        StringBuilder result = new StringBuilder(key);
        for (int i = 0; i < data.length; i++) result.setCharAt(i, (char)(result.charAt(i) ^ data[i]));
        return result.toString();
    }

    public static int[] decode(String data, String key) {
        int[] result = new int[data.length()];
        for (int i = 0; i < data.length(); i++) result[i] = data.charAt(i) ^ key.charAt(i);
        return result;
    }

    public static ArrayList<String> split(String input) {
        int balance = 0;
        int start = 0;
        ArrayList<String> result = new ArrayList<>();

        for(int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') ++balance;
            else if (input.charAt(i) == ')') --balance;
            if (balance == 0) {
                result.add(input.substring(start, i + 1));
                start = i + 1;
            }
        }

        return result;
    }

    public static String shortHand(String input) {
        StringBuilder result = new StringBuilder();
        boolean series = false;
        int repeatCounter = 1;

        for (int i = 0; i < input.length(); i++) {
            if (!series) {
                result.append(input.charAt(i));
                if (input.charAt(i) == input.charAt(i + 1)) {
                    series = true;
                    result.append("*");
                }
            }
            else {
                ++repeatCounter;
                if (i + 1 == input.length() || input.charAt(i) != input.charAt(i + 1)) {
                    series = false;
                    result.append(repeatCounter);
                    repeatCounter = 1;
                }
            }
        }

        return result.toString();
    }

    public static String convertToRome(int number) {
        StringBuilder result = new StringBuilder();
        String sets = "IVXLCDM";

        int numLength = String.valueOf(number).length();

        for (int i = 0; i < numLength; i++) {
            int convert = number % 10;

            if(convert <= 3) {
                result.append(String.valueOf(sets.charAt(2 * i)).repeat(convert));
            } else if (convert == 4) {
                result.append(sets.charAt(i * 2 + 1)).append(sets.charAt(i * 2));
            } else if (convert == 5) {
                result.append(sets.charAt(i * 2 + 1));
            } else if (convert == 6) {
                result.append(sets.charAt(i * 2)).append(sets.charAt(i * 2 + 1));
            } else if (convert == 9) {
                result.append(sets.charAt(i * 2 + 2)).append(sets.charAt(i * 2));
            } else {
                result.append(String.valueOf(sets.charAt(2 * i)).repeat(convert - 5)).append(sets.charAt(i * 2 + 1));
            }

            number /= 10;
        }

        return result.reverse().toString();
    }

    public static String uniqueSubstring(String input) {
        int maxIndex = 0;
        int maxEntries = 0;
        HashMap<Character, Integer> entries = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            if (!entries.containsKey(input.charAt(i))) entries.put(input.charAt(i), 1);
            else {
                int value = entries.get(input.charAt(i)) + 1;
                if (value > maxEntries) {
                    maxEntries = value;
                    maxIndex = i;
                }
                entries.put(input.charAt(i), value);
            }
        }

        return (maxIndex % 2 == 0) ? "чет" : "нечет";
    }

    public static String[] labirint(int[][] data) {
        ArrayList<String> paths = path(data.length - 1, data.length - 1, data);
        Integer bestLength = Integer.MAX_VALUE;
        String bestPath = "";

        for (String path : paths) {
            List<Integer> visits = Arrays.stream(path.split("-")).map(Integer::valueOf).toList();
            if (visits.size() == data.length * 2 - 1) {
                int sum = 0;
                for (int i : visits) sum += i;
                if (sum < bestLength) {
                    bestLength = sum;
                    bestPath = path;
                }
            }
        }

        if (bestPath.isEmpty()) return new String[] {"Прохода нет"};
        return new String[] {bestPath, String.valueOf(bestLength)};
    }

    public static ArrayList<String> path(int x, int y, int[][] data) {
        ArrayList<String> path = new ArrayList<String>();
        if (data[y][x] < 0) return path;
        path.add(String.valueOf(data[y][x]));


        if (x != 0) {
            ArrayList<String> nextPaths = path(x - 1, y, data);
            for (String oneDirection : nextPaths) {
                path.add(String.valueOf(data[y][x] + "-" + oneDirection));
            }
        }
        if (y != 0) {
            ArrayList<String> nextPaths = path(x, y - 1, data);
            for (String oneDirection : nextPaths) {
                path.add(String.valueOf(data[y][x] + "-" + oneDirection));
            }
        }

        return path;
    }

    public static String numericOrder(String input) {
        List<String> words = Arrays.stream(input.split(" ")).toList();
        Map<Integer, String> pairs = new HashMap<>();

        for (String word : words) {
            String wordPart = "";
            String numPart = "";

            for (int i = 0; i < word.length(); i++) {
                if (Character.isDigit(word.charAt(i))) numPart += word.charAt(i);
                else wordPart += word.charAt(i);
            }

            pairs.put(Integer.valueOf(numPart), wordPart);
        }

        return String.join(" ", pairs.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey)).map(Map.Entry::getValue).toList());
    }

    public static boolean fibString(String input) {
        HashMap<Character, Integer> counter = new HashMap<>();
        for (char i : input.toCharArray()) {
            if (!counter.containsKey(i)) counter.put(i, 1);
            else counter.put(i, counter.get(i) + 1);
        }

        List<Integer> numbers = counter.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getValue)).map(Map.Entry::getValue).toList();
        if (numbers.getFirst() != 1 || numbers.get(1) != 1) return false;

        for (int i = 2; i < numbers.size(); i++)
            if (numbers.get(i) != numbers.get(i - 1) + numbers.get(i - 2)) return false;
        return true;
    }
}
