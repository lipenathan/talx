package com.github.lipenathan.talx.core.servicos.repositorio.imp.fabrica;

import com.github.lipenathan.talx.core.servicos.repositorio.imp.RepConversaMockImp;
import com.github.lipenathan.talx.core.servicos.repositorio.imp.RepUsuarioMockImp;
import com.github.lipenathan.talx.core.servicos.repositorio.contrato.RepositorioConversa;
import com.github.lipenathan.talx.core.servicos.repositorio.imp.RepMensagemMockImp;
import com.github.lipenathan.talx.core.servicos.repositorio.contrato.RepositorioMensagem;
import com.github.lipenathan.talx.core.servicos.repositorio.contrato.RepositorioUsuario;

/**
 * Classe responsável por fabricar instância de repositorio de Usuario
 * @author lipen
 * @version 1.0 07/09/21
 * @since 07/09/21
 */
public class FabricaRepositorioUsuario {
    private static RepositorioUsuario repositorioUsuario;
    private RepositorioMensagem repositorioMensagem;
    private RepositorioConversa repositorioConversa;

    public FabricaRepositorioUsuario() {
        this.repositorioMensagem = new RepMensagemMockImp();
        this.repositorioConversa = new RepConversaMockImp(repositorioMensagem);
        this.repositorioUsuario = new RepUsuarioMockImp(repositorioConversa);
    }

    public static RepositorioUsuario getRepositorioUsuario() {
        if (repositorioUsuario == null) {
            new FabricaRepositorioUsuario();
            return repositorioUsuario;
        }
        return repositorioUsuario;
    }
}
