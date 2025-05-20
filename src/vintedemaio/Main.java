package vintedemaio;

public class Main {
public static void main(String[] args) {

    ContarNo.Node raiz = new ContarNo.Node("A");
    raiz.esquerda = new ContarNo.Node("B");
    raiz.direita = new ContarNo.Node("C");
    raiz.esquerda.esquerda = new ContarNo.Node("D");
    raiz.esquerda.direita = new ContarNo.Node("E");
    raiz.direita.direita = new ContarNo.Node("F");

    int total = ContarNo.contarNos(raiz);
    System.out.println("Total de nós: " + total);

    System.out.print("Pré-Ordem: ");
    ContarNo.preOrdem(raiz);
    System.out.println();

    System.out.print("Em-Ordem: ");
    ContarNo.emOrdem(raiz);
    System.out.println();

    System.out.print("Pós-Ordem: ");
    ContarNo.posOrdem(raiz);
    System.out.println();

    System.out.print("Busca em Nível: ");
    ContarNo.buscaNivel(raiz);
    System.out.println();

}
}