package projeto.talx.core.servicos.repositorio.imp;

import projeto.talx.infra.exception.NegocioException;
import projeto.talx.core.dominio.Mensagem;
import projeto.talx.core.servicos.repositorio.contrato.RepositorioMensagem;

import java.util.Stack;

/**
 * Implementa o repositorio de usuário, mockado.
 *
 * @author lipen
 * @version 1.0
 * @since 01/09/09
 */
public class RepMensagemMockImp implements RepositorioMensagem {

    Stack<Mensagem> banco = new Stack<>();

    public RepMensagemMockImp() {
        System.out.println("Repositório de mock" + this.getClass().getSimpleName());
        try {
            this.inserir(new Mensagem(1, 1, "Esta é uma mensagem"));
            this.inserir(new Mensagem(1, 2, "Esta é outra mensagem"));
            this.inserir(new Mensagem(1, 2, "Esta é outra terceira mensagem"));
            this.inserir(new Mensagem(2, 2, "conversa 2 mensagem mock1"));
            this.inserir(new Mensagem(2, 2, "conversa 2 mensagem mock2"));
            this.inserir(new Mensagem(3, 3, "conversa 3 mensagem mock1"));
            this.inserir(new Mensagem(3, 2, "conversa 3 mensagem mock2"));
            this.inserir(new Mensagem(4, 4, "conversa 4 mensagem mock1"));
            this.inserir(new Mensagem(4, 5, "conversa 4 mensagem mock2"));
        } catch (NegocioException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inserir(Mensagem o) throws NegocioException {
        o.setId(banco.size() + 1);
        if (!banco.add(o))
            throw new NegocioException("Erro ao inserir mensagem");
    }

    @Override
    public void deletar(Mensagem o) throws NegocioException {
        if (!banco.remove(o))
            throw new NegocioException("Erro ao remover mensagem");
    }

    @Override
    public Stack<Mensagem> listar() throws NegocioException {
        return banco;
    }

    @Override
    public Stack<Mensagem> getByConversaId(Integer idConversa) {
        Stack<Mensagem> mensagens = new Stack<>();
        for (Mensagem m : banco) {
            if (m.getIdConversa() == idConversa)
                mensagens.add(m);
        }
        return mensagens;
    }

    @Override
    public Mensagem getById(Integer id) throws NegocioException {
        for (Mensagem m : banco) {
            if (m.getId() == id)
                return m;
        }
        throw new NegocioException("Id inválido");
    }

    @Override
    public void alterar(Mensagem o) {
        int index = banco.indexOf(o);
        banco.set(index, o);
    }
}
