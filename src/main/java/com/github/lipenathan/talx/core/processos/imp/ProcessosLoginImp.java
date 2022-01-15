package com.github.lipenathan.talx.core.processos.imp;

import com.github.lipenathan.talx.core.dominio.Usuario;
import com.github.lipenathan.talx.core.processos.contratos.ProcessosLogin;
import com.github.lipenathan.talx.core.servicos.repositorio.contrato.RepositorioUsuario;
import com.github.lipenathan.talx.core.servicos.repositorio.imp.RepositorioUsuarioImp;
import com.github.lipenathan.talx.infra.exception.NegocioException;
import com.github.lipenathan.talx.infra.exception.PersistenceException;

/**
 * serviços de login
 *
 * @author lipen
 * @version 1.0 01/09/21
 * @since 01/09/21
 */

public class ProcessosLoginImp implements ProcessosLogin {

    private final RepositorioUsuario repUsuario = new RepositorioUsuarioImp();



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
        if (u != null && u.getSenha().equals(senha)) {
            System.out.println("Você está logado");
            return u;
        }
        throw new PersistenceException("Usuário não encontrado");
    }
}