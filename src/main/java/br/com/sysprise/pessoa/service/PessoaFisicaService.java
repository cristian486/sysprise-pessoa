package br.com.sysprise.pessoa.service;

import br.com.sysprise.pessoa.model.pessoa.fisica.*;
import br.com.sysprise.pessoa.model.tipo.Tipo;
import br.com.sysprise.pessoa.repository.PessoaFisicaRepository;
import br.com.sysprise.pessoa.service.pessoa.AtualizarPessoa;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pb.Endereco;
import pb.ListaContatos;

import java.util.List;

@Service
@AllArgsConstructor
public class PessoaFisicaService {

    private final TipoService tipoService;
    private final ContatoService contatoService;
    private final EnderecoService enderecoService;
    private final List<AtualizarPessoa> atualizarAtributosPessoa;
    private final PessoaFisicaRepository pessoaFisicaRepository;

    public PessoaFisica findPessoaFisicaById(Long id) {
        return pessoaFisicaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("A pessoa física requisitada não foi encontrada!"));
    }

    public Page<DadosListagemPessoaFisica> listar(Pageable pageable) {
        return pessoaFisicaRepository.findAllByHabilitadoTrue(pageable).map(DadosListagemPessoaFisica::new);
    }

    public DadosDetalhamentoPessoaFisica detalhar(Long id) {
        PessoaFisica pessoaFisica = this.findPessoaFisicaById(id);
        ListaContatos listaContatos = contatoService.getContatosByPessoaId(id);
        Endereco endereco = enderecoService.getEnderecoByid(pessoaFisica.getEnderecoId());
        return new DadosDetalhamentoPessoaFisica(pessoaFisica, endereco, listaContatos);
    }

    public DadosDetalhamentoPessoaFisica cadastrar(DadosCadastroPessoaFisica dadosCadastro) {
        List<Tipo> tipos = tipoService.findAllById(dadosCadastro.tipos());
        PessoaFisica pessoaFisica = new PessoaFisica(dadosCadastro, tipos);
        pessoaFisicaRepository.save(pessoaFisica);
        contatoService.cadastrar(dadosCadastro.contatos(), pessoaFisica);
        enderecoService.cadastrar(dadosCadastro.endereco(), pessoaFisica);
        return new DadosDetalhamentoPessoaFisica(pessoaFisica);
    }

    public DadosDetalhamentoPessoaFisica atualizar(DadosAtualizarPessoaFisica dadosAtualizar) {
        PessoaFisica pessoaFisica = this.findPessoaFisicaById(dadosAtualizar.getId());
        pessoaFisica.atualizarCadastro(dadosAtualizar);
        atualizarAtributosPessoa.forEach(a -> a.executar(dadosAtualizar, pessoaFisica));
        pessoaFisicaRepository.flush();
        return new DadosDetalhamentoPessoaFisica(pessoaFisica);
    }

    public void deletar(Long id) {
        PessoaFisica pessoaFisica = this.findPessoaFisicaById(id);
        // TODO
    }
}
