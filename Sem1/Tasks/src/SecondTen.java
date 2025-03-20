// https://github.com/gitmaxlla/ITandP — Github-репозиторий с работами
import java.util.Arrays;
public class SecondTen {
    public static void main(String[] args) {
        System.out.println(duplicateChars("Barack", "Obama"));
        System.out.println();

        System.out.println(dividedByThree(new int[] {3, 12, 7, 81, 52}));
        System.out.println();

        System.out.println(getInitials("simonov sergei evgenievich"));
        System.out.println(getInitials("kozhevnikova tatiana vitalevna"));
        System.out.println();

        System.out.println(Arrays.toString(normalizator(new double[] {3.5, 7.0, 1.5, 9.0, 5.5})));
        System.out.println();

        System.out.println(Arrays.toString(normalizator(new double[] {10.0, 10.0, 10.0, 10.0})));
        System.out.println();

        System.out.println(Arrays.toString(compressedNums(new double[] {1.6, 0, 212.3, 34.8, 0, 27.5})));
        System.out.println();

        System.out.println(camelToSnake("helloWorld"));
        System.out.println();

        System.out.println(camelToSnake("helloWorld"));
        System.out.println();

        System.out.println(secondBiggest(new int[] {3, 5, 8, 1, 2, 4}));
        System.out.println();

        System.out.println(localReverse("baobaq", 'b'));
        System.out.println(localReverse("Hello, I'm under the water, please help me", 'e'));
        System.out.println();

        System.out.println(equal(8, 1, 8));
        System.out.println(equal(5, 5, 5));
        System.out.println(equal(4, 9, 6));
        System.out.println();

        System.out.println(isAnagram("LISTEN", "silent"));
        System.out.println(isAnagram("Eleven plus two?", "Twelve plus one!"));
        System.out.println(isAnagram("hello", "world"));
        System.out.println();
    }

    public static String duplicateChars(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();

        StringBuilder result = new StringBuilder();
        StringBuilder a_unique = new StringBuilder();

        for (int i = 0; i < a.length(); i++) {
            boolean sameCharAhead = false;

            for (int j = i + 1; j < a.length(); j++) {
                if (a.charAt(i) == a.charAt(j)) {
                    sameCharAhead = true;
                    break;
                }
            }

            if (!sameCharAhead) a_unique.append(a.charAt(i));
        }

        a = a_unique.toString();

        for (int i = 0; i < a.length(); i++) {
            boolean letterDuplicate = false;

            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    letterDuplicate = true;
                    break;
                }
            }

            if (!letterDuplicate) {
                result.append(a.charAt(i));
            }
        }

        return result.toString();
    }

    public static int dividedByThree(int[] numbers) {
        int count = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 1 && numbers[i] % 3 == 0) {
                count++;
            }
        }

        return count;
    }

    public static String getInitials(String fullName) {
        String[] fNSplit = fullName.toUpperCase().split(" ");
        return fNSplit[1].charAt(0) + ". " + fNSplit[2].charAt(0) + ". " + fNSplit[0].charAt(0) + fNSplit[0].substring(1).toLowerCase();
    }

    public static double[] normalizator(double[] array) {
        double min = array[0];
        double max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) min = array[i];
            if (array[i] > max) max = array[i];
        }

        for (int i = 0; i < array.length; i++) {
            if (max == min) {
                array[i] = 0.0;
                continue;
            }

            array[i] = (array[i] - min) / (max - min);
        }

        return array;
    }

    public static int[] compressedNums(double[] array) {
        int nonZero = 0;
        for (int i = 0; i < array.length; i++) if (array[i] != 0) nonZero++;
        int[] result = new int[nonZero];

        int counter = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                result[++counter] = (int) array[i];
            }
        }

        while (true) {
            boolean yetUnsorted = false;

            for (int i = 0; i < nonZero - 1; i++) {
                if (result[i] > result[i + 1]) {
                    int temp = result[i];
                    result[i] = result[i + 1];
                    result[i + 1] = temp;

                    yetUnsorted = true;
                }
            }

            if (!yetUnsorted) break;
        }

        return result;
    }

    public static String camelToSnake(String text) {
        String discriminator = text.toLowerCase();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            if(discriminator.charAt(i) != text.charAt(i)) result.append('_');
            result.append(discriminator.charAt(i));
        }

        return result.toString();
    }

    public static int secondBiggest(int[] array) {
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) max = array[i];
        }

        int second_max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > second_max && array[i] < max) second_max = array[i];
        }

        return second_max;
    }

    public static String localReverse(String original, char marker) {
        String result = "";

        for (int i = 0; i < original.length(); i++) {
            result += original.charAt(i);

            if (original.charAt(i) == marker && i != original.length() - 1) {
                int clauseLength = 1;
                while (clauseLength < original.length() - i && original.charAt(i + clauseLength) != marker) {
                    ++clauseLength;
                }

                if (i + clauseLength == original.length()) continue;

                String toReverse = original.substring(i + 1, i + clauseLength);
                String reversed = "";

                for (int j = toReverse.length() - 1; j >= 0; j--) {
                    reversed += toReverse.charAt(j);
                }

                result += reversed;
                i += clauseLength - 1;
            }
        }

        return result;
    }

    public static int equal(int a, int b, int c) {
        int discriminator = (a == b ? 1 : 0) + (b == c ? 1 : 0) + (a == c ? 1 : 0);
        if (discriminator == 1) discriminator = 2;
        return discriminator;
    }

    public static boolean isAnagram(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        boolean result = false;

        for (int i = 0; i < a.length(); i++) {
            if (!Character.isAlphabetic(a.charAt(i))) continue;

            result = false;

            for (int j = 0; j < b.length(); j++) {
                if (b.charAt(j) == a.charAt(i)) {
                    b = b.substring(0, j) + ' ' + b.substring(j+1, b.length());
                    result = true;
                    break;
                }
            }

            if (!result) {
                return false;
            }
        }

        return true;
    }

}