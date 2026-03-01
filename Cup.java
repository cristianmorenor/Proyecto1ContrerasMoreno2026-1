package stackingItems;

import shapes.Rectangle;

/**
 * Representa una taza cilíndrica del simulador.
 *
 * @David Contreras y Cristian Moreno
 * @version 2.0
 */
public class Cup {

    private int number;
    private int height;
    private int width;
    private Lid lid;

    private Rectangle paredIzq;
    private Rectangle paredDer;
    private Rectangle fondo;

    private String color;
    private int xPosition;
    private int yPosition;

    static final int SCALE        = 8;
    static final int WALL         = 6;
    private static final int RECT_DEFAULT = 20;

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
        fondo.changeSize(SCALE, width);  // fondo = 1cm de alto

        xPosition = RECT_DEFAULT;
        yPosition = RECT_DEFAULT;

        paredDer.moveHorizontal(width - WALL);
        fondo.moveVertical((height * SCALE) - SCALE);  // fondo al pie de la taza
    }

    private String generateColor() {
        String[] colors = {"green", "red", "blue", "magenta", "yellow"};
        return colors[(number - 1) % colors.length];
    }

    public int getNumber() { return number; }
    public int getHeight() { return height; }
    public int getWidth()  { return width;  }
    public String getColor() { return color; }

    public void setLid(Lid lid) {
        this.lid = lid;
        fondo.changeColor("white");
    }

    public void removeLid() {
        this.lid = null;
        fondo.changeColor(color);
    }

    public boolean hasLid() { return lid != null; }
    public Lid getLid() { return lid; }

    public void setPosition(int x, int yTop) {
        int deltaX = x - xPosition;
        int deltaY = yTop - yPosition;

        paredIzq.moveHorizontal(deltaX);
        paredIzq.moveVertical(deltaY);
        paredDer.moveHorizontal(deltaX);
        paredDer.moveVertical(deltaY);
        fondo.moveHorizontal(deltaX);
        fondo.moveVertical(deltaY);

        xPosition = x;
        yPosition = yTop;
    }

    public void makeVisible() {
        paredIzq.makeVisible();
        paredDer.makeVisible();
        fondo.makeVisible();
    }

    public void makeInvisible() {
        paredIzq.makeInvisible();
        paredDer.makeInvisible();
        fondo.makeInvisible();
        if (lid != null) lid.makeInvisible();
    }
}