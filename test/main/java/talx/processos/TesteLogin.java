package main.java.talx.processos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projeto.talx.core.dominio.Conversa;
import projeto.talx.infra.exception.NegocioException;
import projeto.talx.core.dominio.Usuario;
import projeto.talx.core.processos.ProcessoLogin;
import projeto.talx.core.servicos.repositorio.imp.RepConversaMockImp;
import projeto.talx.core.servicos.repositorio.imp.RepMensagemMockImp;
import projeto.talx.core.servicos.repositorio.imp.RepUsuarioMockImp;

import static org.junit.jupiter.api.Assertions.fail;

public class TesteLogin {

    String telefoneCorreto = "(41)98522-5589";
    String telefoneIncorreto = "(43)99670-3";
    String telefoneInexistente = "(55)99670-3305";
    String emailCorreto = "mock5@hotmail.com";
    String emailInexistente = "felipe@gmail.com";
    String emailIncorreto = "felipe@.lob";
    String senhaCorreta = "abcde1";
    String senhaIncorreta = "acefewg";

    RepMensagemMockImp repositorioMensagem;
    RepUsuarioMockImp repositorioUsuario;
    RepConversaMockImp repositorioConversa;
    ProcessoLogin processoLogin;

    @BeforeEach
    void beforeEach() {
        repositorioMensagem = new RepMensagemMockImp();
        repositorioConversa = new RepConversaMockImp(repositorioMensagem);
        repositorioUsuario = new RepUsuarioMockImp(repositorioConversa);
        processoLogin = new ProcessoLogin(repositorioUsuario);
    }

    @Test
    void deveLogarComDadosCorretos() {
        String login = telefoneCorreto;
        String senha = senhaCorreta;
        try {
            repositorioUsuario.listar().forEach(System.out::println);
            Usuario u = processoLogin.login(login, senha);
            System.out.println("\nListando Conversas do Usuario " + u.getNome());
            u.getConversas().forEach(System.out::println);
            int i=1;
            for (Conversa c: u.getConversas()) {
                System.out.println("\n########### Conversa "+ (i++) + " ###########");
                c.getMensagens().forEach(System.out::println);
            }
        } catch (NegocioException e) {
            fail("Deveria logar sem falha " + e.getMessage());
        }
    }

    @Test
    void naoDeveLogarComDadosInexistentes() {
        String login = telefoneInexistente;
        String senha = senhaCorreta;
        try {
            processoLogin.login(login, senha);
            fail("Não deve logar com credenciais inválidas");
        } catch (NegocioException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void naoDeveLogarComDadosInvalidos() {
        String login = telefoneCorreto;
        String senha = senhaIncorreta;
        try {
            processoLogin.login(login, senha);
            fail("Não deve logar com credenciais inválidas");
        } catch (NegocioException e) {
            System.out.println(e.getMessage());
        }
    }
}
