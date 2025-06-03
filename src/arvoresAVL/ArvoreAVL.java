package arvoresAVL;

public class ArvoreAVL {
    private Node raiz;

    private int altura(Node node){
        return (node == null)? 0 : node.altura;
    }

    private int getFatorBalan(Node node){
        return (node == null) ? 0 : altura(node.esquerda) - altura(node.direita);
    }
    private void atualizarAlt (Node node){
        node.altura = 1 + Math.max(altura(node.esquerda), altura(node.direita));
    }
    
}
