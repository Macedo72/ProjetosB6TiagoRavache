package arvores;

public class Node {
    public String valor;
    public Node esquerda;
    public Node direita;

    public Node(String valor){
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }
    @Override
    public String toString() {
        return valor;
    }
}
