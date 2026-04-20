package stackingitems;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias del ciclo 4.
 * Cubre los métodos nuevos: tipos de tazas (opener, hierarchical, greedy)
 * y tipos de tapas (fearful, crazy).
 *
 * @author David Contreras y Cristian Moreno
 * @version 4.0
 */
public class TowerC4test {

    private Tower tower;

    @BeforeEach
    public void setUp() {
        tower = new Tower(200, 50);
    }

    // --- NormalCup ---

    @Test
    public void testPushCupNormalAgregaCorrectamente() {
        tower.pushCup(1);
        assertTrue(tower.ok());
        assertEquals(1, tower.height());
    }

    @Test
    public void testPushCupDuplicadaFalla() {
        tower.pushCup(1);
        tower.pushCup(1);
        assertFalse(tower.ok());
    }

    // --- OpenerCup ---

    @Test
    public void testOpenerEliminaTodasLasTapas() {
        tower.pushCup(1);
        tower.pushCup(2);
        tower.pushLid(1);
        tower.pushLid(2);
        tower.pushCup("opener", 3);
        assertTrue(tower.ok());
        assertEquals(0, tower.liddedCups().length);
    }

    // --- HierarchicalCup ---

    @Test
    public void testHierarchicalDesplazaTazasMenores() {
        tower.pushCup(1);
        tower.pushCup("hierarchical", 3);
        assertTrue(tower.ok());
        String items = tower.stackingItemsAsString();
        assertTrue(items.indexOf("hierarchical-cup-3") < items.indexOf("cup-1"));
    }

    @Test
    public void testHierarchicalEnFondoNoSePuedeQuitar() {
        tower.pushCup("hierarchical", 1);
        tower.removeCup(1);
        assertFalse(tower.ok());
    }

    // --- GreedyCup ---

    @Test
    public void testGreedyRobaTapaDisponible() {
        tower.pushCup(1);
        tower.pushLid(1);
        tower.pushCup("greedy", 2);
        assertTrue(tower.ok());
        assertEquals(1, tower.liddedCups().length);
        assertEquals(2, tower.liddedCups()[0]); // la tapa pasó a la greedy
    }

    @Test
    public void testGreedyNoRobaFearful() {
        tower.pushCup(1);
        tower.pushLid("fearful", 1);
        tower.pushCup("greedy", 2);
        assertTrue(tower.ok());
        assertEquals(1, tower.liddedCups()[0]); // sigue en taza 1
    }

    // --- FearfulLid ---

    @Test
    public void testFearfulNoPuedeEntrarSinSuTaza() {
        tower.pushLid("fearful", 3);
        assertFalse(tower.ok());
    }

    @Test
    public void testFearfulNoPuedeSalirMientrasTapa() {
        tower.pushCup(1);
        tower.pushLid("fearful", 1);
        tower.popLid(1);
        assertFalse(tower.ok());
    }

    // --- CrazyLid ---

    @Test
    public void testCrazyApareceEnLaBase() {
        tower.pushCup(1);
        tower.pushLid("crazy", 1);
        assertTrue(tower.ok());
        assertTrue(tower.stackingItemsAsString().startsWith("[crazy-lid-"));
    }

    @Test
    public void testCrazyPuedeQuitarse() {
        tower.pushCup(1);
        tower.pushLid("crazy", 1);
        tower.popLid(1);
        assertTrue(tower.ok());
    }

    // --- Consultas ---

    @Test
    public void testHeightContabilizaTazasYTapas() {
        tower.pushCup(1); // altura 1
        tower.pushCup(2); // altura 3
        tower.pushLid(1); // +1
        assertEquals(5, tower.height());
    }

    @Test
    public void testLiddedCupsOrdenado() {
        tower.pushCup(1);
        tower.pushCup(2);
        tower.pushCup(3);
        tower.pushLid(3);
        tower.pushLid(1);
        int[] lidded = tower.liddedCups();
        assertEquals(2, lidded.length);
        assertEquals(1, lidded[0]);
        assertEquals(3, lidded[1]);
    }

    @Test
    public void testCoverTapaTodas() {
        tower.pushCup(1);
        tower.pushCup(2);
        tower.cover();
        assertTrue(tower.ok());
        assertEquals(2, tower.liddedCups().length);
    }
}