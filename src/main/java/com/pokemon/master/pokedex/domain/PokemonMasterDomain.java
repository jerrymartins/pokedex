package com.pokemon.master.pokedex.domain;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@With
public class PokemonMasterDomain extends RepresentationModel<PokemonMasterDomain>{
    public String idMaster;

    @NotNull
    @NotEmpty(message = "nome não pode estar vazio")
    public String name;

    @NotNull
    @NotEmpty(message = "genero não pode estar vazio")
    public String gender;

    @NotNull
    @NotEmpty(message = "cidade natal não pode estar vazia")
    public String homeTown;

    public String region;

    public StarterPokemonEnum starterPokemon;

    public List<PokemonDomain> pokemons;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class PokemonDomain extends RepresentationModel<PokemonDomain> {
        public Integer idPokemon;
        public String name;
        public Integer height;
        public Integer weight;
        public Boolean active;
    }

}

