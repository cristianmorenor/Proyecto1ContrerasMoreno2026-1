package shapes;

import java.awt.geom.Ellipse2D;

/**
 * Un círculo que puede moverse y dibujarse en el canvas.
 *
 * @author Michael Kolling and David J. Barnes (refactorizado)
 * @version 2.0
 */
public class Circle extends AShape {

    public static final double PI = 3.1416;

    private int diameter;

    /**
     * Crea un círculo en la posición por defecto.
     */
    public Circle() {
        super(20, 15, "blue");
        diameter = 30;
    }

    /**
     * Cambia el diámetro del círculo.
     *
     * @param newDiameter nuevo diámetro en píxeles (debe ser >= 0)
     */
    public void changeSize(int newDiameter) {
        erase();
        diameter = newDiameter;
        draw();
    }

    @Override
    protected void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new Ellipse2D.Double(xPosition, yPosition, diameter, diameter));
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