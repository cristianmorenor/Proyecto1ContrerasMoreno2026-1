



import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase princiopal Torre (Tower)
 * Se implementan los metodos pedidos por el enunciado 
 * Clase que controla las demas clases 
 * Para la parte visual definimos que towwerFrame será el marco denla torre
 * cmMarks marcas de los centimetros a medida que crece la torre ( copmo una regla )
 * @author Contreras-Moreno  
 * @version 1.0 Fecha-Febrero 15 2026
 */
public class Tower
{
    private int width;
    private int maxHeight;
    private boolean visible;
    private boolean ok;
    private ArrayList<Cup> cups;
    private ArrayList<Lid> lids;
    private ArrayList<Object> stack;
    private Rectangle towerFrame;
    private int framePosX;
    private int framePosY;
    private ArrayList<Rectangle> cmMarks;
    private static final int CANVAS_W = 300;
    private static final int CANVAS_H = 300;
     
  
    /**
     * Constructor for objects of class Tower
     * @param parametro1 es la altura maxima de la torre
     * @param parametro2 es el ancho maxima de la torre
     */
    public Tower(int width, int maxHeight)
    {
        this.width = width;
        this.maxHeight = maxHeight;
        this.visible = false;
        this.ok = true;
        this.cups = new ArrayList<Cup>();
        this.lids = new ArrayList<Lid>();
        this.stack = new ArrayList<Object>();
        this.towerFrame = new Rectangle();
        this.towerFrame.changeColor("black");
        this.towerFrame.makeInvisible();
        this.framePosX = 70;
        this.framePosY = 15;
        this.cmMarks = new ArrayList<Rectangle>();      
    }
    
    /**
     * MEtodo que adiciona la taza a la cima de la torre
     * Como dice el enunciado solo puede existir una taza por numero
     * @param el numero identificador de la taza
     */
    public void pushCup(int i){
        if(getCup(i) != null){
            fail("Ya existe esta taza " + i);
            return;
            
        }
        Cup c = new Cup(i);
        cups.add(c);
        ok = true;
    }
    /**
     * Metodo que elimina la taza que esta en la cima de la torre
     * Si la taza esta tapada con una lid, se eliminan las dos
     */
    public void popCup() {}
    /**
     * Metodo que elimina la taza de numero especifico 
     * De igual forma si esta tapada se eliminaa junto a su lid
     */
    public void removeCup(){}
    /**
     * Adiciona una tapa a la cima de la torre 
     * Si la taza que esta debajo es el mismo numero, esta queda tapada
     */
    public void pushLid(){}
    /**
     * Elimina la tapa que esta más arriba de la torre
     * Si esta tapa esta tapando la misma tapa de su numero se eliminan ambas 
     */
    public void popLid() {}
    /**
     * Metodo que remueve la tapa de numero especifico
     * Si esta tapando una taza con mismo numero se eliminan ambas
     */
    public void removeLid(){}
    /**
     * MEtodo que ordena de mayor a menor los elementos de la torre de acuerdo con su numero
     * Si para un mismo número existe taza y tapa lo que se hace es que se coloca tapa sobre taza.
     */
    public void orderTower() {}
    
    /**
     * MEtodo que invierte el orden los elementods que estan en la torre
     * si una tapa esta tapando una taza se muven juntas
     */
    public void reverseTower() {}
    
    /**
     * Metodo que consulta la altura total de los elementos que hay apilados
     * @retunr altura en centimetros
     */
     public void height() {}
    
    /**
     * Metod que hace visible el simulador
     */
    public void makeVisible() {}
    /**
     * Metodo que hace invisible el simulador
     */
    public void makeInvisible(){}
    
    /**
     * MEtodo que indica si la última operacion que se realizo fue hecha correctamente
     */
    public boolean ok() {
        return ok;
    }
    
    /**
     * Metodo que muestra un mensaje al haber una operacion fallida
     * @param es el mensaje que va a mostrar
     */
    private void fail(String msg) {
        ok = false;
        if (visible) {
            JOptionPane.showMessageDialog(null, msg, "Acción no válida", JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * MEtodo para obtener la cup y su identificador
     */
    private Cup getCup(int n) {
        for (int i = 0; i < cups.size(); i++) {
            Cup c = cups.get(i);
            if (c.getNumber() == n) return c;
        }
        return null;
    }

   
}
