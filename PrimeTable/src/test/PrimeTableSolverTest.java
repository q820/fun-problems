import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PrimeTableSolverTest {

    private PrimeTableSolver mSolver;

    @Before
    public void before() {
        mSolver = new PrimeTableSolver();
    }

    @Test
    public void testFindUpperBoundForNthPrime() {
        // Used https://primes.utm.edu/nthprime/index.php to find the exact nth prime for these test cases.
        assertTrue(mSolver.findUpperBoundForNthPrime(1) >= 2);
        assertTrue(mSolver.findUpperBoundForNthPrime(2) >= 3);
        assertTrue(mSolver.findUpperBoundForNthPrime(3) >= 5);
        assertTrue(mSolver.findUpperBoundForNthPrime(4) >= 7);
        assertTrue(mSolver.findUpperBoundForNthPrime(5) >= 11);
        assertTrue(mSolver.findUpperBoundForNthPrime(10) >= 29);
        assertTrue(mSolver.findUpperBoundForNthPrime(100) >= 541);
        assertTrue(mSolver.findUpperBoundForNthPrime(1000) >= 7919);
        assertTrue(mSolver.findUpperBoundForNthPrime(10000) >= 104729);
        assertTrue(mSolver.findUpperBoundForNthPrime(100000) >= 1299709);
        assertTrue(mSolver.findUpperBoundForNthPrime(1000000) >= 15485863);
        assertTrue(mSolver.findUpperBoundForNthPrime(10000000) >= 179424673);
        assertTrue(mSolver.findUpperBoundForNthPrime(100000000) >= 2038074743);
        assertEquals(2147483642, mSolver.findUpperBoundForNthPrime(100628275));
    }

    @Test
    public void testFillUpSieve() {
        boolean[] sieve = new boolean[100];
        int x = 7;
        sieve[x] = true;

        // test that multiples of 7 are not primes anymore.
        mSolver.fillUpSieve(sieve, x);
        assertTrue(sieve[x]);
        for (int i = 2 * x; i < sieve.length; i += x) {
            assertFalse(sieve[i]);
        }

        // test that filling up the sieve with 0 does nothing
        final int initialHash = Arrays.hashCode(sieve);
        mSolver.fillUpSieve(sieve, 0);
        assertEquals(initialHash, Arrays.hashCode(sieve));
    }

    @Test
    public void testFindFirstNPrimes() {
        final int[] primes = mSolver.findFirstNPrimes(10);

        assertEquals(10, primes.length);
        assertEquals(2, primes[0]);
        assertEquals(3, primes[1]);
        assertEquals(5, primes[2]);
        assertEquals(7, primes[3]);
        assertEquals(11, primes[4]);
        assertEquals(13, primes[5]);
        assertEquals(17, primes[6]);
        assertEquals(19, primes[7]);
        assertEquals(23, primes[8]);
        assertEquals(29, primes[9]);
    }

    @Test
    public void testSolve() {
        final int[][] expectedTable = {{0, 2, 3, 5}, {2, 4, 6, 10}, {3, 6, 9, 15}, {5, 10, 15, 25}};
        assertTrue(Arrays.deepEquals(expectedTable, mSolver.solve((3))));
    }
}