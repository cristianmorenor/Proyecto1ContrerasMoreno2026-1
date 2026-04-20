package stackingitems;

/**
 * Taza de tipo <b>greedy</b>: al entrar a la torre, intenta robar la tapa
 * de alguna taza que ya esté en la torre, tomando la primera disponible
 * que pueda ser removida.
 *
 * Distinción visual: paredes de color naranja.
 *
 * @author David Contreras y Cristian Moreno
 * @version 2.0
 */
public class GreedyCup extends Cup {

    /**
     * Crea una taza greedy con el número y ancho dados.
     *
     * @param number número de la taza
     * @param width  ancho visual en píxeles
     */
    public GreedyCup(int number, int width) {
        super(number, width);
        // Color naranja para distinguirla visualmente
        paredIzq.changeColor("orange");
        paredDer.changeColor("orange");
    }

    /** @return "greedy" */
    @Override
    public String getType() { return "greedy"; }

    /**
     * Al entrar a la torre, se agrega normalmente y luego intenta robar
     * la primera tapa disponible (removible) de cualquier otra taza.
     *
     * @param tower la torre donde se inserta
     */
    @Override
    public void onPush(Tower tower) {
        // Se agrega normalmente en la cima
        tower.addCupAtTop(this);

        // Intenta obtener una tapa si no tiene
        if (!this.hasLid()) {
            for (Cup c : tower.getCups()) {
                // No robarse a sí misma
                if (c == this) continue;

                if (c.hasLid()) {
                    Lid lid = c.getLid();

                    // Solo si la tapa puede ser removida
                    if (lid.canBeRemoved(tower, c)) {
                        c.removeLid();
                        this.setLid(lid);
                        break;
                    }
                }
            }
        }
    }
}