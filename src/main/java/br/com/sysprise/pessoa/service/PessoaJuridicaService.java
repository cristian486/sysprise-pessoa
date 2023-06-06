package br.com.sysprise.pessoa.service;

import br.com.sysprise.pessoa.model.pessoa.juridica.*;
import br.com.sysprise.pessoa.model.tipo.Tipo;
import br.com.sysprise.pessoa.repository.PessoaJuridicaRepository;
import br.com.sysprise.pessoa.service.pessoa.AtualizarPessoa;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PessoaJuridicaService {

    private final TipoService tipoService;
    private final ContatoService contatoService;
    private final EnderecoService enderecoService;
    private final List<AtualizarPessoa> atualizarAtributosPessoa;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    public PessoaJuridica findPessoaJuridicaById(Long id) {
        return pessoaJuridicaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("A pessoa jurídica requisitada não foi encontrada!"));
    }

    public Page<DadosListagemPessoaJuridica> listar(Pageable pageable) {
        return pessoaJuridicaRepository.findAllByHabilitadoTrue(pageable).map(DadosListagemPessoaJuridica::new);
    }

    public DadosDetalhamentoPessoaJuridica detalhar(Long id) {
        PessoaJuridica pessoaJuridica = this.findPessoaJuridicaById(id);
        pb.ListaContatos listaContatos = contatoService.getContatosByPessoaId(id);
        pb.Endereco endereco = enderecoService.getEnderecoByid(pessoaJuridica.getEnderecoId());
        return new DadosDetalhamentoPessoaJuridica(pessoaJuridica, listaContatos, endereco);
    }

    public DadosDetalhamentoPessoaJuridica cadastrar(DadosCadastroPessoaJuridica dadosCadastro){
        List<Tipo> tipos = tipoService.findAllById(dadosCadastro.tipos());
        PessoaJuridica pessoaJuridica = new PessoaJuridica(dadosCadastro, tipos);
        pessoaJuridicaRepository.save(pessoaJuridica);
        contatoService.cadastrar(dadosCadastro.contatos(), pessoaJuridica);
        enderecoService.cadastrar(dadosCadastro.endereco(), pessoaJuridica);
        return new DadosDetalhamentoPessoaJuridica(pessoaJuridica);
    }

    public DadosDetalhamentoPessoaJuridica atualizar(DadosAtualizarPessoaJuridica dadosAtualizar) {
        PessoaJuridica pessoaJuridica = this.findPessoaJuridicaById(dadosAtualizar.getId());
        pessoaJuridica.atualizarCadastro(dadosAtualizar);
        atualizarAtributosPessoa.forEach(a -> a.executar(dadosAtualizar, pessoaJuridica));
        pessoaJuridicaRepository.flush();
        return new DadosDetalhamentoPessoaJuridica(pessoaJuridica);
    }

    public void deletar(Long id) {
        PessoaJuridica pessoaJuridica = this.findPessoaJuridicaById(id);
        // TODO
    }
}
