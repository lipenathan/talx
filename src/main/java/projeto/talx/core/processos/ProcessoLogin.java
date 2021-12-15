package projeto.talx.core.processos;

import projeto.talx.infra.exception.NegocioException;
import projeto.talx.core.dominio.Usuario;
import projeto.talx.core.servicos.repositorio.contrato.RepositorioUsuario;

/**
 * serviços de login
 * @author lipen
 * @version 1.0 01/09/21
 * @since 01/09/21
 */
public class ProcessoLogin {

    private final RepositorioUsuario repUsuario;
    private Usuario u = new Usuario();

    public ProcessoLogin(RepositorioUsuario repUsuario) {
        this.repUsuario = repUsuario;
    }

    /**
     * método responsavel por fazer login do usuario
     * @param login e-mail ou telefone do usuario
     * @param senha para acesso do usuario
     * @return usuário logado
     * @throws NegocioException caso não seja possível logar
     */
    public Usuario login(String login, String senha) throws NegocioException {
        u = repUsuario.buscaPorLogin(login);
        if (u.getSenha().equals(senha)) {
            System.out.println("Você está logado");
            return u;
        }
            throw new NegocioException("Login não efetuado");
    }
}
