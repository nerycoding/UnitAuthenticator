package ink.lucasnsnt.unitauthenticator.estrutura;

import ink.lucasnsnt.unitauthenticator.model.Usuario;

public class TabelaHash {

    private final Node[] tabela;
    private final int tamanho;

    public TabelaHash(int tamanho) {
        if (tamanho <= 0) {
            throw new IllegalArgumentException("O tamanho da tabela deve ser maior que zero.");
        }

        this.tamanho = tamanho;
        this.tabela = new Node[tamanho];
    }

    public boolean inserir(String nome, String senha) {
        validarCredenciais(nome, senha);

        int indice = calcularIndice(nome);
        Node atual = tabela[indice];

        while (atual != null) {
            if (atual.getUsuario().getNome().equals(nome)) {
                return false;
            }
            atual = atual.getNext();
        }

        Node novoNode = new Node(new Usuario(nome, senha));
        novoNode.setNext(tabela[indice]);
        tabela[indice] = novoNode;
        return true;
    }

    public boolean remover(String nome) {
        validarNome(nome);

        int indice = calcularIndice(nome);
        Node atual = tabela[indice];
        Node anterior = null;

        while (atual != null) {
            if (atual.getUsuario().getNome().equals(nome)) {
                if (anterior == null) {
                    tabela[indice] = atual.getNext();
                } else {
                    anterior.setNext(atual.getNext());
                }
                return true;
            }

            anterior = atual;
            atual = atual.getNext();
        }

        return false;
    }

    public boolean existe(String nome) {
        validarNome(nome);
        return buscarUsuario(nome) != null;
    }

    public boolean autenticar(String nome, String senha) {
        validarCredenciais(nome, senha);
        Usuario usuario = buscarUsuario(nome);
        return usuario != null && usuario.getSenha().equals(senha);
    }

    private Usuario buscarUsuario(String nome) {
        int indice = calcularIndice(nome);
        Node atual = tabela[indice];

        while (atual != null) {
            Usuario usuario = atual.getUsuario();
            if (usuario.getNome().equals(nome)) {
                return usuario;
            }
            atual = atual.getNext();
        }

        return null;
    }

    private int calcularIndice(String nome) {
        int hash = nome.chars()
                .reduce(0, (acumulado, caractere) -> acumulado * 31 + caractere);
        return Math.floorMod(hash, tamanho);
    }

    private void validarCredenciais(String nome, String senha) {
        validarNome(nome);
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("A senha não pode estar vazia.");
        }
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode estar vazio.");
        }
    }
}
