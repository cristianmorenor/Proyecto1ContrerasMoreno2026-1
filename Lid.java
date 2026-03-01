package stackingItems;

import shapes.Rectangle;

/**
 * Representa la tapa de una taza.
 * Tiene altura fija de 1 cm y el mismo ancho que su taza asociada.
 *
 * @David Contreras Y Cristian Moreno
 * @version 1.0
 */
public class Lid {

    private int number;
    private int width;
    private Rectangle rectangle;

    private int xPosition;
    private int yPosition;

    private static final int HEIGHT       = 1;   // altura en cm
    private static final int SCALE        = 5;   // px por cm
    private static final int RECT_DEFAULT = 20;  // posición por defecto del Rectangle en BlueJ

    /**
     * Crea una tapa para la taza número n con el ancho dado.
     *
     * @param number número de la taza a la que pertenece
     * @param width  ancho en píxeles (debe coincidir con el de la taza)
     */
    public Lid(int number, int width) {
        this.number = number;
        this.width  = width;

        rectangle = new Rectangle();
        rectangle.changeSize(HEIGHT * SCALE, width);

        // El Rectangle de BlueJ nace en (RECT_DEFAULT, RECT_DEFAULT)
        xPosition = RECT_DEFAULT;
        yPosition = RECT_DEFAULT;
    }

    /**
     * Asigna el color de la tapa (debe ser el mismo que el de su taza).
     *
     * @param color nombre del color
     */
    public void setColor(String color) {
        rectangle.changeColor(color);
    }

    /** @return número de la taza a la que pertenece esta tapa */
    public int getNumber() { return number; }

    /** @return ancho de la tapa en píxeles */
    public int getWidth()  { return width;  }

    /** @return altura de la tapa en cm */
    public int getHeight() { return HEIGHT; }

    /**
     * Mueve la tapa de modo que su esquina superior-izquierda quede en (x, y).
     *
     * @param x coordenada X
     * @param y coordenada Y
     */
    public void setPosition(int x, int y) {
        int deltaX = x - xPosition;
        int deltaY = y - yPosition;

        rectangle.moveHorizontal(deltaX);
        rectangle.moveVertical(deltaY);

        xPosition = x;
        yPosition = y;
    }

    /** Hace visible la tapa. */
    public void makeVisible()   { rectangle.makeVisible();   }

    /** Oculta la tapa. */
    public void makeInvisible() { rectangle.makeInvisible(); }
}