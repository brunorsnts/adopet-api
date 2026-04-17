package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.abrigo.AbrigoCadastroDTO;
import br.com.alura.adopet.api.dto.abrigo.DadosAbrigoDTO;
import br.com.alura.adopet.api.exception.ValidationException;
import br.com.alura.adopet.api.service.AbrigoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    private final AbrigoService service;

    public AbrigoController(AbrigoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DadosAbrigoDTO>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid AbrigoCadastroDTO dto) {
        try {
            DadosAbrigoDTO dadosAbrigoDTO = service.cadastrar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(dadosAbrigoDTO);
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
