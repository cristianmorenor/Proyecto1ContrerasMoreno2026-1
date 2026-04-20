package stackingitems;

import shapes.Canvas;
import java.util.ArrayList;
import javax.swing.JOptionPane;
// Clase base del sistema

/**
 * Representa una torre donde se apilan tazas y tapas.
 * Soporta tazas de tipo normal, opener, hierarchical y greedy.
 * Soporta tapas de tipo normal, fearful y crazy.
 *
 * @author David Contreras y Cristian Moreno
 * @version 4.0
 */
public class Tower {

    private int width;
    private int maxHeight;
    private boolean visible;
    private boolean ok;

    /** Lista de tazas en la torre, índice 0 = base, último = cima. */
    private ArrayList<Cup> cups;

    /** Lista de CrazyLids en la base de la torre (no van sobre ninguna taza). */
    private ArrayList<CrazyLid> crazyLids;

    private TowerBackground background;

    private static final int CANVAS_W = 300;
    private static final int CANVAS_H = 300;
    private static final int CENTER_X = CANVAS_W / 2;
    private static final int BASE_Y   = CANVAS_H - 40;
    private static final int SCALE    = 8;

    // ------------------------------------------------------------------ //
    //  CONSTRUCTORES                                                      //
    // ------------------------------------------------------------------ //

    /**
     * Crea una torre vacía con ancho y altura máxima dados.
     *
     * @param width     ancho visual de referencia
     * @param maxHeight altura máxima permitida en cm
     */
    public Tower(int width, int maxHeight) {
        this.width     = width;
        this.maxHeight = maxHeight;
        this.visible   = false;
        this.ok        = true;
        cups      = new ArrayList<>();
        crazyLids = new ArrayList<>();
        int maxW  = 20 + (maxHeight * 14);
        int leftX = CENTER_X - (maxW / 2);
        background = new TowerBackground(leftX, BASE_Y - SCALE, maxHeight);
        Canvas.getCanvas().setVisible(true);
    }

    /**
     * Crea una torre con tazas normales de 1 a cups.
     * Altura máxima = cups² (suma de alturas).
     *
     * @param cups número de tazas a crear (creador masivo)
     */
    public Tower(int cups) {
        this.width     = 200;
        this.visible   = false;
        this.ok        = true;
        this.cups      = new ArrayList<>();
        this.crazyLids = new ArrayList<>();
        this.maxHeight = cups * cups;

        int maxW  = 20 + (maxHeight * 14);
        int leftX = CENTER_X - (maxW / 2);
        background = new TowerBackground(leftX, BASE_Y - SCALE, maxHeight);
        Canvas.getCanvas().setVisible(true);

        // El creador masivo solo usa elementos normales
        for (int i = 1; i <= cups; i++) {
            NormalCup cup = new NormalCup(i, calculateWidth(i));
            this.cups.add(cup);
        }
    }

    
    //  GESTIÓN DE TAZAS                                                 
    /**
     * Agrega una taza normal número n a la cima de la torre.
     *
     * @param n número de la taza
     */
    public void pushCup(int n) {
        pushCup("normal", n);
    }

    /**
     * Agrega una taza del tipo y número indicados a la torre.
     * Tipos válidos: "normal", "opener", "hierarchical", "greedy".
     *
     * <ul>
     *   <li><b>opener</b>: elimina todas las tapas que le impiden el paso antes de entrar.</li>
     *   <li><b>hierarchical</b>: desplaza a la cima todas las tazas de número menor;
     *       si queda en el fondo (índice 0), no puede ser removida.</li>
     *   <li><b>greedy</b>: al entrar, roba la primera tapa removible disponible.</li>
     * </ul>
     *
     * @param type tipo de taza
     * @param n    número de la taza
     */
    public void pushCup(String type, int n) {
        if (getCup(n) != null) {
            fail("La taza " + n + " ya existe en la torre.");
            return;
        }

        Cup cup = createCup(type, n);

        // --- Comportamiento opener: quitar todas las tapas ---
        if (type.equals("opener")) {
            removeAllLids();
        }

        // --- Comportamiento hierarchical: delegar a lógica especial ---
        if (type.equals("hierarchical")) {
            pushHierarchical((HierarchicalCup) cup);
            ok = true;
            if (visible) repositionAndDraw();
            return;
        }

        if (height() + cup.getHeight() > maxHeight) {
            fail("La taza " + n + " no cabe en la torre.");
            return;
        }

        // Delegar el comportamiento de inserción a la propia taza
        cup.onPush(this);

        updateHierarchicalBottomStatus();
        if (visible) repositionAndDraw();
        ok = true;
    }

