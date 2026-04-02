package stackingItems;

/**
 * Taza de tipo normal: comportamiento estándar al entrar a la torre.
 *
 * @author David Contreras y Cristian Moreno
 * @version 2.0
 */
public class NormalCup extends Cup {

    /**
     * Crea una taza normal con el número y ancho dados.
     *
     * @param number número de la taza
     * @param width  ancho visual en píxeles
     */
    public NormalCup(int number, int width) {
        super(number, width);
    }

    /** @return "normal" */
    @Override
    public String getType() { return "normal"; }

    /**
     * Comportamiento al entrar: simplemente se agrega en la cima de la torre.
     *
     * @param tower la torre donde se inserta
     */
    @Override
    public void onPush(Tower tower) {
        tower.addCupAtTop(this);
    }
}