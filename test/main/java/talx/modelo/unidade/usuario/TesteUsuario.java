package main.java.talx.modelo.unidade.usuario;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projeto.talx.infra.exception.NegocioException;
import projeto.talx.core.dominio.Usuario;

public class TesteUsuario {

    Usuario u;

    @BeforeEach
    public void beforeEach() {
        u = new Usuario("Felipe Nathan", "(43)99670-3305",
                "felipenatthan@gmail.com", "abc123");
    }

    // Negativos

    @Test
    public void naoDeveInserirUsuarioSemNome() {
        u.setNome(null);
        try {
            u.validar();
            fail("Não deve validar nome nulo");
        } catch (NegocioException e) {
            e.printStackTrace();
        }
    }

    @Test
    void naoDeveValidarEmailETelfoneNulo() {
        u.setEmail(null);
        u.setTelefone(null);
        try {
            u.validar();
            fail("Não deve validar e-mail e telefone nulo");
        } catch (NegocioException e) {
            e.printStackTrace();
        }
    }

    @Test
    void naoDeveValidarEmailETelfoneVazio() {
        u.setEmail("");
        u.setTelefone("");
        try {
            u.validar();
            fail("Não deve validar e-mail e telefone vazios");
        } catch (NegocioException e) {
            e.printStackTrace();
        }
    }

    @Test
    void naoDeveValidarSenhaMenorQue6Digitos() {
        u.setSenha("abc1");
        try {
            u.validar();
            fail("Não deve validar senha inválida");
        } catch (NegocioException e) {
            e.printStackTrace();
        }
    }

    @Test
    void naoDeveValidarSenhaApenasComLetras() {
        u.setSenha("abcdbefasfasf");
        try {
            u.validar();
            fail("Não deve validar senha inválida");
        } catch (NegocioException e) {
            e.printStackTrace();
        }
    }

    // Positivos

    @Test
    void deveValidarUmUsuario() {
        try {
            u.validar();
        } catch (Exception e) {
            fail("deve validar usuario com dados corretos");
        }
    }
}
