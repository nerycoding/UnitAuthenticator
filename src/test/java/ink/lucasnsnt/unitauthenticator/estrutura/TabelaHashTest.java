package ink.lucasnsnt.unitauthenticator.estrutura;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TabelaHashTest {

    private TabelaHash tabela;

    @BeforeEach
    void prepararTabela() {
        tabela = new TabelaHash(5);
    }

    @Test
    void deveInserirEBuscarUsuario() {
        assertTrue(tabela.inserir("ana", "123"));
        assertTrue(tabela.existe("ana"));
    }

    @Test
    void naoDeveInserirNomeDuplicado() {
        assertTrue(tabela.inserir("ana", "123"));
        assertFalse(tabela.inserir("ana", "outra"));
    }

    @Test
    void deveAutenticarApenasComSenhaCorreta() {
        tabela.inserir("ana", "123");

        assertTrue(tabela.autenticar("ana", "123"));
        assertFalse(tabela.autenticar("ana", "errada"));
        assertFalse(tabela.autenticar("inexistente", "123"));
    }

    @Test
    void deveRemoverUsuario() {
        tabela.inserir("ana", "123");

        assertTrue(tabela.remover("ana"));
        assertFalse(tabela.existe("ana"));
        assertFalse(tabela.remover("ana"));
    }

    @Test
    void devePreservarOutroUsuarioAposColisaoERemocao() {
        tabela.inserir("a", "senha-a");
        tabela.inserir("f", "senha-f");

        assertTrue(tabela.remover("f"));
        assertTrue(tabela.autenticar("a", "senha-a"));
    }

    @Test
    void deveRejeitarDadosVazios() {
        assertThrows(IllegalArgumentException.class, () -> tabela.inserir("", "123"));
        assertThrows(IllegalArgumentException.class, () -> tabela.inserir("ana", " "));
    }
}