    /**
     * Agrega una taza directamente en la cima de la lista.
     * Usado internamente por las subclases de Cup (ej. NormalCup, GreedyCup).
     *
     * @param cup taza a agregar
     */
    public void addCupAtTop(Cup cup) {
        cups.add(cup);
    }

    /**
     * Retorna la lista de tazas de la torre (de base a cima).
     * Usado por GreedyCup para iterar sobre las tazas disponibles.
     *
     * @return lista de tazas
     */
    public ArrayList<Cup> getCups() {
        return cups;
    }

    /**
     * Elimina la taza en la cima de la torre.
     */
    public void popCup() {
        if (cups.isEmpty()) {
            fail("La torre no tiene tazas.");
            return;
        }
        Cup top = cups.get(cups.size() - 1);
        top.makeInvisible();
        cups.remove(top);
        updateHierarchicalBottomStatus();
        if (visible) repositionAndDraw();
        ok = true;
    }

    /**
     * Elimina la taza número n de cualquier posición.
     * Si es <b>hierarchical</b> y está en el fondo, falla.
     *
     * @param n número de la taza a eliminar
     */
    public void removeCup(int n) {
        Cup cup = getCup(n);
        if (cup == null) {
            fail("La taza " + n + " no existe en la torre.");
            return;
        }

        // hierarchical en el fondo: no se puede quitar
        if (cup instanceof HierarchicalCup && ((HierarchicalCup) cup).isAtBottom()) {
            fail("La taza hierarchical " + n + " está en el fondo y no puede ser removida.");
            return;
        }

        cup.makeInvisible();
        cups.remove(cup);
        updateHierarchicalBottomStatus();
        if (visible) repositionAndDraw();
        ok = true;
    }


    /**
     * Agrega una tapa normal a la taza número n.
     *
     * @param n número de la taza
     */
    public void pushLid(int n) {
        pushLid("normal", n);
    }

    /**
     * Agrega una tapa del tipo indicado a la taza número n.
     * Tipos válidos: "normal", "fearful", "crazy".
     *
     * <ul>
     *   <li><b>fearful</b>: falla si la taza compañera no está en la torre.</li>
     *   <li><b>crazy</b>: en lugar de tapar a su taza, se ubica como base de la torre.</li>
     * </ul>
     *
     * @param type tipo de tapa
     * @param n    número de la taza a tapar
     */
    public void pushLid(String type, int n) {
        Cup cup = getCup(n);

        // --- Comportamiento fearful: la taza debe estar en la torre ---
        if (type.equals("fearful") && cup == null) {
            fail("La tapa fearful no puede entrar: su taza " + n + " no está en la torre.");
            return;
        }

        // --- Comportamiento crazy: va a la base, no tapa a la taza ---
        if (type.equals("crazy")) {
            if (height() + 1 > maxHeight) {
                fail("La tapa crazy no cabe en la torre.");
                return;
            }
            int lidWidth = (cup != null) ? cup.getWidth() : calculateWidth(n);
            CrazyLid crazy = new CrazyLid(n, lidWidth);
            crazyLids.add(crazy);
            if (visible) repositionAndDraw();
            ok = true;
            return;
        }

        // --- Tapa normal y fearful ---
        if (cup == null || cup.hasLid()) {
            fail("No se puede agregar tapa a la taza " + n + ".");
            return;
        }
        if (height() + 1 > maxHeight) {
            fail("La tapa de la taza " + n + " no cabe en la torre.");
            return;
        }

        Lid lid = createLid(type, n, cup.getWidth());
        // Solo la tapa normal toma el color de su taza;
        // las especiales (fearful=rojo) conservan su color distintivo
        if (type.equals("normal")) {
            lid.setColor(cup.getColor());
        }
        cup.setLid(lid);
        if (visible) repositionAndDraw();
        ok = true;
    }

