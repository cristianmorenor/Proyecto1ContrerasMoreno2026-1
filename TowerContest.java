package Contest;
import stackingItems.Tower;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Resuelve y simula el problema de la maratón ICPC 2025 "Stacking Cups".
 *
 * Requisito 14 — solve(n, h): dado el número de tazas n y la altura objetivo h,
 * retorna las alturas en orden de colocación o "impossible".
 *
 * Requisito 15 — simulate(n, h): simula visualmente la solución con Tower.
 * Tower se usa SOLO para simular, nunca para resolver.
 *
 * MODELO FÍSICO:
 * Al colocar la taza c sobre la torre:
 *  - Si c es mayor que la cima actual, la envuelve: base = max(piso_contenedor, tope_envuelta).
 *  - Si c es menor, va dentro de la cima: base = piso del contenedor.
 * Altura final = max(tope de todas las tazas).
 *
 * ALTURAS ALCANZABLES para n tazas: [2n-1, (n-1)^2+1] excepto (n-1)^2-1.
 *
 * ALGORITMO:
 * 1. h == 2n-1 -> orden ascendente [1..n].
 * 2. h fuera de rango o igual al unico imposible -> "impossible".
 * 3. Probar profundidad d=1,2,...: reservar d-1 tazas como rampa,
 *    encontrar subconjunto de {1..n-d} con suma de alturas = h-d,
 *    construir orden [n, rampa desc, cadena asc intercalada con no-cadena desc].
 *
 * @author David Contreras y Cristian Moreno
 * @version 1.0
 */
