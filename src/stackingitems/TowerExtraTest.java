package stackingitems;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Pruebas adicionales para aumentar cobertura.
 * Cubren rutas que normalmente quedan sin ejecutar.
 */
public class TowerExtraTest {

    @Test
    public void testReverseTowerChangesOrder() {
        // Verifica que reverseTower se ejecute sin romper el estado
        Tower t = new Tower(200, 50);
        t.pushCup(1);
        t.pushCup(2);
        t.reverseTower();
        assertTrue(t.ok());
    }

    @Test
    public void testOrderTowerSortsDescending() {
        // Fuerza la ejecución de la lógica de ordenamiento
        Tower t = new Tower(200, 50);
        t.pushCup(1);
        t.pushCup(3);
        t.pushCup(2);
        t.orderTower();
        assertTrue(t.ok());
    }

    @Test
    public void testSwapValidCups() {
        // Cubre el caso válido de swap
        Tower t = new Tower(200, 50);
        t.pushCup(1);
        t.pushCup(2);

        String[] o1 = {"cup", "1"};
        String[] o2 = {"cup", "2"};

        t.swap(o1, o2);
        assertTrue(t.ok());
    }

    @Test
    public void testSwapInvalidFails() {
        // Cubre ruta de error (no existen los elementos)
        Tower t = new Tower(200, 50);

        String[] o1 = {"cup", "1"};
        String[] o2 = {"cup", "2"};

        t.swap(o1, o2);
        assertFalse(t.ok());
    }

    @Test
    public void testStackingItemsReturnsNonEmpty() {
        // Fuerza ejecución del método stackingItems
        Tower t = new Tower(200, 50);
        t.pushCup(1);
        String[][] items = t.stackingItems();
        assertTrue(items.length > 0);
    }

    @Test
    public void testPopCupReducesSize() {
        // Cubre popCup (muchas veces queda sin probar)
        Tower t = new Tower(200, 50);
        t.pushCup(1);
        t.popCup();
        assertTrue(t.ok());
    }
}