package arvores;

public class Main {
    public static void main(String[] args) {
        Node raiz = new Node("A");
        raiz.esquerda = new Node("B");
        raiz.direita = new Node("C");
        raiz.esquerda.esquerda = new Node("D");
        raiz.esquerda.direita = new Node("E");
        raiz.direita.esquerda = new Node("F");

        System.out.println("Total de Nós: " + new Arvore().contarNos(raiz));

        System.out.print("Pré-Ordem: ");
        new Arvore().preOrdem(raiz);

        System.out.print("\nEm-Ordem: ");
        new Arvore().emOrdem(raiz);

        System.out.print("\nPós-Ordem: ");
        new Arvore().posOrdem(raiz);

        System.out.print("\nBusca por nível: ");
        Arvore.buscaNivel(raiz);
    }
}
