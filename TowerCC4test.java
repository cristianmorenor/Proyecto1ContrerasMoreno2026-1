package Test;
import stackingItems.Tower;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Casos de prueba comunes - Ciclo 4.
 * Creación colectiva. Cubre escenarios de integración entre tipos.
 *
 * @author David Contreras y Cristian Moreno
 * @version 4.0
 */
public class TowerCC4test {

    private Tower tower;

    @BeforeEach
    public void setUp() {
        tower = new Tower(200, 60);
    }

    @Test
    public void cc1TorreVaciaAlturaCeroYSinItems() {
        assertEquals(0, tower.height());
        assertEquals(0, tower.stackingItems().length);
    }

    @Test
    public void cc2SecuenciaNormalTazasYTapas() {
        tower.pushCup(1);
        tower.pushCup(2);
        tower.pushLid(1);
        tower.pushLid(2);
        assertTrue(tower.ok());
        assertEquals(2, tower.liddedCups().length);
        // taza1(1)+lid(1) + taza2(3)+lid(1) = 6
        assertEquals(6, tower.height());
    }

    @Test
    public void cc3OpenerEliminaTapasNormalesYCrazy() {
        tower.pushCup(1);
        tower.pushLid("normal", 1);
        tower.pushLid("crazy", 1);   // va a la base
        tower.pushCup("opener", 2);
        assertTrue(tower.ok());
        assertEquals(0, tower.liddedCups().length);
        assertFalse(tower.stackingItemsAsString().contains("crazy"));
    }

    @Test
    public void cc4HierarchicalEnFondoNoPuedeQuitarse() {
        tower.pushCup("hierarchical", 1);
        tower.removeCup(1);
        assertFalse(tower.ok());
    }

    @Test
    public void cc5FearfulNoEntraSinTazaYNoSale() {
        // No entra si la taza no está
        tower.pushLid("fearful", 5);
        assertFalse(tower.ok());

        // Entra cuando la taza está, pero no puede salir
        tower.pushCup(5);
        tower.pushLid("fearful", 5);
        assertTrue(tower.ok());
        tower.removeLid(5);
        assertFalse(tower.ok());
    }

    @Test
    public void cc6CrazyEnBaseYPuedeQuitarse() {
        tower.pushCup(1);
        tower.pushLid("crazy", 1);
        assertTrue(tower.stackingItemsAsString().startsWith("[crazy-lid-"));
        tower.popLid(1);
        assertTrue(tower.ok());
    }

    @Test
    public void cc7GreedyRobaYNoRobaFearful() {
        tower.pushCup(1);
        tower.pushLid("fearful", 1); // no removible
        tower.pushCup(2);
        tower.pushLid("normal", 2);  // removible
        tower.pushCup("greedy", 3);
        assertTrue(tower.ok());
        // Robó la tapa de taza 2, no la fearful de taza 1
        int[] lidded = tower.liddedCups();
        assertEquals(2, lidded.length);
        assertEquals(1, lidded[0]); // fearful sigue en taza 1
        assertEquals(3, lidded[1]); // greedy tiene la tapa de taza 2
    }

    @Test
    public void cc8CombinacionHierarchicalOpenerGreedy() {
        tower.pushCup("hierarchical", 3);
        tower.pushCup(1);
        tower.pushLid("normal", 1);
        tower.pushCup("greedy", 2);  // roba tapa de taza 1
        tower.pushCup("opener", 4);  // elimina todas las tapas
        assertTrue(tower.ok());
        assertEquals(0, tower.liddedCups().length);
    }
}
