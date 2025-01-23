import java.util.Scanner;

public class PrimeNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the upper limit
        int limit = scanner.nextInt();

        // Create an array of boolean values of all values from 2 to upper limit
        boolean[] isPrime = new boolean[limit + 1];
        // Assume all numbers are prime initially, set them accordingly
        for (int i = 2; i <= limit; i++) {
            isPrime[i] = true;
        }

        // Mark non-prime numbers
        // Check all numbers between 2 and the square root of our limit
        for (int i = 2; i <= Math.sqrt(limit); i++) {
            if (isPrime[i]) {
                // Mark all multiples of i as non-prime
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // initialise counter variable
        int cnt = 0;

        // iterate through all values in primes boolean array and if it is a prime add to count
        for (int i = 0; i < isPrime.length; i++) {
            if (isPrime[i]) {
                cnt++;
            }
        }

        // Print the results
        System.out.println(cnt);
    }
}

/*
// This is not fast enough for einstein's timeout function

public class PrimeNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the upper limit
        long limit = scanner.nextInt();

        // Counter of prime number
        long cnt = 0;

        for (int i = 1; i < limit; i++) {
            if (isPrime(i)) {
                cnt += 1;
            }
        }

        // Print the results
        System.out.println(cnt);
    }

    // Function to check if it is prime
    private static boolean isPrime(long num) {
        if (num == 1)
        {
            return false;
        }

        for (int i = 2;i < (num / 2) + 1; i++)
        {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
    */