package main.java.talx.core.repositorio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.github.lipenathan.talx.core.dominio.Conversa;
import com.github.lipenathan.talx.infra.exception.NegocioException;
import com.github.lipenathan.talx.core.servicos.repositorio.imp.RepConversaMockImp;
import com.github.lipenathan.talx.core.servicos.repositorio.imp.RepMensagemMockImp;

public class TesteRepConversaMockImp {

    RepMensagemMockImp repositorioMensagem;
    RepConversaMockImp repositorioConversa;

    @BeforeEach
    void beforeEach() {
        repositorioMensagem = new RepMensagemMockImp();
        repositorioConversa = new RepConversaMockImp(repositorioMensagem);
    }

    @Test
    void deveListarConversas(){
        try {
            repositorioConversa.listar().forEach(c -> {
                System.out.println("\nid: " + c.getId() + "\nEmissor: " + c.getIdP1() +
                        "\nReceptor: " + c.getIdP2() + "\nHorario última Mensagem:" +
                        c.getDataHoraUltimaMensagem());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deveBuscarPorId(){
        try {
            Conversa c = repositorioConversa.getById(1);
            System.out.println(c);
        } catch (NegocioException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deveListarAConversaDeUmUsuarioPeloId() {
        try {
            repositorioConversa.getConversasUsuario(2).forEach(c -> System.out.println("\n" + c));
        } catch (NegocioException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deveListarMensagensDaConversa() {
        try {
            repositorioConversa.getConversasUsuario(1).get(0).getMensagens().forEach(System.out::println);
        } catch (NegocioException e) {
            e.printStackTrace();
        }
    }
}
