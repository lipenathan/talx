package main.java.talx.core.repositorio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.github.lipenathan.talx.infra.exception.NegocioException;
import com.github.lipenathan.talx.core.dominio.Mensagem;
import com.github.lipenathan.talx.core.servicos.repositorio.imp.RepMensagemMockImp;

public class TesteRepMensagemMockImp {

    RepMensagemMockImp rep;
    Mensagem mensagem;

    @BeforeEach
    void beforeEach() {
        rep = new RepMensagemMockImp();
        mensagem = new Mensagem(1, 1,
                "####Mensagem de teste####");
    }

    @Test
    void deveListarMensagens() {
        System.out.println("\nTeste Listar");
        try {
            rep.listar().forEach(System.out::println);
        } catch (NegocioException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deveInserirUmaMensagem() throws NegocioException {
        System.out.println("\nTeste Inserção");
        try {
            rep.listar().forEach(System.out::println);
            System.out.println("\ninserindo\n");
            rep.inserir(mensagem);
            for (int i = 0; i < rep.listar().size(); i++) {
                System.out.println(rep.listar().pop());
            }
        } catch (NegocioException e) {
            e.printStackTrace();
        }
    }
}
