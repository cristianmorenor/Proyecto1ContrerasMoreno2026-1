package stackingItems;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias del Ciclo 1 — solo casos esenciales.
 * Todas en modo invisible.
 *
 * @David Contreras Y Cristian Moreno
 * @version 1.0
 */
public class TowerC1Test {

    private Tower tower;

    @Before
    public void setUp() {
        tower = new Tower(200, 20);
    }

    // ---- Tazas ---- //

    @Test
    public void shouldAddCup() {
        tower.pushCup(1);
        assertTrue(tower.ok());
        assertEquals(1, tower.height());
    }

    @Test
    public void shouldNotAddDuplicateCup() {
        tower.pushCup(1);
        tower.pushCup(1);
        assertFalse(tower.ok());
    }

    @Test
    public void shouldNotAddCupExceedingHeight() {
        Tower t = new Tower(200, 2);
        t.pushCup(1); // h=1, total=1
        t.pushCup(2); // h=3, total=4 > 2
        assertFalse(t.ok());
    }

    @Test
    public void shouldRemoveCup() {
        tower.pushCup(1);
        tower.removeCup(1);
        assertTrue(tower.ok());
        assertEquals(0, tower.height());
    }

    // ---- Tapas ---- //

    @Test
    public void shouldAddLid() {
        tower.pushCup(1);
        tower.pushLid(1);
        assertTrue(tower.ok());
        assertEquals(2, tower.height());
    }

    @Test
    public void shouldNotAddLidTwice() {
        tower.pushCup(1);
        tower.pushLid(1);
        tower.pushLid(1);
        assertFalse(tower.ok());
    }

    @Test
    public void shouldRemoveLidAndKeepCup() {
        tower.pushCup(1);
        tower.pushLid(1);
        tower.removeLid(1);
        assertTrue(tower.ok());
        assertEquals(1, tower.height());
    }

    // ---- Consultas ---- //

    @Test
    public void shouldReturnCorrectHeight() {
        tower.pushCup(1); // 1cm
        tower.pushCup(2); // 3cm
        tower.pushLid(1); // 1cm
        assertEquals(5, tower.height());
    }

    @Test
    public void shouldReturnLiddedCupsSorted() {
        tower.pushCup(1);
        tower.pushCup(2);
        tower.pushLid(2);
        tower.pushLid(1);
        int[] lidded = tower.liddedCups();
        assertEquals(1, lidded[0]);
        assertEquals(2, lidded[1]);
    }

    @Test
    public void shouldReturnStackingItemsBottomToTop() {
        tower.pushCup(2);
        tower.pushCup(1);
        tower.pushLid(1);
        String[][] items = tower.stackingItems();
        assertEquals("cup", items[0][0]); assertEquals("2", items[0][1]);
        assertEquals("cup", items[1][0]); assertEquals("1", items[1][1]);
        assertEquals("lid", items[2][0]); assertEquals("1", items[2][1]);
    }

    // ---- Reorganización ---- //

    @Test
    public void shouldOrderDescending() {
        tower.pushCup(1);
        tower.pushCup(3);
        tower.pushCup(2);
        tower.orderTower();
        String[][] items = tower.stackingItems();
        assertEquals("3", items[0][1]);
        assertEquals("1", items[items.length - 1][1]);
    }

    @Test
    public void shouldReverse() {
        tower.pushCup(1);
        tower.pushCup(2);
        tower.reverseTower();
        String[][] items = tower.stackingItems();
        assertEquals("2", items[0][1]);
        assertEquals("1", items[1][1]);
    }
}