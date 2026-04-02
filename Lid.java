package stackingItems;

import shapes.Rectangle;

/**
 * Representa la tapa base de una taza.
 * Clase base para los demás tipos de tapa.
 * Altura fija de 1 cm, mismo ancho que su taza asociada.
 *
 * @author David Contreras y Cristian Moreno
 * @version 2.0
 */
public class Lid {

    private int number;
    private int width;
    protected Rectangle rectangle;

    private int xPosition;
    private int yPosition;

    protected static final int HEIGHT       = 1;
    protected static final int SCALE        = 5;
    private   static final int RECT_DEFAULT = 20;

    /**
     * Crea una tapa para la taza número n con el ancho dado.
     *
     * @param number número de la taza a la que pertenece
     * @param width  ancho en píxeles
     */
    public Lid(int number, int width) {
        this.number = number;
        this.width  = width;

        rectangle = new Rectangle();
        rectangle.changeSize(HEIGHT * SCALE, width);

        xPosition = RECT_DEFAULT;
        yPosition = RECT_DEFAULT;
    }

    /**
     * Retorna el tipo de la tapa.
     * Las subclases sobreescriben este método.
     *
     * @return tipo de tapa ("normal", "fearful", "crazy", etc.)
     */
    public String getType() { return "normal"; }

    /**
     * Asigna el color de la tapa.
     *
     * @param color nombre del color
     */
    public void setColor(String color) {
        rectangle.changeColor(color);
    }

    /** @return número de la taza asociada */
    public int getNumber() { return number; }

    /** @return ancho en píxeles */
    public int getWidth()  { return width;  }

    /** @return altura en cm (siempre 1) */
    public int getHeight() { return HEIGHT; }

    /**
     * Indica si esta tapa puede ser agregada a la torre.
     * Por defecto retorna true; las subclases pueden restringirlo.
     *
     * @param tower la torre donde se intenta agregar
     * @param cup   la taza compañera
     * @return true si la tapa puede entrar
     */
    public boolean canBeAdded(Tower tower, Cup cup) {
        return true;
    }

    /**
     * Indica si esta tapa puede ser removida de la torre.
     * Por defecto retorna true; las subclases pueden restringirlo.
     *
     * @param tower la torre de donde se intenta remover
     * @param cup   la taza que actualmente tapa
     * @return true si la tapa puede salir
     */
    public boolean canBeRemoved(Tower tower, Cup cup) {
        return true;
    }

    /**
     * Mueve la tapa a la posición (x, y).
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