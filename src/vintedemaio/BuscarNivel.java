package vintedemaio;

import java.util.LinkedList;
import java.util.Queue;

public class BuscarNivel {
    public static void buscaNivel(Node raiz) {
        if (raiz == null) return;

        Queue<Node> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            Node noAtual = fila.poll();
            System.out.print(noAtual.valor + " ");

            if (noAtual.esquerda != null) fila.add(noAtual.esquerda);
            if (noAtual.direita != null) fila.add(noAtual.direita);
        }
    }
}
