package arvoresAVL;

public class Node {
    public int valor;
    public Node esquerda;
    public Node direita;
    public int altura;

    public Node(int valor){
        this.valor = valor;
        this.altura = 1;
    }

    @Override
    public String toString(){
        return String.valueOf(valor);
    }
}
