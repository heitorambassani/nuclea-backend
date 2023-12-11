package br.com.heitor.nuclea.controller;

import br.com.heitor.nuclea.exception.ResourceNotFoundException;
import br.com.heitor.nuclea.exception.StatusProjetoException;
import br.com.heitor.nuclea.model.entities.Pessoa;
import br.com.heitor.nuclea.model.entities.Projeto;
import br.com.heitor.nuclea.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {
    private final ProjetoService projetoService;

    @Autowired
    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping
    public List<Projeto> getAllProjetos() {
        return projetoService.getAllProjetos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getProjetoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(projetoService.getProjetoById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Projeto> createProjeto(@RequestBody Projeto projeto) {
        try {
            return ResponseEntity.ok().body(projetoService.createProjeto(projeto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> updateProjeto(@PathVariable Long id, @RequestBody Projeto updatedProjeto) {
        try {
            return ResponseEntity.ok().body(projetoService.updateProjeto(id, updatedProjeto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjeto(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(projetoService.deleteProjeto(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (StatusProjetoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{idProjeto}/membros")
    public ResponseEntity<?> addMembroToProjeto(@PathVariable Long idProjeto, @RequestParam Long idPessoa) {
        try {
            projetoService.addMembroToProjeto(idProjeto, idPessoa);
            return new ResponseEntity<>("Membro adicionado com sucesso", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{idProjeto}/membros")
    public Set<Pessoa> getAllMembrosFromProject(@PathVariable Long idProjeto) {
        return projetoService.getAllMembrosFromProject(idProjeto);
    }

    @DeleteMapping("/{idProjeto}/membros")
    public ResponseEntity<?> delMembroToProjeto(@PathVariable Long idProjeto, @RequestParam Long idPessoa) {
        try {
            projetoService.delMembroToProjeto(idProjeto, idPessoa);
            return new ResponseEntity<>("Membro removido com sucesso", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (StatusProjetoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }    }
}
