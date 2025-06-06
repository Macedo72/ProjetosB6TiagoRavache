package arvoresAVL;

public class ArvoreAVL {
    private Node raiz;

    private int altura(Node node) {
        return (node == null) ? 0 : node.altura;
    }

    private int getFatorBalan(Node node) {
        return (node == null) ? 0 : altura(node.esquerda) - altura(node.direita);
    }

    private void atualizarAlt(Node node) {
        node.altura = 1 + Math.max(altura(node.esquerda), altura(node.direita));
    }

    private Node rotacaoDireita(Node y) {
        Node x = y.esquerda;
        Node z = x.direita;

        x.direita = y;
        y.esquerda = z;

        atualizarAlt(y);
        atualizarAlt(x);
        return x;
    }

    private Node rotacaoEsquerda(Node x) {
        Node y = x.direita;
        Node z = y.esquerda;

        y.esquerda = x;
        x.direita = z;

        atualizarAlt(x);
        atualizarAlt(y);
        return y;
    }

    public Node insercao(Node node, int valor) {
        if (node == null) return new Node(valor);

        if (valor < node.valor) node.esquerda = insercao(node.esquerda, valor);
        else if (valor < node.valor) node.direita = insercao(node.direita, valor);
        else return node;

        atualizarAlt(node);
        int fatorBalan = getFatorBalan(node);

        // left-left
        if (fatorBalan > 1 && valor < node.esquerda.valor)
            return rotacaoDireita(node);

        // right-right
        if (fatorBalan < -1 && valor > node.direita.valor)
            return rotacaoEsquerda(node);

        // left-right
        if (fatorBalan > 1 && valor > node.esquerda.valor) {
            node.esquerda = rotacaoEsquerda(node.esquerda);
            return rotacaoDireita(node);
        }

        // right-left
        if (fatorBalan < -1 && valor < node.direita.valor) {
            node.direita = rotacaoDireita(node.direita);
            return rotacaoEsquerda(node);
        }
        return node;
    }

    public Node remocao(Node node, int valor) {
        if (node == null) return null;
        if (valor < node.valor) node.esquerda = remocao(node.esquerda, valor);
        else if (valor > node.valor) node.direita = remocao(node.direita, valor);
        else {
            if (node.esquerda == null || node.direita == null) {
                Node temp = (node.esquerda != null) ? node.esquerda : node.direita;
                if (temp == null) node = null;
                else node = temp;
            } else {
                Node sucessor = getMenorValor(node.direita);
                node.valor = sucessor.valor;
                node.direita = remocao(node.direita, sucessor.valor);
            }
        }
        if (node == null) return null;
        node.altura = 1 + Math.max(altura(node.esquerda), altura(node.direita));
        int fatorBalan = getFatorBalan(node);
        if (fatorBalan > 1 && getFatorBalan(node.esquerda) >= 0) return rotacaoDireita(node); // LL
        if (fatorBalan > 1 && getFatorBalan(node.esquerda) < 0) {
            node.esquerda = rotacaoEsquerda(node.esquerda);
            return rotacaoDireita(node);
        }
        if (fatorBalan < -1 && getFatorBalan(node.direita) <= 0) return rotacaoEsquerda(node); // RR
        if (fatorBalan < -1 && getFatorBalan(node.direita) > 0) {
            node.direita = rotacaoDireita(node.direita);
            return rotacaoEsquerda(node);
        }
        return node;
    }
    private Node getMenorValor(Node node){
        Node atual = node;
        while (atual.esquerda != null) atual = atual.esquerda;
        return atual;
    }
    public Node buscar(Node node, int valor){
        if (node == null || node.valor == valor)
            return node;
        if (valor < node.valor)
            return buscar(node.esquerda, valor);
        return buscar(node.direita, valor);
    }
    public void exibirEmOrdem(Node node) {
        if (node != null) {
            exibirEmOrdem(node.esquerda);
            System.out.print(node.valor + " ");
            exibirEmOrdem(node.direita);
        }
    }
}
