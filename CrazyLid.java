package stackingItems;

/**
 * Tapa de tipo <b>crazy</b>: en lugar de ubicarse encima de su taza,
 * se posiciona como base de la torre (en la parte inferior).
 *
 * Distinción visual: color magenta.
 *
 * @author David Contreras y Cristian Moreno
 * @version 1.0
 */
public class CrazyLid extends Lid {

    /**
     * Crea una tapa crazy para la taza número n.
     *
     * @param number número de la taza compañera
     * @param width  ancho en píxeles
     */
    public CrazyLid(int number, int width) {
        super(number, width);
        // Color magenta para distinguirla visualmente
        rectangle.changeColor("magenta");
    }

    /** @return "crazy" */
    @Override
    public String getType() { return "crazy"; }
}