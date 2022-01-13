package com.github.lipenathan.talx.core.servicos.repositorio.contrato;

import com.github.lipenathan.talx.core.dominio.Mensagem;
import com.github.lipenathan.talx.infra.exception.PersistenceException;

import java.util.List;

public interface RepositorioMensagem {
    void inserir(Mensagem o) throws PersistenceException;

    void deletar(Mensagem o) throws PersistenceException;

    List<Mensagem> listar() throws PersistenceException;

    Mensagem getById(Integer id) throws PersistenceException;

    void alterar(Mensagem o) throws PersistenceException;

    List<Mensagem> getByConversaId(Integer id) throws PersistenceException;
}
