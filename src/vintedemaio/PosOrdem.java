package vintedemaio;

public class PosOrdem {
    public void posOrdem(Node raiz) {
        if (raiz != null) {
            posOrdem(raiz.esquerda);
            posOrdem(raiz.direita);
            System.out.print(raiz.valor + " ");
        }
    }
}