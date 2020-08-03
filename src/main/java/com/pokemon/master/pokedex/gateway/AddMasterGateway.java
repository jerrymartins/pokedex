package com.pokemon.master.pokedex.gateway;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.exception.AddMasterPokemonGatewayException;

public interface AddMasterGateway {
    PokemonMasterDomain add(PokemonMasterDomain pokemonMasterDomain) throws AddMasterPokemonGatewayException;
}
