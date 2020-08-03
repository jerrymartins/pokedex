package com.pokemon.master.pokedex.gateway;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.exception.SearchPokemonException;

public interface SearchPokemonGateway {
    PokemonMasterDomain.PokemonDomain searchByName(String name) throws SearchPokemonException;
}