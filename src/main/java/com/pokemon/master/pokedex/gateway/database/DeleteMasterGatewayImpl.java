package com.pokemon.master.pokedex.gateway.database;

import com.pokemon.master.pokedex.gateway.*;
import com.pokemon.master.pokedex.gateway.database.repository.PokemonMasterRepository;
import com.pokemon.master.pokedex.gateway.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteMasterGatewayImpl implements DeleteMasterGateway {

    private final PokemonMasterRepository pokemonMasterRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteMasterGatewayImpl.class);

    @Autowired
    public DeleteMasterGatewayImpl(PokemonMasterRepository pokemonMasterRepository) {
        this.pokemonMasterRepository = pokemonMasterRepository;
    }

    @Override
    public void delete(String id) throws DeletePokemonMasterException {
        try {
            pokemonMasterRepository.deleteById(id);
        } catch (Exception exception) {
            LOGGER.error("Problemas ao deletar mestre pokemon {} {}", id, exception.getMessage());
            throw new DeletePokemonMasterException("Problemas ao deletar mestre pokemon");
        }
    }
}
