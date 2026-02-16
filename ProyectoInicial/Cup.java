
import java.util.*;

/**
 * Representa una copa identificada por un número
 * 
 * @author Contreras-Moreno 
 * @version 1.0 Fecha-Febrero 14 2026
 */
public class Cup
{
    private int number;
    private int height;
    private Lid lid;
    private Circle circle;
    private boolean visible;
    private int x;
    private int y;
    private static final int DEFAULT_X = 20;
    private static final int DEFAULT_Y = 15;
    private String color;

    /**
     * Constructor de la clase cup con base en el enunciado de laa maratón
     * @param numero/identificador de la taza
     */
    public Cup(int number)
    {
        this.number = number;
        this.height = (2 * number) - 1;
        this.lid = null;
        this.circle = new Circle();
        this.visible = false;
        this.x = DEFAULT_X;
        this.y = DEFAULT_Y;
        this.circle.changeColor(this.color);   
    }
    
    /**
     * Metodo get para obtener el numero de la taza
     */
    public int getNumber(){
        return number;
    }
    
    /**
     * Metodo que asigna que tapa (lid) cubre la taza
     */
    public void setLid(Lid lid){
        this.lid =lid;
    }
    
    /**
     * Metodo para quitar la tapa de la taza 
     */
    public void removeLid(){
        this.lid =null;
    }
    
    /**
     * Metodo para saber si la taza estaba tapada 
     * Retrona un True si esta tapada, de lo contrario retorna un False
     */
    public boolean hasLid(){
    return lid != null;
    }
    
    /**
     * Metodo para tener el color de la ctaza que se tiene
     * @return retorna el color de acuerdo a lo que se tiene en shapes
     */
    public String getColor(){
        return color;
    }
    
    /**
     * Metodo para mover la taza, enfocado a actualizar la posición visual 
     * @param  como primer parámetro tiene la nueva coordenada en X y Y
     * No hay retorno
     */
    public void setPosition(int newX, int newY){
    circle.moveHorizontal(newX -x);
    circle.moveVertical(newY - y);
    x = newX;
    y = newY;
    }
    
    /**
     * Metodo alineado con sahpes para hacer visible la taza en la pantalla
     * No hay retorno
     */
    
    public void makeVisible(){
    
    if (!visible){
    circle.makeVisible();
    visible = true;
        }
    }
    
    /**
     * Metodo que hce invisbible la taza 
     * No jay retorno 
     */
    
    public void makeInvisible(){
        if(visible){
            circle.makeInvisible();
            visible = false;
        }
    
    }
    
    
    
    
    
    
    
    
    

    
    
}