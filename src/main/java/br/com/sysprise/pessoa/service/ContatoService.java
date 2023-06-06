package br.com.sysprise.pessoa.service;


import br.com.sysprise.pessoa.model.contato.DadosCadastroContato;
import br.com.sysprise.pessoa.model.pessoa.Pessoa;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import pb.CriarContatoRequest;
import pb.blank;
import pb.ListaContatos;

import java.util.List;


@Service
public class ContatoService {

    @GrpcClient("contato")
    private pb.ContatoServiceGrpc.ContatoServiceStub contatoStub;

    @GrpcClient("contato")
    private pb.ContatoServiceGrpc.ContatoServiceBlockingStub blockingStub;

    public void cadastrar(List<DadosCadastroContato> requests, Pessoa pessoa) {
        // Create a StreamObserver<Blank> to use as argument to the method and them get a StreamObserver<CriarContatoRequest>
        StreamObserver<CriarContatoRequest> streamContato = contatoStub.createContatos(this.getStreamObserver());
        // Send request
        requests.stream().map(contato -> CriarContatoRequest
                .newBuilder()
                .setEmail(contato.email())
                .setTelefone(contato.telefone())
                .setPessoaId(pessoa.getId())
                .build()
        ).forEach(streamContato::onNext);
        // Mark the end of requests
        streamContato.onCompleted();
    }

    public StreamObserver<pb.blank>  getStreamObserver() {
        return new StreamObserver<>() {
            @Override
            public void onNext(blank blank) {
                System.out.println("Registro cadastrado com sucesso");
            }

            @Override
            public void onError(Throwable throwable) {
                throw new RuntimeException(throwable);
            }

            @Override
            public void onCompleted() {
                System.out.println("Contatos cadastrados");
            }
        };
    }

    public ListaContatos getContatosByPessoaId(Long id) {
        return blockingStub.getContatoByPessoaId(pb.PessoaId.newBuilder().setId(id).build());
    }
}
