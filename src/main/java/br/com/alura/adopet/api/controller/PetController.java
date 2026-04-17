package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.pet.DadosPetDTO;
import br.com.alura.adopet.api.dto.pet.PetCadastroDTO;
import br.com.alura.adopet.api.exception.ValidationException;
import br.com.alura.adopet.api.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService service;

    public PetController(PetService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DadosPetDTO>> listarTodosDisponiveis() {
        List<DadosPetDTO> disponiveis = service.listarTodosDisponiveis();
        return ResponseEntity.ok(disponiveis);
    }

    @GetMapping("/{idOuNome}")
    public ResponseEntity<?> buscarPetPorIdOuNome(@PathVariable String idOuNome) {
        try {
            DadosPetDTO dto = service.buscarPetPorIdOuNome(idOuNome);
            return ResponseEntity.ok(dto);
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarPet(@RequestBody @Valid PetCadastroDTO dto) {

        try {
            DadosPetDTO resposta = service.cadastrarPet(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }


    }


}
