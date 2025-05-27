package arvores;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    public int contarNoFolha(Node raiz){
        if (raiz == null) return 0;
        if (raiz.esquerda == null && raiz.direita == null) return 1;
        return contarNoFolha(raiz.esquerda) + contarNoFolha(raiz.direita);
    }

    /* -----------------------------sem recursividade--------------------------------- */

    public void preOrdemSR(Node raiz){
        if (raiz == null) return;
        Stack<Node> pilha = new Stack<>();
        pilha.push(raiz);

        while (!pilha.isEmpty()){
            Node atual = pilha.pop();
            System.out.print(atual + " ");

            if (atual.direita !=null) pilha.push(atual.direita);
            if (atual.esquerda != null) pilha.push(atual.esquerda);
        }
    }

    public void emOrdemSR(Node raiz){

        Stack<Node> pilha = new Stack<>();
        Node atual = raiz;

        while (atual != null || !pilha.isEmpty()) {
            while (atual != null) {
                pilha.push(atual);
                atual = atual.esquerda;
            }

            atual = pilha.pop();
            System.out.print(atual.valor + " ");
            atual = atual.direita;
        }
    }

    public void posOrdemSR(Node raiz){
        if (raiz == null)return;
        Stack<Node> pilha1 = new Stack<>();
        Stack<Node> pilha2 = new Stack<>();

        pilha1.push(raiz);

        while (!pilha1.isEmpty()){
            Node atual = pilha1.pop();
            pilha2.push(atual);
            if (atual.esquerda != null) pilha1.push(atual.esquerda);
            if (atual.direita != null) pilha1.push(atual.direita);

        }
        while (!pilha2.isEmpty()){
            System.out.print(pilha2.pop().valor + " ");
        }
    }
    public int contarNoSR(Node raiz){
        if (raiz == null) return 0;
        int contador = 0;
        Stack<Node> pilha = new Stack<>();
        pilha.push(raiz);
        while (!pilha.isEmpty()) {
            Node atual = pilha.pop();
            contador++;

            if(atual.direita != null) pilha.push(atual.direita);
            if(atual.esquerda != null) pilha.push(atual.esquerda);
        }
        return contador;
    }

    public int contarNoFolhaSR(Node raiz){
        if (raiz == null) return 0;
        int contador = 0;

        Stack<Node> pilha = new Stack<>();
        pilha.push(raiz);

        while (!pilha.isEmpty()) {
            Node atual = pilha.pop();
            if (atual.esquerda == null && atual.direita == null){
                contador++;
            }
            if (atual.direita != null) pilha.push(atual.direita);
            if (atual.esquerda != null) pilha.push(atual.esquerda);
        }
        return contador;
    }
}
