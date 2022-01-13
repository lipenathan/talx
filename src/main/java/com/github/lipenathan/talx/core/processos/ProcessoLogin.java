package com.github.lipenathan.talx.core.processos;

import com.github.lipenathan.talx.core.dominio.Usuario;
import com.github.lipenathan.talx.core.servicos.repositorio.contrato.RepositorioUsuario;
import com.github.lipenathan.talx.infra.exception.NegocioException;
import com.github.lipenathan.talx.infra.exception.PersistenceException;

/**
 * serviços de login
 *
 * @author lipen
 * @version 1.0 01/09/21
 * @since 01/09/21
 */
public class ProcessoLogin {

    private final RepositorioUsuario repUsuario;



    public ProcessoLogin(RepositorioUsuario repUsuario) {
        this.repUsuario = repUsuario;
    }

    /**
     * método responsavel por fazer login do usuario
     *
     * @param login e-mail ou telefone do usuario
     * @param senha para acesso do usuario
     * @return usuário logado
     * @throws NegocioException caso não seja possível logar
     */
    public Usuario logar(String login, String senha) throws PersistenceException {
        Usuario u = repUsuario.buscaPorLogin(login);
        if (u.getSenha().equals(senha)) {
            System.out.println("Você está logado");
            return u;
        }
        throw new PersistenceException("Login não efetuado");
    }
}
