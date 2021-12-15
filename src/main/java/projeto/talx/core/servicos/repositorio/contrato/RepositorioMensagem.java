package projeto.talx.core.servicos.repositorio.contrato;

import projeto.talx.core.dominio.Mensagem;
import projeto.talx.infra.exception.NegocioException;

import java.util.List;
import java.util.Stack;

public interface RepositorioMensagem {
    void inserir(Mensagem o) throws NegocioException;

    void deletar(Mensagem o) throws NegocioException;

    List<Mensagem> listar() throws NegocioException;

    Mensagem getById(Integer id) throws NegocioException;

    void alterar(Mensagem o) throws NegocioException;

    Stack<Mensagem> getByConversaId(Integer id) throws NegocioException;
}
