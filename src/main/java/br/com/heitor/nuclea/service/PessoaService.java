package br.com.heitor.nuclea.service;

import br.com.heitor.nuclea.exception.ResourceNotFoundException;
import br.com.heitor.nuclea.model.entities.Pessoa;
import br.com.heitor.nuclea.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> getAllPessoas(Boolean funcionario) {
        if (Objects.isNull(funcionario) || funcionario == Boolean.FALSE) {
            return pessoaRepository.findAll();
        } else {
            return pessoaRepository.findAllByFuncionarioTrue();
        }
    }

    public Pessoa getPessoaById(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa not found with id: " + id));
    }

    public Pessoa createPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa updatePessoa(Long id, Pessoa updatedPessoa) {
        Pessoa existingPessoa = pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa not found with id: " + id));;
        if (existingPessoa != null) {
            existingPessoa.setNome(updatedPessoa.getNome());
            existingPessoa.setDataNascimento(updatedPessoa.getDataNascimento());
            existingPessoa.setCpf(updatedPessoa.getCpf());
            existingPessoa.setFuncionario(updatedPessoa.isFuncionario());
            return pessoaRepository.save(existingPessoa);
        }
        return null;
    }

    public Pessoa deletePessoa(Long id) {
        Pessoa pessoa = getPessoaById(id);
        pessoaRepository.deleteById(id);
        return pessoa;
    }
}