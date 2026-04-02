package stackingItems;

/**
 * Taza de tipo <b>opener</b>: al ser insertada en la torre, elimina
 * automáticamente todas las tapas que estén en la torre antes de entrar.
 *
 * Distinción visual: paredes de color negro.
 *
 * @author David Contreras y Cristian Moreno
 * @version 2.0
 */
public class OpenerCup extends Cup {

    /**
     * Crea una taza opener.
     *
     * @param number número de la taza
     * @param width  ancho visual en píxeles
     */
    public OpenerCup(int number, int width) {
        super(number, width);
        // Paredes negras para distinguirla visualmente
        paredIzq.changeColor("black");
        paredDer.changeColor("black");
    }

    /** @return "opener" */
    @Override
    public String getType() { return "opener"; }

    /**
     * Al entrar a la torre, la lógica opener (quitar tapas) ya fue ejecutada
     * por Tower.pushCup antes de llamar a onPush. Aquí solo se agrega en la cima.
     *
     * @param tower la torre donde se inserta
     */
    @Override
    public void onPush(Tower tower) {
        tower.addCupAtTop(this);
    }
}