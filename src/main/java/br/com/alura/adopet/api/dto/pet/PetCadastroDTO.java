package br.com.alura.adopet.api.dto.pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetCadastroDTO(
        @NotBlank String tipo,
        @NotBlank String nome,
        @NotBlank String raca,
        @NotNull Integer idade,
        @NotBlank  String cor,
        @NotNull Float peso,
        @NotNull Long abrigoId
) {
}
