package com.pokemon.master.pokedex.gateway.database.translator;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.database.entity.PokemonMasterEntity;
import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;

import java.util.stream.Collectors;

@UtilityClass
public class PokemonMasterDomainToPokemonMasterEntityTranslator {

    public PokemonMasterEntity translate(PokemonMasterDomain pokemonMasterDomain) {
        Assert.notNull(pokemonMasterDomain, "mestre não pode ser nulo");
        return PokemonMasterEntity.builder()
                .idMaster(pokemonMasterDomain.getIdMaster())
                .gender(pokemonMasterDomain.getGender())
                .homeTown(pokemonMasterDomain.getHomeTown())
                .name(pokemonMasterDomain.getName())
                .pokemons(pokemonMasterDomain.getPokemons().stream().map(PokemonDomainToPokemonEntityTranslator::translate).collect(Collectors.toList()))
                .region(pokemonMasterDomain.getRegion())
                .starterPokemon(pokemonMasterDomain.getStarterPokemon().getName().toUpperCase())
                .build();
    }
}
