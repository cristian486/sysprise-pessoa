package br.com.sysprise.pessoa.service;


import br.com.sysprise.pessoa.model.endereco.DadosCadastroEndereco;
import br.com.sysprise.pessoa.model.pessoa.Pessoa;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import pb.Endereco;
import pb.EnderecoId;


@Service
public class EnderecoService {

    @GrpcClient("endereco")
    private pb.EnderecoServiceGrpc.EnderecoServiceBlockingStub enderecoStub;

    public void cadastrar(DadosCadastroEndereco dadosCadastro, Pessoa pessoa) {
        pb.CriarEnderecoRequest request = pb.CriarEnderecoRequest.newBuilder()
                .setBairro(dadosCadastro.bairro())
                .setCep(dadosCadastro.cep())
                .setComplemento(dadosCadastro.complemento())
                .setCidadeId(dadosCadastro.cidade_id())
                .setNumero(dadosCadastro.numero())
                .setRua(dadosCadastro.rua())
                .build();
        EnderecoId enderecoId = enderecoStub.createEndereco(request);
        pessoa.definirEnderecoId(enderecoId.getId());
    }

    public Endereco getEnderecoByid(Long id) {
        return enderecoStub.getEnderecoById(EnderecoId.newBuilder().setId(id).build());
    }

}
