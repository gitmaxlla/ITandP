// https://github.com/gitmaxlla/ITandP — Github-репозиторий с работами
public class FirstTen {
    public static void main(String[] args) {
        System.out.println("convert");
        System.out.println(convert(5));
        System.out.println(convert(3));
        System.out.println(convert(8));
        System.out.println(" ");
        System.out.println("fitCalc");
        System.out.println(fitCalc(15, 1));
        System.out.println(fitCalc(24, 2));
        System.out.println(fitCalc(41, 3));
        System.out.println(" ");
        System.out.println("containers");
        System.out.println(containers(3, 4, 2));
        System.out.println(containers(5, 0, 2));
        System.out.println(containers(4, 1, 4));
        System.out.println(" ");
        System.out.println("triangleType");
        System.out.println(triangleType(5, 5, 5));
        System.out.println(triangleType(5, 4, 5));
        System.out.println(triangleType(3, 4, 5));
        System.out.println(triangleType(5, 1, 1));
        System.out.println(" ");
        System.out.println("ternaryEvaluation");
        System.out.println(ternaryEvaluation(8,4));
        System.out.println(ternaryEvaluation(1,11));
        System.out.println(ternaryEvaluation(5,9));
        System.out.println(" ");
        System.out.println("howManyItems");
        System.out.println(howManyItems(22,1.4f, 2));
        System.out.println(howManyItems(45,1.8f, 1.9f));
        System.out.println(howManyItems(100,2, 2));
        System.out.println(" ");
        System.out.println("factorial");
        System.out.println(factorial(3));
        System.out.println(factorial(5));
        System.out.println(factorial(7));
        System.out.println(" ");
        System.out.println("gcd");
        System.out.println(gcd(48, 18));
        System.out.println(gcd(52, 8));
        System.out.println(gcd(259, 28));
        System.out.println(" ");
        System.out.println("ticketSaler");
        System.out.println(ticketSaler(70, 1500));
        System.out.println(ticketSaler(24, 950));
        System.out.println(ticketSaler(53, 1250));
        System.out.println(" ");
        System.out.println("tables");
        System.out.println(tables(5, 2));
        System.out.println(tables(31, 20));
        System.out.println(tables(123, 58));
    }

    public static int ticketSaler(int tickets, int price) {
        return (int)(tickets * price * (75600f / (70 * 1500)));
    }

    public static int howManyItems(int n, float w, float h) {
        n /= 2;
        return (int)(n / (w * h));
    }


    public static float convert(int gallons) {
        return gallons * 3.785412f;
    }

    public static int fitCalc(int minutes, int intensity) {
        return minutes * intensity;
    }

    public static int containers(int boxes, int bags, int barrels) {
        return boxes*20 + bags*50 + barrels*100;
    }

    public static String triangleType(int x, int y, int z) {
        if ((x == y) && (y == z)) {
            return "isosceles";
        }
        else if (x + y > z && y + z > x && x + z > y) {
            if (x == y || y == z || x == z) {
                return "equilateral";
            }
            else {
                return "different-sided";
            }
        }
        else
        {
            return "not a triangle";
        }
    }

    public static int ternaryEvaluation(int a, int b) {
        return a > b ? a : b;
    }

    public static int factorial(int num) {
        if (num == 1) {
            return 1;
        }

        return num * factorial(num - 1);
    }

    public static int gcd(int a, int b) {
        while (a != b) {
            if (a > b) {
                a -= b;
            }
            else
            {
                b -= a;
            }
        }

        return a;
    }

    public static int tables(int students, int tables) {
        int seats_lacking = students - (tables * 2);
        return Math.max(0, seats_lacking / 2 + seats_lacking % 2);
    }
}