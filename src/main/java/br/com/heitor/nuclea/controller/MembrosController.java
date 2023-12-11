package br.com.heitor.nuclea.controller;

import br.com.heitor.nuclea.exception.ResourceNotFoundException;
import br.com.heitor.nuclea.exception.StatusProjetoException;
import br.com.heitor.nuclea.model.entities.Membros;
import br.com.heitor.nuclea.model.entities.Projeto;
import br.com.heitor.nuclea.service.MembrosService;
import br.com.heitor.nuclea.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/membros")
public class MembrosController {
    private final MembrosService membrosService;

    @Autowired
    public MembrosController(MembrosService membrosService) {
        this.membrosService = membrosService;
    }

    @GetMapping("/{idprojeto}")
    public ResponseEntity<List<Membros>> getAllMembrosByIdprojeto(@PathVariable Long idprojeto) {
        List<Membros> membros = membrosService.getAllMembrosByIdprojeto(idprojeto);
        return new ResponseEntity<>(membros, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addMembro(@RequestParam Long idProjeto, @RequestParam Long idPessoa) {
        membrosService.addMembro(idProjeto, idPessoa);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @DeleteMapping("/{idprojeto}/{idpessoa}")
//    public ResponseEntity<Void> deleteMembrosByIds(@PathVariable Long idprojeto, @PathVariable Long idpessoa) {
//        membrosService.deleteMembrosByIds(idprojeto, idpessoa);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
