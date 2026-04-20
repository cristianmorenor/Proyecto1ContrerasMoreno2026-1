package stackingItems;

import javax.swing.JOptionPane;

/**
 * Pruebas de aceptación del ciclo 4.
 * Evidencian los comportamientos más representativos del proyecto:
 * <ol>
 *   <li>Torre con todos los tipos de tazas y tapas.</li>
 *   <li>Comportamiento de GreedyCup robando tapas.</li>
 * </ol>
 *
 * Cada prueba muestra la animación, hace pausas para observarla y
 * pregunta al usuario si la acepta.
 *
 * @author David Contreras y Cristian Moreno
 * @version 4.0
 */
public class TowerAtest {

    // Espera en milisegundos entre pasos visuales
    private static final int PAUSA_CORTA  = 1000;
    private static final int PAUSA_MEDIA  = 1500;
    private static final int PAUSA_LARGA  = 2000;

    /**
     * Pausa la ejecución el tiempo indicado en milisegundos.
     *
     * @param ms milisegundos a esperar
     */
    private static void esperar(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { /* ignorar */ }
    }

    /**
     * Pregunta al usuario si acepta la prueba.
     * Lanza AssertionError si el usuario dice que no.
     *
     * @param mensaje descripción de lo que debió verse
     */
    private static void preguntar(String mensaje) {
        int resp = JOptionPane.showConfirmDialog(
            null,
            mensaje + "\n\n¿La prueba es correcta?",
            "Prueba de Aceptación",
            JOptionPane.YES_NO_OPTION
        );
        if (resp != JOptionPane.YES_OPTION) {
            throw new AssertionError("Prueba de aceptación RECHAZADA por el usuario.");
        }
    }

    //  PRUEBA DE ACEPTACIÓN 1                                            
    //  Demuestra todos los tipos de tazas y tapas en una misma torre.   
    /**
     * Prueba de aceptación 1:
     * Se construye una torre con tazas normal, opener, hierarchical y greedy,
     * y tapas normal, fearful y crazy. Se observa que:
     * <ul>
     *   <li>La crazy aparece en la base (magenta).</li>
     *   <li>La opener elimina las tapas al entrar.</li>
     *   <li>La hierarchical desplaza las tazas menores hacia arriba.</li>
     *   <li>La greedy roba la primera tapa disponible.</li>
     * </ul>
     */
    public static void aceptacion1TodosLosTipos() {
        Tower tower = new Tower(200, 60);
        tower.makeVisible();
        esperar(PAUSA_MEDIA);

        // Paso 1: tazas normales
        JOptionPane.showMessageDialog(null,
            "PASO 1: Agregando tazas normales 1, 2 y 3.");
        tower.pushCup(1);
        esperar(PAUSA_CORTA);
        tower.pushCup(2);
        esperar(PAUSA_CORTA);
        tower.pushCup(3);
        esperar(PAUSA_MEDIA);

        // Paso 2: tapas normal y fearful
        JOptionPane.showMessageDialog(null,
            "PASO 2: Tapando taza 1 con tapa normal (color de la taza)\n"
          + "y taza 2 con tapa fearful (roja).");
        tower.pushLid("normal", 1);
        esperar(PAUSA_CORTA);
        tower.pushLid("fearful", 2);
        esperar(PAUSA_MEDIA);

        // Paso 3: tapa crazy
        JOptionPane.showMessageDialog(null,
            "PASO 3: Agregando tapa crazy de la taza 3.\n"
          + "Debe aparecer en la BASE de la torre (color magenta).");
        tower.pushLid("crazy", 3);
        esperar(PAUSA_MEDIA);

        // Paso 4: taza opener
        JOptionPane.showMessageDialog(null,
            "PASO 4: Insertando taza OPENER (paredes negras).\n"
          + "Debe eliminar TODAS las tapas al entrar.");
        tower.pushCup("opener", 4);
        esperar(PAUSA_LARGA);

        // Paso 5: taza hierarchical
        JOptionPane.showMessageDialog(null,
            "PASO 5: Insertando taza HIERARCHICAL número 5 (fondo amarillo).\n"
          + "Debe desplazar las tazas menores hacia arriba.");
        tower.pushCup("hierarchical", 5);
        esperar(PAUSA_LARGA);

        // Paso 6: tapa la taza 1 para que greedy la robe
        JOptionPane.showMessageDialog(null,
            "PASO 6: Tapando la taza 1 con tapa normal (preparando para greedy).");
        tower.pushLid("normal", 1);
        esperar(PAUSA_MEDIA);

        // Paso 7: taza greedy
        JOptionPane.showMessageDialog(null,
            "PASO 7: Insertando taza GREEDY número 6 (paredes naranjas).\n"
          + "Debe robar la tapa de la taza 1 al entrar.");
        tower.pushCup("greedy", 6);
        esperar(PAUSA_LARGA);

        // Resultado final
        JOptionPane.showMessageDialog(null,
            "Estado final de la torre:\n" + tower.stackingItemsAsString());

        preguntar(
            "Se observó:\n"
          + "- Tapa crazy en la base (magenta)\n"
          + "- Opener eliminó todas las tapas al entrar\n"
          + "- Hierarchical desplazó tazas menores\n"
          + "- Greedy robó la tapa de la taza 1\n"
          + "- Distinciones visuales por color correctas"
        );

        tower.exit();
    }

