package stackingItems;

import shapes.Canvas;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Representa una torre donde se apilan tazas y tapas.
 * Las tazas se apilan de abajo hacia arriba en el orden en que fueron agregadas.
 *
 * @David Contreras y Cristian Moreno
 * @version 2.0
 */
public class Tower {

    private int width;
    private int maxHeight;
    private boolean visible;
    private boolean ok;

    /** Lista de tazas en la torre, de base (índice 0) a cima (último índice). */
    private ArrayList<Cup> cups;
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
     * Crea una torre vacía con ancho y altura máxima dados (Ciclo 1).
     *
     * @param width     ancho visual de referencia
     * @param maxHeight altura máxima permitida en cm
     */
    public Tower(int width, int maxHeight) {
        this.width     = width;
        this.maxHeight = maxHeight;
        this.visible   = false;
        this.ok        = true;
        cups = new ArrayList<>();
        int maxW  = 20 + (maxHeight * 14);
        int leftX = CENTER_X - (maxW / 2);
        background = new TowerBackground(leftX, BASE_Y - SCALE, maxHeight);
        Canvas.getCanvas().setVisible(true);
    }

    /**
     * Crea una torre con tazas de 1 a cups, sin tapas (Ciclo 2 - Req. 10).
     * La altura máxima se calcula como la suma de todas las tazas.
     * Ejemplo: cups=4 crea tazas 1(h=1), 2(h=3), 3(h=5), 4(h=7) → maxHeight=16
     *
     * @param cups número de tazas a crear (de 1 a cups)
     */
    public Tower(int cups) {
        this.width   = 200;
        this.visible = false;
        this.ok      = true;
        this.cups    = new ArrayList<>();

        // Calcular altura total: suma de (2i-1) para i=1..cups = cups^2
        this.maxHeight = cups * cups;

        int maxW  = 20 + (maxHeight * 14);
        int leftX = CENTER_X - (maxW / 2);
        background = new TowerBackground(leftX, BASE_Y - SCALE, maxHeight);
        Canvas.getCanvas().setVisible(true);

        // Agregar tazas 1..cups en orden
        for (int i = 1; i <= cups; i++) {
            Cup cup = new Cup(i, calculateWidth(i));
            this.cups.add(cup);
        }
    }

    // ------------------------------------------------------------------ //
    //  GESTIÓN DE TAZAS                                                   //
    // ------------------------------------------------------------------ //

    /**
     * Agrega la taza número n a la cima de la torre.
     * Falla si ya existe o si supera la altura máxima.
     *
     * @param n número de la taza
     */
    public void pushCup(int n) {
        if (getCup(n) != null) {
            fail("La taza " + n + " ya existe en la torre.");
            return;
        }
        Cup cup = new Cup(n, calculateWidth(n));
        if (height() + cup.getHeight() > maxHeight) {
            fail("La taza " + n + " no cabe en la torre.");
            return;
        }
        cups.add(cup);
        if (visible) repositionAndDraw();
        ok = true;
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
        if (visible) repositionAndDraw();
        ok = true;
    }

    /**
     * Elimina la taza número n de cualquier posición de la torre.
     *
     * @param n número de la taza a eliminar
     */
    public void removeCup(int n) {
        Cup cup = getCup(n);
        if (cup == null) {
            fail("La taza " + n + " no existe en la torre.");
            return;
        }
        cup.makeInvisible();
        cups.remove(cup);
        if (visible) repositionAndDraw();
        ok = true;
    }

    // ------------------------------------------------------------------ //
    //  GESTIÓN DE TAPAS                                                   //
    // ------------------------------------------------------------------ //

    /**
     * Agrega una tapa a la taza número n.
     * Falla si la taza no existe, ya tiene tapa, o si no cabe.
     *
     * @param n número de la taza a tapar
     */
    public void pushLid(int n) {
        Cup cup = getCup(n);
        if (cup == null || cup.hasLid()) {
            fail("No se puede agregar tapa a la taza " + n + ".");
            return;
        }
        if (height() + 1 > maxHeight) {
            fail("La tapa de la taza " + n + " no cabe en la torre.");
            return;
        }
        Lid lid = new Lid(n, cup.getWidth());
        lid.setColor(cup.getColor());
        cup.setLid(lid);
        if (visible) repositionAndDraw();
        ok = true;
    }

