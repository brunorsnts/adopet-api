package br.com.alura.adopet.api.dto.tutor;

import br.com.alura.adopet.api.model.Tutor;

public record TutorResponseDTO (
        Long id,
        String nome,
        String telefone,
        String email
){
    public TutorResponseDTO(Tutor tutor) {
        this(tutor.getId(), tutor.getNome(), tutor.getTelefone(), tutor.getEmail());
    }
}
