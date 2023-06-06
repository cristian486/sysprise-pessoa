package br.com.sysprise.pessoa.service.pessoa;

import br.com.sysprise.pessoa.model.pessoa.DadosAtualizarPessoa;
import br.com.sysprise.pessoa.model.pessoa.Pessoa;
import br.com.sysprise.pessoa.model.tipo.Tipo;
import br.com.sysprise.pessoa.service.TipoService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component("atualizarTipo")
public class AtualizarPessoaTipo extends AtualizarPessoa {

    private final TipoService tipoService;
    public AtualizarPessoaTipo(TipoService tipoService) {
        super(Optional.empty());
        this.tipoService = tipoService;
    }

    @Override
    public void atualizar(DadosAtualizarPessoa dadosAtualizar, Pessoa pessoa) {
        List<Long> dadosAtualizarTipo = dadosAtualizar.getTipos().get();
        Set<Tipo> tiposDaPessoa = pessoa.getTipos();
        tiposDaPessoa.forEach(pessoa::removerTipo);
        dadosAtualizarTipo.forEach(id -> {
            Tipo tipo = tipoService.findTipoById(id);
            pessoa.adicionarTipo(tipo);
        });
    }

    @Override
    public boolean haRegistroParaAtualizar(DadosAtualizarPessoa dadosAtualizar) {
        return dadosAtualizar.getTipos() != null && dadosAtualizar.getTipos().isPresent();
    }
}
