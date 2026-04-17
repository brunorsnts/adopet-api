package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.pet.DadosPetDTO;
import br.com.alura.adopet.api.dto.pet.PetCadastroDTO;
import br.com.alura.adopet.api.exception.ValidationException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetService {

    private final PetRepository repository;
    private final AbrigoRepository abrigoRepository;

    public PetService(PetRepository repository,
                      AbrigoRepository abrigoRepository) {
        this.repository = repository;
        this.abrigoRepository = abrigoRepository;
    }

    public List<DadosPetDTO> listarTodosDisponiveis() {
        List<Pet> disponiveis = repository.findByAdotado(false);
        return disponiveis.stream()
                .map(DadosPetDTO::new)
                .toList();
    }

    public DadosPetDTO buscarPetPorIdOuNome(String idOuNome) {
        try {
            Long id = Long.parseLong(idOuNome);
            Pet pet = repository.getReferenceById(id);
            return new DadosPetDTO(pet);
        } catch (EntityNotFoundException ex) {
            throw new ValidationException("Nenhum pet com id " + idOuNome + " encontrado.");
        } catch (NumberFormatException ex) {
            Pet pet = repository.findByNome(idOuNome).orElseThrow(() -> new ValidationException("Nenhum pet com nome " + idOuNome + " encontrado."));
            return new DadosPetDTO(pet);
        }
    }

    @Transactional
    public DadosPetDTO cadastrarPet(PetCadastroDTO dto) {
        try {
            Abrigo abrigo = abrigoRepository.getReferenceById(dto.abrigoId());
            Pet pet = new Pet(dto, abrigo);
            pet = repository.save(pet);
            return new DadosPetDTO(pet);
        } catch (EntityNotFoundException ex) {
            throw new ValidationException("O abrigo informado não existe.");
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("O tipo de animal que você está tentando cadastrar é inválido, permitido apenas cachorro ou gato.");
        }
    }
}
