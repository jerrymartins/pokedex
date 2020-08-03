package com.pokemon.master.pokedex.gateway.database.translator;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.database.entity.PokemonMasterEntity;
import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;

@UtilityClass
public class PokemonDomainToPokemonEntityTranslator {

    public PokemonMasterEntity.PokemonEntity translate(PokemonMasterDomain.PokemonDomain pokemonDomain) {
        Assert.notNull(pokemonDomain, "pokemon não pode ser nulo");
        return PokemonMasterEntity.PokemonEntity.builder()
                .idPokemon(pokemonDomain.getIdPokemon())
                .name(pokemonDomain.getName())
                .active(pokemonDomain.active)
                .height(pokemonDomain.getHeight())
                .weight(pokemonDomain.getWeight())
                .build();
    }
}
