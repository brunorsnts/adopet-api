package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.tutor.TutorCadastroDTO;
import br.com.alura.adopet.api.dto.tutor.TutorResponseDTO;
import br.com.alura.adopet.api.dto.tutor.TutorUpdateDTO;
import br.com.alura.adopet.api.exception.ValidationException;
import br.com.alura.adopet.api.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid TutorCadastroDTO dto) {
        try {
            TutorResponseDTO response = tutorService.cadastrar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid TutorUpdateDTO dto) {
        try {
            TutorResponseDTO response = tutorService.atualizar(id, dto);
            return ResponseEntity.ok(response);
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
