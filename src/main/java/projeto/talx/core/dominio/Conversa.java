package projeto.talx.core.dominio;

import projeto.talx.infra.exception.NegocioException;

import java.util.Date;
import java.util.Objects;
import java.util.Stack;

/**
 * Classe que representa a entidade conversas
 *
 * @author lipen
 * @version 1.0 28/08/21
 * @since 28/08/21
 */
public class Conversa {

    /**
     * armazena o id da conversa.
     */
    private Integer id;
    /**
     * armazena o usuario emissor da mensagem.
     */
    private Integer idP1;
    /**
     * armazena o usuario reseptor da mensagem.
     */
    private Integer idP2;
    /**
     * armazena uma lista com as mensagens da conversa.
     */
    private Stack<Mensagem> mensagens;
    /** Armazena a data e horario da ultima mensagem. */
    private Date dataHoraUltimaMensagem;

    public Conversa(Integer idP1, Integer idP2) {
        this.idP1 = idP1;
        this.idP2 = idP2;
    }

    public Conversa() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdP1() {
        return idP1;
    }

    public void setIdP1(Integer idP1) {
        this.idP1 = idP1;
    }

    public Integer getIdP2() {
        return idP2;
    }

    public void setIdP2(Integer idP2) {
        this.idP2 = idP2;
    }

    public Stack<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(Stack<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    public Date getDataHoraUltimaMensagem() {
        return dataHoraUltimaMensagem;
    }

    public void setDataHoraUltimaMensagem(Date dataHoraUltimaMensagem) {
        this.dataHoraUltimaMensagem = dataHoraUltimaMensagem;
    }
    // EQUALS E HASHCODE

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversa conversa = (Conversa) o;
        return Objects.equals(id, conversa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // TO STRING

    @Override
    public String toString() {
        return "Conversa{" +
                "id= " + id +
                "; emissor= " + idP1 +
                "; receptor= " + idP2 +
                "; hora ult. mensagem= " + dataHoraUltimaMensagem;
    }


    // validações e regra de negócio

    /**
     * metódo que valida dados da conversa
     */
    void validarConversa() throws NegocioException {
        if (this.idP1 == null) {
            throw new NegocioException("Emissor não pode ser nulo");
        }
        if (this.idP2 == null) {
            throw new NegocioException("Receptor não pode ser nulo");
        }
        if (this.idP2 == this.idP1) {
            throw new NegocioException("Emissor não pode ser igual receptor");
        }
    }
}
