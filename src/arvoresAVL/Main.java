package arvoresAVL;

public class Main {
    public static void main(String[] args) {
        ArvoreAVL arvoreAVL = new ArvoreAVL();

        int [] chaves = {10, 20, 30, 40, 50, 25};
        for (int valor : chaves) {
            arvoreAVL.raiz = arvoreAVL.insercao(arvoreAVL.raiz, valor);
        }
        System.out.println("Percurso em ordem da árvore AVL: ");
        arvoreAVL.exibirEmOrdem(arvoreAVL.raiz);
    }
}
