package com.github.lipenathan.talx.porta.web;

import com.github.lipenathan.talx.core.dominio.Usuario;
import com.github.lipenathan.talx.core.processos.contratos.ProcessosLogin;
import com.github.lipenathan.talx.core.processos.imp.ProcessosLoginImp;
import com.github.lipenathan.talx.infra.exception.PersistenceException;
import com.github.lipenathan.talx.infra.web.RedirectView;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class PortaLoginForm implements Serializable {

    private static final Long serialVersionUID = 1L;
    private String login;
    private String senha;

    private final ProcessosLogin processosLogin = new ProcessosLoginImp();

    public RedirectView logar() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        System.out.println("Realizando login com usuário - " + login);
        try {
            Usuario usuario = processosLogin.logar(login, senha);
            facesContext.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
            return new RedirectView("conversas");
        } catch (PersistenceException e) {
            facesContext.getExternalContext().getFlash().setKeepMessages(true);
            facesContext.addMessage(null, new FacesMessage("Erro ao logar - " + e));
            System.out.println(e);
        } catch (Exception e) {
            facesContext.getExternalContext().getFlash().setKeepMessages(true);
            facesContext.addMessage(null, new FacesMessage("Erro inesperado - " + e));
            System.out.println(e);
        }
        return null;
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
