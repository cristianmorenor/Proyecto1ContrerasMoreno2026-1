package stackingItems;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias del Ciclo 3.
 * Cubren los metodos solve y simulate de TowerContest.
 *
 * @author David Contreras y Cristian Moreno
 * @version 1.0
 */
public class TowerContestTest {

    // ------------------------------------------------------------------ //
    //  Helper                                                             //
    // ------------------------------------------------------------------ //

    /**
     * Verifica que el resultado de solve sea una permutacion valida:
     * exactamente n alturas impares distintas entre 1 y 2n-1.
     */
    private boolean isValidResult(int n, String result) {
        if ("impossible".equals(result)) return false;
        boolean[] seen = new boolean[n + 1];
        int pos   = 0;
        int start = 0;
        for (int i = 0; i <= result.length(); i++) {
            if (i == result.length() || result.charAt(i) == ' ') {
                int hi  = Integer.parseInt(result.substring(start, i));
                int cup = (hi + 1) / 2;
                if (cup < 1 || cup > n) return false;
                if (seen[cup])          return false;
                seen[cup] = true;
                pos++;
                start = i + 1;
            }
        }
        return pos == n;
    }

    // ------------------------------------------------------------------ //
    //  REQ 14 — ejemplos del enunciado de la maraton                     //
    // ------------------------------------------------------------------ //

    @Test
    public void shouldSolveSampleOne() {
        String result = TowerContest.solve(4, 9);
        assertNotEquals("impossible", result);
        assertTrue(isValidResult(4, result));
    }

    @Test
    public void shouldReturnImpossibleForSampleTwo() {
        assertEquals("impossible", TowerContest.solve(4, 100));
    }

    // ------------------------------------------------------------------ //
    //  REQ 14 — n = 1                                                    //
    // ------------------------------------------------------------------ //

    @Test
    public void shouldSolveNEquals1() {
        assertEquals("1", TowerContest.solve(1, 1));
    }

    @Test
    public void shouldReturnImpossibleNEquals1WrongH() {
        assertEquals("impossible", TowerContest.solve(1, 2));
    }

    // ------------------------------------------------------------------ //
    //  REQ 14 — altura minima                                            //
    // ------------------------------------------------------------------ //

    @Test
    public void shouldSolveMinHeightN4() {
        String result = TowerContest.solve(4, 7);
        assertNotEquals("impossible", result);
        assertTrue(isValidResult(4, result));
    }

    @Test
    public void shouldSolveMinHeightN5() {
        String result = TowerContest.solve(5, 9);
        assertNotEquals("impossible", result);
        assertTrue(isValidResult(5, result));
    }

    // ------------------------------------------------------------------ //
    //  REQ 14 — altura maxima                                            //
    // ------------------------------------------------------------------ //

    @Test
    public void shouldSolveMaxHeightN4() {
        // max para n=4: (3)^2+1 = 10
        String result = TowerContest.solve(4, 10);
        assertNotEquals("impossible", result);
        assertTrue(isValidResult(4, result));
    }

    @Test
    public void shouldSolveMaxHeightN5() {
        // max para n=5: (4)^2+1 = 17
        String result = TowerContest.solve(5, 17);
        assertNotEquals("impossible", result);
        assertTrue(isValidResult(5, result));
    }

    // ------------------------------------------------------------------ //
    //  REQ 14 — casos imposibles                                         //
    // ------------------------------------------------------------------ //

    @Test
    public void shouldReturnImpossibleBelowMin() {
        assertEquals("impossible", TowerContest.solve(5, 8));
    }

    @Test
    public void shouldReturnImpossibleAboveMax() {
        assertEquals("impossible", TowerContest.solve(4, 11));
    }

    @Test
    public void shouldReturnImpossibleMissingN4() {
        // unico imposible dentro del rango: (3)^2-1 = 8
        assertEquals("impossible", TowerContest.solve(4, 8));
    }

    @Test
    public void shouldReturnImpossibleMissingN5() {
        // unico imposible dentro del rango: (4)^2-1 = 15
        assertEquals("impossible", TowerContest.solve(5, 15));
    }

    @Test
    public void shouldReturnImpossibleMissingN6() {
        // unico imposible dentro del rango: (5)^2-1 = 24
        assertEquals("impossible", TowerContest.solve(6, 24));
    }

    @Test
    public void shouldReturnImpossibleZeroHeight() {
        assertEquals("impossible", TowerContest.solve(3, 0));
    }

    // ------------------------------------------------------------------ //
    //  REQ 14 — alturas intermedias n=5                                  //
    // ------------------------------------------------------------------ //

    @Test
    public void shouldSolveH10N5() {
        String result = TowerContest.solve(5, 10);
        assertNotEquals("impossible", result);
        assertTrue(isValidResult(5, result));
    }

    @Test
    public void shouldSolveH13N5() {
        String result = TowerContest.solve(5, 13);
        assertNotEquals("impossible", result);
        assertTrue(isValidResult(5, result));
    }

    @Test
    public void shouldSolveH16N5() {
        String result = TowerContest.solve(5, 16);
        assertNotEquals("impossible", result);
        assertTrue(isValidResult(5, result));
    }

    // ------------------------------------------------------------------ //
    //  REQ 14 — h mayor que el maximo posible                            //
    // ------------------------------------------------------------------ //

    @Test
    public void shouldReturnImpossibleLargeH() {
        assertEquals("impossible", TowerContest.solve(4, 999));
    }

    @Test
    public void shouldSolveMaxHeightN10() {
        // max para n=10: (9)^2+1 = 82
        String result = TowerContest.solve(10, 82);
        assertNotEquals("impossible", result);
        assertTrue(isValidResult(10, result));
    }
}