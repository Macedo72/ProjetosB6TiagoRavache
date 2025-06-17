package arvoreRB;

public class Node {
    public enum Cor { VERMELHO, PRETO }

    public int valor;
    public Node esquerda, direita, parent;
    public Cor cor;

    public Node(int valor) {
        this.valor = valor;
        this.cor = Cor.VERMELHO; // todo nó novo começa vermelho
        this.esquerda = null;
        this.direita = null;
        this.parent = null;
    }
}
