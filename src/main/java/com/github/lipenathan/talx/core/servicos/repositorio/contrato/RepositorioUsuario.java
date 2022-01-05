package com.github.lipenathan.talx.core.servicos.repositorio.contrato;

import com.github.lipenathan.talx.core.dominio.Usuario;
import com.github.lipenathan.talx.infra.exception.NegocioException;

import java.util.List;

public interface RepositorioUsuario {
    void inserir(Usuario o) throws NegocioException;

    void deletar(Usuario o) throws NegocioException;

    List<Usuario> listar() throws NegocioException;

    Usuario getById(Integer id) throws NegocioException;

    void alterar(Usuario o) throws NegocioException;

    Usuario buscaPorLogin(String login) throws NegocioException;

    RepositorioConversa getRepConversa();
}
