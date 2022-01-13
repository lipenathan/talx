package com.github.lipenathan.talx.core.servicos.repositorio.contrato;

import com.github.lipenathan.talx.core.dominio.Conversa;
import com.github.lipenathan.talx.infra.exception.PersistenceException;

import java.util.List;

public interface RepositorioConversa {
    void inserir(Conversa o) throws PersistenceException;

    void deletar(Conversa o) throws PersistenceException;

    List<Conversa> listar() throws PersistenceException;

    Conversa getById(Integer id) throws PersistenceException;

    void alterar(Conversa o) throws PersistenceException;

    List<Conversa> getConversasUsuario(Integer idUsuario) throws PersistenceException;

    RepositorioMensagem getRepMensagem();
}
