package projeto.talx.core.dominio;

import projeto.talx.infra.exception.NegocioException;

import java.util.Date;
import java.util.Objects;

/**
 * Classe que representa entidade mensagem
 *
 * @author lipen
 * @version 1.0 28/08/21
 * @since 28/08/21
 */
public class Mensagem {

    /** armazena o id da mensagem. */
    private Integer id;

    /** armazena o id da conversa. */
    private Integer idConversa;
    /**
     * Armazena nome do emissor.
     */
    private Integer idEmissor;
    /**
     * Armazena o conteúdo em texto da mensagem.
     */
    private String mensagem;
    /**
     * Armazena a data e hora da mensagem.
     */
    private Date data;

    public Mensagem(Integer idConversa, Integer idEmissor, String mensagem) {
        this.idConversa = idConversa;
        this.idEmissor = idEmissor;
        this.mensagem = mensagem;
        this.data = new Date();
    }

    public Mensagem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEmissor() {
        return idEmissor;
    }

    public void setIdEmissor(Integer idEmissor) {
        this.idEmissor = idEmissor;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getData() {
        return data;
    }

    public Integer getIdConversa() {
        return idConversa;
    }

    public void setIdConversa(Integer idConversa) {
        this.idConversa = idConversa;
    }

    // TO STRING

    @Override
    public String toString() {
        return "Mensagem{" +
                "id= " + id +
                "\nidConversa= " + idConversa +
                "\nidEmissor= " + idEmissor +
                "\nmensagem= " + mensagem +
                "\ndata= " + data +
                "}\n";
    }

    // EQUALS E HASHCODE

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mensagem mensagem = (Mensagem) o;
        return Objects.equals(id, mensagem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    // validações e regras de negócio

    /**
     * método que valida os dados da mensagem
     */
    void validarMensagem() throws NegocioException {
        if (this.mensagem == null) {
            throw new NegocioException("conteúdo não pode ser nulo");
        }
        if (this.mensagem.equals("")) {
            throw new NegocioException("conteúdo não pode ser vazio");
        }
        if (this.idEmissor == null) {
            throw new NegocioException("nome do emissor não pode ser nulo");
        }
        if (this.idEmissor.equals("")) {
            throw new NegocioException("nome do emissor não pode ser em branco");
        }
        if (this.data == null) {
            throw new NegocioException("data da mensagem não pode ser nula");
        }
    }
}
