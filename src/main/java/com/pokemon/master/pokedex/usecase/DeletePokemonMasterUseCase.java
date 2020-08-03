package com.pokemon.master.pokedex.usecase;

import com.pokemon.master.pokedex.gateway.DeleteMasterGateway;
import com.pokemon.master.pokedex.gateway.exception.DeletePokemonMasterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DeletePokemonMasterUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeletePokemonMasterUseCase.class);
    private final DeleteMasterGateway deleteMasterGateway;

    public DeletePokemonMasterUseCase(DeleteMasterGateway deleteMasterGateway) {
        this.deleteMasterGateway = deleteMasterGateway;
    }

    public void execute(String id) throws DeletePokemonMasterException {
        LOGGER.info("deletando mestre");
        deleteMasterGateway.delete(id);
    }
}
