package com.pokemon.master.pokedex.gateway.database.translator;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.database.entity.PokemonMasterEntity;
import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;

@UtilityClass
public class PokemonEntityToPokemonDomainTranslator {

    public PokemonMasterDomain.PokemonDomain translate(PokemonMasterEntity.PokemonEntity pokemonEntity) {
        Assert.notNull(pokemonEntity, "pokemon não pode ser nulo");
        return PokemonMasterDomain.PokemonDomain.builder()
                .idPokemon(pokemonEntity.getIdPokemon())
                .name(pokemonEntity.getName())
                .active(pokemonEntity.active)
                .height(pokemonEntity.getHeight())
                .weight(pokemonEntity.getWeight())
                .build();
    }
}
