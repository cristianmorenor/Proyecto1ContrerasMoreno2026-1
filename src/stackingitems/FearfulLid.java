package stackingitems;

/**
 * Tapa de tipo <b>fearful</b>: tiene dos comportamientos especiales:
 * <ul>
 *   <li>No puede entrar a la torre si su taza compañera no está en ella.</li>
 *   <li>Si está tapando a su taza, no puede salir (no puede ser removida).</li>
 * </ul>
 *
 * Distinción visual: color rojo independientemente del color de su taza.
 *
 * @author David Contreras y Cristian Moreno
 * @version 1.0
 */
public class FearfulLid extends Lid {

    /**
     * Crea una tapa fearful para la taza número n.
     *
     * @param number número de la taza compañera
     * @param width  ancho en píxeles
     */
    public FearfulLid(int number, int width) {
        super(number, width);
        // Color rojo para distinguirla visualmente
        rectangle.changeColor("red");
    }

    /** @return "fearful" */
    @Override
    public String getType() { return "fearful"; }

    /**
     * Una tapa fearful solo puede entrar si su taza compañera está en la torre.
     *
     * @param tower la torre donde se intenta agregar
     * @param cup   la taza compañera (puede ser null si no está en la torre)
     * @return true solo si la taza compañera está presente
     */
    @Override
    public boolean canBeAdded(Tower tower, Cup cup) {
        return cup != null;
    }

    /**
     * Una tapa fearful no puede salir mientras esté tapando a su taza.
     *
     * @return false (nunca puede ser removida mientras tapa a su taza)
     */
    @Override
    public boolean canBeRemoved(Tower tower, Cup cup) {
        return false;
    }
}