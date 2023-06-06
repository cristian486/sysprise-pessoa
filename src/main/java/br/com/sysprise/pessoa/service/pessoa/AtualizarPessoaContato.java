package br.com.sysprise.pessoa.service.pessoa;

import br.com.sysprise.pessoa.model.pessoa.DadosAtualizarPessoa;
import br.com.sysprise.pessoa.model.pessoa.Pessoa;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pb.blank;

import java.util.Optional;


@Component("atualizarContato")
public class AtualizarPessoaContato extends AtualizarPessoa {

    @GrpcClient("contato")
    private pb.ContatoServiceGrpc.ContatoServiceBlockingStub contatoStub;

    public AtualizarPessoaContato(@Qualifier("atualizarEndereco") Optional<AtualizarPessoa> proximo) {
        super(proximo);
    }

    @Override
    public void atualizar(DadosAtualizarPessoa dadosAtualizar, Pessoa pessoa) {
        dadosAtualizar.getContatos().get().forEach(c -> {
            contatoStub.updateContato(c);
        });
    }

    @Override
    public boolean haRegistroParaAtualizar(DadosAtualizarPessoa dadosAtualizar) {
        return dadosAtualizar.getContatos() != null && dadosAtualizar.getContatos().isPresent();
    }

}
