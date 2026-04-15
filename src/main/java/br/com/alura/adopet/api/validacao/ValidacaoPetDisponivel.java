package br.com.alura.adopet.api.validacao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidationException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetDisponivel implements ValidacaoSolicitacaoAdocao {

    private final PetRepository petRepository;

    public ValidacaoPetDisponivel(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void validar(SolicitacaoAdocaoDTO dto) {
        Pet pet = petRepository.getReferenceById(dto.petId());
        if (pet.getAdotado()) {
            throw new ValidationException("Pet já foi adotado!");
        }
    }
}
