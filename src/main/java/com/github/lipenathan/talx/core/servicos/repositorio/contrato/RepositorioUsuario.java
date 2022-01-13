package com.github.lipenathan.talx.core.servicos.repositorio.contrato;

import com.github.lipenathan.talx.core.dominio.Usuario;
import com.github.lipenathan.talx.infra.exception.PersistenceException;

import java.util.List;

public interface RepositorioUsuario {
    void inserir(Usuario o) throws PersistenceException;

    void deletar(Usuario o) throws PersistenceException;

    List<Usuario> listar() throws PersistenceException;

    Usuario getById(Integer id) throws PersistenceException;

    void alterar(Usuario o) throws PersistenceException;

    Usuario buscaPorLogin(String login) throws PersistenceException;

    RepositorioConversa getRepConversa();
}
