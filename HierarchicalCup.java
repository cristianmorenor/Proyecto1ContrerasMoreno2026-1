package stackingItems;

/**
 * Taza de tipo <b>hierarchical</b>: al entrar a la torre va desplazando
 * todos los objetos de menor tamaño (número menor). Si logra llegar al
 * fondo de la torre, no se deja quitar.
 *
 * Distinción visual: fondo de color amarillo independientemente del color
 * base, y paredes del color normal de la taza.
 *
 * @author David Contreras y Cristian Moreno
 * @version 2.0
 */
public class HierarchicalCup extends Cup {

    private boolean atBottom;

    /**
     * Crea una taza hierarchical.
     *
     * @param number número de la taza
     * @param width  ancho visual en píxeles
     */
    public HierarchicalCup(int number, int width) {
        super(number, width);
        atBottom = false;
        // Fondo amarillo para distinguirla visualmente
        fondo.changeColor("yellow");
    }

    /** @return "hierarchical" */
    @Override
    public String getType() { return "hierarchical"; }

    /**
     * La lógica de inserción hierarchical (desplazar tazas menores) es
     * manejada directamente por Tower.pushHierarchical. Este método
     * existe solo para satisfacer el contrato abstracto de Cup.
     *
     * @param tower la torre donde se inserta
     */
    @Override
    public void onPush(Tower tower) {
        tower.addCupAtTop(this);
    }

    /**
     * Marca si esta taza se encuentra en el fondo de la torre.
     * Cuando está en el fondo, no puede ser removida.
     *
     * @param atBottom true si está en el fondo
     */
    public void setAtBottom(boolean atBottom) {
        this.atBottom = atBottom;
    }

    /**
     * Indica si la taza está en el fondo de la torre.
     *
     * @return true si está en el fondo
     */
    public boolean isAtBottom() { return atBottom; }
}