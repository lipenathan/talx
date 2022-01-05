package main.java.talx.util.utils;

import org.junit.jupiter.api.Test;
import com.github.lipenathan.talx.infra.exception.NegocioException;
import com.github.lipenathan.talx.core.dominio.Usuario;

import static com.github.lipenathan.flynn.validador.Validador.apenasAlfabetico;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class TesteUtils {

    Usuario u = new Usuario();

    @Test
    public void naoDeveAceitarNomeComNumero() {
        assertFalse(apenasAlfabetico("Nathan2"));
    }

    @Test
    public void naoDeveAceitarEmailInvalido() {
        u.setEmail("abc@hotmail.comer");
        try {
            u.validarEmail();
            fail("não deve validar e-mail incorreto");
        } catch (NegocioException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void naoDeveAceitarTelefoneInvalido() {
        u.setTelefone("(43)996703305");
        try {
            u.validarTelefone();
            fail("não deve validar telefone incorreto");
        } catch (NegocioException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void naoDeveAceitarNomeInvalido() {
        u.setNome("F a");
        try {
            u.validarNome();
            fail("não deve validar nome incorreto");
        } catch (NegocioException e) {
            e.printStackTrace();
        }
    }
}
