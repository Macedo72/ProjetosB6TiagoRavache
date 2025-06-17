package arvoreRB;

public class ArvoreRB {
    private final Node NIL = new Node(0);
    private Node raiz;

    public ArvoreRB() {
        NIL.cor = Node.Cor.PRETO;
        NIL.esquerda = NIL.direita = NIL.parent = NIL;
        raiz = NIL;
    }

    public void inserir(int valor) {
        Node node = new Node(valor);
        node.parent = NIL;
        node.esquerda = NIL;
        node.direita = NIL;
        node.cor = Node.Cor.VERMELHO;

        Node y = NIL;
        Node x = raiz;

        while (x != NIL) {
            y = x;
            if (node.valor < x.valor) {
                x = x.esquerda;
            } else {
                x = x.direita;
            }
        }

        node.parent = y;
        if (y == NIL) {
            raiz = node;
        } else if (node.valor < y.valor) {
            y.esquerda = node;
        } else {
            y.direita = node;
        }

        inserirFix(node);
    }

    private void inserirFix(Node k) {
        Node u;
        while (k.parent.cor == Node.Cor.VERMELHO) {
            if (k.parent == k.parent.parent.esquerda) {
                u = k.parent.parent.direita;
                if (u.cor == Node.Cor.VERMELHO) {
                    // Caso 1: tio vermelho
                    k.parent.cor = Node.Cor.PRETO;
                    u.cor = Node.Cor.PRETO;
                    k.parent.parent.cor = Node.Cor.VERMELHO;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.direita) {
                        // Caso 2: k é filho direito
                        k = k.parent;
                        leftRotate(k);
                    }
                    // Caso 3: k é filho esquerdo
                    k.parent.cor = Node.Cor.PRETO;
                    k.parent.parent.cor = Node.Cor.VERMELHO;
                    rightRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.esquerda;
                if (u.cor == Node.Cor.VERMELHO) {
                    // Caso 1 (espelhado)
                    k.parent.cor = Node.Cor.PRETO;
                    u.cor = Node.Cor.PRETO;
                    k.parent.parent.cor = Node.Cor.VERMELHO;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.esquerda) {
                        // Caso 2 (espelhado)
                        k = k.parent;
                        rightRotate(k);
                    }
                    // Caso 3 (espelhado)
                    k.parent.cor = Node.Cor.PRETO;
                    k.parent.parent.cor = Node.Cor.VERMELHO;
                    leftRotate(k.parent.parent);
                }
            }
            if (k == raiz) {
                break;
            }
        }
        raiz.cor = Node.Cor.PRETO;
    }

    private void leftRotate(Node x) {
        Node y = x.direita;
        x.direita = y.esquerda;
        if (y.esquerda != NIL) {
            y.esquerda.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == NIL) {
            raiz = y;
        } else if (x == x.parent.esquerda) {
            x.parent.esquerda = y;
        } else {
            x.parent.direita = y;
        }
        y.esquerda = x;
        x.parent = y;
    }

    private void rightRotate(Node x) {
        Node y = x.esquerda;
        x.esquerda = y.direita;
        if (y.direita != NIL) {
            y.direita.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == NIL) {
            raiz = y;
        } else if (x == x.parent.direita) {
            x.parent.direita = y;
        } else {
            x.parent.esquerda = y;
        }
        y.direita = x;
        x.parent = y;
    }

    public void inorder() {
        inorderHelper(raiz);
        System.out.println();
    }

    private void inorderHelper(Node node) {
        if (node != NIL) {
            inorderHelper(node.esquerda);
            System.out.print(node.valor + "(" + node.cor + ") ");
            inorderHelper(node.direita);
        }
    }

    // Busca nó pela chave
    private Node searchTree(Node node, int valor) {
        if (node == NIL || valor == node.valor) {
            return node;
        }
        if (valor < node.valor) {
            return searchTree(node.esquerda, valor);
        } else {
            return searchTree(node.direita, valor);
        }
    }

    private void transplant(Node u, Node v) {
        if (u.parent == NIL) {
            raiz = v;
        } else if (u == u.parent.esquerda) {
            u.parent.esquerda = v;
        } else {
            u.parent.direita = v;
        }
        v.parent = u.parent;
    }

    public void delete(int valor) {
        Node z = searchTree(raiz, valor);
        if (z == NIL) {
            System.out.println("Valor " + valor + " não encontrado na arvore.");
            return;
        }

        Node y = z;
        Node.Cor yOriginalCor = y.cor;
        Node x;

        if (z.esquerda == NIL) {
            x = z.direita;
            transplant(z, z.direita);
        } else if (z.direita == NIL) {
            x = z.esquerda;
            transplant(z, z.esquerda);
        } else {
            y = minimum(z.direita);
            yOriginalCor = y.cor;
            x = y.direita;
            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.direita);
                y.direita = z.direita;
                y.direita.parent = y;
            }
            transplant(z, y);
            y.esquerda = z.esquerda;
            y.esquerda.parent = y;
            y.cor = z.cor;
        }
        if (yOriginalCor == Node.Cor.PRETO) {
            deleteFix(x);
        }
    }

    private void deleteFix(Node x) {
        Node w;
        while (x != raiz && x.cor == Node.Cor.PRETO) {
            if (x == x.parent.esquerda) {
                w = x.parent.direita;
                if (w.cor == Node.Cor.VERMELHO) {
                    w.cor = Node.Cor.PRETO;
                    x.parent.cor = Node.Cor.VERMELHO;
                    leftRotate(x.parent);
                    w = x.parent.direita;
                }
                if (w.esquerda.cor == Node.Cor.PRETO && w.direita.cor == Node.Cor.PRETO) {
                    w.cor = Node.Cor.VERMELHO;
                    x = x.parent;
                } else {
                    if (w.direita.cor == Node.Cor.PRETO) {
                        w.esquerda.cor = Node.Cor.PRETO;
                        w.cor = Node.Cor.VERMELHO;
                        rightRotate(w);
                        w = x.parent.direita;
                    }
                    w.cor = x.parent.cor;
                    x.parent.cor = Node.Cor.PRETO;
                    w.direita.cor = Node.Cor.PRETO;
                    leftRotate(x.parent);
                    x = raiz;
                }
            } else {
                w = x.parent.esquerda;
                if (w.cor == Node.Cor.VERMELHO) {
                    w.cor = Node.Cor.PRETO;
                    x.parent.cor = Node.Cor.VERMELHO;
                    rightRotate(x.parent);
                    w = x.parent.esquerda;
                }
                if (w.direita.cor == Node.Cor.PRETO && w.esquerda.cor == Node.Cor.PRETO) {
                    w.cor = Node.Cor.VERMELHO;
                    x = x.parent;
                } else {
                    if (w.esquerda.cor == Node.Cor.PRETO) {
                        w.direita.cor = Node.Cor.PRETO;
                        w.cor = Node.Cor.VERMELHO;
                        leftRotate(w);
                        w = x.parent.esquerda;
                    }
                    w.cor = x.parent.cor;
                    x.parent.cor = Node.Cor.PRETO;
                    w.esquerda.cor = Node.Cor.PRETO;
                    rightRotate(x.parent);
                    x = raiz;
                }
            }
        }
        x.cor = Node.Cor.PRETO;
    }

    private Node minimum(Node node) {
        while (node.esquerda != NIL) {
            node = node.esquerda;
        }
        return node;
    }
}
