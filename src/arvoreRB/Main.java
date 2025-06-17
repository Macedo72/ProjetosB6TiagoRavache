package arvoreRB;

public class Main {
    public static void main(String[] args) {
        ArvoreRB arvoreRB = new ArvoreRB();
        int [] keys = {10, 20, 30, 15, 5, 25};
        for (int key : keys){
            arvoreRB.inserirRB(key);
        }
        System.out.println("Arvore apos inserções (in-order): ");
        arvoreRB.inorder();
        arvoreRB.delete(15);
        arvoreRB.delete(10);
        System.out.println("Arvore apos remocoes (in-order): ");
        arvoreRB.inorder();
    }
}