    /**
     * Elimina la tapa de la taza número n.
     *
     * @param n número de la taza cuya tapa se elimina
     */
    public void popLid(int n) {
        Cup cup = getCup(n);
        if (cup == null || !cup.hasLid()) {
            fail("La taza " + n + " no tiene tapa.");
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

    // ------------------------------------------------------------------ //
    //  REORGANIZACIÓN                                                     //
    // ------------------------------------------------------------------ //

    /**
     * Ordena las tazas de mayor a menor número (mayor queda en la base).
     * Recorta las que no quepan.
     */
    public void orderTower() {
        cups.sort((a, b) -> b.getNumber() - a.getNumber());
        trimToFit();
        if (visible) repositionAndDraw();
        ok = true;
    }

    /**
     * Invierte el orden actual de las tazas en la torre.
     * Recorta las que no quepan.
     */
    public void reverseTower() {
        ArrayList<Cup> reversed = new ArrayList<>();
        for (int i = cups.size() - 1; i >= 0; i--) {
            reversed.add(cups.get(i));
        }
        cups = reversed;
        trimToFit();
        if (visible) repositionAndDraw();
        ok = true;
    }

    /**
     * Intercambia la posición de dos objetos en la torre (Ciclo 2 - Req. 11).
     * Cada objeto se identifica por un par {tipo, número}.
     * tipo puede ser "cup" o "lid". Si es "lid", se mueve junto con su taza.
     * Falla si alguno de los objetos no existe en la torre.
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
        if (idx1 == idx2) {
            ok = true;
            return; // mismo elemento, no hace nada
        }

        // Intercambiar en la lista (si son tapas, la taza va con ellas implícitamente
        // porque la tapa siempre está pegada a su taza en stackingItems)
        Cup cup1 = cups.get(idx1);
        Cup cup2 = cups.get(idx2);
        cups.set(idx1, cup2);
        cups.set(idx2, cup1);

        if (visible) repositionAndDraw();
        ok = true;
    }

    /**
     * Tapa todas las tazas que tienen su tapa disponible en la torre (Ciclo 2 - Req. 12).
     * Una tapa "está en la torre" si existe un Lid con el mismo número que la taza
     * pero aún no está asignada (es decir, la taza no tiene lid y existe un lid suelto).
     * En esta implementación, cover() asigna tapa a toda taza que no la tenga,
     * siempre que quepan en la torre.
     */
    public void cover() {
        for (Cup cup : cups) {
            if (!cup.hasLid() && height() + 1 <= maxHeight) {
                Lid lid = new Lid(cup.getNumber(), cup.getWidth());
                lid.setColor(cup.getColor());
                cup.setLid(lid);
            }
        }
        if (visible) repositionAndDraw();
        ok = true;
    }

    // ------------------------------------------------------------------ //
    //  CONSULTAS                                                          //
    // ------------------------------------------------------------------ //

    /**
     * Retorna la altura total en cm de todos los elementos apilados.
     *
     * @return altura total en cm
     */
    public int height() {
        int total = 0;
        for (Cup c : cups) {
            total += c.getHeight();
            if (c.hasLid()) total += c.getLid().getHeight();
        }
        return total;
    }

    /**
     * Retorna los números de las tazas tapadas, ordenados de menor a mayor.
     *
     * @return arreglo de números de tazas tapadas
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
     * Ejemplo: {{"cup","4"},{"lid","4"},{"cup","2"}}
     *
     * @return arreglo de pares {tipo, número}
     */
    public String[][] stackingItems() {
        ArrayList<String[]> result = new ArrayList<>();
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
     * Ejemplo: "[cup-3][lid-3][cup-1]"
     *
     * @return String con todos los elementos de base a cima
     */
    public String stackingItemsAsString() {
        StringBuilder sb = new StringBuilder();
        for (Cup c : cups) {
            sb.append("[cup-").append(c.getNumber()).append("]");
            if (c.hasLid()) sb.append("[lid-").append(c.getNumber()).append("]");
        }
        return sb.length() == 0 ? "(vacía)" : sb.toString();
    }

    /**
     * Consulta qué intercambio de dos objetos reduciría la altura de la torre
     * (Ciclo 2 - Req. 13).
     *
     * La altura de la torre está determinada por la taza MÁS ALTA (la que tiene
     * mayor número) que se encuentre en la cima visible. Al intercambiar una taza
     * grande que esté arriba con una pequeña que esté abajo, la altura puede bajar.
     *
     * Estrategia: busca el par (i, j) con i < j tal que al intercambiar cups[i] y
     * cups[j] la altura resultante sea mínima. Retorna el par que más reduce.
     * Si no existe ningún intercambio que reduzca la altura, retorna arreglo vacío.
     *
     * @return par {{"cup"|"lid", "n1"}, {"cup"|"lid", "n2"}} o arreglo vacío
     */
    public String[][] swapToReduce() {
        int currentHeight = height();
        int bestHeight    = currentHeight;
        int bestI = -1, bestJ = -1;

        for (int i = 0; i < cups.size(); i++) {
            for (int j = i + 1; j < cups.size(); j++) {
                // Simular intercambio
                Cup tmp = cups.get(i);
                cups.set(i, cups.get(j));
                cups.set(j, tmp);

                int newHeight = height();
                if (newHeight < bestHeight) {
                    bestHeight = newHeight;
                    bestI = i;
                    bestJ = j;
                }

                // Deshacer intercambio
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
     * Versión legible de swapToReduce para inspección en BlueJ.
     * Ejemplo: "swap cup-4 con cup-1" o "ningún intercambio reduce la altura"
     *
     * @return String con el intercambio sugerido
     */
    public String swapToReduceAsString() {
        String[][] result = swapToReduce();
        if (result.length == 0) return "ningún intercambio reduce la altura";
        return "swap " + result[0][0] + "-" + result[0][1]
             + " con " + result[1][0] + "-" + result[1][1];
    }

    // ------------------------------------------------------------------ //
    //  VISIBILIDAD                                                        //
    // ------------------------------------------------------------------ //

    /**
     * Hace visible la torre y todos sus elementos.
     */
    public void makeVisible() {
        visible = true;
        background.makeVisible();
        repositionAndDraw();
    }

    /**
     * Oculta la torre y todos sus elementos.
     */
    public void makeInvisible() {
        visible = false;
        background.makeInvisible();
        for (Cup c : cups) c.makeInvisible();
    }

    /**
     * Termina el simulador cerrando el canvas.
     */
    public void exit() {
        makeInvisible();
        Canvas.getCanvas().setVisible(false);
    }

    /**
     * Indica si la última operación fue exitosa.
     *
     * @return true si la última operación se realizó correctamente
     */
    public boolean ok() {
        return ok;
    }

    // ------------------------------------------------------------------ //
    //  PRIVADOS                                                           //
    // ------------------------------------------------------------------ //

    /**
     * Calcula el ancho visual de la taza número n en píxeles.
     *
     * @param n número de la taza
     * @return ancho en píxeles
     */
    private int calculateWidth(int n) {
        return 20 + (n * 15);
    }

    /**
     * Busca la taza con el número dado.
     *
     * @param n número de la taza
     * @return la taza encontrada, o null si no existe
     */
    private Cup getCup(int n) {
        for (Cup c : cups) {
            if (c.getNumber() == n) return c;
        }
        return null;
    }

    /**
     * Retorna el índice en cups[] del objeto identificado por {tipo, número}.
     * Si tipo es "lid", busca la taza dueña de esa tapa.
     *
     * @param obj arreglo {"cup"|"lid", "número"}
     * @return índice en cups, o -1 si no existe
     */
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

    /**
     * Elimina tazas de la cima hasta que la altura total no supere maxHeight.
     */
    private void trimToFit() {
        while (!cups.isEmpty() && height() > maxHeight) {
            Cup removed = cups.remove(cups.size() - 1);
            removed.makeInvisible();
        }
    }

    /**
     * Reposiciona y redibuja todas las tazas y tapas apilándolas desde la base.
     */
    private void repositionAndDraw() {
        for (Cup c : cups) c.makeInvisible();

        // Ordenar de mayor a menor ancho: la grande primero (queda atrás visualmente).
        ArrayList<Cup> drawOrder = new ArrayList<>(cups);
        drawOrder.sort((a, b) -> b.getWidth() - a.getWidth());

        // Cada taza tiene su base 1cm (SCALE px) más arriba que la anterior.
        // índice 0 = más grande, base en BASE_Y
        // índice 1 = siguiente, base en BASE_Y - SCALE
        // índice i = base en BASE_Y - i*SCALE
        for (int i = 0; i < drawOrder.size(); i++) {
            Cup c      = drawOrder.get(i);
            int myBase = BASE_Y - (i * SCALE);
            int cupX   = CENTER_X - (c.getWidth() / 2);
            int cupTopY = myBase - (c.getHeight() * SCALE);

            c.setPosition(cupX, cupTopY);
            c.makeVisible();

            if (c.hasLid()) {
                c.getLid().setPosition(cupX, cupTopY - SCALE);
                c.getLid().makeVisible();
            }
        }
    }

    /**
     * Marca la operación como fallida y muestra mensaje si el simulador es visible.
     *
     * @param message mensaje de error
     */
    private void fail(String message) {
        ok = false;
        if (visible) {
            JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}