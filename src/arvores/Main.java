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
        Arvore arvore = new Arvore();
        System.out.print("Pré-Ordem: ");
        new Arvore().preOrdem(raiz);

        System.out.print("\nEm-Ordem: ");
        new Arvore().emOrdem(raiz);

        System.out.print("\nPós-Ordem: ");
        new Arvore().posOrdem(raiz);

        System.out.print("\nBusca por nível: ");
        Arvore.buscaNivel(raiz);

        System.out.print("\nTotal de nós folha: " + arvore.contarNoFolha(raiz));
        System.out.println(" ");

        System.out.println("-----------------sem recursividade----------------- ");
        System.out.println("Total de nós SR: " + arvore.contarNoSR(raiz));

        System.out.println("Total de nós SR com fila: " + arvore.contarNoSRComFila(raiz));
        System.out.print("Pré-Ordem SR: "); arvore.preOrdemSR(raiz);
        System.out.println();
        System.out.print("Em-Ordem SR: "); arvore.emOrdemSR(raiz);
        System.out.println();
        System.out.print("Pós-Ordem SR: "); arvore.posOrdemSR(raiz);
        System.out.println();
        System.out.print("Busca por nível SR: "); Arvore.buscaNivel(raiz);
        System.out.println();
        System.out.print("Total de nós folha SR: " + arvore.contarNoFolha(raiz));
    }
}