    /**
     * Elimina la tapa de la taza número n.
     * Si la tapa es <b>fearful</b>, no puede ser removida (falla).
     *
     * @param n número de la taza cuya tapa se elimina
     */
    public void popLid(int n) {
        Cup cup = getCup(n);
        if (cup == null || !cup.hasLid()) {
            // Revisar si es una CrazyLid
            CrazyLid crazy = getCrazyLid(n);
            if (crazy != null) {
                crazy.makeInvisible();
                crazyLids.remove(crazy);
                if (visible) repositionAndDraw();
                ok = true;
                return;
            }
            fail("La taza " + n + " no tiene tapa.");
            return;
        }

        // fearful tapando a su taza: no puede salir
        if (cup.getLid() instanceof FearfulLid) {
            fail("La tapa fearful de la taza " + n
                + " no puede salir mientras esté tapando a su taza.");
            return;
        }

        cup.getLid().makeInvisible();
        cup.removeLid();
        if (visible) repositionAndDraw();
        ok = true;
    }

    /**
     * Alias de popLid.
     *
     * @param n número de la taza cuya tapa se elimina
     */
    public void removeLid(int n) {
        popLid(n);
    }

    //  REORGANIZACIÓN                                                     
    /**
     * Ordena las tazas de mayor a menor número (la mayor queda en la base).
     */
    public void orderTower() {
        cups.sort((a, b) -> b.getNumber() - a.getNumber());
        trimToFit();
        updateHierarchicalBottomStatus();
        if (visible) repositionAndDraw();
        ok = true;
    }

    /**
     * Invierte el orden actual de las tazas en la torre.
     */
    public void reverseTower() {
        ArrayList<Cup> reversed = new ArrayList<>();
        for (int i = cups.size() - 1; i >= 0; i--) {
            reversed.add(cups.get(i));
        }
        cups = reversed;
        trimToFit();
        updateHierarchicalBottomStatus();
        if (visible) repositionAndDraw();
        ok = true;
    }

    /**
     * Intercambia la posición de dos objetos en la torre.
     * Cada objeto se identifica por un par {tipo, número}.
     *
     * @param o1 arreglo {"cup"|"lid", "número"} del primer objeto
     * @param o2 arreglo {"cup"|"lid", "número"} del segundo objeto
     */
    public void swap(String[] o1, String[] o2) {
        int idx1 = findIndex(o1);
        int idx2 = findIndex(o2);

        if (idx1 < 0 || idx2 < 0) {
            fail("Uno de los objetos a intercambiar no existe en la torre.");
            return;
        }
        if (idx1 == idx2) { ok = true; return; }

        Cup cup1 = cups.get(idx1);
        Cup cup2 = cups.get(idx2);
        cups.set(idx1, cup2);
        cups.set(idx2, cup1);

        updateHierarchicalBottomStatus();
        if (visible) repositionAndDraw();
        ok = true;
    }

    /**
     * Tapa todas las tazas que no tienen tapa, siempre que quepan.
     */
    public void cover() {
        for (Cup cup : cups) {
            if (!cup.hasLid() && height() + 1 <= maxHeight) {
                NormalLid lid = new NormalLid(cup.getNumber(), cup.getWidth());
                lid.setColor(cup.getColor());
                cup.setLid(lid);
            }
        }
        if (visible) repositionAndDraw();
        ok = true;
    }


    //  CONSULTAS                                                         
    /**
     * Retorna la altura total en cm de todos los elementos apilados.
     *
     * @return altura total en cm
     */
    public int height() {
        int total = crazyLids.size(); // cada crazy lid ocupa 1 cm en la base
        for (Cup c : cups) {
            total += c.getHeight();
            if (c.hasLid()) total += c.getLid().getHeight();
        }
        return total;
    }

    /**
     * Retorna los números de las tazas tapadas, ordenados de menor a mayor.
     *
     * @return arreglo de enteros con los números de tazas tapadas
     */
    public int[] liddedCups() {
        ArrayList<Integer> result = new ArrayList<>();
        for (Cup c : cups) {
            if (c.hasLid()) result.add(c.getNumber());
        }
        result.sort(Integer::compareTo);
        int[] array = new int[result.size()];
        for (int i = 0; i < result.size(); i++) array[i] = result.get(i);
        return array;
    }

