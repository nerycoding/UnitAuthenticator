package ink.lucasnsnt.unitauthenticator.estrutura;

import ink.lucasnsnt.unitauthenticator.model.Usuario;

public class Node {

    private final Usuario usuario;

    private Node next;

    public Node(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}
