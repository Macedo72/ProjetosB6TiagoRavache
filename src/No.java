public class No {
    String valor;
    No esq, dir;

    public No(String valor, No esq, No dir){
        this.valor = valor;
        esq = dir = null;
    }
}
