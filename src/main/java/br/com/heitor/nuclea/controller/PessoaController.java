package br.com.heitor.nuclea.controller;

import br.com.heitor.nuclea.exception.ResourceNotFoundException;
import br.com.heitor.nuclea.model.entities.Pessoa;
import br.com.heitor.nuclea.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public List<Pessoa> getAllPessoas(@RequestParam(required = false) Boolean funcionario) {
        return pessoaService.getAllPessoas(funcionario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(pessoaService.getPessoaById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Pessoa createPessoa(@RequestBody Pessoa pessoa) {
        return pessoaService.createPessoa(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa updatedPessoa) {
        try {
            return ResponseEntity.ok().body(pessoaService.updatePessoa(id, updatedPessoa));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pessoa> deletePessoa(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(pessoaService.deletePessoa(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
