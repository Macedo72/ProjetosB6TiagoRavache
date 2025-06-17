package arvoreRB;

import java.awt.*;

public class ArvoreRB {
    Node raiz;

    public void inserirRB(int valor) {
        raiz = inserirRB(raiz, valor);
    }

    Node inserirRB(Node node, int valor) {
        if (node == null) return new Node(valor);
        if (valor < node.valor)
            node.esquerda = inserirRB(node.esquerda, valor);
        else if (valor > node.valor)
            node.direita = inserirRB(node.direita, valor);
        else
            return node;

    }
    private void rightRotate(Node y){
        Node x = y.esquerda;
        y.esquerda = x.direita;
        if (x.direita != NIL) x.direita.parent = y;
        x.parent = y.parent;

        if(y.parent == null) root = x;
        else if (y == y.parent.direita) y.parent.direita = x;
        else y.parent.esquerda = x;

        x.direita = y;
        y.esquerda = x;


    }
    private void leftRotate(Node x) {
        Node y = x.direita;
        x.direita = y.esquerda;
        if (y.esquerda != NIL) y.esquerda.parent = x;

        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.esquerda) x.parent.esquerda = y;
        else x.parent.direita = y;

        y.esquerda = x;
        x.direita = y;
    }

    public void insert(int key){
        Node node = new Node(key);
        node.esquerda = node.direita = node.parent = NIL;
        Node y = null;
        Node x = root;

        while (x != NIL) {
            y = x;
            if (node.key < x.key) x = x.esquerda;
            else x = x.direita;
        }
        node.parent = y;
        if (y == null) root = node;
        else if (node.key < y.key) y.esquerda = node;
        else y.direita = node;

        node.esquerda = NIL;
        node.direita = NIL;
        node.color = Color.RED;

        inserFix(node);
    }

    private void inserFix(Node k) {
        while (k.parent != null && k.parent.color == Color.RED){
            if (k.parent == k.parent.parent.esquerda){
                Node u = k.parent.parent.direita;
                if (u.color == Color.RED){
                    k.parent.color = Color.BLACK;
                    u.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.direita;
                } else{
                    if (k == k.parent.direita){
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    rightRotate(k.parent.parent);
                } else {
                    Node u = k.parent.parent.esquerda;
                    if (u.color == Color.RED) {
                        k.parent.color = Color.BLACK;
                        u.color = Color.BLACK;
                        k.parent.parent.color = Color.RED;
                        k = k.parent.parent;
                    } else {
                        if (k == k.parent.esquerda){
                            k = k.parent;
                            rightRotate(k);
                        }
                        k.parent.color = Color.BLACK;
                        k.parent.parent.color = Color.RED;
                        leftRotate(k.parent.parent);
                    }
                }
                if (k == root) break;
            }
            root.color = Color.BLACK;
        }
    }
    private void transplant(Node u, Node v){
        if(u.parent == null) root = v;
        else if (u == u.parent.esquerda) u.parent.esquerda = v;
        else u.parent.direita = v;
        v.parent = u.parent;
    }
    public void delete(int key){
        Node z = searchTree(root, key);
        if (z == NIL)
            return;
        Node y = z;
        Color yOriginalColor = y.color;
        Node x;

        if (z.esquerda == NIL){
            x = z.direita;
            transplant(z, z.direita);
        } else if (z.direita == NIL) {
            x = z.esquerda;
            transplant(z, z.esquerda);
        } else {
            y = minimum(z.direita);
            yOriginalColor = y.color;
            x = y.direita;
            if (y.parent == z) x.parent = y;
            else {
                transplant(y, y.direita);
                y.direita = z.direita;
                y.direita.parent = y;
            }
            transplant(z, y);
            y.esquerda = z.esquerda;
            y.esquerda.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == Color.BLACK) deleteFix(x);
    }

    private void deleteFix(Node x){
        Node w;
        while (x != root && x.color == Color.BLACK){
            if (x == x.parent.esquerda){
                w = x.parent.direita;
                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    leftRotate(x.parent);
                    w = x.parent.direita;
                }
                if (w.esquerda.color == Color.BLACK && w.direita.color) == Color.BLACK{
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.direita.color == Color.BLACK){
                        w.esquerda.color = Color.BLACK;
                        w.color = Color.RED;
                        rightRotate(w);
                        w = x.parent.direita;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    w.direita.color = Color.BLACK;
                    leftRotate(x.parent);
                    x = root;
                } else {
                    w = x.parent.esquerda;
                    if (w.color == Color.RED){
                        w.color = Color.BLACK;
                        x.parent.color = Color.RED;
                        rightRotate(x.parent);
                        w = x.parent.esquerda;
                    }
                    if (w.direita.color == Color.BLACK && w.esquerda.color == Color.BLACK){
                        w.color = Color.RED;
                        x = x.parent;
                    } else {
                        if (w.esquerda.color == Color.BLACK){
                            w.direita.color = Color.BLACK;
                            w.color = Color.RED;
                            leftRotate(w);
                            w = x.parent.esquerda;
                        }
                        w.color = x.parent.color;
                        x.parent.color = Color.BLACK;
                        w.esquerda.color = Color.BLACK;
                        rightRotate(x.parent);
                        x = root;
                    }
                }
            }
            x.color = Color.BLACK;
        }
    }
    private Node searchTree(Node node, int key){
        if (node == NIL || key == node.key)
            return node;
        if (key < node.key)
            return searchTree(node.esquerda, key);
        return searchTree(node.direita, key);
    }
    public void inorder(){
        inorderHelper(root);
        System.out.println();
    }
    private void inorderHelper(Node node){
        if (node != NIL) {
            inorderHelper(node.esquerda);
            String colorSuffix = (node.color == Color.RED) ? "R" : "B";
            System.out.print(node.key + colorSuffix + " ");
            inorderHelper(node.direita);
        }
    }
}