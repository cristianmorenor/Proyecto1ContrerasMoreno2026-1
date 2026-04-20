package shapes;
//clase para refactor de shapes
/**
 * Clase abstracta que representa una figura geométrica genérica.
 * Todas las figuras del paquete shapes heredan de esta clase.
 *
 * @author David Contreras y Cristian Moreno
 * @version 1.0
 */
public abstract class AShape {

    protected int xPosition;
    protected int yPosition;
    protected String color;
    protected boolean isVisible;

    /**
     * Constructor base: inicializa los atributos comunes.
     *
     * @param x      posición inicial en X
     * @param y      posición inicial en Y
     * @param color  color inicial
     */
    public AShape(int x, int y, String color) {
        this.xPosition = x;
        this.yPosition = y;
        this.color     = color;
        this.isVisible = false;
    }

    // ------------------------------------------------------------------ //
    //  MÉTODOS ABSTRACTOS (cada subclase los implementa)                  //
    // ------------------------------------------------------------------ //

    /** Dibuja la figura en el canvas. Solo actúa si isVisible es true. */
    protected abstract void draw();

    /** Borra la figura del canvas. Solo actúa si isVisible es true. */
    protected abstract void erase();

    // ------------------------------------------------------------------ //
    //  VISIBILIDAD                                                        //
    // ------------------------------------------------------------------ //

    /** Hace visible la figura. Si ya era visible, no hace nada. */
    public void makeVisible() {
        isVisible = true;
        draw();
    }

    /** Hace invisible la figura. Si ya era invisible, no hace nada. */
    public void makeInvisible() {
        erase();
        isVisible = false;
    }

    // ------------------------------------------------------------------ //
    //  MOVIMIENTO                                                         //
    // ------------------------------------------------------------------ //

    /** Mueve la figura 20 px a la derecha. */
    public void moveRight()  { moveHorizontal(20);  }

    /** Mueve la figura 20 px a la izquierda. */
    public void moveLeft()   { moveHorizontal(-20); }

    /** Mueve la figura 20 px hacia arriba. */
    public void moveUp()     { moveVertical(-20);   }

    /** Mueve la figura 20 px hacia abajo. */
    public void moveDown()   { moveVertical(20);    }

    /**
     * Mueve la figura horizontalmente una distancia arbitraria.
     *
     * @param distance distancia en píxeles (negativa = izquierda)
     */
    public void moveHorizontal(int distance) {
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Mueve la figura verticalmente una distancia arbitraria.
     *
     * @param distance distancia en píxeles (negativa = arriba)
     */
    public void moveVertical(int distance) {
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Mueve la figura horizontalmente de forma animada (un píxel por paso).
     *
     * @param distance distancia en píxeles
     */
    public void slowMoveHorizontal(int distance) {
        int delta = (distance < 0) ? -1 : 1;
        int steps = Math.abs(distance);
        for (int i = 0; i < steps; i++) {
            xPosition += delta;
            draw();
        }
    }

    /**
     * Mueve la figura verticalmente de forma animada (un píxel por paso).
     *
     * @param distance distancia en píxeles
     */
    public void slowMoveVertical(int distance) {
        int delta = (distance < 0) ? -1 : 1;
        int steps = Math.abs(distance);
        for (int i = 0; i < steps; i++) {
            yPosition += delta;
            draw();
        }
    }

    // ------------------------------------------------------------------ //
    //  COLOR                                                              //
    // ------------------------------------------------------------------ //

    /**
     * Cambia el color de la figura.
     *
     * @param newColor color nuevo. Válidos: "red","yellow","blue","green","magenta","black","white"
     */
    public void changeColor(String newColor) {
        color = newColor;
        draw();
    }

    /** @return el color actual de la figura */
    public String getColor() { return color; }
}