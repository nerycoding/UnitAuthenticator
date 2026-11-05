package ink.lucasnsnt.unitauthenticator;

import ink.lucasnsnt.unitauthenticator.estrutura.TabelaHash;

import java.util.Scanner;

public class Main {

    private static final int TAMANHO_TABELA = 11;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TabelaHash tabela = new TabelaHash(TAMANHO_TABELA);
        int opcao;

        do {
            exibirMenu();
            opcao = lerOpcao(scanner);

            try {
                executarOpcao(opcao, scanner, tabela);
            } catch (IllegalArgumentException exception) {
                System.out.println("Erro: " + exception.getMessage());
            }
        } while (opcao != 5);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== Unit Authenticator ===");
        System.out.println("1. Inserção");
        System.out.println("2. Remoção");
        System.out.println("3. Busca");
        System.out.println("4. Login");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao(Scanner scanner) {
        String entrada = scanner.nextLine();
        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException exception) {
            return -1;
        }
    }

    private static void executarOpcao(int opcao, Scanner scanner, TabelaHash tabela) {
        switch (opcao) {
            case 1 -> inserir(scanner, tabela);
            case 2 -> remover(scanner, tabela);
            case 3 -> buscar(scanner, tabela);
            case 4 -> autenticar(scanner, tabela);
            case 5 -> System.out.println("Sistema encerrado.");
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void inserir(Scanner scanner, TabelaHash tabela) {
        String nome = lerCampo(scanner, "Nome: ");
        String senha = lerCampo(scanner, "Senha: ");
        boolean inserido = tabela.inserir(nome, senha);
        System.out.println(inserido ? "Usuário cadastrado." : "Usuário já cadastrado.");
    }

    private static void remover(Scanner scanner, TabelaHash tabela) {
        String nome = lerCampo(scanner, "Nome: ");
        boolean removido = tabela.remover(nome);
        System.out.println(removido ? "Usuário removido." : "Usuário não encontrado.");
    }

    private static void buscar(Scanner scanner, TabelaHash tabela) {
        String nome = lerCampo(scanner, "Nome: ");
        System.out.println(tabela.existe(nome) ? "Usuário encontrado." : "Usuário não encontrado.");
    }

    private static void autenticar(Scanner scanner, TabelaHash tabela) {
        String nome = lerCampo(scanner, "Nome: ");
        String senha = lerCampo(scanner, "Senha: ");
        System.out.println(tabela.autenticar(nome, senha) ? "Login realizado." : "Nome ou senha inválidos.");
    }

    private static String lerCampo(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }
}
