package com.pokemon.master.pokedex.gateway;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.exception.PokemonMasterNotFoundException;
import com.pokemon.master.pokedex.gateway.exception.SearchPokemonMasterException;

import java.util.List;

public interface SearchMasterGateway {
    PokemonMasterDomain searchById(String id) throws PokemonMasterNotFoundException;
    List<PokemonMasterDomain> all() throws SearchPokemonMasterException;
}
