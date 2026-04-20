package shapes;

/**
 * Un rectángulo que puede moverse y dibujarse en el canvas.
 *
 * @author Michael Kolling and David J. Barnes (refactorizado)
 * @version 2.0
 */
public class Rectangle extends AShape {

    public static int EDGES = 4;

    private int height;
    private int width;

    /**
     * Crea un rectángulo en la posición por defecto.
     */
    public Rectangle() {
        super(70, 15, "magenta");
        height = 30;
        width  = 40;
    }

    /**
     * Cambia el tamaño del rectángulo.
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
            canvas.draw(this, color,
                new java.awt.Rectangle(xPosition, yPosition, width, height));
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