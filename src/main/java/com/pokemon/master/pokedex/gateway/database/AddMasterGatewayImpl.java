package com.pokemon.master.pokedex.gateway.database;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.*;
import com.pokemon.master.pokedex.gateway.database.entity.PokemonMasterEntity;
import com.pokemon.master.pokedex.gateway.database.repository.PokemonMasterRepository;
import com.pokemon.master.pokedex.gateway.database.translator.PokemonMasterDomainToPokemonMasterEntityTranslator;
import com.pokemon.master.pokedex.gateway.database.translator.PokemonMasterEntityToPokemonMasterDomainTranslator;
import com.pokemon.master.pokedex.gateway.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AddMasterGatewayImpl implements AddMasterGateway {

    private final PokemonMasterRepository pokemonMasterRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddMasterGatewayImpl.class);

    @Autowired
    public AddMasterGatewayImpl(PokemonMasterRepository pokemonMasterRepository) {
        this.pokemonMasterRepository = pokemonMasterRepository;
    }

    @Override
    public PokemonMasterDomain add(PokemonMasterDomain pokemonMasterDomain) throws AddMasterPokemonGatewayException {
        try {
            PokemonMasterEntity pokemonMasterEntity = PokemonMasterDomainToPokemonMasterEntityTranslator.translate(pokemonMasterDomain);
            return PokemonMasterEntityToPokemonMasterDomainTranslator.translate(pokemonMasterRepository.save(pokemonMasterEntity));
        } catch (Exception ex) {
            LOGGER.error("erro ao salvar mestre pokemon {}", pokemonMasterDomain);
            throw new AddMasterPokemonGatewayException(ex.getMessage());
        }

    }
}
