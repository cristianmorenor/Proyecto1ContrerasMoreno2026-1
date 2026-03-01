package stackingItems;

import shapes.Rectangle;
import java.util.ArrayList;

/**
 * Dibuja las marcas de centímetros a la izquierda de la torre.
 * Ticks cortos cada 1 cm y ticks largos cada 5 cm, sin números.
 *
 * @David Contreras y Cristian Moreno
 * @version 2.0
 */
public class TowerBackground {

    private static final int SCALE      = 8;   // px por cm
    private static final int TICK_H     = 1;   // alto del tick en px
    private static final int TICK_SHORT = 5;   // ancho tick normal
    private static final int TICK_LONG  = 10;  // ancho tick cada 5 cm
    private static final int RECT_DEFAULT = 20; // offset por defecto de BlueJ

    private int leftX;  // borde izquierdo de la torre
    private int baseY;  // piso de la torre
    private int maxHeight;

    private ArrayList<Rectangle> ticks;

    /**
     * @param leftX     coordenada X del borde izquierdo de la torre
     * @param baseY     coordenada Y del piso de la torre
     * @param maxHeight altura máxima en cm
     */
    public TowerBackground(int leftX, int baseY, int maxHeight) {
        this.leftX     = leftX;
        this.baseY     = baseY;
        this.maxHeight = maxHeight;
        this.ticks     = new ArrayList<>();
        buildTicks();
    }

    /** Crea un tick por cada cm de altura. */
    private void buildTicks() {
        for (int cm = 0; cm <= maxHeight; cm++) {
            int tickY     = baseY - (cm * SCALE);
            int tickWidth = (cm % 5 == 0) ? TICK_LONG : TICK_SHORT;

            Rectangle tick = new Rectangle();
            tick.changeColor("black");
            tick.changeSize(TICK_H, tickWidth);
            // Posicionar justo a la izquierda del borde de la torre
            tick.moveHorizontal(leftX - tickWidth - RECT_DEFAULT);
            tick.moveVertical(tickY - RECT_DEFAULT);
            ticks.add(tick);
        }
    }

    /** Hace visibles las marcas. */
    public void makeVisible() {
        for (Rectangle t : ticks) t.makeVisible();
    }

    /** Oculta las marcas. */
    public void makeInvisible() {
        for (Rectangle t : ticks) t.makeInvisible();
    }
}