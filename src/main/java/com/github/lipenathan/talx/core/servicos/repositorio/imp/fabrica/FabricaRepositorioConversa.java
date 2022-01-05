package com.github.lipenathan.talx.core.servicos.repositorio.imp.fabrica;

import com.github.lipenathan.talx.core.servicos.repositorio.imp.RepConversaMockImp;
import com.github.lipenathan.talx.core.servicos.repositorio.contrato.RepositorioConversa;
import com.github.lipenathan.talx.core.servicos.repositorio.imp.RepMensagemMockImp;
import com.github.lipenathan.talx.core.servicos.repositorio.contrato.RepositorioMensagem;

public class FabricaRepositorioConversa {

    private RepositorioMensagem repositorioMensagem;
    private static RepositorioConversa repositorioConversa;

    public FabricaRepositorioConversa() {
        this.repositorioMensagem = new RepMensagemMockImp();
        this.repositorioConversa = new RepConversaMockImp(repositorioMensagem);
    }

    public static RepositorioConversa getRepositorioConversa() {
        if (repositorioConversa == null) {
            new FabricaRepositorioUsuario();
            return repositorioConversa;
        }
        return repositorioConversa;
    }
}