public class TowerContest {

    
    //  REQ 14 — solve                                                     //
    /**
     * Resuelve el problema de la maraton.
     * La entrada y salida corresponden exactamente al enunciado del ICPC.
     *
     * @param n numero de tazas (1 <= n <= 200000)
     * @param h altura objetivo
     * @return alturas separadas por espacio en orden de colocacion, o "impossible"
     */
    public static String solve(int n, int h) {
        int[] order = findOrder(n, h);
        if (order == null) {
            return "impossible";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < order.length; i++) {
            if (i > 0) sb.append(' ');
            sb.append(2 * order[i] - 1);
        }
        return sb.toString();
    }

    
    //  REQ 15 — simulate                                                 
    /**
     * Simula visualmente la solucion usando Tower.
     * Tower se usa SOLO para visualizar, nunca para calcular la solucion.
     * La entrada corresponde a lo definido en el problema de la maraton.
     * La salida es la imagen de la solucion, si existe y es posible graficarla;
     * en caso contrario presenta un mensaje indicandolo.
     *
     * @param n numero de tazas
     * @param h altura objetivo
     */
    public static void simulate(int n, int h) {
        int[] order = findOrder(n, h);

        if (order == null) {
            javax.swing.JOptionPane.showMessageDialog(
                null,
                "No existe solucion para n=" + n + ", h=" + h + ".\nResultado: impossible",
                "Stacking Cups - Sin solucion",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        // La altura visual maxima es la suma de todas las alturas de las tazas = n^2
        // (Tower.height() acumula las alturas, no mide la altura visual final)
        int totalHeight = h;

        if (n > 20 || totalHeight > 200) {
            javax.swing.JOptionPane.showMessageDialog(
                null,
                "Solucion: " + solve(n, h)
                    + "\n\nNo es posible graficarla (n=" + n
                    + ", altura=" + totalHeight + " excede el canvas).",
                "Stacking Cups - Solucion encontrada",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        // maxHeight debe ser n^2 porque Tower.height() acumula las alturas
        // de todas las tazas colocadas, no la altura visual final de la torre.
        // La suma maxima posible es 1+3+...+(2n-1) = n^2.
        Tower tower = new Tower(200, n * n);
        tower.makeVisible();
        for (int cupNum : order) {
            tower.pushCup(cupNum);
            pauseMs(400);
        }
        javax.swing.JOptionPane.showMessageDialog(
            null,
            "Simulacion completada. Altura: " + totalHeight,
            "Stacking Cups - Simulacion",
            javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
    }

   
    //logica del solver                                     
    /**
     * Busca el orden de colocacion para lograr la altura h con n tazas.
     *
     * @param n numero de tazas
     * @param h altura objetivo
     * @return arreglo de numeros de taza en orden, o null si imposible
     */
    private static int[] findOrder(int n, int h) {
        if (n == 1) {
            return (h == 1) ? new int[]{1} : null;
        }

        int minH = 2 * n - 1;
        if (h < minH) return null;

        if (h == minH) {
            int[] order = new int[n];
            for (int i = 0; i < n; i++) order[i] = i + 1;
            return order;
        }

        // maxH = (n-1)^2 + 1  — puede superar Integer.MAX_VALUE para n grande,
        // pero como h es int, basta verificar que h no supera ese valor.
        // Usamos double solo para esta comparacion de rango.
        double maxH = 1.0 + (double)(n - 1) * (n - 1);
        if (h > maxH)          return null;
        if (n >= 4 && h == maxH - 2) return null;   // unico imposible del rango

        for (int depth = 1; depth < n; depth++) {
            int    chainMax   = n - depth;
            int    target     = h - depth;
            if (target < 0) break;
            double chainMaxSq = (double) chainMax * chainMax;
            if (target > chainMaxSq) continue;

            List<Integer> chain = subsetSumFind(target, chainMax);
            if (chain != null) {
                return buildOrder(n, depth, chainMax, chain);
            }
        }
        return null;
    }

    /**
     * Encuentra un subconjunto de tazas {1..k} cuyas alturas (1,3,...,2k-1) suman target.
     * Los unicos valores no alcanzables en [0, k^2] son: 2  y  k^2-2  (para k >= 2).
     *
     * @param target suma objetivo
     * @param k      taza maxima disponible
     * @return lista de numeros de taza, o null si imposible
     */
    private static List<Integer> subsetSumFind(int target, int k) {
        if (k == 0)  return (target == 0) ? new ArrayList<>() : null;
        double kSq = (double) k * k;
        if (target < 0 || target > kSq)   return null;
        if (k >= 2 && target == 2)         return null;
        if (k >= 2 && target == kSq - 2)   return null;
        if (target == 0) return new ArrayList<>();

        List<Integer> chosen    = new ArrayList<>();
        int           remaining = target;

        for (int i = k; i >= 1; i--) {
            int    v     = 2 * i - 1;
            int    subK  = i - 1;
            if (v <= remaining) {
                int    newRem  = remaining - v;
                double subKSq  = (double) subK * subK;
                boolean ok = true;
                if (newRem > subKSq)                         ok = false;
                else if (subK >= 2 && newRem == 2)           ok = false;
                else if (subK >= 2 && newRem == subKSq - 2)  ok = false;
                if (ok) {
                    chosen.add(i);
                    remaining = newRem;
                    if (remaining == 0) break;
                }
            }
        }
        return (remaining == 0) ? chosen : null;
    }

    /**
     * Construye el orden de colocacion.
     *
     * Estructura:
     *   [n] + [rampa: n-1, n-2, ..., chainMax+1 desc]
     *       + [cadena asc intercalada con no-cadena desc por intervalos]
     *
     * @param n        total de tazas
     * @param depth    profundidad elegida
     * @param chainMax tazas disponibles para la cadena {1..chainMax}
     * @param chain    tazas de la cadena
     * @return arreglo de n numeros de taza
     */
    private static int[] buildOrder(int n, int depth, int chainMax, List<Integer> chain) {
        boolean[] inChain = new boolean[chainMax + 1];
        for (int c : chain) inChain[c] = true;

        List<Integer> result = new ArrayList<>();
        result.add(n);

        for (int r = n - 1; r > chainMax; r--) {
            result.add(r);
        }

        List<Integer> nonChainSmall = new ArrayList<>();
        for (int c = 1; c <= chainMax; c++) {
            if (!inChain[c]) nonChainSmall.add(c);
        }

        List<Integer> chainSorted = new ArrayList<>(chain);
        Collections.sort(chainSorted);

        int lastChain = 0;
        for (int ci : chainSorted) {
            result.add(ci);
            List<Integer> between = new ArrayList<>();
            for (int nc : nonChainSmall) {
                if (nc > lastChain && nc < ci) between.add(nc);
            }
            Collections.sort(between, Collections.reverseOrder());
            result.addAll(between);
            lastChain = ci;
        }

        List<Integer> afterChain = new ArrayList<>();
        for (int nc : nonChainSmall) {
            if (nc > lastChain) afterChain.add(nc);
        }
        Collections.sort(afterChain, Collections.reverseOrder());
        result.addAll(afterChain);

        int[] arr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) arr[i] = result.get(i);
        return arr;
    }


    
    //Utilidades                                           
    private static void pauseMs(int ms) {
        double start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < ms) {
            // espera activa
        }
    }
}