public class Primes {
    public static void main(String[] args) {
        for (int i = 2; i <= 100; i++) {
            if (isPrime(i))
            {
                System.out.print(String.valueOf(i) + ' ');
            }
        }
    }

    public static boolean isPrime(int n) {
        if(n == 2) {
            return true;
        }

        for (int i = 2; i <= n / 2 + 1; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
