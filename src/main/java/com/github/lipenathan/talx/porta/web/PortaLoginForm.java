package com.github.lipenathan.talx.porta.web;

import com.github.lipenathan.talx.core.dominio.Usuario;
import com.github.lipenathan.talx.core.processos.ProcessoLogin;
import com.github.lipenathan.talx.core.servicos.repositorio.imp.fabrica.FabricaRepositorioUsuario;
import com.github.lipenathan.talx.infra.exception.NegocioException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class PortaLoginForm implements Serializable {

    private static final Long serialVersionUID = 1L;
    private String login;
    private String senha;
    private ProcessoLogin processoLogin;

    public PortaLoginForm() {
        this.processoLogin = new ProcessoLogin(FabricaRepositorioUsuario.getRepositorioUsuario());
    }

    public void logar() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        System.out.println("Realizando login com usuário - " + login);
        try {
            Usuario usuario = processoLogin.logar(login, senha);
            facesContext.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
        } catch (NegocioException e) {
            facesContext.getExternalContext().getFlash().setKeepMessages(true);
            facesContext.addMessage(null, new FacesMessage("Usuário não encontrado"));
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
