package br.com.sysprise.pessoa.service.pessoa;

import br.com.sysprise.pessoa.model.pessoa.DadosAtualizarPessoa;
import br.com.sysprise.pessoa.model.pessoa.Pessoa;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pb.Endereco;
import pb.EnderecoServiceGrpc;

import java.util.Optional;

@Component("atualizarEndereco")
public class AtualizarPessoaEndereco extends AtualizarPessoa {

    @GrpcClient("endereco")
    private EnderecoServiceGrpc.EnderecoServiceBlockingStub enderecoStub;

    public AtualizarPessoaEndereco(@Qualifier("atualizarTipo") Optional<AtualizarPessoa> proximo) {
        super(proximo);
    }

    @Override
    public void atualizar(DadosAtualizarPessoa dadosAtualizar, Pessoa pessoa) {
        Endereco endereco = dadosAtualizar.getEndereco().get();
        enderecoStub.updateEndereco(endereco);
    }

    @Override
    public boolean haRegistroParaAtualizar(DadosAtualizarPessoa dadosAtualizar) {
        return dadosAtualizar.getEndereco() != null && dadosAtualizar.getEndereco().isPresent();
    }

}
