package br.com.sysprise.pessoa.model.endereco;



public record DadosDetalhamentoEndereco(Long id, String rua, Integer numero, String bairro,
                                        String complemento, String cep, Long cidade_id, Long estado_id) {

    public DadosDetalhamentoEndereco(pb.Endereco endereco) {
        this(endereco.getId(), endereco.getRua(), endereco.getNumero(), endereco.getBairro(),
                endereco.getComplemento(), endereco.getCep(), endereco.getCidadeId(), endereco.getEstadoId());
    }
}
