package stackingitems;

/**
 * Tapa de tipo normal: puede entrar y salir libremente de la torre.
 *
 * @author David Contreras y Cristian Moreno
 * @version 2.0
 */
public class NormalLid extends Lid {

    /**
     * Crea una tapa normal para la taza número n.
     *
     * @param number número de la taza compañera
     * @param width  ancho en píxeles
     */
    public NormalLid(int number, int width) {
        super(number, width);
    }

    /** @return "normal" */
    @Override
    public String getType() { return "normal"; }

    /**
     * Una tapa normal siempre puede ser agregada.
     *
     * @return true
     */
    @Override
    public boolean canBeAdded(Tower tower, Cup cup) {
        return true;
    }

    /**
     * Una tapa normal siempre puede ser removida.
     *
     * @return true
     */
    @Override
    public boolean canBeRemoved(Tower tower, Cup cup) {
        return true;
    }
}