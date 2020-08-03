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

import java.util.List;


@Service
public class AddPokemonToMasterGatewayImpl implements AddPokemonToMasterGateway {

    private final PokemonMasterRepository pokemonMasterRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddPokemonToMasterGatewayImpl.class);

    @Autowired
    public AddPokemonToMasterGatewayImpl(PokemonMasterRepository pokemonMasterRepository) {
        this.pokemonMasterRepository = pokemonMasterRepository;
    }
    @Override
    public List<PokemonMasterDomain.PokemonDomain> addPokemon(PokemonMasterDomain pokemonMasterDomain) throws AddPokemonToMasterGatewayException {
        try {
            PokemonMasterEntity masterEntity = pokemonMasterRepository.save(PokemonMasterDomainToPokemonMasterEntityTranslator.translate(pokemonMasterDomain));
            PokemonMasterDomain masterDomain = PokemonMasterEntityToPokemonMasterDomainTranslator.translate(masterEntity);
            return masterDomain.getPokemons();
        } catch (Exception exception) {
            LOGGER.error("Problemas ao salvar mestre com nova lista de pokemons {}", exception.getMessage());
            throw new AddPokemonToMasterGatewayException("Problemas ao salvar mestre com nova lista de pokemons");
        }
    }
}
