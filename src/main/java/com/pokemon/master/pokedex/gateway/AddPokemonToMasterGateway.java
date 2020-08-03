package com.pokemon.master.pokedex.gateway;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.exception.AddPokemonToMasterGatewayException;

import java.util.List;

public interface AddPokemonToMasterGateway {
    List<PokemonMasterDomain.PokemonDomain> addPokemon(PokemonMasterDomain pokemonMasterDomain) throws AddPokemonToMasterGatewayException;
}
