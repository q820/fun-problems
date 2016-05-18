package primeTable;

import java.io.PrintStream;
import java.util.Arrays;

public class PrimeTableSolver {

    public static void main(String[] args) {

        final int N;
        if (args.length != 1) {
            N = 10;
        } else {
            // assuming good input.
            N = Integer.parseInt(args[0]);
        }

        if (N > 0) {
            if (N <= 100628275) {
                final PrimeTableSolver solver = new PrimeTableSolver();
                final int[][] table = solver.solve(N);
                solver.print(table, System.out);
            } else {
                throw new IllegalArgumentException(
                        "Current implementation does not support input larger than 100628275, " +
                        "because this is the biggest number for which its sieve upper bound is smaller than MAX_INT");
            }
        }
    }

    int[][] solve(int n) {
        // Find first N primes.
        final int[] primes = findFirstNPrimes(n);

        // Fill up the matrix.
        final int[][] table = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            table[i][0] = table[0][i] = primes[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                table[i][j] = table[j][i] = primes[i - 1] * primes[j - 1];
            }
        }

        return table;
    }

    /**
     * This method uses Eratosthenes' sieve to find the first prime N numbers.
     */
    int[] findFirstNPrimes(int n) {
        final int upperBound = findUpperBoundForNthPrime(n);

        // sieve[i] == true iff i is prime.
        final boolean[] sieve = new boolean[upperBound];

        // assume all are prime.
        Arrays.fill(sieve, true);
        sieve[0] = false;
        sieve[1] = false;

        // Start filtering out non-prime numbers.
        // Special case for 2.
        fillUpSieve(sieve, 2);
        // All other odd numbers:
        for (int i = 3; i <= upperBound/2; i += 2) {
            fillUpSieve(sieve, i);
        }

        final int[] primes = new int[n];
        int counter = 0;
        for (int i = 0; i < upperBound && counter < n; i++) {
            if (sieve[i]) {
                primes[counter++] = i;
            }
        }

        return primes;
    }

    /**
     * This method will leave sieve[i] untouched and set to false all of i's multiples.
     */
    void fillUpSieve(boolean[] sieve, int i) {
        if (i > 0) {
            for (int m = i + i; m < sieve.length; m += i) {
                sieve[m] = false;
            }
        }
    }

    /**
     * We need an upper bound for the sieve; given N, we can find that upper bound:
     * https://en.wikipedia.org/wiki/Prime_number_theorem
     * @return an integer larger than the nth prime number (but not too large)
     */
    int findUpperBoundForNthPrime(int n) {
        return Math.max(6, (int) ((double) n * (Math.log(n) + Math.log(Math.log(n))))) + 1;
    }

    private void print(int[][] table, PrintStream out) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                out.print(table[i][j] + "\t");
            }
            out.println();
        }
    }
}
