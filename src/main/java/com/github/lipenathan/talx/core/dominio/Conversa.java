package com.github.lipenathan.talx.core.dominio;

import com.github.lipenathan.talx.infra.exception.NegocioException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa a entidade conversas
 *
 * @author lipen
 * @version 1.0 28/08/21
 * @since 28/08/21
 */
@Entity
public class Conversa {

    /**
     * armazena o id da conversa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * armazena o usuario emissor da mensagem.
     */
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Usuario> usuarios = new ArrayList<>();
    /**
     * armazena uma lista com as mensagens da conversa.
     */
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Mensagem> mensagens = new ArrayList<>();
    /**
     * Armazena a data e horario da ultima mensagem.
     */
    private Date dataHoraUltimaMensagem;

    public Conversa(List<Usuario> usuario) {
        this.usuarios = usuario;
    }

    public Conversa() {
    }

    public void adicionarMensagem(Mensagem mensagem) {
        this.mensagens.add(mensagem);
        setDataHoraUltimaMensagem(mensagem.getData());
    }

    // validações e regra de negócio
    /**
     * metódo que valida dados da conversa
     */
    public void validarConversa() throws NegocioException {
        if (this.usuarios.size() < 2) {
            throw new NegocioException("Conversa precisa conter usuários");
        }
    }


    // getters e setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
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
                "id=" + id +
                ", usuarios=" + usuarios +
                ", mensagens=" + mensagens +
                ", dataHoraUltimaMensagem=" + dataHoraUltimaMensagem +
                '}';
    }
}
