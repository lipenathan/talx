package com.github.lipenathan.talx.core.servicos.repositorio.contrato;

import com.github.lipenathan.talx.core.dominio.Conversa;
import com.github.lipenathan.talx.infra.exception.NegocioException;

import java.util.List;

public interface RepositorioConversa {
    void inserir(Conversa o) throws NegocioException;

    void deletar(Conversa o) throws NegocioException;

    List<Conversa> listar() throws NegocioException;

    Conversa getById(Integer id) throws NegocioException;

    void alterar(Conversa o) throws NegocioException;

    List<Conversa> getConversasUsuario(Integer idUsuario) throws NegocioException;

    RepositorioMensagem getRepMensagem();
}
