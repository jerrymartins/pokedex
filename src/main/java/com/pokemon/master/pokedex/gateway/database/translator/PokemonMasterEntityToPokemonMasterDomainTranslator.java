package com.pokemon.master.pokedex.gateway.database.translator;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.domain.StarterPokemonEnum;
import com.pokemon.master.pokedex.gateway.database.entity.PokemonMasterEntity;
import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;

import java.util.stream.Collectors;

@UtilityClass
public class PokemonMasterEntityToPokemonMasterDomainTranslator {

    public PokemonMasterDomain translate(PokemonMasterEntity pokemonMasterEntity) {
        Assert.notNull(pokemonMasterEntity, "mestre não pode ser nulo");
        return PokemonMasterDomain.builder()
                .idMaster(pokemonMasterEntity.getIdMaster())
                .gender(pokemonMasterEntity.getGender())
                .homeTown(pokemonMasterEntity.getHomeTown())
                .name(pokemonMasterEntity.getName())
                .pokemons(pokemonMasterEntity.getPokemons().stream().map(PokemonEntityToPokemonDomainTranslator::translate).collect(Collectors.toList()))
                .region(pokemonMasterEntity.getRegion())
                .starterPokemon(StarterPokemonEnum.valueOf(pokemonMasterEntity.getStarterPokemon().toUpperCase()))
                .build();
    }
}
