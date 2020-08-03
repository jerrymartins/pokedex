package com.pokemon.master.pokedex.gateway.feign.translator;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.feign.model.PokemonResponseFeign;
import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;

@UtilityClass
public class PokemonResponseFeignToPokemonDomainTranslator {

    public PokemonMasterDomain.PokemonDomain translate(PokemonResponseFeign pokemonResponseFeign) {
        Assert.notNull(pokemonResponseFeign, "pokemon não pode ser nulo");
        return PokemonMasterDomain.PokemonDomain.builder()
                .idPokemon(pokemonResponseFeign.getId())
                .name(pokemonResponseFeign.getName())
                .active(pokemonResponseFeign.active)
                .height(pokemonResponseFeign.getHeight())
                .weight(pokemonResponseFeign.getWeight())
                .build();
    }
}