    /**
     * Retorna los elementos de la torre de base a cima.
     * Incluye CrazyLids al inicio y luego tazas con sus tapas.
     *
     * @return arreglo de pares {tipo, número}
     */
    public String[][] stackingItems() {
        ArrayList<String[]> result = new ArrayList<>();
        for (CrazyLid cl : crazyLids) {
            result.add(new String[]{"lid", String.valueOf(cl.getNumber())});
        }
        for (Cup c : cups) {
            result.add(new String[]{"cup", String.valueOf(c.getNumber())});
            if (c.hasLid()) {
                result.add(new String[]{"lid", String.valueOf(c.getNumber())});
            }
        }
        return result.toArray(new String[0][]);
    }

    /**
     * Versión legible de stackingItems para inspección en BlueJ.
     *
     * @return String con todos los elementos de base a cima
     */
    public String stackingItemsAsString() {
        StringBuilder sb = new StringBuilder();
        for (CrazyLid cl : crazyLids) {
            sb.append("[crazy-lid-").append(cl.getNumber()).append("]");
        }
        for (Cup c : cups) {
            sb.append("[").append(c.getType()).append("-cup-")
              .append(c.getNumber()).append("]");
            if (c.hasLid()) {
                sb.append("[").append(c.getLid().getType()).append("-lid-")
                  .append(c.getNumber()).append("]");
            }
        }
        return sb.length() == 0 ? "(vacía)" : sb.toString();
    }

    /**
     * Consulta qué intercambio de dos tazas reduciría la altura de la torre.
     * Si no existe ninguno que la reduzca, retorna arreglo vacío.
     *
     * @return par {{"cup","n1"}, {"cup","n2"}} o arreglo vacío
     */
    public String[][] swapToReduce() {
        int currentHeight = height();
        int bestHeight    = currentHeight;
        int bestI = -1, bestJ = -1;

        for (int i = 0; i < cups.size(); i++) {
            for (int j = i + 1; j < cups.size(); j++) {
                Cup tmp = cups.get(i);
                cups.set(i, cups.get(j));
                cups.set(j, tmp);

                int newHeight = height();
                if (newHeight < bestHeight) {
                    bestHeight = newHeight;
                    bestI = i;
                    bestJ = j;
                }

                tmp = cups.get(i);
                cups.set(i, cups.get(j));
                cups.set(j, tmp);
            }
        }

        if (bestI < 0) return new String[0][];

        Cup c1 = cups.get(bestI);
        Cup c2 = cups.get(bestJ);
        return new String[][]{
            {"cup", String.valueOf(c1.getNumber())},
            {"cup", String.valueOf(c2.getNumber())}
        };
    }

    /**
     * Versión legible de swapToReduce.
     *
     * @return String con el intercambio sugerido o mensaje de que no existe
     */
    public String swapToReduceAsString() {
        String[][] result = swapToReduce();
        if (result.length == 0) return "ningún intercambio reduce la altura";
        return "swap " + result[0][0] + "-" + result[0][1]
             + " con " + result[1][0] + "-" + result[1][1];
    }


    //  VISIBILIDAD                                                        
    /** Hace visible la torre y todos sus elementos. */
    public void makeVisible() {
        visible = true;
        background.makeVisible();
        repositionAndDraw();
    }

    /** Oculta la torre y todos sus elementos. */
    public void makeInvisible() {
        visible = false;
        background.makeInvisible();
        for (Cup c : cups) c.makeInvisible();
        for (CrazyLid cl : crazyLids) cl.makeInvisible();
    }

    /** Cierra el simulador. */
    public void exit() {
        makeInvisible();
        Canvas.getCanvas().setVisible(false);
    }

    /**
     * Indica si la última operación fue exitosa.
     *
     * @return true si la última operación se realizó correctamente
     */
    public boolean ok() { return ok; }

    //  PRIVADOS - FÁBRICA DE OBJETOS                                     
    private Cup createCup(String type, int n) {
        int w = calculateWidth(n);
        switch (type) {
            case "opener":       return new OpenerCup(n, w);
            case "hierarchical": return new HierarchicalCup(n, w);
            case "greedy":       return new GreedyCup(n, w);
            default:             return new NormalCup(n, w);
        }
    }

    private Lid createLid(String type, int n, int width) {
        switch (type) {
            case "fearful": return new FearfulLid(n, width);
            case "crazy":   return new CrazyLid(n, width);
            default:        return new NormalLid(n, width);
        }
    }

