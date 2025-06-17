package arvoreRB;

public class Main {
    public static void main(String[] args) {
        ArvoreRB arvore = new ArvoreRB();

        int[] keys = {10, 20, 30, 15, 5, 25};

        for (int key : keys) {
            arvore.inserir(key);
        }

        System.out.println("Árvore após inserções (in-order):");
        arvore.inorder();

        arvore.delete(15);
        arvore.delete(10);

        System.out.println("Árvore após remoções (in-order):");
        arvore.inorder();
    }
}
