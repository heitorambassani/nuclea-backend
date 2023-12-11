package br.com.heitor.nuclea.service;

import br.com.heitor.nuclea.exception.ResourceNotFoundException;
import br.com.heitor.nuclea.exception.StatusProjetoException;
import br.com.heitor.nuclea.model.entities.Pessoa;
import br.com.heitor.nuclea.model.entities.Projeto;
import br.com.heitor.nuclea.model.enums.Risco;
import br.com.heitor.nuclea.model.enums.StatusProjeto;
import br.com.heitor.nuclea.repository.PessoaRepository;
import br.com.heitor.nuclea.repository.ProjetoRepository;
import br.com.heitor.nuclea.service.valid.ProjetoValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final PessoaRepository pessoaRepository;
    private final ProjetoValidate projetoValidate;

    @Autowired
    public ProjetoService(ProjetoRepository projetoRepository, PessoaRepository pessoaRepository, ProjetoValidate projetoValidate) {
        this.projetoRepository = projetoRepository;
        this.pessoaRepository = pessoaRepository;
        this.projetoValidate = projetoValidate;
    }

    public List<Projeto> getAllProjetos() {
        return projetoRepository.findAll();
    }

    public Projeto getProjetoById(Long id) {
        return projetoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com id: " + id));
    }

    public Projeto createProjeto(Projeto projeto) {
        projetoValidate.validate(projeto);

        projeto.setRisco(Risco.fromTag(projeto.getRisco().getTag()));
        return projetoRepository.save(projeto);
    }

    public Projeto updateProjeto(Long id, Projeto updatedProjeto) {
        Projeto existingProjeto = projetoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com id: " + id));;
        if (existingProjeto != null) {
            existingProjeto.setNome(updatedProjeto.getNome());
            existingProjeto.setDataInicio(updatedProjeto.getDataInicio());
            existingProjeto.setDataPrevisaoFim(updatedProjeto.getDataPrevisaoFim());
            existingProjeto.setDataFim(updatedProjeto.getDataFim());
            existingProjeto.setDescricao(updatedProjeto.getDescricao());
            existingProjeto.setStatus(updatedProjeto.getStatus());
            existingProjeto.setOrcamento(updatedProjeto.getOrcamento());
            existingProjeto.setRisco(updatedProjeto.getRisco());
            existingProjeto.setGerente(updatedProjeto.getGerente());
            return projetoRepository.save(existingProjeto);
        }
        return null;
    }

    public Projeto deleteProjeto(Long id) {
        Projeto projeto = getProjetoById(id);

        if (
                Objects.equals(projeto.getStatus().getId(), StatusProjeto.INICIADO.getId()) ||
                Objects.equals(projeto.getStatus().getId(), StatusProjeto.EM_ANDAMENTO.getId()) ||
                Objects.equals(projeto.getStatus().getId(), StatusProjeto.ENCERRADO.getId())
        ) {
            throw new StatusProjetoException("Projeto com status não pode ser apagado: " + projeto.getStatus().getTag());
        }

        projetoRepository.deleteById(id);
        return projeto;
    }

    public void addMembroToProjeto(Long idProjeto, Long idPessoa) {
        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));

        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada"));

        if (!pessoa.isFuncionario()) {
            throw new IllegalArgumentException("Pessoa precisa ser funcionária");
        }

        projeto.getMembros().add(pessoa);

        projetoRepository.save(projeto);
    }

    public Set<Pessoa> getAllMembrosFromProject(Long idProjeto) {
        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));

        return projeto.getMembros();
    }

    public void delMembroToProjeto(Long idProjeto, Long idPessoa) {
        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));

        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada"));

        projeto.getMembros().remove(pessoa);

        projetoRepository.save(projeto);
    }
}