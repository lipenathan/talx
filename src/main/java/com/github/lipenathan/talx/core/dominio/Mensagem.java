package com.github.lipenathan.talx.core.dominio;

import com.github.lipenathan.talx.infra.exception.NegocioException;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Classe que representa entidade mensagem
 *
 * @author lipen
 * @version 1.0 28/08/21
 * @since 28/08/21
 */
@Entity
public class Mensagem {

    /** armazena o id da mensagem. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** armazena o id da conversa. */
    @ManyToOne
    private Conversa conversa;
    /**
     * Armazena nome do emissor.
     */
    @ManyToOne
    private Usuario usuarioEmissor;
    /**
     * Armazena o conteúdo em texto da mensagem.
     */
    @Column(name = "texto_mensagem")
    private String mensagem;
    /**
     * Armazena a data e hora da mensagem.
     */
    @Column(name = "data_mensagem")
    private Date data;

    public Mensagem(Conversa conversa, Usuario usuarioEmissor, String mensagem) {
        this.conversa = conversa;
        this.usuarioEmissor = usuarioEmissor;
        this.mensagem = mensagem;
        data = new Date();
    }

    public Mensagem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Conversa getConversa() {
        return conversa;
    }

    public void setConversa(Conversa conversa) {
        this.conversa = conversa;
    }

    public Usuario getUsuarioEmissor() {
        return usuarioEmissor;
    }

    public void setUsuarioEmissor(Usuario usuarioEmissor) {
        usuarioEmissor = usuarioEmissor;
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

    public void setData(Date data) {
        this.data = data;
    }

    // TO STRING

    @Override
    public String toString() {
        return "Mensagem{" +
                "id=" + id +
                ", conversa=" + conversa +
                ", UsuarioEmissor=" + usuarioEmissor +
                ", mensagem='" + mensagem + '\'' +
                ", data=" + data +
                '}';
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
        if (this.usuarioEmissor == null) {
            throw new NegocioException("nome do emissor não pode ser nulo");
        }
        if (this.data == null) {
            throw new NegocioException("data da mensagem não pode ser nula");
        }
    }
}
