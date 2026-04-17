package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.tutor.TutorCadastroDTO;
import br.com.alura.adopet.api.dto.tutor.TutorResponseDTO;
import br.com.alura.adopet.api.dto.tutor.TutorUpdateDTO;
import br.com.alura.adopet.api.exception.ValidationException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Transactional
    public TutorResponseDTO cadastrar(TutorCadastroDTO dto) {
        verificaDuplicatasCadastro(dto);
        Tutor tutor = new Tutor(dto);
        tutorRepository.save(tutor);
        return new TutorResponseDTO(tutor);
    }

    @Transactional
    public TutorResponseDTO atualizar(Long id, TutorUpdateDTO dto) {
        try {
            Tutor tutor = tutorRepository.getReferenceById(id);
            verificaDuplicatasAtualizacao(tutor, dto);
            tutor.atualizar(dto);
            tutorRepository.save(tutor);
            return new TutorResponseDTO(tutor);
        } catch (EntityNotFoundException ex) {
            throw new ValidationException("Nenhum tutor encontrado para o ID: " + id);
        }
    }

    private void verificaDuplicatasCadastro(TutorCadastroDTO dto) {
        if (tutorRepository.existsByTelefone(dto.telefone()) ||
                tutorRepository.existsByEmail(dto.email())) {
            throw new ValidationException("Dados já cadastrados para outro tutor!");
        }
    }

    private void verificaDuplicatasAtualizacao(Tutor tutor, TutorUpdateDTO dto) {
        if ((!dto.telefone().equals(tutor.getTelefone()) && tutorRepository.existsByTelefone(dto.telefone())) ||
                (!dto.email().equals(tutor.getEmail()) && tutorRepository.existsByEmail(dto.email()))) {
            throw new ValidationException("Dados já cadastrados para outro tutor!");
        }
    }
}