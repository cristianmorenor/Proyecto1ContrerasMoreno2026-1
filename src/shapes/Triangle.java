package shapes;

import java.awt.Polygon;

/**
 * Un triángulo que puede moverse y dibujarse en el canvas.
 *
 * @author Michael Kolling and David J. Barnes (refactorizado)
 * @version 2.0
 */
public class Triangle extends AShape {

    public static int VERTICES = 3;

    private int height;
    private int width;

    /**
     * Crea un triángulo en la posición por defecto.
     */
    public Triangle() {
        super(140, 15, "green");
        height = 30;
        width  = 40;
    }

    /**
     * Cambia el tamaño del triángulo.
     *
     * @param newHeight nueva altura en píxeles (>= 0)
     * @param newWidth  nuevo ancho en píxeles (>= 0)
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width  = newWidth;
        draw();
    }

    @Override
    protected void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { xPosition, xPosition + (width / 2), xPosition - (width / 2) };
            int[] ypoints = { yPosition, yPosition + height, yPosition + height };
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
            canvas.wait(10);
        }
    }

    @Override
    protected void erase() {
        if (isVisible) {
            Canvas.getCanvas().erase(this);
        }
    }
}