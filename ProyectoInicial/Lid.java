
/**
 * Clase lid( tapa) que cubre la cup(taza)
 * Cuanta con un numero/identificador que debe coincidir con el numero de la taza
 * Se representa a partir de la clase Triangle de shapes.
 * @author Contreras-Moreno
 * @version 1.0 Fecha-Febrero 15 2026
 */
public class Lid
{
    private int number;
    private int height;
    private Triangle triangle;
    private boolean visible;
    private int x;
    private int y;
    private String color;
    private static final int DEFAULT_X = 140;
    private static final int DEFAULT_Y = 15;

    

    /**
     * Constructor for objects of class Lid
     * @param el parametro es el numero de la tapa
     * El numero es su identificador
     */
    public Lid()
    {
        this.number = number;
        this.height = 1;
        this.triangle = new Triangle();
        this.visible = false;
        this.x = DEFAULT_X;
        this.y = DEFAULT_Y;
        this.triangle.changeColor(this.color);    
    }

    /**
     * Metodo que retonra el número de la tapa (identificador)
     * Retorna el numero de la tapa 
     */
    public int getNumber(){
        return number;
    
    }
    
    /**
     * Metodo que retorna la altura de la tapa
     * Retorno el entero
     */
    public int getHeight(){
        return height;
    }
    
    /**
     * Retorna el color de la tapa (Lid)
     */
    public String getColor(){
        return color;
    }
    
    /**
     * Metodo para cambiar la posicion visual de la tapa 
     * @param 1 es la de la posicion nueva en x
     * @param 2 es la de la posicion nueva en y
     */
    public void setPosition( int newX, int newY){  
        triangle.moveHorizontal(newX - x);
        triangle.moveVertical(newY - y);
        x = newX;
        y = newY;     
    }
    
    /**
     * Metodo para hacer visible ña tapa, se dejó por default como invisible
     */
    
    public void makeVisible(){
        if (!visible) {
            triangle.makeVisible();
            visible = true;
        }  
    }
    
    /**
     * MEtodo que hace invisible la tapa
     */
    public void makeInvisible(){
        if (visible) {
            triangle.makeInvisible();
            visible = false;
        }
    }
    
    
}