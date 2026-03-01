

public class Cup {

    private int number;
    private int height;
    private Rectangle rectangle;
    private boolean visible;
    private int x;
    private int y;
    private String color;
    private Lid lid;

    private static final int DEFAULT_X = 120;
    private static final int DEFAULT_Y = 250;

    public Cup(int number) {
        this.number = number;
        this.height = (2 * number) - 1;
        this.rectangle = new Rectangle();
        this.visible = false;
        this.x = DEFAULT_X;
        this.y = DEFAULT_Y;
        this.lid = null;

        this.color = randomColor();
        rectangle.changeColor(color);
        rectangle.changeSize(height * 5, 40); 
    }

    private String randomColor() {
        String[] colors = {"red","blue","green","yellow","magenta"};
        return colors[number % colors.length];
    }

    public int getNumber() {
        return number;
    }

    public int getHeight() {
        return height;
    }

    public String getColor() {
        return color;
    }

    public boolean hasLid() {
        return lid != null;
    }

    public void setLid(Lid lid) {
        this.lid = lid;
    }

    public void removeLid() {
        this.lid = null;
    }

    public void setPosition(int newX, int newY) {
        rectangle.moveHorizontal(newX - x);
        rectangle.moveVertical(newY - y);
        x = newX;
        y = newY;

        if(lid != null) {
            lid.setPosition(newX, newY - 5);
        }
    }

    public void makeVisible() {
        if(!visible) {
            rectangle.makeVisible();
            visible = true;
        }
    }

    public void makeInvisible() {
        if(visible) {
            rectangle.makeInvisible();
            visible = false;
        }
    }
}
