package br.com.sysprise.pessoa.service.pessoa;

import br.com.sysprise.pessoa.model.pessoa.DadosAtualizarPessoa;
import br.com.sysprise.pessoa.model.pessoa.Pessoa;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public abstract class AtualizarPessoa {

    private Optional<AtualizarPessoa> proximo;

    public AtualizarPessoa(Optional<AtualizarPessoa> proximo) {
        this.proximo = proximo;
    }

    public abstract void atualizar(DadosAtualizarPessoa dadosAtualizar, Pessoa pessoa);

    public abstract boolean haRegistroParaAtualizar(DadosAtualizarPessoa dadosAtualizar);

    public void executar(DadosAtualizarPessoa dadosAtualizar, Pessoa pessoa) {
        if(haRegistroParaAtualizar(dadosAtualizar))
            atualizar(dadosAtualizar, pessoa);

        proximo.ifPresent(p -> p.executar(dadosAtualizar, pessoa));
    }
}
