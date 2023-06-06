package br.com.sysprise.pessoa.controller;

import br.com.sysprise.pessoa.model.pessoa.Pessoa;
import br.com.sysprise.pessoa.model.pessoa.fisica.PessoaFisica;
import br.com.sysprise.pessoa.model.pessoa.juridica.PessoaJuridica;
import br.com.sysprise.pessoa.service.pessoa.PessoaService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import pb.DadosPessoa;
import pb.PessoaExiste;
import pb.PessoaId;
import pb.PessoaServiceGrpc;

@GrpcService
public class PessoaGrpcController extends PessoaServiceGrpc.PessoaServiceImplBase {

    @Autowired
    private PessoaService pessoaService;

    @Override
    public void verifyExistence(PessoaId request, StreamObserver<PessoaExiste> responseObserver) {
        Boolean exists = pessoaService.existById(request.getId());
        PessoaExiste pessoaExiste = PessoaExiste.newBuilder().setExiste(exists).build();
        responseObserver.onNext(pessoaExiste);
        responseObserver.onCompleted();
    }

    @Override
    public void getPersonData(PessoaId request, StreamObserver<DadosPessoa> responseObserver) {
        Pessoa pessoa = pessoaService.findPessoaById(request.getId());
        String documento = pessoa instanceof PessoaFisica ? ((PessoaFisica) pessoa).getCpf() : ((PessoaJuridica) pessoa).getCnpj();
        DadosPessoa dadosPessoa = DadosPessoa.newBuilder()
                .setId(pessoa.getId())
                .setNome(pessoa.getNome())
                .setDocumento(documento)
                .setEnderecoId(pessoa.getEnderecoId())
                .build();

        responseObserver.onNext(dadosPessoa);
        responseObserver.onCompleted();
    }
}