    //  PRUEBA DE ACEPTACIÓN 2                                       
    //  Demuestra reorganización: order, reverse, swap y coverr. 
    /**
     * Prueba de aceptación 2:
     * Se construye una torre desordenada y se aplican orderTower, reverseTower,
     * swap y cover. Se verifica visualmente que cada operación reorganiza
     * correctamente la torre.
     */
    public static void aceptacion2Reorganizacion() {
        Tower tower = new Tower(200, 60);
        tower.makeVisible();
        esperar(PAUSA_MEDIA);

        // Paso 1: insertar tazas en orden desordenado
        JOptionPane.showMessageDialog(null,
            "PASO 1: Insertando tazas en orden desordenado: 3, 1, 4, 2.");
        tower.pushCup(3);
        esperar(PAUSA_CORTA);
        tower.pushCup(1);
        esperar(PAUSA_CORTA);
        tower.pushCup(4);
        esperar(PAUSA_CORTA);
        tower.pushCup(2);
        esperar(PAUSA_MEDIA);

        // Paso 2: orderTower
        JOptionPane.showMessageDialog(null,
            "PASO 2: Aplicando ORDER TOWER.\n"
          + "Las tazas deben quedar de mayor (base) a menor (cima).");
        tower.orderTower();
        esperar(PAUSA_LARGA);

        // Paso 3: reverseTower
        JOptionPane.showMessageDialog(null,
            "PASO 3: Aplicando REVERSE TOWER.\n"
          + "El orden debe invertirse.");
        tower.reverseTower();
        esperar(PAUSA_LARGA);

        // Paso 4: swap
        JOptionPane.showMessageDialog(null,
            "PASO 4: Aplicando SWAP entre taza 1 y taza 4.\n"
          + "Las dos tazas deben intercambiar posiciones.");
        tower.swap(new String[]{"cup", "1"}, new String[]{"cup", "4"});
        esperar(PAUSA_LARGA);

        // Paso 5: cover
        JOptionPane.showMessageDialog(null,
            "PASO 5: Aplicando COVER.\n"
          + "Todas las tazas sin tapa deben recibir una tapa normal.");
        tower.cover();
        esperar(PAUSA_LARGA);

        // Resultado final
        JOptionPane.showMessageDialog(null,
            "Estado final de la torre:\n" + tower.stackingItemsAsString()
          + "\nAltura total: " + tower.height() + " cm"
          + "\nTazas tapadas: " + tower.liddedCups().length);

        preguntar(
            "Se observó:\n"
          + "- orderTower ordenó de mayor a menor\n"
          + "- reverseTower invirtió el orden\n"
          + "- swap intercambió taza 1 y taza 4\n"
          + "- cover tapó todas las tazas disponibles"
        );

        tower.exit();
    }

    //  MAIN para ejecutar ambas pruebas de aceptación                        
    /**
     * Punto de entrada para ejecutar las pruebas de aceptación.
     *
     * @param args argumentos de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null,
            "=== PRUEBAS DE ACEPTACIÓN - CICLO 4 ===\n\n"
          + "Se ejecutarán 2 pruebas de aceptación.\n"
          + "Observe la animación en cada paso y responda\n"
          + "si lo que se muestra es correcto.\n\n"
          + "Presione OK para comenzar.");

        try {
            aceptacion1TodosLosTipos();
            JOptionPane.showMessageDialog(null,
                "✓ Prueba 1 ACEPTADA. Iniciando prueba 2...");

            aceptacion2Reorganizacion();
            JOptionPane.showMessageDialog(null,
                "✓ Prueba 2 ACEPTADA.\n\nTodas las pruebas de aceptación pasaron.");

        } catch (AssertionError e) {
            JOptionPane.showMessageDialog(null,
                "✗ Una prueba fue RECHAZADA.\n" + e.getMessage(),
                "Prueba Fallida", JOptionPane.ERROR_MESSAGE);
        }
    }
}