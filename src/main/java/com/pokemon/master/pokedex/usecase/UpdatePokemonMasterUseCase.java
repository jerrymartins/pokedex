package com.pokemon.master.pokedex.usecase;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.UpdateMasterGateway;
import com.pokemon.master.pokedex.gateway.exception.UpdateMasterPokemonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UpdatePokemonMasterUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdatePokemonMasterUseCase.class);
    private final UpdateMasterGateway updateMasterGateway;

    public UpdatePokemonMasterUseCase(UpdateMasterGateway updateMasterGateway) {
        this.updateMasterGateway = updateMasterGateway;
    }

    public void execute(String id, PokemonMasterDomain pokemonMasterDomain) throws UpdateMasterPokemonException {
        LOGGER.info("atualizando mestre");
        updateMasterGateway.update(id, pokemonMasterDomain);
    }
}
