package Test;
import Contest.TowerContest;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas de casos comunes (CTest) del Ciclo 3.
 *
 * @author David Contreras y Cristian Moreno
 * @version 1.0
 */
public class TowerContestCTest {

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

  
    //  Muestras del enunciado                                            
    @Test
    public void marathonSample1() {
        String r = TowerContest.solve(4, 9);
        assertNotEquals("impossible", r);
        assertTrue(isValidResult(4, r));
    }

    @Test
    public void marathonSample2() {
        assertEquals("impossible", TowerContest.solve(4, 100));
    }

   
    //  Frontera inferior                                                
    @Test
    public void singleCupH1() {
        assertEquals("1", TowerContest.solve(1, 1));
    }

    @Test
    public void singleCupH2Impossible() {
        assertEquals("impossible", TowerContest.solve(1, 2));
    }

    @Test
    public void twoCupsH3Solvable() {
        assertTrue(isValidResult(2, TowerContest.solve(2, 3)));
    }

    @Test
    public void twoCupsH2Impossible() {
        assertEquals("impossible", TowerContest.solve(2, 2));
    }

    @Test
    public void twoCupsH4Impossible() {
        assertEquals("impossible", TowerContest.solve(2, 4));
    }

    //  Unico imposible dentro del rango para n >= 4                      //
    @Test
    public void missingValueN4() {
        assertEquals("impossible", TowerContest.solve(4, 8));
    }

    @Test
    public void missingValueN5() {
        assertEquals("impossible", TowerContest.solve(5, 15));
    }

    @Test
    public void missingValueN6() {
        assertEquals("impossible", TowerContest.solve(6, 24));
    }

    @Test
    public void missingValueN7() {
        assertEquals("impossible", TowerContest.solve(7, 35));
    }

   
    //  Altura minima y maxima                                           
    @Test
    public void minHeightN3() {
        assertTrue(isValidResult(3, TowerContest.solve(3, 5)));
    }

    @Test
    public void minHeightN4() {
        assertTrue(isValidResult(4, TowerContest.solve(4, 7)));
    }

    @Test
    public void minHeightN5() {
        assertTrue(isValidResult(5, TowerContest.solve(5, 9)));
    }

    @Test
    public void maxHeightN4() {
        assertTrue(isValidResult(4, TowerContest.solve(4, 10)));
    }

    @Test
    public void maxHeightN5() {
        assertTrue(isValidResult(5, TowerContest.solve(5, 17)));
    }

    @Test
    public void maxHeightN6() {
        // max para n=6: (5)^2+1 = 26
        assertTrue(isValidResult(6, TowerContest.solve(6, 26)));
    }


    //  Imposibles fuera de rango                                         
    @Test
    public void belowMinN5() {
        assertEquals("impossible", TowerContest.solve(5, 8));
    }

    @Test
    public void aboveMaxN4() {
        assertEquals("impossible", TowerContest.solve(4, 11));
    }

    
    //  Todos los alcanzables para n=4 y n=5                         
    @Test
    public void allAchievableN4() {
        assertTrue(isValidResult(4, TowerContest.solve(4,  7)));
        assertTrue(isValidResult(4, TowerContest.solve(4,  9)));
        assertTrue(isValidResult(4, TowerContest.solve(4, 10)));
        assertEquals("impossible",  TowerContest.solve(4,  8));
    }

    @Test
    public void allAchievableN5() {
        assertTrue(isValidResult(5, TowerContest.solve(5,  9)));
        assertTrue(isValidResult(5, TowerContest.solve(5, 10)));
        assertTrue(isValidResult(5, TowerContest.solve(5, 11)));
        assertTrue(isValidResult(5, TowerContest.solve(5, 12)));
        assertTrue(isValidResult(5, TowerContest.solve(5, 13)));
        assertTrue(isValidResult(5, TowerContest.solve(5, 14)));
        assertTrue(isValidResult(5, TowerContest.solve(5, 16)));
        assertTrue(isValidResult(5, TowerContest.solve(5, 17)));
        assertEquals("impossible",  TowerContest.solve(5, 15));
    }
}