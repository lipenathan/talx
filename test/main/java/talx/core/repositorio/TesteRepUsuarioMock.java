package main.java.talx.core.repositorio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projeto.talx.infra.exception.NegocioException;
import projeto.talx.core.dominio.Usuario;
import projeto.talx.core.servicos.repositorio.imp.RepConversaMockImp;
import projeto.talx.core.servicos.repositorio.imp.RepMensagemMockImp;
import projeto.talx.core.servicos.repositorio.imp.RepUsuarioMockImp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class TesteRepUsuarioMock {

    Usuario u;
    RepMensagemMockImp repM;
    RepUsuarioMockImp repU;
    RepConversaMockImp repC;

    @BeforeEach
    void beforeEach() {
        repM = new RepMensagemMockImp();
        repC = new RepConversaMockImp(repM);
        repU = new RepUsuarioMockImp(repC);
        u = new Usuario("Afonso", "(43)99670-3305", null, "abc1234");
    }

    private static void print(List<Usuario> lista) {
        lista.forEach(u -> System.out.println(u));
    }

    @Test
    void deveInserirUmNovoUsuario() {
        try {
            print(repU.listar());
            System.out.println("\nInserindo..\n");
            repU.inserir(u);
            print(repU.listar());
        } catch (NegocioException e) {
            fail("deve inserir usuario sem erro");
        }
    }

    @Test
    void deveDeletarUmUsuario() {
        try {
            print(repU.listar());
            Usuario u = repU.getById(1);
            System.out.println("\ndeletando..\n");
            repU.deletar(u);
            print(repU.listar());
        } catch (NegocioException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deveAlterarUmUsuario() {
        try {
            print(repU.listar());
            Usuario u = repU.getById(1);
            u.setNome("Novo Nome Alterado");
            System.out.println("\nAlterando..\n");
            repU.alterar(u);
            print(repU.listar());
        } catch (NegocioException e) {
            e.printStackTrace();
        }
    }
}
