package projeto.talx.core.servicos.repositorio.imp;

import projeto.talx.core.dominio.Conversa;
import projeto.talx.infra.exception.NegocioException;
import projeto.talx.core.servicos.repositorio.contrato.RepositorioConversa;
import projeto.talx.core.servicos.repositorio.contrato.RepositorioMensagem;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa o repositorio de usuário, mockado.
 *
 * @author lipen
 * @version 1.0
 * @since 01/09/09
 */
public class RepConversaMockImp implements RepositorioConversa {

    /** Representa banco de dados. */
    List<Conversa> banco = new ArrayList<>();

    /** "Injeta" mensagens em mock. */
    RepositorioMensagem repMensagem;

    public RepConversaMockImp(RepositorioMensagem repMensagem) {
        this.repMensagem = repMensagem;
        System.out.println("Repositório de mock" + this.getClass().getSimpleName());
        /** Dá acesso aos usuários criados em mock */
        try {
            this.inserir(new Conversa(1, 2));
            this.inserir(new Conversa(2, 3));
            this.inserir(new Conversa(3, 4));
            this.inserir(new Conversa(4, 5));
        } catch (NegocioException e) {
            e.printStackTrace();
        }
    }

//    /** Classe criada apenas para popular conversas em mock. */
//    public RepConversaMockImp() {
//        System.out.println("Repositório de mock" + this.getClass().getSimpleName());
//        /** Dá acesso aos usuários criados em mock */
//        try {
//            this.inserir(new Conversa(1, 2));
//            this.inserir(new Conversa(2, 3));
//            this.inserir(new Conversa(3, 2));
//            this.inserir(new Conversa(4, 5));
//        } catch (NegocioException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void inserir(Conversa o) throws NegocioException {
        o.setId(banco.size() + 1);
        if (!banco.add(o))
            throw new NegocioException("Erro ao inserir conversa");
    }

    @Override
    public void deletar(Conversa o) throws NegocioException {
        if (!banco.remove(o))
            throw new NegocioException("Erro ao remover Conversa");
    }

    @Override
    public List<Conversa> listar() {
        return banco;
    }

    @Override
    public Conversa getById(Integer id) throws NegocioException {
        for (Conversa c : banco) {
            if (c.getId() == id)
                return c;
        }
        throw new NegocioException("id inválido");
    }

    /**
     * Recebe id do usuario e retorna suas conversas com mensagens preenchidas.
     * @param idUsuario a ser buscada conversas.
     * @return lista de conversas
     * @throws NegocioException caso id não seja correto.
     */
    public List<Conversa> getConversasUsuario(Integer idUsuario) throws NegocioException {
        List<Conversa> conversasUsuario = new ArrayList<>();
        for (Conversa c : banco) {
            if (c.getIdP1() == idUsuario || c.getIdP2() == idUsuario){
                c.setMensagens(repMensagem.getByConversaId(c.getId()));
                conversasUsuario.add(c);
            }
        }
        return conversasUsuario;
    }

    @Override
    public void alterar(Conversa o) {
        int index = banco.indexOf(o);
        banco.set(index, o);
    }

    //GETTER E SETTER
    /** Disponibiliza reposiritorio de mensagem usado. */
    public RepositorioMensagem getRepMensagem() {
        return repMensagem;
    }
}
