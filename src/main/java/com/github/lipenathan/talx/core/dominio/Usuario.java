package com.github.lipenathan.talx.core.dominio;

import com.github.lipenathan.talx.infra.exception.NegocioException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.github.lipenathan.flynn.validador.Validador.*;

/**
 * Classe que representa entidade usuario.
 *
 * @author lipen
 * @version 1.0 28/08/21
 * @since 28/08/21
 */
public class Usuario {

    /**
     * armazena o id do usuario.
     */
    private Integer id;
    /**
     * armazena as conversas do usuario
     */
    private Set<Conversa> conversas;
    /**
     * Armazena os contatos do usuário.
     */
    private Set<Usuario> contatos;
    /**
     * armazena o nome do usuario.
     */
    private String nome;
    /**
     * armazena o telefone do usuario.
     */
    private String telefone;
    /**
     * armazena o email do usuario.
     */
    private String email;
    /**
     * armazena  chave do usuario.
     */
    private String chave;
    /**
     * armazena o pin do usuario.
     */
    private Integer pin;
    /**
     * armazena o status do usuario.
     */
    private String status;
    /**
     * armazena a senha do usuario.
     */
    private String senha;

    public Usuario(String nome, String telefone, String email, String senha) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(Integer id, String nome, String telefone, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    public Usuario() {
        contatos = new HashSet<>();
    }

    // GETTERS E SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Conversa> getConversas() {
        return conversas;
    }

    public void setConversas(Set<Conversa> conversas) {
        this.conversas = conversas;
    }

    // EQUALS E HASHCODE

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        if (((Usuario) o).telefone != null) return Objects.equals(telefone, usuario.telefone);
        if (((Usuario) o).email != null) return Objects.equals(email, usuario.email);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(telefone, email);
    }


    // TO STRING

    @Override
    public String toString() {
        return "Usuario{" +
                "id= " + id +
                " nome= " + nome +
                " telefone= " + telefone +
                " email= " + email +
                " senha= " + senha + "}";
    }


    // validações e regras de negócio

    /**
     * Método que une validações em uma única.
     */
    public void validar() throws NegocioException {
        validarUsuario();
        validarNome();
        validarContatoPrincipal();
        validarSenha();
    }

    public void validarUsuario() throws NegocioException {
        if (nome == null) {
            throw new NegocioException("O nome do usuario não pode ser nulo");
        }
        if (nome.equals("")) {
            throw new NegocioException("O nome do usuário não pode ser vazio");
        }
        if ((telefone == null || telefone.equals("")) && (email == null || email.equals(""))) {
            throw new NegocioException("O usuário precisa conter telefone ou e-mail");
        }
        if (senha == null) {
            throw new NegocioException("A senha não pode ser nula");
        }
        if (senha.equals("")) {
            throw new NegocioException("A senha não pode ser vazia");
        }
    }

    /**
     * Método que valida nome
     */
    public void validarNome() throws NegocioException {
        if (!this.nome.matches(NOME_REGEX)) {
            throw new NegocioException("Nome inválido");
        }
    }

    /**
     * Método que valida regra de negócia de senha.
     */
    public void validarSenha() throws NegocioException {
        if (this.senha == null) {
            throw new NegocioException("A senha não pode ser nula");
        }
        if (this.senha.length() < 6) {
            throw new NegocioException("A senha precisa conter no mínimo 6 dígitos");
        }
        if (apenasAlfabetico(this.senha)) {
            throw new NegocioException("A senha precisa ter pelo menos um caracter especial ou número");
        }
    }

    /**
     * Método que valida regra de negócio de e-mail.
     */
    public void validarEmail() throws NegocioException {
        if (this.email == null) {
            throw new NegocioException("E-mail não pode ser nulo");
        }
        if (!this.email.contains("@")) {
            throw new NegocioException("E-mail precisa conter @");
        }
        if (!this.email.matches(EMAIL_REGEX)) {
            throw new NegocioException("E-mail inválido!");
        }
    }

    /**
     * Método que valida o formato do telefone.
     */
    public void validarTelefone() throws NegocioException {
        if (this.telefone == null) {
            throw new NegocioException("Telefone não pode ser nulo");
        }
        if (!this.telefone.matches(MOVEL_REGEX)) {
            throw new NegocioException("Telefone inválido");
        }
    }

    /**
     * Método que valida contato principal.
     */
    public void validarContatoPrincipal() throws NegocioException {
        if ((telefone == null || telefone.equals("")) && (email == null || email.equals("")))
            throw new NegocioException("O usuário precisa conter telefone ou e-mail");
        if (!(telefone == null) && !(email == null)) {
            validarTelefone();
            validarEmail();
        } else if (!(email == null)) {
            validarEmail();
        } else {
            validarTelefone();
        }
    }

    // Métodos do Usuário

    /**
     * Método para adicionar um contato à lista do usuário.
     *
     * @param contato a ser adicionado a lista
     * @return true se adicionar contato, false se contato já existir
     */
    public boolean adicionarContato(Usuario contato) throws NegocioException {
        if (contato == null) {
            throw new NegocioException("Contato não pode ser nulo");
        }
        return contatos.add(contato);
    }
}
