package arvoreRB;

public class Node {
    public int valor;
    public Node esquerda, direita, parent;
    public boolean vermelho;

    public Node(int valor) {
        this.valor = valor;
        this.vermelho = true; // Começa vermelho por padrão na inserção
        this.esquerda = null;
        this.direita = null;
        this.parent = null;
    }
}
