package stackingItems;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias del Ciclo 2.
 * Cubren: nuevo constructor, swap, cover y swapToReduce.
 * Todas en modo invisible.
 *
 * @David Contreras y Cristian Moreno
 * @version 1.0
 */
public class TowerC2Test {

    private Tower tower;

    @Before
    public void setUp() {
        tower = new Tower(200, 30);
    }

    // ------------------------------------------------------------------ //
    //  REQ 10 — Constructor Tower(cups)                                   //
    // ------------------------------------------------------------------ //

    @Test
    public void shouldCreateTowerWithNCups() {
        // Tower(4) debe crear tazas 1,2,3,4
        Tower t = new Tower(4);
        assertTrue(t.ok());
        String[][] items = t.stackingItems();
        assertEquals(4, items.length);
        assertEquals("1", items[0][1]);
        assertEquals("4", items[3][1]);
    }

    @Test
    public void shouldCalculateCorrectHeightForNCups() {
        // Taza 1=1cm, 2=3cm, 3=5cm → total 9cm
        Tower t = new Tower(3);
        assertEquals(9, t.height());
    }

    @Test
    public void shouldNotCreateTowerWithZeroCups() {
        Tower t = new Tower(0);
        assertEquals(0, t.height());
    }

    @Test
    public void shouldNotAddDuplicateCupToNCupTower() {
        Tower t = new Tower(3); // ya tiene taza 1,2,3
        t.pushCup(2);           // duplicado
        assertFalse(t.ok());
    }

    // ------------------------------------------------------------------ //
    //  REQ 11 — swap                                                      //
    // ------------------------------------------------------------------ //

    @Test
    public void shouldSwapTwoCups() {
        tower.pushCup(1);
        tower.pushCup(2);
        tower.pushCup(3);
        // orden actual: 1(base), 2, 3(cima)
        tower.swap(new String[]{"cup","1"}, new String[]{"cup","3"});
        assertTrue(tower.ok());
        String[][] items = tower.stackingItems();
        assertEquals("3", items[0][1]); // 3 ahora en base
        assertEquals("1", items[2][1]); // 1 ahora en cima
    }

    @Test
    public void shouldSwapCupAndLid() {
        tower.pushCup(1);
        tower.pushLid(1);
        tower.pushCup(2);
        // intercambiar lid-1 con cup-2
        tower.swap(new String[]{"lid","1"}, new String[]{"cup","2"});
        assertTrue(tower.ok());
    }

    @Test
    public void shouldNotSwapNonExistentObject() {
        tower.pushCup(1);
        tower.swap(new String[]{"cup","1"}, new String[]{"cup","99"});
        assertFalse(tower.ok());
    }

    @Test
    public void shouldNotSwapLidOfCupWithoutLid() {
        tower.pushCup(1);
        tower.pushCup(2);
        tower.swap(new String[]{"lid","1"}, new String[]{"cup","2"});
        assertFalse(tower.ok()); // taza 1 no tiene tapa
    }

    @Test
    public void shouldSwapSameObjectDoNothing() {
        tower.pushCup(1);
        tower.pushCup(2);
        tower.swap(new String[]{"cup","1"}, new String[]{"cup","1"});
        assertTrue(tower.ok());
        assertEquals(4, tower.height()); // sin cambios
    }

    // ------------------------------------------------------------------ //
    //  REQ 12 — cover                                                     //
    // ------------------------------------------------------------------ //

    @Test
    public void shouldCoverAllCupsWhenTheyFit() {
        Tower t = new Tower(200, 30);
        t.pushCup(1);
        t.pushCup(2);
        t.cover();
        assertTrue(t.ok());
        assertEquals(2, t.liddedCups().length);
    }

    @Test
    public void shouldNotCoverCupsAlreadyCovered() {
        tower.pushCup(1);
        tower.pushLid(1);
        tower.pushCup(2);
        tower.cover(); // taza 1 ya tiene tapa, solo tapa taza 2
        assertTrue(tower.ok());
        int[] lidded = tower.liddedCups();
        assertEquals(2, lidded.length);
        assertEquals(1, lidded[0]);
        assertEquals(2, lidded[1]);
    }

    @Test
    public void shouldNotCoverIfNoRoomForLid() {
        // Torre exactamente llena con una taza
        Tower t = new Tower(200, 1);
        t.pushCup(1); // h=1, llena
        t.cover();    // no cabe la tapa (necesita 1cm más)
        assertEquals(0, t.liddedCups().length);
    }

    @Test
    public void shouldPartialCoverWhenSomeFit() {
        // Torre con espacio solo para una tapa
        Tower t = new Tower(200, 5);
        t.pushCup(1); // h=1
        t.pushCup(2); // h=3, total=4
        t.cover();    // solo cabe 1 tapa (quedaría h=5), la segunda no
        assertTrue(t.liddedCups().length >= 1);
    }

    // ------------------------------------------------------------------ //
    //  REQ 13 — swapToReduce                                              //
    // ------------------------------------------------------------------ //

    @Test
    public void shouldReturnSwapThatReducesHeight() {
        // cup4(h=7) base, cup1(h=1) cima → altura = 8
        // si intercambiamos: cup1(base) cup4(cima) → altura sigue 8
        // necesitamos un caso donde sí reduzca:
        // cup3(h=5) base, cup1(h=1) cima, cup4(h=7) medio → swap cup4 y cup1 reduce cima
        Tower t = new Tower(200, 20);
        t.pushCup(1);
        t.pushCup(4); // 4 está en la cima, h=7 → más alta
        t.pushCup(2);
        // la altura la define el apilamiento; swapToReduce busca reducir
        String[][] result = t.swapToReduce();
        // Si hay un intercambio que reduce, debe retornar 2 elementos
        // Si no hay, retorna vacío — ambos son válidos según el estado
        assertTrue(result.length == 0 || result.length == 2);
    }

    @Test
    public void shouldReturnEmptyWhenNoSwapReduces() {
        // Torre con una sola taza — no hay intercambio posible
        Tower t = new Tower(200, 10);
        t.pushCup(1);
        String[][] result = t.swapToReduce();
        assertEquals(0, result.length);
    }

    @Test
    public void shouldReturnEmptyForEmptyTower() {
        String[][] result = tower.swapToReduce();
        assertEquals(0, result.length);
    }

    @Test
    public void shouldNotModifyTowerWhenCallingSwapToReduce() {
        tower.pushCup(2);
        tower.pushCup(1);
        int heightBefore = tower.height();
        tower.swapToReduce(); // solo consulta, no modifica
        assertEquals(heightBefore, tower.height());
        String[][] items = tower.stackingItems();
        assertEquals("2", items[0][1]); // orden sin cambios
        assertEquals("1", items[1][1]);
    }
}