package com.pokemon.master.pokedex.gateway.feign.translator;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.feign.model.PokemonResponseFeign;
import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;

@UtilityClass
public class PokemonDomainToPokemonResponseFeignTranslator {

    public PokemonResponseFeign translate(PokemonMasterDomain.PokemonDomain pokemonDomain) {
        Assert.notNull(pokemonDomain, "pokemon não pode ser nulo");
        return PokemonResponseFeign.builder()
                .id(pokemonDomain.getIdPokemon())
                .name(pokemonDomain.getName())
                .active(pokemonDomain.active)
                .height(pokemonDomain.getHeight())
                .weight(pokemonDomain.getWeight())
                .build();
    }
}
