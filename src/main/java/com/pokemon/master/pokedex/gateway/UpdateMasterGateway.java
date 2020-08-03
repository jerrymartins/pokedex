package com.pokemon.master.pokedex.gateway;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.exception.UpdateMasterPokemonException;

public interface UpdateMasterGateway {
    void update(String id, PokemonMasterDomain pokemonMasterDomain) throws UpdateMasterPokemonException;
}