    //  PRIVADOS - LÓGICA DE TIPOS ESPECIALES                             //
    /**
     * Elimina todas las tapas de todas las tazas de la torre.
     * Usado por OpenerCup al entrar.
     */
    private void removeAllLids() {
        for (Cup c : cups) {
            if (c.hasLid()) {
                c.getLid().makeInvisible();
                c.removeLid();
            }
        }
        for (CrazyLid cl : crazyLids) cl.makeInvisible();
        crazyLids.clear();
    }

    /**
     * Inserta una taza hierarchical: desplaza a la cima todas las tazas
     * con número menor que la que entra, luego la inserta en su lugar.
     *
     * @param hcup taza hierarchical a insertar
     */
    private void pushHierarchical(HierarchicalCup hcup) {
        if (height() + hcup.getHeight() > maxHeight) {
            fail("La taza hierarchical " + hcup.getNumber() + " no cabe en la torre.");
            return;
        }

        // Separar tazas menores y mayores-iguales
        ArrayList<Cup> smaller = new ArrayList<>();
        ArrayList<Cup> rest    = new ArrayList<>();

        for (Cup c : cups) {
            if (c.getNumber() < hcup.getNumber()) smaller.add(c);
            else rest.add(c);
        }

        // Nueva disposición: rest + hcup + smaller encima
        cups.clear();
        cups.addAll(rest);
        cups.add(hcup);
        cups.addAll(smaller);

        trimToFit();
        updateHierarchicalBottomStatus();
    }

    /**
     * Actualiza la bandera atBottom de todas las tazas hierarchical.
     * Una hierarchical está "en el fondo" si está en el índice 0 de la torre
     * y no hay CrazyLids debajo.
     */
    private void updateHierarchicalBottomStatus() {
        for (int i = 0; i < cups.size(); i++) {
            Cup c = cups.get(i);
            if (c instanceof HierarchicalCup) {
                boolean isAtBottom = (i == 0) && crazyLids.isEmpty();
                ((HierarchicalCup) c).setAtBottom(isAtBottom);
            }
        }
    }


    private int calculateWidth(int n) {
        return 20 + (n * 15);
    }

    private Cup getCup(int n) {
        for (Cup c : cups) {
            if (c.getNumber() == n) return c;
        }
        return null;
    }

    private CrazyLid getCrazyLid(int n) {
        for (CrazyLid cl : crazyLids) {
            if (cl.getNumber() == n) return cl;
        }
        return null;
    }

    private int findIndex(String[] obj) {
        int n = Integer.parseInt(obj[1]);
        for (int i = 0; i < cups.size(); i++) {
            Cup c = cups.get(i);
            if (c.getNumber() == n) {
                if (obj[0].equals("cup")) return i;
                if (obj[0].equals("lid") && c.hasLid()) return i;
            }
        }
        return -1;
    }

    private void trimToFit() {
        while (!cups.isEmpty() && height() > maxHeight) {
            Cup removed = cups.remove(cups.size() - 1);
            removed.makeInvisible();
        }
    }

    private void repositionAndDraw() {
        for (Cup c : cups) c.makeInvisible();
        for (CrazyLid cl : crazyLids) cl.makeInvisible();

        // Dibujar CrazyLids en la base primero
        int currentY = BASE_Y;
        for (CrazyLid cl : crazyLids) {
            int clX = CENTER_X - (cl.getWidth() / 2);
            cl.setPosition(clX, currentY);
            cl.makeVisible();
            currentY -= Lid.SCALE;
        }

        // Dibujar tazas de base a cima
        for (int i = 0; i < cups.size(); i++) {
            Cup c       = cups.get(i);
            int myBase  = currentY - (i * SCALE);
            int cupX    = CENTER_X - (c.getWidth() / 2);
            int cupTopY = myBase - (c.getHeight() * SCALE);

            c.setPosition(cupX, cupTopY);
            c.makeVisible();

            if (c.hasLid()) {
                c.getLid().setPosition(cupX, cupTopY - Lid.SCALE);
                c.getLid().makeVisible();
            }
        }
    }

    private void fail(String message) {
        ok = false;
        if (visible) {
            JOptionPane.showMessageDialog(null, message, "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}