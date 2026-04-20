package stackingitems;

import shapes.Rectangle;

/**
 * Clase abstracta que representa una taza.
 *
 * @author David Contreras y Cristian Moreno
 * @version 2.0
 */
public abstract class Cup {

    protected int number;
    protected int height;
    protected int width;
    protected Lid lid;

    protected Rectangle paredIzq;
    protected Rectangle paredDer;
    protected Rectangle fondo;

    protected String color;
    protected int xPosition;
    protected int yPosition;

    protected static final int SCALE       = 8;
    protected static final int WALL        = 6;
    protected static final int RECT_DEFAULT = 20;

    /**
     * Crea una taza con el número y ancho dados.
     *
     * @param number número de la taza
     * @param width  ancho visual en píxeles
     */
    public Cup(int number, int width) {
        this.number = number;
        this.height = (2 * number) - 1;
        this.width  = width;
        this.lid    = null;

        color = generateColor();

        paredIzq = new Rectangle();
        paredDer = new Rectangle();
        fondo    = new Rectangle();

        paredIzq.changeColor(color);
        paredDer.changeColor(color);
        fondo.changeColor(color);

        paredIzq.changeSize(height * SCALE, WALL);
        paredDer.changeSize(height * SCALE, WALL);
        fondo.changeSize(SCALE, width);

        xPosition = RECT_DEFAULT;
        yPosition = RECT_DEFAULT;

        paredDer.moveHorizontal(width - WALL);
        fondo.moveVertical((height * SCALE) - SCALE);
    }

    /** Genera un color según el número de la taza. */
    protected String generateColor() {
        String[] colors = {"green", "red", "blue", "magenta", "yellow"};
        return colors[(number - 1) % colors.length];
    }

    /**
     * Comportamiento al ser insertada en la torre.
     * Cada subclase define su propia lógica.
     *
     * @param tower la torre donde se inserta
     */
    public abstract void onPush(Tower tower);

    /**
     * Retorna el tipo de la taza como cadena.
     * Las subclases deben sobreescribir este método.
     *
     * @return tipo de taza ("normal", "opener", "hierarchical", "greedy", etc.)
     */
    public String getType() { return "normal"; }

    /** @return número de la taza */
    public int getNumber() { return number; }

    /** @return altura en cm */
    public int getHeight() { return height; }

    /** @return ancho en píxeles */
    public int getWidth()  { return width;  }

    /** @return color base de la taza */
    public String getColor() { return color; }

    /**
     * Asigna una tapa a esta taza y marca el fondo en blanco.
     *
     * @param lid tapa a asignar
     */
    public void setLid(Lid lid) {
        this.lid = lid;
        fondo.changeColor("white");
    }

    /** Quita la tapa de esta taza y restaura el color del fondo. */
    public void removeLid() {
        this.lid = null;
        fondo.changeColor(color);
    }

    /** @return true si la taza tiene tapa */
    public boolean hasLid() { return lid != null; }

    /** @return la tapa de la taza, o null si no tiene */
    public Lid getLid() { return lid; }

    /**
     * Mueve la taza a la posición indicada.
     *
     * @param x    coordenada X
     * @param yTop coordenada Y del tope de la taza
     */
    public void setPosition(int x, int yTop) {
        int dx = x - xPosition;
        int dy = yTop - yPosition;

        paredIzq.moveHorizontal(dx);
        paredIzq.moveVertical(dy);
        paredDer.moveHorizontal(dx);
        paredDer.moveVertical(dy);
        fondo.moveHorizontal(dx);
        fondo.moveVertical(dy);

        xPosition = x;
        yPosition = yTop;
    }

    /** Hace visible la taza y su tapa si tiene. */
    public void makeVisible() {
        paredIzq.makeVisible();
        paredDer.makeVisible();
        fondo.makeVisible();
    }

    /** Oculta la taza y su tapa si tiene. */
    public void makeInvisible() {
        paredIzq.makeInvisible();
        paredDer.makeInvisible();
        fondo.makeInvisible();
        if (lid != null) lid.makeInvisible();
    }
}