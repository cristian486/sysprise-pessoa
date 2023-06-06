package br.com.sysprise.pessoa.model.contato;

public record DadosListagemContato(Long id, String email, String telefone) {

    public DadosListagemContato(pb.Contato contato) {
        this(contato.getId(), contato.getEmail(), contato.getTelefone());
    }
}
