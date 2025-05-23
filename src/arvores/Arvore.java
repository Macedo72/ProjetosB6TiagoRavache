package arvores;

import java.util.LinkedList;
import java.util.Queue;

public class Arvore {
    Node raiz;

    public Arvore(Node raiz){
        this.raiz = raiz;
    }

    public Arvore() {

    }

    public void preOrdem(Node raiz){
        if (raiz != null){
            System.out.print(raiz.valor + " ");
            preOrdem(raiz.esquerda);
            preOrdem(raiz.direita);
        }
    }
    public void emOrdem(Node raiz){
        if (raiz != null){
            emOrdem(raiz.esquerda);
            System.out.print(raiz.valor + " ");
            emOrdem(raiz.direita);
        }
    }
    public void posOrdem(Node raiz){
        if (raiz != null){
            posOrdem(raiz.esquerda);
            posOrdem(raiz.direita);
            System.out.print(raiz.valor + " ");
        }
    }
    public int contarNos(Node raiz){
        if (raiz == null){
            return 0;
        }
        return 1 + contarNos(raiz.esquerda) + contarNos(raiz.direita);
    }
    public static void buscaNivel(Node raiz){
        if (raiz == null) return;
        Queue<Node> fila = new LinkedList<>();
        fila.add(raiz);
        while (!fila.isEmpty()){
            Node noAtual = fila.poll();
            System.out.print(noAtual.valor + " ");

            if (noAtual.esquerda != null) fila.add(noAtual.esquerda);
            if (noAtual.direita != null) fila.add(noAtual.direita);
        }
    }
}
