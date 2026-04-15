package br.com.alura.adopet.api.validacao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidationException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidacaoPetComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao{

    private final AdocaoRepository adocaoRepository;
    private final PetRepository petRepository;

    public ValidacaoPetComAdocaoEmAndamento(AdocaoRepository adocaoRepository,
                                            PetRepository petRepository) {
        this.adocaoRepository = adocaoRepository;
        this.petRepository = petRepository;
    }

    @Override
    public void validar(SolicitacaoAdocaoDTO dto) {
        List<Adocao> adocoes = adocaoRepository.findAll();
        Pet pet = petRepository.getReferenceById(dto.petId());
        for (Adocao a : adocoes) {
            if (a.getPet().equals(pet) && a.getStatus().equals(StatusAdocao.AGUARDANDO_AVALIACAO)) {
                throw new ValidationException("Pet já está aguardando avaliação para ser adotado!");
            }
        }
    }
}
