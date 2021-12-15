package projeto.talx.core.servicos.repositorio.imp;

import projeto.talx.core.dominio.Conversa;
import projeto.talx.core.servicos.repositorio.contrato.RepositorioConversa;
import projeto.talx.infra.exception.NegocioException;
import projeto.talx.core.dominio.Usuario;
import projeto.talx.core.servicos.repositorio.contrato.RepositorioUsuario;

import java.util.*;

import static com.github.lipenathan.flynn.validador.Validador.EMAIL_REGEX;
import static com.github.lipenathan.flynn.validador.Validador.MOVEL_REGEX;

/**
 * Implementa o repositorio de usuário, mockado.
 *
 * @author lipen
 * @version 1.0
 * @since 01/09/09
 */
public class RepUsuarioMockImp implements RepositorioUsuario {

    List<Usuario> banco = new ArrayList<>();

    RepositorioConversa repConversa;

        public RepUsuarioMockImp(RepositorioConversa repConversa) {
            System.out.println("Repositório de mock" + this.getClass().getSimpleName());
            this.repConversa = repConversa;
            try {
                this.inserir(new Usuario("Mock 1", "(43)99845-6685", null, "abc123"));
                this.inserir(new Usuario("Mock 2", null, "mock2@hotmail.com", "abc123"));
                this.inserir(new Usuario("Mock 3", "(41)98522-5589", null, "abcde1"));
                this.inserir(new Usuario("Mock 4", null, "mock4@hotmail.com", "add366"));
                this.inserir(new Usuario("Mock 5", null, "mock5@hotmail.com", "aaa111"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    @Override
    public void inserir(Usuario o) throws NegocioException {
        o.setId(banco.size() + 1);
        for (Usuario u : banco) {
            if (u.equals(o))
                throw new NegocioException("Usuário já cadastrado");
        }
        if (!banco.add(o))
            throw new NegocioException("Erro ao inserir Usuario");
    }

    @Override
    public void deletar(Usuario o) throws NegocioException {
        if (!banco.remove(o))
            throw new NegocioException("Erro ao remover Usuario");
    }

    @Override
    public List<Usuario> listar() {
        return banco;
    }

    @Override
    public Usuario getById(Integer id) throws NegocioException {
        for (Usuario u : banco) {
            if (u.getId() == id)
                return u;
        }
        throw new NegocioException("Id inválido");
    }

    @Override
    public void alterar(Usuario o) throws NegocioException {
        int index = banco.indexOf(o);
        banco.set(index, o);
    }

    /**
     * Recebe login e retorna o usuário referente ao login com conversas preenchidas
     * @param login contendo e-mail ou telefone
     * @return Usuario
     * @throws NegocioException caso login seja incorreto
     */
    public Usuario buscaPorLogin(String login) throws NegocioException {
        if (login.matches(EMAIL_REGEX)) {
            System.out.println("login realizado por e-mail");
            for (Usuario u : banco) {
                Optional<String> optionalLogin = Optional.ofNullable(u.getEmail());
                if (optionalLogin.isPresent()) {
                    if (optionalLogin.get().equals(login)) {
                        u.setConversas(new HashSet<Conversa>(repConversa.getConversasUsuario(u.getId())));
                        return u;
                    }
                }
            }
            System.out.println("e-mail não encontrado!");
        } else if (login.matches(MOVEL_REGEX)) {
            System.out.println("login realizado por telefone");
            for (Usuario u : banco) {
                Optional<String> optionalLogin = Optional.ofNullable(u.getTelefone());
                if (optionalLogin.isPresent()) {
                    if (optionalLogin.get().equals(login)) {
                        u.setConversas(new HashSet<Conversa>(repConversa.getConversasUsuario(u.getId())));
                        return u;
                    }
                }
            }
            System.out.println("telefone não encontrado!");
        }
        throw new NegocioException("Login Inválido");
    }

    //GETTER E SETTER
    /** Disponibiliza reposiritorio de conversa usado. */
    public RepositorioConversa getRepConversa() {
        return repConversa;
    }
}
