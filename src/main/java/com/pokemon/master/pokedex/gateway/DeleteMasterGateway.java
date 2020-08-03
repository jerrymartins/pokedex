package com.pokemon.master.pokedex.gateway;

import com.pokemon.master.pokedex.gateway.exception.DeletePokemonMasterException;

public interface DeleteMasterGateway {
    void delete(String id) throws DeletePokemonMasterException;
}
